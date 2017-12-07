# Java DataSource

- Java 9;
- c3p0;
- JUnit;
- Maven;
- Eclipse IDE;
- PostgreSQL 9.6.


## Uso

- Antes de importar o projeto na IDE Eclipse, edite o arquivo **pom.xml** e altere as *tags* **groupId**, **artifactId**, **version** e **name** com os dados do seu projeto;

- Importe o projeto na IDE Eclipse através da opção: **File > Import... > Maven > Existing Maven Projects**;

- Após a importação faça o *refactor* dos *packages* que estão nas pastas abaixo com os dados do seu projeto:
	**src/main/java**
	**src/test/java**

- Dentro da pasta **src/main/resources/database** existe o arquivo **database.properties** onde deve ser informado as propriedades de conexão e preferências do banco de dados utilizado. O banco de dados utilizado é o PostgreSQL 9.6, para utilizar outro banco de dados é necessário adicionar o JDBC *driver* referente no arquivo **pom.xml**.

- Dentro da pasta **src/main/resources/database** existe também o arquivo **ddl.sql** que cria um *schema* chamado **templates** e uma tabela **people** com alguns dados para testar o funcionamento da aplicação.

- Para obter uma conexão com o banco de dados faça:

```sh
DataSource.getInstance().setFileDatabaseProperties("caminho_do_arquivo_de_propriedades_do_banco_de_dados");
Connection connection = DataSource.getInstance().getConnection();
```
- Após fazer um INSERT, UPDATE, DELETE ou realizar alguma QUERY, faça:

```sh
DataSource.getInstance().commit(connection);
```

- Para desfazer uma transação faça:

```sh
DataSource.getInstance().rollback(connection);
```

- Para encerrar o DataSource faça:

```sh
DataSource.getInstance().closeDataSource();
```