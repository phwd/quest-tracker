package org.chromium.chrome.browser.webauthn;

import J.N;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CableAuthenticatorModuleProvider extends AbstractComponentCallbacksC3550lS {
    public static final /* synthetic */ int y0 = 0;
    public TextView z0;

    public static void onCloudMessage(long j) {
        long MxK6FZrl = N.MxK6FZrl();
        long MX3WavKZ = N.MX3WavKZ();
        C4455ql0 ql0 = AbstractC2062cl.f9627a;
        if (ql0.f()) {
            AbstractC2062cl.a().b(j, MxK6FZrl, MX3WavKZ, "org.chromium.chrome.browser.webauth.authenticator.CableAuthenticatorActivity");
        } else {
            ql0.d(new C1711al(j, MxK6FZrl, MX3WavKZ));
        }
    }

    public final void e1() {
        this.z0.setText("Installed.");
        C0317Fe fe = new C0317Fe(G());
        AbstractComponentCallbacksC3550lS a2 = AbstractC2062cl.a().a();
        Bundle bundle = this.K;
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putLong("org.chromium.chrome.modules.cablev2_authenticator.NetworkContext", N.MxK6FZrl());
        bundle.putLong("org.chromium.chrome.modules.cablev2_authenticator.Registration", N.MX3WavKZ());
        bundle.putString("org.chromium.chrome.modules.cablev2_authenticator.ActivityClassName", "org.chromium.chrome.browser.webauth.authenticator.CableAuthenticatorActivity");
        a2.U0(bundle);
        fe.q(this.a0, a2);
        fe.e();
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public View l0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Context x = x();
        u().setTitle("Installing");
        TextView textView = new TextView(x);
        this.z0 = textView;
        textView.setPadding(0, 60, 0, 60);
        LinearLayout linearLayout = new LinearLayout(x);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(1);
        linearLayout.addView(this.z0, new LinearLayout.LayoutParams(-2, -2));
        C4455ql0 ql0 = AbstractC2062cl.f9627a;
        if (ql0.f()) {
            e1();
        } else {
            this.z0.setText("Installing security key functionality…");
            ql0.d(new C1553Zk(this));
        }
        return linearLayout;
    }
}
