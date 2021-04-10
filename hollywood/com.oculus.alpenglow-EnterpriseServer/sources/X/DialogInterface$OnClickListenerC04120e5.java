package X;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.annotation.VisibleForTesting;

@VisibleForTesting
/* renamed from: X.0e5  reason: invalid class name and case insensitive filesystem */
public class DialogInterface$OnClickListenerC04120e5 implements AnonymousClass04S, DialogInterface.OnClickListener {
    @VisibleForTesting
    public AnonymousClass0N6 A00;
    public ListAdapter A01;
    public CharSequence A02;
    public final /* synthetic */ C04110e4 A03;

    @Override // X.AnonymousClass04S
    public final Drawable A33() {
        return null;
    }

    @Override // X.AnonymousClass04S
    public final int A3g() {
        return 0;
    }

    @Override // X.AnonymousClass04S
    public final int A4r() {
        return 0;
    }

    public DialogInterface$OnClickListenerC04120e5(C04110e4 r1) {
        this.A03 = r1;
    }

    @Override // X.AnonymousClass04S
    public final CharSequence A3e() {
        return this.A02;
    }

    @Override // X.AnonymousClass04S
    public final boolean A5a() {
        AnonymousClass0N6 r0 = this.A00;
        if (r0 != null) {
            return r0.isShowing();
        }
        return false;
    }

    @Override // X.AnonymousClass04S
    public final void A7l(Drawable drawable) {
        Log.e("AppCompatSpinner", "Cannot set popup background for MODE_DIALOG, ignoring");
    }

    @Override // X.AnonymousClass04S
    public final void A7u(int i) {
        Log.e("AppCompatSpinner", "Cannot set horizontal offset for MODE_DIALOG, ignoring");
    }

    @Override // X.AnonymousClass04S
    public final void A7v(int i) {
        Log.e("AppCompatSpinner", "Cannot set horizontal (original) offset for MODE_DIALOG, ignoring");
    }

    @Override // X.AnonymousClass04S
    public final void A8E(int i) {
        Log.e("AppCompatSpinner", "Cannot set vertical offset for MODE_DIALOG, ignoring");
    }

    @Override // X.AnonymousClass04S
    public final void A8Q(int i, int i2) {
        if (this.A01 != null) {
            C04110e4 r3 = this.A03;
            Context popupContext = r3.getPopupContext();
            int A002 = AnonymousClass0N6.A00(popupContext, 0);
            AnonymousClass02e r4 = new AnonymousClass02e(new ContextThemeWrapper(popupContext, AnonymousClass0N6.A00(popupContext, A002)));
            CharSequence charSequence = this.A02;
            if (charSequence != null) {
                r4.A06 = charSequence;
            }
            ListAdapter listAdapter = this.A01;
            int selectedItemPosition = r3.getSelectedItemPosition();
            r4.A05 = listAdapter;
            r4.A01 = this;
            r4.A00 = selectedItemPosition;
            r4.A07 = true;
            AnonymousClass0N6 r2 = new AnonymousClass0N6(r4.A08, A002);
            AnonymousClass02i r1 = r2.A00;
            r4.A00(r1);
            r2.setCancelable(true);
            r2.setCanceledOnTouchOutside(true);
            r2.setOnCancelListener(null);
            r2.setOnDismissListener(null);
            DialogInterface.OnKeyListener onKeyListener = r4.A02;
            if (onKeyListener != null) {
                r2.setOnKeyListener(onKeyListener);
            }
            this.A00 = r2;
            ListView listView = r1.A0K;
            listView.setTextDirection(i);
            listView.setTextAlignment(i2);
            this.A00.show();
        }
    }

    @Override // X.AnonymousClass04S
    public final void dismiss() {
        AnonymousClass0N6 r0 = this.A00;
        if (r0 != null) {
            r0.dismiss();
            this.A00 = null;
        }
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        C04110e4 r3 = this.A03;
        r3.setSelection(i);
        if (r3.getOnItemClickListener() != null) {
            r3.performItemClick(null, i, this.A01.getItemId(i));
        }
        dismiss();
    }

    @Override // X.AnonymousClass04S
    public final void A7j(ListAdapter listAdapter) {
        this.A01 = listAdapter;
    }

    @Override // X.AnonymousClass04S
    public final void A84(CharSequence charSequence) {
        this.A02 = charSequence;
    }
}
