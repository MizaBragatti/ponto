#Author: Mizael
Feature: Recupera as horas feitas no Ahgora e registra no Clarizen

  @apontamento
  Scenario Outline: Efetua login no site Ahgora e recupera horas feitas
    Given Eu acesse a pagina de login e preencha os campos de <matricula> e <senhaahgora>
    When Eu clicar no botao para entrar
    Then Eu valido o <nomeahgora> e verifico se o login foi efetuado com sucesso
    And Clicar em espelho do ponto
    And Copiar horas do dia anterior
    Given Eu acesse a pagina do clarizen e preencha os campos de <email> e <senhaclarizen>
    When Eu clicar no botao de login
    Then Eu valido o <nomeclarizen> e verifico se login foi efetuado com sucesso no clarizen
    And Verificar qual o dia da semana
    And Preencher as horas trabalhadas

    Examples: 
      | email                          | senhaclarizen  | nomeahgora        | matricula | senhaahgora | nomeclarizen                    |
      | "mizael.bragatti@yaman.com.br" | "011#andrezab" | "Mizael Bragatti" | "000282"  | "3487"      | "Mizael Bragatti do Nascimento" |
