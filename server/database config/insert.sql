/**
	StudAPP

	Script de Inserção nas Tabelas


	Legenda de Comentários:
	- [PK]	- Chave Primária
	- [Un]	- Chave Secundária [Atributo único]
	- [Dv] 	- Atributo Derivado
	- [FK]	- Chave Estrangeira

	Outras informações
	- Identação usando caractere TAB = 4 espaços
	- Todos os atributos derivados serão calculados por comandos SELECT após todas as inserções iniciais
*/

INSERT INTO usuario (nome, email, senha, tipo) 
	VALUES ('Andy', 'andy@yopmail.com', 'qwerty', 'A');
INSERT INTO aluno (email) 
	VALUES ('andy@yopmail.com');

INSERT INTO usuario (nome, email, senha, tipo) 
	VALUES ('Yvan', 'yvan@yopmail.com', 'qwerty', 'A');
INSERT INTO aluno (email) 
	VALUES ('yvan@yopmail.com');

INSERT INTO usuario (nome, email, senha, tipo) 
	VALUES ('Professor', 'prof@yopmail.com', 'qwerty', 'P');
INSERT INTO professor (email) 
	VALUES ('prof@yopmail.com');


INSERT INTO avalia (mail_avaliador, mail_avaliado, nota)
	VALUES ('yvan@yopmail.com', 'andy@yopmail.com', 0);

INSERT INTO avalia (mail_avaliador, mail_avaliado, nota, comentario)
	VALUES ('prof@yopmail.com', 'andy@yopmail.com', 5, 'Andy seu lindo!');



INSERT INTO recarga (email, recarga)
	VALUES ('andy@yopmail.com', 8000);

INSERT INTO recarga (email, recarga)
	VALUES ('andy@yopmail.com', 1);