# PROJETO MODELO PARA TESTE WEB 

Projeto desenvolvido com proposito de ser um modelo base para teste para interface web

## PRÉ-REQUISITOS

Requisitos de software e hardware necessários para executar este projeto de automação

*   Java 1.8 SDK
*   Maven 3.5.*
*   Navegador Web (Chrome, Opera, FireFox, Edge ou Safari)
*   Intellij IDE
*   Plugins do Intellij
    * Cumcuber for java
    * Lombok

## ESTRUTURA DO PROJETO

| Diretório                    	| finalidade       	                                                                                        | 
|------------------------------	|---------------------------------------------------------------------------------------------------------- |
| src\main\java\config 			| Interface com as propriedades dos arquivos de ambiente 'Properties'                                       |
| src\main\java\data    		| Reponsável por ler arquivos yaml file e retonar objeto HashMap com os valores dos campos                  |
| src\main\java\dates 			| Metodos de suporte para trabalhar com datas                                                              	|
| src\main\java\driver 			| Responsável por fabricar os drivers para rodar local e remoto para varios navegadores                    	|
| src\main\java\pages			| Local onde deve ser criado as pages objects para facilitar a manutenção do projeto                       	|
| src\main\java\report			| Metodo responsável pela criação de screenshot anexada no Report Alure                                		|
| src\main\java\support			| Metodos de suporte a interação com os elementos web fazendo ações de click e esperas explicitas          	|
| src\main\resources\conf	    | Arquivos de configuração segregados por ambiente                                                        	|
| src\test\java\hooks          	| Metodos que executam antes e depois de cada teste (@Before, @After)                                   	|
| src\test\java\runner         	| Metodo prinicipal que inicia os testes via cucumber                                                      	|
| src\test\java\steps         	| Local onde deve ser criado as classes que representam os steps definition do cucumber                    	|
| src\test\resources\data      	| Massa de dados segregada por ambiente, escritos em arquivos yaml                                      	|
| src\test\resources\features 	| Funcionalidade e cenários de teste escritos em linguagem DSL (Gherkin language)                        	|   
    
## CLONE O PROJETO PARA SUA MÁQUINA LOCAL

Abra o git bash, entre no diretório escolhido na sua máquina e faça o download do projeto com o comando abaixo.

```
git clone https://git.gft.com/latam-qa-practice/automation-assets/web-model-project.git
```

## FRAMEWORKS UTILIZADOS

Abaixo está a lista de frameworks utilizados nesse projeto

* Jackson - Responsável pela leitura de dados de arquivo yaml file
* Selenium - Responsável pela interação com páginas web
* Allure - report em HTML
* Java Faker - Geracão de dados sintéticos
* Cucumber - Responsável pela especificação executável de cenários
* AssertJ - Especializado com mais tipos de validação
* Lombok - Otimização de classes modelos
* Log4j2 - Responsável pelo Log do projeto
* AeonBits - Responsável por gerenciar as properties

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

## LOG DE EXECUÇÃO

Os logs de execução gerados pelo Log4j2 ficam alocados na pasta log na raiz do projeto.
