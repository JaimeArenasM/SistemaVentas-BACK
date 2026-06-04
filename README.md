<div align="center">

# 🛒 Sistema de Ventas - Tienda de Don Pepe

Backend RESTful desarrollado con Spring Boot para la gestión integral de ventas, usuarios, productos, categorías, carritos y seguridad JWT.

![Java](https://img.shields.io/badge/Java_21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)

---

### 👥 Equipo de Desarrollo Backend

| Integrante | Rol Principal | Ramas Asignadas |
|------------|---------------|-----------------|
| **Isaac Livaque** | Gestión de Usuarios, Clientes, Autenticación JWT y Spring Security, Integración General, Despliegue y Dockerización | `feature-usuario`, `feature-seguridad`, `feature-conexionBD`, `hotfix-DockerFile` |
| **César U.** | Gestión de Carrito y Detalle Carrito | `feature-carrito` |
| **Sebastián A.** | Gestión de Categorías y Productos e Integración de ambas ramas | `feature-categoria-producto`, `feature-categoria`, `feature-producto` |
| **Jaime A.** | Gestión de Ventas y Detalle Venta | `feature-ventas` |
| **Casey R.** | Mejora de interfaz gráfica, experiencia de usuario (UI/UX) e integración Frontend | `Soporte Frontend` |

</div>

---

## 📖 Sobre el Proyecto

El backend de Don Pepe es una API REST desarrollada con Spring Boot que proporciona toda la lógica de negocio para la plataforma de ventas. Permite administrar usuarios, clientes, productos, categorías, carritos y ventas mediante endpoints seguros protegidos con JWT y control de acceso basado en roles.

La aplicación sigue una arquitectura multicapa utilizando Spring Security, Spring Data JPA y PostgreSQL, permitiendo una integración eficiente con el frontend Angular.

---

## 🏗️ Arquitectura del Sistema

### 🔐 Seguridad

- Autenticación mediante JWT.
- Spring Security.
- Protección de endpoints por roles.
- Gestión de sesiones basada en tokens.
- Encriptación de contraseñas mediante BCrypt.

### 👤 Gestión de Usuarios

- Registro de usuarios.
- Inicio de sesión.
- Administración de perfiles.
- Asociación Usuario ↔ Cliente.

### 📦 Gestión de Productos

- CRUD completo de productos.
- Administración de categorías.
- Control de stock.
- Filtrado y búsqueda.

### 🛒 Gestión de Carrito

- Carrito activo por usuario.
- Agregado y eliminación de productos.
- Actualización de cantidades.
- Limpieza completa del carrito.

### 💰 Gestión de Ventas

- Checkout completo.
- Generación automática de ventas.
- Registro de detalles de venta.
- Actualización de estados.
- Historial de compras.

---

## 🎯 Endpoints Principales

### 🔐 Autenticación y Seguridad

**Responsable:** Isaac

```http
POST /api/auth/login
POST /api/auth/register
GET  /api/auth/me

GET  /api/usuarios

PUT  /api/clientes/perfil
```

#### Funcionalidades

- Login mediante JWT.
- Registro de usuarios.
- Consulta de usuario autenticado.
- Administración de usuarios.
- Actualización de perfil.

---

### 📂 Categorías

**Responsable:** Sebastián

```http
GET    /api/categorias
POST   /api/categorias
PUT    /api/categorias/{id}
DELETE /api/categorias/{id}
```

#### Funcionalidades

- Crear categorías.
- Actualizar categorías.
- Listar categorías.
- Eliminar categorías.

---

### 📦 Productos

**Responsable:** Sebastián

```http
GET    /api/productos
GET    /api/productos/{id}

POST   /api/productos

PUT    /api/productos/{id}

DELETE /api/productos/{id}
```

#### Funcionalidades

- CRUD completo de productos.
- Gestión de inventario.
- Actualización de stock.
- Filtros por nombre y categoría.

---

### 🛒 Carrito y Detalle Carrito

**Responsable:** César

```http
GET /api/carrito

POST /api/carrito/items

PUT /api/carrito/items/{idProducto}

DELETE /api/carrito/items/{idProducto}

DELETE /api/carrito/limpiar
```

#### Funcionalidades

- Obtener carrito activo.
- Agregar productos.
- Modificar cantidades.
- Eliminar productos.
- Vaciar carrito.

---

### 💰 Ventas y Detalle Venta

**Responsable:** Jaime

```http
POST /api/ventas/checkout

GET /api/ventas/mis-compras

GET /api/ventas

GET /api/ventas/{id}

PUT /api/ventas/{id}/estado
```

#### Funcionalidades

- Procesar compras.
- Registrar ventas.
- Registrar detalle de venta.
- Consultar historial.
- Gestionar estados.

---

## 🗄️ Modelo Relacional

```text
USUARIO
│
└── CLIENTE
     │
     ├── CARRITO
     │      │
     │      └── DETALLE_CARRITO
     │
     └── VENTA
             │
             └── DETALLE_VENTA

PRODUCTO
│
└── CATEGORIA
```

---

## ⚙️ Tecnologías Utilizadas

### Backend

- Java 21
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Spring Security

### Base de Datos

- PostgreSQL

### Seguridad

- JWT Authentication
- BCrypt Password Encoder

### Herramientas

- Maven
- Git
- GitHub
- Docker

---

## 🌿 Flujo de Trabajo (GitFlow)

```text
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
├── feature-conexionBD
│
└── hotfix-DockerFile
```

### Reglas de Trabajo

- `main` → Producción estable.
- `dev` → Integración y pruebas.
- `feature/*` → Desarrollo individual por módulo.
- `hotfix/*` → Correcciones urgentes.

---

## 📁 Arquitectura de Carpetas

```text
src/
└── main/
    ├── java/
    │   └── com.donpepe/
    │
    ├── config/
    │   ├── SecurityConfig.java
    │   ├── JwtFilter.java
    │   ├── JwtService.java
    │   └── CorsConfig.java
    │
    ├── controller/
    │   ├── AuthController.java
    │   ├── UsuarioController.java
    │   ├── ClienteController.java
    │   ├── CategoriaController.java
    │   ├── ProductoController.java
    │   ├── CarritoController.java
    │   └── VentaController.java
    │
    ├── service/
    │   ├── AuthService.java
    │   ├── UsuarioService.java
    │   ├── ClienteService.java
    │   ├── CategoriaService.java
    │   ├── ProductoService.java
    │   ├── CarritoService.java
    │   └── VentaService.java
    │
    ├── repository/
    │   ├── UsuarioRepository.java
    │   ├── ClienteRepository.java
    │   ├── CategoriaRepository.java
    │   ├── ProductoRepository.java
    │   ├── CarritoRepository.java
    │   └── VentaRepository.java
    │
    ├── model/
    │   ├── Usuario.java
    │   ├── Cliente.java
    │   ├── Categoria.java
    │   ├── Producto.java
    │   ├── Carrito.java
    │   ├── DetalleCarrito.java
    │   ├── Venta.java
    │   └── DetalleVenta.java
    │
    ├── dto/
    ├── exception/
    ├── mapper/
    │
    └── SistemaVentasApplication.java
    │
    └── resources/
        ├── application.properties
        ├── application-dev.properties
        └── data.sql
```
