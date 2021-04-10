package org.chromium.chrome.browser.autofill.settings;

import J.N;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.oculus.browser.R;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AutofillProfilesFragment extends AbstractC2324eF0 implements AbstractC3853nC0 {
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
        u().setTitle(R.string.f47040_resource_name_obfuscated_RES_2131952021);
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

    @Override // defpackage.AbstractC2324eF0, defpackage.AbstractC3862nF0
    public void k(Preference preference) {
        RunnableC4262pe peVar;
        if (preference instanceof C3920ne) {
            String string = ((C3920ne) preference).j().getString("guid");
            C2892hd hdVar = null;
            if (string == null) {
                peVar = null;
            } else {
                peVar = new RunnableC4262pe(string);
            }
            View$OnClickListenerC3876nK nKVar = new View$OnClickListenerC3876nK(u(), peVar, Profile.b());
            if (string != null) {
                hdVar = new C2892hd(u(), PersonalDataManager.c().e(string));
            }
            S3 s3 = new S3(2, true);
            s3.f9770a = nKVar;
            s3.b = nKVar.getContext();
            s3.d(hdVar, new C4433qe());
            return;
        }
        super.k(preference);
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void k0(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        menu.add(0, R.id.menu_id_targeted_help, 0, R.string.f54570_resource_name_obfuscated_RES_2131952774).setIcon(R.drawable.f30670_resource_name_obfuscated_RES_2131231107);
    }

    public final void k1() {
        Preference preference;
        this.z0.g.e0();
        C4375qF0 qf0 = this.z0;
        qf0.g.w0 = true;
        ChromeSwitchPreference chromeSwitchPreference = new ChromeSwitchPreference(qf0.f11127a, null);
        chromeSwitchPreference.U(R.string.f47410_resource_name_obfuscated_RES_2131952058);
        chromeSwitchPreference.S(R.string.f47420_resource_name_obfuscated_RES_2131952059);
        chromeSwitchPreference.a0(PersonalDataManager.h());
        chromeSwitchPreference.f9480J = new C4091oe();
        C4603re reVar = new C4603re(this);
        chromeSwitchPreference.B0 = reVar;
        AbstractC1865bc0.b(reVar, chromeSwitchPreference);
        this.z0.g.a0(chromeSwitchPreference);
        PersonalDataManager c = PersonalDataManager.c();
        Objects.requireNonNull(c);
        Object obj = ThreadUtils.f10596a;
        Iterator it = c.f(N.M6XJvXko(c.b, c), N.M4q3jK16(c.b, c)).iterator();
        while (it.hasNext()) {
            PersonalDataManager.AutofillProfile autofillProfile = (PersonalDataManager.AutofillProfile) it.next();
            if (autofillProfile.c) {
                preference = new C3920ne(this.z0.f11127a);
                preference.V(autofillProfile.getFullName());
                preference.T(autofillProfile.p);
                preference.O(preference.M.toString());
            } else {
                preference = new Preference(this.z0.f11127a, null);
                preference.l0 = R.layout.f37010_resource_name_obfuscated_RES_2131624010;
                preference.S = AutofillServerProfileFragment.class.getName();
            }
            preference.j().putString("guid", autofillProfile.getGUID());
            P21 g0 = P21.g0();
            try {
                this.z0.g.a0(preference);
                g0.close();
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
        }
        if (PersonalDataManager.h()) {
            C3920ne neVar = new C3920ne(this.z0.f11127a);
            Drawable c2 = AbstractC3153j7.c(I(), R.drawable.f34580_resource_name_obfuscated_RES_2131231498);
            c2.mutate();
            c2.setColorFilter(I().getColor(R.color.f11100_resource_name_obfuscated_RES_2131099800), PorterDuff.Mode.SRC_IN);
            if (neVar.P != c2) {
                neVar.P = c2;
                neVar.O = 0;
                neVar.s();
            }
            neVar.U(R.string.f47290_resource_name_obfuscated_RES_2131952046);
            neVar.O("new_profile");
            P21 g02 = P21.g0();
            try {
                this.z0.g.a0(neVar);
                g02.close();
                return;
            } catch (Throwable th2) {
                AbstractC0754Mh1.f8495a.a(th, th2);
            }
        } else {
            return;
        }
        throw th;
        throw th;
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
