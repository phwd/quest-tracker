package org.chromium.components.javascript_dialogs;

import J.N;
import android.content.Context;
import com.oculus.browser.R;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class JavascriptTabModalDialog extends D40 {
    public long O;

    public JavascriptTabModalDialog(String str, String str2, String str3, int i) {
        super(str, str2, str3, false, R.string.f56550_resource_name_obfuscated_RES_2131952972, i);
    }

    public static JavascriptTabModalDialog createAlertDialog(String str, String str2) {
        return new JavascriptTabModalDialog(str, str2, null, 0);
    }

    public static JavascriptTabModalDialog createConfirmDialog(String str, String str2) {
        return new JavascriptTabModalDialog(str, str2, null, R.string.f48470_resource_name_obfuscated_RES_2131952164);
    }

    public static JavascriptTabModalDialog createPromptDialog(String str, String str2, String str3) {
        return new JavascriptTabModalDialog(str, str2, str3, R.string.f48470_resource_name_obfuscated_RES_2131952164);
    }

    @Override // defpackage.D40
    public void a(String str, boolean z) {
        long j = this.O;
        if (j != 0) {
            N.M9yPJzg8(j, this, str);
        }
    }

    @Override // defpackage.D40
    public void b(boolean z, boolean z2) {
        long j = this.O;
        if (j != 0) {
            N.M0YaeICP(j, this, z);
        }
    }

    public final void dismiss() {
        C2746gl0 gl0 = this.L;
        if (gl0 != null) {
            gl0.b(this.M, 4);
        }
        this.O = 0;
    }

    public final String getUserInput() {
        return this.N.F.getText().toString();
    }

    public final void showDialog(WindowAndroid windowAndroid, long j) {
        Context context = (Context) windowAndroid.f11022J.get();
        C2746gl0 v0 = windowAndroid.v0();
        if (context == null || v0 == null) {
            N.M0YaeICP(j, this, false);
            return;
        }
        this.O = j;
        c(context, v0, 1);
    }
}
