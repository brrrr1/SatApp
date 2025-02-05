# SatApp

## Descripción
Este proyecto es un sistema de gestión de incidencias diseñado para facilitar el registro, seguimiento y resolución de incidencias dentro de un colegio/instituto. La aplicación permite a los usuarios registrar incidencias, asignarlas a técnicos, agregar notas, gestionar ubicaciones y clasificarlas en diferentes categorías.

## Tecnologías utilizadas
- **Backend**: Spring Boot (Java)
- **Base de datos**: PostgreSQL
- **Contenedores**: Docker

## Estructura del Proyecto
- **Controladores**: Gestionan las peticiones HTTP y dirigen la lógica hacia los servicios adecuados.
- **Servicios**: Contienen la lógica de negocio y gestionan la comunicación con los repositorios.
- **Repositorios**: Interactúan con la base de datos mediante JPA/Hibernate.
- **Entidades**: Representan las tablas de la base de datos.
- **DTOs**: Objetos para transferencia de datos entre capas.
- **Excepciones**: Manejadores de errores y respuestas personalizadas.

## Endpoints de la API

### Incidencias
- **GET** `/incidencia/{id}` - Obtiene una incidencia por su ID.
- **PUT** `/incidencia/{id}` - Edita una incidencia.
- **DELETE** `/incidencia/{id}` - Borra una incidencia.
- **GET** `/incidencia/` - Obtiene todas las incidencias.
- **POST** `/incidencia/` - Crea una incidencia.
- **GET** `/incidencia/{alumnoId}/incidencias` - Obtiene las incidencias de un alumno por su ID.
- **GET** `/incidencia/filtrar` - Filtra incidencias según criterios.

### Ubicación
- **GET** `/ubicacion/{id}` - Obtiene una ubicación.
- **PUT** `/ubicacion/{id}` - Edita una ubicación.
- **DELETE** `/ubicacion/{id}` - Borra una ubicación.
- **GET** `/ubicacion/` - Obtiene todas las ubicaciones.
- **POST** `/ubicacion/` - Crea una ubicación.

### Personal
- **GET** `/personal/` - Obtiene todos los miembros del personal.
- **POST** `/personal/` - Crea un miembro del personal.
- **GET** `/personal/{id}` - Obtiene un miembro del personal por su ID.

### Técnico
- **GET** `/tecnico/` - Obtiene todos los técnicos.
- **POST** `/tecnico/` - Crea un técnico.
- **GET** `/tecnico/{id}` - Obtiene un técnico por su ID.

### Nota
- **PUT** `/nota/{notaId}` - Edita una nota.
- **GET** `/nota/{id}` - Obtiene una nota.
- **POST** `/nota/` - Crea una nota.
- **GET** `/nota/` - Obtiene todas las notas.

### Alumno
- **GET** `/alumno/{id}` - Obtiene un alumno por su ID.
- **PUT** `/alumno/{id}` - Edita un alumno.
- **DELETE** `/alumno/{id}` - Borra un alumno.
- **POST** `/alumno/{alumnoId}/historico` - Crea el histórico de cursos de un alumno.
- **GET** `/alumno/` - Obtiene todos los alumnos.
- **POST** `/alumno/` - Crea un alumno.

### Equipo
- **GET** `/equipo/{id}` - Obtiene un equipo.
- **PUT** `/equipo/{id}` - Edita un equipo.
- **DELETE** `/equipo/{id}` - Borra un equipo.
- **GET** `/equipo/` - Obtiene todos los equipos.
- **POST** `/equipo/` - Crea un equipo.

### Usuario
- **GET** `/usuario/{id}` - Obtiene un usuario por su ID.
- **PUT** `/usuario/{id}` - Edita un usuario.
- **DELETE** `/usuario/{id}` - Borra un usuario.
- **GET** `/usuario/` - Obtiene todos los usuarios.
- **POST** `/usuario/` - Crea un usuario.

### Categoría
- **GET** `/categoria/{id}` - Obtiene una categoría.
- **PUT** `/categoria/{id}` - Edita una categoría.
- **GET** `/categoria/` - Obtiene todas las categorías.
- **POST** `/categoria/` - Crea una categoría.

