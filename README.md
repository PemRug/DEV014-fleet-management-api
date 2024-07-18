Fleet Management API
La Fleet Management API es una aplicación RESTful desarrollada para gestionar y administrar flotas de vehículos. Proporciona endpoints para realizar operaciones como autenticación de usuarios, gestión de taxis, y consulta de datos de trayectorias.

Características
Autenticación: Implementa autenticación basada en tokens JWT para proteger los endpoints sensibles.
Gestión de Usuarios: Permite registrar nuevos usuarios y autenticar usuarios existentes.
Gestión de Taxis: Provee endpoints para la creación, actualización, y consulta de taxis registrados.
Consulta de Trayectorias: Permite buscar y filtrar datos de trayectorias de taxis por fecha y otros parámetros.
Tecnologías Utilizadas
Spring Boot: Framework de desarrollo para aplicaciones Java.
Spring Data JPA: Para la persistencia de datos con la base de datos PostgreSQL.
Spring Security: Para la autenticación y autorización de usuarios.
JWT (JSON Web Tokens): Utilizado para la generación y validación de tokens de acceso.
Documentación
La API está documentada utilizando Swagger. Puedes acceder a la documentación y probar los endpoints desde aquí.

Instalación y Configuración
Requisitos
Java 11 o superior
Maven 3.6.x o superior
PostgreSQL 11.x o superior
Pasos para Configurar y Ejecutar
Clonar el Repositorio:

bash
Copiar código
git clone https://github.com/tu_usuario/fleetmanagementapi.git
cd fleetmanagementapi
Configurar la Base de Datos:

Crear una base de datos PostgreSQL llamada fleet_management.
Asegurarse de que las credenciales de acceso a la base de datos estén configuradas en application.properties.
Compilar y Ejecutar:

bash
Copiar código
mvn clean install
mvn spring-boot:run
La aplicación estará disponible en http://localhost:8080.

Acceder a la Documentación:

Abre un navegador y accede a http://localhost:8080/swagger-ui.html para ver la documentación interactiva de la API.

Haz un fork del repositorio y clónalo localmente.
Crea una nueva rama (git checkout -b feature/nueva-funcionalidad).
Realiza tus cambios y haz commit (git commit -am 'Agrega nueva funcionalidad').
Sube tus cambios a tu repositorio fork (git push origin feature/nueva-funcionalidad).
Abre un Pull Request.
Contacto
Para cualquier consulta o reporte de problemas, contáctanos en fleetmanagementapi@gmail.com

