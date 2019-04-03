# Sistema Ganimedes

O Sistema Ganimedes foi idealizado pela Seção de Estágios do IME-USP para o
acompanhamento dos estágios realizados pelos seus alunos.


## Instalação

Este guia de instalação foi escrito para sistema operacional Ubuntu.

### Instalação do Java JDK

Adicione o PPA da Oracle, atualize seu repositório de pacotes e instale:

```bash
$ sudo add-apt-repository ppa:webupd8team/java
$ sudo apt-get update
$ sudo apt-get install oracle-java8-installer
```



### Instalação do gradle

<a href='https://gradle.org/releases/' >[Baixe]</a> o Gradle e descompacte em <code>/opt/gradle</code>:

```bash  
$ mkdir /opt/gradle
$ unzip -d /opt/gradle gradle-5.3-bin.zip
```

Edite o arquivo <code>/etc/enviroment</code> e inclua o caminho do executável do Gradle na variável PATH

```bash          
PATH="/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/games:/usr/local/games<strong>:/opt/gradle/gradle-5.3/bin</strong>"
```

### Instalação do Wildfly

[Baixe](https://wildfly.org/downloads) o Wildfly e descompacte em <code>/opt/wildfly</code>:


```bash          
$ mkdir /opt/wildfly
$ unzip -d /opt/wildfly wildfly-16.0.0.Final.zip
```

Inicie o Wildfly
```bash          
$ sudo /opt/wildfly/bin/standalone.sh
```

Acesse <code>http://localhost:8080</code> para visualizar a página inicial do Wildfly.



#### Configuração do Wildfly

Crie um usuário para administração do Wildfly. Escolha o usuário e a senha de sua preferência.

```bash          
$ /opt/wildfly/bin/add-user.sh -u 'admin' -p 'admin.1234' -g 'admin'
```

Acesse <code>http://localhost:9990</code> para visualizar o painel de controle do Wildfly.



#### Configuração de driver JDBC e datasource no Wildfly


O Sistema Ganimedes utiliza duas conexões com servidores de bancos de dados, sendo uma com o banco replicado (somente leitura),
executado em um servidor Microsoft SQL Server e com um banco de dados MySQL para armazenamento dos dados produzidos localmente na unidade.

A configuração de conexões com bancos de dados no Wildfly pode ser feita pelo painel de controle e pelo CLI (Command Line Interface),
que é o método apresentado aqui.


<strong>Configuração do Microsoft SQL Server</strong>

Baixe o driver do <a href='http://ida.fel.cvut.cz/maven/com/microsoft/sqlserver/sqljdbc42/6.0.8112/sqljdbc42-6.0.8112.jar'>Microsoft SQL Server</a>.


Inicie o CLI e conecte-o a instância do Wildfly em execução:

```bash
$ /opt/wildfly/bin/jboss-cli.sh --connect
```


Uma vez conectado, adicionamos o module no WildFly apontando para o arquivo jar que foi baixado:

```bash
module add --name=system.layers.base.com.microsoft --resources=/home/usuario/Downloads/sqljdbc42.jar --dependencies=javax.api,javax.transaction.api,javax.xml.bind.api                </pre>
```



Para finalizar, instalamos o driver no Wildfly:

```bash
/subsystem=datasources/jdbc-driver=sqlserver:add(driver-name=sqlserver,driver-module-name=system.layers.base.com.microsoft)
```


Com o módulo criado, então criamos o datasource indicando a configuração do servidor MySQL onde está instalado o banco de dados:

```bash
data-source add --jndi-name=java:/jdbc/replicado --name=replicado --connection-url=jdbc:sqlserver://fqdn-do-servidor:1433;DatabaseName=replicado --driver-name=sqlserver --user-name=replicado --password=password
```


Encerre o CLI

```bash
$ exit
```



<strong>Configuração do MySQL</strong>


Baixe os drivers do <a href='http://central.maven.org/maven2/mysql/mysql-connector-java/8.0.15/mysql-connector-java-8.0.15.jar'>MySQL</a>

```bash
$ /opt/wildfly/bin/jboss-cli.sh --connect
```

Uma vez conectado, adicionamos o module no WildFly apontando para o arquivo jar que foi baixado:

```bash
module add --name=system.layers.base.com.mysql --resources=[PATH_PARA_O_JAR] --dependencies=javax.api,javax.transaction.api
```

Para finalizar, instalamos o driver no Wildfly:

```bash
/subsystem=datasources/jdbc-driver=mysql:add(driver-name=mysql,driver-module-name=system.layers.base.com.mysql)
```


Com o módulo criado, então criamos o datasource indicando a configuração do servidor MySQL onde está instalado o banco de dados:

```bash
data-source add --jndi-name=java:/jdbc/ganimedes --name=ganimedes --connection-url=jdbc:mysql://fqdn-do-servidor:3306/ganimedes --driver-name=mysql --user-name=ganimedes --password=password
```

Encerre o CLI

```bash
$ exit
```






## Uso
