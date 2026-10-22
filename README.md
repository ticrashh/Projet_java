# Projet Java : planification de trajectoires

Projet Java SeaTech (IRIS 3A, 2026-2027), sujet 2 : recherche de chemins sur
un plateau en grille (BFS, Dijkstra, A*), simulation multi-personnages avec
détection de conflits, et planification coordonnée avec Google OR-Tools (CP-SAT).

## Prérequis

- JDK 17 ou plus (JDK 21 conseillé, JavaFX 21 est téléchargé par Maven)
- Maven 3.9+ (ou le Maven intégré à IntelliJ / VS Code)

## Commandes

| Action | Commande |
|---|---|
| Compiler | `mvn compile` |
| Lancer l'application | `mvn javafx:run` |
| Lancer les tests | `mvn test` |
| Générer la Javadoc (dans `doc/`) | `mvn javadoc:javadoc` |
| Construire le jar | `mvn package` |

## Organisation du code

| Paquetage | Contenu | Responsable |
|---|---|---|
| `model` | Plateau, cases, personnages, trajectoires | Commun (étape 1) |
| `algo` | BFS, Dijkstra, A* | Membre A (étape 2) |
| `ui` | Interface JavaFX (+ `CApplication`, point d'entrée) | Membre B (étape 3) |
| `simulation` | Moteur temporel, threads, conflits | Membre B (étape 4) |
| `multiagent` | Approches A et B, OR-Tools | Membre A (étapes 4 et 5) |

Les plateaux de test vont dans `src/main/resources/maps/`, les fichiers SceneBuilder dans `src/main/resources/fxml/`, le rapport et les
schémas dans `rapport/`.

## Utilisation de l'IA

L'énoncé autorise l'IA à condition de le signaler dans le code et dans le
rapport. Noter ici chaque usage, et ajouter un commentaire
`// Généré/aidé par IA : ...` dans le code concerné.

| Date | Outil | Prompt (résumé) | Fichiers concernés | Ce qui a été repris / modifié |
|---|---|---|---|---|
| 2026-10-22 | Claude | Création du squelette Maven (dossiers, classes vides, pom.xml) | Tout le squelette | Structure reprise telle quelle, aucune logique générée |
