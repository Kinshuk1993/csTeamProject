import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.input.DataFormat;
import javafx.util.Pair;

public class DragableContainer implements Serializable{

    private static final long serialVersionUID = -1458406119115196098L;
    
    public static final DataFormat AddNode = new DataFormat("application.DragableNode.add");
    
    private final List <Pair<String, Object> > mDataPairs = new ArrayList <Pair<String, Object> >();

    public void addData(String key, Object value){
        mDataPairs.add(new Pair<String, Object>(key, value));

    }

    public <T> T getValue(String key){

        for (Pair<String, Object> data: mDataPairs) {

            if (data.getKey().equals(key))
                return (T) data.getValue();
            
        }

        return null;
    }

    public List <Pair<String, Object> > getData() {return mDataPairs; }

} 