<div align="center">

# 🛒 Sistema de Ventas - Tienda de Don Pepe

Backend RESTful profesional desarrollado con Spring Boot para la gestión integral de ventas, usuarios, productos, categorías, carritos y seguridad JWT. 

![Java](https://img.shields.io/badge/Java_21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)
![Render](https://img.shields.io/badge/Render-46E3B7?style=for-the-badge&logo=render&logoColor=white)

---

### 👥 Equipo de Desarrollo Backend

| Integrante | Rol Principal | Ramas Asignadas |
|------------|---------------|-----------------|
| **Isaac Livaque** | Gestión de Usuarios, Clientes, Autenticación JWT y Spring Security, Despliegue en Render y Docker | `feature-usuario`, `feature-seguridad`, `feature-conexionBD` |
| **César U.** | Gestión de Carrito y Detalle Carrito | `feature-carrito` |
| **Sebastián A.** | Gestión de Categorías y Productos, Paginación e Integración | `feature-categoria`, `feature-producto` |
| **Jaime A.** | Prueba de Github Actions con Render, Gestión de Ventas, Lógica Transaccional y Detalle Venta  | `feature-ventas` |
| **Casey R.** | Mejora de interfaz gráfica, experiencia de usuario (UI/UX) e integración Frontend | `Soporte Frontend` |

</div>

---

## 📖 Sobre el Proyecto

El backend de la **Tienda de Don Pepe** es una API REST robusta desarrollada con Spring Boot. Proporciona toda la lógica de negocio para la plataforma de ventas, permitiendo administrar usuarios, clientes, productos, categorías, carritos y ventas. 

La aplicación destaca por implementar **Arquitectura Limpia**, utilizando fuertemente el **Patrón DTO (Data Transfer Object)** para separar la base de datos de las respuestas HTTP, el manejo centralizado de excepciones (Global Exception Handler) y la documentación automatizada con OpenAPI 3.0. Todo el sistema está protegido mediante JWT y control de acceso basado en roles.

---

## 🏗️ Arquitectura y Características Clave

### 🔐 Seguridad y Autenticación
- Autenticación Stateless mediante **JWT (JSON Web Tokens)**.
- Protección de endpoints por roles (`admin`, `cliente`).
- Encriptación de contraseñas mediante **BCrypt**.

### 📦 Gestión de Inventario (Paginada)
- CRUD completo de Productos y Categorías.
- **Paginación inteligente (`Pageable`)** para evitar sobrecarga de la base de datos.
- Validaciones estrictas de `@NotBlank`, `@Min` para evitar datos corruptos.

### 🛒 Carrito de Compras Persistente
- Carrito vinculado dinámicamente al Token del usuario logueado.
- Actualización de cantidades y cálculo de subtotales con precisión financiera (`BigDecimal`).

### 💰 Motor de Ventas (Transaccional)
- Endpoint de *Checkout* protegido con `@Transactional` (Rollback automático).
- Generación de historial inmutable de comprobantes.

---

## 🎯 Endpoints Principales (Documentados en Swagger)

*La documentación interactiva está disponible en la ruta `/v3/api-docs` y es compatible con Apidog y Postman.*

### 🔐 Autenticación y Usuarios
- `POST /api/auth/login` ➔ Genera el token JWT
- `POST /api/auth/register` ➔ Registra un nuevo cliente
- `GET /api/auth/me` ➔ Devuelve el perfil del token actual
- `GET /api/usuarios` ➔ *(Admin)* Lista usuarios
- `PUT /api/clientes/perfil` ➔ Actualiza datos del cliente

### 📂 Categorías e 📦 Inventario
- `GET /api/categorias` ➔ Lista para el ComboBox del Frontend
- `POST /api/categorias` ➔ *(Admin)* Crea categoría
- `GET /api/productos?page=0&size=10` ➔ Catálogo paginado y filtrado
- `POST /api/productos` ➔ *(Admin)* Registra producto
- `PUT /api/productos/{id}` ➔ *(Admin)* Edita producto
- `DELETE /api/productos/{id}` ➔ *(Admin)* Baja lógica (Inactivo)

### 🛒 Carrito de Compras
- `GET /api/carrito` ➔ Recupera el carrito del usuario logueado
- `POST /api/carrito/items` ➔ Agrega un producto
- `PUT /api/carrito/items/{id}` ➔ Actualiza cantidad exacta
- `DELETE /api/carrito/items/{id}` ➔ Quita un producto
- `DELETE /api/carrito/limpiar` ➔ Vacía el carrito

### 💰 Ventas y Facturación
- `POST /api/ventas/checkout` ➔ Procesa el carrito y genera la venta
- `GET /api/ventas/mis-compras` ➔ Historial personal del cliente
- `GET /api/ventas` ➔ *(Admin)* Historial general de la tienda
- `PUT /api/ventas/{id}/estado` ➔ *(Admin)* Cambia estado a PAGADO/ANULADO

---

## 🗄️ Modelo Relacional

<pre>
USUARIO (Credenciales y Roles)
│
└── CLIENTE (Datos personales)
     │
     ├── CARRITO (Activo/Abandonado)
     │      │
     │      └── DETALLE_CARRITO (Producto + Cantidad)
     │
     └── VENTA (Historial Inmutable)
            │
            └── DETALLE_VENTA (Precio al momento de compra)

CATEGORIA
│
└── PRODUCTO (Stock dinámico y Precio)
</pre>

---

## ⚙️ Tecnologías Utilizadas

- **Core:** Java 21, Spring Boot 3
- **Base de Datos:** PostgreSQL, Spring Data JPA, Hibernate
- **Seguridad:** Spring Security, JWT
- **Documentación:** Springdoc OpenAPI (Swagger UI)
- **Despliegue y Pruebas:** Render, Docker, Apidog

---

## 🌿 Flujo de Trabajo (GitFlow)

<pre>
main
│
├── dev
│
├── feature-usuario
├── feature-seguridad
├── feature-carrito
├── feature-categoria
├── feature-producto
├── feature-ventas
└── hotfix-DockerFile
</pre>

---

## 📁 Arquitectura del Proyecto

<pre>
src/main/java/group1/HD/Back/
 ├── Configs/         # Configuraciones globales (Swagger, Cors)
 ├── Controller/      # Puntos de entrada HTTP de la API REST
 ├── Dto/             # Envases Request/Response
 ├── Exception/       # Interceptor global de errores
 ├── Model/           # Entidades JPA (PostgreSQL)
 ├── Repository/      # Interfaces de acceso a datos
 ├── Security/        # Filtros JWT y Spring Security
 ├── Service/         # Lógica de negocio y transacciones
 └── BackApplication.java
</pre>