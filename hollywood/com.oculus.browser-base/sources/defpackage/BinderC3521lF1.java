package defpackage;

import android.os.Handler;
import android.os.Parcel;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.cast.zzcj;
import com.google.android.gms.internal.cast.zzdb;
import com.oculus.os.Version;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: lF1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class BinderC3521lF1 extends AbstractBinderC2487fC1 {

    /* renamed from: a  reason: collision with root package name */
    public final AtomicReference f10336a;
    public final Handler b;

    public BinderC3521lF1(C3350kF1 kf1) {
        super("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
        this.f10336a = new AtomicReference(kf1);
        this.b = new HandlerC2841hG1(kf1.i);
    }

    @Override // defpackage.AbstractBinderC2487fC1
    public final boolean c(int i, Parcel parcel, Parcel parcel2, int i2) {
        C3350kF1 kf1 = null;
        switch (i) {
            case 1:
                int readInt = parcel.readInt();
                C3350kF1 kf12 = (C3350kF1) this.f10336a.getAndSet(null);
                if (kf12 != null) {
                    kf12.G();
                    kf1 = kf12;
                }
                if (kf1 == null) {
                    return true;
                }
                NF1 nf1 = C3350kF1.D;
                Object[] objArr = {Integer.valueOf(readInt)};
                if (nf1.d()) {
                    nf1.c("ICastDeviceControllerListener.onDisconnected: %d", objArr);
                }
                if (readInt == 0) {
                    return true;
                }
                Handler handler = kf1.k;
                handler.sendMessage(handler.obtainMessage(6, kf1.A.get(), 2));
                return true;
            case 2:
                ApplicationMetadata applicationMetadata = (ApplicationMetadata) AbstractC4376qF1.a(parcel, ApplicationMetadata.CREATOR);
                String readString = parcel.readString();
                String readString2 = parcel.readString();
                boolean z = parcel.readInt() != 0;
                C3350kF1 kf13 = (C3350kF1) this.f10336a.get();
                if (kf13 == null) {
                    return true;
                }
                kf13.G = applicationMetadata;
                kf13.X = applicationMetadata.F;
                kf13.Y = readString2;
                kf13.N = readString;
                synchronized (C3350kF1.E) {
                    AbstractC4609rg rgVar = kf13.b0;
                    if (rgVar != null) {
                        ((AbstractC4439qg) rgVar).f(new C3692mF1(new Status(0), applicationMetadata, readString, readString2, z));
                        kf13.b0 = null;
                    }
                }
                return true;
            case 3:
                int readInt2 = parcel.readInt();
                C3350kF1 kf14 = (C3350kF1) this.f10336a.get();
                if (kf14 == null) {
                    return true;
                }
                kf14.K(readInt2);
                return true;
            case 4:
                parcel.readString();
                parcel.readDouble();
                int i3 = AbstractC4376qF1.f11128a;
                parcel.readInt();
                NF1 nf12 = C3350kF1.D;
                Object[] objArr2 = new Object[0];
                if (!nf12.d()) {
                    return true;
                }
                nf12.c("Deprecated callback: \"onStatusreceived\"", objArr2);
                return true;
            case 5:
                String readString3 = parcel.readString();
                String readString4 = parcel.readString();
                C3350kF1 kf15 = (C3350kF1) this.f10336a.get();
                if (kf15 == null) {
                    return true;
                }
                NF1 nf13 = C3350kF1.D;
                Object[] objArr3 = {readString3, readString4};
                if (nf13.d()) {
                    nf13.c("Receive (type=text, ns=%s) %s", objArr3);
                }
                this.b.post(new RunnableC4205pF1(kf15, readString3, readString4));
                return true;
            case 6:
                String readString5 = parcel.readString();
                byte[] createByteArray = parcel.createByteArray();
                if (((C3350kF1) this.f10336a.get()) == null) {
                    return true;
                }
                NF1 nf14 = C3350kF1.D;
                Object[] objArr4 = {readString5, Integer.valueOf(createByteArray.length)};
                if (!nf14.d()) {
                    return true;
                }
                nf14.c("IGNORING: Receive (type=binary, ns=%s) <%d bytes>", objArr4);
                return true;
            case Version.VERSION_7:
                int readInt3 = parcel.readInt();
                C3350kF1 kf16 = (C3350kF1) this.f10336a.get();
                if (kf16 == null) {
                    return true;
                }
                kf16.L(readInt3);
                return true;
            case Version.VERSION_8:
                int readInt4 = parcel.readInt();
                C3350kF1 kf17 = (C3350kF1) this.f10336a.get();
                if (kf17 == null) {
                    return true;
                }
                kf17.L(readInt4);
                return true;
            case Version.VERSION_9:
                int readInt5 = parcel.readInt();
                C3350kF1 kf18 = (C3350kF1) this.f10336a.get();
                if (kf18 == null) {
                    return true;
                }
                kf18.X = null;
                kf18.Y = null;
                kf18.L(readInt5);
                if (kf18.I == null) {
                    return true;
                }
                this.b.post(new RunnableC4034oF1(kf18, readInt5));
                return true;
            case Version.VERSION_10:
                parcel.readString();
                long readLong = parcel.readLong();
                int readInt6 = parcel.readInt();
                C3350kF1 kf19 = (C3350kF1) this.f10336a.get();
                if (kf19 == null) {
                    return true;
                }
                kf19.F(readLong, readInt6);
                return true;
            case Version.VERSION_11:
                parcel.readString();
                long readLong2 = parcel.readLong();
                C3350kF1 kf110 = (C3350kF1) this.f10336a.get();
                if (kf110 == null) {
                    return true;
                }
                kf110.F(readLong2, 0);
                return true;
            case Version.VERSION_12:
                zzcj zzcj = (zzcj) AbstractC4376qF1.a(parcel, zzcj.CREATOR);
                C3350kF1 kf111 = (C3350kF1) this.f10336a.get();
                if (kf111 == null) {
                    return true;
                }
                NF1 nf15 = C3350kF1.D;
                Object[] objArr5 = new Object[0];
                if (nf15.d()) {
                    nf15.c("onApplicationStatusChanged", objArr5);
                }
                this.b.post(new FF1(kf111, zzcj));
                return true;
            case Version.VERSION_13:
                zzdb zzdb = (zzdb) AbstractC4376qF1.a(parcel, zzdb.CREATOR);
                C3350kF1 kf112 = (C3350kF1) this.f10336a.get();
                if (kf112 == null) {
                    return true;
                }
                NF1 nf16 = C3350kF1.D;
                Object[] objArr6 = new Object[0];
                if (nf16.d()) {
                    nf16.c("onDeviceStatusChanged", objArr6);
                }
                this.b.post(new RunnableC3863nF1(kf112, zzdb));
                return true;
            default:
                return false;
        }
    }
}
