package X;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

public abstract class GF {
    public int A00;
    public final AbstractC0081Fy A01;
    public final G2 A02;
    public final Writer A03;
    public final /* synthetic */ GG A04;

    public GF(GG gg, OutputStream outputStream, AbstractC0081Fy fy) throws IOException {
        this.A04 = gg;
        this.A01 = fy;
        Gu gu = new Gu(new Gw(outputStream, gg.A06), gg.A08);
        this.A03 = gu;
        this.A02 = new G2(gu);
    }
}
