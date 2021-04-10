package org.chromium.chrome.browser.signin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.chrome.browser.sync.ProfileSyncService;
import org.chromium.chrome.browser.sync.settings.SyncAndServicesSettings;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SyncPromoView extends LinearLayout implements AbstractC3526lH0 {
    public TextView F;
    public Button G;

    public SyncPromoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final /* synthetic */ void a() {
        U20.q(getContext(), new Intent("android.settings.SYNC_SETTINGS"));
    }

    public final void b() {
        Context context = getContext();
        Bundle l1 = SyncAndServicesSettings.l1(false);
        String name = SyncAndServicesSettings.class.getName();
        Intent l = AbstractC2531fV.l(context, XS0.class);
        if (!(context instanceof Activity)) {
            l.addFlags(268435456);
            l.addFlags(67108864);
        }
        if (name != null) {
            l.putExtra("show_fragment", name);
        }
        l.putExtra("show_fragment_args", l1);
        U20.q(context, l);
    }

    public final void c() {
        C3662m51 m51;
        if (!ProfileSyncService.b().k()) {
            m51 = new C3662m51(R.string.f60050_resource_name_obfuscated_RES_2131953322, new C3320k51(R.string.f56710_resource_name_obfuscated_RES_2131952988, new View$OnClickListenerC2637g51(this)));
        } else if (!ProfileSyncService.b().m()) {
            m51 = new C3662m51(R.string.f60060_resource_name_obfuscated_RES_2131953323, new C3320k51(R.string.f51910_resource_name_obfuscated_RES_2131952508, new View$OnClickListenerC2808h51(this)));
        } else {
            m51 = new C3662m51(R.string.f56430_resource_name_obfuscated_RES_2131952960, new C3149j51(null));
        }
        TextView textView = this.F;
        Button button = this.G;
        textView.setText(m51.f10396a);
        m51.b.a(button);
    }

    @Override // defpackage.AbstractC3526lH0
    public void m() {
        c();
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ProfileSyncService.b().a(this);
        c();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ProfileSyncService.b().q(this);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        TextView textView = (TextView) findViewById(R.id.title);
        this.F = (TextView) findViewById(R.id.description);
        this.G = (Button) findViewById(R.id.sign_in);
    }
}
