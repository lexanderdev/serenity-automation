# Roadmap — Serenity Automation Project

## Alta Prioridad

- [x] **Reintentos en tests fallidos (retry)**
  - `serenity.retry.tests = 2` configurado en `serenity.conf` y `serenity.properties`
  - Reintenta hasta 2 veces antes de marcar el test como fallido

- [x] **Ejecución paralela**
  - Configurado en `src/test/resources/junit-platform.properties`
  - Strategy `dynamic` con factor `0.5` (50% de CPUs disponibles)
  - Reduce el tiempo total de ejecución al escalar la suite

- [x] **Esperas explícitas / manejo de waits**
  - `WaitUntil` de Serenity Screenplay aplicado en `NavigateToPim`, `AddEmployee` y `SearchEmployee`
  - Timeout máximo de 10 segundos por espera

## Media Prioridad

- [x] **Externalizar datos de prueba**
  - Datos centralizados en Firebase Realtime Database
  - `FirebaseClient` singleton lee credenciales desde variables de entorno
  - Datos externalizados: login válido, agregar empleado y búsqueda de empleado

- [ ] **Ampliar cobertura de features**
  - Módulos pendientes: Leave, Time, Recruitment
  - Seguir el mismo patrón Screenplay existente

- [ ] **Separación de tags en CI**
  - Ejecutar solo `@smoke` en cada PR
  - Ejecutar `@regression` en scheduled nightly run

## Baja Prioridad

- [ ] **Soporte multi-navegador**
  - Extender configuración a Firefox y Edge con WebDriverManager

- [ ] **Publicar reporte Serenity en GitHub Pages**
  - Permitir al equipo ver el reporte sin descargar artefactos del CI
