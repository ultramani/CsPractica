package com.example.myfirstjob.ui.MyOffer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myfirstjob.R;
import com.example.myfirstjob.ui.OfferViewerScreen;


public class MyOffer extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_my_offer, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((OfferViewerScreen) getActivity()).updateMyUIList();
    }
}