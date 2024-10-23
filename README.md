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

# Documentación de la API de OptimalTech (Backend)

El backend de **OptimalTech** es una API RESTful desarrollada con **Spring Boot**. A continuación, se detalla la lista de endpoints disponibles para la gestión de productos y movimientos, así como la autenticación de usuarios.

## Endpoints

### Autenticación

#### `POST /auth/login`

**Descripción**: Autentica al usuario y devuelve un token JWT.

- **Request Body**:
  ```json
  {
    "username": "string",
    "password": "string"
  }
  ```

- **Response**:
  ```json
  {
    "token": "string"
  }
  ```

#### `POST /auth/register`

**Descripción**: Registra un nuevo usuario.

- **Request Body**:
  ```json
  {
    "username": "string",
    "password": "string",
    "email": "string"
  }
  ```

- **Response**:
  ```json
  {
    "message": "Usuario registrado con éxito"
  }
  ```

### Productos

#### `GET /api/v1/products`

**Descripción**: Obtiene una lista de todos los productos.

- **Response**:
  ```json
  [
    {
      "id": 1,
      "name": "Producto 1",
      "price": 10.99,
      "stock": 100,
      "stockMinimum": 10
    }
  ]
  ```

#### `GET /api/v1/products/{id}`

**Descripción**: Obtiene la información de un producto por su ID.

- **Path Parameter**:
  - `id` (integer): ID del producto.

- **Response**:
  ```json
  {
    "id": 1,
    "name": "Producto 1",
    "price": 10.99,
    "stock": 100,
    "stockMinimum": 10
  }
  ```

#### `POST /api/v1/products`

**Descripción**: Crea un nuevo producto.

- **Request Body**:
  ```json
  {
    "name": "Producto 1",
    "price": 10.99,
    "stock": 100,
    "stockMinimum": 10
  }
  ```

- **Response**:
  ```json
  {
    "id": 1,
    "name": "Producto 1",
    "price": 10.99,
    "stock": 100,
    "stockMinimum": 10
  }
  ```

#### `PUT /api/v1/products/{id}`

**Descripción**: Actualiza un producto existente.

- **Path Parameter**:
  - `id` (integer): ID del producto.

- **Request Body**:
  ```json
  {
    "name": "Producto Actualizado",
    "price": 12.99,
    "stock": 80,
    "stockMinimum": 5
  }
  ```

- **Response**:
  ```json
  {
    "id": 1,
    "name": "Producto Actualizado",
    "price": 12.99,
    "stock": 80,
    "stockMinimum": 5
  }
  ```

#### `DELETE /api/v1/products/{id}`

**Descripción**: Elimina un producto por su ID.

- **Path Parameter**:
  - `id` (integer): ID del producto.

- **Response**:
  ```json
  {
    "message": "Producto eliminado con éxito"
  }
  ```

### Movimientos

#### `GET /api/v1/movements`

**Descripción**: Obtiene una lista de todos los movimientos.

- **Response**:
  ```json
  [
    {
      "id": 1,
      "product": {
        "id": 1,
        "name": "Producto 1"
      },
      "quantity": 10,
      "type": "entry",
      "date": "2024-10-22T14:00:00Z"
    }
  ]
  ```

#### `POST /api/v1/movements`

**Descripción**: Crea un nuevo movimiento.

- **Request Body**:
  ```json
  {
    "productId": 1,
    "quantity": 10,
    "type": "entry"
  }
  ```

- **Response**:
  ```json
  {
    "id": 1,
    "product": {
      "id": 1,
      "name": "Producto 1"
    },
    "quantity": 10,
    "type": "entry",
    "date": "2024-10-22T14:00:00Z"
  }
  ```

#### `DELETE /api/v1/movements/{id}`

**Descripción**: Elimina un movimiento por su ID.

- **Path Parameter**:
  - `id` (integer): ID del movimiento.

- **Response**:
  ```json
  {
    "message": "Movimiento eliminado con éxito"
  }
  ```

## Gestión de Errores

- **400 Bad Request**: Se devuelve cuando los datos proporcionados no son válidos o faltan campos requeridos.
  - Ejemplo:
    ```json
    {
      "name": "El nombre del producto no debe estar vacío",
      "price": "El precio debe ser mayor o igual a 0"
    }
    ```

- **401 Unauthorized**: Se devuelve cuando el usuario no está autenticado.

- **404 Not Found**: Se devuelve cuando el recurso solicitado no existe.
  - Ejemplo:
    ```json
    {
      "error": "Producto no encontrado"
    }
    ```

- **500 Internal Server Error**: Se devuelve cuando ocurre un error interno en el servidor.

## Autenticación

Este proyecto utiliza autenticación basada en JWT (JSON Web Tokens). Para realizar operaciones en los endpoints protegidos, los usuarios deben incluir el token en el encabezado de la solicitud de la siguiente manera:

- **Authorization**: `Bearer {token}`

---
