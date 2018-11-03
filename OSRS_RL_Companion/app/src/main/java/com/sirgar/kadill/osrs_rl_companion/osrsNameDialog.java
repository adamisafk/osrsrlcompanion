package com.sirgar.kadill.osrs_rl_companion;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class osrsNameDialog extends AppCompatDialogFragment {
    private EditText editTextUsername;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String loginName;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.osrs_name_dialog_layout, null);

        builder.setView(view)
            .setTitle("OSRS Login")
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            })
            .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String loginName = editTextUsername.getText().toString();
                    //saves login name input into the accounts class
                    accounts.initialOsrsName = loginName;
                }
            });
            editTextUsername = view.findViewById(R.id.usernameField);

            return builder.create();
    }
}
