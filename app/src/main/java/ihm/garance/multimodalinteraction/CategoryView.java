package ihm.garance.multimodalinteraction;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import ihm.garance.multimodalinteraction.images.Category;

/**
 * Created by sonia on 05/11/15.
 */
public class CategoryView extends ImageView {
    private Category toSort;
    private List<String> foodList;

    // private ImageView imageViewTest;

    public CategoryView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        foodList = new ArrayList<>();
        toSort = new Category();
    }

    public void fillFoodList(String food1, String food2) {
        foodList.add(food2);
        foodList.add(food1);
    }

    public List<String> getFoodList() {
        return foodList;
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
