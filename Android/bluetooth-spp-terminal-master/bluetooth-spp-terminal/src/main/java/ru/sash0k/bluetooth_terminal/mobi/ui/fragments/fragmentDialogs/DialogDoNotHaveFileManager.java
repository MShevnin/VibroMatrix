package ru.sash0k.bluetooth_terminal.mobi.ui.fragments.fragmentDialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import ru.sash0k.bluetooth_terminal.R;


public class DialogDoNotHaveFileManager extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle(getActivity().getString(R.string.message_dialog_title))
                .setMessage(getActivity().getString(R.string.no_file_manager))
                .setPositiveButton(getActivity().getString(R.string.ok_button), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create();
    }
}
