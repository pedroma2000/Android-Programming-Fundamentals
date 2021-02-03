package com.example.roomsample;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class NoteDialog extends DialogFragment {

    private NoteListenerInterface mListener;
    private EditText title;
    private EditText description;

    public NoteDialog() {}

     public Dialog onCreateDialog(Bundle savedInstanceState) {
         AlertDialog.Builder b=  new  AlertDialog.Builder(getActivity())
                 .setTitle("Adiciona Nota")
                 .setPositiveButton("OK",
                         new DialogInterface.OnClickListener() {
                             public void onClick(DialogInterface dialog, int whichButton) {
                                 mListener.addNote(title.getText().toString(), description.getText().toString());
                                 dialog.dismiss();
                             }
                         }
                 )
                 .setNegativeButton("Cancel",
                         new DialogInterface.OnClickListener() {
                             public void onClick(DialogInterface dialog, int whichButton) {
                                 dialog.dismiss();
                             }
                         }
                 );

         LayoutInflater i = getActivity().getLayoutInflater();

         View v = i.inflate(R.layout.create_note_fragment,null);

         this.title = v.findViewById(R.id.editText_title);
         this.description = v.findViewById(R.id.editText_description);


         b.setView(v);
         return b.create();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof NoteListenerInterface) {
            mListener = (NoteListenerInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface NoteListenerInterface {
        void addNote(String title, String description);
    }
}
