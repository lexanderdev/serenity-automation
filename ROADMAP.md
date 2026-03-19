# Roadmap — Serenity Automation Project

## Alta Prioridad

- [x] **Reintentos en tests fallidos (retry)**
  - `serenity.retry.tests = 2` configurado en `serenity.conf` y `serenity.properties`
  - Reintenta hasta 2 veces antes de marcar el test como fallido

- [x] **Ejecución paralela**
  - Configurado en `src/test/resources/junit-platform.properties`
  - Strategy `dynamic` con factor `0.5` (50% de CPUs disponibles)
  - Bug de thread-safety corregido al actualizar Serenity de 4.2.1 a 4.2.34

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

---

## Implementaciones Futuras

### Pruebas de Seguridad — OWASP ZAP
- **Condición previa:** aplicar cuando el proyecto pruebe una app propia (no un demo público)
- Modo recomendado: **Guided Scan con proxy** — ZAP intercepta el tráfico mientras los escenarios de Cucumber/Selenium se ejecutan, analizando exactamente los flujos ya definidos (login, PIM, etc.)
- Integrar como job independiente en `.github/workflows/ci.yml`, ejecutándose solo en merge a `main`

**Cómo quedaría el `ci.yml`:**
```yaml
security-scan:
  needs: build-and-test
  if: github.ref == 'refs/heads/main'
  runs-on: ubuntu-latest
  steps:
    - name: Checkout code
      uses: actions/checkout@v5

    - name: Start ZAP as proxy
      run: docker run -d -p 8080:8080 zaproxy/zap-stable zap.sh -daemon -port 8080 -host 0.0.0.0

    - name: Run tests through ZAP proxy
      env:
        JAVA_OPTS: "-Dhttp.proxyHost=localhost -Dhttp.proxyPort=8080"
        FIREBASE_PROJECT_ID: ${{ secrets.FIREBASE_PROJECT_ID }}
        FIREBASE_PRIVATE_KEY: ${{ secrets.FIREBASE_PRIVATE_KEY }}
        FIREBASE_CLIENT_EMAIL: ${{ secrets.FIREBASE_CLIENT_EMAIL }}
        FIREBASE_DATABASE_URL: ${{ secrets.FIREBASE_DATABASE_URL }}
      run: ./gradlew test -Dcucumber.filter.tags="@smoke"

    - name: Generate ZAP security report
      run: docker exec <zap-container> zap-cli report -o zap-report.html -f html

    - name: Upload ZAP report
      uses: actions/upload-artifact@v5
      with:
        name: ZAP Security Report
        path: zap-report.html
```
