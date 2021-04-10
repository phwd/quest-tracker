package org.chromium.chrome.browser.browsing_data;

import J.N;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import androidx.preference.Preference;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.settings.SpinnerPreference;
import org.chromium.ui.widget.ButtonCompat;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class ClearBrowsingDataFragment extends AbstractC2324eF0 implements AbstractC0273Ek, YE0, XE0 {
    public static final /* synthetic */ int G0 = 0;
    public DialogFragmentC5669xt0 H0;
    public ProgressDialog I0;
    public C0537Iu[] J0;
    public ClearBrowsingDataFetcher K0;
    public ConfirmImportantSitesDialogFragment L0;

    public static int n1(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 2;
        }
        if (i == 2) {
            return 1;
        }
        int i2 = 3;
        if (i != 3) {
            i2 = 4;
            if (i != 4) {
                if (i == 5) {
                    return 5;
                }
                throw new IllegalArgumentException();
            }
        }
        return i2;
    }

    public static String p1(int i) {
        if (i == 0) {
            return "clear_history_checkbox";
        }
        if (i == 1) {
            return "clear_cookies_checkbox";
        }
        if (i == 2) {
            return "clear_cache_checkbox";
        }
        if (i == 3) {
            return "clear_passwords_checkbox";
        }
        if (i == 4) {
            return "clear_form_data_checkbox";
        }
        if (i == 5) {
            return "clear_site_settings_checkbox";
        }
        throw new IllegalArgumentException();
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS, defpackage.AbstractC2324eF0
    public void C0(Bundle bundle) {
        super.C0(bundle);
        bundle.putParcelable("clearBrowsingDataFetcher", this.K0);
    }

    @Override // defpackage.XE0
    public boolean a(Preference preference, Object obj) {
        if (!preference.Q.equals("time_period_spinner")) {
            return false;
        }
        for (C0537Iu iu : this.J0) {
            iu.f8255J = false;
        }
        BrowsingDataBridge c = BrowsingDataBridge.c();
        int m1 = m1();
        int i = ((C0598Ju) obj).f8323a;
        Objects.requireNonNull(c);
        N.MyZiGmx0(c, m1, i);
        return true;
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void b0(Bundle bundle) {
        this.i0 = true;
        u1();
        i1(null);
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void c0(int i, int i2, Intent intent) {
        if (i == 1 && i2 == -1) {
            String[] stringArrayExtra = intent.getStringArrayExtra("DeselectedDomains");
            int[] intArrayExtra = intent.getIntArrayExtra("DeselectedDomainReasons");
            String[] stringArrayExtra2 = intent.getStringArrayExtra("IgnoredDomains");
            int[] intArrayExtra2 = intent.getIntArrayExtra("IgnoredDomainReasons");
            if (stringArrayExtra != null) {
                ClearBrowsingDataFetcher clearBrowsingDataFetcher = this.K0;
                if (clearBrowsingDataFetcher.G != null) {
                    int length = stringArrayExtra.length;
                    int i3 = clearBrowsingDataFetcher.F;
                    AbstractC3364kK0.e("History.ClearBrowsingData.ImportantDeselectedNum", length, 1, i3 + 1, i3 + 1);
                    int length2 = stringArrayExtra2.length;
                    int i4 = this.K0.F;
                    AbstractC3364kK0.e("History.ClearBrowsingData.ImportantIgnoredNum", length2, 1, i4 + 1, i4 + 1);
                    AbstractC3364kK0.g("History.ClearBrowsingData.ImportantDeselectedPercent", (stringArrayExtra.length * 20) / this.K0.G.length, 21);
                    AbstractC3364kK0.g("History.ClearBrowsingData.ImportantIgnoredPercent", (stringArrayExtra2.length * 20) / this.K0.G.length, 21);
                }
            }
            k1(q1(), stringArrayExtra, intArrayExtra, stringArrayExtra2, intArrayExtra2);
        }
    }

    @Override // defpackage.YE0
    public boolean d(Preference preference) {
        if (!preference.Q.equals("clear_button")) {
            return false;
        }
        r1();
        return true;
    }

    @Override // defpackage.AbstractC0273Ek
    public void e() {
        if (u() != null) {
            if (C3432km0.g(u()) && ((C5271va) q1()).contains(0) && this.K0.f10628J) {
                int i = DialogFragmentC5669xt0.F;
                if (!NU0.f8549a.d("org.chromium.chrome.browser.settings.privacy.PREF_OTHER_FORMS_OF_HISTORY_DIALOG_SHOWN", false)) {
                    DialogFragmentC5669xt0 xt0 = new DialogFragmentC5669xt0();
                    this.H0 = xt0;
                    xt0.show(u().getFragmentManager(), "OtherFormsOfHistoryDialogFragment");
                    l1();
                    AbstractC3100ip1.f10165a.a("History.ClearBrowsingData.ShownHistoryNoticeAfterClearing", true);
                    return;
                }
            }
            l1();
            u().finish();
            AbstractC3100ip1.f10165a.a("History.ClearBrowsingData.ShownHistoryNoticeAfterClearing", false);
        }
    }

    @Override // defpackage.AbstractC2324eF0
    public void g1(Bundle bundle, String str) {
        boolean z;
        if (bundle != null) {
            this.K0 = (ClearBrowsingDataFetcher) bundle.getParcelable("clearBrowsingDataFetcher");
        }
        u().setTitle(R.string.f48960_resource_name_obfuscated_RES_2131952213);
        AbstractC2870hT0.a(this, R.xml.f76110_resource_name_obfuscated_RES_2132213767);
        List o1 = o1();
        this.J0 = new C0537Iu[o1.size()];
        int i = 0;
        for (int i2 = 0; i2 < o1.size(); i2++) {
            int intValue = ((Integer) o1.get(i2)).intValue();
            if (intValue != 0 || N.MzIXnlkD(Wr1.a(Profile.b()).f10883a, "history.deleting_enabled")) {
                z = true;
            } else {
                BrowsingDataBridge c = BrowsingDataBridge.c();
                int n1 = n1(0);
                Objects.requireNonNull(c);
                N.MBI7g3zY(c, n1, 0, false);
                BrowsingDataBridge c2 = BrowsingDataBridge.c();
                int n12 = n1(0);
                Objects.requireNonNull(c2);
                N.MBI7g3zY(c2, n12, 1, false);
                z = false;
            }
            C0537Iu[] iuArr = this.J0;
            Activity u = u();
            ClearBrowsingDataCheckBoxPreference clearBrowsingDataCheckBoxPreference = (ClearBrowsingDataCheckBoxPreference) f1(p1(intValue));
            BrowsingDataBridge c3 = BrowsingDataBridge.c();
            int n13 = n1(intValue);
            int m1 = m1();
            Objects.requireNonNull(c3);
            iuArr[i2] = new C0537Iu(u, this, intValue, clearBrowsingDataCheckBoxPreference, N.MK1rP8DI(c3, n13, m1), z);
        }
        C5271va vaVar = new C5271va(0);
        for (int i3 = 0; i3 < 6; i3++) {
            vaVar.add(Integer.valueOf(i3));
        }
        vaVar.removeAll(o1);
        C5101ua uaVar = new C5101ua(vaVar);
        while (uaVar.hasNext()) {
            this.z0.g.f0(f1(p1(((Integer) uaVar.next()).intValue())));
        }
        SpinnerPreference spinnerPreference = (SpinnerPreference) f1("time_period_spinner");
        Activity u2 = u();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C0598Ju(0, u2.getString(R.string.f48930_resource_name_obfuscated_RES_2131952210)));
        arrayList.add(new C0598Ju(1, u2.getString(R.string.f48890_resource_name_obfuscated_RES_2131952206)));
        arrayList.add(new C0598Ju(2, u2.getString(R.string.f48900_resource_name_obfuscated_RES_2131952207)));
        arrayList.add(new C0598Ju(3, u2.getString(R.string.f48920_resource_name_obfuscated_RES_2131952209)));
        if (N.M09VlOh_("ClearOldBrowsingData")) {
            arrayList.add(new C0598Ju(5, u2.getString(R.string.f48940_resource_name_obfuscated_RES_2131952211)));
        }
        arrayList.add(new C0598Ju(4, u2.getString(R.string.f48910_resource_name_obfuscated_RES_2131952208)));
        C0598Ju[] juArr = (C0598Ju[]) arrayList.toArray(new C0598Ju[0]);
        BrowsingDataBridge c4 = BrowsingDataBridge.c();
        int m12 = m1();
        Objects.requireNonNull(c4);
        int MWrAQRuo = N.MWrAQRuo(c4, m12);
        int i4 = -1;
        while (true) {
            if (i >= juArr.length) {
                break;
            } else if (juArr[i].f8323a == MWrAQRuo) {
                i4 = i;
                break;
            } else {
                i++;
            }
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(spinnerPreference.F, spinnerPreference.w0 ? R.layout.f40820_resource_name_obfuscated_RES_2131624391 : 17367048, juArr);
        spinnerPreference.u0 = arrayAdapter;
        arrayAdapter.setDropDownViewResource(17367049);
        spinnerPreference.v0 = i4;
        spinnerPreference.f9480J = this;
    }

    public final void k1(Set set, String[] strArr, int[] iArr, String[] strArr2, int[] iArr2) {
        Object obj;
        s1();
        int i = 0;
        int i2 = 1;
        if (u() != null) {
            this.I0 = ProgressDialog.show(u(), u().getString(R.string.f48870_resource_name_obfuscated_RES_2131952204), u().getString(R.string.f48860_resource_name_obfuscated_RES_2131952203), true, false);
        }
        C5271va vaVar = new C5271va(0);
        C5101ua uaVar = new C5101ua((C5271va) set);
        while (uaVar.hasNext()) {
            vaVar.add(Integer.valueOf(n1(((Integer) uaVar.next()).intValue())));
        }
        if (!vaVar.contains(2)) {
            if (vaVar.contains(1)) {
                i = 2;
            }
            i2 = i;
        } else if (vaVar.contains(1)) {
            i2 = 3;
        }
        AbstractC3364kK0.g("History.ClearBrowsingData.UserDeletedCookieOrCacheFromDialog", i2, 4);
        SpinnerPreference spinnerPreference = (SpinnerPreference) f1("time_period_spinner");
        Spinner spinner = spinnerPreference.t0;
        if (spinner == null) {
            obj = spinnerPreference.u0.getItem(spinnerPreference.v0);
        } else {
            obj = spinner.getSelectedItem();
        }
        int i3 = ((C0598Ju) obj).f8323a;
        int[] b = AbstractC0417Gv.b(new ArrayList(vaVar));
        if (strArr == null || strArr.length == 0) {
            BrowsingDataBridge.c().a(this, b, i3);
        } else {
            BrowsingDataBridge.c().b(this, b, i3, strArr, iArr, strArr2, iArr2);
        }
        if (Y8.f9254a == null) {
            Objects.requireNonNull(AppHooks.get());
            Y8.f9254a = new Y8();
        }
        Objects.requireNonNull(Y8.f9254a);
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS, defpackage.AbstractC2324eF0
    public View l0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LinearLayout linearLayout = (LinearLayout) super.l0(layoutInflater, viewGroup, bundle);
        ButtonCompat buttonCompat = (ButtonCompat) layoutInflater.inflate(R.layout.f37270_resource_name_obfuscated_RES_2131624036, (ViewGroup) linearLayout, false);
        buttonCompat.setOnClickListener(new View$OnClickListenerC0476Hu(this));
        linearLayout.addView(buttonCompat);
        this.A0.s0(null);
        return linearLayout;
    }

    public final void l1() {
        ProgressDialog progressDialog = this.I0;
        if (progressDialog != null && progressDialog.isShowing()) {
            this.I0.dismiss();
        }
        this.I0 = null;
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void m0() {
        this.i0 = true;
        l1();
        for (C0537Iu iu : this.J0) {
            BrowsingDataCounterBridge browsingDataCounterBridge = iu.I;
            long j = browsingDataCounterBridge.f10627a;
            if (j != 0) {
                N.MdFUmBu6(j, browsingDataCounterBridge);
                browsingDataCounterBridge.f10627a = 0;
            }
        }
    }

    public abstract int m1();

    public abstract List o1();

    public final Set q1() {
        C5271va vaVar = new C5271va(0);
        C0537Iu[] iuArr = this.J0;
        for (C0537Iu iu : iuArr) {
            if (iu.H.t0) {
                vaVar.add(Integer.valueOf(iu.G));
            }
        }
        return vaVar;
    }

    public void s1() {
    }

    /* renamed from: t1 */
    public final void r1() {
        C5271va vaVar = (C5271va) q1();
        boolean z = false;
        if (vaVar.contains(2) || vaVar.contains(1)) {
            String[] strArr = this.K0.G;
            if (!(strArr == null || strArr.length == 0)) {
                z = true;
            }
            AbstractC3100ip1.f10165a.a("History.ClearBrowsingData.ImportantDialogShown", z);
        }
        if (z) {
            ClearBrowsingDataFetcher clearBrowsingDataFetcher = this.K0;
            String[] strArr2 = clearBrowsingDataFetcher.G;
            int[] iArr = clearBrowsingDataFetcher.H;
            String[] strArr3 = clearBrowsingDataFetcher.I;
            ConfirmImportantSitesDialogFragment confirmImportantSitesDialogFragment = new ConfirmImportantSitesDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putStringArray("ImportantDomains", strArr2);
            bundle.putIntArray("ImportantDomainReasons", iArr);
            bundle.putStringArray("FaviconURLs", strArr3);
            confirmImportantSitesDialogFragment.U0(bundle);
            this.L0 = confirmImportantSitesDialogFragment;
            confirmImportantSitesDialogFragment.b1(this, 1);
            this.L0.k1(this.W, "ConfirmImportantSitesDialogFragment");
            return;
        }
        k1(q1(), null, null, null, null);
    }

    public final void u1() {
        ((Button) this.k0.findViewById(R.id.clear_button)).setEnabled(!((C5271va) q1()).isEmpty());
    }
}
