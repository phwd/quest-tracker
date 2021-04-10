package X;

import android.os.SystemClock;
import androidx.annotation.NonNull;
import com.bumptech.glide.load.engine.DecodeJob;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* renamed from: X.1f1  reason: invalid class name */
public final class AnonymousClass1f1<R> implements AnonymousClass1fL, Runnable, Comparable<AnonymousClass1f1<?>>, AbstractC08161e6 {
    public static final String __redex_internal_original_name = "com.bumptech.glide.load.engine.DecodeJob";
    public int A00;
    public int A01;
    public int A02;
    public C08731fB A03;
    public AnonymousClass1cY A04;
    public AbstractC06491aL A05;
    public AbstractC06491aL A06;
    public AnonymousClass1cO A07;
    public AbstractC08841fc A08;
    public DecodeJob.Callback<R> A09;
    public C06481aK A0A;
    public Integer A0B;
    public Integer A0C;
    public Object A0D;
    public boolean A0E;
    public long A0F;
    public AnonymousClass1fM A0G;
    public AbstractC06491aL A0H;
    public AbstractC07051bX<?> A0I;
    public Object A0J;
    public Thread A0K;
    public final AnonymousClass1ez<R> A0L = new AnonymousClass1ez<>();
    public final AnonymousClass1fO<?> A0M = new AnonymousClass1fO<>();
    public final C08881fg A0N = new C08881fg();
    public final C08051dt A0O;
    public final AnonymousClass06o<AnonymousClass1f1<?>> A0P;
    public final AbstractC08341eQ A0Q = new C08331eP();
    public final List<Throwable> A0R = new ArrayList();
    public volatile AbstractC08981fq A0S;
    public volatile boolean A0T;
    public volatile boolean A0U;

    private AbstractC08981fq A00() {
        Integer num = this.A0C;
        switch (num.intValue()) {
            case 1:
                return new AnonymousClass1f0(this.A0L, this);
            case 2:
                AnonymousClass1ez<R> r2 = this.A0L;
                return new C08681f6(r2.A02(), r2, this);
            case 3:
                return new C08661f4(this.A0L, this);
            case 4:
            default:
                throw new IllegalStateException(AnonymousClass006.A07("Unrecognized stage: ", A02(num)));
            case 5:
                return null;
        }
    }

