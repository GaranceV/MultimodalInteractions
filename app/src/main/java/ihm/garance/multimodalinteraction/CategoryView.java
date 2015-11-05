package ihm.garance.multimodalinteraction;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import ihm.garance.multimodalinteraction.images.Category;

/**
 * Created by sonia on 05/11/15.
 */
public class CategoryView extends View {
    private Category toSort;

    public CategoryView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        toSort = new Category();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Bitmap myBitmap = BitmapFactory.decodeResource(getResources(), toSort.getPath());
        //canvas.drawBitmap(myBitmap, 0, 0, null);
    }
}
