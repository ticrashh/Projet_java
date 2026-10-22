package fr.seatech.pathfinding.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Trajectoire d'un personnage : suite ordonnée de cases, de la case de
 * départ à la case d'arrivée, avec son coût total.
 * <p>
 * L'indice d'une case dans la liste correspond au pas de temps auquel le
 * personnage s'y trouve (utile pour la simulation et OR-Tools).
 */
public class CTrajectoire {

    private final List<CCase> cases = new ArrayList<>();
    private int coutTotal;

    /** Crée une trajectoire vide. */
    public CTrajectoire() {
        this.coutTotal = 0;
    }

    /**
     * Ajoute une case en fin de trajectoire et met à jour le coût total.
     *
     * @param c la case à ajouter
     */
    public void ajouterCase(CCase c) {
        // TODO [Étape 1] ajouter la case et mettre à jour coutTotal
    }

    /** @return la liste (non modifiable) des cases de la trajectoire */
    public List<CCase> getCases() {
        return Collections.unmodifiableList(cases);
    }

    /**
     * @param t pas de temps
     * @return la case occupée au temps {@code t} (la dernière case si {@code t} dépasse la fin)
     */
    public CCase getCaseAuTemps(int t) {
        // TODO [Étape 1]
        return null;
    }

    /** @return le nombre de cases de la trajectoire */
    public int getLongueur() {
        return cases.size();
    }

    /** @return le coût total de la trajectoire */
    public int getCoutTotal() {
        return coutTotal;
    }

    /** @return {@code true} si la trajectoire ne contient aucune case */
    public boolean estVide() {
        return cases.isEmpty();
    }
}
