INSERT INTO question (id, texte) VALUES (1, "Quel sont les trois grands principes de la POO ?");
INSERT INTO question (id, texte) VALUES (2, "Quel interface implémente la classe ArrayList ?");

INSERT INTO reponse (texte, question_id) VALUES ("L'encapsulation, l'héritage et le polymorphisme.", 1);
INSERT INTO reponse (texte, question_id) VALUES ("L'encapsulation, l'héritage multiple et le polymorphisme.", 1);
INSERT INTO reponse (texte, question_id) VALUES ("Le multi-threading, l'accès aux données et le polymorphisme.", 1);

INSERT INTO reponse (texte, question_id) VALUES ("List", 2);
INSERT INTO reponse (texte, question_id) VALUES ("Queue", 2);
INSERT INTO reponse (texte, question_id) VALUES ("Serializable", 2);
