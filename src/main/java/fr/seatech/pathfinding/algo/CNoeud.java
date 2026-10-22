package fr.seatech.pathfinding.algo;

import fr.seatech.pathfinding.model.CCase;

/**
 * Nœud de l'arbre de recherche : une case explorée, avec son coût depuis le
 * départ (g), son heuristique vers l'arrivée (h) et son nœud parent.
 */
public class CNoeud implements Comparable<CNoeud> {

    private final CCase caseAssociee;
    private int g;
    private int h;
    private CNoeud parent;

    /**
     * Crée un nœud.
     *
     * @param caseAssociee case représentée par ce nœud
     * @param g            coût du chemin depuis le départ
     * @param h            estimation du coût restant (0 pour BFS/Dijkstra)
     * @param parent       nœud précédent sur le chemin ({@code null} pour le départ)
     */
    public CNoeud(CCase caseAssociee, int g, int h, CNoeud parent) {
        this.caseAssociee = caseAssociee;
        this.g = g;
        this.h = h;
        this.parent = parent;
    }

    /** @return la case associée */
    public CCase getCase() {
        return caseAssociee;
    }

    /** @return le coût g */
    public int getG() {
        return g;
    }

    /** @param g le nouveau coût g */
    public void setG(int g) {
        this.g = g;
    }

    /** @return l'heuristique h */
    public int getH() {
        return h;
    }

    /** @return le nœud parent */
    public CNoeud getParent() {
        return parent;
    }

    /** @param parent le nouveau parent */
    public void setParent(CNoeud parent) {
        this.parent = parent;
    }

    /**
     * @return la priorité f = g + h
     */
    public int getF() {
        // TODO [Étape 2]
        return 0;
    }

    /**
     * Compare deux nœuds selon f (utilisé par la file de priorité).
     */
    @Override
    public int compareTo(CNoeud autre) {
        // TODO [Étape 2] comparer getF(), départager éventuellement par h
        return 0;
    }
}
