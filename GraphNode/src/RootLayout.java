import javafx.scene.layout.AnchorPane;

public class RootLayout extends AnchorPane{

    public RootLayout(){

        FXMLLoader fxmlLoader = new FXMLLoader(
            getClass().getResource("/RootLayout.fxml")
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