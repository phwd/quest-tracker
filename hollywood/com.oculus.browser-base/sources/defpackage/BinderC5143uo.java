package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;

/* renamed from: uo  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BinderC5143uo extends Binder implements AbstractC2198dZ {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ int f11436a = 0;
    public final /* synthetic */ C5653xo b;

    public BinderC5143uo(C5653xo xoVar) {
        this.b = xoVar;
        attachInterface(this, "org.chromium.base.process_launcher.IParentProcess");
    }

    @Override // defpackage.AbstractC2198dZ
    public void N(int i) {
        this.b.e.post(new RunnableC4803so(this, i));
    }

    @Override // defpackage.AbstractC2198dZ
    public void a() {
        C5653xo xoVar;
        Object obj = C5653xo.f11634a;
        synchronized (C5653xo.f11634a) {
            xoVar = this.b;
            xoVar.K = true;
        }
        xoVar.e.post(new RunnableC4973to(this));
    }

    public IBinder asBinder() {
        return this;
    }

    @Override // defpackage.AbstractC2198dZ
    public void j(int i, long j) {
        if (C5653xo.c.getAndSet(i) != i) {
            AbstractC3364kK0.j("Android.ChildProcessStartTimeV2.Zygote", j);
        }
    }

    @Override // defpackage.AbstractC2198dZ
    public void j0(String str) {
        C5653xo xoVar;
        Object obj = C5653xo.f11634a;
        synchronized (C5653xo.f11634a) {
            xoVar = this.b;
            xoVar.f11635J = str;
        }
        xoVar.e.post(new RunnableC4973to(this));
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1) {
            parcel.enforceInterface("org.chromium.base.process_launcher.IParentProcess");
            N(parcel.readInt());
            return true;
        } else if (i == 2) {
            parcel.enforceInterface("org.chromium.base.process_launcher.IParentProcess");
            j0(parcel.readString());
            parcel2.writeNoException();
            return true;
        } else if (i == 3) {
            parcel.enforceInterface("org.chromium.base.process_launcher.IParentProcess");
            a();
            parcel2.writeNoException();
            return true;
        } else if (i == 4) {
            parcel.enforceInterface("org.chromium.base.process_launcher.IParentProcess");
            j(parcel.readInt(), parcel.readLong());
            return true;
        } else if (i != 1598968902) {
            return super.onTransact(i, parcel, parcel2, i2);
        } else {
            parcel2.writeString("org.chromium.base.process_launcher.IParentProcess");
            return true;
        }
    }
}
