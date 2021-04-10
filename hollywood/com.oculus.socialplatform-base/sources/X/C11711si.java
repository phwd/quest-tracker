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
import com.oculus.socialplatform.R;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.1si  reason: invalid class name and case insensitive filesystem */
public final class C11711si implements AnonymousClass1t6, AdapterView.OnItemClickListener {
    public Context A00;
    public C11581sN A01;
    public int A02 = R.layout.abc_list_menu_item_layout;
    public LayoutInflater A03;
    public ExpandedMenuView A04;
    public C11761sn A05;
    public AbstractC11941tc A06;

    @Override // X.AnonymousClass1t6
    public final boolean A2B(C11581sN r2, C11601sP r3) {
        return false;
    }

    @Override // X.AnonymousClass1t6
    public final boolean A2v(C11581sN r2, C11601sP r3) {
        return false;
    }

    @Override // X.AnonymousClass1t6
    public final boolean A3F() {
        return false;
    }

    @Override // X.AnonymousClass1t6
    public final void A5e(Context context, C11581sN r3) {
        if (this.A00 != null) {
            this.A00 = context;
            if (this.A03 == null) {
                this.A03 = LayoutInflater.from(context);
            }
        }
        this.A01 = r3;
        C11761sn r0 = this.A05;
        if (r0 != null) {
            r0.notifyDataSetChanged();
        }
    }

    @Override // X.AnonymousClass1t6
    public final void A6r(C11581sN r2, boolean z) {
        AbstractC11941tc r0 = this.A06;
        if (r0 != null) {
            r0.A6r(r2, z);
        }
    }

    @Override // X.AnonymousClass1t6
    public final void AAw(boolean z) {
        C11761sn r0 = this.A05;
        if (r0 != null) {
            r0.notifyDataSetChanged();
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.A01.A0K(this.A05.getItem(i), this, 0);
    }

    public C11711si(Context context) {
        this.A00 = context;
        this.A03 = LayoutInflater.from(context);
    }

    @Override // X.AnonymousClass1t6
    public final boolean A89(SubMenuC11621sV r10) {
        if (!r10.hasVisibleItems()) {
            return false;
        }
        AnonymousClass1sh r3 = new AnonymousClass1sh(r10);
        C11581sN r8 = r3.A02;
        Context context = r8.A0M;
        int A012 = DialogInterfaceC11721sj.A01(context, 0);
        C11741sl r4 = new C11741sl(new ContextThemeWrapper(context, DialogInterfaceC11721sj.A01(context, A012)));
        Context context2 = r4.A08;
        C11711si r2 = new C11711si(context2);
        r3.A01 = r2;
        r2.A9h(r3);
        C11581sN r1 = r3.A02;
        r1.A0E(r2, r1.A0M);
        C11711si r12 = r3.A01;
        C11761sn r0 = r12.A05;
        if (r0 == null) {
            r0 = new C11761sn(r12);
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
        DialogInterfaceC11721sj r13 = new DialogInterfaceC11721sj(context2, A012);
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
        attributes.type = 1003;
        attributes.flags |= 131072;
        r3.A00.show();
        AbstractC11941tc r02 = this.A06;
        if (r02 == null) {
            return true;
        }
        r02.A7T(r10);
        return true;
    }

    @Override // X.AnonymousClass1t6
    public final void A9h(AbstractC11941tc r1) {
        this.A06 = r1;
    }
}
