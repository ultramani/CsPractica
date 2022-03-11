package com.example.myfirstjob.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myfirstjob.R;
import com.example.myfirstjob.ui.OfferViewerScreen;

public class HomeFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        /*Button b = (Button) getActivity().findViewById(R.id.btnUpdateListOffers);
        b.performClick();*/
        ((OfferViewerScreen) getActivity()).updateUIList();
        ((OfferViewerScreen) getActivity()).setOfferFilterMethods();
    }


}