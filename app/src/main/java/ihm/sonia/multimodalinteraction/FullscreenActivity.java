package ihm.sonia.multimodalinteraction;

import ihm.sonia.multimodalinteraction.util.SystemUiHider;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class FullscreenActivity extends Activity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * milliseconds.
     */
    private static final boolean AUTO_HIDE = true;


    private CategoryView glucideCategory;
    private CategoryView laitierCategory;
    private CategoryView fruitlegCategory;
    private FoodView foodView;

    private String categoryName;
    private int categoryFile;

    private Map<String, Integer> foodList;

    private final int QUANTITY_TO_SORT=30;
    private int quantitySorted;
    private void createAllTheFood() {
        foodList = new HashMap<>();
        foodList.put("farine", R.drawable.farine);
        foodList.put("riz", R.drawable.riz);
        foodList.put("patate", R.drawable.patate);
        foodList.put("pates", R.drawable.pates);
        foodList.put("pain", R.drawable.pain);
        foodList.put("croissant", R.drawable.croissant);
        foodList.put("biscotte", R.drawable.biscotte);
        foodList.put("mais", R.drawable.mais);
        foodList.put("cereales", R.drawable.cereales);
        foodList.put("poischiche", R.drawable.poischiche);

        foodList.put("lait", R.drawable.lait);
        foodList.put("fromage", R.drawable.fromage);
        foodList.put("fromage2", R.drawable.fromage2);
        foodList.put("fromagerape", R.drawable.fromagerape);
        foodList.put("yaourt", R.drawable.yaourt);
        foodList.put("cremefraiche", R.drawable.cremefraiche);
        foodList.put("cremefraiche2", R.drawable.cremefraiche2);
        foodList.put("fromageblanc", R.drawable.fromageblanc);
        foodList.put("chantilly", R.drawable.chantilly);
        foodList.put("kiri", R.drawable.kiri);

        foodList.put("banane", R.drawable.banane);
        foodList.put("fraises", R.drawable.fraises);
        foodList.put("pommes", R.drawable.pommes);
        foodList.put("kiwi", R.drawable.kiwi);
        foodList.put("fruitpassion", R.drawable.fruitpassion);
        foodList.put("chou", R.drawable.chou);
        foodList.put("poireaux", R.drawable.poireaux);
        foodList.put("salade", R.drawable.salade);
        foodList.put("tomate", R.drawable.tomate);
        foodList.put("courgettes", R.drawable.courgettes);
    }


    private void nextFoodToSort() {

        //randomly chosing a new food to sort
        Random       random    = new Random();
        List<String> keys      = new ArrayList<String>(foodList.keySet());
        String       randomKey = keys.get( random.nextInt(keys.size()) );
        Integer       value     = foodList.get(randomKey);
      //  foodList.remove(randomKey);

        foodView.setImageResource(value);
        foodView.setFood(randomKey, value);
        quantitySorted++;
        if (quantitySorted >= QUANTITY_TO_SORT) {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
            r.play();
        //    createAllTheFood();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        quantitySorted=0;
        super.onCreate(savedInstanceState);
        createAllTheFood();

        setContentView(R.layout.activity_fullscreen);


        categoryName = "Glucide";
        categoryFile = R.drawable.glucide;
        glucideCategory = (CategoryView) findViewById(R.id.glucideCategory);
        glucideCategory.setImageResource(categoryFile);
        glucideCategory.setCategory(categoryName, categoryFile);
        List<String> glucidList = new ArrayList<>();
        glucidList.add("farine");glucidList.add("patate"); glucidList.add("croissant");glucidList.add("pain");
        glucidList.add("pates");glucidList.add("riz"); glucidList.add("biscotte");glucidList.add("mais");
        glucidList.add("cereales");glucidList.add("poischiche");

        glucideCategory.fillFoodList(glucidList);
        categoryName = "Laitier";
        categoryFile = R.drawable.laitier;

        laitierCategory = (CategoryView) findViewById(R.id.laitierCategory);
        laitierCategory.setImageResource(categoryFile);
        laitierCategory.setCategory(categoryName, categoryFile);
        List<String> dairyList = new ArrayList<>();
        dairyList.add("lait");dairyList.add("fromage"); dairyList.add("fromage2");dairyList.add("fromagerape");
        dairyList.add("yaourt");dairyList.add("cremefraiche");dairyList.add("fromageblanc");
        dairyList.add("cremefraiche2");dairyList.add("chantilly");dairyList.add("kiri");
        laitierCategory.fillFoodList(dairyList);

        categoryName = "FruitLeg";
        categoryFile = R.drawable.fruit;
        fruitlegCategory = (CategoryView) findViewById(R.id.fruitlegCategory);
        fruitlegCategory.setImageResource(categoryFile);
        fruitlegCategory.setCategory(categoryName, categoryFile);
        List<String> fruitList = new ArrayList<>();
        fruitList.add("fraises");fruitList.add("poireaux"); fruitList.add("banane");fruitList.add("chou");
        fruitList.add("tomate");fruitList.add("salade");fruitList.add("fruitpassion");fruitList.add("kiwi");
        fruitList.add("courgettes");fruitList.add("pommes");
        fruitlegCategory.fillFoodList(fruitList);

        foodView = (FoodView) findViewById(R.id.foodView);
        nextFoodToSort();
     foodView.setOnTouchListener(new OnTouchListener() {
         @Override
         public boolean onTouch(View v, MotionEvent event) {
             if (event.getAction() == MotionEvent.ACTION_DOWN) {
                 View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                 String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
                 ClipData data = new ClipData(v.getTag().toString(), mimeTypes, new ClipData.Item(foodView.getFood().getName()));
                 v.startDrag(data, shadowBuilder, v, 0);
                 return true;
             }
             return false;
         }
     });

        glucideCategory.setOnDragListener(new dropListener());
        laitierCategory.setOnDragListener(new dropListener());
        fruitlegCategory.setOnDragListener(new dropListener());

        setOnClickListener();


        View contentView = findViewById(R.id.fullscreen_content);
            contentView.setOnDragListener(new dropListener());
    }

    public void setOnClickListener(){
        glucideCategory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                CategoryView viewClicked = (CategoryView) v;
                if(checkCategory(viewClicked.getFoodList())){
                    nextFoodToSort();
                }
            }
        });

        laitierCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CategoryView viewClicked = (CategoryView) v;
                if(checkCategory(viewClicked.getFoodList())){
                    nextFoodToSort();
                }
            }
        });
        fruitlegCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CategoryView viewClicked = (CategoryView) v;
                if(checkCategory(viewClicked.getFoodList())){
                    nextFoodToSort();
                }
            }
        });
    }

    public boolean checkCategory(List<String> listCat){
        for (String cat : listCat) {
            if (cat.equals(foodView.getFood().getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }



    Handler mHideHandler = new Handler();
    Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
        }
    };

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }


    private class dropListener implements View.OnDragListener {

        View draggedView;
        FoodView dropped;

        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    draggedView = (View) event.getLocalState();
                    dropped = (FoodView) draggedView;
                    draggedView.setVisibility(View.INVISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    if (v instanceof CategoryView) {
                        CategoryView dropTarget = (CategoryView) v;
                        //Define what happens on drag!!!
                        if (checkCategory(dropTarget.getFoodList())) {
                            nextFoodToSort();
                        }
                    }
                    draggedView.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    draggedView.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
            return true;
        }

    }
}
