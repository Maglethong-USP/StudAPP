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
DROP TABLE contato			CASCADE;
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
	* id 						-- [PK] ID
	* nome 						-- 		Nome do usuário
	* email 					-- [SK] E-mail do usuário
	* senha 					-- 		Senha do usuário
	* rank		 				-- 		Rank calculado do usuário
	* rank_sum		 			-- 		Soma de todas as notas ao usuario
	* rank_count		 		-- 		Numero de notas dadas ao usuario
	* tipo		 				-- 		Tipo de usuário
											* A -> Aluno [DEFAULT]
											* P -> Professor

	Constraints:
	* usuario_pk 				-- [PK] 
	* usuario_sk_email			-- [SK] Email nao pode repetir
	* usuario_tipo				--		Tipos permitidos
	* usuario_email				--		Formato de e-mail
 */
CREATE TABLE usuario
(
	-- Atributos
	id 			SERIAL,
	nome		VARCHAR(32),
	email		VARCHAR(32),
	senha		VARCHAR(32),
	rank 		REAL,
	rank_sum	REAL,
	rank_count	INT,
	tipo		CHAR(1) 		DEFAULT 'A',
	-- Constraints
	CONSTRAINT usuario_pk
		PRIMARY KEY (id),
	CONSTRAINT usuario_sk_email
		UNIQUE (email),
	CONSTRAINT usuario_tipo 
		CHECK ( UPPER(tipo) IN ( 'A', 'P' ) ),
	CONSTRAINT usuario_email 
		CHECK ( UPPER(email) LIKE ( '%@%.%' ) )
);


/** ==================== Tabela Contato ====================
	
	Define um contato de um usuario

	Atributos:
	* id_usuario				-- [PK]	ID do usuário
	* id_contato				-- [PK] ID do contato do usuário

	Constraints:
	* contato_pk 				-- [PK] 
	* contato_fk_principal		-- [FK]
	* contato_fk_amigo			-- [FK]
	* contato_diferentes		-- 		N~ao pode ter si mesmo como contato
 */
CREATE TABLE contato
(
	-- Atributos
	id_usuario			INT,
	id_contato			INT,
	-- Constraints
	CONSTRAINT contato_pk
		PRIMARY KEY (id_usuario, id_contato),
	CONSTRAINT contato_fk_principal 
		FOREIGN KEY (id_usuario)
		REFERENCES usuario (id)
		ON DELETE CASCADE,
	CONSTRAINT contato_fk_amigo 
		FOREIGN KEY (id_contato)
		REFERENCES usuario (id)
		ON DELETE CASCADE,
	CONSTRAINT contato_diferentes
		CHECK ( id_usuario != id_contato )
);


/** ==================== Tabela Avalia ====================
	
	Define uma avaliacao de um usuario com nota e possivel
	comentario

	Atributos:
	* id_avaliador 				-- [PK] id do avaliador
	* id_avaliado 				-- [PK] id do avaliado
	* nota 						-- 		Nota da avaliacao [0-5]
	* comentario 				-- 		Comentario opcional da nota
	* data 						-- 		Data do comentario

	Constraints:
	* avalia_pk	 				-- [PK]
	* avalia_fk_avaliador	 	-- [FK]
	* avalia_fk_avaliado	 	-- [FK]
	* avalia_diferentes			-- 		Usu'ario n`ao pode se auto avaliar
	* avalia_nota	 			-- 		Notas permitidas
 */
CREATE TABLE avalia
(
	-- Atributos
	id_avaliador	INT,
	id_avaliado		INT,
	nota			INT2,
	comentario		TEXT,
	data			TIMESTAMP,

	-- Constraints
	CONSTRAINT avalia_pk
		PRIMARY KEY (id_avaliador, id_avaliado),
	CONSTRAINT avalia_fk_avaliador
		FOREIGN KEY (id_avaliador)
		REFERENCES usuario (id)
		ON DELETE CASCADE,
	CONSTRAINT avalia_fk_avaliado
		FOREIGN KEY (id_avaliado)
		REFERENCES usuario (id)
		ON DELETE CASCADE,
	CONSTRAINT avalia_diferentes
		CHECK ( id_avaliador != id_avaliado ),
	CONSTRAINT avalia_nota
		CHECK ( nota >= 0 AND nota <= 5 )
);



/** ==================== Tabela Aluno ====================

	Define informacoes de um aluno

	Atributos:
	* id 	 					-- [PK] id do aluno
	* creditos 					-- [Dv] Creditos atuais do aluno

	Constraints:
	* aluno_pk	 				-- [PK]
	* aluno_fk_usuario	 		-- [FK]
 */
CREATE TABLE aluno
(
	-- Atributos
	id			INT,
	creditos	REAL 			DEFAULT 0,
	-- Constraints
	CONSTRAINT aluno_pk
		PRIMARY KEY (id),
	CONSTRAINT aluno_fk_usuario
		FOREIGN KEY (id)
		REFERENCES usuario (id)
		ON DELETE CASCADE
);

