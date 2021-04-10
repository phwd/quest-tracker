package defpackage;

import android.graphics.Rect;
import org.chromium.base.Callback;
import org.chromium.components.paintpreview.player.PlayerCompositorDelegateImpl;
import org.chromium.url.GURL;

/* renamed from: jb0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3228jb0 {

    /* renamed from: a  reason: collision with root package name */
    public AbstractC5900zD0 f10216a;
    public Callback b;
    public Rect c;
    public Callback d;

    public C3228jb0(GURL gurl, AbstractC0156Cm0 cm0, String str, C1149Sv0 sv0, Rect rect, Callback callback, Callback callback2) {
        this.b = callback;
        this.c = rect;
        this.d = callback2;
        this.f10216a = new PlayerCompositorDelegateImpl(cm0, sv0, gurl, str, true, new C2887hb0(this), callback2);
    }
}
