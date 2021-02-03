package com.example.livedata;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 *
 * @author Pedro Machado pedroma2000
 */
 */
public class MasterFragment extends Fragment {

    private TextView t;
    private WordViewModel liveData;
    private InputDialog dialog;

    public MasterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        liveData = ViewModelProviders.of(getActivity()).get(WordViewModel.class);
        liveData.getWord().observe(this, word -> {t.setText(word);});
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_master, container, false);

        t = v.findViewById(R.id.text1);

        Button b = v.findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new InputDialog();
                dialog.show(getFragmentManager(), "my_dialog");
            }
        });

        return v;
    }

}
