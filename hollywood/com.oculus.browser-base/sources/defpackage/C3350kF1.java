package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.zzae;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.BinderWrapper;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: kF1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3350kF1 extends KV {
    public static final NF1 D = new NF1("CastClientImpl");
    public static final Object E = new Object();
    public static final Object F = new Object();
    public ApplicationMetadata G;
    public final CastDevice H;
    public final AbstractC1252Um I;

    /* renamed from: J  reason: collision with root package name */
    public final Map f10268J = new HashMap();
    public final long K;
    public final Bundle L;
    public BinderC3521lF1 M;
    public String N;
    public boolean O;
    public boolean P;
    public boolean Q;
    public boolean R;
    public double S;
    public zzae T;
    public int U;
    public int V;
    public final AtomicLong W = new AtomicLong(0);
    public String X;
    public String Y;
    public Bundle Z;
    public final Map a0 = new HashMap();
    public AbstractC4609rg b0;
    public AbstractC4609rg c0;

    public C3350kF1(Context context, Looper looper, C3800mv mvVar, CastDevice castDevice, long j, AbstractC1252Um um, Bundle bundle, WV wv, XV xv) {
        super(context, looper, 10, mvVar, wv, xv);
        this.H = castDevice;
        this.I = um;
        this.K = j;
        this.L = bundle;
        G();
        J();
    }

    public final void A(String str, AbstractC1313Vm vm) {
        GF1.d(str);
        z(str);
        if (vm != null) {
            synchronized (this.f10268J) {
                this.f10268J.put(str, vm);
            }
            JF1 jf1 = (JF1) l();
            if (I()) {
                Parcel c = jf1.c();
                c.writeString(str);
                jf1.e0(11, c);
            }
        }
    }

    public final void B(double d) {
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            StringBuilder sb = new StringBuilder(41);
            sb.append("Volume cannot be ");
            sb.append(d);
            throw new IllegalArgumentException(sb.toString());
        }
        JF1 jf1 = (JF1) l();
        if (I()) {
            double d2 = this.S;
            boolean z = this.O;
            Parcel c = jf1.c();
            c.writeDouble(d);
            c.writeDouble(d2);
            int i = AbstractC4376qF1.f11128a;
            c.writeInt(z ? 1 : 0);
            jf1.e0(7, c);
        }
    }

    public final void C(AbstractC4609rg rgVar) {
        synchronized (E) {
            AbstractC4609rg rgVar2 = this.b0;
            if (rgVar2 != null) {
                ((AbstractC4439qg) rgVar2).f(new C3692mF1(new Status(2002)));
            }
            this.b0 = rgVar;
        }
    }

    public final void D(String str, AbstractC4609rg rgVar) {
        synchronized (F) {
            if (this.c0 != null) {
                ((AbstractC4439qg) rgVar).f(new Status(2001));
            } else {
                this.c0 = rgVar;
            }
        }
        JF1 jf1 = (JF1) l();
        if (I()) {
            Parcel c = jf1.c();
            c.writeString(str);
            jf1.e0(5, c);
            return;
        }
        L(2016);
    }

    public final void E(String str, String str2, AbstractC4609rg rgVar) {
        if (TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("The message payload cannot be null or empty");
        } else if (str2.length() <= 524288) {
            GF1.d(str);
            long incrementAndGet = this.W.incrementAndGet();
            try {
                this.a0.put(Long.valueOf(incrementAndGet), rgVar);
                JF1 jf1 = (JF1) l();
                if (I()) {
                    Parcel c = jf1.c();
                    c.writeString(str);
                    c.writeString(str2);
                    c.writeLong(incrementAndGet);
                    jf1.e0(9, c);
                    return;
                }
                F(incrementAndGet, 2016);
            } catch (Throwable th) {
                this.a0.remove(Long.valueOf(incrementAndGet));
                throw th;
            }
        } else {
            NF1 nf1 = D;
            Log.w(nf1.f8536a, nf1.c("Message send failed. Message exceeds maximum size", new Object[0]));
            throw new IllegalArgumentException("Message exceeds maximum size");
        }
    }

    public final void F(long j, int i) {
        AbstractC4609rg rgVar;
        synchronized (this.a0) {
            rgVar = (AbstractC4609rg) this.a0.remove(Long.valueOf(j));
        }
        if (rgVar != null) {
            ((AbstractC4439qg) rgVar).f(new Status(1, i, null, null));
        }
    }

    public final void G() {
        this.R = false;
        this.U = -1;
        this.V = -1;
        this.G = null;
        this.N = null;
        this.S = 0.0d;
        J();
        this.O = false;
        this.T = null;
    }

    public final void H() {
        NF1 nf1 = D;
        Object[] objArr = new Object[0];
        if (nf1.d()) {
            nf1.c("removing all MessageReceivedCallbacks", objArr);
        }
        synchronized (this.f10268J) {
            this.f10268J.clear();
        }
    }

    public final boolean I() {
        BinderC3521lF1 lf1;
        if (this.R && (lf1 = this.M) != null) {
            if (!(lf1.f10336a.get() == null)) {
                return true;
            }
        }
        return false;
    }

    public final double J() {
        if (this.H.A(2048)) {
            return 0.02d;
        }
        if (!this.H.A(4) || this.H.A(1) || "Chromecast Audio".equals(this.H.f9641J)) {
            return 0.05d;
        }
        return 0.02d;
    }

    public final void K(int i) {
        synchronized (E) {
            AbstractC4609rg rgVar = this.b0;
            if (rgVar != null) {
                ((AbstractC4439qg) rgVar).f(new C3692mF1(new Status(1, i, null, null)));
                this.b0 = null;
            }
        }
    }

    public final void L(int i) {
        synchronized (F) {
            AbstractC4609rg rgVar = this.c0;
            if (rgVar != null) {
                Status status = new Status(1, i, null, null);
                AbstractC4439qg qgVar = (AbstractC4439qg) rgVar;
                Objects.requireNonNull(qgVar);
                qgVar.f(status);
                this.c0 = null;
            }
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, defpackage.LV
    public final Bundle b() {
        Bundle bundle = this.Z;
        if (bundle == null) {
            return null;
        }
        this.Z = null;
        return bundle;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, defpackage.AbstractC2129d7
    public final void disconnect() {
        NF1 nf1 = D;
        Object[] objArr = {this.M, Boolean.valueOf(a())};
        if (nf1.d()) {
            nf1.c("disconnect(); ServiceListener=%s, isConnected=%b", objArr);
        }
        BinderC3521lF1 lf1 = this.M;
        C3350kF1 kf1 = null;
        this.M = null;
        if (lf1 != null) {
            C3350kF1 kf12 = (C3350kF1) lf1.f10336a.getAndSet(null);
            if (kf12 != null) {
                kf12.G();
                kf1 = kf12;
            }
            if (kf1 != null) {
                H();
                try {
                    JF1 jf1 = (JF1) l();
                    jf1.e0(1, jf1.c());
                    return;
                } catch (RemoteException | IllegalStateException e) {
                    NF1 nf12 = D;
                    Object[] objArr2 = {e.getMessage()};
                    if (nf12.d()) {
                        nf12.c("Error while disconnecting the controller interface: %s", objArr2);
                    }
                    return;
                } finally {
                    super.disconnect();
                }
            }
        }
        Object[] objArr3 = new Object[0];
        if (nf1.d()) {
            nf1.c("already disposed, so short-circuiting", objArr3);
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final /* synthetic */ IInterface e(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.cast.internal.ICastDeviceController");
        if (queryLocalInterface instanceof JF1) {
            return (JF1) queryLocalInterface;
        }
        return new JF1(iBinder);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, defpackage.AbstractC2129d7
    public final int getMinApkVersion() {
        return 12800000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final Bundle i() {
        Bundle bundle = new Bundle();
        NF1 nf1 = D;
        Object[] objArr = {this.X, this.Y};
        if (nf1.d()) {
            nf1.c("getRemoteService(): mLastApplicationId=%s, mLastSessionId=%s", objArr);
        }
        CastDevice castDevice = this.H;
        Objects.requireNonNull(castDevice);
        bundle.putParcelable("com.google.android.gms.cast.EXTRA_CAST_DEVICE", castDevice);
        bundle.putLong("com.google.android.gms.cast.EXTRA_CAST_FLAGS", this.K);
        Bundle bundle2 = this.L;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
        BinderC3521lF1 lf1 = new BinderC3521lF1(this);
        this.M = lf1;
        Objects.requireNonNull(lf1);
        bundle.putParcelable("listener", new BinderWrapper(lf1));
        String str = this.X;
        if (str != null) {
            bundle.putString("last_application_id", str);
            String str2 = this.Y;
            if (str2 != null) {
                bundle.putString("last_session_id", str2);
            }
        }
        return bundle;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String m() {
        return "com.google.android.gms.cast.internal.ICastDeviceController";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String n() {
        return "com.google.android.gms.cast.service.BIND_CAST_DEVICE_CONTROLLER_SERVICE";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final void q(ConnectionResult connectionResult) {
        super.q(connectionResult);
        H();
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final void r(int i, IBinder iBinder, Bundle bundle, int i2) {
        NF1 nf1 = D;
        Object[] objArr = {Integer.valueOf(i)};
        if (nf1.d()) {
            nf1.c("in onPostInitHandler; statusCode=%d", objArr);
        }
        if (i == 0 || i == 1001) {
            this.R = true;
            this.P = true;
            this.Q = true;
        } else {
            this.R = false;
        }
        if (i == 1001) {
            Bundle bundle2 = new Bundle();
            this.Z = bundle2;
            bundle2.putBoolean("com.google.android.gms.cast.EXTRA_APP_NO_LONGER_RUNNING", true);
            i = 0;
        }
        super.r(i, iBinder, bundle, i2);
    }

    public final void z(String str) {
        AbstractC1313Vm vm;
        if (!TextUtils.isEmpty(str)) {
            synchronized (this.f10268J) {
                vm = (AbstractC1313Vm) this.f10268J.remove(str);
            }
            if (vm != null) {
                try {
                    JF1 jf1 = (JF1) l();
                    Parcel c = jf1.c();
                    c.writeString(str);
                    jf1.e0(12, c);
                } catch (IllegalStateException e) {
                    NF1 nf1 = D;
                    Object[] objArr = {str, e.getMessage()};
                    if (nf1.d()) {
                        nf1.c("Error unregistering namespace (%s): %s", objArr);
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("Channel namespace cannot be null or empty");
        }
    }
}
