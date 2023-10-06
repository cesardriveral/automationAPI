# language: es
@Feature03 @Features
Característica: Consultar registro

  @TEST01-03
  Escenario: Consultar un registro por id
    Dado Cesar que quiere crear un registro
    Cuando se consulta un registro usando un id
    Entonces Se visualiza el registro

  @TEST02-03
  Escenario: Consultar un registro por nombre
    Dado Cesar que quiere crear un registro
    Cuando se consulta un registro usando nombres
    Entonces Se visualiza el registro

  @TEST03-03
  Escenario: Consultar un registro por fechas
    Dado Cesar que quiere crear un registro
    Cuando se consulta un registro usando fechas
    Entonces Se visualiza el registro

  @TEST04-03
  Escenario: Consultar todos los registros
    Dado Cesar que quiere crear un registro
    Cuando se consulta todos los registros
    Entonces Se visualiza el registro