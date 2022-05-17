Dado('que el usuario ha abierto la aplicación y ha indicado que quiere cambiar a modo oscuro') do
  app_running
end

Cuando('el usuario haga click sobre el switch de modo oscuro') do
  tap_when_element_exists("android.widget.Switch index:#switchNocturno")
end

Entonces('la aplicación cambia a modo oscuro') do
  #Se comprueba el valor de la variable de java del modo nocturno. En caso de ser 2, estaríamos en el modo nocturno.
  #En caso de ser 1, estáriamos en el modo claro.
  #No hemos conseguido comprobar el valor de una variable de Java.
end
