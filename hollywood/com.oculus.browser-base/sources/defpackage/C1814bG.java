package defpackage;

import android.graphics.Point;
import android.view.Display;
import java.util.Objects;

/* renamed from: bG  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1814bG {

    /* renamed from: a  reason: collision with root package name */
    public final Point f9526a;
    public final boolean b;

    public C1814bG(Point point) {
        this.b = true;
        this.f9526a = point;
    }

    public C1814bG(Display.Mode mode, boolean z) {
        Objects.requireNonNull(mode, "Display.Mode == null, can't wrap a null reference");
        this.b = z;
        this.f9526a = new Point(mode.getPhysicalWidth(), mode.getPhysicalHeight());
    }
}
