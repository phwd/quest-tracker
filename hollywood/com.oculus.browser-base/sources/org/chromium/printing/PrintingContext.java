package org.chromium.printing;

import J.N;
import android.app.Activity;
import android.print.PageRange;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PrintingContext {

    /* renamed from: a  reason: collision with root package name */
    public final UF0 f11007a = VF0.b();
    public final long b;

    public PrintingContext(long j) {
        this.b = j;
    }

    public static PrintingContext create(long j) {
        Object obj = ThreadUtils.f10596a;
        return new PrintingContext(j);
    }

    public static void pdfWritingDone(int i) {
        PageRange[] pageRangeArr;
        Object obj = ThreadUtils.f10596a;
        VF0 vf0 = (VF0) VF0.b();
        if (vf0.m == 1) {
            vf0.m = 0;
            vf0.a();
            if (i > 0) {
                int[] iArr = vf0.h;
                if (iArr != null) {
                    int length = iArr.length;
                    pageRangeArr = new PageRange[length];
                    for (int i2 = 0; i2 < length; i2++) {
                        int i3 = iArr[i2];
                        pageRangeArr[i2] = new PageRange(i3, i3);
                    }
                } else {
                    pageRangeArr = new PageRange[]{new PageRange(0, i - 1)};
                }
                ((QF0) vf0.i).f8748a.onWriteFinished(pageRangeArr);
                return;
            }
            PF0 pf0 = vf0.i;
            ((QF0) pf0).f8748a.onWriteFailed(vf0.b);
            vf0.c();
        }
    }

    public static void setPendingPrint(WindowAndroid windowAndroid, Printable printable, int i, int i2) {
        Activity activity = (Activity) windowAndroid.s0().get();
        if (activity != null) {
            ((VF0) VF0.b()).d(printable, new SF0(activity), i, i2);
        }
    }

    public void askUserForSettings(int i) {
        Object obj = ThreadUtils.f10596a;
        VF0 vf0 = (VF0) this.f11007a;
        if (vf0.m == 2) {
            N.M8HtOhJl(this.b, this, false);
            return;
        }
        Objects.requireNonNull(vf0);
        N.M8HtOhJl(this.b, this, true);
    }

    public int getDpi() {
        Object obj = ThreadUtils.f10596a;
        return ((VF0) this.f11007a).f;
    }

    public int getFileDescriptor() {
        Object obj = ThreadUtils.f10596a;
        return ((VF0) this.f11007a).e.getFd();
    }

    public int getHeight() {
        Object obj = ThreadUtils.f10596a;
        return ((VF0) this.f11007a).g.getHeightMils();
    }

    public int[] getPages() {
        Object obj = ThreadUtils.f10596a;
        int[] iArr = ((VF0) this.f11007a).h;
        if (iArr == null) {
            return null;
        }
        return (int[]) iArr.clone();
    }

    public int getWidth() {
        Object obj = ThreadUtils.f10596a;
        return ((VF0) this.f11007a).g.getWidthMils();
    }

    public void showPrintDialog() {
        Object obj = ThreadUtils.f10596a;
        ((VF0) this.f11007a).e();
        N.Mmq2M8tt(this.b, this);
    }
}
