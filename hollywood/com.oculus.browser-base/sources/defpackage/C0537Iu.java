package defpackage;

import J.N;
import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.preference.Preference;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.chrome.browser.browsing_data.BrowsingDataBridge;
import org.chromium.chrome.browser.browsing_data.BrowsingDataCounterBridge;
import org.chromium.chrome.browser.browsing_data.ClearBrowsingDataCheckBoxPreference;
import org.chromium.chrome.browser.browsing_data.ClearBrowsingDataFragment;

/* renamed from: Iu  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0537Iu implements AbstractC0334Fk, YE0 {
    public final ClearBrowsingDataFragment F;
    public final int G;
    public final ClearBrowsingDataCheckBoxPreference H;
    public BrowsingDataCounterBridge I;

    /* renamed from: J  reason: collision with root package name */
    public boolean f8255J;

    public C0537Iu(Context context, ClearBrowsingDataFragment clearBrowsingDataFragment, int i, ClearBrowsingDataCheckBoxPreference clearBrowsingDataCheckBoxPreference, boolean z, boolean z2) {
        int i2;
        this.F = clearBrowsingDataFragment;
        this.G = i;
        this.H = clearBrowsingDataCheckBoxPreference;
        this.I = new BrowsingDataCounterBridge(this, ClearBrowsingDataFragment.n1(i), clearBrowsingDataFragment.m1());
        clearBrowsingDataCheckBoxPreference.K = this;
        clearBrowsingDataCheckBoxPreference.K(z2);
        clearBrowsingDataCheckBoxPreference.a0(z);
        if (clearBrowsingDataFragment.I().getConfiguration().smallestScreenWidthDp >= 360) {
            if (i == 0) {
                i2 = R.drawable.f33110_resource_name_obfuscated_RES_2131231351;
            } else if (i == 1) {
                i2 = R.drawable.f34470_resource_name_obfuscated_RES_2131231487;
            } else if (i == 2) {
                i2 = R.drawable.f29790_resource_name_obfuscated_RES_2131231019;
            } else if (i == 3) {
                i2 = R.drawable.f33060_resource_name_obfuscated_RES_2131231346;
            } else if (i == 4) {
                i2 = R.drawable.f30010_resource_name_obfuscated_RES_2131231041;
            } else if (i == 5) {
                i2 = R.drawable.f32870_resource_name_obfuscated_RES_2131231327;
            } else {
                throw new IllegalArgumentException();
            }
            Drawable b = AbstractC2870hT0.b(context, i2);
            if (clearBrowsingDataCheckBoxPreference.P != b) {
                clearBrowsingDataCheckBoxPreference.P = b;
                clearBrowsingDataCheckBoxPreference.O = 0;
                clearBrowsingDataCheckBoxPreference.s();
            }
        }
    }

    @Override // defpackage.YE0
    public boolean d(Preference preference) {
        ClearBrowsingDataFragment clearBrowsingDataFragment = this.F;
        int i = ClearBrowsingDataFragment.G0;
        clearBrowsingDataFragment.u1();
        this.f8255J = true;
        BrowsingDataBridge c = BrowsingDataBridge.c();
        int n1 = ClearBrowsingDataFragment.n1(this.G);
        int m1 = this.F.m1();
        boolean z = this.H.t0;
        Objects.requireNonNull(c);
        N.MBI7g3zY(c, n1, m1, z);
        return true;
    }
}
