import javafx.scene.layout.AnchorPane;

public class DragIcon extends AnchorPane{

    public DragIcon(){

        FXMLLoader fxmlLoader = new FXMLLoader(
            getClass().getResource("/DragIcon.fxml")
        );

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    private void initialize() {
    }
}