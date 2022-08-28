package com.nicomot.myquran.util;

import androidx.appcompat.app.ActionBar;

public class Hidden {

    public static void hiddenActionBar(ActionBar actionBar){
        if(actionBar != null){
            actionBar.hide();
        }
    }
}
