# Flujo de desarrollo para Kanchay Backend

Estas reglas aplican a todo cambio realizado en este repositorio.

## Flujo obligatorio

1. Revisar el estado actual del repositorio y entender el flujo afectado antes de editar.
2. Definir el cambio en una tarea concreta, incluyendo el comportamiento esperado y sus casos límite.
3. Mantener la arquitectura por capas existente: `controller`, `service`, `repository`, `model`, `dto` y `security`.
4. Implementar el cambio más pequeño que resuelva la tarea. No mezclar refactors ni cambios de formato sin relación.
5. Validar entradas en los DTO y conservar las reglas de seguridad existentes. No exponer contraseñas, tokens ni secretos en código, logs o respuestas.
6. Añadir o actualizar pruebas para el comportamiento modificado, especialmente en autenticación, autorización, validación y endpoints.
7. Ejecutar las validaciones antes de solicitar revisión.
8. Revisar el diff completo, confirmar que no contiene archivos generados ni credenciales y documentar cualquier decisión relevante.

## Comandos de validación

Usar el wrapper incluido en el repositorio:

```powershell
.\mvnw.cmd test
.\mvnw.cmd verify
```

Para una iteración rápida puede ejecutarse:

```powershell
.\mvnw.cmd test -DskipTests=false
```

El cambio no está listo si falla compilación, pruebas o verificación, salvo que el fallo sea previo y quede documentado.

## Convenciones

- Usar Java 21 y las convenciones existentes de Spring Boot.
- Mantener nombres y paquetes en inglés, siguiendo el estilo existente.
- Los controladores reciben solicitudes y delegan; la lógica de negocio vive en servicios.
- Los repositorios se encargan de persistencia; evitar lógica de negocio en ellos.
- Usar DTOs para las fronteras HTTP y no devolver entidades JPA directamente sin una razón clara.
- Mantener respuestas y códigos HTTP consistentes con los endpoints existentes.
- Preferir cambios compatibles con el frontend. Si cambia un contrato, actualizar documentación, pruebas y frontend en coordinación.
- No editar `target/` ni otros artefactos generados.

## Revisión y entrega

Antes de abrir un pull request, comprobar:

- La tarea y los casos límite están cubiertos.
- Las pruebas relevantes pasan.
- La autorización se verifica en las rutas protegidas.
- Los errores tienen respuestas controladas y no filtran información sensible.
- El diff contiene únicamente cambios relacionados.
- El pull request explica qué cambió, cómo se validó y cualquier migración o configuración necesaria.
