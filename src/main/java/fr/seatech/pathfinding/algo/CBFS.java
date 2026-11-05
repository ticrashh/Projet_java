package fr.seatech.pathfinding.algo;

import fr.seatech.pathfinding.model.CCase;
import fr.seatech.pathfinding.model.CPlateau;
import fr.seatech.pathfinding.model.CTrajectoire;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Parcours en largeur (BFS) : plus court chemin en nombre de cases, sans tenir compte des coûts.
 */
public class CBFS implements IAlgorithmeRecherche {

    @Override
    public CResultatRecherche rechercher(CPlateau plateau, CCase depart, CCase arrivee) {
        long debutTemps = System.nanoTime();

        Queue<CNoeud> file = new LinkedList<>();
        List<CCase> casesExplorees = new ArrayList<>();
        List<CCase> casesDecouvertes = new ArrayList<>();

        // Chaque voisin est marqué dès son ajout à la file pour ne pas le traiter deux fois.
        file.add(new CNoeud(depart, 0, 0, null));
        casesDecouvertes.add(depart);

        while (!file.isEmpty()) {
            CNoeud courant = file.poll();
            CCase caseCourante = courant.getCase();
            casesExplorees.add(caseCourante);

            if (caseCourante.equals(arrivee)) {
                CTrajectoire trajectoire = reconstruireTrajectoire(courant);
                long tempsCalculNs = System.nanoTime() - debutTemps;
                return new CResultatRecherche(getNom(), trajectoire, casesExplorees, tempsCalculNs);
            }

            for (CCase voisin : plateau.getVoisins(caseCourante)) {
                if (!casesDecouvertes.contains(voisin)) {
                    casesDecouvertes.add(voisin);
                    file.add(new CNoeud(voisin, courant.getG() + 1, 0, courant));
                }
            }
        }

        long tempsCalculNs = System.nanoTime() - debutTemps;
        return new CResultatRecherche(getNom(), new CTrajectoire(), casesExplorees, tempsCalculNs);
    }

    /** Reconstruit le chemin en remontant les parents depuis l'arrivée. */
    private CTrajectoire reconstruireTrajectoire(CNoeud noeudArrivee) {
        List<CCase> chemin = new ArrayList<>();
        CNoeud courant = noeudArrivee;

        while (courant != null) {
            chemin.add(courant.getCase());
            courant = courant.getParent();
        }

        Collections.reverse(chemin);
        CTrajectoire trajectoire = new CTrajectoire();
        for (CCase c : chemin) {
            trajectoire.ajouterCase(c);
        }
        return trajectoire;
    }

    @Override
    public String getNom() {
        return "BFS";
    }
}
