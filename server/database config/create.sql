/**
	StudAPP

	Script de Criação de Tabelas


	Legenda de Comentários:
	- [PK]	- Chave Primária
	- [Un]	- Chave Secundária [Atributo único]
	- [Dv] 	- Atributo Derivado
	- [FK]	- Chave Estrangeira

	Outras informações
	- Identação usando caractere TAB = 4 espaços
	- Todos os atributos derivados serão calculados por comandos SELECT após todas as inserções iniciais
*/

/** ========================================
 *		Drop em Tabelas e Seqüências
 * =========================================
 */

-- Drop em todas as tabelas, eliminando também suas restrições
DROP TABLE usuario				CASCADE CONSTRAINTS;

-- Drop em todas as sequencias



/** ========================================
 *					Tabelas
 * =========================================
 */

/** ==================== Tabela Usuário ====================
	
	Define um usuário do sistema

	Atributos:
	* nome 						-- 
	* email 					-- [PK] E-mail do usuário
	* senha 					-- 
	* rank		 				-- 
	Constraints:
	* usuario_pk 				-- 
 */
CREATE TABLE usuario
(
	-- Atributos
	nome		VARCHAR(32),
	email		VARCHAR(32),
	senha		VARCHAR(32),
	rank		REAL,
	tipo		CHAR(1) 		DEFAULT 'A',
	-- Constraints
	CONSTRAINT usuario_pk
		PRIMARY KEY (email),
	CONSTRAINT usuario_tipo 
		CHECK ( UPPER(tipo) IN ( 'A', 'P' ) )
);

/** ==================== Tabela Aluno ====================
	Atributos:
	* nome 						-- 
	Constraints:
	* aluno_pk	 				-- 
 */
CREATE TABLE aluno
(
	-- Atributos
	email		VARCHAR(32),
	creditos	REAL 			DEFAULT 0,
	-- Constraints
	CONSTRAINT aluno_pk
		PRIMARY KEY (email),
	CONSTRAINT aluno_fk_usuario
		FOREIGN KEY (email)
		REFERENCES usuario (email)
		ON DELETE CASCADE
);

/** ==================== Tabela Professor ====================
	Atributos:
	* nome 						-- 
	Constraints:
	* aluno_pk	 				-- 
 */
CREATE TABLE professor
(
	-- Atributos
	email		VARCHAR(32),
	-- Constraints
	CONSTRAINT professor_pk
		PRIMARY KEY (email),
	CONSTRAINT professor_fk_usuario
		FOREIGN KEY (email)
		REFERENCES usuario (email)
		ON DELETE CASCADE
);

/** ==================== Tabela Estuda ====================
	Atributos:
	* nome 						-- 
	* lingua 					-- 
	Constraints:
	* estuda_pk 				-- 
 */
CREATE TABLE estuda
{
	-- Atributos
	email		VARCHAR(32),
	lingua		VARCHAR(16),
	-- Constraints
	CONSTRAINT estuda_pk
		PRIMARY KEY (nome, lingua),
	CONSTRAINT estuda_fk_usuario
		FOREIGN KEY (email)
		REFERENCES usuario (email)
		ON DELETE CASCADE
}

/** ==================== Tabela Mensagem ====================
	Atributos:
	* nome 						-- 
	Constraints:
	* usuario_pk 				-- 
 */
CREATE TABLE mensagem
(
	-- Atributos
	mail_origem		VARCHAR(32),
	mail_destino	VARCHAR(32),
	conteudo		TEXT,
	data			TIMESTAMP,
	-- Constraints
	CONSTRAINT mensagem_pk
		PRIMARY KEY (mail_origem, mail_destino),
	CONSTRAINT mensagem_fk_usuario_origem
		FOREIGN KEY (mail_origem)
		REFERENCES usuario (email)
		ON DELETE CASCADE,
	CONSTRAINT mensagem_fk_usuario_destino
		FOREIGN KEY (mail_destino)
		REFERENCES usuario (email)
		ON DELETE CASCADE
);

/** ==================== Tabela LigaçãoPaga ====================
	Atributos:
	* nome 						-- 
	Constraints:
	* usuario_pk 				-- 
 */
CREATE TABLE ligacao_paga
(
	-- Atributos
	mail_aluno		VARCHAR(32),
	mail_professor	VARCHAR(32),
	inicio			TIMESTAMP,
	fim				TIMESTAMP,
	custo			REAL,
	-- Constraints
	CONSTRAINT ligacao_paga_pk
		PRIMARY KEY (mail_aluno, mail_professor, inicio),
	CONSTRAINT ligacao_paga_fk_aluno
		FOREIGN KEY (mail_aluno)
		REFERENCES aluno (email)
		ON DELETE CASCADE,
	CONSTRAINT ligacao_paga_fk_professor
		FOREIGN KEY (mail_professor)
		REFERENCES professor (email)
		ON DELETE CASCADE
);

/** ==================== Tabela Usuário ====================
	Atributos:
	* nome 						-- 
	Constraints:
	* usuario_pk 				-- 
 */
CREATE TABLE recarga
(
	-- Atributos
	email			VARCHAR(32),
	data			TIMESTAMP,
	recarga			REAL,
	-- Constraints
	CONSTRAINT recarga_pk
		PRIMARY KEY (email, data),
	CONSTRAINT recarga_fk_aluno
		FOREIGN KEY (email)
		REFERENCES aluno (email)
		ON DELETE CASCADE
);