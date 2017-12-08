# Java Web template

- Java 9;
- JSF com Primefaces;
- CDI;
- Bean Validation;
- i18n para as páginas e Bean Validation;
- Tomcat 9;
- Maven;
- Eclipse IDE.

## Uso

- Antes de importar o projeto na IDE Eclipse, edite o arquivo **pom.xml** e altere as *tags* **groupId**, **artifactId**, **version** e **name** com os dados do seu projeto. Altere também no *plugin* **maven-war-plugin** a *tag* **warName** com a URL de sua preferência que será usada para acessar o sistema quando o Eclipse ID fizer o *deploy* da aplicação no Tomcat;

- Após a importação faça o *refactor* dos *packages* que estão na pasta **src/mani/java** nomeando-os de acordo com a sua preferência;

- URL para teste após importar projeto na IDE:
  http://localhost:8080/URL_adicionada_no_plugin_maven-war-plugin/account/SignIn.xhtml
  
- Os arquivos de internacionalização (i18n) utilizados nas páginas e na aplicação estão armazenados na pasta **src/main/resources/i18n/**. Esses arquivos são configurados no arquivo **src/main/webapp/WEB-INF/faces-config.xml**.

- Os arquivos de internacionalização (i18n) utilizados pelo *Bean Validation* estão armazenados na pasta **src/main/resources/org/hibernate/validator/**. Esses arquivos são lidos automaticamente pelo *Bean Validation* não necessitando de configuração para utilizá-los.


