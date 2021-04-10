package X;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentState;

/* renamed from: X.0Cq  reason: invalid class name */
public final class AnonymousClass0Cq {
    public int A00 = -1;
    @NonNull
    public final AnonymousClass0MN A01;
    public final AnonymousClass0Cd A02;

    public final void A00(@NonNull ClassLoader classLoader) {
        AnonymousClass0MN r3 = this.A01;
        Bundle bundle = r3.A08;
        if (bundle != null) {
            bundle.setClassLoader(classLoader);
            r3.A09 = r3.A08.getSparseParcelableArray("android:view_state");
            String string = r3.A08.getString("android:target_state");
            r3.A0O = string;
            if (string != null) {
                r3.A06 = r3.A08.getInt("android:target_req_state", 0);
            }
            boolean z = r3.A08.getBoolean("android:user_visible_hint", true);
            r3.A0e = z;
            if (!z) {
                r3.A0S = true;
            }
        }
    }

    public AnonymousClass0Cq(@NonNull AnonymousClass0Cd r2, @NonNull AnonymousClass0MN r3) {
        this.A02 = r2;
        this.A01 = r3;
    }

    public AnonymousClass0Cq(@NonNull AnonymousClass0Cd r3, @NonNull AnonymousClass0MN r4, @NonNull FragmentState fragmentState) {
        String str;
        this.A02 = r3;
        this.A01 = r4;
        r4.A09 = null;
        r4.A02 = 0;
        r4.A0X = false;
        r4.A0Q = false;
        AnonymousClass0MN r0 = r4.A0E;
        if (r0 != null) {
            str = r0.A0P;
        } else {
            str = null;
        }
        r4.A0O = str;
        r4.A0E = null;
        Bundle bundle = fragmentState.A00;
        r4.A08 = bundle == null ? new Bundle() : bundle;
    }

    public AnonymousClass0Cq(@NonNull AnonymousClass0Cd r5, @NonNull ClassLoader classLoader, @NonNull C00940Ca r7, @NonNull FragmentState fragmentState) {
        this.A02 = r5;
        this.A01 = r7.A01(classLoader, fragmentState.A05);
        Bundle bundle = fragmentState.A04;
        if (bundle != null) {
            bundle.setClassLoader(classLoader);
        }
        AnonymousClass0MN r2 = this.A01;
        AnonymousClass0Cj r1 = r2.A0H;
        if (r1 == null || (!r1.A0G && !r1.A0H)) {
            r2.A07 = bundle;
            r2.A0P = fragmentState.A07;
            r2.A0U = fragmentState.A09;
            r2.A0c = true;
            r2.A04 = fragmentState.A02;
            r2.A03 = fragmentState.A01;
            r2.A0N = fragmentState.A06;
            r2.A0d = fragmentState.A0C;
            r2.A0b = fragmentState.A0B;
            r2.A0T = fragmentState.A08;
            r2.A0V = fragmentState.A0A;
            r2.A0J = AnonymousClass0DX.values()[fragmentState.A03];
            Bundle bundle2 = fragmentState.A00;
            if (bundle2 != null) {
                this.A01.A08 = bundle2;
                return;
            }
            this.A01.A08 = new Bundle();
            return;
        }
        throw new IllegalStateException("Fragment already added and state has been saved");
    }
}
