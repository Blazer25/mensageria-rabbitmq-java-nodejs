# Projeto: Java Spring Boot com RabbitMQ, Node.js com TypeScript e React

Este é um projeto que utiliza uma arquitetura baseada em microserviços, utilizando as tecnologias Java Spring Boot com RabbitMQ e NodeJS + TypeScript para a parte do backend e React + TypeScript para o frontend. 

## Visão Geral

O projeto é dividido em quatro partes principais:

1. **Backend Produtor (Java Spring Boot e RabbitMQ):**
   - Desenvolvido em Java com o framework Spring Boot.
   - Utiliza o RabbitMQ para comunicação assíncrona entre os serviços (Produz e envia as mensagens).
   - Gerencia a lógica de negócios, persistência de dados e integrações com outros sistemas.

2. **Backend Receptor (Java Spring Boot e RabbitMQ):**
   - Desenvolvido em Java com o framework Spring Boot.
   - Utiliza o RabbitMQ para comunicação assíncrona entre os serviços (Recebe as mensagens).

2. **Backend Receptor (NodeJS com TypeScript):**
   - Desenvolvido em NodeJS com TypeScript.
   - Utiliza o RabbitMQ para comunicação assíncrona entre os serviços (Recebe as mensagens).

3. **Frontend (React com TypeScript):**
   - Utiliza o framework React para a construção da interface de usuário.
   - Comunica-se com o backend em NodeJS por meio de APIs RESTful.

## Requisitos

Antes de iniciar o projeto, certifique-se de ter as seguintes ferramentas instaladas:

- Docker (utilizado para suportar o RabbitMQ localmente)
- JDK (Java Development Kit) 8 ou superior
- Node.js (Versão 20.0.0 ou superior) e npm (Node Package Manager)

## Configuração

### Baixando o projeto
1. Clone ou baixe o projeto do seguinte repositório do GitHub: https://github.com/Blazer25/mensageria-rabbitmq-java-nodejs

## Execução

### Geral

1. Abra o projeto em sua pasta raiz, e execute o seguinte comando do Docker: `docker-compose up -d --build`

### Backend - Produtor [JAVA]

1. Abra o projeto, vá até o repositório: `java-producer-estoque-preco`
2. Execute o projeto Java pela IDE IntelliJ IDEA ou usando o comando `mvn spring-boot:run`
3. Acesse a documentação da API para ter acesso aos endpoints que enviam dados dos produtos para esta aplicação (https://github.com/Blazer25/mensageria-rabbitmq-java-nodejs/blob/master/documentacao/TUTORIAL.md).

### Backend - Receptor [JAVA]

1. Abra o projeto, vá até o repositório: `java-consumer-cliente-estoque`
2. Execute o projeto Java pela IDE IntelliJ IDEA ou usando o comando `mvn spring-boot:run`

### Backend - Receptor [NodeJS]

1. Abra o projeto, vá até o repositório: `nodejs-consumer-cliente-preco`
2. Abra o terminal na pasta do repositório `nodejs-consumer-cliente-preco` e de o seguinte comando: `npm install`
3. Aguarde as dependências do projeto serem baixadas
4. Abra o terminal e de o seguinte comando: `npm run build`
5. Aguarda os arquivos JS serem buildados
6. Execute o seguinte comando no terminal para iniciar a aplicação: `node .\server.js`

### Fronted - Visualizador [React]

1. Abra o projeto, vá até o repositório: `react-frontend-send-visualize-products`
2. Abra o terminal na pasta do repositório `react-frontend-send-visualize-products` e de o seguinte comando: `npm install`
3. Aguarde as dependências do projeto serem baixadas
4. Abra o terminal e de o seguinte comando: `npm run dev`
5. Aguarda o Vite (React) iniciar a aplicação frontend
6. Acesse a rota da aplicação para ter acesso ao frontend (http://localhost:5173/ se a porta 5173 estiver disponível no momento...)