package defpackage;

import android.view.View;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.chrome.browser.vr.VrModuleProvider;

/* renamed from: PT  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PT implements View.OnLayoutChangeListener {
    public final /* synthetic */ View F;
    public final /* synthetic */ ST G;

    public PT(ST st, View view) {
        this.G = st;
        this.F = view;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.G.G.sendEmptyMessage(1);
        if (i4 - i2 > i8 - i6) {
            Objects.requireNonNull(this.G);
            Objects.requireNonNull(VrModuleProvider.b());
            if (!VrModuleProvider.b().a()) {
                ST st = this.G;
                C1184Ti1 ti1 = st.P;
                if (ti1 != null) {
                    ti1.b.cancel();
                }
                C1184Ti1 a2 = C1184Ti1.a(st.F, R.string.f53020_resource_name_obfuscated_RES_2131952619, 1);
                st.P = a2;
                a2.b.setGravity(49, 0, 0);
                st.P.b.show();
            }
            this.F.removeOnLayoutChangeListener(this);
        }
    }
}
