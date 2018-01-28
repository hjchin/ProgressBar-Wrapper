package world.trav.uiloader.sample.model;

import java.util.ArrayList;

/**
 * Created by HJ Chin on 28/1/2018.
 */

public class Item {

    public static ArrayList<Item> list;

    static{
        list = new ArrayList<>();
        for(int i=0;i<50;i++){
            list.add(new Item("Item "+String.valueOf(i)));
        }
    }

    private String text;

    public Item(String text){
        this.text = text;
    }
}
