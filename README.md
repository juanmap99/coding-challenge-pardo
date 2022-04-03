# coding-challenge-pardo

## Ejercicio 1
Los métodos requeridos en el ejercicio uno pueden ser ejecutados a traves de la clase ubicada en **src/test/java/com/codingchallenge/challenge/EjercicioUno.java**.
Allí se encuentra presente una secuencia de 20 test unitarios que analizan los diferentes casos.

## Ejercicio 2
La documentación de la API Rest puede ser encontrada en **api-doc.json**.

La aplicación se encuentra hosteada en **https://coding-challenge-pardo.herokuapp.com** y a través de una solicitud **POST** sobre la ruta **/tasatarjeta**
otorgando un archivo **json** que contenga la **marca** y el **importe** se puede analizar el funcionamiento del programa.


Se agregó al diseño un manejo de excepciones custom, por lo que de no enviar los parametros de la manera esperada la API responderá un mensaje de error
detallando los campos incompletos en el json que faltan agregar. 

Por otra parte, ante datos erroneos(Marca invalida o importe mayor igual a 1000 o menor a 0)
la API se percatará de ello y respondera un mensaje de error que represente el error cometido.

## Consideraciones de diseño
En el enunciado del problema había ciertas ambiguedades que decidí afrontar tomando arbitrariamente una decisión de diseño en base a lo que
creía mas óptimo al problema, ya que debido a que el desarrollo fue realizado durante el fin de semana no pude despejar ciertas dudas.
El código se encuentra documentado y estas decisiones fueron plasmadas en la documentación propia a los diversos metodos, pero en pos de resumir:

* **Cardholder**: Dicho campo contiene tanto el nombre como el apellido. No se crearon campos en especifico separados para el nombre y el apellido.
* **Número de tarjeta** : Se opto por un string por temas de optimización de espacio y tener un modelo que se adapte ante números de tarjeta que puedan superar los limites establecidos por un long.
* **Validez del numero de tarjeta**: El enunciado no especificada los criterios que determinan que tarjetas son válidas y cuales no. Debido a que ese es el caso,
una tarjeta sera válida siempre y cuando la longitud del String sea mayor a uno y contenga solamente digitos.
* **Igualdad de tarjetas**: El enunciado requería la existencia de un método que determine si dos tarjetas son iguales pero no especifica los criterios que
determinan dicha igualdad. Debido a que ese es el caso, se determinó que dos tarjetas son iguales siempre y cuando el número de tarjeta y la marca sean iguales.
* **Información de tarjeta**: El enunciado requeria un metodo que retornase la información de una tarjeta pero no especificaba el formato buscado. Debido a
que ese es el caso se opto por un String que represente un .json.
* **Operación válida**: El enunciado menciona que las operaciones son válidas siempre y tanto el monto sea menor a 1000, sin embargo no menciona si ese monto
es anterior o posterior al aplicamiento de la tasa de tarjeta. Debido a que ese es el caso, se decidio optar que esa restricción aplique previo al agregado de tasa de tarjeta.
* **Tasas de tarjeta**: El enunciado menciona que la tasa de servicio de las tarjetas debe ser mayor a 0.3% y menor a 5%, sin embargo por la propia naturaleza
de la operación que hay que realizar para obtener las tasas de las tarjetas existirán situaciones en las que el resultado se escapará del rango
establecido por el enunciado. Debido a que ese es el caso se decidió respetar el rango establecido de manera tal que si el cálculo de la tasa de la tarjeta supera
el 5% la misma se verá reducida al 5%, mientras que si el resultado cae por debajo del 0.3% la misma se vera incrementada hacia 0.3%.


