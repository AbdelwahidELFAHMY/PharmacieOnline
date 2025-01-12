# 📋 Système de Pharmacie en Ligne - Application Web

## Introduction
Ce dépôt contient le code source de l'application web pour le système de pharmacie en ligne. Cette application permet aux patients de soumettre leurs ordonnances et de suivre leurs commandes, tandis que les pharmaciens peuvent gérer les ordonnances reçues. L'application web est conçue pour une expérience utilisateur fluide, sécurisée et conforme aux normes en matière de données de santé.

---

## 🚀 Fonctionnalités Principales

### Fonctionnalités pour les Patients :
- **Création et gestion de compte** : Enregistrement et mise à jour des informations personnelles.
- **Soumission d'ordonnance** : Téléchargement d'une ordonnance sous forme d'image.
- **Carte des pharmacies** : Localisation des pharmacies partenaires, avec filtrage par nom, statut, distance et zone.
- **Suivi des commandes** : Consultation en temps réel du statut des commandes.
- **Historique des commandes** : Visualisation des ordonnances et commandes passées.
- **Notifications** : Réception de mises à jour sur le statut des commandes par email ou SMS.

### Fonctionnalités pour les Pharmaciens :
- **Gestion des ordonnances** : Consultation des ordonnances reçues des patients.
- **Mise à jour des commandes** : Notification des patients concernant le statut des commandes (en préparation, prête, etc.) et le montant total.

### Fonctionnalités pour l’Administrateur :
- **Gestion des utilisateurs** : Consultation et gestion des informations des utilisateurs.
- **Gestion des pharmacies** : Visualisation des pharmacies partenaires et modification de leur statut (active ou non active).

---

## 🔒 Sécurité et Confidentialité
- **Authentification et autorisation** : Gestion sécurisée des accès pour tous les utilisateurs.
- **Chiffrement des données** : Protection des informations personnelles et des images d'ordonnances.
- **Conformité** : Respect des réglementations locales concernant les données de santé.

---

## ⚙️ Technologies Utilisées
### Frontend :
- **Framework** : React.js
- **Structure MVC** : Utilisation de composants pour une interface modulaire.

### Backend :
- **Technologies** : Java, EJB, Servlets, JSP
- **Base de données** : MySQL

### Notifications :
- **Email et SMS** : Intégration avec des services tiers pour les mises à jour en temps réel.

### Serveur d’Application :
- **Serveur supporté** : Tomcat ou WildFly

---

## 🛠️ Installation et Déploiement

### Prérequis :
- Node.js et npm (ou yarn) installés sur votre machine.
- Serveur d'application (Tomcat ou WildFly) configuré.
- Base de données MySQL configurée avec les schémas nécessaires.

### Étapes d'installation :
1. Clonez le dépôt :
   ```bash
   git clone https://github.com/votre-utilisateur/votre-repository.git
   cd votre-repository
