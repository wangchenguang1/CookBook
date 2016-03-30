package com.example.wcg.cookbook.Controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wcg.cookbook.R;
import com.example.wcg.cookbook.View.CKNameCell;

/**
 * Created by wcg on 16/3/9.
 */
public class CKNameFragment extends Fragment {


    public static CKNameFragment newInstance(){

        CKNameFragment fragment = new CKNameFragment();
        return fragment;

    }

    public CKNameFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = null;
        view = inflater.inflate(R.layout.fragment_name, container, false);

        CKNameCell collection= (CKNameCell) view.findViewById(R.id.collection);
        collection.setCkNameCellListener(new CKNameCell.CKNameCellListener() {
            @Override
            public void click() {
                System.out.println("123456778");
            }
        });



        return view;

    }
}
