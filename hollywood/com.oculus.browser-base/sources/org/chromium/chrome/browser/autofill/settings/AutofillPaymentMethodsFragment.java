package org.chromium.chrome.browser.autofill.settings;

import J.N;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.chrome.browser.profiles.Profile;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AutofillPaymentMethodsFragment extends AbstractC2324eF0 implements AbstractC3853nC0 {
    public static final /* synthetic */ int G0 = 0;

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void B0() {
        this.i0 = true;
        k1();
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void b0(Bundle bundle) {
        this.i0 = true;
        PersonalDataManager c = PersonalDataManager.c();
        Objects.requireNonNull(c);
        Object obj = ThreadUtils.f10596a;
        c.c.add(this);
        N.Melg71WL(c.b, c);
    }

    @Override // defpackage.AbstractC2324eF0
    public void g1(Bundle bundle, String str) {
        u().setTitle(R.string.f47530_resource_name_obfuscated_RES_2131952070);
        V0(true);
        C4375qF0 qf0 = this.z0;
        PreferenceScreen a2 = qf0.a(qf0.f11127a);
        if (!a2.y0) {
            a2.C0 = false;
            j1(a2);
            return;
        }
        throw new IllegalStateException("Cannot change the usage of generated IDs while attached to the preference hierarchy");
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void k0(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        menu.add(0, R.id.menu_id_targeted_help, 0, R.string.f54570_resource_name_obfuscated_RES_2131952774).setIcon(R.drawable.f30670_resource_name_obfuscated_RES_2131231107);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0086, code lost:
        if ((r0 != null && r0.hasEnrolledFingerprints()) != false) goto L_0x0088;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x005f, code lost:
        if (r0.canAuthenticate() == 0) goto L_0x0088;
     */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x015c  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x01be  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x01e1  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x01e5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void k1() {
        /*
        // Method dump skipped, instructions count: 517
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.autofill.settings.AutofillPaymentMethodsFragment.k1():void");
    }

    public final void l1(Preference preference, boolean z) {
        if (z) {
            preference.T(null);
            preference.K(true);
            return;
        }
        preference.S(R.string.f58270_resource_name_obfuscated_RES_2131953144);
        preference.K(false);
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS, defpackage.AbstractC2324eF0
    public void n0() {
        PersonalDataManager c = PersonalDataManager.c();
        Objects.requireNonNull(c);
        Object obj = ThreadUtils.f10596a;
        c.c.remove(this);
        super.n0();
    }

    @Override // defpackage.AbstractC3853nC0
    public void p() {
        k1();
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public boolean u0(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.menu_id_targeted_help) {
            return false;
        }
        AbstractC0073Be.c(u(), Profile.b());
        return true;
    }
}
