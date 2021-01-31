![logo]

## Descripción

Os presentamos el clásico juego del *Buscaminas* con mejoras añadidas:

- 3 niveles de juego + 1 personalizado. 
- Ranking con los 3 diferentes niveles de juego.
- 3 diferentes tipos de minas: mina normal, mina reset y mina 50%.
- Opción de personalizar iconos del tablero y sonidos del juego.
- Instrucciones de las nuevas minas y del funcionamiento del juego.

![ventana-nivel-1-jugada]

### Hecho con
Frameworks y programas usados para hacer esta aplicación:
* [Maven](https://maven.apache.org)
* [Java](https://www.java.com)
* [MySQL Server](https://www.mysql.com)
* [IntelliJ IDEA](https://www.jetbrains.com)


<!-- GETTING STARTED -->
## Configurando este Buscaminas...

Se necesita tener la versión de MySQL Server. Está testeado con MySQL 8.0. Una vez que se tenga instalado, 
se debe ejecutar en la base de datos, el script que está en la carpeta _Resources_, llamado [Script-bd-buscaminas.sql](https://github.com/NaiaraManeiro/BuscaminasSimple/blob/master/src/main/resources/Script-bd-buscaminas.sql).
Cuando se ejecute, se debe ir a la clase [GestorBD](https://github.com/NaiaraManeiro/BuscaminasSimple/blob/master/src/main/java/packControlador/GestorBD.java), y cambiar los atributos, _nombreBD_, _usuarioBD_, _contrasenaBD_, _host_ y _puerto_.

### Prerequesitos
Se necesita tener instalado IntelliJ IDEA y descargar del mismo programa el openJDK-15 que se usa para ejecutar esta aplicación.

<!-- USAGE EXAMPLES -->
## Usage

### Personalización del buscaminas
Se pueden personalizar los **iconos del tablero** y los sonidos de **ganar** y de **perder**.

![ventana-iconos]

#### Los iconos del tablero
Se puede elegir entre:
- Emojis

![ventana-buscaminas-emoji]
- Los clásicos

![ventana-buscaminas-classic2]

![ventana-nivel-1-jugada]
#### Los sonidos
Se puede escuchar una preview para facilitar la elección.

![ventana-sonidos]


### Niveles

Se ofrecen tres niveles para jugar:

- Nivel 1

![ventana-nivel-1-jugada]
- Nivel 2

![ventana-nivel-2]
- Nivel 3

![ventana-nivel-3]
  

### Autores
* [Leire](https://github.com/leiregonzalez11)
* [Naiara](https://github.com/NaiaraManeiro)
* [Aitor](https://github.com/aitorjus)

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[ventana-iconos]: images/ventana_iconos.png
[ventana-sonidos]: images/ventana_sonidos.png
[ventana-buscaminas-classic2]: images/ventana_buscaminas_classic2.png
[ventana-buscaminas-emoji]: images/ventana_buscaminas_emoji.png
[ventana-nivel-1-jugada]: images/ventana_nivel1_jugada.png
[ventana-nivel-2]: images/ventana_nivel_2.png
[ventana-nivel-3]: images/ventana_nivel_3.png
[logo]: images/logo.png
