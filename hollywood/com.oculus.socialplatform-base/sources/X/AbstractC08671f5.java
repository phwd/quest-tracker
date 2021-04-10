package X;

import android.util.Log;
import java.util.Map;

/* renamed from: X.1f5  reason: invalid class name and case insensitive filesystem */
public interface AbstractC08671f5 {
    public static final boolean A08 = Log.isLoggable("Engine", 2);
    public final C08691f7 A00;
    public final AnonymousClass1fN A01;
    public final AnonymousClass1eX A02;
    public final C08051dt A03;
    public final C09031fy A04;
    public final AnonymousClass1fV A05;
    public final C08471eh A06;
    public final C07821dS A07;

    default AbstractC08671f5(C07821dS r8, AbstractC08061du r9, ExecutorServiceC07671cp r10, ExecutorServiceC07671cp r11, ExecutorServiceC07671cp r12, ExecutorServiceC07671cp r13) {
        this.A07 = r8;
        this.A03 = new C08051dt(r9);
        C08691f7 r2 = new C08691f7();
        this.A00 = r2;
        synchronized (this) {
            synchronized (r2) {
                r2.A00 = this;
            }
        }
        this.A04 = new C09031fy();
        this.A05 = new AnonymousClass1fV();
        this.A02 = new AnonymousClass1eX(r10, r11, r12, r13, this, this);
        this.A01 = new AnonymousClass1fN(this.A03);
        this.A06 = new C08471eh();
        r8.A00 = this;
    }

    final synchronized default void A02(AbstractC08651f2<?> r3, AbstractC06491aL r4, C08701f8<?> r5) {
        Map<AbstractC06491aL, AbstractC08651f2<?>> map;
        if (r5 != null) {
            if (r5.A03) {
                this.A00.A01(r4, r5);
            }
        }
        AnonymousClass1fV r1 = this.A05;
        if (r3.A09) {
            map = r1.A01;
        } else {
            map = r1.A00;
        }
        if (r3.equals(map.get(r4))) {
            map.remove(r4);
        }
    }

    static final default void A00(AnonymousClass1fR<?> r1) {
        if (r1 instanceof C08701f8) {
            ((C08701f8) r1).A00();
            return;
        }
        throw new IllegalArgumentException("Cannot release anything but an EngineResource");
    }

    final default void A01(AbstractC06491aL r4, C08701f8<?> r5) {
        C08691f7 r2 = this.A00;
        synchronized (r2) {
            C08761fF remove = r2.A02.remove(r4);
            if (remove != null) {
                remove.A00 = null;
                remove.clear();
            }
        }
        if (r5.A03) {
            this.A07.A04(r4, r5);
        } else {
            this.A06.A00(r5, false);
        }
    }
}
