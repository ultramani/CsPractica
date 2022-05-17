# language: es
Característica: cambiar a modo oscuro
    Para ver mejor la pantalla
    Como un usuario
    Quiero cambiar la paleta de colores a oscuros

Escenario: Activar el modo oscuro
    Dado que el usuario ha abierto las opciones e indicado que quiere cambiar a modo oscuro
    Cuando el usuario haga click sobre el swtich de modo oscuro
    Entonces la aplicación cambia a modo oscuro

Escenario: Desactivar el modo oscuro
    Dado que el usuario ha abierto las opciones e indicado que quiere cambiar a modo claro
    Cuando el usuario haga click sobre el switch de modo oscuro
    Entonces la aplicación cambia a modo claro