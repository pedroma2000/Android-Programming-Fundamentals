package pt.ipp.estg.fragments.fragments;

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

import java.util.ArrayList;
import java.util.List;

import pt.ipp.estg.fragments.R;
import pt.ipp.estg.fragments.adapters.CityAdapter;
import pt.ipp.estg.fragments.entities.City;


public class ListFragment extends Fragment {

    private RecyclerView recyclerList;
    private Context mContext;
    private CityAdapter myAdapter;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_list, container, false);
        recyclerList = v.findViewById(R.id.countryRecycler);

        List<City> cities = createCities();
        myAdapter = new CityAdapter(mContext, cities);


        recyclerList.setAdapter(myAdapter);
        recyclerList.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerList.addItemDecoration(new DividerItemDecoration(mContext,
                DividerItemDecoration.VERTICAL));

        return v;
    }

    private List<City> createCities(){
        List<City> tmp = new ArrayList<>();

        tmp.add(new City("Lisboa","Portugal","Lisboa é a capital de Portugal, " +
                "situada na costa. Do imponente Castelo de São Jorge, a vista abrange as construções " +
                "em tons pastel da cidade antiga, o estuário do Tejo e a Ponte 25 de Abril. Perto dali, " +
                "o Museu Nacional do Azulejo exibe 5 séculos de azulejos decorativos. Nas proximidades de Lisboa, " +
                "há uma sequência de praias do Atlântico, entre elas Cascais e Estoril."));

        tmp.add(new City("Nova Iorque","USA","A cidade de Nova York compreende " +
                "5 distritos situados no encontro do rio Hudson com o Oceano Atlântico. No centro da " +
                "cidade fica Manhattan, um distrito com alta densidade demográfica que está entre os " +
                "principais centros comerciais, financeiros e culturais do mundo. Entre seus pontos " +
                "emblemáticos, destacam-se arranha-céus, como o Empire State Building, e o enorme Central Park. " +
                "O teatro da Broadway fica em meio às luzes de neon da Times Square."));

        return tmp;
    }
}