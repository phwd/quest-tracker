package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.ArrayList;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0bl  reason: invalid class name and case insensitive filesystem */
public final class C03180bl extends AbstractC01860Mq {
    public final ArrayList<Object> A00;

    public static void A00(@Nullable C03180bl r1, Object obj) {
        if (r1.A02) {
            r1.A00.add(obj);
            return;
        }
        throw new IllegalStateException("Expected object to be mutable");
    }

    public final void A04(AbstractC01860Mq r3) {
        if (this.A02) {
            r3.A02();
            A00(this, r3);
            r3.A02();
            r3.A00 = this;
            return;
        }
        throw new IllegalStateException("Expected object to be mutable");
    }

    public C03180bl(int i) {
        this.A00 = new ArrayList<>(i);
    }
}
