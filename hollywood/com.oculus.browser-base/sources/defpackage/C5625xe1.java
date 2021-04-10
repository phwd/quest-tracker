package defpackage;

import android.os.Build;
import android.view.Window;

/* renamed from: xe1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5625xe1 {

    /* renamed from: a  reason: collision with root package name */
    public C1413Xd1 f11622a;

    public C5625xe1(Window window, AbstractC0124Ca1 ca1, AbstractC1509Ys0 ys0) {
        if (Build.VERSION.SDK_INT >= 27) {
            this.f11622a = new C1413Xd1(window, ca1, ys0);
        }
    }
}
