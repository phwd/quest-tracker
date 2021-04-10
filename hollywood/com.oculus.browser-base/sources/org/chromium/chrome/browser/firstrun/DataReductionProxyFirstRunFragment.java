package org.chromium.chrome.browser.firstrun;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.widget.SwitchCompat;
import com.oculus.browser.R;
import org.chromium.chrome.browser.net.spdyproxy.DataReductionProxySettings;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DataReductionProxyFirstRunFragment extends AbstractComponentCallbacksC3550lS implements UQ {
    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void D0() {
        this.i0 = true;
        PC.b();
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void F0(View view, Bundle bundle) {
        TextView textView = (TextView) view.findViewById(R.id.data_reduction_promo_summary_text);
        SwitchCompat switchCompat = (SwitchCompat) view.findViewById(R.id.enable_data_saver_switch);
        switchCompat.setOnClickListener(new SC(this, switchCompat));
        C4467qp0 qp0 = new C4467qp0(I(), new RC(this));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(FY0.a(O(R.string.f50580_resource_name_obfuscated_RES_2131952375), new EY0("<link>", "</link>", qp0)));
        ((Button) view.findViewById(R.id.next_button)).setOnClickListener(new TC(this));
        switchCompat.setChecked(true);
        DataReductionProxySettings d = DataReductionProxySettings.d();
        view.getContext();
        d.g(switchCompat.isChecked());
    }

    public final void e1() {
        if (U()) {
            TQ.a(this).h(R.string.f50570_resource_name_obfuscated_RES_2131952374);
        }
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public View l0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.f38580_resource_name_obfuscated_RES_2131624167, viewGroup, false);
    }
}
