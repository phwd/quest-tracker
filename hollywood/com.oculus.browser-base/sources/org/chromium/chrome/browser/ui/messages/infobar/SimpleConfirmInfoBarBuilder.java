package org.chromium.chrome.browser.ui.messages.infobar;

import J.N;
import android.content.Context;
import android.graphics.BitmapFactory;
import org.chromium.content_public.browser.WebContents;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SimpleConfirmInfoBarBuilder {

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public interface Listener {
        boolean a(boolean z);

        void b();

        boolean c();
    }

    public static void a(WebContents webContents, Listener listener, int i, Context context, int i2, String str, String str2, String str3, String str4, boolean z) {
        N.Mpy_Meaj(webContents, i, (context == null || i2 == 0) ? null : BitmapFactory.decodeResource(context.getResources(), i2), str, str2, str3, str4, z, listener);
    }

    public static boolean onInfoBarButtonClicked(Listener listener, boolean z) {
        if (listener == null) {
            return false;
        }
        return listener.a(z);
    }

    public static void onInfoBarDismissed(Listener listener) {
        if (listener != null) {
            listener.b();
        }
    }

    public static boolean onInfoBarLinkClicked(Listener listener) {
        if (listener == null) {
            return false;
        }
        return listener.c();
    }
}
