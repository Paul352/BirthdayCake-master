package cs301.birthdaycake;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        CakeView theCake = findViewById(R.id.cakeview);
        CakeController theController = new CakeController(theCake);

        Button Extinguish = findViewById(R.id.Extinguish);
        Extinguish.setOnClickListener(theController);

        Switch toggleCandles = findViewById(R.id.candlesSwitch);
        toggleCandles.setOnCheckedChangeListener(theController);

        Switch frostingSwitch = findViewById(R.id.frostingSwitch);
        frostingSwitch.setOnCheckedChangeListener(theController);

        SeekBar numCandles = findViewById(R.id.numCandles);
        numCandles.setOnSeekBarChangeListener(theController);

        theCake.setOnTouchListener(theController);


    }

        public void goodbye(View button){
            Log.i("button", "Goodbye");
            finishAffinity();
        }

}
