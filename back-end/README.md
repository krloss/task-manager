# task-manager-api

## Sobre :
* Disponibiliza serviço Restful através do JAX-RS.
* H2 como banco de dados.
* JPA/Hibernate para persistência dos dados.

## Requerimentos :
* Usar Java 8+
* Maven 3.2.5

## Uso :
> java -jar task-manager-0.1.0-1.jar
> [Link Local - http://localhost:8080/](http://localhost:8080/)

## Desenvolvimento :
``` bash
# Apagar Cache
mvn clean

# Testar Aplicação
mvn test

# Contruir Aplicação
mvn packge

# Execuar Aplicação
mvn spring-boot:run
```

## Considerações:
* Organização do Projeto
``` bash
.
└── src
    ├── main
    │   ├── java
    │   │   └── org
    │   │       └── test
    │   │           └── taskmanager
    │   │               ├── model
    │   │               ├── repository
    │   │               └── resource
    │   └── resources
    └── test
        └── java
            └── org
                └── test
                    └── taskmanager
```
* Algumas adaptações foram criadas como:
    1. Páginação para as requisições GET sem parâmetros.
    2. Crição da assinatura GET plan/{id}/tasks
* Em uma nova versão substituir o JAX-RS pelo Spring Data REST.
