package fr.seatech.pathfinding.ui;

import fr.seatech.pathfinding.model.CCase;
import fr.seatech.pathfinding.model.CPlateau;
import fr.seatech.pathfinding.model.CTrajectoire;
import fr.seatech.pathfinding.model.ETypeCase;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Zone de dessin du plateau : cases, obstacles, cases explorées, chemins
 * et personnages.
 */
public class CGrillePanel extends Canvas {

    private static final int TAILLE = 30;
    private static final Color COULEUR_EXPLOREE = Color.rgb(173, 216, 230);
    private static final Color COULEUR_CHEMIN = Color.GOLD;
    private static final Color COULEUR_DEPART = Color.LIMEGREEN;
    private static final Color COULEUR_ARRIVEE = Color.CRIMSON;

    private CPlateau plateau;
    private CCase depart;
    private CCase arrivee;
    private List<CCase> casesExplorees = new ArrayList<>();
    private int nbExploreesAffichees;
    private List<CCase> chemin = new ArrayList<>();

    /**
     * @param plateau le plateau à afficher
     */
    public CGrillePanel(CPlateau plateau) {
        setPlateau(plateau);
        // TODO [Étape 3] écouteurs souris pour éditer le plateau
    }

    /** @param plateau le nouveau plateau à afficher */
    public void setPlateau(CPlateau plateau) {
        this.plateau = plateau;
        setWidth(plateau.getNbColonnes() * TAILLE);
        setHeight(plateau.getNbLignes() * TAILLE);
        effacerRecherche();
    }

    /** @param depart la case de départ à afficher */
    public void setDepart(CCase depart) {
        this.depart = depart;
        dessiner();
    }

    /** @param arrivee la case d'arrivée à afficher */
    public void setArrivee(CCase arrivee) {
        this.arrivee = arrivee;
        dessiner();
    }

    /**
     * Affiche les premières cases explorées d'une recherche (utilisé pour
     * l'animation).
     *
     * @param explorees     cases explorées, dans l'ordre d'exploration
     * @param nbAffichees   nombre de cases à afficher depuis le début de la liste
     */
    public void afficherExploration(List<CCase> explorees, int nbAffichees) {
        this.casesExplorees = explorees;
        this.nbExploreesAffichees = Math.min(nbAffichees, explorees.size());
        dessiner();
    }

    /**
     * Affiche le chemin trouvé.
     *
     * @param trajectoire le chemin ({@code null} ou vide si aucun)
     */
    public void afficherChemin(CTrajectoire trajectoire) {
        this.chemin = trajectoire == null ? new ArrayList<>() : trajectoire.getCases();
        dessiner();
    }

    /** Efface les cases explorées et le chemin. */
    public void effacerRecherche() {
        casesExplorees = new ArrayList<>();
        nbExploreesAffichees = 0;
        chemin = new ArrayList<>();
        dessiner();
    }

    /** Redessine la grille, les cases explorées, les chemins et les personnages. */
    public void dessiner() {
        GraphicsContext gc = getGraphicsContext2D();
        for (int l = 0; l < plateau.getNbLignes(); l++) {
            for (int c = 0; c < plateau.getNbColonnes(); c++) {
                remplir(gc, plateau.getCase(l, c), couleur(plateau.getCase(l, c).getType()));
            }
        }
        for (int i = 0; i < nbExploreesAffichees; i++) {
            remplir(gc, casesExplorees.get(i), COULEUR_EXPLOREE);
        }
        for (CCase c : chemin) {
            remplir(gc, c, COULEUR_CHEMIN);
        }
        if (depart != null) {
            remplir(gc, depart, COULEUR_DEPART);
        }
        if (arrivee != null) {
            remplir(gc, arrivee, COULEUR_ARRIVEE);
        }
    }

    /** Colore une case et trace son contour. */
    private void remplir(GraphicsContext gc, CCase c, Color couleur) {
        double x = c.getColonne() * TAILLE;
        double y = c.getLigne() * TAILLE;
        gc.setFill(couleur);
        gc.fillRect(x, y, TAILLE, TAILLE);
        gc.setStroke(Color.LIGHTGRAY);
        gc.strokeRect(x, y, TAILLE, TAILLE);
    }

    /**
     * Couleur d'affichage d'un type de case.
     *
     * @param t le type de case
     * @return la couleur correspondante
     */
    private Color couleur(ETypeCase t) {
        return switch (t) {
            case LIBRE -> Color.WHITE;
            case OBSTACLE -> Color.BLACK;
            case COUT_ELEVE -> Color.BURLYWOOD;
        };
    }
}
