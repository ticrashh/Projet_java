package fr.seatech.pathfinding.algo;

import fr.seatech.pathfinding.model.CCase;
import fr.seatech.pathfinding.model.CPlateau;
import fr.seatech.pathfinding.model.CTrajectoire;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Algorithme de Dijkstra : plus court chemin pondéré par le coût des cases.
 */
public class CDijkstra implements IAlgorithmeRecherche {

    @Override
    public CResultatRecherche rechercher(CPlateau plateau, CCase depart, CCase arrivee) {
        long debutTemps = System.nanoTime();

        PriorityQueue<CNoeud> filePriorite = new PriorityQueue<>();
        List<CCase> casesExplorees = new ArrayList<>();
        Map<CCase, Integer> meilleursCouts = new HashMap<>();

        filePriorite.add(new CNoeud(depart, 0, 0, null));
        meilleursCouts.put(depart, 0);

        while (!filePriorite.isEmpty()) {
            CNoeud courant = filePriorite.poll();
            CCase caseCourante = courant.getCase();

            // Une ancienne entrée peut rester dans la file après une amélioration du coût.
            if (courant.getG() != meilleursCouts.get(caseCourante)) {
                continue;
            }

            casesExplorees.add(caseCourante);

            if (caseCourante.equals(arrivee)) {
                CTrajectoire trajectoire = reconstruireTrajectoire(courant);
                long tempsCalculNs = System.nanoTime() - debutTemps;
                return new CResultatRecherche(getNom(), trajectoire, casesExplorees, tempsCalculNs);
            }

            for (CCase voisin : plateau.getVoisins(caseCourante)) {
                int nouveauCout = courant.getG() + voisin.getCout();

                if (!meilleursCouts.containsKey(voisin) || nouveauCout < meilleursCouts.get(voisin)) {
                    meilleursCouts.put(voisin, nouveauCout);
                    filePriorite.add(new CNoeud(voisin, nouveauCout, 0, courant));
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
        return "Dijkstra";
    }
}
