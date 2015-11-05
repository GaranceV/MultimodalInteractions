package ihm.garance.multimodalinteraction;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import ihm.garance.multimodalinteraction.images.Food;

/**
 * Created by Garance on 05/11/2015.
 */
public class FoodView extends View {
    public Food toSort;

    public FoodView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        toSort = new Food();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Bitmap myBitmap = BitmapFactory.decodeResource(getResources(), toSort.getPath());
  //      canvas.drawBitmap(myBitmap, 0, 0, null);
    }


}
