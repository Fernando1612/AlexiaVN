# AlexiaVN

[![en](https://img.shields.io/badge/lang-en-red.svg)](https://github.com/Fernando1612/AlexiaVN/blob/master/README.en.md)

Alexia visual novel es un motor de novelas visuales con el cual puedes crear y reproducir novelas visuales de una manera muy sencilla desde tu celular android. Puedes ingresar textos, imágenes, sonidos y vídeos para crear un gran historia. Es muy fácil de usar con instrucciones intuitivas y no es necesario saber programar.

## Comienzo rápido

Crear un proyecto en blanco desde la aplicación dirigiéndote al menú de la parte superior y presionando el botón **Crear proyecto en blanco**, Finalmente, ponle un nombre a tu proyecto y presiona aceptar.


https://user-images.githubusercontent.com/42527034/117889857-d8062380-b279-11eb-8cae-cca802bd1ade.mp4


Después, con ayuda de una navegador de archivos (recomendamos ASTRO) o conectando tu celular a una computadora dirígete a la carpeta **alexavn**.

Dentro de **alexavn** encontrarás una carpeta con el nombre de tu proyecto y dentro de esa carpeta verás lo básico para crear una novel visual.


https://user-images.githubusercontent.com/42527034/117898276-3dfaa700-b28a-11eb-9f7a-be64e13296f1.mp4


Dentro de la carpeta de tu proyecto tendrás que poner la imagen que quieras como portada de tu novela visual. 

Ahora, dirígete a la carpeta **Scenes** y coloca la imagen que usaras como fondo en tu novela visual (en está carpeta pondrás todas las imágenes que vayas a ocupar).

Después, dirígete a la carpeta **Scripts** y crea un archivo con  extensión **.txt** (en está carpeta guardarás los archivos que van a ser leídos por la aplicación).


https://user-images.githubusercontent.com/42527034/117898321-566ac180-b28a-11eb-8ec2-3c1ba4add811.mp4


Con ayuda del editor de texto de tu preferencia abre el archivo con extensión **.txt** y comienza a escribir tu historia.
Primero, coloca todos los recursos con los que quieres que inicie tu novela, en este caso agregamos el fondo y el texto que deseamos mostrar en la pantalla utilizando el símbolo **+** como separador.

![carbono](https://user-images.githubusercontent.com/42527034/117898505-b5c8d180-b28a-11eb-8c08-9a54efee1ddd.png)

Regresa a donde están todas las carpetas de tu proyecto y con ayuda de tu editor de texto abre el archivo **config.avn**, dentro de ese archivo pondrás el título de tu novela, el archivo de tu carpeta **Scripts** con el que deseas iniciarla y la imagen que desees como portada en ese orden y separados por una coma ",".

![config](https://user-images.githubusercontent.com/42527034/117898511-b95c5880-b28a-11eb-8940-9cfd4a69ee14.png)

Abre **Alexia VN player**, busca el archivo **config.avn** y haz click sobre él para cargar tu novela.

Finalmente, haz click sobre la portada de tu novela para iniciarla.

https://user-images.githubusercontent.com/42527034/117898415-8c0faa80-b28a-11eb-969b-d079a91c9084.mp4





## Carpetas y archivos

Un proyecto básico de Alexia VN contiene 10 carpetas:

1. Carpeta con el nombre del proyecto 
2. BGM
3. Chars
4. FX
5. Menu
6. Scenes
7. Scripts
8. Textbox
9. Video
10. Voices

y 2 archivos:

1. config.avn
2. Una imagen para la portada de la novela

La estructura de las carpetas tiene la siguiente forma.

![carpetasAlexia](https://user-images.githubusercontent.com/42527034/118007843-249d3d80-b312-11eb-8c45-9c146020c2ad.png)

### BGM

En la carpeta **BGM** se almacenan la música de fondo que van en la novela visual, la extensión de los archivos puede ser .mp3, .Wav, .Flac, etc.

### Chars

En la carpeta **Chars** se almanecen las imagenes de los personajes de la novela visual, se recomienda utilizar imagenes con extensión .png y sin fondo.

### FX

En la carpeta **FX** se almencen los efectos de sonido que se utilicen en la novela visual, la extensión de los archivos puede ser .mp3, .Wav, .Flac, etc.

### Menu

Estamos trabajando en ello...

### Scenes

En la carpeta **Scenes** se almacenan la mayoria de las imágenes que se van a utilizar en la novela visual, como los fondos, escenas, etc. La extensión de los archivos puede ser .jpp, .png, .bmp, etc.

### Scripts

En la carpeta **Scripts** se almacenan los archivos que son leidos por la aplicación los cuales contendrán todas las instrucciones, estos archivos deben tener una extensión **.txt** .

### Textbox

En la carpeta **Textbox** se almacenan las imágenes de las cajas de texto que se utilizarán en la  novela visual, las imágenes se recomiendan guardar con extensión **.png** y sin fondo para una mejor experiencia.

### Video

En la carpeta **Video** se almacenan los vídeos que se utilizarán en la novela visual. La extensión de los archivos de video puede ser .mp4, .avi, .mkv, .flv, etc.

### Voices

Estamos trabajando en ello...

### config.avn

El archivo **config.avn** debe contener solo **3** elementos:

1. Título de la novela visual
2. Archivo de la carpeta **Scripts** con el cual se desea iniciar la novela
3. Imagen de la portada para la novela visual

Con ayuda de un editor de texto abrir el archivo y colocar los elementos en ese, orden separados por comas.

![config](https://user-images.githubusercontent.com/42527034/117898511-b95c5880-b28a-11eb-8940-9cfd4a69ee14.png)

### Portada

El archivo de **portada** es una imagen la cual será la portada de la novela visual y se vizualizara al cargar el archivo **config.avn** a la aplicación. La extensión del archivo puede ser .jpp, .png, .bmp, etc. 



### Nota

La manipulación y vizualización de las diferentes carpetas y archivos se puede hacer en la computadora sin ningún problema, solo se debe conectar el celular a está y navegar por sus archivos hasta encontrar el proyecto.



## Instrucciones

Acontinuación se describirán las intrucciones con las que cuenta Alexia VN, estas instrucciones van dentro de los archivos **txt** que se encuentran dentro de la carpeta **Scripts**, observe que se utiliza el símbolo **+** como separador.

### [BGM]

La instrucción reproduce sonidos, debe llevar dos parámetros, la palabra **looping** y el nombre del archivo que se desea reproducir ubicado en la carpeta **BGM**.

![bgm](https://user-images.githubusercontent.com/42527034/118008011-4dbdce00-b312-11eb-83e4-76c3de04c12b.png)

### [CHAR]

La instrucción coloca la imagen de un personaje, necesita dos parámetros, la posición las cuales pueden ser **left** para ponerlo a la izquierda de la pantalla, **center** para ponerlo al centro de la pantalla o **right** para ponerlo a la derecha de la pantalla y el nombre de la imagen del personaje que se encuentra en la carpeta **Chars**.

![char](https://user-images.githubusercontent.com/42527034/118008085-5d3d1700-b312-11eb-8f57-c5df62bbd427.png)

![Screenshot_2021-05-11-23-07-53-108_maceda alejandro alexiavnplayer](https://user-images.githubusercontent.com/42527034/118008554-ccb30680-b312-11eb-9f08-6c14435a53d6.jpg)

### [CHARBIG] 

La instrucción es similar a **[CHAR]** la diferencia es que **[CHARBIG]** hace la imagen del personaje mas grande, tiene los mismos parametros que char.

![charbig](https://user-images.githubusercontent.com/42527034/118008594-d6d50500-b312-11eb-9ee0-56e62ce67b75.png)

![Screenshot_2021-05-11-23-07-18-180_maceda alejandro alexiavnplayer](https://user-images.githubusercontent.com/42527034/118008634-e2283080-b312-11eb-9557-07542647ed0e.jpg)

### [CHARBIGCLEAR]

La instrucción es similar a **[CHARBIGCLEAR]**, quita la imagen grande del personaje y necesita el mismo parametro que **[CHARCLEAR]**.

![charbigclear](https://user-images.githubusercontent.com/42527034/118008721-f66c2d80-b312-11eb-9726-8158c2166b38.png)

### [CHARCLEAR]

La instrucción quita la imagen del personaje, necesita solo un parámeto el cual es la posición donde esta el personaje, para quitarlo.

![charclear](https://user-images.githubusercontent.com/42527034/118008739-fd933b80-b312-11eb-96d6-e263b72b3220.png)

### [CHOICES]

La instrucción sirve para colocar botones en los cuales puedes poner decisiones dentro de la novela y asi tomar los diferentes caminos que existan, como mínimo se necesitan 7 parámetros:

1. Texto que aparece en la parte inferior
2. Texto que aparece en el botón
3. Nombre del nuevo archivo que se va a leer y se debe encontrar en la carpeta **Scripts**
4. Tamaño del botón en pixeles
5. Tamaño del texto del botón
6. Color de fondo del botón en hexadecimal
7. Color del texto en hexadecimal

![choices1](https://user-images.githubusercontent.com/42527034/118009513-b9546b00-b313-11eb-9055-8191e34dbef0.png)

![Screenshot_2021-05-12-11-22-16-551_maceda alejandro alexiavnplayer](https://user-images.githubusercontent.com/42527034/118010430-a726fc80-b314-11eb-95e3-9278c56226a8.jpg)

Para poner más de una decisión se debe agregar el simbolo **+** al final y poner los parámetros del 2 al 7 de nuevo, como se muestra en el ejemplo. 

![choices2](https://user-images.githubusercontent.com/42527034/118009566-c2453c80-b313-11eb-893a-071a5a640948.png)

![Screenshot_2021-05-12-11-23-58-810_maceda alejandro alexiavnplayer](https://user-images.githubusercontent.com/42527034/118010457-aee6a100-b314-11eb-9627-6bed16c63014.jpg)

Se puede seguir asi hasta n decisiones agregando sus respectivos parametros (Todo debe ser en una sola línea). La instrucción **[CHOISES]** y **[CHOISESIMAGE]** deben ir al final de cada archivo de la carpeta **Scripts** para continuar la historia, exceptuando el ultimo archivo de la novela. 

### [CHOICESIMAGE]

La instrucción es muy similar a **[CHOICES]**, la diferencias es que en lugar de usar botones ahora se pueden utilizar imágenes, como minímo se necesitan 5 parámetros:

1. Texto que aparece en la parte inferior
2. Nombre de la imagen a utilizar la cual debe estar en la carpeta **Scenes**
3. Nombre del nuevo archivo que se va a leer y se debe encontrar en la carpeta **Scripts**
4. Ancho de la imagen en pixeles
5. Alto de la imagen en pixeles

![choicesimages1](https://user-images.githubusercontent.com/42527034/118013994-5addbb80-b318-11eb-9c8f-724bf7462d5b.png)

![Screenshot_2021-05-12-11-47-59-574_maceda alejandro alexiavnplayer](https://user-images.githubusercontent.com/42527034/118014089-721ca900-b318-11eb-919a-d9c1be16bd30.jpg)

Se recomienda utilizar imágenes con extensión **.png** y sin fondo.

Para poner más de una decisión se debe agregar el simbolo **+** al final y poner los parámetros del 2 al 5 de nuevo, como se muestra en el ejemplo. 

![choicesimage2](https://user-images.githubusercontent.com/42527034/118014123-7a74e400-b318-11eb-929f-70a237bf38f9.png)

![Screenshot_2021-05-12-11-49-46-610_maceda alejandro alexiavnplayer](https://user-images.githubusercontent.com/42527034/118014148-806ac500-b318-11eb-9702-a8873bb63718.jpg)

Se puede seguir asi hasta n decisiones agregando sus respectivos parametros (Todo debe ser en una sola línea). La instrucción **[CHOISES]** y **[CHOISESIMAGE]** deben ir al final de cada archivo de la carpeta **Scripts** para continuar la historia, exceptuando el ultimo archivo de la novela. 

### [CLEARNAMECHAR]

La instrucción quita el texto con el nombre de un personaje de la pantalla, no necesita ningún parámetro.

![clearnamechar](https://user-images.githubusercontent.com/42527034/118014245-9a0c0c80-b318-11eb-9707-437dc49a47d9.png)

### [END]

La instrucción termina la novela visual y muesta el mensaje "FIN DE LA NOVELA", no necesita ningún parámetro.

![end](https://user-images.githubusercontent.com/42527034/118014257-9ed0c080-b318-11eb-8957-e3ea10670ff1.png)

### [FADEIN]

La instrucción hace el efecto de "aparecer", puede ser una imagen o un video, no lleva ningún parámetro.

![fedein](https://user-images.githubusercontent.com/42527034/118017074-c8d7b200-b31b-11eb-8c79-cc700be1ee9a.png)

### [FADEOUT]

La instrucción hace el efecto de "desaparecer", puede ser una imagen o un video, no lleva ningún parámetro.

![fadeout](https://user-images.githubusercontent.com/42527034/118014350-bc9e2580-b318-11eb-946e-6eb5d38399db.png)

### [IGNORE]

La instrucción permite hacer anotaciones dentro del archivo, que no aparecerán en la novela, necesita un parámetro, la anotación que deseas poner en el archivo.

![ignore](https://user-images.githubusercontent.com/42527034/118014371-c2940680-b318-11eb-9946-903abceff1ff.png)

### [IMG]

La instrucción coloca una imagen como fondo, necesita un parámetro el cual es el nombre de la imagen que se encuentra en la carpeta **Scenes**.

![img](https://user-images.githubusercontent.com/42527034/118014391-c889e780-b318-11eb-9900-46d1a704c4e7.png)

![Screenshot_2021-05-11-23-08-13-035_maceda alejandro alexiavnplayer](https://user-images.githubusercontent.com/42527034/118014438-d93a5d80-b318-11eb-8989-141d40b330f6.jpg)

### [NAMECHAR]

La instrucción permite colocar un texto con el nombre de un personaje, y se mostrara en la parte inferior de la pantalla, necesita cuatro parámetros, el nombre del personaje, el tamaño del texto, el color del texto y el color de fondo (Si no se desea color de fondo este parámetro puede quedar vacio).

![namechar](https://user-images.githubusercontent.com/42527034/118014511-ec4d2d80-b318-11eb-8584-e6aaaea29b5b.png)

![Screenshot_2021-05-12-11-57-01-394_maceda alejandro alexiavnplayer](https://user-images.githubusercontent.com/42527034/118014848-42ba6c00-b319-11eb-995c-e6ce8c9ae0c3.jpg)

![Screenshot_2021-05-12-11-57-18-156_maceda alejandro alexiavnplayer](https://user-images.githubusercontent.com/42527034/118014868-4817b680-b319-11eb-8a89-36b55639f7b5.jpg)

### [NPLAYER]

La instrucción permite utilizar la información guardada en **Configuración** la cual es el nombre del jugador de la novela, pondra un texto seguido del nombre del jugador y opcionalmente se puede agregar mas texto.

![nplayer](https://user-images.githubusercontent.com/42527034/118014909-54037880-b319-11eb-979c-e8defe400359.png)

![Screenshot_2021-05-12-11-59-21-676_maceda alejandro alexiavnplayer](https://user-images.githubusercontent.com/42527034/118015105-8ad98e80-b319-11eb-89e6-eeaddc720d09.jpg)

### [SHOWBG]

La instrucción muestra una imagen o título en pantalla completa, no necesita ningún parámetro, se recomienda utilizar esta instrucción despues de la instrucción **[TITLE]**.

![showbg](https://user-images.githubusercontent.com/42527034/118015165-9a58d780-b319-11eb-82e2-3d721104c462.png)

### [SOUNDFX]

La instrucción reproduce los efectos de sonido guardados en la carpeta **FX**, se necesitan 2 parámetros los cuales son la palabra **now** y el nombre del archivo del efecto.

![soundfx](https://user-images.githubusercontent.com/42527034/118015185-a04eb880-b319-11eb-8754-b93ff2f75f7b.png)

### [STOPSOUND]

La instrucción detiene cualquier sonido que se este reproduciendo, no necesita parámetros.

![stopsound](https://user-images.githubusercontent.com/42527034/118015206-a5136c80-b319-11eb-8a0e-6417dd0dae45.png)

### [STOPVIDEO]

La instrucción quita la reproducción del video, no necesita ningún parámetro.

![stopvideo](https://user-images.githubusercontent.com/42527034/118015232-aa70b700-b319-11eb-8b47-ba6adaa845b9.png)

### [TEXT]

La instrucción coloca el texto en la parte inferior de la pantalla con un efecto, solo necesita un parámetro, el texto que desea que aparezca.

![text](https://user-images.githubusercontent.com/42527034/118015268-b492b580-b319-11eb-809c-576af7cb024e.png)

![Screenshot_2021-05-12-12-01-42-313_maceda alejandro alexiavnplayer](https://user-images.githubusercontent.com/42527034/118015415-dbe98280-b319-11eb-8070-44e72460d28a.jpg)

### [TEXTBOX]

La instrucción coloca una imagen en la parte de abajo de la pantalla, la cual sirve para que el texto se vea mejor, necesita un parámetro, el nombre de la imagen que se encuentra en la carpeta **Textbox**.

![textbox](https://user-images.githubusercontent.com/42527034/118015557-076c6d00-b31a-11eb-8c06-77532804fb1d.png)

![Screenshot_2021-05-12-12-07-46-097_maceda alejandro alexiavnplayer](https://user-images.githubusercontent.com/42527034/118016283-d7719980-b31a-11eb-9503-46814294d4af.jpg)

### [TEXTBOXCLEAR]

La intrucción quita la imagen de la caja de texto, no necesita ningún parámetro.

![textboxclear](https://user-images.githubusercontent.com/42527034/118016350-e7897900-b31a-11eb-83c3-76085be46537.png)

### [TEXTBOXTRANS]

La instrucción coloca una sombra en la parte inferior de la pantalla, la cual ayuda a que el texto se pueda vizualizar mejor, no necesita ningun parámetro.

![textboxtrans](https://user-images.githubusercontent.com/42527034/118016375-ee17f080-b31a-11eb-9912-f0939aebd5c5.png)

![Screenshot_2021-05-12-12-10-19-574_maceda alejandro alexiavnplayer](https://user-images.githubusercontent.com/42527034/118016508-19024480-b31b-11eb-9977-75472a4e9721.jpg)

### [TEXTCOLOR]

La instrucción permite cambiar el color del texto que se va mostrando en pantalla, necesita un parámetro, el color que sea desea color en hexadecimal.

![textcolor](https://user-images.githubusercontent.com/42527034/118016535-21f31600-b31b-11eb-8f23-f6d430603e81.png)

![Screenshot_2021-05-12-12-12-10-230_maceda alejandro alexiavnplayer](https://user-images.githubusercontent.com/42527034/118016701-549d0e80-b31b-11eb-9cae-dbd4a3376d39.jpg)

### [TITLE]

La instrucción coloca un texto de pantalla completa haciendo un efecto similar al de **[FADEIN]**, necesita 3 parámetros, el texto que se desea mostrar, el nombre del color del texto en inglés o hexadecimal y el tamaño del texto.

![title](https://user-images.githubusercontent.com/42527034/118016717-5b2b8600-b31b-11eb-9280-5f95ed648f88.png)

![Screenshot_2021-05-12-12-13-53-844_maceda alejandro alexiavnplayer](https://user-images.githubusercontent.com/42527034/118016872-8d3ce800-b31b-11eb-8ba7-32438ab62b64.jpg)

### [TITLEOFF]

La instrucción quita el título de la pantalla haciendo un efecto similar al de **[FADEOUT]**, no necesita ningún parámetro.

![titleoff](https://user-images.githubusercontent.com/42527034/118016894-93cb5f80-b31b-11eb-9e0b-cc51211eae1a.png)

### [VIBRATE]

La instrucción hace vibrar el teléfono por un período de tiempo, necesita un parámetro, el cual es el tiempo de vibrado en milisegundo. Por ejemplo 1000 significa que el teléfono va a vibrar por 1 segundo.

![vibrate](https://user-images.githubusercontent.com/42527034/118016916-9928aa00-b31b-11eb-81e0-f5a76a21efd1.png)

### [VIDEO]

La instrucción reproduce un video, necesita un parámetro el cual es el nombre del video que se encuentra en la carpeta **Video**.

![video](https://user-images.githubusercontent.com/42527034/118016950-a2b21200-b31b-11eb-9329-415ae962cd2d.png)

### [WAIT]

La instrucción detiene todo por un período de timpo, necesita un parámetro el cual es es tiempo que desea detener la novel y se expresa en milisegundos. Por ejemplo 2000 milisegundos lo detiene 2 segundos.

![wait](https://user-images.githubusercontent.com/42527034/118016962-a6459900-b31b-11eb-973d-17d961a4293a.png)

### Nota

Para los colores se recomienda utilizar la página https://www.color-hex.com/.



## Opciones



### Creación de nuevos proyectos

Para la creación de nuevos proyectos debes presionar el botón del menú despegable que se encuentra en la parte superior derecha, despues hacer clic en **Crear proyecto en blanco**, finalmente, debes ingresar el nombre de tu proyecto y presionar "ACEPTAR", esto creará una carpeta con el nombre de tu proyecto y dentro de ella se encontraran las carpetas y archivos necesarios para crear tu novela, exceptuando la foto de portada. La visualización de todas las carpertas y archivos puedes hacerla utilizando cualquier navegador de archivos ya sea desde tu celular o utilizando una computadora.

https://user-images.githubusercontent.com/42527034/118031036-af3e6680-b32b-11eb-939a-9a4f309ef0fa.mp4

#### Nota

Si intentas crear un proyecto con el mismo nombre se mostrará un mensaje en pantalla diciendo que el proyecto ya existe y no se volveran a crear las carpetas.

### Cargar proyectos

Para cargar un proyecto debes presionar el botón de **archivos** que se encuentra en la parte inferior derecha de la pantalla, una vez hecho esto se mostrará una pantalla con los archivos de tu dispositivo debes buscar la carpeta **alexavn** y entrar en ella, despues debes buscar la carpeta con el proyecto que deseas cargar y entrar en ella, una vez ahí debes buscar el archivo **config.avn** y hace clic en él, se abrira de nuevo la pantalla principal mostrando el título y la portada del proyecto.

https://user-images.githubusercontent.com/42527034/118031239-f3ca0200-b32b-11eb-8748-bcf7e179b135.mp4

### Eliminación de proyectos

Cuando un proyecto este cargado a un lado del título se encontrárá el botón de **Eliminar** de color azul, al presionarlo se mostrará un mensaje diciendo **¿Estas seguro? se quitara de la lista**, al presionar **ELIMINAR** el proyecto se eliminará de la pantalla principal pero aun se encontran guardadas sus carpetas en nuestro dispositvo.

https://user-images.githubusercontent.com/42527034/118031380-14925780-b32c-11eb-926a-1eb369d34e20.mp4

### Configuración

En la configuración se deben asignar el nombre del jugador de la novela y su sexo, este nombre se puede utilizar en la instrucción **[NPLAYER]** para un mejor desarrollo de la novela visual, para guaradar estos datos solo debes llenarlos y presionar el botón **guardar**. La información se puede vizualizar en la pantalla **Preferencias**.

https://user-images.githubusercontent.com/42527034/118031515-3d1a5180-b32c-11eb-8187-bc2089f56eb9.mp4

### Ayuda

En la ventana de **Ayuda** se mostrarán enlances a tutoriales para utlizar la aplicación asi como un enlace a está documentación, para ir a cualquiera de ellos solo debes dar clic en el enlace y se abrirá tu navegador web.

### Guardar partida 

Para guardar la partida existen 2 opciones, puede ser desde la pantalla donde se esta reproduciendo la novela, en la parte superior derecha se encuentra el botón de **guardar** con la imagen de un disquete, al presionarlo se mostrará un mensaje diciendo **Guardando**. Los datos se guardaran en el primer slot de la pantalla **Guardar**. La segunda opción es presionar el botón de **opciones** que se encuentra aun lado del botón **guardar** y tiene una imagen de engrane, se abrirá la pantalla **Guardar** y puede guardar el avance de su partida en cualquiera de los slots, al guardar los datos se mostrará la imagen de portada de la novela y la fecha en la que se guardaron los datos.

https://user-images.githubusercontent.com/42527034/118032504-5ff93580-b32d-11eb-9a3e-edc5fd55796a.mp4

### Cargar partida

Para cargar una partida primero debe abrir una novela despues de esto en la pantalla donde se esta reproduciendo la novela debe hacer clic en el botón **opciones** que se encuentra en la parte superior derecha y tiene una imagen de engrane, despues de presionar el botón **Cargar** y se vera la pantalla con los slots de sus partidas guardadas, finalmente, presione el slot con la partida que desea cargar y comenzará la reproducción de la novela desde el punto guardado.

https://user-images.githubusercontent.com/42527034/118032562-6f787e80-b32d-11eb-90b0-6e5d6f8eddee.mp4

### Eliminar partida

Para eliminarlos los datos guardados de una partida, debes dirigirte a la ventana de **Guardar** y mantener presionado el slot de la partida que deseas borrar, despues te aparecera un mensaje preguntando si **¿Deseas borrar los datos?** presiona **Si** para eliminar los datos.

https://user-images.githubusercontent.com/42527034/118032643-84eda880-b32d-11eb-8e36-bdcb5f3a20f4.mp4

### Galeria

En la ventana **Galeria** se mostrarán todas las imágenes guardadas en la carpeta **Scenes** en forma de carrousel, para cambiar de imagen solo debe deslizar su dedo a la derecha o a la izquierda.

https://user-images.githubusercontent.com/42527034/118033960-072a9c80-b32f-11eb-92d4-89c029f7624a.mp4

### Preferencias

En la ventana **Preferencias** se mostrará la información guardada en **Configuración**.

![Screenshot_2021-05-12-14-23-30-190_maceda alejandro alexiavnplayer](https://user-images.githubusercontent.com/42527034/118032813-b1a1c000-b32d-11eb-8b4d-f1bde5569d01.jpg)

### Acerca de

En la ventana **Acerca de** se mostrará la información de los programadores.

![Screenshot_2021-05-12-14-23-39-349_maceda alejandro alexiavnplayer](https://user-images.githubusercontent.com/42527034/118032838-b8303780-b32d-11eb-98b5-299fd5af9c7e.jpg)

#### Nota

Si tiene alguna duda, sugerencia o comentario no dude en enviar un correo a cualquiera de los programadores, el proyecto es open source para la comunidad, si puede ayudar para mejorarlo no dude en contactarnos.

### Compartir tu proyecto

Para compartir tu proyecto con cualquier persona lo primero que debes hacer el comprimir la carpeta de tu proyecto en una archivo con estensión **.zip**, despues enviar este archivo a la persona que quieras puede ser por mensaje, correo electrónico, bluetooth, etc.

https://user-images.githubusercontent.com/42527034/118033104-09402b80-b32e-11eb-9e31-c33c6fac256d.mp4

La otra presona deberá tener la aplicación de Alexia VN instalada en su celular. Una vez que la persona reciba el archivo comprimido **.zip**, deberá colocar dicho archivo dentro de la carperta **alexvn** y despues descomprimirlo (Esto se puede hacer desde una computadora o utilizando una aplicación como **ASTRO** para celular), cuando el archivo este descomprimido aparecera una carpeta con el nombre del proyecto.

https://user-images.githubusercontent.com/42527034/118033455-7784ee00-b32e-11eb-9167-10548d2addbc.mp4

Finalmente, se deben seguir los pasas descritos en **Cargar proyectos**.

## Contribuir código 

 Puede enviar pull request al [repositorio de github](https://github.com/Fernando1612/AlexiaVN).

## Licencia

Este proyecto está bajo la licencia GNU General Public License v2 o posterior. Puede leerlo en línea en ([v2](http://www.gnu.org/licenses/gpl-2.0.html) o [v3](http://www.gnu.org/licenses/gpl.html)).

