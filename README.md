# ChatLearn

Para importar y que se pueda ejecutar el proyecto con IntelliJ seguir las siguientes instrucciones:
1. Importar desde git. (New project from version control).
2. Ir a la pestaña run -> edit configurations.
3. Hacer click en el +, seleccionar Tomcat server local.
4. En application server: hacer click en configure y seleccionar la carpeta de Tomcat.
5. Ir a Deployment, hacer click en +, artifact, ChatLearn:war.
6. (opcional) Volver a Server, en URL escribir: http://localhost:8080/ChatLearn_war/html/login.html.

# Pasos Para Crear Web Archive Y Hacer El Despliegue Sin Intellij
Requisitos: Tener instalado Maven, Make, JDK.
1. Clonar el repositorio.  
2. En el directorio apache-tomcat-9.0.39 ya se encuentra una versión de la aplicación, se puede iniciar el servidor ejecutando el script startup encontrado en el directorio bin 
de Tomcat, se puede acceder a la aplicación desde el URL http://localhost:8080/ChatLearn_war/html/login.html.  
3. Si se quiere compilar los source files y crar un war: se proporciona un makefile, ejecutar con make build, obtendrás un directorio ChatLearn_war. Copiar al directorio webapps 
de Tomcat e iniciar Tomcat con el startup script del directorio bin de Tomcat.  

Nota: en Unix para darle permisos de ejecución a los .sh: chmod +x *.sh.  
Nota: Si no se ha hecho la configuracion de Tomcat y no se ha configurado JAVA_HOME 	se tendrá que configurar. Configuracion de Apache Tomcat: https://tomcat.apache.org/tomcat-8.5-doc/setup.html.

# Base De Datos

Requisito: MySQL

Para el correcto funcionamiento de la aplicación el servidor Tomcat usado debe contener el jar mysql-connector-java-8.0.22 copiado en su directorio lib. Se proporciona este Jar
en el directorio Connector j 8.0.

Para la creación de la base de datos "chatlearn", se proporciona el fichero ./db/creacion.sql", para crear la base de datos ejecutar el siguiente comando en el CLI de MySQL:

		1. source C:\ruta\al\fichero\creacion.sql
		
Para insertar usuarios de prueba leemos el otro fichero de este directorio "insercion.sql" con el siguiente comando:

		2. source C:\ruta\al\fichero\insercion.sql







