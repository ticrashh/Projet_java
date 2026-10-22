package fr.seatech.pathfinding.simulation;

import fr.seatech.pathfinding.model.CPersonnage;
import fr.seatech.pathfinding.model.CPlateau;

import java.util.List;

/**
 * Moteur de simulation temporelle : fait avancer les personnages pas à pas
 * dans un thread dédié, avec pause, reprise et exécution pas-à-pas.
 */
public class CSimulation implements Runnable {

    private final CPlateau plateau;
    private final List<CPersonnage> personnages;
    private final CDetecteurConflits detecteur;

    private volatile boolean enCours;
    private volatile boolean enPause;
    private int tempsCourant;
    private long delaiMs;
    private Thread thread;

    /**
     * @param plateau     le plateau
     * @param personnages les personnages (avec leur trajectoire planifiée)
     * @param delaiMs     délai entre deux pas de temps, en millisecondes
     */
    public CSimulation(CPlateau plateau, List<CPersonnage> personnages, long delaiMs) {
        this.plateau = plateau;
        this.personnages = personnages;
        this.delaiMs = delaiMs;
        this.detecteur = new CDetecteurConflits();
    }

    /** Démarre la simulation dans un nouveau thread. */
    public void demarrer() {
        // TODO [Étape 4]
    }

    /** Met la simulation en pause. */
    public void pause() {
        // TODO [Étape 4]
    }

    /** Reprend la simulation après une pause. */
    public void reprendre() {
        // TODO [Étape 4]
    }

    /** Arrête définitivement la simulation. */
    public void arreter() {
        // TODO [Étape 4]
    }

    /** Exécute un seul pas de temps (mode pas-à-pas). */
    public void pasSuivant() {
        // TODO [Étape 4] déplacer les personnages, détecter les conflits, notifier l'UI
    }

    /** Boucle principale du thread de simulation. */
    @Override
    public void run() {
        // TODO [Étape 4]
    }

    /** @return le pas de temps courant */
    public int getTempsCourant() {
        return tempsCourant;
    }

    /** @param delaiMs nouveau délai entre deux pas, en millisecondes */
    public void setDelaiMs(long delaiMs) {
        this.delaiMs = delaiMs;
    }

    /** @return {@code true} si la simulation tourne */
    public boolean estEnCours() {
        return enCours;
    }

    /** @return {@code true} si la simulation est en pause */
    public boolean estEnPause() {
        return enPause;
    }
}
