package pt.ipp.estg.dialogs;

import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public interface InterfaceDialog {
    public void onDialogPositiveClick(DialogFragment dialog);
    public void onDialogNegativeClick(DialogFragment dialog);

}
