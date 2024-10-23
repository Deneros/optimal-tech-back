# OptimalTech Backend

## Este proyecto es el backend de la aplicación OptimalTech, desarrollado con Spring Boot. Proporciona una API RESTful para gestionar productos y movimientos, interactuando con una base de datos PostgreSQL.

### Requisitos

- Java 21
- Docker (opcional para contenerización)
- Docker Compose (para levantar el backend junto con la base de datos)

### Instalación y ejecución

#### Sin Docker

1. Clona este repositorio:

    ```bash
    git clone https://github.com/Deneros/optimal-tech-back.git
    cd optimal-tech-back
    ```

2. Compila el proyecto con Gradle:

    ```bash
    ./gradlew build
    ```

3. Configura la conexión a la base de datos en el archivo `application.properties`:

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/optimaltechdb
    spring.datasource.username=root
    spring.datasource.password=root
    ```

4. Ejecuta la aplicación:

    ```bash
    ./gradlew bootRun
    ```

5. La API estará disponible en `http://localhost:8080`.

#### Con Docker

1. Construye la imagen de Docker:

    ```bash
    docker build -t optimaltech-backend .
    ```

2. Corre el contenedor de Docker:

    ```bash
    docker run -p 8080:8080 optimaltech-backend
    ```

3. La API estará disponible en `http://localhost:8080`.

### Despliegue con Docker Compose

Para facilitar el despliegue tanto del frontend como del backend, se debe tener un archivo `docker-compose.yml` en la raíz del proyecto que levante ambos servicios. Este archivo debe contener las configuraciones necesarias para levantar el frontend, el backend y la base de datos PostgreSQL.


### Tecnologías usadas

- Java 17
- Spring Boot
- PostgreSQL
