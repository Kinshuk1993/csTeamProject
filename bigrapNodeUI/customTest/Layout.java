import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Point2D;

public class Layout extends AnchorPane{

    @FXML SplitPane base_pane;
    @FXML AnchorPane right_pane;
    @FXML VBox left_pane;

    private DragableNode mDragableNodeOver = null;

    private EventHandler mNodeDragOverRoot = null;
    private EventHandler mNodeDragDropped = null;
    private EventHandler mNodeDragOverRightPane = null;

    public Layout(){

        FXMLLoader fxmlLoader = new FXMLLoader(
            getClass().getResource("/res/Layout.fxml")
        );

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try{
            fxmlLoader.load();
        } catch(IOException exception){
            throw new RuntimeException(exception);
        }
    }

    @FXML
    private void initialize(){

        mDragableNodeOver = new DragableNode();

        mDragableNodeOver.setVisible(false);
        mDragableNodeOver.setOpacity(0.65);
        getChildren().add(mDragableNodeOver);

        for (int i = 0; i < 7; i++){

            DragableNode node = new DragableNode();

            addDragDetection(node);

            node.setType(DragableNodeType.values()[i]);
            left_pane.getChildren().add(node);
        }

        buildDragHandlers();

    }

    private void buildDragHandlers(){

        mNodeDragOverRoot = new EventHandler<DragEvent>() {
            
            @Override
            public void handle(DragEvent event) {
                
                Point2D p = right_pane.sceneToLocal(event.getSceneX(), event.getSceneY());

                if (!right_pane.boundsInLocalProperty().get().contains(p)){

                    event.acceptTransferModes(TransferMode.ANY);
                    mDragableNodeOver.relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));
                    return;
                }

                event.consume();
            }
        };

        mNodeDragOverRightPane = new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {
                
                event.acceptTransferModes(TransferMode.ANY);

                mDragableNodeOver.relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));

                event.consume();
            }
        };

        mNodeDragDropped = new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {
                
                DragableContainer container = (DragableContainer) event.getDragboard().getContent(DragableContainer.AddNode);

                container.addData("scene_coordinates", new Point2D(event.getSceneX(), event.getSceneY()));
 
                ClipboardContent content = new ClipboardContent();
                content.put(DragableContainer.AddNode, container);

                event.getDragboard().setContent(content);
                event.setDropCompleted(true);
                
            }
        };

        this.setOnDragDone(new EventHandler <DragEvent> (){

            @Override
            public void handle(DragEvent event) {

                right_pane.removeEventHandler(DragEvent.DRAG_OVER, mNodeDragOverRightPane);
                right_pane.removeEventHandler(DragEvent.DRAG_DROPPED, mNodeDragDropped);
                base_pane.removeEventHandler(DragEvent.DRAG_OVER, mNodeDragOverRoot);

                mDragableNodeOver.setVisible(false);

                DragableContainer container = (DragableContainer) event.getDragboard().getContent(DragableContainer.AddNode);

                System.out.println(container.getData().toString());

                if (container != null){
                    if (container.getValue("scene_coordinates") != null){
                        
                        DragableNode nodeDropped = new DragableNode();

                        nodeDropped.setType(DragableNodeType.valueOf(container.getValue("type")));
                        right_pane.getChildren().add(nodeDropped);

                        Point2D cursorPoint = container.getValue("scene_coordinates");

                        nodeDropped.relocateToPoint(new Point2D(cursorPoint.getX()-32, cursorPoint.getY()-32));

                    }
                }

                event.consume();
                
            }
        });

    }

    private void addDragDetection(DragableNode dragableNode){

        dragableNode.setOnDragDetected(new EventHandler <MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                base_pane.setOnDragOver(mNodeDragOverRoot);
                right_pane.setOnDragOver(mNodeDragOverRightPane);
                right_pane.setOnDragDropped(mNodeDragDropped);

                //get ref to clicked node
                DragableNode node = (DragableNode) event.getSource();

                //drag baby
                mDragableNodeOver.setType(node.getType());
                mDragableNodeOver.relocateToPoint(new Point2D (event.getSceneX(),event.getSceneY()));

                ClipboardContent content = new ClipboardContent();
                DragableContainer container = new DragableContainer();

                container.addData("type", mDragableNodeOver.getType().toString());
                content.put(DragableContainer.AddNode, container);

                mDragableNodeOver.startDragAndDrop (TransferMode.ANY).setContent(content);
                mDragableNodeOver.setVisible(true);
                mDragableNodeOver.setMouseTransparent(true);
                event.consume();
            }
        });
    }
}