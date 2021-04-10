package X;

import android.annotation.SuppressLint;
import com.facebook.annotations.OkToExtend;
import com.facebook.secure.context.IntentLaunchingPlugin;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

@OkToExtend
@SuppressLint({"InstanceMethodCanBeStatic"})
/* renamed from: X.0ao  reason: invalid class name and case insensitive filesystem */
public final class C02600ao {
    @Nullable
    public static C02600ao A0C;
    public static final C02620au A0D = new C02620au();
    public static final AnonymousClass1UE A0E = new AnonymousClass1UE();
    public static final C02660b0 A0F = new C02660b0();
    @Nullable
    public AnonymousClass1Di A00 = null;
    @Nullable
    public AnonymousClass1Di A01 = null;
    @Nullable
    public AnonymousClass1Di A02 = null;
    @Nullable
    public AnonymousClass0II A03 = null;
    @Nullable
    public C002608p A04 = null;
    @Nullable
    public AnonymousClass0IG A05 = null;
    @Nullable
    public C002408m A06 = null;
    public Map<C02870bf, C002308l> A07 = new HashMap();
    public Map<C02870bf, C002308l> A08 = new HashMap();
    public final List<IntentLaunchingPlugin> A09 = AnonymousClass1C2.A00();
    public final Map<C02870bf, AnonymousClass1Di> A0A = new HashMap();
    public final Map<C02870bf, AnonymousClass1Di> A0B = new HashMap();

    public static synchronized AnonymousClass0II A01(C02600ao r4) {
        AnonymousClass0II r3;
        synchronized (r4) {
            r3 = r4.A03;
            if (r3 == null) {
                r3 = new AnonymousClass0II(A0D, A0E, A0F);
                r4.A03 = r3;
            }
        }
        return r3;
    }

    public static final synchronized C002608p A02(C02600ao r4) {
        C002608p r3;
        synchronized (r4) {
            r3 = r4.A04;
            if (r3 == null) {
                r3 = new C002608p(A0D, A0E, A0F);
                r4.A04 = r3;
            }
        }
        return r3;
    }

    private synchronized AnonymousClass0IG A03() {
        AnonymousClass0IG r3;
        r3 = this.A05;
        if (r3 == null) {
            r3 = new AnonymousClass0IG(A0D, A0E, A0F);
            this.A05 = r3;
        }
        return r3;
    }

    public static final synchronized C002408m A04(C02600ao r4) {
        C002408m r3;
        synchronized (r4) {
            r3 = r4.A06;
            if (r3 == null) {
                r3 = new C002408m(A0D, A0E, A0F);
                r4.A06 = r3;
            }
        }
        return r3;
    }

    private synchronized C002308l A05(C02870bf r6) {
        if (!this.A07.containsKey(r6)) {
            this.A07.put(r6, new C002308l(r6, A0D, A0E, A0F));
        }
        return this.A07.get(r6);
    }

    public final synchronized AnonymousClass1Di A06() {
        AnonymousClass1Di r2;
        r2 = this.A02;
        if (r2 == null) {
            r2 = new AnonymousClass1Di(A03(), this.A09);
            this.A02 = r2;
        }
        return r2;
    }

    public final synchronized AnonymousClass1Di A07(C02870bf r5) {
        Map<C02870bf, AnonymousClass1Di> map;
        map = this.A0A;
        if (!map.containsKey(r5)) {
            map.put(r5, new AnonymousClass1Di(A05(r5), this.A09));
        }
        return map.get(r5);
    }

    public final synchronized AnonymousClass1Di A08(C02870bf r4) {
        AnonymousClass1Di r0;
        r0 = new AnonymousClass1Di(A05(r4), this.A09);
        r0.A01();
        return r0;
    }

    public static synchronized C02600ao A00() {
        C02600ao r0;
        synchronized (C02600ao.class) {
            r0 = A0C;
            if (r0 == null) {
                r0 = new C02600ao();
                A0C = r0;
            }
        }
        return r0;
    }
}
