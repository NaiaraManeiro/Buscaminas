![logo]

<!-- TABLE OF CONTENTS -->
  <summary><h2 style="display: inline-block">Tabla de contenidos</h2></summary>
  <ol>
    <li>
      <a href="#descripcion">Sobre el Proyecto</a>
      <ul>
        <li><a href="#desarrollado-con">Desarrollado con</a></li>
      </ul>
    </li>
    <li>
      <a href="#configurando-este-buscaminas">Configurando este Buscaminas</a>
      <ul>
        <li><a href="#prerequesitos">Prerequesitos</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li>
        <a href="#usos">Usos</a>
        <ul>
            <li><a href="#personalizacion-del-buscaminas">Personalización del buscaminas</a>
                <ul>
                    <li><a href="#los-iconos-del-tablero">Los iconos del tablero</a></li>
                    <li><a href="#los-sonidos">Los sonidos</a></li>
                </ul>
            </li>
            <li><a href="#niveles">Niveles</a></li>
            <li><a href="#ayuda-sobre-el-juego">Ayuda sobre el juego</a></li>
        </ul>
    </li>
    <li><a href="#autores">Autores</a></li>
  </ol>

## Descripción

Os presentamos el clásico juego del *Buscaminas* con mejoras añadidas:

- 3 niveles de juego + 1 personalizado. 
- Ranking con los 3 diferentes niveles de juego.
- 3 diferentes tipos de minas: mina normal, mina reset y mina 50%.
- Opción de personalizar iconos del tablero y sonidos del juego.
- Instrucciones de las nuevas minas y del funcionamiento del juego.



<p align="center">
  <img src="https://raw.githubusercontent.com/NaiaraManeiro/BuscaminasSimple/feature/readme/images/ventana_nivel1_jugada.png?token=AKRAPAFXK4SPQ2AKZMCE3ALAD7H2O">
</p>

### Desarrollado con
Frameworks y programas usados para hacer esta aplicación:
* [Maven](https://maven.apache.org)
* [Java](https://www.java.com)
* [MySQL Server](https://www.mysql.com)
* [IntelliJ IDEA](https://www.jetbrains.com)


<!-- GETTING STARTED -->
## Configurando este Buscaminas

Se necesita tener la versión de MySQL Server. Está testeado con MySQL 8.0. Una vez que se tenga instalado, 
se debe ejecutar en la base de datos, el script que está en la carpeta _Resources_, llamado [Script-bd-buscaminas.sql](https://github.com/NaiaraManeiro/BuscaminasSimple/blob/master/src/main/resources/Script-bd-buscaminas.sql).
Cuando se ejecute, se debe ir a la clase [GestorBD](https://github.com/NaiaraManeiro/BuscaminasSimple/blob/master/src/main/java/packControlador/GestorBD.java), y cambiar los atributos, _nombreBD_, _usuarioBD_, _contrasenaBD_, _host_ y _puerto_.

### Prerequesitos
Se necesita tener instalado IntelliJ IDEA y descargar del mismo programa el openJDK-15 que se usa para ejecutar esta aplicación.

<!-- USAGE EXAMPLES -->
## Usos

### Personalización del Buscaminas
Se pueden personalizar los **iconos del tablero** y los sonidos de **ganar** y de **perder**.

<p align="center">
  <img src="https://raw.githubusercontent.com/NaiaraManeiro/BuscaminasSimple/feature/readme/images/ventana_iconos.png?token=AKRAPAGICXCA5JY45ZP3JJ3AD7H2O">
</p>

#### Los iconos del tablero
Se puede elegir entre:
- Emojis

<p align="center">
  <img src="https://raw.githubusercontent.com/NaiaraManeiro/BuscaminasSimple/feature/readme/images/ventana_buscaminas_emoji.png?token=AKRAPAHSN2J2QYEJIN7ZWOLAD7H2W"/>
</p>
- Los clásicos

<p align="center">
  <img src="https://raw.githubusercontent.com/NaiaraManeiro/BuscaminasSimple/feature/readme/images/ventana_buscaminas_classic2.png?token=AKRAPACFG6GKMPXXRUKD2MTAD7H2Y"/>
</p>

<p align="center">
  <img src="https://raw.githubusercontent.com/NaiaraManeiro/BuscaminasSimple/feature/readme/images/ventana_nivel1_jugada.png?token=AKRAPAFXK4SPQ2AKZMCE3ALAD7H2O"/>
</p>

#### Los sonidos
Se puede escuchar una preview para facilitar la elección.

<p align="center">
<img src="https://raw.githubusercontent.com/NaiaraManeiro/BuscaminasSimple/feature/readme/images/ventana_sonidos.png?token=AKRAPAHUCJC55B5KE6ELYYDAD7H2G" />
</p>


### Niveles

Se ofrecen tres niveles para jugar:

- Nivel 1

<p align="center">
<img src="https://raw.githubusercontent.com/NaiaraManeiro/BuscaminasSimple/feature/readme/images/ventana_nivel1_jugada.png?token=AKRAPAFXK4SPQ2AKZMCE3ALAD7H2O" />
</p>

- Nivel 2

<p align="center">
<img src="https://raw.githubusercontent.com/NaiaraManeiro/BuscaminasSimple/feature/readme/images/ventana_nivel_2.png?token=AKRAPAF5TNORHEEFUYUJYADAD7H2K" />
</p>

- Nivel 3

<p align="center">
<img src="https://raw.githubusercontent.com/NaiaraManeiro/BuscaminasSimple/feature/readme/images/ventana_nivel_3.png?token=AKRAPACD7VQO34XF4M35G2DAD7H2I" />
</p>

### Ayuda sobre el juego

Se ofrecen varias interfaces de ayuda para entender el funcionamiento del juego, así como las diferentes posibilidades que hay.
- Una descripción del juego, así como un mensaje de bienvenida.
<p align="center">
<img src="https://raw.githubusercontent.com/NaiaraManeiro/BuscaminasSimple/feature/readme/images/ayuda-info-juego.png?token=AKRAPADZU5RQPJD6LGW4FBLAD755S" />
</p>

- Información sobre qué son las minas especiales.

<p align="center">
<img src="https://raw.githubusercontent.com/NaiaraManeiro/BuscaminasSimple/feature/readme/images/ayuda-minas-especiales.png?token=AKRAPAEOOV5MI2I6YC77IP3AD755U" />
</p>


<p align="center">
<img src="https://raw.githubusercontent.com/NaiaraManeiro/BuscaminasSimple/feature/readme/images/ayuda-como-jugar.png?token=AKRAPAACPP6G2SH2IC7DNA3AD755O" />
</p>



## Autores
* [Leire - GitHub](https://github.com/leiregonzalez11)
* [Naiara - GitHub](https://github.com/NaiaraManeiro)
* [Aitor - GitHub](https://github.com/aitorjus)

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[logo]: images/logo.png
