package X;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* renamed from: X.1uU  reason: invalid class name */
public final class AnonymousClass1uU {
    public static AnonymousClass1uS A00 = new AnonymousClass1uT();

    /* JADX WARN: Incorrect args count in method signature: <T:LX/1uW;>(Landroid/view/LayoutInflater;ILandroid/view/ViewGroup;ZLX/08r;)TT; */
    public static AnonymousClass1uW A00(@NonNull LayoutInflater layoutInflater, int i, @Nullable ViewGroup viewGroup, boolean z) {
        boolean z2;
        int i2 = 0;
        if (viewGroup == null || !z) {
            z2 = false;
        } else {
            z2 = true;
            i2 = viewGroup.getChildCount();
        }
        View inflate = layoutInflater.inflate(i, viewGroup, z);
        if (z2) {
            int childCount = viewGroup.getChildCount();
            int i3 = childCount - i2;
            if (i3 == 1) {
                inflate = viewGroup.getChildAt(childCount - 1);
            } else {
                View[] viewArr = new View[i3];
                for (int i4 = 0; i4 < i3; i4++) {
                    viewArr[i4] = viewGroup.getChildAt(i4 + i2);
                }
                return A00.getDataBinder((AbstractC003408r) null, viewArr, i);
            }
        }
        return A00.getDataBinder((AbstractC003408r) null, inflate, i);
    }

    @Nullable
    public static <T extends AnonymousClass1uW> T A01(@NonNull View view) {
        T t = (T) AnonymousClass1uW.getBinding(view);
        if (t != null) {
            return t;
        }
        Object tag = view.getTag();
        if (tag instanceof String) {
            int layoutId = A00.getLayoutId((String) tag);
            if (layoutId != 0) {
                return (T) A00.getDataBinder((AbstractC003408r) null, view, layoutId);
            }
            StringBuilder sb = new StringBuilder("View is not a binding layout. Tag: ");
            sb.append(tag);
            throw new IllegalArgumentException(sb.toString());
        }
        throw new IllegalArgumentException("View is not a binding layout");
    }
}
