package pt.ipp.estg.toolbarandpreferences.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pt.ipp.estg.toolbarandpreferences.MainActivity;
import pt.ipp.estg.toolbarandpreferences.R;
import pt.ipp.estg.toolbarandpreferences.models.Contact;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private Context mContext;
    private List<Contact> mContacts;

    public ContactAdapter(Context mContext) {
        this.mContext = mContext;
        MainActivity.databaseWriteExecutor.execute(() -> {
            mContacts = MainActivity.myDB.daoAccess().loadAllContacts();
        });
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.recycler_contact, parent, false);

        return new ContactViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = mContacts.get(position);

        TextView nameView = holder.nameTextView;
        nameView.setText(contact.Name);

        TextView numberView = holder.phoneNumber;
        numberView.setText(contact.PhoneNumber);

        Button button = holder.deleteButton;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.databaseWriteExecutor.execute(() -> {
                    MainActivity.myDB.daoAccess().deleteContacts(contact);
                    mContacts = MainActivity.myDB.daoAccess().loadAllContacts();
                });
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder{
        public TextView nameTextView;
        public TextView phoneNumber;
        public Button deleteButton;

        public ContactViewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.textView_name);
            phoneNumber = itemView.findViewById(R.id.textView_phone);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }
    }
}
