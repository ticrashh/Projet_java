package fr.seatech.pathfinding.algo;

import fr.seatech.pathfinding.model.CCase;
import fr.seatech.pathfinding.model.CPlateau;

/**
 * Interface commune aux algorithmes de recherche de chemin mono-agent
 * (BFS, Dijkstra, A*).
 */
public interface IAlgorithmeRecherche {

    /**
     * Recherche un chemin entre deux cases du plateau.
     *
     * @param plateau le plateau sur lequel chercher
     * @param depart  case de départ
     * @param arrivee case d'arrivée
     * @return le résultat de la recherche (chemin éventuel et métriques)
     */
    CResultatRecherche rechercher(CPlateau plateau, CCase depart, CCase arrivee);

    /**
     * @return le nom de l'algorithme, affiché dans l'interface
     */
    String getNom();
}
