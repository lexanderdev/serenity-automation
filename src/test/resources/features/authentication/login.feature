#language:en
#Author: Alexander  Vanegas

@authentication
Feature: Autenticación en OrangeHRM
  Como usuario del sistema
  Quiero iniciar sesión en OrangeHRM
  Para acceder a las funcionalidades de la plataforma

  @smoke @login-exitoso
  Scenario: inicio de sesión exitoso con credenciales válidas
    Given el usuario intenta iniciar sesión con usuario "Admin" y contraseña "admin123"
    Then el usuario visualiza el Dashboard de OrangeHRM

  @regression @login-fallido
  Scenario: inicio de sesión fallido con credenciales inválidas
    Given el usuario intenta iniciar sesión con credenciales inválidas desde Firebase
    Then el sistema muestra el mensaje de error correspondiente
