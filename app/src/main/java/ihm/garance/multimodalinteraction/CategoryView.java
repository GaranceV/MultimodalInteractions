package ihm.garance.multimodalinteraction;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

import ihm.garance.multimodalinteraction.images.Category;

/**
 * Created by sonia on 05/11/15.
 */
public class CategoryView extends ImageView {
    private Category toSort;
   // private ImageView imageViewTest;

    public CategoryView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        toSort = new Category();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void setCategory(String name, int file) {
        toSort.setName(name);
        toSort.setPath(file);
    }

    public Category getCategory() {
        return toSort;
    }
}
