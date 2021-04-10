package X;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.Map;

@SuppressLint({"UnknownNullness"})
@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.0A6  reason: invalid class name */
public abstract class AnonymousClass0A6 {
    public abstract Object A04(Object obj);

    public abstract Object A05(Object obj);

    public abstract Object A06(Object obj, Object obj2, Object obj3);

    public abstract Object A07(Object obj, Object obj2, Object obj3);

    public abstract void A08(ViewGroup viewGroup, Object obj);

    public abstract void A0A(Object obj, Rect rect);

    public abstract void A0B(Object obj, View view);

    public abstract void A0C(Object obj, View view);

    public abstract void A0D(Object obj, View view);

    public abstract void A0E(Object obj, View view, ArrayList<View> arrayList);

    public abstract void A0F(Object obj, View view, ArrayList<View> arrayList);

    public abstract void A0G(Object obj, Object obj2, ArrayList<View> arrayList, Object obj3, ArrayList<View> arrayList2, Object obj4, ArrayList<View> arrayList3);

    public abstract void A0H(Object obj, ArrayList<View> arrayList);

    public abstract void A0I(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2);

    public abstract void A0J(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2);

    public abstract boolean A0K(Object obj);

    public static final void A00(View view, Rect rect) {
        if (view.isAttachedToWindow()) {
            RectF rectF = new RectF();
            rectF.set(0.0f, 0.0f, (float) view.getWidth(), (float) view.getHeight());
            view.getMatrix().mapRect(rectF);
            rectF.offset((float) view.getLeft(), (float) view.getTop());
            ViewParent parent = view.getParent();
            while (parent instanceof View) {
                View view2 = (View) parent;
                rectF.offset((float) (-view2.getScrollX()), (float) (-view2.getScrollY()));
                view2.getMatrix().mapRect(rectF);
                rectF.offset((float) view2.getLeft(), (float) view2.getTop());
                parent = view2.getParent();
            }
            int[] iArr = new int[2];
            view.getRootView().getLocationOnScreen(iArr);
            rectF.offset((float) iArr[0], (float) iArr[1]);
            rect.set(Math.round(rectF.left), Math.round(rectF.top), Math.round(rectF.right), Math.round(rectF.bottom));
        }
    }

    public final void A02(ArrayList<View> arrayList, View view) {
        if (view.getVisibility() != 0) {
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (viewGroup.isTransitionGroup()) {
                arrayList.add(viewGroup);
                return;
            }
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                A02(arrayList, viewGroup.getChildAt(i));
            }
            return;
        }
        arrayList.add(view);
    }

    public final void A03(Map<String, View> map, @NonNull View view) {
        if (view.getVisibility() == 0) {
            String transitionName = view.getTransitionName();
            if (transitionName != null) {
                map.put(transitionName, view);
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    A03(map, viewGroup.getChildAt(i));
                }
            }
        }
    }

    public void A09(@NonNull Fragment fragment, @NonNull Object obj, @NonNull AnonymousClass05d r3, @NonNull Runnable runnable) {
        runnable.run();
    }
}
