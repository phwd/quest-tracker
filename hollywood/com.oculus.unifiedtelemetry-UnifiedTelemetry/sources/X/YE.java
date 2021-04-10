package X;

import android.text.TextUtils;
import com.facebook.infer.annotation.NullsafeStrict;
import java.util.ArrayList;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NullsafeStrict
@NotThreadSafe
public final class YE extends MF {
    public int A00;
    public final ArrayList<Object> A01;

    @Override // X.MF
    public final void A08() {
        for (int i = 0; i < this.A00; i++) {
            Object A0A = A0A(i);
            if (A0A instanceof MF) {
                ((MF) A0A).A04();
            }
        }
    }

    private void A01(String str) {
        if (!this.A03) {
            throw new IllegalStateException("Expected object to be mutable");
        } else if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException(AnonymousClass06.A04("key=", str));
        }
    }

    @Override // X.MF
    public final void A06() {
        this.A01.clear();
        this.A00 = 0;
    }

    @Override // X.MF
    public final void A07() {
        super.A01.A02.A4e(this);
    }

    @Override // X.MF
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

    @Nullable
    public final Object A0A(int i) {
        if (i >= 0 && i < this.A00) {
            return this.A01.get((i << 1) + 1);
        }
        throw new ArrayIndexOutOfBoundsException(i);
    }

    public final String A0B(int i) {
        if (i >= 0 && i < this.A00) {
            return (String) this.A01.get(i << 1);
        }
        throw new ArrayIndexOutOfBoundsException(i);
    }

    public YE(int i) {
        this.A01 = new ArrayList<>(i << 1);
    }

    public static void A00(YE ye, @Nullable String str, Object obj) {
        ye.A01(str);
        ArrayList<Object> arrayList = ye.A01;
        arrayList.add(str);
        arrayList.add(obj);
        ye.A00++;
    }

    public final void A0C(String str, MF mf) {
        A01(str);
        mf.A03();
        A00(this, str, mf);
        mf.A03();
        mf.A00 = this;
    }
}
