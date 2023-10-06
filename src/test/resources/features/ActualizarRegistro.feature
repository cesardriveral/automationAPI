# language: es
@Feature04 @Features
Característica: Actualizar registro

  @TEST01-04
  Escenario: Actualizar un registro usando todos los datos
    Dado Cesar que quiere crear un registro
    Cuando se requiere actualizar un registro con "CARLOS", "SPRING", "90", "2023-10-20", "2023-10-26" y "TRABAJO"
    Entonces actualiza correctamente

  @TEST02-04
  Escenario: Actualizar un registro parcialmente
    Dado Cesar que quiere crear un registro
    Cuando envia algunos datos para actualizar como "MARIO" y "VENEGAS"
    Entonces actualiza correctamente