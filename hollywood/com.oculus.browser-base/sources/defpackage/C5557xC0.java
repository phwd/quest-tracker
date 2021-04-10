package defpackage;

import android.net.Uri;

/* renamed from: xC0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5557xC0 implements Comparable {
    public Uri F;
    public long G;
    public int H;

    public C5557xC0(Uri uri, long j, int i) {
        this.F = uri;
        this.G = j;
        this.H = i;
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        int i = (((C5557xC0) obj).G > this.G ? 1 : (((C5557xC0) obj).G == this.G ? 0 : -1));
        if (i < 0) {
            return -1;
        }
        return i == 0 ? 0 : 1;
    }
}
