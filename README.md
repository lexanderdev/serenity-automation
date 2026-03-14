# Proyecto de Automatización de Pruebas para OrangeHRM

Este proyecto contiene pruebas de aceptación automatizadas para la aplicación OrangeHRM, desarrollado utilizando el framework Serenity BDD y el patrón de diseño Screenplay.

## 🌟 Características

*   **Serenity BDD**: Genera documentación viva y reportes detallados sobre la ejecución de las pruebas.
*   **Patrón Screenplay**: Fomenta la escritura de pruebas limpias, legibles y altamente mantenibles, centradas en las habilidades de un actor.
*   **Behavior-Driven Development (BDD)**: Las pruebas se escriben en Gherkin (`.feature`), lo que facilita la colaboración entre desarrolladores, QAs y stakeholders.
*   **Gradle**: Gestiona las dependencias del proyecto y facilita la ejecución de las pruebas.
*   **Configuración Multi-ambiente**: Permite ejecutar las pruebas contra diferentes entornos (`default`, `dev`, `staging`, `prod`) cambiando un solo parámetro.
*   **Lombok**: Reduce el código repetitivo (boilerplate) en las clases de modelo.

## 📋 Prerrequisitos

Antes de empezar, asegúrate de tener instalado:

*   **Java JDK**: Versión 21 o superior.
*   **Navegador Web**: Google Chrome (configurado por defecto).

## 🚀 Instalación y Configuración

1.  **Clona el repositorio**:
    ```sh
    git clone <URL-DEL-REPOSITORIO>
    ```

2.  **Navega al directorio del proyecto**:
    ```sh
    cd orange
    ```

El proyecto utiliza el **Gradle Wrapper**, por lo que no es necesario instalar Gradle manualmente. El wrapper descargará la versión correcta de Gradle automáticamente.

## ⚙️ Ejecución de las Pruebas

Puedes ejecutar las pruebas desde la línea de comandos utilizando los siguientes comandos de Gradle.

### Ejecución Estándar

Para ejecutar todas las pruebas utilizando el ambiente por defecto (`default`):

```sh
./gradlew clean test aggregate
```
*(En Windows, usa `gradlew.bat clean test aggregate`)*

### Ejecución por Ambientes

Para ejecutar las pruebas en un ambiente específico (definido en `src/test/resources/serenity.conf`), usa la propiedad `-Denvironment`:

```sh
# Ejecutar en el ambiente de Staging
./gradlew clean test aggregate -Denvironment=staging

# Ejecutar en el ambiente de Desarrollo
./gradlew clean test aggregate -Denvironment=dev
```

### Ejecución por Tags

Para ejecutar solo un subconjunto de escenarios, puedes filtrar por los tags de Cucumber definidos en tus archivos `.feature`:

```sh
# Ejecutar solo los escenarios con el tag @login
./gradlew clean test aggregate -Dcucumber.filter.tags="@login"
```

## 📊 Visualización de Reportes

Después de cada ejecución, Serenity BDD genera un reporte detallado en formato HTML. Puedes encontrarlo en la siguiente ruta:

```
target/site/serenity/index.html
```

Abre este archivo en tu navegador para ver un desglose completo de los resultados de las pruebas, incluyendo capturas de pantalla en cada paso.

## 🛠️ Tecnologías Utilizadas

*   **[Serenity BDD](http://serenity-bdd.info/)**: Framework de pruebas de aceptación.
*   **[Cucumber](https://cucumber.io/)**: Herramienta para BDD.
*   **[Java 21](https://www.oracle.com/java/)**: Lenguaje de programación.
*   **[Gradle](https://gradle.org/)**: Herramienta de automatización de compilación.
*   **[JUnit 5](https://junit.org/junit5/)**: Framework de pruebas para Java.
*   **[Lombok](https://projectlombok.org/)**: Librería para reducir código boilerplate.

---
Este `README.md` debería proporcionar una excelente primera impresión y toda la información necesaria para que cualquier persona pueda colaborar o ejecutar tu proyecto.
