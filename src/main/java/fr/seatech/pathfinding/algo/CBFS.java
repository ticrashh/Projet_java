package fr.seatech.pathfinding.algo;

import fr.seatech.pathfinding.model.CCase;
import fr.seatech.pathfinding.model.CPlateau;

/**
 * Parcours en largeur (BFS) : plus court chemin en nombre de cases, sans tenir compte des coûts.
 */
public class CBFS implements IAlgorithmeRecherche {

    @Override
    public CResultatRecherche rechercher(CPlateau plateau, CCase depart, CCase arrivee) {
        // TODO [Étape 2] implémenter (file FIFO)
        return null;
    }

    @Override
    public String getNom() {
        return "BFS";
    }
}
