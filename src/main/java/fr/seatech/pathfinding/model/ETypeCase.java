package fr.seatech.pathfinding.model;

/**
 * Type d'une case du plateau, qui détermine si elle est traversable et son
 * coût de déplacement.
 */
public enum ETypeCase {

    /** Case libre, coût unitaire. */
    LIBRE(1, true),

    /** Obstacle infranchissable. */
    OBSTACLE(Integer.MAX_VALUE, false),

    /** Terrain difficile, traversable mais plus coûteux. */
    COUT_ELEVE(5, true);

    private final int cout;
    private final boolean traversable;

    ETypeCase(int cout, boolean traversable) {
        this.cout = cout;
        this.traversable = traversable;
    }

    /**
     * @return le coût pour entrer dans une case de ce type
     */
    public int getCout() {
        return cout;
    }

    /**
     * @return {@code true} si un personnage peut entrer dans une case de ce type
     */
    public boolean estTraversable() {
        return traversable;
    }
}
