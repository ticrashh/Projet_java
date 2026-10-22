package fr.seatech.pathfinding.simulation;

import fr.seatech.pathfinding.model.CCase;
import fr.seatech.pathfinding.model.CPersonnage;

import java.util.List;

/**
 * Détection des conflits entre personnages : collisions sur une même case,
 * croisements (échange de cases) et blocages.
 */
public class CDetecteurConflits {

    /** Types de conflits détectés. */
    public enum ETypeConflit {
        /** Deux personnages sur la même case au même instant. */
        COLLISION,
        /** Deux personnages échangent leurs cases entre t et t+1. */
        CROISEMENT,
        /** Un personnage ne peut plus avancer. */
        BLOCAGE
    }

    /**
     * Conflit détecté à un instant donné.
     *
     * @param type    type de conflit
     * @param p1      premier personnage impliqué
     * @param p2      second personnage impliqué ({@code null} pour un blocage seul)
     * @param instant pas de temps du conflit
     * @param lieu    case concernée
     */
    public record CConflit(ETypeConflit type, CPersonnage p1, CPersonnage p2, int instant, CCase lieu) {
    }

    /**
     * Cherche les conflits au passage de l'instant {@code t} à {@code t + 1}.
     *
     * @param personnages les personnages de la simulation
     * @param t           pas de temps courant
     * @return la liste des conflits détectés
     */
    public List<CConflit> detecter(List<CPersonnage> personnages, int t) {
        // TODO [Étape 4]
        return List.of();
    }
}
