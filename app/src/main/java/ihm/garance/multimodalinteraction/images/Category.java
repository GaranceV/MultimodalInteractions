package ihm.garance.multimodalinteraction.images;

/**
 * Created by Garance on 05/11/2015.
 */
public class Category {
    private String name;
    private int path;

    public void Category(String name, int path){
        this.name=name;
        this.path=path;
    }

    public String getName() {
        return name;
    }

    public int getPath() {
        return path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(int path) {
        this.path = path;
    }
}
