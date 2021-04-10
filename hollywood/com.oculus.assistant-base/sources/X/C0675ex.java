package X;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseIntArray;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zace;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Executor;

/* renamed from: X.ex  reason: case insensitive filesystem */
public final class C0675ex implements AbstractC1086sA, AbstractC1087sB {
    public ConnectionResult A00 = null;
    public boolean A01;
    public final int A02;
    public final AbstractC1084s8 A03;
    public final C0315Qm A04;
    public final RF A05;
    public final List A06 = new ArrayList();
    public final Map A07 = new HashMap();
    public final Queue A08 = new LinkedList();
    public final zace A09;
    public final Set A0A = new HashSet();
    public final /* synthetic */ C0319Qs A0B;

    public C0675ex(C0319Qs qs, C0310Qg qg) {
        this.A0B = qs;
        Handler handler = qs.A05;
        Looper looper = handler.getLooper();
        RP A002 = C0310Qg.A00(qg);
        RQ rq = new RQ(A002.A00, A002.A02, A002.A03, A002.A01);
        C1083s7 s7Var = qg.A03.A00;
        RZ.A01(s7Var);
        AbstractC1084s8 A003 = s7Var.A00(qg.A01, looper, rq, qg.A02, this, this);
        String str = qg.A07;
        if (str != null && (A003 instanceof RO)) {
            ((RO) A003).A0M = str;
        }
        this.A03 = A003;
        this.A04 = qg.A04;
        this.A05 = new RF();
        this.A02 = qg.A00;
        if (this.A03.A4r()) {
            Context context = qs.A04;
            RP A004 = C0310Qg.A00(qg);
            this.A09 = new zace(context, handler, new RQ(A004.A00, A004.A02, A004.A03, A004.A01));
            return;
        }
        this.A09 = null;
    }

    @Override // X.AbstractC0323Qx
    public final void A3y(ConnectionResult connectionResult) {
        A01(connectionResult, null);
    }

    private final void A00(ConnectionResult connectionResult) {
        Set set = this.A0A;
        Iterator it = set.iterator();
        if (it.hasNext()) {
            it.next();
            if (RY.A00(connectionResult, ConnectionResult.A04)) {
                this.A03.A2P();
            }
            throw new NullPointerException("zaa");
        }
        set.clear();
    }

