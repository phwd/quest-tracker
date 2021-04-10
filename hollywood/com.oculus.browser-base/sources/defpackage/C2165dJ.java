package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.List;

/* renamed from: dJ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2165dJ extends ArrayAdapter {
    public C2165dJ(Context context, int i, int i2, List list) {
        super(context, i, i2, new ArrayList(list));
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2 = super.getView(i, view, viewGroup);
        Rect rect = new Rect();
        viewGroup.getBackground().getPadding(rect);
        view2.setPadding(view2.getPaddingLeft() + rect.left, view2.getPaddingTop(), view2.getPaddingRight() + rect.right, view2.getPaddingBottom());
        return view2;
    }

    public C2165dJ(Context context, int i, List list) {
        super(context, i, new ArrayList(list));
    }
}
