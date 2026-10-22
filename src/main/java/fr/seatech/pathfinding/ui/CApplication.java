package fr.seatech.pathfinding.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Point d'entrée de l'application de planification de trajectoires.
 * <p>
 * Lancement : {@code mvn javafx:run}.
 */
public class CApplication extends Application {

    /**
     * Construit la scène principale et affiche la fenêtre.
     *
     * @param stage fenêtre fournie par JavaFX
     */
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new CFenetrePrincipale(), 1000, 700);
        stage.setTitle("Planification de trajectoires");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Méthode principale.
     *
     * @param args arguments de la ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        launch(args);
    }
}
