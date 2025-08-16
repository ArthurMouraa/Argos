-- Desativar verificação de chaves estrangeiras temporariamente
SET REFERENTIAL_INTEGRITY FALSE;

-- Criação das tabelas principais primeiro
CREATE TABLE turma (id BIGINT AUTO_INCREMENT PRIMARY KEY, nome VARCHAR(255), data_criacao DATE);
CREATE TABLE usuario (id VARCHAR(255) PRIMARY KEY, nome VARCHAR(255) NOT NULL, email VARCHAR(255) NOT NULL UNIQUE, senha VARCHAR(255) NOT NULL, matricula VARCHAR(255) NOT NULL UNIQUE, foto VARCHAR(255), role VARCHAR(255));
CREATE TABLE aluno (id BIGINT AUTO_INCREMENT PRIMARY KEY, nome VARCHAR(255) NOT NULL, matricula VARCHAR(255), tdh VARCHAR(100), tea VARCHAR(100), foto VARCHAR(255), status VARCHAR(255), sexo VARCHAR(255), turma_id BIGINT);
CREATE TABLE ocorrencia (id BIGINT AUTO_INCREMENT PRIMARY KEY, observacao TEXT, data_ocorrencia DATE, hora_ocorrencia TIME, gravidade VARCHAR(255), aluno_id BIGINT, usuario_id VARCHAR(255));

-- Criação das tabelas de junção
CREATE TABLE aluno_usuario (aluno_id BIGINT, usuario_id VARCHAR(255), PRIMARY KEY (aluno_id, usuario_id));
CREATE TABLE turma_usuario (turma_id BIGINT, usuario_id VARCHAR(255), PRIMARY KEY (turma_id, usuario_id));

-- Adição das chaves estrangeiras
ALTER TABLE aluno ADD CONSTRAINT FK_ALUNO_TURMA FOREIGN KEY (turma_id) REFERENCES turma(id);
ALTER TABLE ocorrencia ADD CONSTRAINT FK_OCORRENCIA_ALUNO FOREIGN KEY (aluno_id) REFERENCES aluno(id);
ALTER TABLE ocorrencia ADD CONSTRAINT FK_OCORRENCIA_USUARIO FOREIGN KEY (usuario_id) REFERENCES usuario(id);
ALTER TABLE aluno_usuario ADD CONSTRAINT FK_ALUNO_USUARIO_ALUNO FOREIGN KEY (aluno_id) REFERENCES aluno(id);
ALTER TABLE aluno_usuario ADD CONSTRAINT FK_ALUNO_USUARIO_USUARIO FOREIGN KEY (usuario_id) REFERENCES usuario(id);
ALTER TABLE turma_usuario ADD CONSTRAINT FK_TURMA_USUARIO_TURMA FOREIGN KEY (turma_id) REFERENCES turma(id);
ALTER TABLE turma_usuario ADD CONSTRAINT FK_TURMA_USUARIO_USUARIO FOREIGN KEY (usuario_id) REFERENCES usuario(id);

-- Reativar verificação
SET REFERENTIAL_INTEGRITY TRUE;