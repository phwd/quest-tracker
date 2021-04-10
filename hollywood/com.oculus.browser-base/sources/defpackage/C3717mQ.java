package defpackage;

import android.graphics.RectF;
import java.util.Comparator;

/* renamed from: mQ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3717mQ implements Comparator {
    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        RectF rectF = (RectF) obj;
        RectF rectF2 = (RectF) obj2;
        int compare = Float.compare(rectF.top, rectF2.top);
        return compare != 0 ? compare : Float.compare(rectF.left, rectF2.left);
    }
}
