
--Ingrediente: (Id,Nome)
CREATE TABLE ingrediente( 
  id integer NOT NULL,
  nome varchar(100) NOT NULL,
  CONSTRAINT pk_ingrediente PRIMARY KEY (id));
 
 --Unidade: (Id,Tipo)
CREATE TABLE unidade( 
  id integer NOT NULL,	
  tipo varchar(100) NOT NULL,
  CONSTRAINT pk_unidade PRIMARY KEY (id));

--Receita: (Nome, ID Receita, Descriçao)
CREATE TABLE receita( 
  id integer NOT NULL,
  nome varchar(100) NOT NULL,
  descricao character varying(3000),
  CONSTRAINT pk_Receitas PRIMARY KEY (id));
  
  ALTER TABLE receita ADD modopreparo character varying(3000);

--Receita-Ingrediente: (Id,Quantidade, Id Receita, ID Ingrediente, ID Unidade)
CREATE TABLE receitaingrediente( 
  id integer NOT NULL,
  quantidade integer NOT NULL,
  id_receita integer NOT NULL,
  id_ingrediente integer NOT NULL,
  id_unidade integer NOT NULL,
  
  CONSTRAINT pk_receitaingrediente PRIMARY KEY (id));
  
ALTER TABLE receitaingrediente ADD 
  CONSTRAINT fk_receitaingrediente_receita FOREIGN KEY (id_receita ) REFERENCES receita(id);
  
ALTER TABLE receitaingrediente ADD
  CONSTRAINT fk_receitaingrediente_ingrediente FOREIGN KEY (id_ingrediente ) REFERENCES ingrediente(id);
  
ALTER TABLE receitaingrediente ADD
  CONSTRAINT fk_receitaingrediente_unidade FOREIGN KEY (id_unidade) REFERENCES unidade(id);

--Restrição: (Id, Nome)
CREATE TABLE restricao( 
  id integer NOT NULL,
  nome varchar(100) NOT NULL,
  CONSTRAINT pk_restricao PRIMARY KEY (id));
  

  --Restrição-Ingrediente: (Id, Nome, ID Ingrediente)
  CREATE TABLE restricaoingrediente( 
  id integer NOT NULL,
  id_ingrediente integer NOT NULL,
  CONSTRAINT pk_restricaoingrediente PRIMARY KEY (id));

ALTER TABLE restricaoingrediente ADD 
  CONSTRAINT fk_restricaoingrediente_ingrediente FOREIGN KEY (id_ingrediente ) REFERENCES ingrediente(id);
  

--Usuario: (Id, Nome, Senha, Restrição)
CREATE TABLE usuario( 
  id integer NOT NULL,
  nome character varying (100) NOT NULL,
  senha character varying (100) NOT NULL,
  id_restricao integer NOT NULL,
  CONSTRAINT pk_usuario PRIMARY KEY (id));
  
ALTER TABLE usuario ADD 
  CONSTRAINT fk_usuario_restricao FOREIGN KEY (id_restricao) REFERENCES restricao(id);
  
  
  
  