package pt.ipp.estg.tourpediaexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

import pt.ipp.estg.tourpediaexample.Models.POI;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class POIadapter extends RecyclerView.Adapter<POIadapter.POIViewHolder>{

    private Context mContext;
    private List<POI> mLocations;
    private String location;
    private String name;

    public POIadapter(Context mContext, List<POI> poi) {
        this.mContext = mContext;
        this.mLocations = poi;
    }

    @NonNull
    @Override
    public POIViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View locationsView = inflater.inflate(R.layout.recycler_item, parent, false);

        return new POIViewHolder(locationsView);
    }

    @Override
    public void onBindViewHolder(@NonNull POIViewHolder holder, int position) {
        POI poi = mLocations.get(position);

        TextView nameTextView = holder.LocationName;
        nameTextView.setText(poi.getName());

        Button button = holder.itemButton;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LocationsActivity) mContext).ReplaceDetails(poi);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLocations.size();
    }

    public class POIViewHolder extends RecyclerView.ViewHolder {

        public TextView LocationName;
        public Button itemButton;

        public POIViewHolder(View itemView) {
            super(itemView);
            LocationName = itemView.findViewById(R.id.location_name);
            itemButton = itemView.findViewById(R.id.details_button);
        }
    }
}



