package pt.ipp.estg.simplefragment.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pt.ipp.estg.simplefragment.R;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class Fragment_2 extends Fragment {

    TextView tv2;

    public Fragment_2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_2, container, false);

        tv2 = v.findViewById(R.id.textViewFrag2);
        tv2.setText(getArguments().getString("VALUE"));
        return v;
    }
}