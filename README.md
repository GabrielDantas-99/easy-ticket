# **Easy Ticket**

## **Descrição**

**Easy Ticket** é um projeto voltado para o gerenciamento de eventos, desenvolvido como parte de uma aplicação prática dos conhecimentos em Spring Boot, Angular 17, PostgreSQL e AWS. O sistema permite o cadastro, listagem, filtragem e detalhamento de eventos, além da associação de cupons de desconto para os participantes.

## **Funcionalidades**

- **Cadastro de Eventos:** Criação de eventos com detalhes como nome, data, local, e descrição.
- **Listagem de Eventos:** Exibição de uma lista de eventos com opções de filtragem e ordenação.
- **Detalhamento de Eventos:** Visualização de informações detalhadas sobre um evento específico.
- **Associação de Cupons de Desconto:** Integração de cupons de desconto para eventos, permitindo a aplicação de descontos na inscrição.

## **Tecnologias Utilizadas**

- **Back-End:**
  - **Spring Boot:** Framework Java para desenvolvimento de aplicações robustas e escaláveis.
  - **PostgreSQL:** Banco de dados relacional para armazenamento das informações dos eventos e cupons.

- **Front-End:**
  - **Angular 17:** Framework JavaScript para construção de interfaces dinâmicas e responsivas.

- **Infraestrutura:**
  - **AWS (Amazon Web Services):** Utilizado para deploy e gerenciamento da infraestrutura do projeto.

## **Modelagem do Sistema**

### **Diagrama de Entidades (ERD)**

![Modelagem do Sistema](system_model.png)

## **Infraestrutura**

### **Arquitetura de Infraestrutura**

![Arquitetura da Infraestrutura](infrastructure_architecture.png)

## **Instalação e Configuração**

### **Pré-requisitos**

- **Java 21 LTS:** Certifique-se de que o Java 21 está instalado.
- **Node.js e npm:** Necessários para rodar o Angular.
- **PostgreSQL:** Banco de dados para armazenar as informações dos eventos.
- **AWS CLI:** Para configurar e gerenciar a infraestrutura na AWS.

### **Passos para Configuração**

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/easy-tickets.git
   cd easy-tickets

2. **Configuração do Back-End**

    - Navegue até o diretório `backend` e configure as variáveis de ambiente para conexão com o banco de dados PostgreSQL.

    - Execute o comando Maven para compilar e iniciar o servidor:

      ```bash
      mvn spring-boot:run
3. **Configuração do Front-End**

    - Navegue até o diretório frontend e instale as dependências:
      ```bash
      mvn spring-boot:run

    - Execute o servidor de desenvolvimento Angular:

      ```bash
      ng serve
## Uso
Após a configuração, o sistema estará disponível em http://localhost:4200 (ou no domínio configurado na AWS). Navegue pela aplicação para gerenciar eventos, adicionar cupons e visualizar os detalhes dos eventos.

## Contribuições
Contribuições são bem-vindas! Sinta-se à vontade para abrir um PR ou criar uma issue para discutir melhorias.

## Licença
Este projeto está licenciado sob os termos da licença MIT. Consulte o arquivo LICENSE para mais detalhes.
