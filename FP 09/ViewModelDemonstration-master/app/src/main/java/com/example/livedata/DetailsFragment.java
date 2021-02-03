package com.example.livedata;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class DetailsFragment extends Fragment {

    private WordViewModel liveData;
    private TextView t;

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        liveData = ViewModelProviders.of(getActivity()).get(WordViewModel.class);
        liveData.getWord().observe(this, item -> {
            t.setText(item);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_details, container, false);
        t = v.findViewById(R.id.text2);

        return v;
    }

}
