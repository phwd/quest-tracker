package defpackage;

import android.os.Parcel;
import android.util.SparseIntArray;

/* renamed from: Ms1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Ms1 extends Ls1 {
    public final SparseIntArray d;
    public final Parcel e;
    public final int f;
    public final int g;
    public final String h;
    public int i;
    public int j;
    public int k;

    public Ms1(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), "", new C4931ta(), new C4931ta(), new C4931ta());
    }

    @Override // defpackage.Ls1
    public void a() {
        int i2 = this.i;
        if (i2 >= 0) {
            int i3 = this.d.get(i2);
            int dataPosition = this.e.dataPosition();
            this.e.setDataPosition(i3);
            this.e.writeInt(dataPosition - i3);
            this.e.setDataPosition(dataPosition);
        }
    }

    @Override // defpackage.Ls1
    public Ls1 b() {
        Parcel parcel = this.e;
        int dataPosition = parcel.dataPosition();
        int i2 = this.j;
        if (i2 == this.f) {
            i2 = this.g;
        }
        return new Ms1(parcel, dataPosition, i2, AbstractC2531fV.h(new StringBuilder(), this.h, "  "), this.f8442a, this.b, this.c);
    }

    @Override // defpackage.Ls1
    public boolean h(int i2) {
        while (this.j < this.g) {
            int i3 = this.k;
            if (i3 == i2) {
                return true;
            }
            if (String.valueOf(i3).compareTo(String.valueOf(i2)) > 0) {
                return false;
            }
            this.e.setDataPosition(this.j);
            int readInt = this.e.readInt();
            this.k = this.e.readInt();
            this.j += readInt;
        }
        return this.k == i2;
    }

    @Override // defpackage.Ls1
    public void l(int i2) {
        a();
        this.i = i2;
        this.d.put(i2, this.e.dataPosition());
        this.e.writeInt(0);
        this.e.writeInt(i2);
    }

    public Ms1(Parcel parcel, int i2, int i3, String str, C4931ta taVar, C4931ta taVar2, C4931ta taVar3) {
        super(taVar, taVar2, taVar3);
        this.d = new SparseIntArray();
        this.i = -1;
        this.j = 0;
        this.k = -1;
        this.e = parcel;
        this.f = i2;
        this.g = i3;
        this.j = i2;
        this.h = str;
    }
}
