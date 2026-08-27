# Documentación Técnica y Manual del Motor AlexiaVN Player

## 1. Visión General del Proyecto

**AlexiaVN Player** es un motor y reproductor de novelas visuales (Visual Novels) desarrollado en **Java nativo para Android**, distribuido bajo la licencia libre **GNU GPL v2 o posterior**. Su objetivo es permitir la creación y reproducción de historias interactivas de manera sencilla directamente desde dispositivos Android, sin necesidad de conocimientos de programación.

El motor soporta la incorporación de texto, gráficos (fondos, personajes, cajas de texto), efectos de sonido, música de fondo, cinemáticas en video y ramificaciones de historia mediante menús de decisiones interactivas.

---

## 2. Arquitectura General y Estructura de Proyectos

### 2.1. Estructura de Archivos y Carpetas en el Dispositivo

Cada proyecto de novela visual administrado por AlexiaVN reside en el almacenamiento externo (`/sdcard/alexavn/<NombreProyecto>/`) y está estructurado en 10 carpetas y 2 archivos principales:

```
/sdcard/alexavn/<NombreProyecto>/
├── config.avn            # Archivo de configuración inicial del proyecto
├── portada.png           # Imagen de portada de la novela visual
├── BGM/                  # Música de fondo (.mp3, .wav, .flac)
├── Chars/                # Sprites de personajes en PNG transparente (posiciones left, center, right)
├── FX/                   # Efectos de sonido breves (.mp3, .wav, .flac)
├── Menu/                 # Recursos gráficos para menús personalizados (en desarrollo)
├── Scenes/               # Fondos de pantalla, CGs e imágenes generales (.jpg, .png, .bmp)
├── Scripts/              # Archivos de guion/script con extensión .txt
├── Textbox/              # Texturas y marcos para cajas de diálogo (.png sin fondo)
├── Video/                # Cinematográficas y videos de introducción (.mp4, .avi, .mkv)
└── Voices/               # Clips de voz para personajes (en desarrollo)
```

---

## 3. Especificación del Archivo de Configuración (`config.avn`)

El archivo **`config.avn`** es leído por el cargador de la aplicación y debe contener exactamente **3 elementos separados por comas (`,`)** en una sola línea:

```text
Título de la Novela, Archivo_Inicial_en_Scripts.txt, imagen_portada.png
```

Ejemplo:
```text
El Último Dinosaurio, inicio.txt, portada.jpg
```

---

## 4. Análisis de Componentes Principales

### 4.1. `MainActivity.java`: Menú Principal y Gestor de Proyectos

