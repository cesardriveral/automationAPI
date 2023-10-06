# language: es
@Feature01 @Features
Característica: Crear registro

  @TEST01-01
  Escenario: Crear un registro
    Dado Cesar que quiere crear un registro
    Cuando envia los datos necesarios para crear un registro con "Chicho" "Bruce" "100" "2023-10-10" "2023-10-16" y "vacaciones"
    Entonces Se crea un registro correctamente