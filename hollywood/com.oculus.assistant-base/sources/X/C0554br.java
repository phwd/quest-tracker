package X;

import java.io.Closeable;

/* renamed from: X.br  reason: case insensitive filesystem */
public final class C0554br implements Closeable {
    public final String A00;
    public final EnumC0549bm A01;
    public final int A02;
    public final long A03;
    public final long A04;
    public final C0540bd A05;
    public final C0542bf A06;
    public final C0551bo A07;
    public final C0554br A08;
    public final C0554br A09;
    public final C0554br A0A;
    public final AbstractC0555bs A0B;
    public volatile C0527bQ A0C;

    public final String A00(String str) {
        String A002 = this.A06.A00(str);
        if (A002 == null) {
            return null;
        }
        return A002;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.A0B.close();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Response{protocol=");
        sb.append(this.A01);
        sb.append(", code=");
        sb.append(this.A02);
        sb.append(", message=");
        sb.append(this.A00);
        sb.append(", url=");
        sb.append(this.A07.A03);
        sb.append('}');
        return sb.toString();
    }

    public C0554br(C0553bq bqVar) {
        this.A07 = bqVar.A07;
        this.A01 = bqVar.A06;
        this.A02 = bqVar.A00;
        this.A00 = bqVar.A03;
        this.A05 = bqVar.A04;
        this.A06 = new C0542bf(bqVar.A05);
        this.A0B = bqVar.A0B;
        this.A09 = bqVar.A09;
        this.A08 = bqVar.A08;
        this.A0A = bqVar.A0A;
        this.A04 = bqVar.A02;
        this.A03 = bqVar.A01;
    }
}
