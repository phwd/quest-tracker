package X;

/* renamed from: X.p7  reason: case insensitive filesystem */
public final class C0932p7 implements IV {
    public final /* synthetic */ YE A00;
    public final /* synthetic */ StringBuilder A01;

    public C0932p7(YE ye, StringBuilder sb) {
        this.A00 = ye;
        this.A01 = sb;
    }

    @Override // X.IV
    public final void A5N(long j, long j2, int i, String str, Ie ie, Ix ix) {
        StringBuilder sb = this.A01;
        sb.append("<p:");
        sb.append(str);
        if (ie != null) {
            sb.append('=');
            sb.append(ie);
        }
        sb.append(' ');
        sb.append(j);
        sb.append("[ms]>");
    }
}
