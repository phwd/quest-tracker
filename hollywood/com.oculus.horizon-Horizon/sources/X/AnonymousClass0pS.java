package X;

import android.text.TextUtils;
import com.facebook.infer.annotation.NullsafeStrict;
import java.util.ArrayList;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NullsafeStrict
@NotThreadSafe
/* renamed from: X.0pS  reason: invalid class name */
public final class AnonymousClass0pS extends AbstractC01300Mt {
    public int A00;
    public final ArrayList<Object> A01;

    @Override // X.AbstractC01300Mt
    public final void A08() {
        int i = 0;
        while (true) {
            int i2 = this.A00;
            if (i >= i2) {
                return;
            }
            if (i >= 0 && i < i2) {
                Object obj = this.A01.get((i << 1) + 1);
                if (obj instanceof AbstractC01300Mt) {
                    ((AbstractC01300Mt) obj).A04();
                }
                i++;
            }
        }
        throw new ArrayIndexOutOfBoundsException(i);
    }

    private void A02(String str) {
        if (!this.A04) {
            throw new IllegalStateException("Expected object to be mutable");
        } else if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException(AnonymousClass006.A05("key=", str));
        }
    }

    @Override // X.AbstractC01300Mt
    public final void A06() {
        this.A01.clear();
        this.A00 = 0;
    }

    @Override // X.AbstractC01300Mt
    public final void A07() {
        super.A01.A03.A01(this);
    }

    @Override // X.AbstractC01300Mt
    public final void A09(int i) {
        int i2 = this.A00 - i;
        while (true) {
            int i3 = i2 - 1;
            if (i2 > 0) {
                ArrayList<Object> arrayList = this.A01;
                arrayList.remove(arrayList.size() - 1);
                arrayList.remove(arrayList.size() - 1);
                i2 = i3;
            } else {
                this.A01.trimToSize();
                return;
            }
        }
    }

    public final AnonymousClass0pT A0A() {
        C01310Mu r2 = super.A01;
        AnonymousClass0pT A002 = r2.A02.A00();
        if (A002 == null) {
            A002 = new AnonymousClass0pT(r2.A01);
        }
        A002.A05(r2);
        A02("fields");
        A002.A03();
        A01(this, "fields", A002);
        A002.A03();
        ((AbstractC01300Mt) A002).A00 = this;
        return A002;
    }

    public final AnonymousClass0pS A0B(String str) {
        AnonymousClass0pS A002 = super.A01.A00();
        A02(str);
        A002.A03();
        A01(this, str, A002);
        A002.A03();
        ((AbstractC01300Mt) A002).A00 = this;
        return A002;
    }

    public AnonymousClass0pS(int i) {
        this.A01 = new ArrayList<>(i << 1);
    }

    public static void A01(AnonymousClass0pS r1, @Nullable String str, Object obj) {
        r1.A02(str);
        ArrayList<Object> arrayList = r1.A01;
        arrayList.add(str);
        arrayList.add(obj);
        r1.A00++;
    }
}
