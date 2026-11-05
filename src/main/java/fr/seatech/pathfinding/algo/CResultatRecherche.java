package fr.seatech.pathfinding.algo;

import fr.seatech.pathfinding.model.CCase;
import fr.seatech.pathfinding.model.CTrajectoire;

import java.util.List;

/**
 * Résultat d'une recherche de chemin et métriques associées, utilisées pour
 * l'affichage et la comparaison des algorithmes.
 */
public class CResultatRecherche {

    private final String nomAlgorithme;
    private final CTrajectoire chemin;
    private final List<CCase> casesExplorees;
    private final long tempsExecutionNs;

    /**
     * Crée un résultat.
     *
     * @param nomAlgorithme    nom de l'algorithme utilisé
     * @param chemin           chemin trouvé ({@code null} ou vide si aucun)
     * @param casesExplorees   cases visitées, dans l'ordre d'exploration
     * @param tempsExecutionNs durée de la recherche en nanosecondes
     */
    public CResultatRecherche(String nomAlgorithme, CTrajectoire chemin,
                              List<CCase> casesExplorees, long tempsExecutionNs) {
        this.nomAlgorithme = nomAlgorithme;
        this.chemin = chemin;
        this.casesExplorees = casesExplorees;
        this.tempsExecutionNs = tempsExecutionNs;
    }

    /** @return le nom de l'algorithme */
    public String getNomAlgorithme() {
        return nomAlgorithme;
    }

    /** @return le chemin trouvé */
    public CTrajectoire getChemin() {
        return chemin;
    }

    /** @return les cases explorées, dans l'ordre */
    public List<CCase> getCasesExplorees() {
        return casesExplorees;
    }

    /** @return la durée de la recherche en nanosecondes */
    public long getTempsExecutionNs() {
        return tempsExecutionNs;
    }

    /** @return {@code true} si un chemin a été trouvé */
    public boolean estCheminTrouve() {
        return chemin != null && !chemin.estVide();
    }

    /** @return le nombre de cases explorées */
    public int getNbCasesExplorees() {
        return casesExplorees.size();
    }

    /** @return le coût du chemin trouvé */
    public int getCout() {
        if (chemin == null) {
            return 0;
        }
        return chemin.getCoutTotal();
    }
}
