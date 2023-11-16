# Workout Management System

## Descrição

O Workout Management System é uma aplicação para gerenciar treinos físicos, permitindo que personal trainers criem planos de exercícios personalizados para seus clientes. A aplicação é construída utilizando o Spring Boot, Kotlin e R2DBC para comunicação reativa com o banco de dados PostgreSQL no Supabase.

## Pré-requisitos

Antes de começar, certifique-se de ter os seguintes pré-requisitos instalados e configurados:

- [Java JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Gradle](https://gradle.org/)
- [PostgreSQL no Supabase](https://supabase.io/docs/guides/database)

## Configuração do Banco de Dados

1. Crie um banco de dados PostgreSQL no Supabase.
2. Obtenha as credenciais de conexão, incluindo a URL, nome do banco de dados, nome de usuário e senha.
3. Atualize o arquivo `application.yml` com as informações do banco de dados.

## Configuração do Projeto

1. Clone este repositório.
2. Navegue até o diretório do projeto.
3. Execute os seguintes comandos:

   ```bash
   # Para projetos Gradle
   ./gradlew build

   # Ou, para projetos Maven
   ./mvnw clean install

