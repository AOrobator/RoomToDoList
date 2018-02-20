package com.c4q.roomtodolist;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

public class AddTaskDialogFragment extends DialogFragment {
  @NonNull @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

    LayoutInflater inflater = getActivity().getLayoutInflater();
    View view = inflater.inflate(R.layout.add_task_dialog_view, null);
    final TextInputEditText editText = view.findViewById(R.id.task_name_edit_text);

    builder.setTitle("Add Task")
        .setView(view)
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
          @Override public void onClick(DialogInterface dialog, int which) {

          }
        })
    .setPositiveButton("Add", new DialogInterface.OnClickListener() {
      @Override public void onClick(DialogInterface dialog, int which) {
        addTask(editText.getText().toString());
      }
    });
    return builder.create();
  }

  private void addTask(String taskName) {
    ((MainActivity) getActivity()).presenter.addTask(taskName);
  }
}
