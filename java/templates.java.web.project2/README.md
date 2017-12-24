# Java Web template - Project 2

- Java 9;
- REST (Jersey 2.26)
- Tomcat 9;
- Maven;
- Eclipse IDE.

## Uso

- Antes de importar o projeto na IDE Eclipse, edite o arquivo **pom.xml** e altere as *tags* **groupId**, **artifactId**, **version** e **name** com os dados do seu projeto. Altere também no *plugin* **maven-war-plugin** a *tag* **warName** com a URL de sua preferência que será usada para acessar o sistema quando o Eclipse IDE fizer o *deploy* da aplicação no Tomcat;

- URL para teste após *deploy* da aplicação:
  http://localhost:8080/URL_adicionada_no_plugin_maven-war-plugin/Index.html

- Após a importação e testes as *class* e *packages* que estão na pasta **src/main/java** e os arquivos *Index.html*, *Index.js* e *Index.css* da pasta **src/main/webapp** podem ser excluídos, pois são utilizados somente para demostração dos recursos REST.

- É necessário editar no arquivo **web.xml** a *tag* **param-value** que deve ser informado qual *package* da aplicação encontra-se as classes de recursos REST da sua aplicação. 



