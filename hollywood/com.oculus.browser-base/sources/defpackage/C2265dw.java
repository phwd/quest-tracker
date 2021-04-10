package defpackage;

import android.content.res.ColorStateList;
import android.graphics.Shader;

/* renamed from: dw  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2265dw {

    /* renamed from: a  reason: collision with root package name */
    public final Shader f9818a;
    public final ColorStateList b;
    public int c;

    public C2265dw(Shader shader, ColorStateList colorStateList, int i) {
        this.f9818a = shader;
        this.b = colorStateList;
        this.c = i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:80:0x01c4, code lost:
        throw new org.xmlpull.v1.XmlPullParserException(r2.getPositionDescription() + ": <item> tag requires a 'color' attribute and a 'offset' attribute!");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static defpackage.C2265dw a(android.content.res.Resources r29, int r30, android.content.res.Resources.Theme r31) {
        /*
        // Method dump skipped, instructions count: 645
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C2265dw.a(android.content.res.Resources, int, android.content.res.Resources$Theme):dw");
    }

    public boolean b() {
        return this.f9818a != null;
    }

    public boolean c() {
        ColorStateList colorStateList;
        return this.f9818a == null && (colorStateList = this.b) != null && colorStateList.isStateful();
    }

    public boolean d(int[] iArr) {
        if (c()) {
            ColorStateList colorStateList = this.b;
            int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
            if (colorForState != this.c) {
                this.c = colorForState;
                return true;
            }
        }
        return false;
    }
}
