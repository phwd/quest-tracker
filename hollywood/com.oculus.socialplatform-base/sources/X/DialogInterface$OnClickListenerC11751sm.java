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
/* renamed from: X.1sm  reason: invalid class name and case insensitive filesystem */
public class DialogInterface$OnClickListenerC11751sm implements AbstractC11811ss, DialogInterface.OnClickListener {
    @VisibleForTesting
    public DialogInterfaceC11721sj A00;
    public ListAdapter A01;
    public CharSequence A02;
    public final /* synthetic */ AnonymousClass1sR A03;

    @Override // X.AbstractC11811ss
    public final Drawable A3Q() {
        return null;
    }

    @Override // X.AbstractC11811ss
    public final int A47() {
        return 0;
    }

    @Override // X.AbstractC11811ss
    public final int A5H() {
        return 0;
    }

    public DialogInterface$OnClickListenerC11751sm(AnonymousClass1sR r1) {
        this.A03 = r1;
    }

    @Override // X.AbstractC11811ss
    public final CharSequence A46() {
        return this.A02;
    }

    @Override // X.AbstractC11811ss
    public final boolean A6B() {
        DialogInterfaceC11721sj r0 = this.A00;
        if (r0 != null) {
            return r0.isShowing();
        }
        return false;
    }

    @Override // X.AbstractC11811ss
    public final void A9f(Drawable drawable) {
        Log.e("AppCompatSpinner", "Cannot set popup background for MODE_DIALOG, ignoring");
    }

    @Override // X.AbstractC11811ss
    public final void A9u(int i) {
        Log.e("AppCompatSpinner", "Cannot set horizontal offset for MODE_DIALOG, ignoring");
    }

    @Override // X.AbstractC11811ss
    public final void A9v(int i) {
        Log.e("AppCompatSpinner", "Cannot set horizontal (original) offset for MODE_DIALOG, ignoring");
    }

    @Override // X.AbstractC11811ss
    public final void AAG(int i) {
        Log.e("AppCompatSpinner", "Cannot set vertical offset for MODE_DIALOG, ignoring");
    }

    @Override // X.AbstractC11811ss
    public final void AAP(int i, int i2) {
        if (this.A01 != null) {
            AnonymousClass1sR r3 = this.A03;
            Context popupContext = r3.getPopupContext();
            int A012 = DialogInterfaceC11721sj.A01(popupContext, 0);
            C11741sl r4 = new C11741sl(new ContextThemeWrapper(popupContext, DialogInterfaceC11721sj.A01(popupContext, A012)));
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
            DialogInterfaceC11721sj r2 = new DialogInterfaceC11721sj(r4.A08, A012);
            C11731sk r1 = r2.A00;
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

    @Override // X.AbstractC11811ss
    public final void dismiss() {
        DialogInterfaceC11721sj r0 = this.A00;
        if (r0 != null) {
            r0.dismiss();
            this.A00 = null;
        }
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        AnonymousClass1sR r3 = this.A03;
        r3.setSelection(i);
        if (r3.getOnItemClickListener() != null) {
            r3.performItemClick(null, i, this.A01.getItemId(i));
        }
        dismiss();
    }

    @Override // X.AbstractC11811ss
    public final void A9e(ListAdapter listAdapter) {
        this.A01 = listAdapter;
    }

    @Override // X.AbstractC11811ss
    public final void AA6(CharSequence charSequence) {
        this.A02 = charSequence;
    }
}
