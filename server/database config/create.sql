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
DROP TABLE usuario			CASCADE;
DROP TABLE avalia			CASCADE;
DROP TABLE aluno			CASCADE;
DROP TABLE professor		CASCADE;
DROP TABLE estuda			CASCADE;
DROP TABLE mensagem			CASCADE;
DROP TABLE ligacao_paga		CASCADE;
DROP TABLE recarga			CASCADE;


/** ========================================
 *					Tabelas
 * =========================================
 */

/** ==================== Tabela Usuário ====================
	
	Define um usuário do sistema

	Atributos:
	* nome 						-- 		Nome do usuário
	* email 					-- [PK] E-mail do usuário
	* senha 					-- 		Senha do usuário
	* rank		 				-- 		Rank calculado do usuário
	* rank_sum		 			-- 		Soma de todas as notas ao usuario
	* rank_count		 		-- 		Numero de notas dadas ao usuario
	* tipo		 				-- 		Tipo de usuário
											* A -> Aluno [DEFAULT]
											* P -> Professor

	Constraints:
	* usuario_pk 				-- [PK] 
	* usuario_tipo				--		Tipos permitidos
	* usuario_email				--		Formato de e-mail
 */
CREATE TABLE usuario
(
	-- Atributos
	nome		VARCHAR(32),
	email		VARCHAR(32),
	senha		VARCHAR(32),
	rank 		REAL,
	rank_sum	REAL,
	rank_count	INT,
	tipo		CHAR(1) 		DEFAULT 'A',
	-- Constraints
	CONSTRAINT usuario_pk
		PRIMARY KEY (email),
	CONSTRAINT usuario_tipo 
		CHECK ( UPPER(tipo) IN ( 'A', 'P' ) ),
	CONSTRAINT usuario_email 
		CHECK ( UPPER(email) LIKE ( '%@%.%' ) )
);



/** ==================== Tabela Avalia ====================
	
	Define uma avaliacao de um usuario com nota e possivel
	comentario

	Atributos:
	* mail_avaliador 			-- [PK] E-mail do avaliador
	* mail_avaliado 			-- [PK] E-mail do avaliado
	* nota 						-- 		Nota da avaliacao [0-5]
	* comentario 				-- 		Comentario opcional da nota
	* data 						-- 		Data do comentario

	Constraints:
	* avalia_pk	 				-- [PK]
	* avalia_fk_avaliador	 	-- [FK]
	* avalia_fk_avaliado	 	-- [FK]
	* avalia_nota	 			-- 		Notas permitidas
 */
CREATE TABLE avalia
(
	-- Atributos
	mail_avaliador	VARCHAR(32),
	mail_avaliado	VARCHAR(32),
	nota			INT2,
	comentario		TEXT,
	data			TIMESTAMP,

	-- Constraints
	CONSTRAINT avalia_pk
		PRIMARY KEY (mail_avaliador, mail_avaliado),
	CONSTRAINT avalia_fk_avaliador
		FOREIGN KEY (mail_avaliador)
		REFERENCES usuario (email)
		ON DELETE CASCADE,
	CONSTRAINT avalia_fk_avaliado
		FOREIGN KEY (mail_avaliado)
		REFERENCES usuario (email)
		ON DELETE CASCADE,
	CONSTRAINT avalia_nota
		CHECK ( nota >= 0 AND nota <= 5 )
);



/** ==================== Tabela Aluno ====================

	Define informacoes de um aluno

	Atributos:
	* email 					-- [PK] E-mail do aluno
	* creditos 					-- [Dv] Creditos atuais do aluno

	Constraints:
	* aluno_pk	 				-- [PK]
	* aluno_fk_usuario	 		-- [FK]
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

	Define informacoes de um professor

	Atributos:
	* email 					-- [PK] E-mail do professor

	Constraints:
	* professor_pk	 			-- [PK]
	* professor_fk_usuario	 	-- [FK]
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

	Define as linguas estudadas pelo usuario

	Atributos:
	* email 					-- [PK] E-mail do usuario
	* lingua 					-- 		Lingua estudada

	Constraints:
	* estuda_pk 				-- [PK]
	* estuda_fk_usuario 		-- [FK]
 */
CREATE TABLE estuda
(
	-- Atributos
	email		VARCHAR(32),
	lingua		VARCHAR(16),
	-- Constraints
	CONSTRAINT estuda_pk
		PRIMARY KEY (email, lingua),
	CONSTRAINT estuda_fk_usuario
		FOREIGN KEY (email)
		REFERENCES usuario (email)
		ON DELETE CASCADE
);