El módulo [`MainActivity.java`](file:///Users/fernandomaceda/Documents/GitHub/AlexiaVN/AlexiaVNPlayer/app/src/main/java/maceda/alejandro/alexiavnplayer/MainActivity.java) actúa como el Dashboard principal del reproductor:

- **Gestión de Permisos de Almacenamiento**: Soporta el esquema de permisos en tiempo de ejecución tanto para Android 11+ (`MANAGE_APP_ALL_FILES_ACCESS_PERMISSION`) como para versiones anteriores (`READ_EXTERNAL_STORAGE` / `WRITE_EXTERNAL_STORAGE`).
- **Descompresión / Copia Automática de Demostración**: Al conceder permisos por primera vez, copia automáticamente el proyecto demo integrado `UltimoDinosaurio` desde los `assets` de la aplicación a `/sdcard/alexavn/UltimoDinosaurio`.
- **Historial de Novelas Recientes (`GetRecents`)**: Consulta asíncrona a la base de datos SQLite (`recents`) para mostrar en un `RecyclerView` las novelas iniciadas con su portada y datos.
- **Creación de Proyectos en Blanco (`crearProyecto`)**: Crea dinámicamente el árbol de 10 carpetas y genera el archivo `config.avn` vacío.
- **Configuración del Jugador (`ResultadoCuadroDialogo`)**: Almacena en `SharedPreferences` los datos personales del jugador (Nombre y Sexo), los cuales pueden inyectarse dinámicamente en los diálogos mediante la etiqueta `[NPLAYER]`.

---

### 4.2. `alexavn.java`: Motor de Ejecución de la Novela

El módulo [`alexavn.java`](file:///Users/fernandomaceda/Documents/GitHub/AlexiaVN/AlexiaVNPlayer/app/src/main/java/maceda/alejandro/alexiavnplayer/alexavn.java) es la actividad donde se ejecuta la lectura e interpretación del guion:

- **Typewriter (Máquina de escribir)**: Muestra el diálogo progresivamente. Un toque en la pantalla cancela la animación y muestra la línea completa inmediatamente.
- **Decodificación eficiente de Bitmaps**: `decodeSampledBitmapFromUri` escala dinámicamente las imágenes según el espacio disponible para prevenir errores `OutOfMemoryError`.
- **Tokenizador con Separador `+`**: Lee archivos `.txt` en la carpeta `Scripts/` línea por línea y evalúa la instrucción separada por `+`.
- **Modo CG (Ocultar Interfaz)**: `full_image(View v)` permite ocultar los elementos de la interfaz de usuario para apreciar la ilustración de fondo limpia.

---

## 5. Especificación Completa del Lenguaje de Scripting

Todas las instrucciones dentro de los archivos `.txt` en `Scripts/` utilizan el carácter **`+`** como separador de parámetros:

| Comando / Token | Sintaxis Completa | Descripción y Parámetros |
| :--- | :--- | :--- |
| `[BGM]` | `[BGM]+looping+archivo.mp3` | Reproduce música de fondo desde `/BGM/`. El segundo parámetro suele ser `looping`. |
| `[STOPSOUND]` | `[STOPSOUND]` | Detiene la música de fondo y los efectos activos. |
| `[SOUNDFX]` | `[SOUNDFX]+now+efecto.mp3` | Reproduce un efecto de sonido secundario desde `/FX/`. |
| `[IMG]` | `[IMG]+fondo.jpg` | Establece el fondo de escena desde `/Scenes/`. |
| `[CHAR]` | `[CHAR]+posición+sprite.png` | Muestra un personaje en la posición `left`, `center` o `right` desde `/Chars/`. |
| `[CHARBIG]` | `[CHARBIG]+posición+sprite.png` | Carga una versión ampliada del sprite en `left`, `center` o `right`. |
| `[CHARCLEAR]` | `[CHARCLEAR]+posición` | Elimina el personaje en la posición indicada. |
| `[CHARBIGCLEAR]` | `[CHARBIGCLEAR]+posición` | Elimina el personaje ampliado en la posición indicada. |
| `[TEXTBOX]` | `[TEXTBOX]+caja.png` | Carga un marco de diálogo personalizado desde `/Textbox/`. |
| `[TEXTBOXCLEAR]` | `[TEXTBOXCLEAR]` | Remueve la textura de la caja de diálogo. |
| `[TEXTBOXTRANS]` | `[TEXTBOXTRANS]` | Aplica una sombra/fondo translúcido estándar (`#80000000`) a la caja. |
| `[TEXTCOLOR]` | `[TEXTCOLOR]+#HEX` | Cambia el color del texto principal de la historia. |
| `[NAMECHAR]` | `[NAMECHAR]+Nombre+Tamaño+#ColorTexto+#ColorFondo` | Muestra y da formato a la caja del nombre del personaje hablante. |
| `[CLEARNAMECHAR]` | `[CLEARNAMECHAR]` | Oculta la caja de nombre del personaje. |
| `[CHOICES]` | `[CHOICES]+TextoInferior+Label1+Script1.txt+AnchoPx1+Size1+HexBg1+HexTxt1+...` | Genera botones de decisiones para ramificación. Requiere mínimo 7 parámetros (se extienden de 6 en 6 por cada opción adicional). |
| `[CHOICESIMAGE]` | `[CHOICESIMAGE]+TextoInferior+Img1.png+Script1.txt+AnchoPx1+AltoPx1+...` | Genera opciones de decisiones basadas en imágenes cliqueables desde `/Scenes/`. |
| `[NPLAYER]` | `[NPLAYER]+TextoAntes+TextoDespues` | Inyecta automáticamente el nombre del jugador configurado en la app en medio de dos fragmentos de texto. |
| `[IGNORE]` | `[IGNORE]+Anotación de comentario` | Permite escribir comentarios o notas internas en el script que el motor ignorará. |
| `[SHOWBG]` | `[SHOWBG]` | Muestra la escena a pantalla completa (frecuentemente usado después de `[TITLE]`). |
| `[VIDEO]` | `[VIDEO]+video.mp4` | Reproduce un video cinemático desde la carpeta `/Video/`. |
| `[STOPVIDEO]` | `[STOPVIDEO]` | Detiene el video y restablece la escena gráfica. |
| `[WAIT]` | `[WAIT]+milisegundos` | Congela la ejecución por N milisegundos (ej: `2000` = 2 segundos). |
| `[FADEIN]` / `[FADEOUT]` | `[FADEIN]` / `[FADEOUT]` | Ejecuta animaciones de desvanecimiento en el fondo. |
| `[VIBRATE]` | `[VIBRATE]+milisegundos` | Hace vibrar el dispositivo móvil durante N milisegundos. |
| `[TITLE]` | `[TITLE]+Texto+Color+#Tamaño` | Renderiza un título centrado animado a pantalla completa. |
| `[TITLEOFF]` | `[TITLEOFF]` | Desvanece y oculta el título centrado. |
| `[AVATAR]` | `[AVATAR]+imagen.png` | Muestra un avatar gráfico en la parte inferior de la pantalla. |
| `[AVATAROFF]` | `[AVATAROFF]` | Oculta el avatar. |
| `[SAVE]` | `[SAVE]` | Actualiza el registro de progreso en SQLite. |
| `[END]` | `[END]` | Muestra el mensaje "FIN DE LA NOVELA" y finaliza la actividad. |
| **(Texto Plano)** | `Cualquier texto` | Se procesa como diálogo estándar dentro de la caja de texto. |

---

## 6. Sistema de Guardado, Carga y Reanudación de Partidas

### 6.1. Guardado de Partida (Slots / SharedPreferences)
El motor almacena en `SharedPreferences` (`saveVNslot1`) el estado exacto de la partida:
- Nombre y ruta del proyecto.
- Nombre del script activo y la línea exacta (`saveLineaExacta`).
- Fondos, personajes y cajas de texto visibles al momento de guardar.
- Texto mostrado en pantalla.

### 6.2. Carga Inteligente de Partida (`acciones2`)
Al cargar una partida previamente guardada:
1. El motor abre el script correspondiente y ejecuta `acciones2()`, un bucle silencioso que no reproduce audio ni animaciones mientras avanza rápidamente por las líneas.
2. Al llegar a `recuperarLineaExacta`, se detiene el avance rápido y se ejecuta `recuperarDatos()`, restableciendo los elementos visuales (`recuperarImageFondo`, `recuperarImageBox`, `recuperarImageChar`) sin interferencia de sonido.

---

## 7. Persistencia de Datos con SQLite (`DatabaseConnector`)

El motor gestiona el historial de lectura a través de la base de datos `store` y la tabla `recents`:

```sql
CREATE TABLE recents (
    _id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT,
    path TEXT,
    file TEXT,
    image TEXT,
    savefile TEXT,
    line INTEGER,
    username TEXT
);
```

---

## 8. Distribución y Compartición de Proyectos

Para compartir una novela creada en AlexiaVN:
1. Comprimir la carpeta del proyecto en formato **`.zip`**.
2. Enviar el archivo comprimido al usuario destino.
3. El usuario debe descomprimir el archivo `.zip` dentro del directorio `/sdcard/alexavn/` de su dispositivo.
4. Cargar el proyecto desde AlexiaVN navegando hasta `/sdcard/alexavn/<Proyecto>/config.avn`.
