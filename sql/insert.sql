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

INSERT INTO usuario (nome, email, senha, tipo) 
	VALUES ('Yvan', 'yvan@yopmail.com', 'qwerty', 'A');

INSERT INTO usuario (nome, email, senha, tipo) 
	VALUES ('Professor', 'prof@yopmail.com', 'qwerty', 'P');


INSERT INTO avalia (id_avaliador, id_avaliado, nota)
	VALUES
	(
		(SELECT id FROM usuario WHERE email = 'yvan@yopmail.com'), 
		(SELECT id FROM usuario WHERE email = 'andy@yopmail.com'), 
		0
	);

INSERT INTO avalia (id_avaliador, id_avaliado, nota)
	VALUES
	(
		(SELECT id FROM usuario WHERE email = 'prof@yopmail.com'), 
		(SELECT id FROM usuario WHERE email = 'andy@yopmail.com'), 
		5
	);



INSERT INTO recarga (id, recarga)
	VALUES
	(
		(SELECT id FROM usuario WHERE email = 'andy@yopmail.com'), 
		8000
	);

INSERT INTO recarga (id, recarga)
	VALUES
	(
		(SELECT id FROM usuario WHERE email = 'andy@yopmail.com'), 
		1
	);


INSERT INTO estuda (id, lingua) 
	VALUES 
	(
		(SELECT id FROM usuario WHERE email = 'andy@yopmail.com'), 
		'Ingles'
	);

INSERT INTO estuda (id, lingua) 
	VALUES 
	(
		(SELECT id FROM usuario WHERE email = 'andy@yopmail.com'), 
		'Alemao'
	);