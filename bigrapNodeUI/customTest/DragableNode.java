import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;

public class DragableNode extends AnchorPane{

    //@FXML AnchorPane root_pane;

    private DragableNodeType mType = null;

    public DragableNode() {

        FXMLLoader fxmlLoader = new FXMLLoader(
            getClass().getResource("/res/DragableNode.fxml")
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
    private void initialize() { }

    public void relocateToPoint(Point2D p){

        Point2D localCoordinates = getParent().sceneToLocal(p);

        relocate( (int) (localCoordinates.getX() - (getBoundsInLocal().getWidth()/2)), (int) (localCoordinates.getY() - (getBoundsInLocal().getHeight()/2)));
    }

    public DragableNodeType getType() { return mType; }

    public void setType(DragableNodeType type){
        mType = type;
        
        getStyleClass().clear();
        getStyleClass().add("dragablenode");

        switch (mType){

        case blue:
            getStyleClass().add("icon-blue");
        break;

        case red:
            getStyleClass().add("icon-red");
        break;

        case green:
            getStyleClass().add("icon-green");
        break;

        case yellow:
            getStyleClass().add("icon-yellow");
        break;

        case grey:
            getStyleClass().add("icon-grey");
        break;

        case purple:
            getStyleClass().add("icon-purple");
        break;

        case black:
            getStyleClass().add("icon-black");
        break;

        default:
        break;

        }
    }
}
