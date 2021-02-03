package pt.ipp.estg.tourpediaexample.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import pt.ipp.estg.tourpediaexample.ConverterFactory;
import pt.ipp.estg.tourpediaexample.Models.POI;
import pt.ipp.estg.tourpediaexample.POIadapter;
import pt.ipp.estg.tourpediaexample.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class ListFragment extends Fragment {

    private RecyclerView recyclerList;
    private Context mContext;
    private POIadapter myAdapter;

    private String Location;
    private String Name;

    private ConverterFactory api = new ConverterFactory();

    public ListFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle!=null){
            this.Location = bundle.getString("LOCATION");
            this.Name = bundle.getString("NAME");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_list, container, false);
        recyclerList = v.findViewById(R.id.locations_recycler);

        api.getApi().getListPointsOfInterest(Location,Name)
                .enqueue(new Callback<List<POI>>() {
                    @Override
                    public void onResponse(Call<List<POI>> call, Response<List<POI>> response) {
                        List<POI> points = response.body();
                        myAdapter = new POIadapter(mContext, points);

                        recyclerList.setAdapter(myAdapter);
                        recyclerList.setLayoutManager(new LinearLayoutManager(mContext));
                        recyclerList.addItemDecoration(new DividerItemDecoration(mContext,
                                DividerItemDecoration.VERTICAL));
                    }

                    @Override
                    public void onFailure(Call<List<POI>> call, Throwable t) {
                        System.out.printf("ERROR");
                    }
                });


        return v;
    }
}