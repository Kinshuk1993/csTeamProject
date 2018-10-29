import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        
        BorderPane root = new BorderPane();

        try{
            
            Scene scene = new Scene(root, 640, 480);
            scene.getStylesheets().add(getClass().getResource("/res/app.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch(Exception e){
            e.printStackTrace();
        }

        root.setCenter(new Layout());

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}