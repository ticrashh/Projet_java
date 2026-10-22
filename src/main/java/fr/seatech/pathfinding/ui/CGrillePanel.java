package fr.seatech.pathfinding.ui;

import fr.seatech.pathfinding.model.CPlateau;

import javafx.scene.canvas.Canvas;

/**
 * Zone de dessin du plateau : cases, obstacles, cases explorées, chemins
 * et personnages.
 */
public class CGrillePanel extends Canvas {

    private CPlateau plateau;

    /**
     * @param plateau le plateau à afficher
     */
    public CGrillePanel(CPlateau plateau) {
        this.plateau = plateau;
        // TODO [Étape 3] écouteurs souris pour éditer le plateau
    }

    /** @param plateau le nouveau plateau à afficher */
    public void setPlateau(CPlateau plateau) {
        this.plateau = plateau;
        dessiner();
    }

    /** Redessine la grille, les cases explorées, les chemins et les personnages. */
    public void dessiner() {
        // TODO [Étape 3] utiliser getGraphicsContext2D()
    }
}
