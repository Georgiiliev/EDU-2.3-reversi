package controller;

public class GUIController {

    public GUIController(){

    }

    public int positieOmzetten(int size, int row, int column) {
        int positie;

        int resultaat = size * column;
        positie = resultaat + row;

        return positie;
    }

}
