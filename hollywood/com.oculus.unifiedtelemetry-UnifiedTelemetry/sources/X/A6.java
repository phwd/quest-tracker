package X;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

@SuppressLint({"UnknownNullness"})
@RestrictTo({AnonymousClass2C.LIBRARY_GROUP_PREFIX})
public abstract class A6 {
    public abstract Object A01(Object obj);

    public abstract Object A02(Object obj);

    public abstract Object A03(Object obj, Object obj2, Object obj3);

    public abstract void A04(ViewGroup viewGroup, Object obj);

    public abstract void A06(Object obj, View view);

    public abstract void A07(Object obj, View view);

    public abstract void A08(Object obj, View view, ArrayList<View> arrayList);

    public abstract void A09(Object obj, Object obj2, ArrayList<View> arrayList, Object obj3, ArrayList<View> arrayList2, Object obj4, ArrayList<View> arrayList3);

    public abstract void A0A(Object obj, ArrayList<View> arrayList);

    public abstract void A0B(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2);

    public abstract void A0C(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2);

    public abstract boolean A0D(Object obj);

    public void A05(@NonNull Fragment fragment, @NonNull Object obj, @NonNull AnonymousClass5d r3, @NonNull Runnable runnable) {
        runnable.run();
    }
}
