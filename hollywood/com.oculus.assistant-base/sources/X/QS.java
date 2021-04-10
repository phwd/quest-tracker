package X;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public final class QS extends DialogFragment {
    public Dialog A00;
    public DialogInterface.OnCancelListener A01;
    public Dialog A02;

    public final void onCancel(DialogInterface dialogInterface) {
        DialogInterface.OnCancelListener onCancelListener = this.A01;
        if (onCancelListener != null) {
            onCancelListener.onCancel(dialogInterface);
        }
    }

    public final Dialog onCreateDialog(Bundle bundle) {
        Dialog dialog = this.A00;
        if (dialog != null) {
            return dialog;
        }
        setShowsDialog(false);
        Dialog dialog2 = this.A02;
        if (dialog2 != null) {
            return dialog2;
        }
        AlertDialog create = new AlertDialog.Builder(getActivity()).create();
        this.A02 = create;
        return create;
    }
}
