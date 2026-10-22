package fr.seatech.pathfinding.multiagent;

import fr.seatech.pathfinding.algo.IAlgorithmeRecherche;
import fr.seatech.pathfinding.model.CPersonnage;
import fr.seatech.pathfinding.model.CPlateau;
import fr.seatech.pathfinding.model.CTrajectoire;

import java.util.List;
import java.util.Map;

/**
 * Planification des trajectoires de plusieurs personnages.
 * <ul>
 *   <li>Approche A : chaque personnage planifie seul, les conflits sont gérés
 *       pendant la simulation.</li>
 *   <li>Approche B : planification coordonnée globale avec OR-Tools.</li>
 * </ul>
 */
public class CPlanificateurMultiAgents {

    private final CPlateau plateau;

    /**
     * @param plateau le plateau commun à tous les personnages
     */
    public CPlanificateurMultiAgents(CPlateau plateau) {
        this.plateau = plateau;
    }

    /**
     * Approche A : planification indépendante de chaque personnage.
     *
     * @param personnages les personnages à planifier
     * @param algorithme  l'algorithme mono-agent utilisé
     * @return la trajectoire de chaque personnage
     */
    public Map<CPersonnage, CTrajectoire> planifierIndependant(List<CPersonnage> personnages,
                                                               IAlgorithmeRecherche algorithme) {
        // TODO [Étape 4]
        return null;
    }

    /**
     * Approche B : planification coordonnée via le solveur CP-SAT.
     *
     * @param personnages les personnages à planifier
     * @param horizon     nombre maximal de pas de temps
     * @return la trajectoire de chaque personnage, ou {@code null} si aucune solution
     */
    public Map<CPersonnage, CTrajectoire> planifierCoordonne(List<CPersonnage> personnages, int horizon) {
        // TODO [Étape 5] déléguer à COptimiseurORTools
        return null;
    }
}
