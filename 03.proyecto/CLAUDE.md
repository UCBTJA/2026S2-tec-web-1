# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Contexto del repositorio

- La raíz git es `Almacen/` (dos niveles arriba de este directorio). Está organizada por fases del proyecto de la materia Taller de Sistemas (UCB): `01.bus`, `02.req`, `03.des`, `04.imp`, `05.dep`, `06.pro`. Este proyecto Spring Boot vive en `04.imp/almacen` (implementación); las demás carpetas de fase están actualmente vacías.
- El proyecto está en estado inicial (scaffold de Spring Initializr): solo existe `AlmacenApplication` y un test `contextLoads`.

## Stack

- Java 25 (toolchain de Gradle), Spring Boot 4.1.1, Gradle 9.7.1 vía wrapper.
- Dependencias: `spring-boot-starter-webmvc` (REST/servlet) y el driver `postgresql` (runtimeOnly). No hay JPA/JDBC starter todavía, por lo que no se configura ningún datasource.
- Paquete base: `ucb.tallersis.almacen`.
- Configuración en `src/main/resources/application.yaml` (YAML, no `.properties`); el servidor corre en el puerto **8090**.

## Comandos

Ejecutar desde `04.imp/almacen`:

```bash
./gradlew build                 # compilar + tests
./gradlew bootRun               # levantar la app en http://localhost:8090
./gradlew test                  # todos los tests (JUnit 5)
./gradlew test --tests 'ucb.tallersis.almacen.AlmacenApplicationTests'           # una clase
./gradlew test --tests 'ucb.tallersis.almacen.AlmacenApplicationTests.contextLoads'  # un método
./gradlew clean
```

No hay linter ni formateador configurado.

## Notas

- `bin/` y `build/` son salidas de compilación (IDE / Gradle); no editarlas.
- Al agregar Spring Data JPA o JDBC, habrá que definir `spring.datasource.*` en `application.yaml`; de lo contrario `@SpringBootTest` fallará al cargar el contexto.
