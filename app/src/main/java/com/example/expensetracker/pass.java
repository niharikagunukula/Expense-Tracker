package com.example.expensetracker;

import android.net.Uri;

public class pass {
    String s;
    Uri uri;

    public void pass1(String s) {
        this.s = s;
    }
    public void pass2(Uri uri){
        this.uri=uri;
    }

    public String getS() {
        return s;
    }

    public Uri getUri() {
        return uri;
    }


}
