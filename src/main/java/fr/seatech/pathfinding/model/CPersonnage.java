package fr.seatech.pathfinding.model;

/**
 * Personnage (agent) qui se déplace sur le plateau de sa case de départ
 * vers sa case but.
 */
public class CPersonnage {

    private final int id;
    private final String nom;
    private CCase position;
    private final CCase depart;
    private final CCase but;
    private int vitesse;
    private EEtatPersonnage etat;
    private CTrajectoire trajectoire;

    /**
     * Crée un personnage.
     *
     * @param id      identifiant unique
     * @param nom     nom affiché
     * @param depart  case de départ (position initiale)
     * @param but     case à atteindre
     * @param vitesse nombre de cases parcourues par pas de temps
     */
    public CPersonnage(int id, String nom, CCase depart, CCase but, int vitesse) {
        this.id = id;
        this.nom = nom;
        this.depart = depart;
        this.position = depart;
        this.but = but;
        this.vitesse = vitesse;
        this.etat = EEtatPersonnage.EN_ATTENTE;
    }

    /** @return l'identifiant */
    public int getId() {
        return id;
    }

    /** @return le nom */
    public String getNom() {
        return nom;
    }

    /** @return la position courante */
    public CCase getPosition() {
        return position;
    }

    /** @param position la nouvelle position */
    public void setPosition(CCase position) {
        this.position = position;
    }

    /** @return la case de départ */
    public CCase getDepart() {
        return depart;
    }

    /** @return la case but */
    public CCase getBut() {
        return but;
    }

    /** @return la vitesse (cases par pas de temps) */
    public int getVitesse() {
        return vitesse;
    }

    /** @param vitesse la nouvelle vitesse */
    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    /** @return l'état courant */
    public EEtatPersonnage getEtat() {
        return etat;
    }

    /** @param etat le nouvel état */
    public void setEtat(EEtatPersonnage etat) {
        this.etat = etat;
    }

    /** @return la trajectoire planifiée (peut être {@code null}) */
    public CTrajectoire getTrajectoire() {
        return trajectoire;
    }

    /** @param trajectoire la trajectoire planifiée */
    public void setTrajectoire(CTrajectoire trajectoire) {
        this.trajectoire = trajectoire;
    }

    /** @return {@code true} si le personnage est sur sa case but */
    public boolean estArrive() {
        // TODO [Étape 1]
        return false;
    }

    @Override
    public String toString() {
        return nom + " #" + id;
    }
}
