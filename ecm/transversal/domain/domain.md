# Módulo Domain

[:gb:](domain_en.md)

El módulo Domain es un módulo transversal a la aplicación que permite unificar los diferentes dominios de información que se pueden utilizar y así unificar los textos comunes tales pueden ser las etiquetas que aparecen en pantalla, tipos de datos(Sexo, Idiomas, etc), y así conseguimos que todas las partes del programa utilicen los mismos.

En este módulo podremos realizar todas las tareas de mantenimiento de los dominios(domains), sus valores(values) y sus respectivas traducciones(text) para poder internacionalizar la aplicación

El módulo se va a dividir en tres partes:
- [Domain](domain/domain.md): Submódulo encargado de mantener los diferentes dominios de información.
- DomainValue: Esta segunda división es la responsable de gestionar los valores que puede tener cada uno de los dominios.
- DomainValueText: Por último tenemos el módulo que gestiona las diferentes traducciones que puede tener un valor de dominio, de momento la aplicación estará disponible en Castellano, Catalán e Ingles.
