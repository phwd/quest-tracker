package defpackage;

import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.widget.PopupWindow;

/* renamed from: ze  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ComponentCallbacksC5963ze implements ComponentCallbacks {
    public final /* synthetic */ PopupWindow F;

    public ComponentCallbacksC5963ze(PopupWindow popupWindow) {
        this.F = popupWindow;
    }

    public void onConfigurationChanged(Configuration configuration) {
        PopupWindow popupWindow;
        if (configuration.orientation == 2 && (popupWindow = this.F) != null) {
            popupWindow.dismiss();
        }
    }

    public void onLowMemory() {
    }
}
