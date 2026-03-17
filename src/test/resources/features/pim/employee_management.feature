#language:en
#Author: Alex Vanegas

@pim
Feature: Gestión de empleados en el módulo PIM
  Como administrador de RRHH
  Quiero gestionar los empleados en el módulo PIM
  Para mantener actualizada la información del personal

  Background:
    Given el usuario Admin está autenticado en OrangeHRM

  @smoke @employee-list
  Scenario: visualización de la lista de empleados
    When el usuario navega al módulo PIM
    Then el sistema muestra la lista de empleados

  @regression @add-employee
  Scenario: agregar un nuevo empleado
    When el usuario navega al módulo PIM
    And el usuario agrega un empleado con los siguientes datos
      | firstName | lastName |
      | Juan      | Prueba   |
    Then el sistema muestra los detalles del empleado recién creado

  @regression @search-employee
  Scenario: búsqueda de un empleado existente por nombre
    When el usuario navega al módulo PIM
    And el usuario busca el empleado "Admin"
    Then el sistema muestra al menos un resultado con "Admin"
