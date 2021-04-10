package X;

import android.annotation.TargetApi;
import android.os.Handler;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import com.facebook.cameracore.muxing.MuxerWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;

@RequiresApi(18)
@TargetApi(18)
/* renamed from: X.1xt  reason: invalid class name and case insensitive filesystem */
public final class C11211xt {
    public Handler A00;
    public AnonymousClass1y5 A01 = AnonymousClass1y5.STOPPED;
    public AbstractC11091xe A02;
    public AnonymousClass1xu A03;
    public Map<AnonymousClass1yA, AbstractC11261xz> A04 = new HashMap();
    public final Handler A05;
    @Nullable
    public volatile AnonymousClass1xh A06;

    /* JADX WARN: Incorrect args count in method signature: (Ljava/util/List<LX/1yR;>;LX/1xk;)V */
    public static void A00(C11211xt r5, List list, AbstractC11131xk r7) {
        AnonymousClass1y5 r2 = r5.A01;
        AnonymousClass1y5 r1 = AnonymousClass1y5.STOPPED;
        if (r2 == r1 || r2 == AnonymousClass1y5.PREPARED) {
            if (r2 == r1) {
                for (AbstractC11261xz r0 : r5.A04.values()) {
                    r0.release();
                }
            }
            r5.A01 = AnonymousClass1y5.PREPARE_STARTED;
            AnonymousClass1y7 r4 = new AnonymousClass1y7(new C11231xw(r5, list, new AnonymousClass1yD(r5, r7), r7), r5.A00);
            Iterator it = list.iterator();
            while (it.hasNext()) {
                AnonymousClass1yR r22 = (AnonymousClass1yR) it.next();
                AbstractC11261xz r12 = r5.A04.get(r22.A4Z());
                if (r12 != null) {
                    r12.A8r(r5.A03);
                    r12.A7T(r22, r4.A00(new AnonymousClass1yJ(r5, r12)));
                }
            }
            r4.A01();
            return;
        }
        r5.A04(new AnonymousClass1y6(r5, r7));
    }

    public final String A01() {
        StringBuilder sb = new StringBuilder();
        for (AnonymousClass1yA r0 : this.A04.keySet()) {
            sb.append(r0);
            sb.append(",");
        }
        return sb.toString();
    }

    public final Map<String, String> A02() {
        HashMap hashMap = new HashMap();
        for (AbstractC11261xz r0 : this.A04.values()) {
            Map<String, String> A3H = r0.A3H();
            if (A3H != null) {
                hashMap.putAll(A3H);
            }
        }
        return hashMap;
    }

    public final void A03(AnonymousClass1lF r11) {
        AnonymousClass1xh r2 = this.A06;
        if (r2 != null) {
            this.A06 = null;
            r11.A01(A02());
            this.A05.post(new RunnableC11121xi(this, r11, r2));
            return;
        }
        this.A02.A5Q("recording_controller_error", "RecordingThreadController", (long) hashCode(), A01(), r11, "notifyOnDifferentThreadCaptureFailed", null);
    }

    @VisibleForTesting
    public final void A04(@Nullable AbstractC11151xn r2) {
        A05(new AnonymousClass1y0(this, r2));
    }

    public final void A05(AbstractC11151xn r5) {
        AnonymousClass1y5 r3;
        AnonymousClass1y1 r0;
        AnonymousClass1y5 r1 = this.A01;
        if (r1 == AnonymousClass1y5.STOPPED || r1 == (r3 = AnonymousClass1y5.STOP_STARTED)) {
            r5.A6B();
        } else if (r1 == AnonymousClass1y5.PREPARED) {
            this.A01 = r3;
            A04(new AnonymousClass1yK(this, r5));
        } else {
            this.A02.A5O(19, "recording_stop_requested");
            this.A01 = r3;
            AnonymousClass1y2 r2 = new AnonymousClass1y2(new C11181xq(this, r5), this.A00);
            for (AbstractC11261xz r12 : this.A04.values()) {
                if (r12.A4v()) {
                    synchronized (r2) {
                        if (!r2.A01) {
                            r2.A00++;
                            r0 = new AnonymousClass1y1(r2);
                        } else {
                            throw new IllegalStateException("Cannot generate callbacks after complete is called");
                        }
                    }
                    r12.A9O(r0);
                }
            }
            synchronized (r2) {
                r2.A01 = true;
                if (r2.A06.get() == r2.A00) {
                    AnonymousClass1z7.A00(r2.A04, r2.A03);
                }
            }
        }
    }

    public final void A06(AbstractC11151xn r11, C11191xr r12, AnonymousClass1xh r13) {
        AnonymousClass1yB r6;
        this.A02.A5O(19, "recording_start_requested");
        AnonymousClass1y5 r1 = this.A01;
        if (r1 == AnonymousClass1y5.RECORDING) {
            A03(new C11081xd("Recording video has already started"));
            r11.A6B();
        } else if (r1 != AnonymousClass1y5.PREPARED) {
            A04(new AnonymousClass1y9(this, r1, r11));
        } else {
            this.A01 = AnonymousClass1y5.RECORDING_STARTED;
            this.A06 = r13;
            AnonymousClass1xu r3 = this.A03;
            r3.A04 = new AnonymousClass1yI(this, r11);
            r3.A03 = r12;
            ArrayList arrayList = new ArrayList();
            String absolutePath = r12.A01.getAbsolutePath();
            AnonymousClass1yQ r7 = r3.A05.get(AnonymousClass1yA.AUDIO);
            AnonymousClass1yQ r8 = r3.A05.get(AnonymousClass1yA.VIDEO);
            AbstractC11091xe r9 = r3.A07;
            AnonymousClass1X9 r14 = r3.A02;
            if (r14 != null) {
                if (r14.A06) {
                    r6 = new AnonymousClass1Xw(r14);
                } else {
                    r6 = new AnonymousClass1Xv(r14);
                }
            } else if (r7 != null) {
                r6 = new AnonymousClass1Wc();
            } else {
                r6 = new AnonymousClass1WO();
            }
            arrayList.add(new AnonymousClass1y3(absolutePath, r6, r7, r8, r9));
            C11221xv r4 = new C11221xv(arrayList);
            r3.A01 = r4;
            r4.A00 = r3.A00;
            Iterator<MuxerWrapper> it = r4.A06.iterator();
            while (it.hasNext()) {
                it.next();
            }
            r3.A0C = false;
            r3.A0D = false;
            r3.A0B = false;
            r3.A0A = 0;
            r3.A0F = false;
            int i = 0;
            for (AbstractC11261xz r0 : this.A04.values()) {
                if (r0.A4v()) {
                    i++;
                }
            }
            AtomicInteger atomicInteger = new AtomicInteger(i);
            for (AbstractC11261xz r2 : this.A04.values()) {
                if (r2.A4v()) {
                    r2.A9D(new C11201xs(this, r2, r11), new C11241xx(this, r2, atomicInteger, r11));
                }
            }
        }
    }

    public C11211xt(Handler handler, AbstractC11091xe r3) {
        this.A05 = handler;
        this.A02 = r3;
    }
}
