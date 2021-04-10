package org.chromium.chrome.browser.password_manager.settings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PasswordEditingBridge implements AbstractC0970Px0 {

    /* renamed from: a  reason: collision with root package name */
    public long f10741a;

    public PasswordEditingBridge(long j) {
        this.f10741a = j;
        C1031Qx0.f8798a.b = this;
    }

    public static PasswordEditingBridge create(long j) {
        return new PasswordEditingBridge(j);
    }

    public final void showEditingUI(Context context, String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        bundle.putString("credentialUrl", str);
        bundle.putString("credentialName", str2);
        bundle.putString("credentialPassword", str3);
        String name = PasswordEntryEditor.class.getName();
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
    }
}
