package defpackage;

import android.graphics.Rect;
import java.util.Arrays;
import org.chromium.base.UnguessableToken;

/* renamed from: Tv0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1210Tv0 {

    /* renamed from: a  reason: collision with root package name */
    public UnguessableToken f8994a;
    public int b;
    public int c;
    public C1210Tv0[] d;
    public Rect[] e;
    public int f;
    public int g;

    public C1210Tv0(UnguessableToken unguessableToken, int i, int i2, int i3, int i4) {
        this.f8994a = unguessableToken;
        this.b = i;
        this.c = i2;
        this.f = i3;
        this.g = i4;
    }

    public boolean equals(Object obj) {
        if (obj == null || C1210Tv0.class != obj.getClass()) {
            return false;
        }
        C1210Tv0 tv0 = (C1210Tv0) obj;
        if (this.f8994a.equals(tv0.f8994a) && this.c == tv0.c && this.b == tv0.b && Arrays.equals(this.d, tv0.d) && Arrays.equals(this.e, tv0.e)) {
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuilder i = AbstractC2531fV.i("Guid : ");
        i.append(this.f8994a);
        i.append(", ContentWidth : ");
        i.append(this.b);
        i.append(", ContentHeight: ");
        i.append(this.c);
        i.append(", SubFrames: ");
        i.append(Arrays.deepToString(this.d));
        i.append(", SubFrameClips: ");
        i.append(Arrays.deepToString(this.e));
        return i.toString();
    }
}
