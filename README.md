#Descripcion del Proyecto 
<p>
Este proyecto se basa netamente en agregar informacion mediante una aplicacion a los clientes de un supermercado dando aviso sobre productos o promociones que se vayan a hacer.
</p>

#Tecnologias Usadas 
<p>
Para la creacion del proyecto se utilizaron los siguientes programas.
- Android Studio
- firebase
- node-red
</p>

#Instalacion
<p>
Primero es necesario descargar el archivo, importar el archivo en Android Studio y darle a iniciar el proyecto. Dependera si lo inicias desde la maquina virtual de android studio o desde su dispositivo movil personal. 

Maquina virtual: para iniciar en la maquina virtual de android studio se debera descargar el sistema operativo en device manager.

Dispositivo personal: para este metodo debera activar el modo desarrollador y activar la depuracion usb y en android studio seleccionar le dispositivo al que lo iniciara.
</p>
#Flujo de la app
Primero se accede al apartado de inicio de sesion de la app donde tendra que colocar un correo, contraseña y agregar el codigo de seguridad de la empresa que solo la tendra los empleados. Posterior a eso se podra agregar la informacion correspondiente que se vera reflejada en el inicio de nuestra app. en el apartado agregar se debera colocar un titulo y una descripcion de la informacion dada, esa informacion se almacenara en firebase tanto el inicio de sesion como la informacion agregada.
#Estructura del codigo

### Raiz del proyecto
    app/: Contiene los archivos principales del proyecto.

###Manifests

    AndroidManifest.xml: Archivo que define los componentes principales de la aplicación, como actividades, permisos, y el nombre del paquete.

###Directorio java

    Paquete principal (com.example.appfinal)
        agregar: Clase relacionada con la funcionalidad de agregar elementos a la aplicación.
		CardAdapter: Clase para manejar la vista adaptada de los elementos en un                             RecyclerView.
        CardItem: Modelo que define los datos de cada tarjeta en el RecyclerView.
        DatatItem: (Posiblemente se refiere a DataItem) Modelo o utilidad relacionado con la gestión de datos.
        inicio: Clase para la pantalla principal de la aplicación.
        iniciosesion: Clase que maneja la pantalla de inicio de sesión y registro.
        MainActivity: Actividad principal del proyecto, posiblemente la primera que se ejecuta.
        MqttHelper: Clase encargada de gestionar la comunicación mediante el protocolo MQTT (si es parte de la lógica de la app).

    Test
        androidTest y test: Directorios utilizados para pruebas instrumentadas y pruebas   unitarias, respectivamente.

###Directorio res

    drawable: Carpeta para recursos gráficos, como imágenes o vectores.

    layout: Contiene los diseños de las interfaces de usuario (archivos XML):
        activity_agregar.xml: Layout para la funcionalidad de agregar elementos.
        activity_inicio.xml: Layout para la pantalla principal de la aplicación.
        activity_iniciosesion.xml: Layout para la pantalla de inicio de sesión y registro.
        activity_main.xml: Layout principal, posiblemente relacionado con MainActivity.
        item_card.xml: Diseño para cada tarjeta que se muestra en el RecyclerView.

    mipmap/: Carpeta para íconos de la aplicación.

    navigation/: Configuración para la navegación dentro de la app.

    raw/: Recursos sin procesar, como archivos de audio o texto.

    values/: Contiene recursos como colores, estilos, cadenas de texto, y temas.

    xml/: Archivos de configuración adicionales, como preferencias.

###Gradle Scripts

    Contiene los scripts de Gradle para la construcción del proyecto:
        build.gradle (nivel de aplicación y proyecto): Definición de dependencias, configuraciones, y versiones.

#Imagenes app 
###inicio de la app

![](https://github.com/JereAmaya27/proyecto-final/blob/main/AppFinal/imagenes%20app/inicio.png?raw=true)

### Agregar de la app

![](https://github.com/JereAmaya27/proyecto-final/blob/main/AppFinal/imagenes%20app/agregar.png?raw=true)

###Inicio de sesion de la app

![](https://github.com/JereAmaya27/proyecto-final/blob/main/AppFinal/imagenes%20app/iniciosesion.png?raw=true)

###Datos guardados en realtime database

![](https://github.com/JereAmaya27/proyecto-final/blob/main/AppFinal/imagenes%20app/datos.png?raw=true)

###Datos inicio de sesion en Authentication

![](https://github.com/JereAmaya27/proyecto-final/blob/main/AppFinal/imagenes%20app/regitros.png?raw=true)


