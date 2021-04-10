package androidx.fragment.app;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class FragmentState implements Parcelable {
    public static final Parcelable.Creator CREATOR = new OS();
    public final String F;
    public final String G;
    public final boolean H;
    public final int I;

    /* renamed from: J  reason: collision with root package name */
    public final int f9472J;
    public final String K;
    public final boolean L;
    public final boolean M;
    public final boolean N;
    public final Bundle O;
    public final boolean P;
    public final int Q;
    public Bundle R;

    public FragmentState(AbstractComponentCallbacksC3550lS lSVar) {
        this.F = lSVar.getClass().getName();
        this.G = lSVar.f10345J;
        this.H = lSVar.R;
        this.I = lSVar.a0;
        this.f9472J = lSVar.b0;
        this.K = lSVar.c0;
        this.L = lSVar.f0;
        this.M = lSVar.Q;
        this.N = lSVar.e0;
        this.O = lSVar.K;
        this.P = lSVar.d0;
        this.Q = lSVar.t0.ordinal();
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentState{");
        sb.append(this.F);
        sb.append(" (");
        sb.append(this.G);
        sb.append(")}:");
        if (this.H) {
            sb.append(" fromLayout");
        }
        if (this.f9472J != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.f9472J));
        }
        String str = this.K;
        if (str != null && !str.isEmpty()) {
            sb.append(" tag=");
            sb.append(this.K);
        }
        if (this.L) {
            sb.append(" retainInstance");
        }
        if (this.M) {
            sb.append(" removing");
        }
        if (this.N) {
            sb.append(" detached");
        }
        if (this.P) {
            sb.append(" hidden");
        }
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.F);
        parcel.writeString(this.G);
        parcel.writeInt(this.H ? 1 : 0);
        parcel.writeInt(this.I);
        parcel.writeInt(this.f9472J);
        parcel.writeString(this.K);
        parcel.writeInt(this.L ? 1 : 0);
        parcel.writeInt(this.M ? 1 : 0);
        parcel.writeInt(this.N ? 1 : 0);
        parcel.writeBundle(this.O);
        parcel.writeInt(this.P ? 1 : 0);
        parcel.writeBundle(this.R);
        parcel.writeInt(this.Q);
    }

    public FragmentState(Parcel parcel) {
        this.F = parcel.readString();
        this.G = parcel.readString();
        boolean z = true;
        this.H = parcel.readInt() != 0;
        this.I = parcel.readInt();
        this.f9472J = parcel.readInt();
        this.K = parcel.readString();
        this.L = parcel.readInt() != 0;
        this.M = parcel.readInt() != 0;
        this.N = parcel.readInt() != 0;
        this.O = parcel.readBundle();
        this.P = parcel.readInt() == 0 ? false : z;
        this.R = parcel.readBundle();
        this.Q = parcel.readInt();
    }
}
