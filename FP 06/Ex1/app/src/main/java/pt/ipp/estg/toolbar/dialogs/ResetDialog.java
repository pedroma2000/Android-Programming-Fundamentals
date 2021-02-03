package pt.ipp.estg.toolbar.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import pt.ipp.estg.toolbar.Interfaces.IDialogReset;
import pt.ipp.estg.toolbar.R;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class ResetDialog extends DialogFragment {

    private IDialogReset dialogListener;

    public ResetDialog() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        dialogListener = (IDialogReset) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_reset_dialog,null);


        builder.setView(v)
                // Add action buttons
                .setPositiveButton(R.string.Sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialogListener.onDialogPositiveClick(ResetDialog.this);
                    }
                })
                .setNegativeButton(R.string.Nao, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialogListener.onDialogNegativeClick(ResetDialog.this);
                    }
                });

        return builder.create();
    }



}