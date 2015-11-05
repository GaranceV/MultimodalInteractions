package ihm.garance.multimodalinteraction;

import ihm.garance.multimodalinteraction.images.Category;
import ihm.garance.multimodalinteraction.images.Food;
import ihm.garance.multimodalinteraction.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

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
    private CategoryView categoryView;
    private FoodView foodView;

    private String categoryName;
    private int categoryFile;

    private Map<String, Integer> foodList;

    private List<String> glucidFood;
    private List<String> milkyFood;

    private void createAllTheFood() {
        foodList = new HashMap<>();
        foodList.put("farine", R.drawable.farine);
        foodList.put("lait", R.drawable.lait);
        foodList.put("patate", R.drawable.patate);
        foodList.put("fromage", R.drawable.fromage);
    }

    private void fillListOfFoodInRightCategory() {
        glucidFood = new ArrayList<>();
        glucidFood.add("farine");
        glucidFood.add("patate");

        milkyFood = new ArrayList<>();
        milkyFood.add("lait");
        milkyFood.add("fromage");
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
        fillListOfFoodInRightCategory();

        setContentView(R.layout.activity_fullscreen);


        categoryName = "Glucide";
        categoryFile = R.drawable.glucide;

        categoryView = (CategoryView) findViewById(R.id.categoryView);
        categoryView.setImageResource(categoryFile);
        categoryView.setCategory(categoryName, categoryFile);


        foodView = (FoodView) findViewById(R.id.foodView);
        nextFoodToSort();

        categoryView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                System.out.println("*************************************************");
                for (String cat : glucidFood) {
                    if (cat.equals(foodView.getFood().getName())) {
                        System.out.println("Brravo t'as trouvé !");
                        nextFoodToSort();
                        return true;
                    }
                }
                System.out.println("essaie encore !");
                return false;
            }
        });

      //  final View controlsView = findViewById(R.id.fullscreen_content_controls);
        final View contentView = findViewById(R.id.fullscreen_content);

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
     //   findViewById(R.id.imageToSort).setOnTouchListener(mDelayHideTouchListener);
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
    View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
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
}
