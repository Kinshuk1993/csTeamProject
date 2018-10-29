import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DraOgDropp extends Application {

    @Override
    public void start(Stage stage){
        stage.setTitle("Dra og dropp");

        Group root = new Group();
        Scene scene = new Scene(root, 640, 480);
        scene.setFill(Color.LIGHTGRAY);

        final Text source = new Text(50,100,"Dra meg");
        source.setScaleX(2.0);
        source.setScaleY(2.0);

        final Text target = new Text(250, 100, "Slipp meg her");
        target.setScaleX(2.0);
        target.setScaleY(2.0);

        root.getChildren().addAll(source,target);
        stage.setScene(scene);
        stage.show();
    
    }

    public static void main(String[] args){
        Application.launch(args);
    }

}