    private final void A01(ConnectionResult connectionResult, Exception exc) {
        Status A002;
        E2 e2;
        C0319Qs qs = this.A0B;
        Handler handler = qs.A05;
        RZ.A00(handler);
        zace zace = this.A09;
        if (!(zace == null || (e2 = zace.A02) == null)) {
            e2.A1h();
        }
        RZ.A00(handler);
        this.A00 = null;
        qs.A07.A01.clear();
        A00(connectionResult);
        if (this.A03 instanceof C0654dz) {
            qs.A02 = true;
            handler.sendMessageDelayed(handler.obtainMessage(19), 300000);
        }
        int i = connectionResult.A00;
        if (i == 4) {
            A002 = C0319Qs.A0G;
        } else {
            Queue queue = this.A08;
            if (queue.isEmpty()) {
                this.A00 = connectionResult;
                return;
            } else if (exc != null) {
                RZ.A00(handler);
                A02(null, exc, false);
                return;
            } else {
                boolean z = qs.A0D;
                C0315Qm qm = this.A04;
                if (z) {
                    A02(C0319Qs.A00(qm, connectionResult), null, true);
                    if (!queue.isEmpty()) {
                        synchronized (C0319Qs.A0H) {
                        }
                        if (!qs.A04(connectionResult, this.A02)) {
                            if (i == 18) {
                                this.A01 = true;
                            }
                            if (this.A01) {
                                handler.sendMessageDelayed(Message.obtain(handler, 9, qm), 5000);
                                return;
                            }
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                }
                A002 = C0319Qs.A00(qm, connectionResult);
            }
        }
        A07(this, A002);
    }

    private final void A02(Status status, Exception exc, boolean z) {
        RZ.A00(this.A0B.A05);
        boolean z2 = true;
        boolean z3 = false;
        if (status == null) {
            z3 = true;
        }
        if (exc != null) {
            z2 = false;
        }
        if (z3 != z2) {
            Iterator it = this.A08.iterator();
            while (it.hasNext()) {
                R2 r2 = (R2) it.next();
                if (!z || r2.A00 == 2) {
                    if (status != null) {
                        r2.A01(status);
                    } else {
                        r2.A02(exc);
                    }
                    it.remove();
                }
            }
            return;
        }
        throw new IllegalArgumentException("Status XOR exception should be null");
    }

    public static final void A03(C0675ex exVar) {
        Handler handler = exVar.A0B.A05;
        RZ.A00(handler);
        exVar.A00 = null;
        exVar.A00(ConnectionResult.A04);
        if (exVar.A01) {
            C0315Qm qm = exVar.A04;
            handler.removeMessages(11, qm);
            handler.removeMessages(9, qm);
            exVar.A01 = false;
        }
        Iterator it = exVar.A07.values().iterator();
        if (it.hasNext()) {
            it.next();
            throw new NullPointerException("zaa");
        }
        A04(exVar);
        A05(exVar);
    }

    public static final void A04(C0675ex exVar) {
        Queue queue = exVar.A08;
        ArrayList arrayList = new ArrayList(queue);
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            R2 r2 = (R2) obj;
            if (!exVar.A03.isConnected()) {
                return;
            }
            if (exVar.A08(r2)) {
                queue.remove(r2);
            }
        }
    }

    public static final void A05(C0675ex exVar) {
        C0319Qs qs = exVar.A0B;
        Handler handler = qs.A05;
        C0315Qm qm = exVar.A04;
        handler.removeMessages(12, qm);
        handler.sendMessageDelayed(handler.obtainMessage(12, qm), qs.A00);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x007d A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:5:0x0026  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x006c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void A06(X.C0675ex r7, int r8) {
        /*
        // Method dump skipped, instructions count: 126
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0675ex.A06(X.ex, int):void");
    }

    public static final void A07(C0675ex exVar, Status status) {
        RZ.A00(exVar.A0B.A05);
        exVar.A02(status, null, false);
    }

    private final boolean A08(R2 r2) {
        boolean z;
        if (r2 instanceof AbstractC1094sK) {
            AbstractC1094sK sKVar = (AbstractC1094sK) r2;
            if (!(sKVar instanceof IR)) {
                this.A07.get(null);
            } else {
                R1 r1 = ((IR) sKVar).A01;
                Feature[] featureArr = r1.A01;
                if (!(featureArr == null || (r14 = featureArr.length) == 0)) {
                    AbstractC1084s8 s8Var = this.A03;
                    Feature[] A2H = s8Var.A2H();
                    if (A2H == null) {
                        A2H = new Feature[0];
                    }
                    int length = A2H.length;
                    C0626dQ dQVar = new C0626dQ(length);
                    for (Feature feature : A2H) {
                        dQVar.put(feature.A00, Long.valueOf(feature.A00()));
                    }
                    for (Feature feature2 : featureArr) {
                        Number number = (Number) dQVar.get(feature2.A00);
                        if (number == null || number.longValue() < feature2.A00()) {
                            String name = s8Var.getClass().getName();
                            String str = feature2.A00;
                            long A002 = feature2.A00();
                            StringBuilder sb = new StringBuilder(name.length() + 77 + String.valueOf(str).length());
                            sb.append(name);
                            sb.append(" could not execute call because it requires feature (");
                            sb.append(str);
                            sb.append(", ");
                            sb.append(A002);
                            sb.append(").");
                            Log.w("GoogleApiManager", sb.toString());
                            C0319Qs qs = this.A0B;
                            if (!qs.A0D || !r1.A00) {
                                sKVar.A02(new C0314Ql(feature2));
                                return true;
                            }
                            C0318Qr qr = new C0318Qr(this.A04, feature2);
                            List list = this.A06;
                            int indexOf = list.indexOf(qr);
                            if (indexOf >= 0) {
                                Object obj = list.get(indexOf);
                                Handler handler = qs.A05;
                                handler.removeMessages(15, obj);
                                handler.sendMessageDelayed(Message.obtain(handler, 15, obj), 5000);
                                return false;
                            }
                            list.add(qr);
                            Handler handler2 = qs.A05;
                            handler2.sendMessageDelayed(Message.obtain(handler2, 15, qr), 5000);
                            handler2.sendMessageDelayed(Message.obtain(handler2, 16, qr), 120000);
                            ConnectionResult connectionResult = new ConnectionResult(2, null);
                            synchronized (C0319Qs.A0H) {
                            }
                            qs.A04(connectionResult, this.A02);
                            return false;
                        }
                    }
                }
            }
        }
        RF rf = this.A05;
        AbstractC1084s8 s8Var2 = this.A03;
        boolean A4r = s8Var2.A4r();
        if (!(r2 instanceof IR)) {
            try {
                IS is = (IS) r2;
                try {
                    this.A07.remove(null);
                    C1107su suVar = is.A00.A00;
                    synchronized (suVar.A04) {
                        if (suVar.A02) {
                            return true;
                        }
                        suVar.A02 = true;
                        suVar.A01 = false;
                        suVar.A03.A00(suVar);
                        return true;
                    }
                } catch (DeadObjectException e) {
                    is.A01(R2.A00(e));
                    throw e;
                } catch (RemoteException e2) {
                    is.A01(R2.A00(e2));
                    return true;
                } catch (RuntimeException e3) {
                    is.A02(e3);
                    return true;
                }
            } catch (DeadObjectException unused) {
                A3z(1);
                s8Var2.A1i("DeadObjectException thrown while running ApiCallRunner.");
                return true;
            } catch (Throwable th) {
                throw new IllegalStateException(String.format("Error in GoogleApi implementation for client %s.", s8Var2.getClass().getName()), th);
            }
        } else {
            Sl sl = ((IR) r2).A02;
            rf.A01.put(sl, Boolean.valueOf(A4r));
            C1107su suVar2 = sl.A00;
            C1095sL sLVar = new C1095sL(rf, sl);
            if (!(suVar2 instanceof C1107su)) {
                throw new UnsupportedOperationException("addOnCompleteListener is not implemented");
            }
            Executor executor = Sn.A01;
            C0348Sp sp = suVar2.A03;
            C1106st stVar = new C1106st(executor, sLVar);
            synchronized (sp.A02) {
                Queue queue = sp.A00;
                if (queue == null) {
                    queue = new ArrayDeque();
                    sp.A00 = queue;
                }
                queue.add(stVar);
            }
            synchronized (suVar2.A04) {
                z = suVar2.A02;
            }
            if (z) {
                sp.A00(suVar2);
            }
            IR ir = (IR) r2;
            try {
                ((C1093sJ) ir.A01).A00.A00.A17(s8Var2, ir.A02);
                return true;
            } catch (DeadObjectException e4) {
                throw e4;
            } catch (RemoteException e5) {
                ir.A01(R2.A00(e5));
                return true;
            } catch (RuntimeException e6) {
                ir.A02(e6);
                return true;
            }
        }
    }

    public final void A09() {
        RZ.A00(this.A0B.A05);
        Status status = C0319Qs.A0F;
        A07(this, status);
        RF.A00(this.A05, false, status);
        for (C0322Qw qw : (C0322Qw[]) this.A07.keySet().toArray(new C0322Qw[0])) {
            A0B(new C0664eD(qw, new Sl()));
        }
        A00(new ConnectionResult(4));
        AbstractC1084s8 s8Var = this.A03;
        if (s8Var.isConnected()) {
            s8Var.A4P(new C1092sI(this));
        }
    }

    public final void A0A() {
        C0319Qs qs = this.A0B;
        RZ.A00(qs.A05);
        AbstractC1084s8 s8Var = this.A03;
        if (!s8Var.isConnected() && !s8Var.A3Q()) {
            try {
                C0332Rm rm = qs.A07;
                Context context = qs.A04;
                RZ.A01(context);
                if (s8Var.A4q()) {
                    int A2g = s8Var.A2g();
                    SparseIntArray sparseIntArray = rm.A01;
                    int i = sparseIntArray.get(A2g, -1);
                    if (i == -1) {
                        int i2 = 0;
                        while (true) {
                            if (i2 >= sparseIntArray.size()) {
                                i = rm.A00.A01(context, A2g);
                                break;
                            }
                            int keyAt = sparseIntArray.keyAt(i2);
                            if (keyAt > A2g && sparseIntArray.get(keyAt) == 0) {
                                i = 0;
                                break;
                            }
                            i2++;
                        }
                        sparseIntArray.put(A2g, i);
                    }
                    if (i != 0) {
                        ConnectionResult connectionResult = new ConnectionResult(i, null);
                        String name = s8Var.getClass().getName();
                        String valueOf = String.valueOf(connectionResult);
                        StringBuilder sb = new StringBuilder(name.length() + 35 + valueOf.length());
                        sb.append("The service for ");
                        sb.append(name);
                        sb.append(" is not available: ");
                        sb.append(valueOf);
                        Log.w("GoogleApiManager", sb.toString());
                        A3y(connectionResult);
                        return;
                    }
                }
                C1090sG sGVar = new C1090sG(qs, s8Var, this.A04);
                if (s8Var.A4r()) {
                    zace zace = this.A09;
                    RZ.A01(zace);
                    E2 e2 = zace.A02;
                    if (e2 != null) {
                        e2.A1h();
                    }
                    zace.A01.A00 = Integer.valueOf(System.identityHashCode(zace));
                    C1083s7 s7Var = zace.A06;
                    Context context2 = zace.A04;
                    Handler handler = zace.A05;
                    Looper looper = handler.getLooper();
                    RQ rq = zace.A01;
                    zace.A02 = (E2) s7Var.A00(context2, looper, rq, rq.A01, zace, zace);
                    zace.A00 = sGVar;
                    Set set = zace.A03;
                    if (set == null || set.isEmpty()) {
                        handler.post(new RC(zace));
                    } else {
                        zace.A02.A6D();
                    }
                }
                try {
                    s8Var.A1T(sGVar);
                } catch (SecurityException e) {
                    A01(new ConnectionResult(10), e);
                }
            } catch (IllegalStateException e3) {
                A01(new ConnectionResult(10), e3);
            }
        }
    }

    public final void A0B(R2 r2) {
        RZ.A00(this.A0B.A05);
        if (!this.A03.isConnected()) {
            this.A08.add(r2);
            ConnectionResult connectionResult = this.A00;
            if (connectionResult == null || connectionResult.A00 == 0 || connectionResult.A01 == null) {
                A0A();
            } else {
                A3y(connectionResult);
            }
        } else if (A08(r2)) {
            A05(this);
        } else {
            this.A08.add(r2);
        }
    }

    @Override // X.Qq
    public final void A3x(Bundle bundle) {
        Looper myLooper = Looper.myLooper();
        Handler handler = this.A0B.A05;
        if (myLooper == handler.getLooper()) {
            A03(this);
        } else {
            handler.post(new R4(this));
        }
    }

    @Override // X.Qq
    public final void A3z(int i) {
        Looper myLooper = Looper.myLooper();
        Handler handler = this.A0B.A05;
        if (myLooper == handler.getLooper()) {
            A06(this, i);
        } else {
            handler.post(new R3(this, i));
        }
    }
}
