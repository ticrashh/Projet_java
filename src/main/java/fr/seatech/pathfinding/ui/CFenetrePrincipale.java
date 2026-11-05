package fr.seatech.pathfinding.ui;

import fr.seatech.pathfinding.algo.CAStar;
import fr.seatech.pathfinding.algo.CBFS;
import fr.seatech.pathfinding.algo.CDijkstra;
import fr.seatech.pathfinding.algo.CResultatRecherche;
import fr.seatech.pathfinding.algo.IAlgorithmeRecherche;
import fr.seatech.pathfinding.model.CCase;
import fr.seatech.pathfinding.model.CPlateau;
import fr.seatech.pathfinding.model.ETypeCase;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Contenu de la fenêtre principale : la grille au centre, le panneau de
 * résultats sur le côté, les menus et les commandes (choix de l'algorithme,
 * lancement, pause...).
 */
public class CFenetrePrincipale extends BorderPane {

    /** Délai entre l'affichage de deux cases explorées, en millisecondes. */
    private static final double DELAI_ANIMATION_MS = 15;

    private CPlateau plateau;
    private CCase depart;
    private CCase arrivee;

    private CGrillePanel grillePanel;
    private CResultatsPanel resultatsPanel;
    private MenuBar menuBar;
    private ComboBox<IAlgorithmeRecherche> choixAlgorithme;
    private Button boutonCalculer;
    private Button boutonEffacer;
    private Timeline animation;

    /** Crée le contenu de la fenêtre principale. */
    public CFenetrePrincipale() {
        plateau = creerPlateauDemo();
        depart = plateau.getCase(0, 0);
        arrivee = plateau.getCase(plateau.getNbLignes() - 1, plateau.getNbColonnes() - 1);
        initialiserComposants();
        creerMenus();
        setTop(new VBox(menuBar, creerBarreOutils()));
    }

    /** Crée et place les panneaux. */
    private void initialiserComposants() {
        grillePanel = new CGrillePanel(plateau);
        grillePanel.setDepart(depart);
        grillePanel.setArrivee(arrivee);
        resultatsPanel = new CResultatsPanel();
        setCenter(grillePanel);
        setRight(resultatsPanel);
    }

    /** Crée la barre de menus. */
    private void creerMenus() {
        Menu menuFichier = new Menu("Fichier");
        MenuItem itemQuitter = new MenuItem("Quitter");
        itemQuitter.setOnAction(e -> Platform.exit());
        menuFichier.getItems().add(itemQuitter);

        Menu menuAlgorithme = new Menu("Algorithme");
        MenuItem itemCalculer = new MenuItem("Calculer le chemin");
        itemCalculer.setOnAction(e -> lancerRecherche());
        MenuItem itemEffacer = new MenuItem("Effacer le résultat");
        itemEffacer.setOnAction(e -> effacerRecherche());
        menuAlgorithme.getItems().addAll(itemCalculer, itemEffacer);

        Menu menuSimulation = new Menu("Simulation");
        // TODO [Étape 4] lancer, pause, reprise, pas-à-pas

        menuBar = new MenuBar(menuFichier, menuAlgorithme, menuSimulation);
    }

    /**
     * Crée la barre d'outils : choix de l'algorithme et boutons de calcul.
     *
     * @return la barre d'outils
     */
    private ToolBar creerBarreOutils() {
        choixAlgorithme = new ComboBox<>();
        choixAlgorithme.getItems().addAll(new CBFS(), new CDijkstra(), new CAStar());
        choixAlgorithme.setCellFactory(liste -> new CelluleAlgorithme());
        choixAlgorithme.setButtonCell(new CelluleAlgorithme());
        choixAlgorithme.getSelectionModel().selectFirst();

        boutonCalculer = new Button("Calculer");
        boutonCalculer.setOnAction(e -> lancerRecherche());

        boutonEffacer = new Button("Effacer");
        boutonEffacer.setOnAction(e -> effacerRecherche());

        return new ToolBar(new Label("Algorithme :"), choixAlgorithme, boutonCalculer, boutonEffacer);
    }

