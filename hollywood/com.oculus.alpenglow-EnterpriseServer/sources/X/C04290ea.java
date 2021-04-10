package X;

import android.content.Context;
import android.content.DialogInterface;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import androidx.annotation.RestrictTo;
import androidx.appcompat.view.menu.ExpandedMenuView;
import com.oculus.alpenglow.R;
import com.oculus.alpenglow.constants.Constants;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.0ea  reason: invalid class name and case insensitive filesystem */
public final class C04290ea implements AbstractC000603b, AdapterView.OnItemClickListener {
    public Context A00;
    public C04280eZ A01;
    public int A02 = R.layout.abc_list_menu_item_layout;
    public LayoutInflater A03;
    public ExpandedMenuView A04;
    public AnonymousClass03T A05;
    public AbstractC000503a A06;

    @Override // X.AbstractC000603b
    public final boolean A1k(C04280eZ r2, C04250eW r3) {
        return false;
    }

    @Override // X.AbstractC000603b
    public final boolean A2V(C04280eZ r2, C04250eW r3) {
        return false;
    }

    @Override // X.AbstractC000603b
    public final boolean A2p() {
        return false;
    }

    @Override // X.AbstractC000603b
    public final void A5E(Context context, C04280eZ r3) {
        if (this.A00 != null) {
            this.A00 = context;
            if (this.A03 == null) {
                this.A03 = LayoutInflater.from(context);
            }
        }
        this.A01 = r3;
        AnonymousClass03T r0 = this.A05;
        if (r0 != null) {
            r0.notifyDataSetChanged();
        }
    }

    @Override // X.AbstractC000603b
    public final void A5x(C04280eZ r2, boolean z) {
        AbstractC000503a r0 = this.A06;
        if (r0 != null) {
            r0.A5x(r2, z);
        }
    }

    @Override // X.AbstractC000603b
    public final void A8k(boolean z) {
        AnonymousClass03T r0 = this.A05;
        if (r0 != null) {
            r0.notifyDataSetChanged();
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.A01.A0K(this.A05.getItem(i), this, 0);
    }

    public C04290ea(Context context) {
        this.A00 = context;
        this.A03 = LayoutInflater.from(context);
    }

    @Override // X.AbstractC000603b
    public final boolean A6e(SubMenuC01890Mu r10) {
        if (!r10.hasVisibleItems()) {
            return false;
        }
        DialogInterface$OnClickListenerC04270eY r3 = new DialogInterface$OnClickListenerC04270eY(r10);
        C04280eZ r8 = r3.A02;
        Context context = r8.A0M;
        int A002 = AnonymousClass0N6.A00(context, 0);
        AnonymousClass02e r4 = new AnonymousClass02e(new ContextThemeWrapper(context, AnonymousClass0N6.A00(context, A002)));
        Context context2 = r4.A08;
        C04290ea r2 = new C04290ea(context2);
        r3.A01 = r2;
        r2.A7m(r3);
        C04280eZ r1 = r3.A02;
        r1.A0E(r2, r1.A0M);
        C04290ea r12 = r3.A01;
        AnonymousClass03T r0 = r12.A05;
        if (r0 == null) {
            r0 = new AnonymousClass03T(r12);
            r12.A05 = r0;
        }
        r4.A05 = r0;
        r4.A01 = r3;
        View view = r8.A02;
        if (view != null) {
            r4.A04 = view;
        } else {
            r4.A03 = r8.A01;
            r4.A06 = r8.A05;
        }
        r4.A02 = r3;
        AnonymousClass0N6 r13 = new AnonymousClass0N6(context2, A002);
        r4.A00(r13.A00);
        r13.setCancelable(true);
        r13.setCanceledOnTouchOutside(true);
        r13.setOnCancelListener(null);
        r13.setOnDismissListener(null);
        DialogInterface.OnKeyListener onKeyListener = r4.A02;
        if (onKeyListener != null) {
            r13.setOnKeyListener(onKeyListener);
        }
        r3.A00 = r13;
        r13.setOnDismissListener(r3);
        WindowManager.LayoutParams attributes = r3.A00.getWindow().getAttributes();
        attributes.type = Constants.OTA_SCHEDULE_WINDOW_END_JOB_ID;
        attributes.flags |= 131072;
        r3.A00.show();
        AbstractC000503a r02 = this.A06;
        if (r02 == null) {
            return true;
        }
        r02.A6L(r10);
        return true;
    }

    @Override // X.AbstractC000603b
    public final void A7m(AbstractC000503a r1) {
        this.A06 = r1;
    }
}
