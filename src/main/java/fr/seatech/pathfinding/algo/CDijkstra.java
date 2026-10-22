package fr.seatech.pathfinding.algo;

import fr.seatech.pathfinding.model.CCase;
import fr.seatech.pathfinding.model.CPlateau;

/**
 * Algorithme de Dijkstra : plus court chemin pondéré par le coût des cases.
 */
public class CDijkstra implements IAlgorithmeRecherche {

    @Override
    public CResultatRecherche rechercher(CPlateau plateau, CCase depart, CCase arrivee) {
        // TODO [Étape 2] implémenter (file de priorité sur g)
        return null;
    }

    @Override
    public String getNom() {
        return "Dijkstra";
    }
}
