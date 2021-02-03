package pt.ipp.estg.fragments.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pt.ipp.estg.fragments.MainActivity;
import pt.ipp.estg.fragments.R;
import pt.ipp.estg.fragments.entities.City;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder>{

    private Context mContext;
    private List<City> mCities;

    public CityAdapter(Context mContext, List<City> mCities) {
        this.mContext = mContext;
        this.mCities = mCities;
    }

    @NonNull
    @Override
    public CityAdapter.CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.recycler_item, parent, false);

        return new CityViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull CityAdapter.CityViewHolder holder, int position) {
        City city = mCities.get(position);

        TextView nameTextView = holder.nameTextView;
        nameTextView.setText(city.getName());

        TextView countryTextView = holder.countryTextView;
        countryTextView.setText(city.getCountry());

        Button button = holder.itemButton;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) mContext).ReplaceDetails(city);
            }
        });

        Log.d("CITY_ADAPTER", "onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        return mCities.size();
    }

    public class CityViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public TextView countryTextView;
        public Button itemButton;

        public CityViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textView9);
            countryTextView = itemView.findViewById(R.id.textView10);
            itemButton = itemView.findViewById(R.id.button2);
        }
    }
}
