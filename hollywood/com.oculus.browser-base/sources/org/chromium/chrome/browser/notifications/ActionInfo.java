package org.chromium.chrome.browser.notifications;

import android.graphics.Bitmap;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ActionInfo {

    /* renamed from: a  reason: collision with root package name */
    public final String f10701a;
    public final Bitmap b;
    public final int c;
    public final String d;

    public ActionInfo(String str, Bitmap bitmap, int i, String str2) {
        this.f10701a = str;
        this.b = bitmap;
        this.c = i;
        this.d = str2;
    }

    public static ActionInfo createActionInfo(String str, Bitmap bitmap, int i, String str2) {
        return new ActionInfo(str, bitmap, i, str2);
    }
}
