# CodingTest

Une application de surveillance des produits réalisée avec JavaEE et une base de données PostgreSQL.

L'application permet de visualiser une liste de capteurs et de produits présents dans la base de données et d'en ajouter, de les modifier et de les supprimer.

 - CodingTest_JavaEE : projet Netbeans permettant de lancer le serveur Web. La majorité des fichiers de développement (classes, servlets...) se trouvent dans l'archive Web (WAR)
Les informations relatives à la base de données PostgreSQL (URL de la base, nom d'utilisateur et mot de passe) sont à modifier dans le fichier conn.properties (CodingTest_JavaEE-war/src/java/model ou dans CodingTest_JavaEE-war/build/web/WEB-INF/classes/model pour ne pas avoir à recompiler)

La couche métiers de l'application a été réalisée avec le pattern DAO, ce qui permet de séparer les opérations effectuées sur les objets de leur intégration dans la base, et donc lors de modifications futures de faciliter l'implémentation de nouvelles fonctionnalités (par exemple on pourra ajouter à un objet Capteur les propriétés nécessaires à la lecture de ses informations dans la vraie vie, et donc pouvoir afficher ces informations dans l'application sans rien modifier d'autre que l'objet).

 - CodingTest_PostgreSQL : contient le fichier SQL à exécuter sur la base de données PostgreSQL afin de créer et initialiser les tables Capteur et Produit nécessaires à l'application. Attention, si des tables du même nom existent déjà elles seront effacées !
 
 Fonctionnalités non réalisées (par manque de temps) :
 
 - Design : aucun CSS n'est présent car la priorité a été donnée au back-end
 
 - Protection contre les injections de code