/** ==================== Tabela Mensagem ====================

	Define uma mensagem de um usuario a outro

	Atributos:
	* mail_origem 				-- [PK] E-mail do usuario que enviou
	* mail_destino 				-- [PK] E-mail do usuario que recebe
	* conteudo 					-- 		Conteudo da mensagem
	* data 						-- 		Data de envio

	Constraints:
	* usuario_pk 					-- [PK]
	* mensagem_fk_usuario_origem 	-- [FK]
	* mensagem_fk_usuario_destino 	-- [FK]
 */
CREATE TABLE mensagem
(
	-- Atributos
	mail_origem		VARCHAR(32),
	mail_destino	VARCHAR(32),
	conteudo		TEXT NOT NULL,
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

	Define uma ligacao paga entre dois usuarios

	Atributos:
	* mail_aluno 				-- E-mail do aluno que realizou a ligacao
	* mail_professor 			-- E-mail do professor que recebeu a ligacao
	* inicio 					-- Data/Hora de inicio da ligacao
	* fim 						-- Data/Hora de termino
	* custo 					-- Custo total da ligacao

	Constraints:
	* ligacao_paga_pk 			-- [PK]
	* ligacao_paga_fk_aluno 	-- [FK]
	* ligacao_paga_fk_professor -- [FK]
	* ligacao_paga_custo		-- 		Verifica custo da ligacao >= 0
 */
CREATE TABLE ligacao_paga
(
	-- Atributos
	mail_aluno		VARCHAR(32),
	mail_professor	VARCHAR(32),
	inicio			TIMESTAMP NOT NULL,
	fim				TIMESTAMP NOT NULL,
	custo			REAL NOT NULL,
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
		ON DELETE CASCADE,
	CONSTRAINT ligacao_paga_custo
		CHECK ( custo >= 0 )
);

/** ==================== Tabela Recarga ====================

	Define uma recarga feita por um aluno

	Atributos:
	* email 					-- [PK] E-mail do aluno realizando a recarga
	* recarga 					-- 		Valor da recarga
	* data 						-- [PK] Data da recarga

	Constraints:
	* recarga_pk 				-- [PK]
	* recarga_fk_aluno 			-- [FK]
	* recarga_valor 			-- 		Verifica valor da recarga positivo
 */
CREATE TABLE recarga
(
	-- Atributos
	email			VARCHAR(32),
	recarga			REAL NOT NULL,
	data			TIMESTAMP,
	-- Constraints
	CONSTRAINT recarga_pk
		PRIMARY KEY (email, data),
	CONSTRAINT recarga_fk_aluno
		FOREIGN KEY (email)
		REFERENCES aluno (email)
		ON DELETE CASCADE,
	CONSTRAINT recarga_valor
		CHECK ( recarga > 0 )
);


/** ========================================
 *					Triggers
 * =========================================
 */

/** ==================== Recarga de Aluno ====================

	Atualiza os creditos de um aluno apos uma recarga.
	Configura a Data/Hora da recarga

 */
CREATE OR REPLACE FUNCTION aluno_recarga() 
RETURNS TRIGGER AS $aluno_recarga$
	BEGIN
		IF (TG_OP = 'DELETE') THEN
			UPDATE aluno SET creditos = creditos - OLD.recarga
			WHERE aluno.email = OLD.email;
		ELSIF (TG_OP = 'UPDATE') THEN
			IF (OLD.email = NEW.email) THEN
				UPDATE aluno SET creditos = creditos -OLD.recarga +NEW.recarga
				WHERE aluno.email = NEW.email;
			ELSE
				UPDATE aluno SET creditos = creditos -OLD.recarga
				WHERE aluno.email = OLD.email;

				UPDATE aluno SET creditos = creditos +NEW.recarga
				WHERE aluno.email = NEW.email;
			END IF;
		ELSIF (TG_OP = 'INSERT') THEN
			NEW.data := current_timestamp;
			UPDATE aluno SET creditos = creditos + NEW.recarga
			WHERE aluno.email = NEW.email;
		END IF;

		RETURN NEW;
	END;
$aluno_recarga$ LANGUAGE plpgsql;

CREATE TRIGGER aluno_recarga BEFORE INSERT OR UPDATE OR DELETE ON recarga
	FOR EACH ROW EXECUTE PROCEDURE aluno_recarga();


/** ==================== Ligacao de Aluno ====================

	Atualiza os creditos de um aluno de acordo com o custo da ligacao paga
	
 */
CREATE OR REPLACE FUNCTION aluno_ligacao_paga() 
RETURNS TRIGGER AS $aluno_ligacao_paga$
	BEGIN
		IF (TG_OP = 'DELETE') THEN
			UPDATE aluno SET creditos = creditos +OLD.custo
			WHERE aluno.email = OLD.mail_aluno;
		ELSIF (TG_OP = 'UPDATE') THEN
			IF (OLD.mail_aluno = NEW.mail_aluno) THEN
				UPDATE aluno SET creditos = creditos +OLD.custo -NEW.custo
				WHERE aluno.email = NEW.mail_aluno;
			ELSE
				UPDATE aluno SET creditos = creditos +OLD.custo
				WHERE aluno.email = OLD.mail_aluno;

				UPDATE aluno SET creditos = creditos -NEW.custo
				WHERE aluno.email = NEW.mail_aluno;
			END IF;
		ELSIF (TG_OP = 'INSERT') THEN
			UPDATE aluno SET creditos = creditos -NEW.custo
			WHERE aluno.email = NEW.mail_aluno;
		END IF;

		RETURN NEW;
	END;
$aluno_ligacao_paga$ LANGUAGE plpgsql;

CREATE TRIGGER aluno_ligacao_paga BEFORE INSERT OR UPDATE OR DELETE ON ligacao_paga
	FOR EACH ROW EXECUTE PROCEDURE aluno_ligacao_paga();


/** ==================== Mensagem Usuario ====================

	Configura a Data/Hora de uma mensagem
	
 */

CREATE OR REPLACE FUNCTION user_mensagem() 
RETURNS TRIGGER AS $user_mensagem$
	BEGIN

		NEW.data := current_timestamp;

		RETURN NEW;
	END;
$user_mensagem$ LANGUAGE plpgsql;

CREATE TRIGGER user_mensagem BEFORE INSERT ON mensagem
	FOR EACH ROW EXECUTE PROCEDURE user_mensagem();


/** ==================== Usuario ====================

	Configura o rank inicial do usuaio sendo 0
	
 */

CREATE OR REPLACE FUNCTION usuario() 
RETURNS TRIGGER AS $usuario$
	BEGIN

		NEW.rank 		:= 0;
		NEW.rank_sum	:= 0;
		NEW.rank_count	:= 0;

		RETURN NEW;
	END;
$usuario$ LANGUAGE plpgsql;

CREATE TRIGGER usuario BEFORE INSERT ON usuario
	FOR EACH ROW EXECUTE PROCEDURE usuario();


/** ==================== Avaliacao de Usuario ====================

	Atualiza o rank do usuario de acordo com as avaliacoes feitas.
	Configura a data/hora da avaliacao para a data atual
	
 */

CREATE OR REPLACE FUNCTION avalia_usuario() 
RETURNS TRIGGER AS $avalia_usuario$
	BEGIN
		IF (TG_OP = 'DELETE') THEN

			UPDATE usuario SET 
				rank_sum = rank_sum -OLD.nota,
				rank_count = rank_count -1,
				rank = (rank_sum -OLD.nota)/(rank_count -1)
			WHERE usuario.email = OLD.mail_avaliado;

		ELSIF (TG_OP = 'UPDATE') THEN

			NEW.data := current_timestamp;

			IF (OLD.mail_avaliado = NEW.mail_avaliado) THEN
				UPDATE usuario SET 
					rank_sum = rank_sum +NEW.nota -OLD.nota,
					rank = (rank_sum +NEW.nota -OLD.nota)/rank_count
				WHERE usuario.email = NEW.mail_avaliado;
			ELSE
				UPDATE usuario SET 
					rank_sum = rank_sum -OLD.nota,
					rank_count = rank_count -1,
					rank = (rank_sum -OLD.nota)/(rank_count -1)
				WHERE usuario.email = OLD.mail_avaliado;

				UPDATE usuario SET 
					rank_sum = rank_sum +NEW.nota,
					rank_count = rank_count +1,
					rank = (rank_sum +NEW.nota)/(rank_count +1)
				WHERE usuario.email = NEW.mail_avaliado;
			END IF;

		ELSIF (TG_OP = 'INSERT') THEN

			NEW.data := current_timestamp;
			UPDATE usuario SET 
				rank_sum = rank_sum +NEW.nota,
				rank_count = rank_count +1,
				rank = (rank_sum +NEW.nota)/(rank_count +1)
			WHERE usuario.email = NEW.mail_avaliado;

		END IF;

		RETURN NEW;
	END;
$avalia_usuario$ LANGUAGE plpgsql;

CREATE TRIGGER avalia_usuario BEFORE INSERT OR UPDATE OR DELETE ON avalia
	FOR EACH ROW EXECUTE PROCEDURE avalia_usuario();