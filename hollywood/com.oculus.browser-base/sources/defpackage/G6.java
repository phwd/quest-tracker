package defpackage;

import android.animation.LayoutTransition;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;

/* renamed from: G6  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class G6 {

    /* renamed from: a  reason: collision with root package name */
    public static final ViewGroup.MarginLayoutParams f8063a;
    public LinearLayoutManager b;

    static {
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-1, -1);
        f8063a = marginLayoutParams;
        marginLayoutParams.setMargins(0, 0, 0, 0);
    }

    public G6(LinearLayoutManager linearLayoutManager) {
        this.b = linearLayoutManager;
    }

    public static boolean a(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            LayoutTransition layoutTransition = viewGroup.getLayoutTransition();
            if (layoutTransition != null && layoutTransition.isChangingLayout()) {
                return true;
            }
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (a(viewGroup.getChildAt(i))) {
                    return true;
                }
            }
        }
        return false;
    }
}
