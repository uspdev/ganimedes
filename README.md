# Sistema Ganimedes

## Descrição

O Sistema Ganimedes foi idealizado pela Seção de Estágios do IME-USP para o
acompanhamento dos estágios realizados pelos seus alunos.





## Índice



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

<h4>Instalação do Gradle</h4>


[Baixe](https://gradle.org/releases/) o Gradle e descompacte em <code>/opt/gradle:</code>

```bash  
$ mkdir /opt/gradle
$ unzip -d /opt/gradle gradle-5.3-bin.zip
```

Edite o arquivo <code>/etc/enviroment</code> e inclua o caminho do executável do Gradle na variável PATH

```bash          
PATH="/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/games:/usr/local/games<strong>:/opt/gradle/gradle-5.3/bin</strong>"
```



## Uso



## Contribuindo com o projetor



## Créditos



## Licenciamento
