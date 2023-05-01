DROP TABLE IF EXISTS employees;
 
CREATE TABLE employees (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  mail VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL
);

DROP TABLE IF EXISTS typecredit;
CREATE TABLE typecredit (
  id_type INT AUTO_INCREMENT  PRIMARY KEY,
  type_credit VARCHAR(250) NOT NULL  
);

DROP TABLE IF EXISTS visiteurs;
 
CREATE TABLE visiteurs (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  nom VARCHAR(250) NOT NULL,
  prenom VARCHAR(250) NOT NULL,
  mail VARCHAR(250) NOT NULL,
  revenus_foyer FLOAT NOT NULL,
  montant_credit FLOAT NOT NULL,
  duree_credit INT NOT NULL,
  montant_mensualites FLOAT NOT NULL,
  contact BOOLEAN NULL DEFAULT NULL,
  
  type_credit INT REFERENCES typecredit (id_type)
);
 
INSERT INTO employees (first_name, last_name, mail, password) VALUES
  ('Laurent', 'GINA', 'laurentgina@mail.com', 'laurent'),
  ('Sophie', 'FONCEK', 'sophiefoncek@mail.com', 'sophie'),
  ('Agathe', 'FEELING', 'agathefeeling@mail.com', 'agathe');
  
INSERT INTO `typecredit` VALUES (1,'Consommation'),(2,'Immobilier');
