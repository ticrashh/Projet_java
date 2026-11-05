package fr.seatech.pathfinding.ui;

import fr.seatech.pathfinding.algo.CResultatRecherche;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Panneau d'affichage des métriques (longueur, coût, cases explorées, temps)
 * et de la comparaison entre algorithmes.
 */
public class CResultatsPanel extends VBox {

    private final Label labelAlgorithme = new Label();
    private final Label labelStatut = new Label();
    private final Label labelLongueur = new Label();
    private final Label labelCout = new Label();
    private final Label labelExplorees = new Label();
    private final Label labelTemps = new Label();

    /** Crée le panneau de résultats. */
    public CResultatsPanel() {
        super(8);
        setPadding(new Insets(12));
        setPrefWidth(230);

        Label titre = new Label("Résultats");
        titre.setFont(Font.font(null, FontWeight.BOLD, 16));

        getChildren().addAll(titre, labelAlgorithme, labelStatut,
                labelLongueur, labelCout, labelExplorees, labelTemps);
        effacer();
        // TODO [Étape 3] tableau de comparaison entre algorithmes
    }

    /**
     * Affiche les métriques d'une recherche.
     *
     * @param resultat le résultat à afficher
     */
    public void afficherResultat(CResultatRecherche resultat) {
        labelAlgorithme.setText("Algorithme : " + resultat.getNomAlgorithme());
        if (resultat.estCheminTrouve()) {
            labelStatut.setText("Chemin trouvé");
            labelLongueur.setText("Longueur : " + resultat.getChemin().getLongueur() + " cases");
            labelCout.setText("Coût : " + resultat.getCout());
        } else {
            labelStatut.setText("Aucun chemin");
            labelLongueur.setText("Longueur : -");
            labelCout.setText("Coût : -");
        }
        labelExplorees.setText("Cases explorées : " + resultat.getNbCasesExplorees());
        labelTemps.setText(String.format("Temps : %.3f ms", resultat.getTempsExecutionNs() / 1_000_000.0));
    }

    /**
     * Affiche un message (calcul en cours, erreur...).
     *
     * @param message le message à afficher
     */
    public void afficherMessage(String message) {
        effacer();
        labelStatut.setText(message);
    }

    /** Efface les résultats affichés. */
    public void effacer() {
        labelAlgorithme.setText("Algorithme : -");
        labelStatut.setText("Aucun calcul lancé");
        labelLongueur.setText("Longueur : -");
        labelCout.setText("Coût : -");
        labelExplorees.setText("Cases explorées : -");
        labelTemps.setText("Temps : -");
    }
}
