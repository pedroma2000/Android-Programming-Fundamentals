package pt.ipp.estg.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder>{
    private Context mContext;
    private List<Contacto> mContacts;

    public ContactAdapter(Context mContext, List<Contacto> mContacts) {
        this.mContext = mContext;
        this.mContacts = mContacts;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_contact, parent, false);

        return new ContactViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contacto contact = mContacts.get(position);

        TextView textView = holder.nameTextView;
        textView.setText(contact.getNome());

        Button button = holder.messageButton;
        button.setText(contact.isOnline() ? "Message" : "Offline");
        button.setEnabled(contact.isOnline());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, MainActivity2.class);
                i.putExtra("CONTACT", contact);
                mContext.startActivity(i);
            }
        });

        ImageView image = holder.contactStatus;
        if (contact.isOnline()) {
            image.setImageResource(R.drawable.green);
        } else {
            image.setImageResource(R.drawable.red);
        }


        Log.d("CONTACT_ADAPTER", "onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public Button messageButton;
        public ImageView contactStatus;

        public ContactViewHolder(View itemView) {
            super(itemView);

            contactStatus = itemView.findViewById(R.id.imageView2);
            nameTextView = itemView.findViewById(R.id.textView);
            messageButton = itemView.findViewById(R.id.button);
        }
    }

}
