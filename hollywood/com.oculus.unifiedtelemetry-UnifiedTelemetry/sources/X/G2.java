package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import java.io.Writer;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class G2 {
    public boolean A00;
    public boolean A01;
    public boolean A02;
    public final Writer A03;

    public static void A01(G2 g2) {
        if (g2.A00) {
            throw new IllegalStateException("Cannot perform action because we have ended the batch");
        }
    }

    public G2(Writer writer) {
        this.A03 = writer;
    }

    public static void A00(G2 g2) throws IOException {
        Writer writer;
        int i;
        A01(g2);
        if (!g2.A01) {
            g2.A01 = true;
            writer = g2.A03;
            i = 123;
        } else {
            writer = g2.A03;
            i = 44;
        }
        writer.write(i);
    }
}
