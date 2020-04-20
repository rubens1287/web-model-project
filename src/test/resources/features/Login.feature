# language: pt
# charset: UTF-8

Funcionalidade: Login
   Eu como cliente gostaria de acessar o sistema via login somente com credenciais validas

  @dev
   Cenario: CT001 - Consulta - Consultar dados de um usuário
    Dado eu estou na pagina de login
    Quando eu efetuar o login com credencias validas
    Entao sera apresentado a tela do menu principal