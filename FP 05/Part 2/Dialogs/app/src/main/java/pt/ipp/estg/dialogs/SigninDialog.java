package pt.ipp.estg.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class SigninDialog extends DialogFragment {

    private InterfaceDialog dialogListener;
    private TextView userName;
    private TextView password;
    public static boolean verify = false;

    public SigninDialog() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        dialogListener = (InterfaceDialog) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_signin,null);

        userName = v.findViewById(R.id.username);
        password = v.findViewById(R.id.password);

        builder.setView(v)
                // Add action buttons
                .setPositiveButton(R.string.signin, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        verify = verify(userName.getText().toString(), password.getText().toString());
                        dialogListener.onDialogPositiveClick(SigninDialog.this);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialogListener.onDialogNegativeClick(SigninDialog.this);
                    }
                });

        return builder.create();
    }

    private boolean verify(String username, String password){

        if(username.equals("jorge") && password.equals("miguel")){
            System.out.println("Correct credentials");
            return true;
        } else{
            System.out.println("Diffetrent credentials");
            return false;
        }

    }
}