package X;

import android.util.SparseIntArray;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X.2Q8  reason: invalid class name */
public final class AnonymousClass2Q8 extends AnonymousClass1uS {
    public static final SparseIntArray A00 = new SparseIntArray(0);

    @Override // X.AnonymousClass1uS
    public final List<AnonymousClass1uS> collectDependencies() {
        return new ArrayList(0);
    }

    @Override // X.AnonymousClass1uS
    public final int getLayoutId(String str) {
        Integer num;
        if (str == null || (num = AnonymousClass2Q9.A00.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }

    @Override // X.AnonymousClass1uS
    public final String convertBrIdToString(int i) {
        return AnonymousClass2QA.A00.get(i);
    }

    @Override // X.AnonymousClass1uS
    public final AnonymousClass1uW getDataBinder(AbstractC003408r r3, View view, int i) {
        if (A00.get(i) <= 0 || view.getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }

    @Override // X.AnonymousClass1uS
    public final AnonymousClass1uW getDataBinder(AbstractC003408r r3, View[] viewArr, int i) {
        if (viewArr == null || viewArr.length == 0 || A00.get(i) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }
}
