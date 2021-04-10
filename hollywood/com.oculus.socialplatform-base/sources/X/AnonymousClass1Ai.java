package X;

import android.os.Trace;
import androidx.recyclerview.widget.RecyclerView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

/* renamed from: X.1Ai  reason: invalid class name */
public final class AnonymousClass1Ai implements Runnable {
    public static Comparator<AnonymousClass1BJ> A04 = new AnonymousClass1BC();
    public static final ThreadLocal<AnonymousClass1Ai> A05 = new ThreadLocal<>();
    public static final String __redex_internal_original_name = "androidx.recyclerview.widget.GapWorker";
    public long A00;
    public long A01;
    public ArrayList<RecyclerView> A02 = new ArrayList<>();
    public ArrayList<AnonymousClass1BJ> A03 = new ArrayList<>();

    public static AnonymousClass1Ah A00(RecyclerView recyclerView, int i, long j) {
        int A3V = recyclerView.mChildHelper.A01.A3V();
        for (int i2 = 0; i2 < A3V; i2++) {
            AnonymousClass1Ah childViewHolderInt = RecyclerView.getChildViewHolderInt(recyclerView.mChildHelper.A01.A3U(i2));
            if (childViewHolderInt.mPosition == i && !childViewHolderInt.isInvalid()) {
                return null;
            }
        }
        AnonymousClass1Af r3 = recyclerView.mRecycler;
        try {
            recyclerView.onEnterLayoutOrScroll();
            AnonymousClass1Ah A042 = r3.A04(i, j);
            if (A042 != null) {
                if (!A042.isBound() || A042.isInvalid()) {
                    r3.A0A(A042, false);
                } else {
                    r3.A07(A042.itemView);
                }
            }
            return A042;
        } finally {
            recyclerView.onExitLayoutOrScroll(false);
        }
    }

    public final void run() {
        long j;
        WeakReference<RecyclerView> weakReference;
        RecyclerView recyclerView;
        AnonymousClass1BJ r0;
        try {
            Trace.beginSection(RecyclerView.TRACE_PREFETCH_TAG);
            ArrayList<RecyclerView> arrayList = this.A02;
            if (!arrayList.isEmpty()) {
                int size = arrayList.size();
                long j2 = 0;
                for (int i = 0; i < size; i++) {
                    RecyclerView recyclerView2 = this.A02.get(i);
                    if (recyclerView2.getWindowVisibility() == 0) {
                        j2 = Math.max(recyclerView2.getDrawingTime(), j2);
                    }
                }
                if (j2 != 0) {
                    long nanos = TimeUnit.MILLISECONDS.toNanos(j2) + this.A00;
                    int size2 = this.A02.size();
                    int i2 = 0;
                    for (int i3 = 0; i3 < size2; i3++) {
                        RecyclerView recyclerView3 = this.A02.get(i3);
                        if (recyclerView3.getWindowVisibility() == 0) {
                            recyclerView3.mPrefetchRegistry.A00(recyclerView3, false);
                            i2 += recyclerView3.mPrefetchRegistry.A00;
                        }
                    }
                    this.A03.ensureCapacity(i2);
                    int i4 = 0;
                    for (int i5 = 0; i5 < size2; i5++) {
                        RecyclerView recyclerView4 = this.A02.get(i5);
                        if (recyclerView4.getWindowVisibility() == 0) {
                            AnonymousClass1Ak r9 = recyclerView4.mPrefetchRegistry;
                            int abs = Math.abs(r9.A01) + Math.abs(r9.A02);
                            for (int i6 = 0; i6 < (r9.A00 << 1); i6 += 2) {
                                ArrayList<AnonymousClass1BJ> arrayList2 = this.A03;
                                if (i4 >= arrayList2.size()) {
                                    r0 = new AnonymousClass1BJ();
                                    arrayList2.add(r0);
                                } else {
                                    r0 = arrayList2.get(i4);
                                }
                                int[] iArr = r9.A03;
                                int i7 = iArr[i6 + 1];
                                boolean z = false;
                                if (i7 <= abs) {
                                    z = true;
                                }
                                r0.A04 = z;
                                r0.A02 = abs;
                                r0.A00 = i7;
                                r0.A03 = recyclerView4;
                                r0.A01 = iArr[i6];
                                i4++;
                            }
                        }
                    }
                    Collections.sort(this.A03, A04);
                    int i8 = 0;
                    while (true) {
                        ArrayList<AnonymousClass1BJ> arrayList3 = this.A03;
                        if (i8 >= arrayList3.size()) {
                            break;
                        }
                        AnonymousClass1BJ r7 = arrayList3.get(i8);
                        if (r7.A03 == null) {
                            break;
                        }
                        if (r7.A04) {
                            j = RecyclerView.FOREVER_NS;
                        } else {
                            j = nanos;
                        }
                        AnonymousClass1Ah A002 = A00(r7.A03, r7.A01, j);
                        if (!(A002 == null || (weakReference = A002.mNestedRecyclerView) == null || !A002.isBound() || A002.isInvalid() || (recyclerView = weakReference.get()) == null)) {
                            if (recyclerView.mDataSetHasChangedAfterLayout && recyclerView.mChildHelper.A01.A3V() != 0) {
                                recyclerView.removeAndRecycleViews();
                            }
                            AnonymousClass1Ak r6 = recyclerView.mPrefetchRegistry;
                            r6.A00(recyclerView, true);
                            if (r6.A00 != 0) {
                                try {
                                    Trace.beginSection(RecyclerView.TRACE_NESTED_PREFETCH_TAG);
                                    AnonymousClass1As r10 = recyclerView.mState;
                                    AnonymousClass1Aj r5 = recyclerView.mAdapter;
                                    r10.A08 = 1;
                                    r10.A07 = r5.getItemCount();
                                    r10.A0A = false;
                                    r10.A0D = false;
                                    r10.A0B = false;
                                    for (int i9 = 0; i9 < (r6.A00 << 1); i9 += 2) {
                                        A00(recyclerView, r6.A03[i9], nanos);
                                    }
                                } finally {
                                    Trace.endSection();
                                }
                            }
                        }
                        r7.A04 = false;
                        r7.A02 = 0;
                        r7.A00 = 0;
                        r7.A03 = null;
                        r7.A01 = 0;
                        i8++;
                    }
                }
            }
        } finally {
            this.A01 = 0;
            Trace.endSection();
        }
    }

    public final void A01(RecyclerView recyclerView, int i, int i2) {
        if (recyclerView.isAttachedToWindow() && this.A01 == 0) {
            this.A01 = System.nanoTime();
            recyclerView.post(this);
        }
        AnonymousClass1Ak r0 = recyclerView.mPrefetchRegistry;
        r0.A01 = i;
        r0.A02 = i2;
    }
}
