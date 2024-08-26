

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
```
