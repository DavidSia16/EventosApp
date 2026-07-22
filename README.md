# Sistema de Gerenciamento de Eventos (EventosApp)

Um sistema web simples desenvolvido em Spring Boot para cadastro de eventos e gerenciamento de listas de convidados. Projeto criado com o objetivo de praticar conceitos de desenvolvimento web, persistência de dados e injeção de dependências.

## 🚀 Funcionalidades

* **Cadastro de Eventos:** Registro de nome, local, data e horário do evento.
* **Listagem de Eventos:** Página inicial para visualização de todos os eventos criados.
* **Detalhes do Evento:** Página dedicada para ver as informações de um evento específico.
* **Lista de Convidados:** Cadastro e vinculação de convidados (Nome e RG) diretamente na página do evento correspondente.

## 🛠️ Tecnologias Utilizadas

* **Backend:** Java 17+ / Spring Boot 4.x (Spring Data JPA, Spring Web / MVC)
* **Frontend:** Thymeleaf, HTML5, CSS3 (Materialize CSS)
* **Banco de Dados:** MySQL
* **Gerenciador de Dependências:** Maven

## 📦 Como rodar o projeto localmente

### Pré-requisitos
* Java 17 ou superior instalado.
* MySQL Server rodando localmente.

### Passos para execução
1. Clone o repositório para a sua máquina:
   ```bash
   git clone https://github.com
   ```

2. Crie um banco de dados no seu MySQL chamado `eventosapp`:
   ```sql
   CREATE DATABASE eventosapp;
   ```

3. Abra o arquivo `src/main/resources/application.properties` e configure as credenciais do seu banco de dados local:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/eventosapp
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   ```

4. Execute a aplicação através da sua IDE ou pelo terminal usando o Maven Wrapper:
   ```bash
   ./mvnw spring-boot:run
   ```

5. Acesse no seu navegador: `http://localhost:8080`

## 📁 Estrutura do Código

* `controller/`: Classes responsáveis pelas rotas (`EventoController`, `IndexController`).
* `model/`: Entidades de banco de dados (`Evento`, `Convidado`).
* `repository/`: Interfaces que estendem o JpaRepository para persistência de dados.
* `templates/`: Páginas HTML estruturadas com Thymeleaf.
