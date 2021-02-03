package com.example.livedata;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class InputDialog extends DialogFragment {


    private EditText description;
    private WordViewModel liveData;

    public InputDialog() {}

     public Dialog onCreateDialog(Bundle savedInstanceState) {
         liveData = ViewModelProviders.of(getActivity()).get(WordViewModel.class);
         description = getActivity().findViewById(R.id.editText_description);

         AlertDialog.Builder b=  new  AlertDialog.Builder(getActivity())
                 .setTitle("Adiciona Mensagem")
                 .setPositiveButton("OK",
                         new DialogInterface.OnClickListener() {
                             public void onClick(DialogInterface dialog, int whichButton) {
                                liveData.setWord(description.getText().toString());
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

         View v = i.inflate(R.layout.create_input_fragment,null);

         this.description = v.findViewById(R.id.editText_description);


         b.setView(v);
         return b.create();
    }


}
