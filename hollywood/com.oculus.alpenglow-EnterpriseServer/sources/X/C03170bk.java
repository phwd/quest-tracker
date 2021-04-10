package X;

import android.text.TextUtils;
import com.facebook.infer.annotation.NullsafeStrict;
import java.util.ArrayList;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NullsafeStrict
@NotThreadSafe
/* renamed from: X.0bk  reason: invalid class name and case insensitive filesystem */
public final class C03170bk extends AbstractC01860Mq {
    public int A00;
    public final ArrayList<Object> A01;

    private void A01(String str) {
        if (!this.A02) {
            throw new IllegalStateException("Expected object to be mutable");
        } else if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException(AnonymousClass006.A05("key=", str));
        }
    }

    public final C03180bl A04(String str) {
        C01870Mr r2 = super.A01;
        C03180bl A002 = r2.A01.A00();
        if (A002 == null) {
            A002 = new C03180bl(r2.A00);
        }
        A002.A03(r2);
        A05(str, A002);
        return A002;
    }

    public C03170bk(int i) {
        this.A01 = new ArrayList<>(i << 1);
    }

    public static void A00(C03170bk r1, @Nullable String str, Object obj) {
        r1.A01(str);
        ArrayList<Object> arrayList = r1.A01;
        arrayList.add(str);
        arrayList.add(obj);
        r1.A00++;
    }

    public final void A05(String str, AbstractC01860Mq r2) {
        A01(str);
        r2.A02();
        A00(this, str, r2);
        r2.A02();
        r2.A00 = this;
    }
}
