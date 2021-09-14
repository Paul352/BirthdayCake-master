package cs301.birthdaycake;

public class CakeController {

    private CakeView theCakeView;
    private CakeModel theCakeModel;

    public CakeController(CakeView cake){
        theCakeView = cake;
        theCakeModel = theCakeView.getCakeModel();
    }

}
