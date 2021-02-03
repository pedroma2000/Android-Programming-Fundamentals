package pt.ipp.estg.toolbar.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pt.ipp.estg.toolbar.DetailsActivity;
import pt.ipp.estg.toolbar.R;
import pt.ipp.estg.toolbar.models.Contact;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder>{

    private Context mContext;
    private List<Contact> mContacts;

    public ContactAdapter(Context mContext, List<Contact> mContacts) {
        this.mContext = mContext;
        this.mContacts = mContacts;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.recycler_item, parent, false);

        return new ContactViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = mContacts.get(position);

        TextView textView = holder.nameTextView;
        textView.setText(contact.getNome());

        Button button = holder.messageButton;
        button.setText(contact.isOnline() ? "Message" : "Offline");
        button.setEnabled(contact.isOnline());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, DetailsActivity.class);
                i.putExtra("CONTACT", contact);
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }


    public class ContactViewHolder extends RecyclerView.ViewHolder{

        public TextView nameTextView;
        public Button messageButton;

        public ContactViewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.textView_name);
            messageButton = itemView.findViewById(R.id.message_button);

        }
    }
}
