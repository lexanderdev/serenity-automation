#language:en
#Autor: Alex Vanegas

@stories
Feature: Test Orange
    As a user, I want to run automation tests on the platform.
   @scenario1
   Scenario: Usuario inicia sesión y navega al menú 'My Info' para consultar detalles de contacto
       Given El usuario 'Admin' está autenticado en la plataforma Orange
         | strUser       | strPassword   |
         | Admin          | admin123 |
       When El usuario selecciona la opción 'My Info' en el menú principal
       Then El usuario visualiza la sección 'Contact Details' en los datos personales
        | strSearch  |
        | Contact Details |
