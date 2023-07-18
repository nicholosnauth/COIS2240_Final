package Menus;

import javafx.scene.control.TextField;


/*
Used code from this question on stack overflow to create this class
"How to limit the amount of characters a javafx textfield" (poor grammar preserved)
https://stackoverflow.com/questions/22714268/how-to-limit-the-amount-of-characters-a-javafx-textfield
*/

public class CappedTextField extends TextField {

    private final int maxLength;

    public CappedTextField(){
        super();
        this.maxLength = 3;
    }

    @Override
    public void replaceText(int i, int i1, String s) {
        String current = this.getText();

        String end = current.substring(0, i) + s + current.substring(i1);
        int exceeding = end.length() - maxLength;
        if(exceeding <= 0){
            super.replaceText(i, i1, s);
        }else{
            String cut = s.substring(
                    0,
                    s.length() - exceeding
            );

            super.replaceText(i, i1, cut);
        }

    }

}
