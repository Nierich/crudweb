create table pessoa (id serial NOT NULL,
						nome VARCHAR(150) NOT NULL,
						email VARCHAR(50) NULL,
						telefone VARCHAR(15) NOT NULL,
						data_nascimento DATE NOT NULL,
						primary key (id)
);

create table usuario (id serial NOT NULL,
					 nome VARCHAR(150) NOT NULL,
					 senha VARCHAR(200) NOT NULL,
					 primary key (id)
					 );