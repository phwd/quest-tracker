package defpackage;

import java.util.List;

/* renamed from: sW  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4753sW {

    /* renamed from: a  reason: collision with root package name */
    public final int[] f11279a;
    public final float[] b;

    public C4753sW(List list, List list2) {
        int size = list.size();
        this.f11279a = new int[size];
        this.b = new float[size];
        for (int i = 0; i < size; i++) {
            this.f11279a[i] = ((Integer) list.get(i)).intValue();
            this.b[i] = ((Float) list2.get(i)).floatValue();
        }
    }

    public C4753sW(int i, int i2) {
        this.f11279a = new int[]{i, i2};
        this.b = new float[]{0.0f, 1.0f};
    }

    public C4753sW(int i, int i2, int i3) {
        this.f11279a = new int[]{i, i2, i3};
        this.b = new float[]{0.0f, 0.5f, 1.0f};
    }
}
