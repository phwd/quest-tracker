package X;

/* renamed from: X.p9  reason: case insensitive filesystem */
public final class C0934p9 implements IV {
    public boolean A00 = true;
    public final /* synthetic */ YE A01;
    public final /* synthetic */ StringBuilder A02;

    public C0934p9(YE ye, StringBuilder sb) {
        this.A01 = ye;
        this.A02 = sb;
    }

    @Override // X.IV
    public final void A5N(long j, long j2, int i, String str, Ie ie, Ix ix) {
        if (!this.A00) {
            this.A02.append(',');
        } else {
            this.A00 = false;
        }
        StringBuilder sb = this.A02;
        sb.append("{\"name\":\"");
        sb.append(YE.A02(str));
        sb.append('\"');
        sb.append(",\"value\":");
        sb.append(j);
        if (ie != null) {
            sb.append(",\"data\":{");
            C0933p8 p8Var = new C0933p8(this);
            int i2 = 0;
            int i3 = 0;
            while (i2 < ie.A00) {
                String[] strArr = ie.A02;
                p8Var.A5X(strArr[i3], strArr[i3 + 1], ie.A01[i2]);
                i2++;
                i3 += 2;
            }
            sb.append("}");
        }
        sb.append("}");
    }
}
