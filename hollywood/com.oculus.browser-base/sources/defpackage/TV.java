package defpackage;

import android.content.Context;
import android.util.SparseIntArray;
import java.util.Objects;

/* renamed from: TV  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TV {

    /* renamed from: a  reason: collision with root package name */
    public final SparseIntArray f8961a = new SparseIntArray();
    public UV b;

    public TV(UV uv) {
        Objects.requireNonNull(uv, "null reference");
        this.b = uv;
    }

    public int a(Context context, AbstractC2129d7 d7Var) {
        Objects.requireNonNull(context, "null reference");
        Objects.requireNonNull(d7Var, "null reference");
        int minApkVersion = d7Var.getMinApkVersion();
        int i = this.f8961a.get(minApkVersion, -1);
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        while (true) {
            if (i2 < this.f8961a.size()) {
                int keyAt = this.f8961a.keyAt(i2);
                if (keyAt > minApkVersion && this.f8961a.get(keyAt) == 0) {
                    i = 0;
                    break;
                }
                i2++;
            } else {
                break;
            }
        }
        if (i == -1) {
            i = this.b.b(context, minApkVersion);
        }
        this.f8961a.put(minApkVersion, i);
        return i;
    }
}
