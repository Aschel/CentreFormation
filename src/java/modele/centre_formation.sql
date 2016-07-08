-- Change le délimiteur pour pouvoir écrire des ; dans la
-- procédure stockée
DELIMITER §

DROP SCHEMA IF EXISTS centre_formation §
CREATE SCHEMA IF NOT EXISTS centre_formation DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci §
USE centre_formation §

CREATE TABLE IF NOT EXISTS promotion (
  id_promotion INT NOT NULL AUTO_INCREMENT,
  nom VARCHAR(45) NOT NULL,
  PRIMARY KEY (id_promotion),
  UNIQUE INDEX nom_UNIQUE (nom ASC))
ENGINE = InnoDB§


CREATE TABLE IF NOT EXISTS personne (
  id_personne INT NOT NULL AUTO_INCREMENT,
  nom VARCHAR(45) NOT NULL,
  prenom VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  PRIMARY KEY (id_personne),
  UNIQUE INDEX email_UNIQUE (email ASC))
ENGINE = InnoDB§


CREATE TABLE IF NOT EXISTS membre_promotion (
  id_promotion INT NOT NULL,
  id_personne INT NOT NULL,
  PRIMARY KEY (id_promotion, id_personne),
  CONSTRAINT fk_membre_promotion_promotion
    FOREIGN KEY (id_promotion)
    REFERENCES promotion (id_promotion)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_membre_promotion_personne
    FOREIGN KEY (id_personne)
    REFERENCES personne (id_personne)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB§


CREATE TABLE IF NOT EXISTS projet (
  id_projet INT NOT NULL AUTO_INCREMENT,
  id_promotion INT NOT NULL,
  id_createur INT NOT NULL,
  sujet TEXT NOT NULL,
  titre VARCHAR(100) NOT NULL,
  date_creation DATETIME NOT NULL,
  date_limite DATETIME NOT NULL,
  PRIMARY KEY (id_projet),
  CONSTRAINT fk_projet_promotion
    FOREIGN KEY (id_promotion)
    REFERENCES promotion (id_promotion)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_projet_personne
    FOREIGN KEY (id_createur)
    REFERENCES personne (id_personne)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB§


CREATE TABLE IF NOT EXISTS equipe (
  id_equipe INT NOT NULL AUTO_INCREMENT,
  id_projet INT NOT NULL,
  id_createur INT NOT NULL,
  date_creation DATETIME NOT NULL,
  resume VARCHAR(255) NULL,
  PRIMARY KEY (id_equipe),
  CONSTRAINT fk_equipe_projet
    FOREIGN KEY (id_projet)
    REFERENCES projet (id_projet)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_equipe_personne
    FOREIGN KEY (id_createur)
    REFERENCES personne (id_personne)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB§


CREATE TABLE IF NOT EXISTS membre_equipe (
  id_equipe INT NOT NULL,
  id_personne INT NOT NULL,
  PRIMARY KEY (id_equipe, id_personne),
  CONSTRAINT fk_membre_equipe_equipe
    FOREIGN KEY (id_equipe)
    REFERENCES equipe (id_equipe)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_membre_equipe_personne
    FOREIGN KEY (id_personne)
    REFERENCES personne (id_personne)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB§

DROP PROCEDURE IF EXISTS centre_formation_refresh §
CREATE DEFINER=root@localhost PROCEDURE centre_formation_refresh()
BEGIN
	-- Lever temporairement les contraintes d'intégrité
	SET FOREIGN_KEY_CHECKS=0;
	TRUNCATE equipe;
	TRUNCATE membre_equipe;
	TRUNCATE membre_promotion;
	TRUNCATE personne;
    TRUNCATE projet;
    TRUNCATE promotion;
	-- Remettre les contraintes d'integrite
	SET FOREIGN_KEY_CHECKS=1;

	BEGIN
    -- Annuler les changements et afficher l'erreur
    -- si violation de contrainte d'intégrité
	  DECLARE EXIT HANDLER FOR SQLSTATE '23000'
	  BEGIN
      SHOW ERRORS;
      ROLLBACK;
	  END;
    -- Démarrer la transaction
		START TRANSACTION;
        INSERT INTO promotion(id_promotion,nom) VALUES
        (1,'Mars2016'),
        (2,'Juin2016');
        
		INSERT INTO personne (id_personne,nom,prenom,email,password) VALUES
        (1,'Jean','Feyte','jean.feyte@test.fr','aversion'),
        (2,'Jacques','Tata','jacques.tata@essai.com','bibliotheque'),
        (3,'Flo','Feyte','flo.feyte@gmail.com','carbonara'),
        (4,'Bob','Gob','bob.gob@jeu.fr','deltaplane'),
        (5,'Alex','Click','alex.click@fail.fr','eclipse'),
        (6,'Rey','Tour','reytourn@test.com','formation'),
        (7,'Poupee','Love','poupee@reves.fr','gravier'),
        (8,'Anna','Mont','anna.mont@nana.com','helicoptere'),
        (9,'Form','Premier','premierform@formateur.com','invisible'),
        (10,'Chef','Capitain','capitaine@cefisi.fr','juriste');
        
        INSERT INTO membre_promotion(id_promotion,id_personne) VALUES
        (1,1),
        (1,2),
        (1,3),
        (1,4),
        (1,5),
        (1,6),
        (2,7),
        (2,8);
        
        INSERT INTO projet(id_projet,id_promotion,id_createur,sujet,titre,date_creation,date_limite) VALUES
        (1,1,10,'Organisation du projet de creation web','Organisation','2016-06-11 00:00:00','2017-07-01 00:00:00'),
        (2,1,10,'Première étapes du projet web','Commencement','2016-06-01 00:00:00','2017-12-01 00:00:00'),
        (3,1,10,'Projet sans equipes','Sans Equipe','2016-07-01 00:00:00','2017-09-01 00:00:00');
        
        INSERT INTO equipe (id_equipe,id_projet,id_createur,date_creation,resume) VALUES
        (1,1,1,'2016-07-01 00:00:00','première équipe'),
        (2,1,3,'2016-06-08 00:00:00','deuxième équipe'),
        (3,1,5,'2016-06-08 00:00:00','Troisième équipe'),
        (4,2,3,'2016-06-08 00:00:00','Equipe 4-2'),
        (5,2,2,'2016-06-08 00:00:00','Equipe 5-2');
        
        INSERT INTO membre_equipe(id_equipe,id_personne) VALUES
        (1,1),
        (1,2),
        (2,3),
        (2,4),
        (3,5),
        (3,6),
        (4,4),
        (4,5),
        (5,3),
        (5,6);
        
		COMMIT;
	END;
END§

DELIMITER §
DROP TRIGGER IF EXISTS trigger_insert_personne§
CREATE TRIGGER trigger_insert_personne
BEFORE INSERT ON personne
FOR EACH ROW
BEGIN
-- prénom capitalisé en INSERT
	SET NEW.prenom = INITCAP(trim(NEW.prenom));

-- nom en majuscule en INSERT
  SET NEW.nom = trim(MAJ(NEW.nom));
  
  -- adresse email sans espace en INSERT
   SET NEW.email = trim(NEW.email);
END§


DELIMITER §
DROP TRIGGER IF EXISTS trigger_update_personne§
CREATE TRIGGER trigger_update_personne
BEFORE UPDATE ON personne
FOR EACH ROW
BEGIN
 -- nom en majuscule en UPDATE
 SET NEW.nom = trim(MAJ(NEW.nom));

 -- adresse email sans espace en UPDATE
 SET NEW.email = trim(NEW.email);
END§

DROP TRIGGER IF EXISTS trigger_update_projet§
CREATE TRIGGER trigger_update_projet
BEFORE UPDATE ON projet
FOR EACH ROW
BEGIN
-- projet sans date de début en UPDATE
 SET NEW.date_creation = NOW();
END§

-- Peupler la base avec les données
CALL centre_formation_refresh()§