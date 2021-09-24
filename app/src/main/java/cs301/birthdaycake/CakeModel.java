package cs301.birthdaycake;

public class CakeModel {
    boolean candlesLit;
    int numCandles;
    boolean hasFrosting;
    boolean hasCandles;
    float textX,textY;

    public CakeModel(){
        candlesLit = true;
        numCandles = 2;
        hasFrosting = true;
        hasCandles = true;
        // Set these to an invalid value so text is not drawn until user clicks.
        textX = -1;
        textY = -1;
    }
}
