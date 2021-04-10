package X;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentState;

public final class B1 {
    public int A00 = -1;
    @NonNull
    public final Fragment A01;
    public final Ao A02;

    public final void A00(@NonNull ClassLoader classLoader) {
        Fragment fragment = this.A01;
        Bundle bundle = fragment.A08;
        if (bundle != null) {
            bundle.setClassLoader(classLoader);
            fragment.A09 = fragment.A08.getSparseParcelableArray("android:view_state");
            String string = fragment.A08.getString("android:target_state");
            fragment.A0O = string;
            if (string != null) {
                fragment.A06 = fragment.A08.getInt("android:target_req_state", 0);
            }
            boolean z = fragment.A08.getBoolean("android:user_visible_hint", true);
            fragment.A0e = z;
            if (!z) {
                fragment.A0S = true;
            }
        }
    }

    public B1(@NonNull Ao ao, @NonNull Fragment fragment) {
        this.A02 = ao;
        this.A01 = fragment;
    }

    public B1(@NonNull Ao ao, @NonNull Fragment fragment, @NonNull FragmentState fragmentState) {
        String str;
        this.A02 = ao;
        this.A01 = fragment;
        fragment.A09 = null;
        fragment.A02 = 0;
        fragment.A0X = false;
        fragment.A0Q = false;
        Fragment fragment2 = fragment.A0E;
        if (fragment2 != null) {
            str = fragment2.A0P;
        } else {
            str = null;
        }
        fragment.A0O = str;
        fragment.A0E = null;
        Bundle bundle = fragmentState.A00;
        fragment.A08 = bundle == null ? new Bundle() : bundle;
    }

    public B1(@NonNull Ao ao, @NonNull ClassLoader classLoader, @NonNull Al al, @NonNull FragmentState fragmentState) {
        this.A02 = ao;
        this.A01 = al.A01(classLoader, fragmentState.A05);
        Bundle bundle = fragmentState.A04;
        if (bundle != null) {
            bundle.setClassLoader(classLoader);
        }
        Fragment fragment = this.A01;
        Au au = fragment.A0H;
        if (au == null || (!au.A0G && !au.A0H)) {
            fragment.A07 = bundle;
            fragment.A0P = fragmentState.A07;
            fragment.A0U = fragmentState.A09;
            fragment.A0c = true;
            fragment.A04 = fragmentState.A02;
            fragment.A03 = fragmentState.A01;
            fragment.A0N = fragmentState.A06;
            fragment.A0d = fragmentState.A0C;
            fragment.A0b = fragmentState.A0B;
            fragment.A0T = fragmentState.A08;
            fragment.A0V = fragmentState.A0A;
            fragment.A0J = EnumC0040Bp.values()[fragmentState.A03];
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
