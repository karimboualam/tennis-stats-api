# 🎾 Tennis Stats API

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=karimboualam_tennis-stats-api&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=karimboualam_tennis-stats-api)
![Build](https://img.shields.io/badge/build-passing-brightgreen)
![Coverage](https://img.shields.io/badge/coverage-91%25-brightgreen)
![Checkstyle](https://img.shields.io/badge/checkstyle-clean-blue)
![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.3.4-brightgreen)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](./LICENSE)

---

## 📑 Sommaire
- [🧩 Description](#-description)
- [🧱 Architecture du projet](#-architecture-du-projet)
- [🧠 Bonnes pratiques et conception](#-bonnes-pratiques-et-conception)
- [⚙️ Installation & Exécution locale](#️-installation--exécution-locale)
- [🧪 Tests & Qualité](#-tests-et-qualité)
- [☁️ Déploiement Render](#️-déploiement-render)
- [📊 SonarCloud Dashboard](#-sonarcloud-dashboard)
- [🧠 Stack technique](#-stack-technique)
- [📄 Documentation & Rapports](#-documentation--rapports)
- [👨‍💻 Auteur](#-auteur)

---

## 🧩 Description

**Tennis Stats API** est une application **Spring Boot 3 / Java 21** développée dans le cadre du **test technique L’Atelier**.  
Elle expose une **API RESTful** permettant de :

- 🎾 Consulter la liste des joueurs triés par classement (1 = meilleur)  
- 🔍 Rechercher un joueur par son identifiant  
- ➕ Créer un nouveau joueur  
- 📊 Obtenir des statistiques globales :  
  - 🏳️ Pays avec le meilleur ratio de victoires  
  - ⚖️ IMC moyen de tous les joueurs  
  - 📏 Taille médiane des joueurs  

L’application suit les **principes SOLID**, une **architecture claire en couches**, et inclut des outils de **qualité logicielle (SonarQube, Checkstyle, JaCoCo)** ainsi qu’un **déploiement automatisé sur Render**.

---

## 🧱 Architecture du projet

```text
tennis-stats-api
┣ src
┃ ┣ main/java/com/atelier/tennis
┃ ┃ ┣ config/ → Configuration Spring (Swagger, DataLoader)
┃ ┃ ┣ controller/ → Endpoints REST (Player, Stats)
┃ ┃ ┣ dto/ → Objets de transfert (PlayerDTO, StatsResponseDTO)
┃ ┃ ┣ entity/ → Entités JPA (Player, Country, Stats)
┃ ┃ ┣ exception/ → Gestion globale des erreurs REST
┃ ┃ ┣ mapper/ → Conversions Entity ↔ DTO
┃ ┃ ┣ repository/ → Accès base (Spring Data JPA)
┃ ┃ ┣ service/ → Logique métier (PlayerService, StatsService)
┃ ┃ ┗ util/ → Fonctions utilitaires (MathUtils)
┃ ┗ resources/
┃ ┃ ┣ application.yml
┃ ┃ ┗ data/headtohead.json (données de test)
┗ test/java/com/atelier/tennis
  ┣ controller/ → Tests unitaires REST
  ┣ service/ → Tests unitaires métier
  ┗ integration/ → Tests d’intégration complets

``` 

---

## 🧠 Bonnes pratiques et conception

- Respect des **principes SOLID**
- Architecture **Controller → Service → Repository**
- **DTOs** pour séparer les entités JPA du modèle exposé
- **Validation** des données via `jakarta.validation`
- **Gestion centralisée des exceptions** via `GlobalExceptionHandler`
- **Couverture de test élevée** (unitaires + intégration)
- **JavaDoc complète** pour toutes les classes publiques
- **Qualité vérifiée** via SonarQube, JaCoCo et Checkstyle

---

## ⚙️ Installation & Exécution locale

### 1️⃣ Cloner le projet

git clone https://github.com/karimboualam/tennis-stats-api.git
&& cd tennis-stats-api


### 2️⃣ Lancer l’application avec H2

mvn clean spring-boot:run

Par défaut, le profil actif est dev.
L’application démarre sur :
👉 http://localhost:8081

### 3️⃣ Accéder à la console H2

👉 http://localhost:8081/h2-console

JDBC URL : jdbc:h2:mem:tennisdb

Username : sa

Password : (vide)

---

### 📚 Endpoints principaux

| Méthode | URL                 | Description                                 |
| ------- | ------------------- | ------------------------------------------- |
| `GET`   | `/api/players`      | Liste tous les joueurs triés par classement |
| `GET`   | `/api/players/{id}` | Récupère un joueur spécifique               |
| `POST`  | `/api/players`      | Crée un nouveau joueur                      |
| `GET`   | `/api/stats`        | Renvoie les statistiques globales           |

---

### 📘 Documentation interactive (Swagger) :
👉 http://localhost:8081/swagger-ui.html

🧪 Tests et qualité du code
Lancer les tests unitaires et d’intégration :

mvn clean verify

---

### Rapports générés automatiquement :


| Type                | Outil                   | Commande               | Rapport                                        |
| ------------------- | ----------------------- | ---------------------- | ---------------------------------------------- |
| ✅ Couverture        | **JaCoCo**              | `mvn verify`           | `target/site/jacoco/index.html`                |
| 🎯 Style            | **Checkstyle (Google)** | `mvn checkstyle:check` | `target/checkstyle-result.xml`                 |
| 🧩 Analyse statique | **SonarQube**           | `mvn sonar:sonar`      | [http://localhost:9000](http://localhost:9000) |

---

### 🧩 Tests d’intégration

Le test PlayerIntegrationTest vérifie le flux complet de l’application :

Création d’un joueur (POST /api/players)

Lecture de tous les joueurs (GET /api/players)

Lecture d’un joueur par ID (GET /api/players/{id})

Calcul des statistiques (GET /api/stats)

✅ Ces tests utilisent la base H2 en mémoire, couvrant :

Controller → Service → Repository

---

### 📘 Documentation JavaDoc

La documentation complète du projet est disponible ici :
👉 Tennis Stats API – JavaDoc

---

### ☁️ Déploiement Render (Cloud)


🔗 **Démo en ligne :** [https://tennis-stats-api.onrender.com](https://tennis-stats-api.onrender.com)


Étapes de déploiement :

Connexion du repo GitHub à Render

Configuration du service Web

Build command :

mvn clean package -DskipTests


Start command :

java -jar target/tennis-stats-api-1.0.0.jar


Profil actif : prod

Base de données : PostgreSQL (hébergée)

---

### 🔁 Intégration Continue (CI/CD)

Un pipeline Maven est configuré pour :

🧪 Lancer les tests unitaires

✅ Vérifier le style de code (Checkstyle)

📊 Générer la couverture JaCoCo

📈 Publier les résultats SonarCloud

☁️ Déployer automatiquement sur Render

Exemple de workflow GitHub Actions :

# .github/workflows/maven.yml
```

name: CI Build
on: [push]
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: Set up JDK
        uses: actions/setup-java@v3
        with:
          java-version: '21'
          distribution: 'temurin'
      - name: Build & Test
        run: mvn clean verify
      - name: SonarCloud Analysis
        run: mvn sonar:sonar -Dsonar.login=${{ secrets.SONAR_TOKEN }}
```

---

### 📊 SonarCloud Dashboard


🔗 **Analyse qualité :**
[https://sonarcloud.io/summary/new_code?id=karimboualam_tennis-stats-api](https://sonarcloud.io/summary/new_code?id=karimboualam_tennis-stats-api)

| Métrique              | Description                            |
| --------------------- | -------------------------------------- |
| 🧩 Bugs & Code Smells | Analyse statique du code               |
| 🧪 Test Coverage      | Taux de couverture des tests unitaires |
| 🧹 Duplications       | Détection de code redondant            |
| 🔒 Security Hotspots  | Vérification de la sécurité du code    |

---

### 🧠 Stack technique

| Technologie                | Rôle                             |
| -------------------------- | -------------------------------- |
| **Java 21**                | Langage principal                |
| **Spring Boot 3.3.4**      | Framework backend                |
| **Spring Data JPA**        | Accès aux données                |
| **H2 / PostgreSQL**        | Base de données (dev/prod)       |
| **Swagger / OpenAPI**      | Documentation REST               |
| **JUnit 5 + MockMvc**      | Tests unitaires et d’intégration |
| **JaCoCo**                 | Couverture des tests             |
| **Checkstyle (Google)**    | Analyse du style de code         |
| **SonarQube / SonarCloud** | Analyse qualité et sécurité      |
| **Render**                 | Déploiement cloud                |
| **GitHub Actions**         | CI/CD                            |

---

### 📄 Documentation & rapports

| Type          | Accès                                                                          |
| ------------- | ------------------------------------------------------------------------------ |
| 📘 Swagger UI | [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html) |
| 🧠 JavaDoc    | [Tennis Stats API – JavaDoc](https://karimboualam.github.io/tennis-stats-api/) |
| 🧪 SonarCloud | [https://sonarcloud.io/summary/new_code?id=karimboualam_tennis-stats-api](https://sonarcloud.io/summary/new_code?id=karimboualam_tennis-stats-api)                                                                |
| 📈 JaCoCo     | `target/site/jacoco/index.html`                                                |

---

## 👨‍💻 Auteur

**Karim Boualam**  
💼 Ingénieur Full Stack & DevOps  

🎓 **Formations :**  
- 2024–2025 : **DU Big Data / Analyse des Risques – Python**, Université de Montpellier (34), France  
- 2022–2024 : **Master Informatique**, Université de Montpellier (34), France  
- 2022–2024 : **Master en double diplôme – Management des Technologies et des Sciences**, IAE de Montpellier (34), France  

📧 **Contact :** [karim.boualam.2@gmail.com](mailto:karim.boualam.2@gmail.com)  
🌐 **GitHub :** [karimboualam](https://github.com/karimboualam)  

---

🧾 **Licence : [MIT](./LICENSE)**  
