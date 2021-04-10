package org.chromium.chrome.browser.datareduction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.text.format.Formatter;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.chrome.browser.datareduction.settings.DataReductionPreferenceFragment;
import org.chromium.chrome.browser.net.spdyproxy.DataReductionProxySettings;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.profiles.ProfileManager;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DataReductionMainMenuItem extends FrameLayout implements View.OnClickListener {
    public static final /* synthetic */ int F = 0;

    public DataReductionMainMenuItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onClick(View view) {
        AbstractC3535lK0.a("MobileMenuDataSaverOpened");
        Bundle bundle = new Bundle();
        bundle.putBoolean("FromMainMenu", true);
        Context context = getContext();
        String name = DataReductionPreferenceFragment.class.getName();
        Intent l = AbstractC2531fV.l(context, XS0.class);
        if (!(context instanceof Activity)) {
            l.addFlags(268435456);
            l.addFlags(67108864);
        }
        if (name != null) {
            l.putExtra("show_fragment", name);
        }
        l.putExtra("show_fragment_args", bundle);
        U20.q(context, l);
        Um1.a(Profile.b()).notifyEvent("data_saver_overview_opened");
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        TextView textView = (TextView) findViewById(R.id.menu_item_text);
        TextView textView2 = (TextView) findViewById(R.id.menu_item_summary);
        ((ImageView) findViewById(R.id.icon)).setContentDescription(getContext().getString(R.string.f50630_resource_name_obfuscated_RES_2131952380));
        if (DataReductionProxySettings.d().e()) {
            UC.a(21);
            String formatShortFileSize = Formatter.formatShortFileSize(getContext(), DataReductionProxySettings.d().a());
            long b = DataReductionProxySettings.d().b() - 2592000000L;
            long c = DataReductionProxySettings.d().c();
            if (b <= c) {
                b = c;
            }
            String str = DateUtils.formatDateTime(getContext(), b, 65544).toString();
            textView.setText(getContext().getString(R.string.f50600_resource_name_obfuscated_RES_2131952377, formatShortFileSize));
            textView2.setText(getContext().getString(R.string.f50440_resource_name_obfuscated_RES_2131952361, str));
            Profile b2 = ProfileManager.b ? Profile.b() : null;
            if (b2 != null) {
                Tm1 a2 = Um1.a(b2);
                a2.a(new IC(a2));
            }
        } else {
            UC.a(22);
            textView.setText(R.string.f50630_resource_name_obfuscated_RES_2131952380);
            textView2.setText(R.string.f63320_resource_name_obfuscated_RES_2131953649);
        }
        setOnClickListener(this);
    }
}
