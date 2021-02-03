package pt.ipp.estg.tourpediaexample.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pt.ipp.estg.tourpediaexample.Models.POI;
import pt.ipp.estg.tourpediaexample.R;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class DetailsFragment extends Fragment {

    private TextView id;
    private TextView name;
    private TextView address;
    private TextView category;
    private TextView location;
    private TextView lat;
    private TextView lon;
    private TextView details;

    private POI point;


    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle!=null){
            point = (POI) bundle.getSerializable("POI");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_details, container, false);

        id = v.findViewById(R.id.obj_ID);
        name = v.findViewById(R.id.obj_name);
        address = v.findViewById(R.id.obj_address);
        category = v.findViewById(R.id.obj_category);
        location = v.findViewById(R.id.obj_location);
        lat = v.findViewById(R.id.obj_lat);
        lon = v.findViewById(R.id.obj_long);
        details = v.findViewById(R.id.obj_details);

        id.setText(String.valueOf(point.getId()));
        name.setText(point.getName());
        address.setText(point.getAddress());
        category.setText(point.getCategory());
        location.setText(point.getLocation());
        lat.setText(String.valueOf(point.getLat()));
        lon.setText(String.valueOf(point.getLng()));
        details.setText(point.getDetails());

        return v;
    }
}