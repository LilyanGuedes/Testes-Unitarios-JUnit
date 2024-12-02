# **Testes Unitários com JUnit para Cadastro e Login de Usuários**

alunos: Lílyan Gabryella Guedes 01565435  / Júlio César Amorim 01024947

## **Descrição do Projeto**
Este projeto implementa um sistema simples para cadastro e login de usuários, com validações para senhas, emails e tratamento de exceções. Inclui testes unitários desenvolvidos com JUnit para validar as funcionalidades principais, garantindo a confiabilidade e qualidade do sistema.

## **Funcionalidades**
- **Cadastro de Usuários**:
  - Permite registrar usuários com email único, nome e senha válida.
  - Rejeita emails duplicados.
  - Valida senhas com pelo menos 8 caracteres.
  
- **Login de Usuários**:
  - Permite login com email e senha corretos.
  - Rejeita login com email não cadastrado ou senha inválida.

## **Tecnologias Utilizadas**
- **Linguagem**: Java
- **Frameworks**:
  - Spring Boot (para controle e persistência)
  - JUnit 5 (para testes unitários)
  - Mockito (para mocks em testes)
- **Ferramentas de Build**:
  - Maven

## **Configuração do Projeto**

### **Pré-requisitos**
- **Java 11 ou superior**
- **Maven**
- IDE como IntelliJ IDEA, Eclipse ou VS Code.

