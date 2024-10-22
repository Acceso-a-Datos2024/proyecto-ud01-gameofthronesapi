# Introducción

Para este supuesto hemos elegido utilizar la API Game of Thrones Character Api (https://thronesapi.com/api) la cual contiene una lista de personajes de la serie Juego de Tronos identificados por distintos campos por los que podremos
realizar las consultas. Estos son : id, nombre, apellido, título, familia, imagen, imagenURL.
 
Para comprobar el funcionamiento y que podiamos utilizarla , creamos una coleccion y en ella realizamos 3 consultas distintas.

![Get 1](res/imagenes/APIGet1.png)

![Get 2](res/imagenes/APIGet2.png)

![Get 3](res/imagenes/APIGet3.png)

# Manual  técnico para desarrolladores

Para la creación de nuestro Proyecto en JavaFX comenzamos eligiendo el JDK y la versión de JavaFX en la que vamos a trabajar. En nuestro caso la 17.0.6.

La aplicación consta de 3 ventanas principales (login.fxml, consultas_tabla.fxml y exportar_documento.fxml) y una emergente, por lo que en el "Main" de nuestra aplicación cargaremos por defecto la ventana login.fxml y le daremos
una resolución de 640x400. Para el fin de nuestra aplicación no necesitariamos ocupar toda la pantalla, pero en caso de que fuese neceario pondriamos el parametro .setResizable(true) y hariamos cambios en los .fxml para que se 
adaptase la interfaz correctamente. 

![Main](res/imagenes/Main.png)

Tendremos que crear las clases.java con los que vamos a trabajar. Volcaremos en un archivo JSON los personajes de la API para así generar las clases con roboPOJOGenerator. Las clases tendrán los mismos campos que hemos indicado más arriba.
Generando así una clase que sería una lista de personajes y la clase personaje, que contiene las características de cada personaje. Deberemos generar constructores en cada una de ellas y un "toString" en la clase de Personajes para poder
modificar la salida de datos por pantalla.

![ClasePersonajes](res/imagenes/Clasepersonajes.png)

Cada boton o campo de la interfaz tiene su correspondiente ID declarado en el .fxml y estos pueden interactuar con métodos para realizar distintas funciones a través del "Controller". En este archivo es donde haremos que los campos de
texto y los botones realicen funciones e interactúen entre si. 

Comenzamos declarando los imports que vayamos a necesitar, aunque muchos de ellos podremos importarlos a través de sugerencias del entorno de desarrollo. Acto seguido declaramos los componentes de la interfaz gráfica (FXML). 
Los utilizaremos más adelante en los métodos.

![Componentes](res/imagenes/Componentes.png)

Comenzaremos declarando el método "initialize()" que se ejecutará al cargar la vista del .fxml y nos permitirá configurar la tabla para mostrar los resultados de las búsquedas. Además dentro de este llamaremos a otro método llamado 
"cargarPersonajesDesdeJSON" el cual básicamente nos cargará la lista de personajes desde el archivo JSON que generamos con la API. Así podremos realizar consultas en nuestra propia aplicación sin depender de una conexión a internet.
Y si en un futuro se realizase actualziaciones en la API simplemente tendríamos que reflejarlas en nuestro JSON. 

![Componentes](res/imagenes/Componentes.png)

Una vez tengamos esto declararemos el método que nos permita realizar búsquedas, comentado en la siguiente imagen. A mayores hemos incluido otro pequeño método para pasar la id que introduce el usuario (String) a un int , que es como 
figura en la clase de los Personajes, así podremos realizar la búsqueda por ID sin probelmas.

![Componentes](res/imagenes/Busqueda_parseid.png)

Con esto ya tendríamos nuestra ventana de búsquedas lista para realizar consultas. Ahora declararemos el método "abrirExportarDocumento" que nos llevará a la ventana donde indicaremos que tipo de documento queremos exportar y que nombre
queremos darle.

![Botón Exportar](res/imagenes/Abrirexportardoc.png)

En esta ventana tendremos 4 botones creados previamente en el FXML. Cada uno de ellos llamará a uno de los siguientes métodos en función del tipo de archivo que queremos exportar. Dentro de cada uno de ellos hemos hecho una llamada 
a un vboxOK declarado por nosotros, que básicamente es una ventana emergente que nos idica que el documento se ha exportado con éxito. Y "doc_vacio" es el mensaje que nos informa de que debemos dar un nombre al documento previamente
para poder exportarlo.

**XML**

![ExportXML](res/imagenes/ExportXML.png)
**JSON**

![ExportJSON](res/imagenes/Imagen_exportarJSON.png)
**BIN**

![ExportBIN](res/imagenes/Imagen_exportarBIN.png)
**TXT**

![ExportTXT](res/imagenes/Imagen_exportarTXT.png)

A mayores hemos incluido algunos métodos como "volver" o "salir" que básicamente hacen que los botones volver y salir de sus respectivas ventanas nos devuelvan a la ventana anterior o nos deslogueen.

**Volver**

![Volver](res/imagenes/Volver.png)

**Salir**

![Salir](res/imagenes/Salir.png)

# Manual de usuario con juego de pruebas

Para comenzar a utilizar la aplicación se debe realizar un log in con uno de los usuarios válidos con su correspondiente contraseña.

![ Imagen loginbien ](res/imagenes/Loginbien.png)

En caso de que alguno de los dos campos sea incorrecto aparecerá un mensaje indicando que el nombre o la contraseña son incorrectos.

![ Imagen loginmal ](res/imagenes/Errorlogin.png)

Una vez terminado el log in se accede a la pantalla de búsquedas donde se podrán realizar búsquedas de personaje por tres campos distintos: id, nombre y apellido.
En caso de que haya varios personajes que coincidan con la búsqueda realizada estos apareceran en una tabla.

Búsqueda por id:

![ Imagen id ](res/imagenes/ConsultaID.png)

Búsqueda por nombre:

![ Imagen nom ](res/imagenes/Consultanombre.png)

Búsqueda por apellido:

![ Imagen apel ](res/imagenes/Consultaapellido.png)

Además también se podrá buscar por diferentes campos al mismo tiempo.

![ Imagen varios ](res/imagenes/Bsuqedavarioscampos.png)

En caso de que ningún dato coincida con la búsqueda realizada la tabla aparecerá vacía.

![ Imagen errbus ](res/imagenes/Bsuqedaerronea.png)

Una vez realizada la búsqueda se podrán seleccionar los personajes de la tabla para que muestre una foto del personaje seleccionado.

![ Imagen ned ](res/imagenes/NedFoto.png)

![ Imagen rob ](res/imagenes/RobFoto.png)

Cuando se haya terminado la búsqueda se puede pulsar el botón *Exportar Tabla* para ir a la siguiente pantalla, donde se podrá elegir el nombre y tipo de documento al que se desea exportar entre los disponibles (.txt, .bin, .json, .xml).

En caso de que no se haya proporcionado un nombre de documento aparecerá un mensaje en pantalla indicándolo y no se realizará la exportación.

![ Imagen errexport ](res/imagenes/Errorexportar.png)

Cuando la exportación se realice correctamente aparecerá esta ventana emergente confirmando la exportación. Los documentos se guardarán en la carpeta *docs*.

![ Imagen exportcorrecto ](res/imagenes/ExportarCorrecto.png)

Por último si se desea seguir realizando búsquedas se pulsará el botón *Volver* para ir a la pantalla de búsquedas.

# Reparto de tareas

Se han repartido las tareas en función del tiempo que se le ha dedicado a cada una de forma que este fuera equitativo:

Jorge ha implementado las funcionalidades de búsqueda por los distintos campos y mostrar los resultados mediante una tabla, la exportación de datos a XML, la verificación de usuarios y el login, botones de cambio de pantalla y mensajes emergentes y ha realizado el modelo de las pantallas de login y búsquedas.

Miguel ha implementado las funcionalidades de exportación a binario, texto y json, la interacción con la tabla para recibir la imagen de cada personaje, botones de cambio de pantalla, ventanas y mensajes emergentes. Ha realizado el modelo de la pantalla de exportación de documentos y ha añadido estilos a las distintas pantallas.

# Extras realizados

# Propuestas de mejora

Entre las mejoras que se podrían implementar estan:

- Añadir un mensaje que indique que no se han encontrado coincidencias al realizar la búsqueda.
- Actualmente los datos se extraen de un json obtenido de la API y que se ha guardado en la carpeta *resources*, la mejora sería implementar que la búsqueda se realizara directamente contra la API.
- Encriptar los datos del fichero properties para el log in.

# Conclusiones
    
### Dedicación temporal

A este proyecto cada uno de los integrantes le ha dedicado un total de 20 horas divididos entre los distintos días de la semana de la siguiente manera.

![ Imagen horas trabajo ](res/imagenes/Horas_trabajo.png)


### Cualificación estimada