/** ==================== Tabela Professor ====================

	Define informacoes de um professor

	Atributos:
	* id 						-- [PK] id do professor

	Constraints:
	* professor_pk	 			-- [PK]
	* professor_fk_usuario	 	-- [FK]
 */
CREATE TABLE professor
(
	-- Atributos
	id		INT,
	-- Constraints
	CONSTRAINT professor_pk
		PRIMARY KEY (id),
	CONSTRAINT professor_fk_usuario
		FOREIGN KEY (id)
		REFERENCES usuario (id)
		ON DELETE CASCADE
);

/** ==================== Tabela Estuda ====================

	Define as linguas estudadas pelo usuario

	Atributos:
	* id 						-- [PK] id do usuario
	* lingua 					-- 		Lingua estudada

	Constraints:
	* estuda_pk 				-- [PK]
	* estuda_fk_usuario 		-- [FK]
 */
CREATE TABLE estuda
(
	-- Atributos
	id			INT,
	lingua		VARCHAR(16),
	-- Constraints
	CONSTRAINT estuda_pk
		PRIMARY KEY (id, lingua),
	CONSTRAINT estuda_fk_usuario
		FOREIGN KEY (id)
		REFERENCES usuario (id)
		ON DELETE CASCADE
);

/** ==================== Tabela Mensagem ====================

	Define uma mensagem de um usuario a outro

	Atributos:
	* id_origem 					-- [PK] id do usuario que enviou
	* id_destino 					-- [PK] id do usuario que recebe
	* conteudo 						-- 		Conteudo da mensagem
	* data 							-- [PK]	Data de envio

	Constraints:
	* usuario_pk 					-- [PK]
	* mensagem_fk_usuario_origem 	-- [FK]
	* mensagem_fk_usuario_destino 	-- [FK]
	* mensagem_diferentes			-- 		N~ao pode mandar mensagens a si mesmo
 */
CREATE TABLE mensagem
(
	-- Atributos
	id_origem		INT,
	id_destino		INT,
	conteudo		TEXT NOT NULL,
	data			TIMESTAMP,
	-- Constraints
	CONSTRAINT mensagem_pk
		PRIMARY KEY (id_origem, id_destino, data),
	CONSTRAINT mensagem_fk_usuario_origem
		FOREIGN KEY (id_origem)
		REFERENCES usuario (id)
		ON DELETE CASCADE,
	CONSTRAINT mensagem_fk_usuario_destino
		FOREIGN KEY (id_destino)
		REFERENCES usuario (id)
		ON DELETE CASCADE,
	CONSTRAINT mensagem_diferentes
		CHECK ( id_origem != id_destino )
);

/** ==================== Tabela LigaçãoPaga ====================

	Define uma ligacao paga entre dois usuarios

	Atributos:
	* id_aluno 					-- id do aluno que realizou a ligacao
	* id_professor 				-- id do professor que recebeu a ligacao
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
	id_aluno		INT,
	id_professor	INT,
	inicio			TIMESTAMP NOT NULL,
	fim				TIMESTAMP NOT NULL,
	custo			REAL,
	-- Constraints
	CONSTRAINT ligacao_paga_pk
		PRIMARY KEY (id_aluno, id_professor, inicio),
	CONSTRAINT ligacao_paga_fk_aluno
		FOREIGN KEY (id_aluno)
		REFERENCES aluno (id)
		ON DELETE CASCADE,
	CONSTRAINT ligacao_paga_fk_professor
		FOREIGN KEY (id_professor)
		REFERENCES professor (id)
		ON DELETE CASCADE,
	CONSTRAINT ligacao_paga_custo
		CHECK ( custo >= 0 )
);

/** ==================== Tabela Recarga ====================

	Define uma recarga feita por um aluno

	Atributos:
	* id 					-- [PK] id do aluno realizando a recarga
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
	id				INT,
	recarga			REAL NOT NULL,
	data			TIMESTAMP,
	-- Constraints
	CONSTRAINT recarga_pk
		PRIMARY KEY (id, data),
	CONSTRAINT recarga_fk_aluno
		FOREIGN KEY (id)
		REFERENCES aluno (id)
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
			WHERE aluno.id = OLD.id;
			RETURN OLD;
		ELSIF (TG_OP = 'UPDATE') THEN
			IF (OLD.id = NEW.id) THEN
				UPDATE aluno SET creditos = creditos -OLD.recarga +NEW.recarga
				WHERE aluno.id = NEW.id;
			ELSE
				UPDATE aluno SET creditos = creditos -OLD.recarga
				WHERE aluno.id = OLD.id;

				UPDATE aluno SET creditos = creditos +NEW.recarga
				WHERE aluno.id = NEW.id;
				RETURN NEW;
			END IF;
		ELSIF (TG_OP = 'INSERT') THEN
			NEW.data := current_timestamp;
			UPDATE aluno SET creditos = creditos + NEW.recarga
			WHERE aluno.id = NEW.id;
			RETURN NEW;
		END IF;

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
			WHERE aluno.id = OLD.id_aluno;
			RETURN OLD;
		ELSIF (TG_OP = 'UPDATE') THEN
			IF (OLD.id_aluno = NEW.id_aluno) THEN
				UPDATE aluno SET creditos = creditos +OLD.custo -NEW.custo
				WHERE aluno.id = NEW.id_aluno;
			ELSE
				UPDATE aluno SET creditos = creditos +OLD.custo
				WHERE aluno.id = OLD.id_aluno;

				UPDATE aluno SET creditos = creditos -NEW.custo
				WHERE aluno.id = NEW.id_aluno;
			END IF;
			RETURN NEW;
		ELSIF (TG_OP = 'INSERT') THEN
			UPDATE aluno SET creditos = creditos -NEW.custo
			WHERE aluno.id = NEW.id_aluno;
			RETURN NEW;
		END IF;

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

CREATE OR REPLACE FUNCTION usuario_def() 
RETURNS TRIGGER AS $usuario_def$
	BEGIN

		--NEW.id 			:= sequence?;
		NEW.rank 		:= 0;
		NEW.rank_sum	:= 0;
		NEW.rank_count	:= 0;

		RETURN NEW;
	END;
$usuario_def$ LANGUAGE plpgsql;

CREATE TRIGGER usuario_def BEFORE INSERT ON usuario
	FOR EACH ROW EXECUTE PROCEDURE usuario_def();

/** ==================== Usuario ====================

	Insere o usuário na tabela aluno/professor de acordo com seu tipo
	
 */

