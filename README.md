# banheiro-unissex
Projeto de banheiro unissex utilizando concorrência em java


## Sobre

Este projeto simula a utiização de um banheiro unissex por meio de concorrência. O objetivo é que pessoas de diferentes sexos, representadas por Threads, utilizem o banheiro
respeitando a condição que apenas pessoas do mesmo sexo estejam utilizando o banheiro ao mesmo tempo. Alem disso, cada pessoa pode passar um tempo diferente no banheiro e este tem vagas limitadas

## Estrutura 

```
+─banheiro-unissex                                     
   ├──doc
   └──src
      └──main  
          └──java
            └──com
                └──mycompany 
                   └─── banheiro
                      └─── unissex        
                        └─── Banheiro.java
                        └─── BanheiroUnissex.java
                        └─── FilaBanheiro.java
                        └─── Pessoa.java
   └── README.md
   └── Relatório - banheiro unissex.pdf
   └── pom.xml
   └── .gitignore
   

```
## Executar 

Para executar o programa, faça o clone do projeto e importe a aplicação como um projeto Java Maven em sua IDE. 
Faça o build e execute o arquivo BanheiroUnissex.java onde se encontra o método main. 
