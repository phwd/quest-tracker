package defpackage;

import com.oculus.browser.R;
import org.chromium.chrome.browser.password_manager.settings.PasswordEntryEditor;

/* renamed from: Tx0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1214Tx0 implements Runnable {
    public final PasswordEntryEditor F;

    public RunnableC1214Tx0(PasswordEntryEditor passwordEntryEditor) {
        this.F = passwordEntryEditor;
    }

    public void run() {
        PasswordEntryEditor passwordEntryEditor = this.F;
        passwordEntryEditor.u().getWindow().setFlags(8192, 8192);
        passwordEntryEditor.A0.setInputType(131217);
        passwordEntryEditor.C0.setImageResource(R.drawable.f32930_resource_name_obfuscated_RES_2131231333);
        passwordEntryEditor.C0.setOnClickListener(new View$OnClickListenerC1153Sx0(passwordEntryEditor));
    }
}
