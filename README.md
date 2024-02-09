# Documentation README - LabXpert

La documentation de LabXpert. Ce document fournit un aperçu de haut niveau du système LabXpert, en mettant particulièrement l'accent sur la sécurité et les technologies utilisées.

---

## Contexte du Projet

LabXpert est un système de gestion d'analyses médicales conçu pour garantir la confidentialité, l'intégrité et la disponibilité des données médicales. La sécurité est une priorité essentielle dans ce contexte. Pour renforcer la sécurité du système, LabXpert utilise des technologies modernes telles que Spring Boot Security avec OAuth 2.0.

## Sécurité dans LabXpert

### Authentification des Utilisateurs avec OAuth 2.0

LabXpert utilise OAuth 2.0 pour permettre aux utilisateurs de s'authentifier de manière sécurisée. Ce protocole assure que seuls les utilisateurs autorisés peuvent accéder aux fonctionnalités du système.

### Gestion des Utilisateurs, des Rôles et des Autorisations

LabXpert dispose d'un système de gestion des utilisateurs avec différents rôles (techniciens, responsables de laboratoire, administrateurs, etc.), chacun ayant des autorisations spécifiques. L'interface d'administration simplifie la gestion de ces rôles et autorisations.

### Token JWT (JSON Web Token)

LabXpert utilise des tokens JWT pour renforcer la sécurité. Ces tokens, générés lors de l'authentification, sont inclus dans chaque requête subséquente, éliminant ainsi le besoin de stocker des informations d'authentification côté serveur.

### OAuth 2.0 pour la Sécurité au Niveau des Services

L'implémentation d'OAuth 2.0 au niveau des services garantit que seuls les utilisateurs autorisés peuvent effectuer des opérations spécifiques. Cette approche granulaire renforce la sécurité du système.

### Intégration d'OAuth 2.0 avec JWT

LabXpert combine OAuth 2.0 avec JWT pour une double couche de sécurité, assurant une authentification solide et la délégation sécurisée des autorisations.

## Stack Technique

- Langage de Programmation : Java
- Backend : Spring Boot API RESTful avec Spring Security et OAuth2
- Gestion de Dépendances : Apache Maven
- Base de Données : PostgreSQL
- Serveur d'Application : Apache Tomcat
- Testing : JUnit & Mockito
- Sécurité : Spring Security (Authentification, Autorisation, OAuth2, JWT)
- CICD : Jenkins
- Gestion des Tâches : Jira
- Système de Gestion de Version : Git et Github
- Documentation de l'API : Swagger
- Logging : Log4j

