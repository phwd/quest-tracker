package defpackage;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content.browser.selection.SmartSelectionClient;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: dS0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract /* synthetic */ class AbstractC2185dS0 {
    public static AbstractC2185dS0 b(WebContents webContents) {
        C4406qS0 qs0 = SelectionPopupControllerImpl.r(webContents).N;
        WindowAndroid I = webContents.I();
        if (Build.VERSION.SDK_INT < 26 || I == null) {
            return null;
        }
        Context context = (Context) I.f11022J.get();
        boolean z = true;
        if (!(context == null || context.getContentResolver() == null || Settings.Global.getInt(context.getContentResolver(), "device_provisioned", 0) != 0)) {
            z = false;
        }
        if (!z || webContents.a()) {
            return null;
        }
        return new SmartSelectionClient(qs0, webContents);
    }

    public abstract void a();

    public ZX0 c() {
        return null;
    }

    public abstract void d(String str);

    public abstract void e(int i, float f, float f2);

    public abstract boolean f(boolean z);

    public abstract void g(boolean z, int i, int i2);
}
