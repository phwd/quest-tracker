package X;

import com.facebook.common.util.TriState;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public final class IZ {
    public RunnableC0929p4 A00;
    public C0916or A01;
    public final Cb A02;
    public final IL A03;
    public final IU A04;
    public final ArrayList[] A05;
    public final IU A06;
    public final C0924oz A07;
    public final Ix A08;
    public final C0938pF A09;
    public final C0940pH A0A;
    public final Object A0B = new Object();
    public final IP[] A0C;
    public volatile Boolean A0D;

    public static Ix A00(IZ iz, long j) {
        IP[] ipArr = iz.A0C;
        C0937pE pEVar = null;
        if (!(ipArr == null || j == 0)) {
            C0916or orVar = iz.A01;
            C0915oq oqVar = (orVar == null || !(orVar instanceof C0649dt)) ? null : ((C0649dt) orVar).A00;
            for (IP ip : ipArr) {
                if (!((ip.A2n() & j) == 0 || oqVar == null || !ip.A3S(oqVar))) {
                    if (pEVar == null) {
                        pEVar = new C0937pE();
                    }
                    pEVar.put(Long.numberOfTrailingZeros(ip.A2n()), ip.A58());
                }
            }
        }
        return pEVar;
    }

    public static final Ix A01(IZ iz, long j) {
        IP[] ipArr = iz.A0C;
        if (ipArr == null || j == 0) {
            return null;
        }
        C0937pE pEVar = new C0937pE();
        for (IP ip : ipArr) {
            C0916or orVar = iz.A01;
            C0915oq oqVar = (orVar == null || !(orVar instanceof C0649dt)) ? null : ((C0649dt) orVar).A00;
            if (!((ip.A2n() & j) == 0 || oqVar == null || !ip.A3S(oqVar))) {
                pEVar.put(Long.numberOfTrailingZeros(ip.A2n()), ip.A56());
            }
        }
        return pEVar;
    }

    public static void A02(IZ iz, Ix ix, long j) {
        IP[] ipArr = iz.A0C;
        if (!(ipArr == null || j == 0)) {
            C0916or orVar = iz.A01;
            C0915oq oqVar = null;
            if (orVar != null && (orVar instanceof C0649dt)) {
                oqVar = ((C0649dt) orVar).A00;
            }
            for (IP ip : ipArr) {
                if (!((ip.A2n() & j) == 0 || oqVar == null || !ip.A3S(oqVar))) {
                    int numberOfTrailingZeros = Long.numberOfTrailingZeros(ip.A2n());
                    if (ix != null) {
                        ix.get(numberOfTrailingZeros);
                    }
                }
            }
        }
    }

    public IZ(C0924oz ozVar, IP[] ipArr, C0916or orVar, Cb cb, C0938pF pFVar, C0940pH pHVar) {
        this.A07 = ozVar;
        this.A0C = ipArr;
        this.A02 = cb;
        this.A04 = new IU(cb);
        this.A06 = new IU(cb);
        this.A05 = new ArrayList[16358];
        TriState.NO.asBooleanObject();
        this.A01 = orVar;
        this.A09 = pFVar;
        this.A0A = pHVar;
        this.A03 = new IL(this.A06);
        this.A08 = new C0937pE();
    }

    public final void A03(int i, int i2, int i3, long j, TimeUnit timeUnit, String str, Ie ie, int i4) {
        long j2;
        int i5;
        int i6 = i ^ (i2 * 179426549);
        IU iu = this.A04;
        iu.A00();
        try {
            IL il = this.A03;
            RunnableC0929p4 A012 = il.A01(i6);
            boolean z = false;
            long j3 = 0;
            if (A012 == null || !IL.A00(A012)) {
                j2 = 0;
                i5 = 0;
            } else {
                j2 = timeUnit.toNanos(j) - A012.A09;
                long j4 = A012.A08;
                i5 = A012.A06;
                if (j4 == 0 || i4 == 0) {
                    A012.A00(j2, TimeUnit.NANOSECONDS, i3, str, ie, null);
                    timeUnit.toMillis(j);
                    timeUnit.toNanos(j);
                } else {
                    z = true;
                }
                j3 = j4;
            }
            if (z) {
                Ix A013 = A01(this, j3);
                iu.A00();
                try {
                    RunnableC0929p4 A014 = il.A01(i6);
                    if (A014 != null && A014.A06 == i5 && IL.A00(A014)) {
                        A014.A00(j2, TimeUnit.NANOSECONDS, i3, str, ie, A013);
                        timeUnit.toMillis(j);
                        timeUnit.toNanos(j);
                    }
                } finally {
                    iu.unlock();
                }
            }
        } finally {
            iu.unlock();
        }
    }
}
