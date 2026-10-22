package fr.seatech.pathfinding.model;

/**
 * État courant d'un personnage pendant la simulation.
 */
public enum EEtatPersonnage {

    /** Le personnage n'a pas encore commencé à se déplacer (ou attend son tour). */
    EN_ATTENTE,

    /** Le personnage suit sa trajectoire. */
    EN_DEPLACEMENT,

    /** Le personnage a atteint son but. */
    ARRIVE,

    /** Le personnage ne peut plus avancer (conflit ou chemin inexistant). */
    BLOQUE
}
