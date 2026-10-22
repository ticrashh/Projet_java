package fr.seatech.pathfinding.algo;

import fr.seatech.pathfinding.model.CCase;
import fr.seatech.pathfinding.model.CPlateau;

/**
 * Algorithme A* : recherche heuristique (distance de Manhattan) du plus court chemin pondéré.
 */
public class CAStar implements IAlgorithmeRecherche {

    @Override
    public CResultatRecherche rechercher(CPlateau plateau, CCase depart, CCase arrivee) {
        // TODO [Étape 2] implémenter (file de priorité sur f = g + h)
        return null;
    }

    /**
     * Heuristique : distance de Manhattan entre deux cases.
     *
     * @param a première case
     * @param b seconde case
     * @return l'estimation du coût entre a et b
     */
    private int heuristique(CCase a, CCase b) {
        // TODO [Étape 2]
        return 0;
    }

    @Override
    public String getNom() {
        return "A*";
    }
}
