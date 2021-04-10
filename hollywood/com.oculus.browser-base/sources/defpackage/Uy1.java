package defpackage;

import android.graphics.Rect;
import android.view.Window;

/* renamed from: Uy1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Uy1 {

    /* renamed from: a  reason: collision with root package name */
    public final Window f9058a;

    public Uy1(Window window) {
        this.f9058a = window;
    }

    public void a(Rect rect) {
        this.f9058a.getDecorView().getWindowVisibleDisplayFrame(rect);
    }
}
