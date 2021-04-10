package defpackage;

import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/* renamed from: RF0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RF0 extends PrintDocumentAdapter {

    /* renamed from: a  reason: collision with root package name */
    public OF0 f8822a;

    public void onFinish() {
        super.onFinish();
        VF0 vf0 = (VF0) this.f8822a;
        vf0.h = null;
        vf0.c = -1;
        vf0.d = -1;
        vf0.m = 2;
        vf0.a();
        vf0.c();
        vf0.n = false;
    }

    public void onLayout(PrintAttributes printAttributes, PrintAttributes printAttributes2, CancellationSignal cancellationSignal, PrintDocumentAdapter.LayoutResultCallback layoutResultCallback, Bundle bundle) {
        OF0 of0 = this.f8822a;
        NF0 nf0 = new NF0(layoutResultCallback);
        VF0 vf0 = (VF0) of0;
        Objects.requireNonNull(vf0);
        vf0.f = printAttributes2.getResolution().getHorizontalDpi();
        vf0.g = printAttributes2.getMediaSize();
        vf0.j = nf0;
        if (vf0.m == 1) {
            nf0.f8535a.onLayoutFailed(vf0.b);
            vf0.c();
            return;
        }
        ((NF0) vf0.j).f8535a.onLayoutFinished(new PrintDocumentInfo.Builder(vf0.k.getTitle()).setContentType(0).setPageCount(-1).build(), true);
    }

    public void onStart() {
        ((VF0) this.f8822a).m = 0;
    }

    public void onWrite(PageRange[] pageRangeArr, ParcelFileDescriptor parcelFileDescriptor, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
        OF0 of0 = this.f8822a;
        QF0 qf0 = new QF0(writeResultCallback);
        VF0 vf0 = (VF0) of0;
        Objects.requireNonNull(vf0);
        int[] iArr = null;
        if (pageRangeArr == null || pageRangeArr.length == 0) {
            qf0.f8748a.onWriteFailed(null);
            return;
        }
        vf0.i = qf0;
        try {
            vf0.e = parcelFileDescriptor.dup();
            if (pageRangeArr.length != 1 || !pageRangeArr[0].equals(PageRange.ALL_PAGES)) {
                ArrayList arrayList = new ArrayList();
                for (PageRange pageRange : pageRangeArr) {
                    for (int start = pageRange.getStart(); start <= pageRange.getEnd(); start++) {
                        arrayList.add(Integer.valueOf(start));
                    }
                }
                int size = arrayList.size();
                iArr = new int[size];
                Iterator it = arrayList.iterator();
                for (int i = 0; i < size; i++) {
                    iArr[i] = ((Integer) it.next()).intValue();
                }
            }
            vf0.h = iArr;
            if (vf0.k.b(vf0.c, vf0.d)) {
                vf0.m = 1;
                return;
            }
            ((QF0) vf0.i).f8748a.onWriteFailed(vf0.b);
            vf0.c();
        } catch (IOException e) {
            PF0 pf0 = vf0.i;
            StringBuilder i2 = AbstractC2531fV.i("ParcelFileDescriptor.dup() failed: ");
            i2.append(e.toString());
            ((QF0) pf0).f8748a.onWriteFailed(i2.toString());
            vf0.c();
        }
    }
}
