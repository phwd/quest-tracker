package X;

import com.facebook.imagepipeline.producers.ProducerContextCallbacks;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.horizon.fbconnect.FBConnectLogger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1qU  reason: invalid class name */
public class AnonymousClass1qU {
    public static final Set<String> A0D;
    @GuardedBy("this")
    public EnumC10221rl A00;
    @GuardedBy("this")
    public boolean A01;
    @GuardedBy("this")
    public boolean A02;
    @GuardedBy("this")
    public boolean A03;
    public final C10061qu A04;
    public final AnonymousClass1qE A05;
    public final AnonymousClass1pG A06;
    public final C09811pd A07;
    public final Object A08;
    public final String A09;
    @Nullable
    public final String A0A;
    @GuardedBy("this")
    public final List<ProducerContextCallbacks> A0B;
    public final Map<String, Object> A0C;

    static {
        HashSet hashSet = new HashSet(2);
        Collections.addAll(hashSet, "id", "uri_source");
        A0D = new AnonymousClass0KQ(hashSet);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001a, code lost:
        if (r1.hasNext() == false) goto L_0x0027;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        ((X.AnonymousClass1t4) r1.next()).A00();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0012, code lost:
        r1 = r0.iterator();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A03() {
        /*
            r3 = this;
            r2 = r3
            monitor-enter(r2)
            boolean r0 = r3.A01     // Catch:{ all -> 0x0028 }
            if (r0 == 0) goto L_0x0007
            goto L_0x0026
        L_0x0007:
            r0 = 1
            r3.A01 = r0     // Catch:{ all -> 0x0028 }
            java.util.List<com.facebook.imagepipeline.producers.ProducerContextCallbacks> r1 = r3.A0B     // Catch:{ all -> 0x0028 }
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x0028 }
            r0.<init>(r1)     // Catch:{ all -> 0x0028 }
            monitor-exit(r2)
            java.util.Iterator r1 = r0.iterator()
        L_0x0016:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L_0x0027
            java.lang.Object r0 = r1.next()
            X.1t4 r0 = (X.AnonymousClass1t4) r0
            r0.A00()
            goto L_0x0016
        L_0x0026:
            monitor-exit(r2)
        L_0x0027:
            return
        L_0x0028:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1qU.A03():void");
    }

    public final void A04(AnonymousClass1t4 r2) {
        boolean z;
        synchronized (this) {
            this.A0B.add(r2);
            z = this.A01;
        }
        if (z) {
            r2.A00();
        }
    }

    public final synchronized boolean A08() {
        return this.A02;
    }

    public final synchronized boolean A09() {
        return this.A03;
    }

    public static void A00(@Nullable List<ProducerContextCallbacks> list) {
        C09971qh r0;
        if (list != null) {
            for (AnonymousClass1t4 r1 : list) {
                if (r1 instanceof C10161re) {
                    AnonymousClass1qT r12 = ((C10161re) r1).A02;
                    if (r12.A01.A08()) {
                        r0 = r12.A02;
                    }
                } else if (r1 instanceof AnonymousClass1rD) {
                    A00(C09931qV.A01(((AnonymousClass1rD) r1).A01));
                } else if (r1 instanceof AnonymousClass1s8) {
                    AnonymousClass1qN r13 = ((AnonymousClass1s8) r1).A01;
                    if (r13.A02.A08()) {
                        r0 = r13.A03;
                    }
                }
                r0.A03();
            }
        }
    }

    public static void A01(@Nullable List<ProducerContextCallbacks> list) {
        if (list != null) {
            for (AnonymousClass1t4 r1 : list) {
                if (r1 instanceof AnonymousClass1rD) {
                    A01(C09931qV.A02(((AnonymousClass1rD) r1).A01));
                }
            }
        }
    }

    public static void A02(@Nullable List<ProducerContextCallbacks> list) {
        if (list != null) {
            for (AnonymousClass1t4 r1 : list) {
                if (r1 instanceof AnonymousClass1rD) {
                    A02(C09931qV.A03(((AnonymousClass1rD) r1).A01));
                }
            }
        }
    }

    public final void A05(String str, @Nullable Object obj) {
        if (!A0D.contains(str)) {
            this.A0C.put(str, obj);
        }
    }

    public final void A06(@Nullable String str, @Nullable String str2) {
        Map<String, Object> map = this.A0C;
        map.put(FBConnectLogger.EXTRA_ORIGIN, str);
        map.put("origin_sub", str2);
    }

    public final void A07(@Nullable Map<String, ?> map) {
        if (map != null) {
            for (Map.Entry<String, ?> entry : map.entrySet()) {
                A05(entry.getKey(), entry.getValue());
            }
        }
    }

    public AnonymousClass1qU(C09811pd r4, String str, @Nullable String str2, AnonymousClass1qE r7, Object obj, AnonymousClass1pG r9, boolean z, boolean z2, EnumC10221rl r12, C10061qu r13) {
        Object obj2;
        this.A07 = r4;
        this.A09 = str;
        HashMap hashMap = new HashMap();
        this.A0C = hashMap;
        hashMap.put("id", str);
        Map<String, Object> map = this.A0C;
        if (r4 == null) {
            obj2 = "null-request";
        } else {
            obj2 = r4.A03;
        }
        map.put("uri_source", obj2);
        this.A0A = str2;
        this.A05 = r7;
        this.A08 = obj;
        this.A06 = r9;
        this.A03 = z;
        this.A00 = r12;
        this.A02 = z2;
        this.A01 = false;
        this.A0B = new ArrayList();
        this.A04 = r13;
    }
}
