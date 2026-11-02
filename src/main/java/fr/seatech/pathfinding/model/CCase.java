package fr.seatech.pathfinding.model;

import java.util.Objects;

/**
 * Case du plateau, c'est-à-dire un sommet du graphe de déplacement.
 * <p>
 * Une case est identifiée par ses coordonnées (ligne, colonne). Son type
 * détermine si elle est traversable et son coût.
 */
public class CCase {

    private final int ligne;
    private final int colonne;
    private ETypeCase type;

    /**
     * Crée une case.
     *
     * @param ligne   indice de ligne (0 en haut)
     * @param colonne indice de colonne (0 à gauche)
     * @param type    type de la case
     */
    public CCase(int ligne, int colonne, ETypeCase type) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.type = type;
    }

    /** @return l'indice de ligne */
    public int getLigne() {
        return ligne;
    }

    /** @return l'indice de colonne */
    public int getColonne() {
        return colonne;
    }

    /** @return le type de la case */
    public ETypeCase getType() {
        return type;
    }

    /** @param type le nouveau type de la case (édition du plateau) */
    public void setType(ETypeCase type) {
        this.type = type;
    }

    /** @return le coût pour entrer dans cette case */
    public int getCout() {
        return type.getCout();
    }

    /** @return {@code true} si la case est traversable */
    public boolean estTraversable() {
        return type.estTraversable();
    }

    /** Deux cases sont égales si elles ont les mêmes coordonnées. */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CCase autre)) {
            return false;
        }
        return ligne == autre.ligne && colonne == autre.colonne;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ligne, colonne);
    }

    @Override
    public String toString() {
        return "(" + ligne + ", " + colonne + ")";
    }
}
