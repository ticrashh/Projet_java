package fr.seatech.pathfinding.multiagent;

import fr.seatech.pathfinding.model.CPersonnage;
import fr.seatech.pathfinding.model.CPlateau;
import fr.seatech.pathfinding.model.CTrajectoire;

import java.util.List;
import java.util.Map;

/**
 * Modèle de planification coordonnée résolu avec Google OR-Tools (CP-SAT).
 * <p>
 * Variables envisagées : x[p][t][c] = 1 si le personnage p est sur la case c
 * au temps t. Contraintes : une case par personnage et par instant,
 * déplacements entre cases voisines, pas deux personnages sur la même case,
 * pas d'échange de cases (croisement). Objectif : minimiser la somme des
 * temps d'arrivée (ou le makespan).
 */
public class COptimiseurORTools {

    private final CPlateau plateau;
    private final int horizon;

    /**
     * @param plateau le plateau
     * @param horizon nombre maximal de pas de temps T
     */
    public COptimiseurORTools(CPlateau plateau, int horizon) {
        this.plateau = plateau;
        this.horizon = horizon;
    }

    /**
     * Construit le modèle CP-SAT, le résout et extrait les trajectoires.
     * <p>
     * Penser à appeler {@code com.google.ortools.Loader.loadNativeLibraries()}
     * une fois avant d'utiliser le solveur.
     *
     * @param personnages les personnages à planifier
     * @return la trajectoire de chaque personnage, ou {@code null} si aucune solution
     */
    public Map<CPersonnage, CTrajectoire> optimiser(List<CPersonnage> personnages) {
        // TODO [Étape 5] créer les variables, les contraintes et l'objectif
        return null;
    }

    /** @return l'horizon temporel T */
    public int getHorizon() {
        return horizon;
    }
}
