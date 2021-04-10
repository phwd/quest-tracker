package X;

import android.app.ActivityManager;
import android.os.Debug;

public final class hO implements IP {
    public ActivityManager A00;
    public final Runtime A01 = Runtime.getRuntime();

    @Override // X.IP
    public final String A2m() {
        return "memory_stats";
    }

    @Override // X.IP
    public final long A2n() {
        return 16;
    }

    @Override // X.IP
    public final void A1d(RunnableC0929p4 p4Var, Object obj, Object obj2) {
        A6 a6 = (A6) obj;
        A6 a62 = (A6) obj2;
        if (a62 != null) {
            if (a6 == null) {
                a6 = null;
            }
            Ic A2f = p4Var.A2f();
            ActivityManager.MemoryInfo memoryInfo = a62.A02;
            long j = memoryInfo.availMem;
            Ic.A00(A2f, "avail_mem", Long.valueOf(j));
            Ic.A00(p4Var.A2f(), "low_mem", Long.valueOf(memoryInfo.threshold));
            Ic.A00(p4Var.A2f(), "total_mem", Long.valueOf(memoryInfo.totalMem));
            if (a6 != null) {
                Ic.A00(p4Var.A2f(), "avail_mem_delta", Long.valueOf(a6.A02.availMem - j));
                Ic A2f2 = p4Var.A2f();
                long j2 = a62.A00;
                long j3 = a6.A00;
                Ic.A00(A2f2, "java_heap_used", Long.valueOf(j2 - j3));
                Ic A2f3 = p4Var.A2f();
                long j4 = a62.A01;
                long j5 = a6.A01;
                Ic.A00(A2f3, "native_heap_used", Long.valueOf(j4 - j5));
                Ic.A00(p4Var.A2f(), "java_heap_at_start", Long.valueOf(j3));
                Ic.A00(p4Var.A2f(), "native_heap_at_start", Long.valueOf(j5));
            }
        }
    }

    @Override // X.IP
    public final boolean A3S(C0915oq oqVar) {
        if (!(oqVar instanceof C0650du)) {
            return false;
        }
        return A4.A03;
    }

    @Override // X.IP
    public final Object A56() {
        A6 a6 = new A6();
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        a6.A02 = memoryInfo;
        this.A00.getMemoryInfo(memoryInfo);
        Runtime runtime = this.A01;
        a6.A00 = runtime.totalMemory() - runtime.freeMemory();
        a6.A01 = Debug.getNativeHeapAllocatedSize() - Debug.getNativeHeapFreeSize();
        return a6;
    }

    @Override // X.IP
    public final Object A58() {
        A6 a6 = new A6();
        a6.A02 = new ActivityManager.MemoryInfo();
        a6.A00 = 0;
        a6.A01 = 0;
        return a6;
    }

    public hO(ActivityManager activityManager) {
        this.A00 = activityManager;
    }

    @Override // X.IP
    public final Class A2t() {
        return A6.class;
    }

    @Override // X.IP
    public final Class A2w() {
        return A6.class;
    }
}
