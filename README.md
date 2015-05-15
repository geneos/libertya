Libertya
========

Código fuente de la versión 14.02 de Libertia

Guía de instalación
===================

Instalación de Libertya - Ubuntu

  Instalar el JRE 1.6 (ver porque ubuntu en general lo trae instalado).
  Verificación: en consola poner java -version

    postgres@pepo:/home/pepo$ java -version
    java version "1.6.0_30"
    OpenJDK Runtime Environment (IcedTea6 1.13.1) (6b30-1.13.1-1ubuntu2~0.12.04.1)
    OpenJDK 64-Bit Server VM (build 23.25-b01, mixed mode)


Referencia para instalar openjdk 6

https://www.digitalocean.com/community/tutorials/instalar-java-en-ubuntu-con-apt-get-es


Instalar postgres + pgadmin
===========================

  Agregar el repositorio de postgresql:

	deb http://apt.postgresql.org/pub/repos/apt/ precise-pgdg main

  Importar la clave de firma del repositorio	

	sudo wget --quiet -O - https://www.postgresql.org/media/keys/ACCC4CF8.asc | sudo apt-key add - 

  Actualizar e instalar postgresql
  
	sudo apt-get update 
	sudo apt-get install postgresql-9.3
	sudo apt-get install pgadmin3

  Primer superusuario con logging admin

	createuser -s -P -e admin

  Correr el servicio en Ubuntu/Debian:

	/etc/init.d/initdb start -- Inicializando el cluster

	/etc/init.d/postgresql start -- Arrancar el gestor

  Cambiar password de usuario postgres a postgres por ejemplo

  Crear en pgadmin el role libertya con clave libertya y permisos full

  Crear una base de datos libertya

  Modificar en pg_hba.conf (sudo gedit /etc/postgresql/9.1/main/pg_hba.conf) agregando según corresponda el código a continuación

    host all all 192.168.0.0/24 trust
      o
    host    all         all         127.0.0.1/32          trust

 Verificar en el archivo /etc/hosts que el localhost sea 127.0.0.1

Instalación de Libertya 14.02
=============================

Descargar Libertya 14.02 (en el ejemplo se instala el servidor ya compilado)

Descomprimir en un directorio de acceso simple /home/apps/pepo/apps/ServidorOXP/

Restaurar el backup de libertya

Como usuario postgres (su postgres)

    psql -U postgres -d libertya -f /home/pepo/Documentos/laboral/geneos/proyectos/Libertya/dump_libertya_1402ar.sql

Nota es necesario que la carpeta del script tenga permisos 777 

    chmod -R 777 /home/pepo/Documentos/laboral/geneos/proyectos/Libertya/

En la carpeta del servidor de Libertya /home/apps/pepo/apps/ServidorOXP/ dar permisos de ejecución a los script sh

    chmod +x *.sh

En la carpeta del servidor de Libertya /home/apps/pepo/apps/ServidorOXP/ ejecutar

    ./Configurar.sh 

En este punto hay que configurar los datos que pide de conexión a la base y del servidor. Puerto del servidor cambiar 80 a 8080 y 443 a 8443 por ejemplo (si el 8080 está ocupado puede ser 8084). A continuación validar los datos.

Acceder a la carpeta /home/apps/pepo/apps/ServidorOXP/utils/ y ejecutar

    ./IniciarServidor.sh

Acceder a la aplicación web

    localhost:8080/webui

