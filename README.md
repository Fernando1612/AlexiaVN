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

----------------------------------------------------------------PONER  IMAGEN--------------------------------------------------------------

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

-------------------------------------PONER IMAGEN---------------------------------------------------

### [CHAR]

La instrucción coloca la imagen de un personaje, necesita dos parámetros, la posición las cuales pueden ser **left** para ponerlo a la izquierda de la pantalla, **center** para ponerlo al centro de la pantalla o **right** para ponerlo a la derecha de la pantalla y el nombre de la imagen del personaje que se encuentra en la carpeta **Chars**.

--------------------PONER IMAGEN-----------------------------------------------

### [CHARBIG] 

La instrucción es similar a **[CHAR]** la diferencia es que **[CHARBIG]** hace la imagen del personaje mas grande, tiene los mismos parametros que char.

-------------------------PONER IMAGEN----------------------------------------

### [CHARBIGCLEAR]

La instrucción es similar a **[CHARBIGCLEAR]**, quita la imagen grande del personaje y necesita el mismo parametro que **[CHARCLEAR]**.

--------------------------------PONER  IMAGEN--------------------------------

### [CHARCLEAR]

La instrucción quita la imagen del personaje, necesita solo un parámeto el cual es la posición donde esta el personaje, para quitarlo.

----------------------------PONER IMAGEN---------------------------

### [CHOICES]

La instrucción sirve para colocar botones en los cuales puedes poner decisiones dentro de la novela y asi tomar los diferentes caminos que existan, como mínimo se necesitan 7 parámetros:

1. Texto que aparece en la parte inferior
2. Texto que aparece en el botón
3. Nombre del nuevo archivo que se va a leer y se debe encontrar en la carpeta **Scripts**
4. Tamaño del botón en pixeles
5. Tamaño del texto del botón
6. Color de fondo del botón en hexadecimal
7. Color del texto en hexadecimal

------------------------------PONER IMAGEN-------------------------------------------------------------------

Para poner más de una decisión se debe agregar el simbolo **+** al final y poner los parámetros del 2 al 7 de nuevo, como se muestra en el ejemplo. 

-------------------------PONER IMAGEN-------------------------------------------------

Se puede seguir asi hasta n decisiones agregando sus respectivos parametros (Todo debe ser en una sola línea). La instrucción **[CHOISES]** y **[CHOISESIMAGE]** deben ir al final de cada archivo de la carpeta **Scripts** para continuar la historia, exceptuando el ultimo archivo de la novela. 

### [CHOISESIMAGE]

La instrucción es muy similar a **[CHOICES]**, la diferencias es que en lugar de usar botones ahora se pueden utilizar imágenes, como minímo se necesitan 5 parámetros:

1. Texto que aparece en la parte inferior
2. Nombre de la imagen a utilizar la cual debe estar en la carpeta **Scenes**
3. Nombre del nuevo archivo que se va a leer y se debe encontrar en la carpeta **Scripts**
4. Ancho de la imagen en pixeles
5. Alto de la imagen en pixeles

---------------------------------------------PONER IMAGEN-----------------------------------------------

Se recomienda utilizar imágenes con extensión **.png** y sin fondo.

Para poner más de una decisión se debe agregar el simbolo **+** al final y poner los parámetros del 2 al 5 de nuevo, como se muestra en el ejemplo. 

-----------------------------------PONER IMAGEN---------------------------------------------------------------

Se puede seguir asi hasta n decisiones agregando sus respectivos parametros (Todo debe ser en una sola línea). La instrucción **[CHOISES]** y **[CHOISESIMAGE]** deben ir al final de cada archivo de la carpeta **Scripts** para continuar la historia, exceptuando el ultimo archivo de la novela. 

### [CLEARNAMECHAR]

La instrucción quita el texto con el nombre de un personaje de la pantalla, no necesita ningún parámetro.

-----------------------PONER IMAGEN--------------------------------------------

### [END]

La instrucción termina la novela visual y muesta el mensaje "FIN DE LA NOVELA", no necesita ningún parámetro.

------------------------------------PONER IMAGE---------------------------------------------

### [FADEIN]

La instrucción hace el efecto de "aparecer", puede ser una imagen o un video, no lleva ningún parámetro.

------------------------------PONER IMAGEN-----------------------------------------------

### [FADEOUT]

La instrucción hace el efecto de "desaparecer", puede ser una imagen o un video, no lleva ningún parámetro.

-------------------------------------PONER IMAGEN---------------------------------------

### [IGNORE]

