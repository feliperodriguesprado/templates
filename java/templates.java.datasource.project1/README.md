# DataSource template for Java - Project 1

- Java 9;
- c3p0 0.9.5.2;
- JUnit 4.12;
- Maven;
- Eclipse IDE;
- PostgreSQL 9.6.


## Uso

- Antes de importar o projeto na IDE Eclipse, edite o arquivo **pom.xml** e altere as *tags* **groupId**, **artifactId**, **version** e **name** com os dados do seu projeto;

- Importe o projeto na IDE Eclipse através da opção: **File > Import... > Maven > Existing Maven Projects**;

- Após a importação faça o *refactor* dos *packages* que estão nas pastas abaixo nomeando-os de acordo com a sua preferência:

  **src/main/java**
  </br>**src/test/java**

- Dentro da pasta **src/main/resources/database** existe o arquivo **database.properties** onde deve ser informado as propriedades de conexão e preferências do banco de dados utilizado. O banco de dados utilizado é o PostgreSQL 9.6, para utilizar outro banco de dados é necessário adicionar o JDBC *driver* referente no arquivo **pom.xml**.

- Dentro da pasta **src/main/resources/database** existe também o arquivo **ddl.sql** que cria um *schema* chamado **templates** e uma tabela **people** com alguns dados para testar o funcionamento da aplicação.

- Defina o caminho do arquivo de propriedades do banco de dados:

```
DataSource.getInstance().setFileDatabaseProperties("caminho_do_arquivo_de_propriedades_do_banco_de_dados");
```

- Para obter uma conexão com o banco de dados faça:

```
Connection connection = DataSource.getInstance().getConnection();
```
- Após realizar uma transação de INSERT, UPDATE, DELETE ou realizar alguma QUERY faça:

```
DataSource.getInstance().commit(connection);
```

- Para desfazer uma transação de INSERT, UPDATE, DELETE faça:

```
DataSource.getInstance().rollback(connection);
```

- Para encerrar o DataSource faça:

```
DataSource.getInstance().closeDataSource();
```
