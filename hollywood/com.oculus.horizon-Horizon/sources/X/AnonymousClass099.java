package X;

import android.app.Dialog;
import android.content.DialogInterface;
import androidx.annotation.Nullable;

/* renamed from: X.099  reason: invalid class name */
public class AnonymousClass099 implements DialogInterface.OnDismissListener {
    public final /* synthetic */ AnonymousClass0AH A00;

    public AnonymousClass099(AnonymousClass0AH r1) {
        this.A00 = r1;
    }

    public final void onDismiss(@Nullable DialogInterface dialogInterface) {
        AnonymousClass0AH r1 = this.A00;
        Dialog dialog = r1.mDialog;
        if (dialog != null) {
            r1.onDismiss(dialog);
        }
    }
}
