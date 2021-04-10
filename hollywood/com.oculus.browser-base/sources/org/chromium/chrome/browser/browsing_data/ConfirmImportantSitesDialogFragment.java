package org.chromium.chrome.browser.browsing_data;

import android.app.ActivityManager;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.profiles.Profile;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ConfirmImportantSitesDialogFragment extends OE {
    public String[] M0;
    public Map N0 = new HashMap();
    public String[] O0;
    public Map P0 = new HashMap();
    public DialogC2461f4 Q0;
    public C5000tx R0;
    public X60 S0;
    public Profile T0;
    public ListView U0;

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void U0(Bundle bundle) {
        super.U0(bundle);
        this.M0 = bundle.getStringArray("ImportantDomains");
        this.O0 = bundle.getStringArray("FaviconURLs");
        int[] intArray = bundle.getIntArray("ImportantDomainReasons");
        int i = 0;
        while (true) {
            String[] strArr = this.M0;
            if (i < strArr.length) {
                this.N0.put(strArr[i], Integer.valueOf(intArray[i]));
                this.P0.put(this.M0[i], Boolean.TRUE);
                i++;
            } else {
                return;
            }
        }
    }

    @Override // defpackage.OE
    public Dialog g1(Bundle bundle) {
        boolean z;
        if (bundle != null) {
            this.M0 = new String[0];
            this.O0 = new String[0];
            f1(false, false);
        }
        Profile b = Profile.b();
        this.T0 = b;
        this.S0 = new X60(b);
        int min = Math.min((((ActivityManager) ContextUtils.getApplicationContext().getSystemService("activity")).getMemoryClass() / 16) * 25 * 1024, 102400);
        X60 x60 = this.S0;
        Objects.requireNonNull(x60);
        x60.c = new U60(x60, min);
        this.R0 = new C5000tx(this, this.M0, this.O0, I(), null);
        DialogInterface$OnClickListenerC4660rx rxVar = new DialogInterface$OnClickListenerC4660rx(this);
        Set a2 = AbstractC2957hy1.f10115a.a();
        String[] strArr = this.M0;
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = false;
                break;
            }
            if (((HashSet) a2).contains(strArr[i])) {
                z = true;
                break;
            }
            i++;
        }
        int i2 = z ? R.string.f53040_resource_name_obfuscated_RES_2131952621 : R.string.f53030_resource_name_obfuscated_RES_2131952620;
        int i3 = z ? R.string.f48850_resource_name_obfuscated_RES_2131952202 : R.string.f48840_resource_name_obfuscated_RES_2131952201;
        View inflate = u().getLayoutInflater().inflate(R.layout.f37290_resource_name_obfuscated_RES_2131624038, (ViewGroup) null);
        ListView listView = (ListView) inflate.findViewById(R.id.select_dialog_listview);
        this.U0 = listView;
        listView.setAdapter((ListAdapter) this.R0);
        this.U0.setOnItemClickListener(this.R0);
        ((TextView) inflate.findViewById(R.id.message)).setText(i3);
        C2290e4 e4Var = new C2290e4(u(), R.style.f72700_resource_name_obfuscated_RES_2132017843);
        e4Var.g(i2);
        e4Var.e(R.string.f48830_resource_name_obfuscated_RES_2131952200, rxVar);
        e4Var.d(R.string.f48470_resource_name_obfuscated_RES_2131952164, rxVar);
        C1598a4 a4Var = e4Var.f9828a;
        a4Var.r = inflate;
        a4Var.q = 0;
        DialogC2461f4 a3 = e4Var.a();
        this.Q0 = a3;
        return a3;
    }

    @Override // defpackage.OE
    public void onDismiss(DialogInterface dialogInterface) {
        if (!this.J0) {
            f1(true, true);
        }
        X60 x60 = this.S0;
        if (x60 != null) {
            x60.a();
        }
    }
}