CREATE OR REPLACE FUNCTION usuario() 
RETURNS TRIGGER AS $usuario$
	BEGIN
		IF (TG_OP = 'DELETE') THEN

			IF (OLD.tipo = 'A') THEN
				DELETE FROM aluno WHERE id = OLD.id;
			ELSE
				DELETE FROM professor WHERE id = OLD.id;
			END IF;
			RETURN OLD;
			
		ELSIF (TG_OP = 'UPDATE') THEN

			IF (Old.tipo != NEW.tipo) THEN
			
				RAISE EXCEPTION '% Not allowed to change type', NEW.id;

			ELSIF (OLD.id != NEW.id) THEN

				IF (NEW.tipo = 'A') THEN
					UPDATE aluno SET id = NEW.id
					WHERE id = OLD.id;
				ELSE
					UPDATE professor SET id = NEW.id
					WHERE id = OLD.id;
				END IF;

			END IF;
			RETURN NEW;

		ELSIF (TG_OP = 'INSERT') THEN

			IF (NEW.tipo = 'A') THEN
				INSERT INTO aluno (id) VALUES (NEW.id);
			ELSE
				INSERT INTO professor (id) VALUES (NEW.id);
			END IF;
			RETURN NEW;

		END IF;

	END;
$usuario$ LANGUAGE plpgsql;

CREATE TRIGGER usuario AFTER INSERT OR UPDATE OR DELETE ON usuario
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
			WHERE usuario.id = OLD.id_avaliado;
			RETURN OLD;

		ELSIF (TG_OP = 'UPDATE') THEN

			NEW.data := current_timestamp;

			IF (OLD.id_avaliado = NEW.id_avaliado) THEN
				UPDATE usuario SET 
					rank_sum = rank_sum +NEW.nota -OLD.nota,
					rank = (rank_sum +NEW.nota -OLD.nota)/rank_count
				WHERE usuario.id = NEW.id_avaliado;
			ELSE
				UPDATE usuario SET 
					rank_sum = rank_sum -OLD.nota,
					rank_count = rank_count -1,
					rank = (rank_sum -OLD.nota)/(rank_count -1)
				WHERE usuario.id = OLD.id_avaliado;

				UPDATE usuario SET 
					rank_sum = rank_sum +NEW.nota,
					rank_count = rank_count +1,
					rank = (rank_sum +NEW.nota)/(rank_count +1)
				WHERE usuario.id = NEW.id_avaliado;
			END IF;
			RETURN NEW;

		ELSIF (TG_OP = 'INSERT') THEN

			NEW.data := current_timestamp;
			UPDATE usuario SET 
				rank_sum = rank_sum +NEW.nota,
				rank_count = rank_count +1,
				rank = (rank_sum +NEW.nota)/(rank_count +1)
			WHERE usuario.id = NEW.id_avaliado;
			RETURN NEW;

		END IF;

	END;
$avalia_usuario$ LANGUAGE plpgsql;

CREATE TRIGGER avalia_usuario BEFORE INSERT OR UPDATE OR DELETE ON avalia
	FOR EACH ROW EXECUTE PROCEDURE avalia_usuario();