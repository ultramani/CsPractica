# language: es
Característica: comprobar formato DNI
    Para comprobar que la información introducida es correcta
    Como un desarrollador
    Quiero comprobar que el formato del DNI es válido con 8 cifras y una letra

Escenario: Comprobar un DNI
    Dado que el usuario ha introducido "07563256A" en el campo DNI
    Cuando el usuario haga click en el boton de crear
    Entonces la salida deberia ser "true"

Escenario: Comprobar un DNI
    Dado que el usuario ha introducido "256A" en el campo DNI
    Cuando el usuario haga click en el boton de crear
    Entonces la salida deberia ser "false"