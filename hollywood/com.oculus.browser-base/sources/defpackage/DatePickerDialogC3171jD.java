package defpackage;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.DatePicker;

/* renamed from: jD  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DatePickerDialogC3171jD extends DatePickerDialog {
    public final DatePickerDialog.OnDateSetListener F;

    public DatePickerDialogC3171jD(Context context, DatePickerDialog.OnDateSetListener onDateSetListener, int i, int i2, int i3) {
        super(context, onDateSetListener, i, i2, i3);
        this.F = onDateSetListener;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == -1 && this.F != null) {
            DatePicker datePicker = getDatePicker();
            datePicker.clearFocus();
            this.F.onDateSet(datePicker, datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
        }
    }

    @Override // android.app.AlertDialog, android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle("");
    }
}
