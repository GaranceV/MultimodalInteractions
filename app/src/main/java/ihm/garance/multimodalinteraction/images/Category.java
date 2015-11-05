package ihm.garance.multimodalinteraction.images;


import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import ihm.garance.multimodalinteraction.R;

/**
 * Created by Garance on 05/11/2015.
 */
public class Category {
    private String name;
    private Integer path;

    public void Category(String name, int path){
        this.name=name;
        this.path=path;
    }

    public void Category(){
  //      this.name="imageTest";
//        this.path= R.drawable.imagetest;
        this.name="";
    }


    public String getName() {
        return name;
    }

    public Integer getPath() {
        System.out.println("*****************************");
        if (path == null) {
            System.out.println("///////////////////////////////////////////////::");
        }
        return path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(Integer path) {
        this.path = path;
    }
}
