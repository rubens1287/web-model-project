# PROJETO MODELO PARA TESTE API

Projeto desenvolvido com proposito de ser um modelo base para teste de API

## PRÉ-REQUISITOS

Requisitos de software e hardware necessários para executar este projeto de automação

*   Java 1.8 SDK
*   Maven 3.5.*
*   Intellij IDE
*   Plugins do Intellij
    * Cumcuber for java
    * Lombok

## ESTRUTURA DO PROJETO

| Diretório                    	| finalidade       	                                                                                        | 
|------------------------------	|---------------------------------------------------------------------------------------------------------- |
| src\main\java\br\com\core 	| Metodos genéricos que apoiam as classes de testes      	                                                |
| src\main\java\br\com\data    	| Metodos do tipo models, para trabalhar com objetos de dados                                               |
| src\main\java\br\com\request 	| Metodos do tipo models, que representa objetos a serem serealizado em json para as requisições de apis   	|
| src\main\java\br\com\respose 	| Metodos do tipo models, que representa objetos que recebem a deserializaçao do json de reposta das apis  	|
| src\main\java\br\com\services	| Local onde deve ser criado os objetos que executam requisições e validações das respotas               	|
| src\test\java\hooks          	| Metodos que executam antes e depois de cada teste (@Before, @After)                                   	|
| src\test\java\runner         	| Metodo prinicipal que inicia os testes via cucumber                                                      	|
| src\test\java\steps         	| Local onde deve ser criado as classes que representam os steps definition do cucumber                    	|
| src\test\resources\data      	| Massa de dados segregada por ambiente, escritos em arquivos yaml                                      	|
| src\test\resources\features 	| Funcionalidade e cenarios de teste escritos em linguagem DSL (Gherkin language)                        	| 
| src\test\resources\schema 	| Local para armazenamento dos arquivos de schema do json utilizados para validação de contrato           	| 
    
## CLONE O PROJETO PARA SUA MÁQUINA LOCAL

Abra o git bash, entre no diretório escolhido na sua máquina e faça o download do projeto com o comando abaixo.

```
git clone https://git.gft.com/latam-qa-practice/automation-assets/api-model-project.git
```

## FRAMEWORKS UTILIZADOS

Abaixo está a lista de frameworks utilizados nesse projeto

* Jackson para leitura de dados de arquivo yaml file
* Gson para serializacao e deserializacao de objetos
* Allure report em HTML
* Java Faker Geracao de dados sinteticos
* Rest Assured para teste de API (Json, Soap, Xml)
* Cucumber especificacao executavel de cenarios
* Assert especializados com mais tipos de validacao
* Lombok - Otimizacao de classes modelos

## COMANDO PARA EXECUTAR OS TESTES

Com o git bash acesse a pasta do projeto, onde esta localizado o arquivo pom.xml, execute o comando abaixo para rodar os testes automatizados.

```
mvn clean test
```

## COMANDO PARA GERAR EVIDÊNCIAS EM HTML (ALLURE)

Com o git bash acesse a pasta do projeto, onde esta localizado o arquivo pom.xml, execute o comando abaixo para rodar os testes automatizados.

```
mvn allure:report
```

## MULTIPLOS COMANDOS 

Você também pode mesclar a linha de comando maven com options do cucumber, 
sendo assim você pode escolher uma determinada tag que se deseja executar do cucumber, 
podendo escolher também a massa de dados que irá utilizar e juntamente aplicar o linha de comando para gerar o report HTML.

```
mvn clean test -Dcucumber.options="--tags @dev" -Denv=des allure:report
```

## EVIDÊNCIAS

Os arquivos com as evidências ficam localizados na pasta target do projeto, esta pasta só é criada depois da primeira execução.

```
 Report HTML: target\site\index.html
 Json Cucumber: target\json-cucumber-reports\cucumber.json
 Xml Junit: tagert\xml-junit\junit.xml
```
