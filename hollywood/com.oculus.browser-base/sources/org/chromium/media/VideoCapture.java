package org.chromium.media;

import J.N;
import android.hardware.display.DisplayManager;
import java.util.ArrayList;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class VideoCapture {

    /* renamed from: a  reason: collision with root package name */
    public int f10987a;
    public boolean b;
    public VideoCaptureFormat c;
    public final int d;
    public final long e;

    public VideoCapture(int i, long j) {
        this.d = i;
        this.e = j;
    }

    public static int[] c(ArrayList arrayList) {
        int[] iArr = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            iArr[i] = ((Integer) arrayList.get(i)).intValue();
        }
        return iArr;
    }

    public final int a() {
        int i;
        if (this.b) {
            i = 360 - b();
        } else {
            i = b();
        }
        return (this.f10987a + i) % 360;
    }

    public abstract boolean allocate(int i, int i2, int i3, boolean z);

    public final int b() {
        int rotation = ((DisplayManager) ContextUtils.getApplicationContext().getSystemService("display")).getDisplay(0).getRotation();
        if (rotation == 1) {
            return 90;
        }
        if (rotation == 2) {
            return 180;
        }
        if (rotation != 3) {
            return 0;
        }
        return 270;
    }

    public void d(long j) {
        N.MdZBZ$ST(this.e, this, j, null);
    }

    public abstract void deallocate();

    public final int getColorspace() {
        int i = this.c.d;
        int i2 = 17;
        if (i != 17) {
            i2 = 35;
            if (i != 35) {
                i2 = 842094169;
                if (i != 842094169) {
                    return 0;
                }
            }
        }
        return i2;
    }

    public abstract void getPhotoCapabilitiesAsync(long j);

    public final int queryFrameRate() {
        return this.c.c;
    }

    public final int queryHeight() {
        return this.c.b;
    }

    public final int queryWidth() {
        return this.c.f10988a;
    }

    public abstract void setPhotoOptions(double d2, int i, double d3, int i2, double d4, double d5, double[] dArr, boolean z, double d6, double d7, int i3, double d8, boolean z2, boolean z3, int i4, boolean z4, boolean z5, double d9);

    public final void setTestMode() {
    }

    public abstract boolean startCaptureMaybeAsync();

    public abstract boolean stopCaptureAndBlockUntilStopped();

    public abstract void takePhotoAsync(long j);
}
