# ARGOS

**ARGOS** é um **Sistema de Acompanhamento, Registro, Gestão de Ocorrências e Sinalizações Acadêmicas**.

---

## 📌 Descrição do Projeto

Aplicação web voltada ao registro e acompanhamento de ocorrências acadêmicas, permitindo a vinculação de alunos a turmas, geração de relatórios, controle de acesso por perfil, entre outras funcionalidades administrativas.

### 🔧 Tecnologias Utilizadas
- **Backend**: Java com Spring Boot
- **Banco de Dados**: MySQL

---

## 🚀 Instruções para Iniciar o Projeto

### 💻 Ambiente de Desenvolvimento

- IDE de sua preferência  
  > 💡 *Sugestão: IntelliJ IDEA*

#### ⚠️ Requisitos ao usar IntelliJ:
Como o projeto utiliza **Lombok**, certifique-se de realizar as seguintes configurações:

##### ✅ Habilitar o plugin Lombok
1. Vá em `File > Settings` (ou `Preferences` no macOS)
2. Acesse a seção `Plugins`
3. Pesquise por **Lombok** e instale
4. Reinicie o IntelliJ

##### ✅ Habilitar Annotation Processing
1. Vá em `File > Settings` → `Build, Execution, Deployment` → `Compiler` → `Annotation Processors`
2. Marque:
   - `✔ Enable annotation processing`
   - `✔ Obtain processors from project classpath`
3. Clique em **Apply** e reinicie o IntelliJ

---

- ###Backend###:
    - Tecnologia: Java com Spring Boot.
    - Passos:
        1. Navegar até a pasta main.
        2. Compilar e iniciar o servidor:
            ```bash
            ./mvnw spring-boot:run
            ```
    - 🔐 Observação: O backend realiza autenticação baseada em token JWT, implementada utilizando Spring Security.

## Funcionalidades Implementadas

- **Backend**:
   ## ✅ Funcionalidades Implementadas
  
- **Gerenciamento de Turmas**:
  - Listar, criar, atualizar e excluir turmas.

- **Gerenciamento de Alunos**:
  - Vincular aluno a uma turma, listar, criar, atualizar e excluir alunos.
 
- **Gerenciamento de Ocorrencias**:
  - Listar todas ocorrências de um aluno, criar, listar todas ocorrências, atualizar, excluir.
 
- **Autenticação e Autorização**:
  - Login utilizando JWT.
  - Validação de tokens e controle de acesso por perfil.
  - 🔸 *Obs.: Existem professores que também são coordenadores — esses possuem permissões adicionais.*

- **Documentação da API**:
  - Integrada via Swagger UI com especificações no formato OpenAPI.

- **Segurança**:
  - Implementada com Spring Security.
  - Autenticação baseada em JWT.
  - Configuração de CORS para permitir acesso entre diferentes origens (frontend/backend).

- **Integração com Banco de Dados**:
  - Persistência de dados com JPA/Hibernate.
  - Utilização do banco de dados MySQL.

- **Tratamento de Erros e Validações**:
  - Respostas personalizadas para exceções.
  - Validação de dados de entrada com mensagens claras para o usuário.
