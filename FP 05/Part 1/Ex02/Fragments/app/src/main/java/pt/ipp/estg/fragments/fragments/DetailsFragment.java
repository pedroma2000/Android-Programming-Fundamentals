package pt.ipp.estg.fragments.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pt.ipp.estg.fragments.R;
import pt.ipp.estg.fragments.entities.City;

public class DetailsFragment extends Fragment {

    private TextView nameText;
    private TextView countryText;
    private TextView descriptionText;
    private City city;

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle!=null){
            city= (City) bundle.getSerializable("CITY");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_details, container, false);

        nameText = v.findViewById(R.id.textView4);
        countryText = v.findViewById(R.id.textView5);
        descriptionText = v.findViewById(R.id.textView);

        if(city!=null){
            nameText.setText(city.getName());
            countryText.setText(city.getCountry());
            descriptionText.setText(city.getDescription());
        } else {
            nameText.setText("Nome");
            countryText.setText("País");
            descriptionText.setText("Descrição");
        }



        return v;
    }

    public void update(City newCity){
        nameText.setText(newCity.getName());
        countryText.setText(newCity.getCountry());
        descriptionText.setText(newCity.getDescription());
    }

}