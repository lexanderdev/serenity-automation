#language:en
#Autor: Alex Vanegas

@stories
Feature: Test Orange
    As a user, I want to run automation tests on the platform.
   @scenario1
   Scenario: Navegar por el menu del sistema
       Given then Alex needs to log in to Orange
         | strUser       | strPassword   |
         | Admin          | admin123 |
       When Seleccionar my info desde el menu
        Then Selecionar datos personales
        | strSearch  |
        | Contact Details |
