# Sistema Ganimedes

O Sistema Ganimedes foi idealizado pela Seção de Estágios do IME-USP para o
acompanhamento dos estágios realizados pelos alunos do Instituto.


## Instalação

Este guia de instalação foi elaborado para o sistema operacional Ubuntu. No entanto, com os devidos ajustes esse guia pode ser
adaptado para outros sistemas Linux, Windows ou MacOS.




### Instalação do Java JDK

Adicione o PPA da Oracle, atualize seu repositório de pacotes e instale:

```bash
$ sudo add-apt-repository ppa:webupd8team/java
$ sudo apt-get update
$ sudo apt-get install oracle-java8-installer
```




### Instalação do Gradle

[Baixe](https://gradle.org/releases/)</a> o Gradle e descompacte em <code>/opt/gradle</code>:

```bash  
$ mkdir /opt/gradle
$ unzip -d /opt/gradle gradle-5.3-bin.zip
```

Edite o arquivo <code>/etc/enviroment</code> e inclua o caminho do executável do Gradle na variável PATH

```bash          
PATH="/opt/gradle/gradle-5.3/bin"
```


### Configuração e criação do banco de dados no Mysql

```
create database ganimedes;
```

Execute o script a seguir para a criação das tabelas no banco de dados: [src/main/resources/META-INF/sql/create.sql](src/main/resources/META-INF/sql/create.sql)



### Instalação e configuração do Wildfly

[Baixe](https://wildfly.org/downloads) o Wildfly e descompacte em <code>/opt/wildfly</code>:


```bash          
$ sudo mkdir /opt/wildfly
$ sudo unzip -d /opt/wildfly wildfly-16.0.0.Final.zip
```

Inicie o Wildfly
```bash          
$ sudo /opt/wildfly/bin/standalone.sh
```




Crie um usuário para acessar a interface web de administração do Wildfly.

```bash          
$ /opt/wildfly/bin/add-user.sh -u 'admin' -p 'admin.1234' -g 'admin'
```

Para visualizar a página inicial e a interface web de administração do Wildfly acesse <code>http://localhost:8080</code>. O Wildfly
também possui uma interface de linha de comando, o Wildfly <strong>CLI</strong>.



#### Configuração de driver JDBC e datasource no Wildfly


O Sistema Ganimedes utiliza duas conexões com servidores de bancos de dados, sendo uma com o banco replicado (somente leitura),
executado em um servidor Microsoft SQL Server e outra com um banco de dados MySQL para armazenamento dos dados do sistema.



<strong>Configuração para o Microsoft SQL Server</strong>

[Baixe](http://ida.fel.cvut.cz/maven/com/microsoft/sqlserver/sqljdbc42/6.0.8112/sqljdbc42-6.0.8112.jar) o driver JDBC do Microsoft SQL Server.


Através do CLI conecte-se à instância do Wildfly em execução:

```bash
$ /opt/wildfly/bin/jboss-cli.sh --connect
```


Adicione o módulo do driver no WildFly, indicando a localização do arquivo jar que foi baixado:

```bash
module add --name=system.layers.base.com.microsoft --resources=/home/usuario/Downloads/sqljdbc42.jar --dependencies=javax.api,javax.transaction.api,javax.xml.bind.api                
```

Execute também o comando abaixo para concluir a inclusão do módulo:

```bash
/subsystem=datasources/jdbc-driver=sqlserver:add(driver-name=sqlserver,driver-module-name=system.layers.base.com.microsoft)
```


Crie o datasource indicando as credenciais de acesso ao banco de dados:

```bash
data-source add --jndi-name=java:/jdbc/replicado --name=replicado --connection-url=jdbc:sqlserver://[SERVIDOR]:1433;DatabaseName=[NOME DO BANCO] --driver-name=sqlserver --user-name=[USUARIO] --password=[SENHA]
```


Encerre o CLI

```
exit
```



<strong>Configuração para o MySQL</strong>


[Baixe](http://central.maven.org/maven2/mysql/mysql-connector-java/8.0.15/mysql-connector-java-8.0.15.jar) o driver do MySQL.

```bash
$ /opt/wildfly/bin/jboss-cli.sh --connect
```

Adicione o módulo do driver no WildFly, indicando a localização do arquivo jar que foi baixado:

```bash
module add --name=system.layers.base.com.mysql --resources=[PATH_PARA_O_JAR] --dependencies=javax.api,javax.transaction.api
```

Execute também o comando abaixo para concluir a inclusão do módulo:

```bash
/subsystem=datasources/jdbc-driver=mysql:add(driver-name=mysql,driver-module-name=system.layers.base.com.mysql)
```


Crie o datasource indicando as credenciais de acesso ao banco de dados:

```bash
data-source add --jndi-name=java:/jdbc/ganimedes --name=ganimedes --connection-url=jdbc:mysql://[SERVIDOR]:3306/[NOME DO BANCO] --driver-name=mysql --user-name=[USUARIO] --password=[SENHA]
```

Encerre o CLI

```
exit
```


Acesse a interface web de administração do Wildfly para verificar se os drivers estão corretamente instalados e para
e testar se as conexões dos datasources com os bancos de dados estão funcionando.

As configurações de bancos de dados estão em: <code>Administration Console -> Configuration -> Subsystems -> DataSources & Drivers</code>


### Compilação

Clone o projeto:

```bash
$ git clone https://github.com/uspdev/ganimedes.git
```

Acesse o diretório criado:

```bash
$ cd ganimedes
```

Compile e crie o arquivo WAR com o Gradle.

```bash
$ ./gradlew build
```


Se o procedimento foi realizado com sucesso, espera-se que o Gradle tenha criado o arquivo <code>buld/libs/ganimedes.war</code>




### Implantação


Para implantar a aplicação no Wildfly, execute o comando abaixo indicando o caminho do WAR:

```bash
$ /opt/wildfly/bin/jboss-cli.sh --connect --command="deploy --force [CAMINHO DO WAR]"
```

Se tudo deu certo, o sistema pode ser acessado através de <code>http://localhost:8080/ganimedes</code>

Na tela de login há um link com o título <em>Cadastrar administrador</em> que fica disponível até o momento
da criação do primeiro administrador no sistema.





## Uso

Nessa seção serão apresentadas as funcionalidades do sistema.