    /**
     * Lance l'algorithme choisi dans un thread séparé pour ne pas figer
     * l'interface, puis anime le résultat.
     */
    private void lancerRecherche() {
        IAlgorithmeRecherche algorithme = choixAlgorithme.getValue();
        if (algorithme == null) {
            return;
        }
        arreterAnimation();
        grillePanel.effacerRecherche();
        resultatsPanel.afficherMessage("Calcul en cours...");
        setCommandesActives(false);

        Task<CResultatRecherche> tache = new Task<>() {
            @Override
            protected CResultatRecherche call() {
                return algorithme.rechercher(plateau, depart, arrivee);
            }
        };
        // setOnSucceeded et setOnFailed sont exécutés dans le thread JavaFX
        tache.setOnSucceeded(e -> animerResultat(tache.getValue()));
        tache.setOnFailed(e -> {
            resultatsPanel.afficherMessage("Erreur : " + tache.getException().getMessage());
            setCommandesActives(true);
        });

        Thread thread = new Thread(tache, "recherche-" + algorithme.getNom());
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * Affiche les cases explorées une par une, puis le chemin et les métriques.
     *
     * @param resultat le résultat de la recherche
     */
    private void animerResultat(CResultatRecherche resultat) {
        int nbExplorees = resultat.getNbCasesExplorees();
        int[] compteur = {0};

        animation = new Timeline(new KeyFrame(Duration.millis(DELAI_ANIMATION_MS), e -> {
            compteur[0]++;
            grillePanel.afficherExploration(resultat.getCasesExplorees(), compteur[0]);
        }));
        animation.setCycleCount(Math.max(nbExplorees, 1));
        animation.setOnFinished(e -> {
            grillePanel.afficherChemin(resultat.getChemin());
            resultatsPanel.afficherResultat(resultat);
            setCommandesActives(true);
        });
        animation.play();
    }

    /** Efface le résultat affiché sur la grille et dans le panneau. */
    private void effacerRecherche() {
        arreterAnimation();
        grillePanel.effacerRecherche();
        resultatsPanel.effacer();
        setCommandesActives(true);
    }

    /** Arrête l'animation en cours, s'il y en a une. */
    private void arreterAnimation() {
        if (animation != null && animation.getStatus() == Animation.Status.RUNNING) {
            animation.stop();
        }
    }

    /** Active ou désactive les commandes pendant un calcul. */
    private void setCommandesActives(boolean actives) {
        boutonCalculer.setDisable(!actives);
        choixAlgorithme.setDisable(!actives);
    }

    /**
     * Crée un plateau de démonstration en attendant le chargement depuis
     * les fichiers de {@code maps/}.
     *
     * @return un plateau 15 x 20 avec un mur et une zone de terrain difficile
     */
    private CPlateau creerPlateauDemo() {
        CPlateau p = new CPlateau(15, 20);
        // Mur vertical avec un passage en bas
        for (int l = 0; l < 12; l++) {
            p.getCase(l, 8).setType(ETypeCase.OBSTACLE);
        }
        // Mur horizontal
        for (int c = 11; c < 18; c++) {
            p.getCase(6, c).setType(ETypeCase.OBSTACLE);
        }
        // Zone de terrain difficile
        for (int l = 9; l < 13; l++) {
            for (int c = 12; c < 16; c++) {
                p.getCase(l, c).setType(ETypeCase.COUT_ELEVE);
            }
        }
        return p;
    }

    /** Affiche le nom de l'algorithme dans la liste déroulante. */
    private static class CelluleAlgorithme extends ListCell<IAlgorithmeRecherche> {
        @Override
        protected void updateItem(IAlgorithmeRecherche algorithme, boolean vide) {
            super.updateItem(algorithme, vide);
            setText(vide || algorithme == null ? null : algorithme.getNom());
        }
    }
}
