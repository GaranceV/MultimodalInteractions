package ihm.garance.multimodalinteraction;

import android.content.Context;

import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

import ihm.garance.multimodalinteraction.images.Food;

/**
 * Created by Garance on 05/11/2015.
 */
public class FoodView extends ImageView {
    public Food toSort;

    public FoodView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        toSort = new Food();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void setFood(String name, int file) {
        toSort.setName(name);
        toSort.setPath(file);
    }

    public Food getFood() {
        return toSort;
    }

}
