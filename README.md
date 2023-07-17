# Setup

Les services :
- reservation
- user
- vehicule

utilisent MYSQL. Il vous faut donc un serveur mysql sur le port 3306 et ajouter vos identifiant de mysql dans le fichier application properties.

```
spring.datasource.url=jdbc:mysql://localhost:3306/user_service
// le nom des bases par default est après :3306/"Le nom par default"
spring.datasource.username="Votre user name"
spring.datasource.password="Votre mot de passe"
```

Créer les différentes bases de données:
- reservation_service
- vehicle_service
- user_service

Des données sont déja fake pour les modifiers il faut changer les valeurs dans le fichier data.sql disponible dans le dossier resources de chaque service.

Pour un les services dans un terminal ouvert à la racine du projet run  
`mvn spring-boot: run`