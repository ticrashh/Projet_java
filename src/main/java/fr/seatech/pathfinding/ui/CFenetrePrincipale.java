package fr.seatech.pathfinding.ui;

import fr.seatech.pathfinding.model.CPlateau;
import fr.seatech.pathfinding.model.ETypeCase;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

/**
 * Contenu de la fenêtre principale : la grille au centre, le panneau de
 * résultats sur le côté, les menus et les commandes (choix de l'algorithme,
 * lancement, pause...).
 */
public class CFenetrePrincipale extends BorderPane {

    private CPlateau plateau;
    private CGrillePanel grillePanel;
    private CResultatsPanel resultatsPanel;

    /** Crée le contenu de la fenêtre principale. */
    public CFenetrePrincipale() {
        plateau = creerPlateauDemo();
        initialiserComposants();
        creerMenus();
    }

    /** Crée et place les panneaux. */
    private void initialiserComposants() {
        grillePanel = new CGrillePanel(plateau);
        resultatsPanel = new CResultatsPanel();
        setCenter(grillePanel);
        setRight(resultatsPanel);
    }

    /** Crée la barre de menus et les boutons de commande. */
    private void creerMenus() {
        Menu menuFichier = new Menu("Fichier");
        MenuItem itemQuitter = new MenuItem("Quitter");
        itemQuitter.setOnAction(e -> Platform.exit());
        menuFichier.getItems().add(itemQuitter);

        Menu menuAlgorithme = new Menu("Algorithme");
        // TODO [Étape 3] choix BFS / Dijkstra / A*

        Menu menuSimulation = new Menu("Simulation");
        // TODO [Étape 4] lancer, pause, reprise, pas-à-pas

        MenuBar menuBar = new MenuBar(menuFichier, menuAlgorithme, menuSimulation);
        setTop(menuBar);
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
}
