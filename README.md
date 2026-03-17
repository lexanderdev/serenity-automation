# Proyecto de Automatización de Pruebas para OrangeHRM

![Serenity BDD](https://img.shields.io/badge/Serenity%20BDD-4.2.34-00C4CC?style=for-the-badge&logoColor=white)
![Java](https://img.shields.io/badge/Java-21-FF6B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Cucumber](https://img.shields.io/badge/Cucumber-7.15.0-00BF63?style=for-the-badge&logo=cucumber&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-4.21.0-00D084?style=for-the-badge&logo=selenium&logoColor=white)
![Firebase](https://img.shields.io/badge/Firebase-Realtime%20DB-FFA000?style=for-the-badge&logo=firebase&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-8.7-1EBBCC?style=for-the-badge&logo=gradle&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/GitHub%20Actions-CI%2FCD-0EA5E9?style=for-the-badge&logo=githubactions&logoColor=white)
![SonarCloud](https://img.shields.io/badge/SonarCloud-Quality-FF6D00?style=for-the-badge&logo=sonarcloud&logoColor=white)

Este proyecto contiene pruebas de aceptación automatizadas para la aplicación [OrangeHRM](https://opensource-demo.orangehrmlive.com), desarrollado utilizando el framework Serenity BDD y el patrón de diseño Screenplay.

## Características

- **Serenity BDD**: Genera documentación viva y reportes detallados sobre la ejecución de las pruebas.
- **Patrón Screenplay**: Pruebas organizadas en Tasks, Questions y UserInterface con separación clara de responsabilidades.
- **Behavior-Driven Development (BDD)**: Escenarios escritos en Gherkin (`.feature`), organizados por módulo funcional.
- **Gradle**: Gestiona las dependencias y la ejecución de las pruebas.
- **Configuración Multi-ambiente**: Soporta los entornos `default`, `dev`, `staging` y `prod`.
- **Lombok**: Reduce el código repetitivo en las clases de modelo.

## Prerrequisitos

- **Java JDK** 21 o superior
- **Google Chrome** (última versión)

## Instalación

```sh
git clone <URL-DEL-REPOSITORIO>
cd orange
```

El proyecto usa **Gradle Wrapper** — no es necesario instalar Gradle manualmente.

## Ejecución de las Pruebas

### Todos los escenarios (modo headless)
```sh
./gradlew clean test aggregate
```

### Con navegador visible (modo interactivo)
```sh
./gradlew clean test aggregate -Dchrome.switches="--start-maximized"
```

### Con navegador visible y ejecución lenta (para depuración)
```sh
./gradlew clean test aggregate -Dchrome.switches="--start-maximized" -Dserenity.step.delay=1000
```

### Por ambiente
```sh
./gradlew clean test aggregate -Denvironment=staging
./gradlew clean test aggregate -Denvironment=dev
```

### Por tag
```sh
./gradlew clean test aggregate -Dcucumber.filter.tags="@smoke"
./gradlew clean test aggregate -Dcucumber.filter.tags="@pim"
./gradlew clean test aggregate -Dcucumber.filter.tags="@authentication"
```

## Escenarios Implementados

### Módulo: Autenticación (`features/authentication/login.feature`)

| Escenario | Tag | Estado |
|---|---|---|
| Inicio de sesión exitoso con credenciales válidas | `@smoke @login-exitoso` | ✅ Completado |
| Inicio de sesión fallido — credenciales inválidas (×2) | `@regression @login-fallido` | ✅ Completado |

### Módulo: PIM — Gestión de Empleados (`features/pim/employee_management.feature`)

| Escenario | Tag | Estado |
|---|---|---|
| Visualización de la lista de empleados | `@smoke @employee-list` | ✅ Completado |
| Agregar un nuevo empleado | `@regression @add-employee` | ✅ Completado |
| Búsqueda de un empleado existente por nombre | `@regression @search-employee` | ✅ Completado |

## Estructura del Proyecto

```
src/test/
├── java/co/com/certification/orangetest/
│   ├── runners/          # Configuración del runner (tags, glue, plugins)
│   ├── stepdefinitions/  # Conecta los pasos Gherkin con Tasks y Questions
│   ├── tasks/            # Lo que el actor HACE en la UI
│   │   └── pim/
│   ├── questions/        # Lo que el actor VERIFICA en la UI
│   ├── userinterface/    # Locators de los elementos de la página
│   │   └── pim/
│   ├── model/            # POJOs para DataTables de Cucumber
│   └── datatables/       # Transformadores de DataTable a modelos
└── resources/
    ├── features/
    │   ├── authentication/
    │   └── pim/
    └── serenity.conf     # URLs por ambiente
```

## Reporte de Resultados

Después de cada ejecución el reporte HTML queda en:

```
target/site/serenity/index.html
```

## Protección de la rama main

La rama `main` tiene las siguientes reglas configuradas en GitHub:

- **Pull Request obligatorio** — no se permite push directo, todo entra por PR con al menos 1 aprobación
- **CI requerido** — el job `build-and-test` debe pasar en verde antes de mergear
- **Rama actualizada** — el PR debe estar al día con `main` antes de mergear
- **Historial lineal** — se requiere squash o rebase, no merge commits

## Mejoras Implementadas

### Integración continua con GitHub Actions + SonarCloud
- Pipeline CI configurado en `.github/workflows/ci.yml`
- Se dispara automáticamente en cada Pull Request a `main` o `develop`
- Ejecuta los tests, genera el reporte de Serenity y analiza la calidad del código con SonarCloud
- Artefactos de test y Serenity disponibles en cada ejecución del pipeline

### Reintentos automáticos en tests fallidos
- Configurado `serenity.retry.tests = 2` en `serenity.conf` y `serenity.properties`
- Si un escenario falla por inestabilidad (red, timing), se reintenta hasta 2 veces antes de marcarlo como fallido
- Los tests que pasan en reintento se reportan como `COMPROMISED` en Serenity para identificar flakiness

### Ejecución paralela
- Configurado en `src/test/resources/junit-platform.properties`
- Strategy `dynamic` con factor `0.5` — usa el 50% de los CPUs disponibles
- Resultado medido: **3m 25s** de ejecución real vs **10m 24s** de tiempo acumulado secuencial (ahorro de ~7 minutos con 6 escenarios)

### Integración con Firebase Realtime Database
- Los datos de prueba se centralizan en Firebase en lugar de estar hardcodeados en los feature files
- `FirebaseClient` (singleton) inicializa la conexión con credenciales desde variables de entorno y expone métodos de lectura síncronos
- Datos externalizados: credenciales de login válidas, datos de empleado para agregar y nombre para búsqueda
- Las variables de entorno se cargan automáticamente desde `.env` en local (via `build.gradle`) y desde GitHub Secrets en CI

**Estructura de datos en Firebase Realtime Database:**
```json
{
  "test-data": {
    "login": {
      "valid": { "username": "...", "password": "..." }
    },
    "employees": {
      "add": { "firstName": "...", "lastName": "..." },
      "search": { "name": "..." }
    }
  }
}
```

**Variables de entorno requeridas:**
```
FIREBASE_PROJECT_ID
FIREBASE_PRIVATE_KEY
FIREBASE_CLIENT_EMAIL
FIREBASE_DATABASE_URL
```

## Tecnologías Utilizadas

- **[Serenity BDD](http://serenity-bdd.info/)** 4.2.1
- **[Cucumber](https://cucumber.io/)** 7.15.0
- **[Java](https://www.oracle.com/java/)** 21
- **[Gradle](https://gradle.org/)**
- **[JUnit 5](https://junit.org/junit5/)** 5.10.1
- **[Selenium WebDriver](https://www.selenium.dev/)** 4.21.0
- **[Lombok](https://projectlombok.org/)** 1.18.30
- **[Firebase Admin SDK](https://firebase.google.com/docs/admin/setup)** 9.2.0