    public static String A02(Integer num) {
        if (num == null) {
            return "null";
        }
        switch (num.intValue()) {
            case 1:
                return "RESOURCE_CACHE";
            case 2:
                return "DATA_CACHE";
            case 3:
                return "SOURCE";
            case 4:
                return "ENCODE";
            case 5:
                return "FINISHED";
            default:
                return "INITIALIZE";
        }
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x005e, code lost:
        if (r3.A0E != false) goto L_0x0060;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void A03() {
        /*
        // Method dump skipped, instructions count: 1042
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1f1.A03():void");
    }

    private void A06() {
        Throwable th;
        this.A0Q.A00();
        if (this.A0U) {
            List<Throwable> list = this.A0R;
            if (list.isEmpty()) {
                th = null;
            } else {
                th = list.get(list.size() - 1);
            }
            throw new IllegalStateException("Already notified", th);
        }
        this.A0U = true;
    }

    public static void A07(AnonymousClass1f1 r4) {
        C08881fg r1 = r4.A0N;
        synchronized (r1) {
            r1.A00 = false;
            r1.A02 = false;
            r1.A01 = false;
        }
        AnonymousClass1fO<?> r12 = r4.A0M;
        r12.A00 = null;
        r12.A01 = null;
        r12.A02 = null;
        AnonymousClass1ez<R> r13 = r4.A0L;
        r13.A02 = null;
        r13.A0A = null;
        r13.A04 = null;
        r13.A08 = null;
        r13.A09 = null;
        r13.A05 = null;
        r13.A03 = null;
        r13.A0B = null;
        r13.A06 = null;
        r13.A0H.clear();
        r13.A0D = false;
        r13.A0G.clear();
        r13.A0C = false;
        r4.A0U = false;
        r4.A03 = null;
        r4.A06 = null;
        r4.A07 = null;
        r4.A04 = null;
        r4.A0A = null;
        r4.A09 = null;
        r4.A0C = null;
        r4.A0S = null;
        r4.A0K = null;
        r4.A05 = null;
        r4.A0J = null;
        r4.A0G = null;
        r4.A0I = null;
        r4.A0F = 0;
        r4.A0T = false;
        r4.A0D = null;
        r4.A0R.clear();
        r4.A0P.A8z(r4);
    }

    @Override // X.AbstractC08161e6
    @NonNull
    public final AbstractC08341eQ A5G() {
        return this.A0Q;
    }

    @Override // X.AnonymousClass1fL
    public final void A6w(AbstractC06491aL r3, Object obj, AbstractC07051bX<?> r5, AnonymousClass1fM r6, AbstractC06491aL r7) {
        this.A05 = r3;
        this.A0J = obj;
        this.A0I = r5;
        this.A0G = r6;
        this.A0H = r7;
        if (Thread.currentThread() != this.A0K) {
            this.A0B = AnonymousClass007.A03;
            this.A09.A03(this);
            return;
        }
        A03();
    }

    @Override // X.AnonymousClass1fL
    public final void A9L() {
        this.A0B = AnonymousClass007.A01;
        this.A09.A03(this);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // java.lang.Comparable
    public final int compareTo(@NonNull AnonymousClass1f1<?> r3) {
        AnonymousClass1f1<?> r32 = r3;
        int ordinal = this.A04.ordinal() - r32.A04.ordinal();
        if (ordinal == 0) {
            return this.A01 - r32.A01;
        }
        return ordinal;
    }

    public final void run() {
        String str;
        AbstractC07051bX<?> r3 = this.A0I;
        try {
            if (this.A0T) {
                A04();
            } else {
                Integer num = this.A0B;
                int intValue = num.intValue();
                switch (intValue) {
                    case 0:
                        this.A0C = A01(this, AnonymousClass007.A00);
                        this.A0S = A00();
                    case 1:
                        A05();
                        break;
                    case 2:
                        A03();
                        break;
                    default:
                        if (num != null) {
                            switch (intValue) {
                                case 1:
                                    str = "SWITCH_TO_SOURCE_SERVICE";
                                    break;
                                case 2:
                                    str = "DECODE_DATA";
                                    break;
                                default:
                                    str = "INITIALIZE";
                                    break;
                            }
                        } else {
                            str = "null";
                        }
                        throw new IllegalStateException(AnonymousClass006.A07("Unrecognized run reason: ", str));
                }
            }
            if (r3 != null) {
                r3.A26();
            }
        } catch (C08921fk e) {
            throw e;
        } catch (Throwable th) {
            if (r3 != null) {
                r3.A26();
            }
            throw th;
        }
    }

    public AnonymousClass1f1(DecodeJob.DiskCacheProvider diskCacheProvider, AnonymousClass06o<AnonymousClass1f1<?>> r3) {
        this.A0O = diskCacheProvider;
        this.A0P = r3;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public static Integer A01(AnonymousClass1f1 r2, Integer num) {
        switch (num.intValue()) {
            case 0:
                AbstractC08841fc r1 = r2.A08;
                if ((r1 instanceof C08831fb) || (r1 instanceof C08871ff) || (!(r1 instanceof C08861fe) && !(r1 instanceof C08901fi))) {
                    return AnonymousClass007.A01;
                }
                return A01(r2, AnonymousClass007.A01);
            case 1:
                AbstractC08841fc r12 = r2.A08;
                if ((r12 instanceof C08831fb) || (!(r12 instanceof C08871ff) && ((r12 instanceof C08861fe) || !(r12 instanceof C08901fi)))) {
                    return AnonymousClass007.A03;
                }
                return A01(r2, AnonymousClass007.A03);
            case 2:
                if (!r2.A0E) {
                    return AnonymousClass007.A04;
                }
                break;
            case 3:
            case 5:
                break;
            case 4:
            default:
                throw new IllegalArgumentException(AnonymousClass006.A07("Unrecognized stage: ", A02(num)));
        }
        return AnonymousClass007.A06;
    }

    private void A04() {
        boolean z;
        A06();
        AnonymousClass1Or r0 = new AnonymousClass1Or("Failed to load resource", new ArrayList(this.A0R));
        AbstractC08651f2<?> r4 = this.A09;
        synchronized (r4) {
            r4.A04 = r0;
        }
        synchronized (r4) {
            r4.A0L.A00();
            if (r4.A0N) {
                AbstractC08651f2.A00(r4);
            } else {
                List<AnonymousClass1fY> list = r4.A0G.A00;
                if (list.isEmpty()) {
                    throw new IllegalStateException("Received an exception without any callbacks to notify");
                } else if (!r4.A06) {
                    r4.A06 = true;
                    AbstractC06491aL r3 = r4.A01;
                    AnonymousClass1fI r2 = new AnonymousClass1fI(new ArrayList(list));
                    AbstractC08651f2.A01(r4, r2.A00.size() + 1);
                    r4.A0D.A02(r4, r3, null);
                    Iterator<AnonymousClass1fY> it = r2.iterator();
                    while (it.hasNext()) {
                        AnonymousClass1fY next = it.next();
                        next.A01.execute(new RunnableC08751fE(r4, next.A00));
                    }
                    r4.A02();
                } else {
                    throw new IllegalStateException("Already failed once");
                }
            }
        }
        C08881fg r22 = this.A0N;
        synchronized (r22) {
            z = true;
            r22.A01 = true;
            if (!r22.A02) {
                z = false;
            }
        }
        if (z) {
            A07(this);
        }
    }

    private void A05() {
        this.A0K = Thread.currentThread();
        this.A0F = SystemClock.elapsedRealtimeNanos();
        boolean z = false;
        while (!this.A0T && this.A0S != null && !(z = this.A0S.AAU())) {
            this.A0C = A01(this, this.A0C);
            this.A0S = A00();
            if (this.A0C == AnonymousClass007.A04) {
                A9L();
                return;
            }
        }
        if ((this.A0C == AnonymousClass007.A06 || this.A0T) && !z) {
            A04();
        }
    }

    @Override // X.AnonymousClass1fL
    public final void A6v(AbstractC06491aL r4, Exception exc, AbstractC07051bX<?> r6, AnonymousClass1fM r7) {
        r6.A26();
        AnonymousClass1Or r1 = new AnonymousClass1Or("Fetching data failed", Collections.singletonList(exc));
        Class<?> A3h = r6.A3h();
        r1.key = r4;
        r1.dataSource = r7;
        r1.dataClass = A3h;
        this.A0R.add(r1);
        if (Thread.currentThread() != this.A0K) {
            this.A0B = AnonymousClass007.A01;
            this.A09.A03(this);
            return;
        }
        A05();
    }
}
