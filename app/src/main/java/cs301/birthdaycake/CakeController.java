package cs301.birthdaycake;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class CakeController implements View.OnClickListener, CompoundButton.OnCheckedChangeListener,
        SeekBar.OnSeekBarChangeListener, View.OnTouchListener{

    private CakeView theCakeView;
    private CakeModel theCakeModel;

    public CakeController(CakeView cake){
        theCakeView = cake;
        theCakeModel = theCakeView.getCakeModel();
    }

    @Override
    public void onClick(View view) {

        TextView v = (TextView) view;

        theCakeModel.candlesLit = !theCakeModel.candlesLit;
        theCakeView.invalidate();

        if (!theCakeModel.candlesLit){
            v.setText("Re-Light");
        }
        else{
            v.setText("Extinguish");
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId())
        {
            case R.id.candlesSwitch:
                theCakeModel.hasCandles = b;
                theCakeView.invalidate();
                break;
            case R.id.frostingSwitch:
                theCakeModel.hasFrosting = b;
                theCakeView.invalidate();
                break;

        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        theCakeModel.numCandles = i;
        theCakeView.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if(event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            theCakeModel.x = event.getX();
            theCakeModel.y = event.getY();

            //check the click
            Log.d("click", "onTouch working");

            //want to draw
            theCakeView.invalidate();

            return true;
        }

        return false;
    }

}
