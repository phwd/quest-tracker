package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0NV  reason: invalid class name */
public final class AnonymousClass0NV {
    public int A00 = -1;
    public long[] A01 = new long[20];

    public final String toString() {
        StringBuilder sb = new StringBuilder("<LongStack vector:[");
        int i = 0;
        while (true) {
            long[] jArr = this.A01;
            if (i < jArr.length) {
                if (i != 0) {
                    sb.append(" ");
                }
                int i2 = this.A00;
                if (i == i2) {
                    sb.append(">>");
                }
                sb.append(jArr[i]);
                if (i == i2) {
                    sb.append("<<");
                }
                i++;
            } else {
                sb.append("]>");
                return sb.toString();
            }
        }
    }
}
