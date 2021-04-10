package defpackage;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.SparseArray;
import com.google.android.gms.internal.vision.zzae;
import com.google.android.gms.internal.vision.zzy;

/* renamed from: Qf1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0992Qf1 {

    /* renamed from: a  reason: collision with root package name */
    public zzae[] f8775a;
    public Point[] b;
    public Rect c;

    public C0992Qf1(SparseArray sparseArray) {
        this.f8775a = new zzae[sparseArray.size()];
        int i = 0;
        while (true) {
            zzae[] zzaeArr = this.f8775a;
            if (i < zzaeArr.length) {
                zzaeArr[i] = (zzae) sparseArray.valueAt(i);
                i++;
            } else {
                return;
            }
        }
    }

    public Point[] a() {
        C0992Qf1 qf1;
        zzae[] zzaeArr;
        C0992Qf1 qf12 = this;
        if (qf12.b == null) {
            char c2 = 0;
            if (qf12.f8775a.length == 0) {
                qf12.b = new Point[0];
            } else {
                int i = Integer.MIN_VALUE;
                int i2 = 0;
                int i3 = Integer.MAX_VALUE;
                int i4 = Integer.MAX_VALUE;
                int i5 = Integer.MIN_VALUE;
                while (true) {
                    zzaeArr = qf12.f8775a;
                    if (i2 >= zzaeArr.length) {
                        break;
                    }
                    zzy zzy = zzaeArr[i2].G;
                    zzy zzy2 = zzaeArr[c2].G;
                    double sin = Math.sin(Math.toRadians((double) zzy2.f9676J));
                    double cos = Math.cos(Math.toRadians((double) zzy2.f9676J));
                    Point[] pointArr = new Point[4];
                    pointArr[c2] = new Point(zzy.F, zzy.G);
                    pointArr[c2].offset(-zzy2.F, -zzy2.G);
                    int i6 = (int) ((((double) pointArr[c2].y) * sin) + (((double) pointArr[c2].x) * cos));
                    int i7 = (int) ((((double) pointArr[0].y) * cos) + (((double) (-pointArr[0].x)) * sin));
                    pointArr[0].x = i6;
                    pointArr[0].y = i7;
                    pointArr[1] = new Point(zzy.H + i6, i7);
                    pointArr[2] = new Point(zzy.H + i6, zzy.I + i7);
                    pointArr[3] = new Point(i6, i7 + zzy.I);
                    i5 = i5;
                    for (int i8 = 0; i8 < 4; i8++) {
                        Point point = pointArr[i8];
                        i3 = Math.min(i3, point.x);
                        i = Math.max(i, point.x);
                        i4 = Math.min(i4, point.y);
                        i5 = Math.max(i5, point.y);
                    }
                    i2++;
                    c2 = 0;
                    qf12 = this;
                }
                zzy zzy3 = zzaeArr[c2].G;
                int i9 = zzy3.F;
                int i10 = zzy3.G;
                double sin2 = Math.sin(Math.toRadians((double) zzy3.f9676J));
                double cos2 = Math.cos(Math.toRadians((double) zzy3.f9676J));
                Point[] pointArr2 = {new Point(i3, i4), new Point(i, i4), new Point(i, i5), new Point(i3, i5)};
                for (int i11 = 0; i11 < 4; i11++) {
                    int i12 = (int) ((((double) pointArr2[i11].x) * cos2) - (((double) pointArr2[i11].y) * sin2));
                    double d = ((double) pointArr2[i11].y) * cos2;
                    pointArr2[i11].x = i12;
                    pointArr2[i11].y = (int) (d + (((double) pointArr2[i11].x) * sin2));
                    pointArr2[i11].offset(i9, i10);
                }
                qf1 = this;
                qf1.b = pointArr2;
                return qf1.b;
            }
        }
        qf1 = qf12;
        return qf1.b;
    }
}
