package defpackage;

/* renamed from: rD  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4538rD {

    /* renamed from: a  reason: collision with root package name */
    public int f11191a;
    public long b;
    public long c = 0;
    public final long d;
    public final long e;

    public C4538rD(long j, int i) {
        this.d = j;
        this.e = (long) i;
    }

    public void a(long j, long j2) {
        if (j % 8 != 0) {
            throw new C4200pE("Incorrect starting alignment: " + j + ".");
        } else if (j < this.b) {
            throw new C4200pE("Trying to access memory out of order.");
        } else if (j2 < j) {
            throw new C4200pE("Incorrect memory range.");
        } else if (j2 <= this.d) {
            this.b = -8 & ((j2 + 8) - 1);
        } else {
            throw new C4200pE("Trying to access out of range memory.");
        }
    }
}
