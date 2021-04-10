package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.ArrayList;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class YF extends MF {
    public final ArrayList<Object> A00;

    public static final void A00(YF yf, MF mf) {
        if (yf.A03) {
            mf.A03();
            A01(yf, mf);
            mf.A03();
            mf.A00 = yf;
            return;
        }
        throw new IllegalStateException("Expected object to be mutable");
    }

    public static void A01(@Nullable YF yf, Object obj) {
        if (yf.A03) {
            yf.A00.add(obj);
            return;
        }
        throw new IllegalStateException("Expected object to be mutable");
    }

    @Override // X.MF
    public final void A06() {
        this.A00.clear();
    }

    @Override // X.MF
    public final void A07() {
        this.A01.A01.A4e(this);
    }

    @Override // X.MF
    public final void A08() {
        ArrayList<Object> arrayList = this.A00;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Object obj = arrayList.get(i);
            if (obj instanceof MF) {
                ((MF) obj).A04();
            }
        }
    }

    @Override // X.MF
    public final void A09(int i) {
        ArrayList<Object> arrayList = this.A00;
        int size = arrayList.size() - i;
        while (true) {
            int i2 = size - 1;
            if (size > 0) {
                arrayList.remove(arrayList.size() - 1);
                size = i2;
            } else {
                arrayList.trimToSize();
                return;
            }
        }
    }

    public YF(int i) {
        this.A00 = new ArrayList<>(i);
    }
}
