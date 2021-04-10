package org.chromium.components.javascript_dialogs;

import J.N;
import android.content.Context;
import com.oculus.browser.R;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class JavascriptAppModalDialog extends D40 {
    public long O;

    public JavascriptAppModalDialog(String str, String str2, String str3, boolean z, int i, int i2) {
        super(str, str2, str3, z, i, i2);
    }

    public static JavascriptAppModalDialog createAlertDialog(String str, String str2, boolean z) {
        return new JavascriptAppModalDialog(str, str2, null, z, R.string.f56550_resource_name_obfuscated_RES_2131952972, 0);
    }

    public static JavascriptAppModalDialog createBeforeUnloadDialog(String str, String str2, boolean z, boolean z2) {
        return new JavascriptAppModalDialog(str, str2, null, z2, z ? R.string.f60100_resource_name_obfuscated_RES_2131953327 : R.string.f54070_resource_name_obfuscated_RES_2131952724, R.string.f48470_resource_name_obfuscated_RES_2131952164);
    }

    public static JavascriptAppModalDialog createConfirmDialog(String str, String str2, boolean z) {
        return new JavascriptAppModalDialog(str, str2, null, z, R.string.f56550_resource_name_obfuscated_RES_2131952972, R.string.f48470_resource_name_obfuscated_RES_2131952164);
    }

    public static JavascriptAppModalDialog createPromptDialog(String str, String str2, boolean z, String str3) {
        return new JavascriptAppModalDialog(str, str2, str3, z, R.string.f56550_resource_name_obfuscated_RES_2131952972, R.string.f48470_resource_name_obfuscated_RES_2131952164);
    }

    @Override // defpackage.D40
    public void a(String str, boolean z) {
        long j = this.O;
        if (j != 0) {
            N.Mo6rag0q(j, this, str, z);
        }
    }

    @Override // defpackage.D40
    public void b(boolean z, boolean z2) {
        long j = this.O;
        if (j != 0) {
            N.MOSW2s7O(j, this, z2);
        }
    }

    public final void dismiss() {
        C2746gl0 gl0 = this.L;
        if (gl0 != null) {
            gl0.b(this.M, 4);
        }
        this.O = 0;
    }

    public void showJavascriptAppModalDialog(WindowAndroid windowAndroid, long j) {
        Context context = (Context) windowAndroid.f11022J.get();
        if (context == null || windowAndroid.v0() == null) {
            N.MOSW2s7O(j, this, false);
            return;
        }
        this.O = j;
        c(context, windowAndroid.v0(), 0);
    }
}
