package fr.seatech.pathfinding.ui;

import fr.seatech.pathfinding.model.CPlateau;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import fr.seatech.pathfinding.model.ETypeCase;


/**
 * Zone de dessin du plateau : cases, obstacles, cases explorées, chemins
 * et personnages.
 */
public class CGrillePanel extends Canvas {

    private CPlateau plateau;
    private static final int TAILLE = 30;

    /**
     * @param plateau le plateau à afficher
     */
    public CGrillePanel(CPlateau plateau) {
        this.plateau = plateau;
        setWidth(plateau.getNbColonnes() * TAILLE);
        setHeight(plateau.getNbLignes() * TAILLE);
        dessiner();
        // TODO [Étape 3] écouteurs souris pour éditer le plateau
    }

    /** @param plateau le nouveau plateau à afficher */
    public void setPlateau(CPlateau plateau) {
        this.plateau = plateau;
        dessiner();
    }

    /** Redessine la grille, les cases explorées, les chemins et les personnages. */
    public void dessiner() {
        GraphicsContext gc = getGraphicsContext2D();
        for (int l = 0; l < plateau.getNbLignes(); l++) {
            for (int c = 0; c < plateau.getNbColonnes(); c++) {
                gc.setFill(couleur(plateau.getCase(l, c).getType()));
                gc.fillRect(c * TAILLE, l * TAILLE, TAILLE, TAILLE);
                gc.setStroke(Color.LIGHTGRAY);
                gc.strokeRect(c * TAILLE, l * TAILLE, TAILLE, TAILLE);
            }
        }
        // TODO [Étape 3] utiliser getGraphicsContext2D()
    }

    /**
 * Couleur d'affichage d'un type de case.
 *
    @param t le type de case
    @return la couleur correspondante
 */
    private Color couleur(ETypeCase t) {
        return switch (t) {
            case LIBRE -> Color.WHITE;
            case OBSTACLE -> Color.BLACK;
            case COUT_ELEVE -> Color.BURLYWOOD;
        };
    }
}
