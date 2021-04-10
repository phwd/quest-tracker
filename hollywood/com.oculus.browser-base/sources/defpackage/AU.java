package defpackage;

import android.os.Build;
import android.os.SystemClock;
import android.view.InputDevice;
import java.util.BitSet;
import java.util.List;

/* renamed from: AU  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AU {

    /* renamed from: a  reason: collision with root package name */
    public static final int[] f7672a = {96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 109, 108, 106, 107, 19, 20, 21, 22, 110};
    public int b;
    public int c;
    public long d;
    public final float[] e = new float[4];
    public final float[] f = new float[17];
    public final float[] g = new float[256];
    public final float[] h = new float[256];
    public String i;
    public int[] j;
    public NU k;

    public AU(int i2, InputDevice inputDevice) {
        NU nu;
        NU nu2;
        this.c = i2;
        this.b = inputDevice.getId();
        this.i = inputDevice.getName();
        this.d = SystemClock.uptimeMillis();
        List<InputDevice.MotionRange> motionRanges = inputDevice.getMotionRanges();
        this.j = new int[motionRanges.size()];
        int i3 = 0;
        int i4 = 0;
        for (InputDevice.MotionRange motionRange : motionRanges) {
            if ((motionRange.getSource() & 16) != 0) {
                this.j[i4] = motionRange.getAxis();
                i4++;
            }
        }
        BitSet bitSet = new BitSet(110);
        boolean[] hasKeys = inputDevice.hasKeys(f7672a);
        while (true) {
            int[] iArr = f7672a;
            if (i3 >= iArr.length) {
                break;
            }
            if (hasKeys[i3]) {
                bitSet.set(iArr[i3]);
            }
            i3++;
        }
        int[] iArr2 = this.j;
        int productId = inputDevice.getProductId();
        int vendorId = inputDevice.getVendorId();
        NU nu3 = null;
        if (vendorId == 1356 && (productId == 1476 || productId == 2508 || productId == 2976)) {
            if (Build.VERSION.SDK_INT >= 28) {
                nu = new LU(null);
            } else {
                nu = new HU();
            }
        } else if (vendorId == 1118 && productId == 736) {
            nu = new MU(null);
        } else {
            nu = (vendorId == 2652 && productId == 34050) ? new JU(iArr2) : null;
        }
        if (nu == null) {
            String name = inputDevice.getName();
            if (name.startsWith("NVIDIA Corporation NVIDIA Controller") || name.equals("Microsoft X-Box 360 pad")) {
                nu2 = new LU(null);
            } else if (name.equals("Sony PLAYSTATION(R)3 Controller")) {
                if (Build.VERSION.SDK_INT >= 28) {
                    nu2 = new FU(null);
                } else {
                    nu2 = new GU(null);
                }
            } else if (name.equals("Samsung Game Pad EI-GP20")) {
                nu2 = new IU(null);
            } else {
                if (name.equals("Amazon Fire Game Controller")) {
                    nu2 = new EU(null);
                }
                nu = nu3;
            }
            nu3 = nu2;
            nu = nu3;
        }
        this.k = nu == null ? new KU(iArr2, bitSet) : nu;
    }
}
