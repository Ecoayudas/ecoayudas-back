# ecoayudas

Descripción:

Este repositorio contiene el código fuente de la aplicación web Ecoayudas, desarrollada como parte del proyecto final de curso del grado superior en Desarrollo de Aplicaciones Web.

Ecoayudas es un servicio ofrecido por la gestoría Numerus Asesores de Empresas S.L.,
que se encarga de solicitar y gestionar las subvenciones ofrecidas por las administraciones públicas para la ejecución de instalaciones ligadas al autoconsumo con fuentes de energías renovables.

La aplicación ha sido desarrollada utilizando el framework Spring Boot, base de datos PostgreSQL y securización con JWT.

Funcionalidades:

La aplicación, hasta el momento, ofrece las siguientes funcionalidades:

Inicio de sesión de usuarios (clientes e instaladores).
Creación, consulta, modificación y eliminación de clientes, instaladores, solicitudes.
Subida de archivos.

Requisitos:

Para ejecutar esta aplicación, se requiere:

JDK 1.8 o superior
Maven 3.6.0 o superior
PostgreSQL 9.5 o superior

Instalación y configuración:

Clonar este repositorio en tu máquina local.
Ejecutar el script database.sql en tu instancia de PostgreSQL para crear la base de datos y las tablas necesarias.
Abrir el archivo application.properties y modificar la configuración de la base de datos según sea necesario.
Ejecutar el comando mvn clean install para compilar la aplicación y generar el archivo .jar.
Ejecutar el comando java -jar ecoayudas.jar para iniciar la aplicación.

Contribución:

Este repositorio se ha creado como parte del proyecto final de curso del grado superior en Desarrollo de Aplicaciones Web., por lo que no se aceptarán contribuciones externas.

Licencia:

Este proyecto está licenciado bajo la Licencia MIT. Consulte el archivo LICENSE para obtener más información.
