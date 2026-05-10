# PROYECTO FINAL IS-210 (PROGRAMACION II) 

## Requerimientos

Debe de existir una pantalla principal donde se centraliza todas las funcionalidades del sistema.

1) El sistema tendra 2 pestañas:
#### Pestaña para gestionar anotaciones:
- El usuario podra guardar los elementos creados, eliminar sus documentos, modificar su contenido y buscar sus notas. 
- Contara con los siguientes botones: ***`Nuevo`***, ***`Agregar una nueva nota`***, ***`Eliminar nota`***.
- Los botones poseerán un tooltip que describe la funcion de cada boton.
- Incluira una talbla donde aparecerea le fecha de creacion u modificación y una tabla con el contenido de la nota.
- Al cerrarse el programa debera de recordar las notas que fueron escritos por el usuario con la fecha de creación y sus contenidos.


#### Pestaña para gestionar clientes:
- Contendra el nombre que el usuario haya agregado, numero de telefono el cual el usuario agregara y el correo electronico que el usuario agrego.
- Contara con los siguientes botones: ***`Nuevo`***, ***`Guardar nota`***, ***`Eliminar nota`***.
- Los botones poseerán un tooltip que describe la funcion de cada boton.
- Incluira una tabla donde aparecerea el nombre del usuario, numero telefonico y el correo electronico del usuario.
- Al cerrarse el programa debera de recordar las notas que fueron escritos por el usuario.

2) El sistema tendra una barra menu en la parte superior de la ventana que contendra los siguientes botones:
- ***Archivos***: Poseerá *`Volver a cargar serializado`*, *`Borrar serializado`* y *`Salir`*.
- ***Ayuda***: Pooserá un *`Acerca de`* donde visualizara la información del creador del sistema, con información de nombre, su correo institucional, asignatura, fecha, docente y número de cuenta, 2 a 3 parrafos del informacion sobre como funciona el programa, que contendra titulos grandes, distintos fonts de letras, colores y subrayados.


## Analisis Orientado a Objeto

### Program
Contendra el GUI con todos los botones creados en la ventana de diseño asi tambien con las acciones de los botones con un validador en las acciones de algunos botones para poder tener accesos a utilizar los botones que deben de ejecutarse despues.

### Validator
Validara que solo se ingrese numero en vez de letras para que requiera que ponga correctamente los valores de numero telefonico, validaremos tambien si pone un correo real de todas las opciones que tienen para hacer un correo haciendo que aparezca @ y el tipo de correo que sea.

### AdArgument
Contendra la fecha de creación y el contenido de la tabla y ahi se guardara todas las tablas creadas cuando se cierre el programa y al abrir devuelta el programa aparecera las tablas que ya se habian creado y agregaremos todos los contenidos con el JTextField en en el contenido de las anotaciones.

### AdUser
Contedra todo lo que pide en la pestaña de cliente lo cual sera el nombre, el correo electronico, observaciones y numero telefonico para que aparezca en la tabla el usuario egresado y se guardara la lista de los usuarios egresados al cerrar el programa para que cuando se vuelve a abrir se mantengan en la pantalla principal de cliente todos los usuarios agregados utilizando el JTextField para agregar todo el contenido en nombre, correo electronico, observaciones y el numero telefonico.


## Diseño Orientado a Objetos

<img src="Program UML.png">
<img src="Validator UML.png">
<img src="DataModel UML.png">




