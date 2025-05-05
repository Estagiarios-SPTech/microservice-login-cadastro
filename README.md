# microservice-login-cadastro
Este projeto é um microserviço para login e cadastro dos colaboradores do sistema de controle de ordens de fornecimento.


## Estrutura do projeto

```
microservice-login-cadastro/
├── src/
├── target/
├── .dockerignore
├── .gitignore
├── mvnw
├── pom.xml
├── README.md
└── scriptBanco

```

## Descrição
Este microserviço oferece endpoints REST para **autenticação** (login) e **cadastro** de usuários, com controle de permissões por tipo de usuário (role). Utiliza o framework Quarkus para realizar isso.

---

## Pré-requisitos

- **Java 17+**
- **Maven**
- **Quarkus CLI**
- **MySQL**

---

## Configuração do Banco de Dados

1. **Crie o banco de dados no MySQL**.
2. **Execute o script de criação:**  
   No MySQL Workbench, execute o arquivo `scriptBanco` para criar as tabelas e estruturas necessárias.

---

## Como Rodar

1. Clone o repositório:
   ```bash
   git clone https://github.com/seuusuario/microservice-login-cadastro.git
   cd microservice-login-cadastro

2. Instale as dependências e rode o serviço em modo desenvolvedor
   
```shell script
./mvnw quarkus:dev
```
3. Certifique-se de que o banco de dados está configurado corretamente em:
   
`src/main/resources/application.properties`

## Acessar aplicação pela porta 8080

http://localhost:8080

## Roles

Há quatro categorias de role 
- Admin 
- RT
- Manager
- Colaborador

Apenas Admin e RT podem cadastrar outros colaboradores após realizar login e coletar o token obtido em resposta. 
O cadastro pode ser realizado pela url
`localhost:8080/users/cadastrar`


