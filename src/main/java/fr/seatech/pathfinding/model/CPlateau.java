package fr.seatech.pathfinding.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Plateau de jeu : grille rectangulaire de cases, vue comme un graphe dont
 * les sommets sont les cases traversables et les arêtes relient les cases
 * voisines.
 */
public class CPlateau {

    /** Déplacements en 4-connexité : haut, bas, gauche, droite. */
    private static final int[][] DIRECTIONS_4 = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private final int nbLignes;
    private final int nbColonnes;
    private final CCase[][] cases;

    /**
     * Crée un plateau dont toutes les cases sont libres.
     *
     * @param nbLignes   nombre de lignes
     * @param nbColonnes nombre de colonnes
     */
    public CPlateau(int nbLignes, int nbColonnes) {
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        this.cases = new CCase[nbLignes][nbColonnes];
        for (int l = 0; l < nbLignes; l++) {
            for (int c = 0; c < nbColonnes; c++) {
                cases[l][c] = new CCase(l, c, ETypeCase.LIBRE);
            }
        }
    }

    /** @return le nombre de lignes */
    public int getNbLignes() {
        return nbLignes;
    }

    /** @return le nombre de colonnes */
    public int getNbColonnes() {
        return nbColonnes;
    }

    /**
     * @param ligne   indice de ligne
     * @param colonne indice de colonne
     * @return la case aux coordonnées données
     */
    public CCase getCase(int ligne, int colonne) {
        return cases[ligne][colonne];
    }

    /**
     * @param ligne   indice de ligne
     * @param colonne indice de colonne
     * @return {@code true} si les coordonnées sont dans le plateau
     */
    public boolean estDansPlateau(int ligne, int colonne) {
        return ligne >= 0 && ligne < nbLignes && colonne >= 0 && colonne < nbColonnes;
    }

    /**
     * Retourne les voisins traversables d'une case (4-connexité : haut, bas,
     * gauche, droite).
     *
     * @param c la case dont on cherche les voisins
     * @return la liste des cases voisines traversables
     */
    public List<CCase> getVoisins(CCase c) {
        List<CCase> voisins = new ArrayList<>();
        for (int[] d : DIRECTIONS_4) {
            int l = c.getLigne() + d[0];
            int col = c.getColonne() + d[1];
            if (estDansPlateau(l, col) && cases[l][col].estTraversable()) {
                voisins.add(cases[l][col]);
            }
        }
        return voisins;
    }

    /**
     * Charge un plateau depuis un fichier de ressources (dossier {@code maps/}).
     *
     * @param cheminRessource chemin de la ressource, par ex. {@code "/maps/plateau1.txt"}
     * @return le plateau chargé
     */
    public static CPlateau chargerDepuisRessource(String cheminRessource) {
        // TODO [Étape 1] définir le format de fichier et implémenter la lecture
        throw new UnsupportedOperationException("Non implémenté");
    }
}
