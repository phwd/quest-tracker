package org.chromium.chrome.browser.search_engines.settings;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SearchEngineSettings extends Q80 {
    public View$OnClickListenerC4742sQ0 I0;

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void D0() {
        this.i0 = true;
        View$OnClickListenerC4742sQ0 sq0 = this.I0;
        sq0.e();
        AbstractC0444Hf1.a().b.b(sq0);
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void E0() {
        this.i0 = true;
        View$OnClickListenerC4742sQ0 sq0 = this.I0;
        if (sq0.L) {
            AbstractC0444Hf1.a().k(sq0);
            sq0.L = false;
        }
        AbstractC0444Hf1.a().b.c(sq0);
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS, defpackage.Q80
    public void F0(View view, Bundle bundle) {
        e1();
        e1();
        ListView listView = this.C0;
        listView.setDivider(null);
        listView.setItemsCanFocus(true);
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void h0(Bundle bundle) {
        super.h0(bundle);
        u().setTitle(R.string.f61070_resource_name_obfuscated_RES_2131953424);
        View$OnClickListenerC4742sQ0 sq0 = new View$OnClickListenerC4742sQ0(u());
        this.I0 = sq0;
        g1(sq0);
    }
}
