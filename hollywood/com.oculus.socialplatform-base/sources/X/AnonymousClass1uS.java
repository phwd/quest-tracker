package X;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.util.Collections;
import java.util.List;

@RestrictTo({AnonymousClass02C.LIBRARY})
/* renamed from: X.1uS  reason: invalid class name */
public abstract class AnonymousClass1uS {
    public abstract String convertBrIdToString(int i);

    public abstract AnonymousClass1uW getDataBinder(AbstractC003408r v, View view, int i);

    public abstract AnonymousClass1uW getDataBinder(AbstractC003408r v, View[] viewArr, int i);

    public abstract int getLayoutId(String str);

    @NonNull
    public List<AnonymousClass1uS> collectDependencies() {
        return Collections.emptyList();
    }
}
