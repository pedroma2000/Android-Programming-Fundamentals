package pt.ipp.estg.fragments.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import pt.ipp.estg.fragments.CustomInterface;
import pt.ipp.estg.fragments.MainActivity;
import pt.ipp.estg.fragments.R;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class Fragment_1 extends Fragment {
    Button bf1;
    EditText ef1;

    CustomInterface ctx;

    public Fragment_1() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_1.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_1 newInstance(String param1, String param2) {
        Fragment_1 fragment = new Fragment_1();

        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.ctx = (CustomInterface) context;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_1, container, false);

        bf1 = v.findViewById(R.id.button);
        ef1 = v.findViewById(R.id.editTextTextPersonName);

        bf1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ctx.myMethod(ef1.getText().toString());
            }
        });

        return v;
    }
}