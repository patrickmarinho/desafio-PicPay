## Desafio Backend PicPay
Este projeto é uma solução desenvolvida em Java para um desafio backend proposto pelo [PicPay](https://picpay.com/). Você pode conferir o desafio original proposto [aqui](https://github.com/PicPay/picpay-desafio-backend).

## Índice
- [Visão Geral](#visão-geral)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Instalação](#instalação-via-repositório)
- [Execução](#execução)
- [Endpoints da API](#endpoints-da-api)

## Visão Geral
Este projeto consiste em uma API RESTful desenvolvida em Java com Spring Boot. A API é capaz de criar usuários e efetuar transações, seguindo as melhores práticas de desenvolvimento e design de software.

## Tecnologias Utilizadas
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Lombok](https://projectlombok.org/)
- [Maven](https://maven.apache.org/)
- [Flyway](https://flywaydb.org/)
- [PostgreSQL](https://www.postgresql.org/)
- [Spring Security](https://spring.io/projects/spring-security)

## Instalação via Repositório
1. Clone o repositório:

```bash
git clone https://github.com/patrickmarinho/desafio-PicPay.git
```

2. Instale as dependências com o Maven

3. Instale o PostgreSQL e crie o banco de dados: picpay_api

## Execução
1. Inicie a aplicação com o Maven

2. A API vai estar acessível em http://localhost:8080

## Endpoints da API

#### Criar Usuário:

```http

  POST /User
  
```
Recebe os parâmetros no seguinte formato JSON:

```json
{
	"firstName": "John",
	"lastName": "Doe",
	"cpfCnpj": "00000000000",
	"email": "johndoe@domain.com",
	"password": "12345",
	"currentBalance": 100,
	"userType": "COMMON"
}
``

```
| Parâmetro | Tipo            | Descrição                                                              |
|-----------|-----------------|------------------------------------------------------------------------|
|`firstName`         |`String`          |**Obrigatório**. O seu primeiro nome.
|`lastName`          |`String`          |**Obrigatório**. O seu último nome.
|`cpfCnpj`           |`String`          |**Obrigatório**. O seu CPF ou CNPJ.
|`email`             |`String`          |**Obrigatório**. O seu email.
|`password`          |`String`          |**Obrigatório**. A sua senha.
|`currentBalance`    |`BigDecimal`      |**Obrigatório**. O valor monetário da sua conta. 
|`userType`          |`UserType `       |**Obrigatório**. O seu tipo de usuário. Podendo ser `COMMON`(Comum) ou `SHOPKEEPER`(Lojista).

Cria um usuário.

#### Requisitos:
- Usuários não podem registrar contas com saldo negativo.
- Usuário não podem registrar contas com CPF/CPNJ e email já usados anteriormente.



#### Efetuar uma Transação:



```http

POST /Transaction

```
Recebe os parâmetros no seguinte formato JSON:

```json
{
	"amount": 100,
	"payerID": 15,
	"payeeID": 10
}
``

```
| Parâmetro | Tipo            | Descrição                                                              |
|-----------|-----------------|------------------------------------------------------------------------|
|`amount`         |`BigDecimal `    |**Obrigatório**. O valor a ser transferido.
|`payerID`        |`Long `          |**Obrigatório**. O ID do pagador.
|`payeeID`        |`Long `          |**Obrigatório**. O ID do recebedor.

Efetua uma transferência.

#### Requisitos:
- Usuário que efetua a transferência `(payer)` não pode ser do tipo `SHOPKEEPER`(Lojista).
- Usuário que efetua a transferência `(payer)` precisa ter saldo em sua conta.
- O valor da transação deve ser maior que zero.