package com.example.wcg.cookbook.Controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wcg.cookbook.R;

/**
 * Created by wcg on 16/3/9.
 */
public class CKDetailFragment extends Fragment {


    public static CKDetailFragment newInstance(){

        CKDetailFragment fragment = new CKDetailFragment();
        return fragment;
    }

    public CKDetailFragment(){

        System.out.println("init");

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        System.out.println("detail Oncreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        System.out.println("detail OncreateView");

        return inflater.inflate(R.layout.fragment_detail, container, false);

    }




}
