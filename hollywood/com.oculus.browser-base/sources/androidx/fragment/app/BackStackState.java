package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class BackStackState implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0378Ge();
    public final int[] F;
    public final ArrayList G;
    public final int[] H;
    public final int[] I;

    /* renamed from: J  reason: collision with root package name */
    public final int f9470J;
    public final String K;
    public final int L;
    public final int M;
    public final CharSequence N;
    public final int O;
    public final CharSequence P;
    public final ArrayList Q;
    public final ArrayList R;
    public final boolean S;

    public BackStackState(C0317Fe fe) {
        int size = fe.f8026a.size();
        this.F = new int[(size * 5)];
        if (fe.g) {
            this.G = new ArrayList(size);
            this.H = new int[size];
            this.I = new int[size];
            int i = 0;
            int i2 = 0;
            while (i < size) {
                C2186dT dTVar = (C2186dT) fe.f8026a.get(i);
                int i3 = i2 + 1;
                this.F[i2] = dTVar.f9783a;
                ArrayList arrayList = this.G;
                AbstractComponentCallbacksC3550lS lSVar = dTVar.b;
                arrayList.add(lSVar != null ? lSVar.f10345J : null);
                int[] iArr = this.F;
                int i4 = i3 + 1;
                iArr[i3] = dTVar.c;
                int i5 = i4 + 1;
                iArr[i4] = dTVar.d;
                int i6 = i5 + 1;
                iArr[i5] = dTVar.e;
                iArr[i6] = dTVar.f;
                this.H[i] = dTVar.g.ordinal();
                this.I[i] = dTVar.h.ordinal();
                i++;
                i2 = i6 + 1;
            }
            this.f9470J = fe.f;
            this.K = fe.i;
            this.L = fe.s;
            this.M = fe.j;
            this.N = fe.k;
            this.O = fe.l;
            this.P = fe.m;
            this.Q = fe.n;
            this.R = fe.o;
            this.S = fe.p;
            return;
        }
        throw new IllegalStateException("Not on back stack");
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(this.F);
        parcel.writeStringList(this.G);
        parcel.writeIntArray(this.H);
        parcel.writeIntArray(this.I);
        parcel.writeInt(this.f9470J);
        parcel.writeString(this.K);
        parcel.writeInt(this.L);
        parcel.writeInt(this.M);
        TextUtils.writeToParcel(this.N, parcel, 0);
        parcel.writeInt(this.O);
        TextUtils.writeToParcel(this.P, parcel, 0);
        parcel.writeStringList(this.Q);
        parcel.writeStringList(this.R);
        parcel.writeInt(this.S ? 1 : 0);
    }

    public BackStackState(Parcel parcel) {
        this.F = parcel.createIntArray();
        this.G = parcel.createStringArrayList();
        this.H = parcel.createIntArray();
        this.I = parcel.createIntArray();
        this.f9470J = parcel.readInt();
        this.K = parcel.readString();
        this.L = parcel.readInt();
        this.M = parcel.readInt();
        this.N = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.O = parcel.readInt();
        this.P = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.Q = parcel.createStringArrayList();
        this.R = parcel.createStringArrayList();
        this.S = parcel.readInt() != 0;
    }
}
