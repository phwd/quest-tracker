package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.Writer;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.Gr  reason: case insensitive filesystem */
public final class C0086Gr {
    public int A00 = 1;
    public final Writer A01;

    public C0086Gr(Writer writer) {
        this.A01 = writer;
    }
}
