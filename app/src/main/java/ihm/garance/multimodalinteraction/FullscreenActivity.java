package ihm.garance.multimodalinteraction;

import ihm.garance.multimodalinteraction.images.Category;
import ihm.garance.multimodalinteraction.util.SystemUiHider;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
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
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * If set, will toggle the system UI visibility upon interaction. Otherwise,
     * will show the system UI visibility upon interaction.
     */
    private static final boolean TOGGLE_ON_CLICK = true;

    /**
     * The flags to pass to {@link SystemUiHider#getInstance}.
     */
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

    /**
     * The instance of the {@link SystemUiHider} for this activity.
     */
    private SystemUiHider mSystemUiHider;
    private CategoryView glucideCategory;
    private CategoryView laitierCategory;
    private FoodView foodView;

    private String categoryName;
    private int categoryFile;

    private Map<String, Integer> foodList;

    private void createAllTheFood() {
        foodList = new HashMap<>();
        foodList.put("farine", R.drawable.farine);
        foodList.put("lait", R.drawable.lait);
        foodList.put("patate", R.drawable.patate);
        foodList.put("fromage", R.drawable.fromage);
        foodList.put("croissant", R.drawable.croissant);
        foodList.put("fromage2", R.drawable.fromage2);
        foodList.put("pain", R.drawable.pain);
        foodList.put("fromagerape", R.drawable.fromagerape);
        foodList.put("pates", R.drawable.pates);
        foodList.put("riz", R.drawable.riz);
        foodList.put("yaourt", R.drawable.yaourt);
    }


    private void nextFoodToSort() {

        //randomly chosing a new food to sort
        Random       random    = new Random();
        List<String> keys      = new ArrayList<String>(foodList.keySet());
        String       randomKey = keys.get( random.nextInt(keys.size()) );
        Integer       value     = foodList.get(randomKey);

        foodView.setImageResource(value);
        foodView.setFood(randomKey, value);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        glucidList.add("pates");glucidList.add("riz");
        glucideCategory.fillFoodList(glucidList);
        categoryName = "Laitier";
        categoryFile = R.drawable.laitier;

        laitierCategory = (CategoryView) findViewById(R.id.laitierCategory);
        laitierCategory.setImageResource(categoryFile);
        laitierCategory.setCategory(categoryName, categoryFile);
        List<String> dairyList = new ArrayList<>();
        glucidList.add("lait");glucidList.add("fromage"); glucidList.add("fromage2");glucidList.add("fromagerape");
        glucidList.add("yaourt");
        laitierCategory.fillFoodList(dairyList);


        foodView = (FoodView) findViewById(R.id.foodView);
        nextFoodToSort();
     foodView.setOnTouchListener(new OnTouchListener() {
         @Override
         public boolean onTouch(View v, MotionEvent event) {
             if (event.getAction() == MotionEvent.ACTION_DOWN) {
                 System.out.println("about to initate drag ######################################");
                 View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                 String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
                 ClipData data = new ClipData(v.getTag().toString(), mimeTypes, new ClipData.Item(foodView.getFood().getName()));
                 v.startDrag(data, shadowBuilder, v, 0);
                 return true;
             }
             System.out.println("got a false..............................");
             return false;
         }
     });

        glucideCategory.setOnDragListener(new dropListener());
        laitierCategory.setOnDragListener(new dropListener());


        setOnClickListener();


        View contentView = findViewById(R.id.fullscreen_content);
            contentView.setOnDragListener(new dropListener());
        // Set up an instance of SystemUiHider to control the system UI for
        // this activity.
        mSystemUiHider = SystemUiHider.getInstance(this, contentView, HIDER_FLAGS);
        mSystemUiHider.setup();
        // Set up the user interaction to manually show or hide the system UI.
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TOGGLE_ON_CLICK) {
                    mSystemUiHider.toggle();
                } else {
                    mSystemUiHider.show();
                }
            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
     //   findViewById(R.id.imageToSort).setOnClickListener(mDelayHideTouchListener);
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
    }

    public boolean checkCategory(List<String> listCat){
        System.out.println("*************************************************");
        for (String cat : listCat) {
            if (cat.equals(foodView.getFood().getName())) {
                System.out.println("/////////////////////////////////////////");
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


    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    OnTouchListener mDelayHideTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    Handler mHideHandler = new Handler();
    Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            mSystemUiHider.hide();
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
                        System.out.println("Ce que t'as voulu, c'est " + dropped.getFood().getName());
                        if (checkCategory(dropTarget.getFoodList())) {
                            nextFoodToSort();
                        }
                    }
                    draggedView.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    break;
                default:
                    break;
            }
            return true;
        }

    }
}