La instrucción permite hacer anotaciones dentro del archivo, que no aparecerán en la novela, necesita un parámetro, la anotación que deseas poner en el archivo.

----------------------PONER IMAGEN----------------------------------------------

### [IMG]

La instrucción coloca una imagen como fondo, necesita un parámetro el cual es el nombre de la imagen que se encuentra en la carpeta **Scenes**.

---------------------------PONER IMAGEN-----------------------------------------------------------

### [NAMECHAR]

La instrucción permite colocar un texto con el nombre de un personaje, y se mostrara en la parte inferior de la pantalla, necesita cuatro parámetros, el nombre del personaje, el tamaño del texto, el color del texto y el color de fondo (Si no se desea color de fondo este parámetro puede quedar vacio).

--------------------------------PONER IMAGEN-------------------------------

### [NPLAYER]

La instrucción permite utilizar la información guardada en **Configuración** la cual es el nombre del jugador de la novela, pondra un texto seguido del nombre del jugador y opcionalmente se puede agregar mas texto.

------------------------------poner imagen-------------------------------------------------

### [SHOWBG]

La instrucción muestra una imagen o título en pantalla completa, no necesita ningún parámetro, se recomienda utilizar esta instrucción despues de la instrucción **[TITLE]**.

-----------------------PONER IMAGEN---------------------------------------------

### [SOUNDFX]

La instrucción reproduce los efectos de sonido guardados en la carpeta **FX**, se necesitan 2 parámetros los cuales son la palabra **now** y el nombre del archivo del efecto.

--------------------------------PONER IMAGEN-----------------------------------------

### [STOPSOUND]

La instrucción detiene cualquier sonido que se este reproduciendo, no necesita parámetros.

----------------------------------PONER IMAGEN---------------------------------------------------------

### [STOPVIDEO]

La instrucción quita la reproducción del video, no necesita ningún parámetro.

--------------------------------------PONER IMGEN--------------------------------

### [TEXT]

La instrucción coloca el texto en la parte inferior de la pantalla con un efecto, solo necesita un parámetro, el texto que desea que aparezca.

---------------------------------PONER IMAGEN----------------------------------------------

### [TEXTBOX]

La instrucción coloca una imagen en la parte de abajo de la pantalla, la cual sirve para que el texto se vea mejor, necesita un parámetro, el nombre de la imagen que se encuentra en la carpeta **Textbox**.

--------------------------------------PONER IMAGEN-----------------------------------------

### [TEXTBOXCLEAR]

La intrucción quita la imagen de la caja de texto, no necesita ningún parámetro.

------------------------------PONER IMAGEN--------------------------------------------

### [TEXTBOXTRANS]

La instrucción coloca una sombra en la parte inferior de la pantalla, la cual ayuda a que el texto se pueda vizualizar mejor, no necesita ningun parámetro.

------------------------------PONER IMAGEN--------------------------------------------

### [TEXTCOLOR]

La instrucción permite cambiar el color del texto que se va mostrando en pantalla, necesita un parámetro, el color que sea desea color en hexadecimal.

--------------------------------PONER IMAGE-----------------------------------------------

### [TITLE]

La instrucción coloca un texto de pantalla completa haciendo un efecto similar al de **[FADEIN]**, necesita 3 parámetros, el texto que se desea mostrar, el nombre del color del texto en inglés o hexadecimal y el tamaño del texto.

---------------------------------PONER IMAGEN----------------------------------------

### [TITLEOFF]

La instrucción quita el título de la pantalla haciendo un efecto similar al de **[FADEOUT]**, no necesita ningún parámetro.

-----------------------------------------PONER IMAGEN-------------------------------

### [VIBRATE]

La instrucción hace vibrar el teléfono por un período de tiempo, necesita un parámetro, el cual es el tiempo de vibrado en milisegundo. Por ejemplo 1000 significa que el teléfono va a vibrar por 1 segundo.

-----------------------------------PONER IMAGEN------------------------------

### [VIDEO]

La instrucción reproduce un video, necesita un parámetro el cual es el nombre del video que se encuentra en la carpeta **Video**.

----------------------------------------PONER IMAGEN------------------------------------------------

### [WAIT]

La instrucción detiene todo por un período de timpo, necesita un parámetro el cual es es tiempo que desea detener la novel y se expresa en milisegundos. Por ejemplo 2000 milisegundos lo detiene 2 segundos.

-----------------------------PONER IMAGEN----------------------------------

### Nota

Para los colores se recomienda utilizar la página https://www.color-hex.com/.





