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
 * Algorithme A* : recherche heuristique (distance de Manhattan) du plus court chemin pondéré.
 * Note : Implémentation assistée par IA générative (conformément aux consignes du projet).
 */
public class CAStar implements IAlgorithmeRecherche {

    @Override
    public CResultatRecherche rechercher(CPlateau plateau, CCase depart, CCase arrivee) {
        long debutTemps = System.nanoTime();

        // File de priorité triée selon f = g + h (via compareTo dans CNoeud)
        PriorityQueue<CNoeud> listeOuverte = new PriorityQueue<>();

        // Liste des cases déjà visitées pour l'affichage et éviter les boucles
        List<CCase> casesExplorees = new ArrayList<>();

        // Meilleur coût g connu pour atteindre chaque case
        Map<CCase, Integer> coutsG = new HashMap<>();

        // Initialisation avec la case de départ : CNoeud(caseAssociee, g, h, parent)
        int hDepart = heuristique(depart, arrivee);
        CNoeud noeudDepart = new CNoeud(depart, 0, hDepart, null);
        listeOuverte.add(noeudDepart);
        coutsG.put(depart, 0);

        while (!listeOuverte.isEmpty()) {
            CNoeud courant = listeOuverte.poll();
            CCase caseCourante = courant.getCase();

            // Si la case a déjà été traitée avec un meilleur coût, on l'ignore
            if (casesExplorees.contains(caseCourante)) {
                continue;
            }

            casesExplorees.add(caseCourante);

            // Condition d'arrêt : on a atteint la case d'arrivée
            if (caseCourante.equals(arrivee)) {
                long tempsCalculMs = (System.nanoTime() - debutTemps) ;
                CTrajectoire trajectoire = reconstruireTrajectoire(courant);
                return new CResultatRecherche(getNom(), trajectoire, casesExplorees, tempsCalculMs);
            }

            // Exploration des voisins (getVoisins ne renvoie que les cases traversables)
            for (CCase voisin : plateau.getVoisins(caseCourante)) {
                if (casesExplorees.contains(voisin)) {
                    continue;
                }

                int nouveauG = courant.getG() + voisin.getCout();

                // Si on découvre le voisin ou qu'on trouve un chemin moins coûteux
                if (!coutsG.containsKey(voisin) || nouveauG < coutsG.get(voisin)) {
                    coutsG.put(voisin, nouveauG);
                    int h = heuristique(voisin, arrivee);
                    CNoeud noeudVoisin = new CNoeud(voisin, nouveauG, h, courant);
                    listeOuverte.add(noeudVoisin);
                }
            }
        }

        // Aucun chemin trouvé : on renvoie une trajectoire vide
        long tempsCalculMs = (System.nanoTime() - debutTemps) ;
        return new CResultatRecherche(getNom(), new CTrajectoire(), casesExplorees, tempsCalculMs);
    }

    /**
     * Reconstruit la trajectoire du départ à l'arrivée en remontant les parents.
     *
     * @param noeudArrivee le noeud final atteint
     * @return la trajectoire complète avec son coût calculé automatiquement
     */
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

    /**
     * Heuristique : distance de Manhattan entre deux cases.
     *
     * @param a première case
     * @param b seconde case
     * @return l'estimation du coût entre a et b
     */
    private int heuristique(CCase a, CCase b) {
        return Math.abs(a.getLigne() - b.getLigne()) + Math.abs(a.getColonne() - b.getColonne());
    }

    @Override
    public String getNom() {
        return "A*";
    }
}
