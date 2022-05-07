Dado('que el usuario ha introducido {string} en el campo DNI') do |entrada|
  @entrada = entrada
end

Cuando('el usuario haga click en el boton de crear') do
  @salida = (@entrada.length == 9 && (@entrada.match?(/^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$/i) ||
    @entrada.match?(/^[XYZ][0-9]{7}[TRWAGMYFPDXBNJZSQVHLCKE]$/i)))
end

Entonces('la salida deberia ser {string}') do |salida_esperada|
  @salida == salida_esperada
end