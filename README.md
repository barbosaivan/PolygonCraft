# PolygonCraft
_Aplicación desarrollada en Kotlin-Android para la creación de formas poligonales. (Escenario práctico)_

_Aplicación desarrollada en Kotlin usando la arquitectura MVVC, que presenta información obtenida mediante un consumo API REST y consultas a tablas de datos en SQLite._

_Se implementó la librería Retrofit2 en su versión 2.9.0 para realizar el consumo del servicio API REST._

_Se implementó la librería Room en su versión 2.6.1 para almacenar los datos de forma local._

_Se implementó la librería dagger hilt para realizar inyección de dependencias._

_Se implementó la librería coroutines para la gestión de tareas asíncronas._

_La aplicación contiene dos interfaces: la interfaz principal con un listado de polígonos pre-creados y una interfaz desing que presenta, permite la modificación y guardado del polígono seleccionado._

_La información de la interfaz principal se presenta en contenedores de tipo CardView, los cuales fueron inflados mediante un adaptador._

_Los datos presentados en la interfaz Home mediante cardView son name(Polygon) y número de lados(Point)._

_La interfaz Home permite crear nuevos polígonos regulares ingresando el número de lados y la escala._

_En la interfaz desing se genera un polígono usando la clase Canvas según los puntos dados en el polígono pre-creado o el número de lados al crear uno nuevo._

_En la interfaz Desing permite guardar el polígono presente en pantalla dándole un nombre único._

_Se implementaron las librerías Junit, Mockito, Coroutines-test para la realización de las pruebas unitarias._


## Instrucciones de instalación

_El proyecto se encuentra en la rama máster._

_La clonación del proyecto se realiza mediante la siguiente URL: https://github.com/barbosaivan/PolygonCraft.git_

_El nivel de API mínimo requerido para la instalación de la aplicación es: 24._

_El android gradle plugin versión usada es: 8.2.1._

## Funcionalidad
_La aplicación muestra un listado de polígonos almacenados en una base de datos SQLite, la cual es gestionada con Romm._

_Los datos son previamente descargados usando retrofit y almacenados en la base de datos local._

_Los datos ya cargados en la base de datos no se vuelven a insertar._

_La aplicación cuenta con una interfaz escroleable. Al dar clic sobre un polígono, te envía a una nueva interfaz donde se genera en pantalla el polígono seleccionado._

_Para crear un nuevo polígono regular, se debe insertar un número de lados válido y seleccionar una escala._

_La interfaz Desing genera el polígono y permite modificar los valores de los puntos generados arrastrando estos con el touch de la pantalla._

_La interfaz Desing permite almacenar el polígono creado o modificado, dándole un nombre único._

_Los polígonos creados se ven reflejados de inmediato en la interfaz Home._

_Al iniciar la aplicación, se dibujará en pantalla automáticamente el polígono más reciente creado. Si se da el caso, este solo sucederá una vez después de creado._

## Url API REST
_Polygons:_
_http://74.235.109.154/api/polygons_

 
 ## Herramientas

 * retrofit2

 * Room

 * dagger hilt
 
 * androidx

 * Mockito
 
 ## Dependencias implementasdas

* Retrofit2

_implementation ("com.squareup.retrofit2:retrofit:2.9.0")_

_implementation ("com.squareup.retrofit2:converter-gson:2.9.0")_


* Room

_implementation ("androidx.room:room-runtime:2.6.1")_

_kapt ("androidx.room:room-compiler:2.6.1")_

_implementation ("androidx.room:room-ktx:2.6.1")_

* Corrutinas

_implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")_

* dagger hilt

_implementation ("com.google.dagger:hilt-android:2.50")_

_kapt ("com.google.dagger:hilt-android-compiler:2.50")_

* ViewModel

 _implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")_

* Dependecia andoroid ktx

_implementation("androidx.activity:activity-ktx:1.8.2")_

* Mockito

_testImplementation ("org.mockito:mockito-inline:3.12.4")_

_testImplementation ("androidx.arch.core:core-testing:2.2.0")_

* Coroutines-test

_testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")_

 ## Autor
_Ivan Barbosa Ortega_

_Ejercicio como prueba para optar a la vacante de desarrollador Android en la empresa GCA_

_Desarrollador Android || Ingeniero de sistemas._
