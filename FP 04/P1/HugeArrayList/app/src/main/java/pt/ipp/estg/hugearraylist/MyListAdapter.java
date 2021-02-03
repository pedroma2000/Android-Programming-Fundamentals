package pt.ipp.estg.hugearraylist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class MyListAdapter extends ArrayAdapter<Contact> {

    private Context mContext;
    private List<Contact> mContacts;

    public MyListAdapter(@NonNull Context context, int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mContacts = objects;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.row_layout, null);
        }

        Contact contact = mContacts.get(position);

        TextView txtName = (TextView) v.findViewById(R.id.textView);
        txtName.setText(contact.getName());


        return v;
    }

}
