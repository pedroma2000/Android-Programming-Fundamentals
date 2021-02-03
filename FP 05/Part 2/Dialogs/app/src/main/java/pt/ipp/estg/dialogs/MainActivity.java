package pt.ipp.estg.dialogs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.widget.TextView;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class MainActivity extends AppCompatActivity implements InterfaceDialog {

    private SigninDialog dialog;
    private TextView logged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logged = findViewById(R.id.TextLogged);

        dialog = new SigninDialog();
        dialog.show(getSupportFragmentManager(), "my_dialog");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        if(SigninDialog.verify){
            logged.setText("Logged in!");
        } else {
            System.out.println("Failed login");
            dialog.dismiss();
            finish();
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        dialog.dismiss();
        finish();
    }
}