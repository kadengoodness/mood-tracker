package com.kadengood.moodtracker.controller;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.kadengood.moodtracker.R;

/**
 * Created by kadengood on 3/5/19.
 */
public class CommentDialog extends AppCompatDialogFragment {
    private EditText editTextComment;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_comment, null);

        builder.setView(view)
                .setTitle("Commentaire")
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String comment = editTextComment.getText().toString();

                    }

                });

        editTextComment = view.findViewById(R.id.edit_comment);

        return builder.create();
    }

}
