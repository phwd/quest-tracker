package defpackage;

import android.util.Pair;

/* renamed from: vy1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5345vy1 extends Pair {
    public C5345vy1(C3640ly1 ly1, C3640ly1 ly12) {
        super(ly1, ly12);
    }

    public int hashCode() {
        Object obj = ((Pair) this).first;
        int i = 0;
        int hashCode = ((obj == null ? 0 : ((C3640ly1) obj).hashCode()) + 31) * 31;
        Object obj2 = ((Pair) this).second;
        if (obj2 != null) {
            i = ((C3640ly1) obj2).hashCode();
        }
        return hashCode + i;
    }
}
