package com.example.wcg.cookbook.Model.Classfy;

import java.util.ArrayList;

/**
 * Created by wcg on 16/3/8.
 */
public class CKClassifyData {

    private ArrayList<CKClassifyContent> tngou;
    private boolean status;


    public ArrayList<CKClassifyContent> getTngou() {
        return tngou;
    }

    public void setTngou(ArrayList<CKClassifyContent> tngou) {
        this.tngou = tngou;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    
}
