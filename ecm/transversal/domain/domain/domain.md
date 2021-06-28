# Domain

[:gb:](domain_en.md)

Como se ha mencionado anteriormente este submódulo es el encargado de gestionar los dominios de información(domains), para permitir un correcto mantenimiento de estos.

- [Domain](#domain)
  - [Propiedades de un dominio](#propiedades-de-un-dominio)
  - [Funcionalidades](#funcionalidades)
    - [Creación de dominios](#creación-de-dominios)
    - [Modificación de un dominio](#modificación-de-un-dominio)
    - [Habilitar/Deshabilitar un dominio](#habilitardeshabilitar-un-dominio)
    - [Obtener todos los dominios](#obtener-todos-los-dominios)
    - [Obtener un dominio](#obtener-un-dominio)
    - [Eliminar un dominio](#eliminar-un-dominio)
  - [Excepciones](#excepciones)
  - [Seguridad](#seguridad)

## Propiedades de un dominio
La información que tiene que almacenar un dominio es la siguiente:
  - domain_id: Identificador del dominio, este valores es único, numérico y autoincremental.
  - code_domain: Esta propiedad identifica al dominio mediante un nombre que tiene que ser único, la longitud del mismo sera de entre 3 y 10 caracteres alfanuméricos, el campo sera obligatorio y una vez creado el dominio no se le podrá modificar.
  - description: Campo que sirve para añadir una pequeña descripción de la información que contiene el dominio, de momento este campo solo permite la descripción en un único idioma.
  - enable: Sirve para indicar si podemos utilizar o no el dominio, contendrá un valor booleano, donde el valor de activo sera 1 y el de desactivado será 0, por defecto el valor será el de activado.

## Funcionalidades
Para poder gestionar correctamente los dominios este módulo realizara las siguientes tares:

### Creación de dominios
Crea nuevos dominios en el sistema, las características de la funcionalidad son las siguientes:
  - Entrada: 
    - Para poder crear un dominio se tendrá que aportar un código de dominio y una descripción opcional.
  - Salida: 
    - Una vez se ha validado la información aportada y todo es correcto devuelve la información del nuevo dominio.
  - Validaciones:
    - El código de dominio tiene que ser único y no debe existir ningún otro dominio con el mismo código, si la validación falla entonces se ejecutara la excepción EXISTS_CODE_DOMAIN.
  - Códigos de retorno:
    - Cuando se crea el dominio, el código de retorno sera el 201.
    - Si la validación no ha sido correcta se mostrara un error 409 y se mostrara el mensaje de la excepción EXIST_CODE_DOMAIN

### Modificación de un dominio
En esta funcionalidad únicamente podremos modificar la descripción de dominio, ya que el código de dominio no se puede modificar, las características de la funcionalidad son las siguientes:
  - Entrada: 
    - Esta función necessita dos parametros:
      1. Identificador y/o código del dominio.
      2. Nueva descripción para el dominio.
  - Validaciones: 
    - Se comprobara que el dominio existe en el sistema, sino existe entonces se ejecuta la excepción DOMAIN_NOT_FOUND.
  - Salida: 
    - Una vez modificado el dominio se devolvera la información de este.
  - Códigos de retorno:
    - Si todo ha ido bien se retornara un código 200.
    - Si no existe el dominio que se quiere modificar se lanzara un error 404 y con el mensaje de la excepción DOMAIN_NOT_FOUND.

### Habilitar/Deshabilitar un dominio
Habilita o deshabilita un dominio dependiendo de su estado actual, las características de la funcionalidad son las siguientes:
  - Entrada: 
    - Se proporcionara el código y/o el identificador de dominio.
  - Validaciones: 
    - Se comprobara que el dominio existe en el sistema.
  - Procedimiento: 
    - Cuando se cambia el estado de un dominio hay que realizar los siguientes pasos
      1. Modificar el estado de los valores del dominio.
      2. Modificar el estado del dominio.
  - Salida: 
    - Se devolverá el dominio con la información modificada.
  - Códigos de retorno:
    - Cuando se hayan modificado el dominio y sus valores se devolvera el código 200
    - Si no existe el dominio que se quiere modificar se lanzara un error 404 y con el mensaje de la excepción DOMAIN_NOT_FOUND.

### Obtener todos los dominios
Se devuelven todos los dominios que hay en el sistema, de esta funcionalidad se podrá utilizar de varias formas:
  1. Todos los dominios: Se devolverán todos los dominios indistintamente que estén habilitados o no.
     - Entrada: 
       - No existe requerimientos de entrada.
     - Salida: 
       - Si existen dominios devuelve una lista con ellos.
       - Si no existen dominios se devuelve una lista vacia.
     - Códigos de salida:
       - Si se devuelve una lista con los dominios, el código de retorno 200.
       - Si se devuelve una lista vacia, el códifo de retorno es 200 y con el mensaje de la excepción DOMAINS_EMPTY.
  2. Todos los dominios paginados: Devuelve una lista páginada con todos los dominios del sistema estén habilitados o no.
     - Entrada: 
       - Cantidad de dominios que se mostraran por página, por defecto los elementos en cada página serán 10.
       - Número de página a la que se quiere acceder, por defecto se mostrara la primera página.
     - Salida: 
       - Si existen dominios con la cantidad necesaria de elementos por página y página requerida, se devuelve la lista página con los dominios.
       - Si existen dominios pero no hay suficientes para la página requerida, se devuelve la lista con la última página con elementos encontrados.
       - Si existen dominios pero no hay suficientes para el número de elementos requeridos por página, entonces devolvemos la lista con los dominios que existan.
       - Si no existen dominio se devuelve una lista vacia.
     - Códigos de salida:
       - Si encuentran dominios en el sistema se devuelve un código 200.
       - Si no se encuentran dominios en el sistema devuelve un error 404 y con el mensaje de la excepción DOMAINS_EMPTY.
  3. Dominios activos: Devuelve una lista de todos los dominios que están activos en el sistema.
     - Entrada: 
       - No se requieren elementos de entrada.
     - Salida: 
       - Lista con los dominios activos del sistema.
     - Códigos de salida:
         - Si encuentran dominios en el sistema se devuelve un código 200.
         - Si no se encuentran dominios en el sistema devuelve un error 404 y con el mensaje de la excepción NOT_EXISTS_ACTIVE_DOMAINS.
  4. Dominios activos paginados: Devuelve una lista página con todos los dominios del sistema que estén activos.
     - Entrada: 
       - Cantidad de dominios que se mostraran por página y número de página a la que se quiere acceder.
     - Salida: 
       - Lista con los dominios correspondientes por página y página requerida.
     - Códigos de salida:
       - Si encuentran dominios en el sistema se devuelve un código 200.
       - Si no se encuentran dominios en el sistema devuelve un error 404 y con el mensaje de la excepción NOT_EXISTS_ACTIVE_DOMAINS.

### Obtener un dominio
Devuelve la información de un dominio en concreto, las características de la funcionalidad son las siguientes:
  - Entrada: 
    - Identificador y/o código de dominio.
  - Salida: 
    - Si encuentra el dominio, devuelve la información de este.
    - Si no encuentra el dominio, ejecuta la excepción DOMAIN_NOT_FOUND.
  - Códigos de salida:
    - Si encuentra el dominio devuelve un código 200
    - Si se ejecuta la excepción DOMAIN_NOT_FOUND, devuelve un error 404 y se muestra el error de la excepción.

### Eliminar un dominio
Eliminar un dominio del sistema, las características de la funcionalidad son las siguientes:
  - Entrada: 
    - Para buscar un dominio lo podremos hacer mediante su identificador y/o código de dominio.
  - Validaciones: 
    - El dominio se podrá eliminar si se cumplen las siguientes situaciones:
      1. El dominio existe, si no se encuentra el valor se ejecuta la excepción DOMAIN_NOT_FOUND.
      2. El dominio no tiene valores asociados
      3. Si tiene valores estos no pueden estar en uso, si hay algún valor en uso se ejecuta la excepción VALUES_DOMAIN_IN_USE_ERROR.
  - Procedimiento: 
    - Una vez las validaciones han sido correctas el procedimiento para eliminar un dominio es el siguiente:
      1. Eliminar las traducciones de los valores.
      2. Eliminar los valores del dominio.
      3. Eliminar el dominio.
  - Salida: 
    - No se devolverá ninguna información.
  - Códigos de salida:
    - Si se puede eliminar se devolverá un código 204
    - Si no existe el dominio devuelve un error 404 y mostrará el mensaje de la excepción DOMAIN_NOT_FOUND.
    - Si algúno de los valores del dominio esta en uso, se devolverá el código 409 y se mostrará el mensaje de la excepción VALUES_DOMAIN_IN_USE_ERROR.

## Excepciones
- DOMAINS_EMPTY: 
  - No existen dominios.
- DOMAIN_NOT_FOUND: 
    - No se encuentra el dominio con el código %CODE_DOMAIN%
    - No se encuentra el dominio con el identificador %DOMAIN_ID%
    - No se encuentra el dominio con el identificador %DOMAIN_ID% y el código %CODE_DOMAIN%
- EXISTS_CODE_DOMAIN: 
  - Ya existe un dominio cuyo código es %CODE_DOMAIN%
- NOT_EXISTS_ACTIVE_DOMAINS: 
  - No hay dominios activos.
- VALUES_DOMAIN_IN_USE_ERROR: 
  - El dominio con el código %CODE_DOMAIN% contiene valores que están en uso.

## Seguridad
Para poder utilizar este módulo los usuarios tendrán que estar autorizados en la aplicación y tener el rol de domain_admin.