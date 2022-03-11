package com.example.myfirstjob.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myfirstjob.R;
import com.example.myfirstjob.data.User;
import com.example.myfirstjob.databinding.FragmentGalleryBinding;
import com.example.myfirstjob.persistence.Manage;
import com.example.myfirstjob.ui.OfferViewerScreen;
import com.example.myfirstjob.ui.UserView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        Button bAct = getActivity().findViewById(R.id.btnUpdateListOffers);
        bAct.setVisibility(View.GONE);
        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
        View root = binding.getRoot();

        EditText etName = binding.etName;
        etName.setEnabled(false);
        etName.setText(UserView.user.getName());

        EditText etEmail = binding.etEmailProfile;
        etEmail.setEnabled(false);
        etEmail.setText(UserView.user.getEmail());

        EditText etStudies = binding.etStudiesProfile;
        etStudies.setEnabled(false);
        etStudies.setText(UserView.user.getStudies());

        EditText etCompanyName = binding.etCompanyNameProfile;
        etCompanyName.setEnabled(false);
        etCompanyName.setText(UserView.user.getCompanyName());

        Button b1 = binding.button1;
        b1.setVisibility(View.VISIBLE);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit(view);
            }
        });

        Button b2 = binding.button2;
        b2.setVisibility(View.GONE);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Button bAct = getActivity().findViewById(R.id.btnUpdateListOffers);
        bAct.setVisibility(View.VISIBLE);
        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);
        binding = null;
    }

    public void edit(View view) {
        Button b1 = binding.button1;
        b1.setVisibility(View.GONE);
        Button b2 = binding.button2;
        b2.setVisibility(View.VISIBLE);

        EditText etName = binding.etName;
        etName.setText(UserView.user.getName());
        EditText etEmail = binding.etEmailProfile;
        etEmail.setText(UserView.user.getEmail());
        EditText etStudies = binding.etStudiesProfile;
        etStudies.setText(UserView.user.getStudies());
        EditText etCompanyName = binding.etCompanyNameProfile;
        etCompanyName.setText(UserView.user.getCompanyName());

        etName.setEnabled(true);
        etEmail.setEnabled(true);
        etStudies.setEnabled(true);
        etCompanyName.setEnabled(true);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save(view);
            }
        });
    }

    public void save(View view) {
        EditText etName = binding.etName;
        String Sname = etName.getText().toString();

        EditText etEmail = binding.etEmailProfile;
        String Semail = etEmail.getText().toString();

        EditText etStudies = binding.etStudiesProfile;
        String Sstudies = etStudies.getText().toString();

        EditText etCompanyName = binding.etCompanyNameProfile;
        String Scomp = etCompanyName.getText().toString();

        String oldEmail = UserView.user.getEmail();

        UserView.user.setName(Sname);
        UserView.user.setEmail(Semail);
        UserView.user.setStudies(Sstudies);
        UserView.user.setCompanyName(Scomp);

        User u = new User(Sname, Semail, null, UserView.user.getListOffersId(), UserView.user.getDni(), Sstudies, Scomp);

        Manage m = new Manage();
        m.updateUser(getContext(), u, oldEmail);
        etName.setEnabled(false);
        etEmail.setEnabled(false);
        etStudies.setEnabled(false);
        etCompanyName.setEnabled(false);
        Button b1 = binding.button1;
        Button b2 = binding.button2;
        b1.setVisibility(View.VISIBLE);
        b2.setVisibility(View.GONE);
        ((OfferViewerScreen) getActivity()).changeUIProfile();
    }

}