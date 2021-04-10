package X;

import android.content.Context;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import androidx.annotation.RestrictTo;
import androidx.appcompat.app.ActionBar;
import com.oculus.socialplatform.R;
import java.util.ArrayList;

/* renamed from: X.1rX  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC11251rX {
    public final Context A03() {
        if (this instanceof C11201rK) {
            C11201rK r4 = (C11201rK) this;
            Context context = r4.A02;
            if (context == null) {
                TypedValue typedValue = new TypedValue();
                r4.A01.getTheme().resolveAttribute(R.attr.actionBarWidgetTheme, typedValue, true);
                int i = typedValue.resourceId;
                if (i != 0) {
                    context = new ContextThemeWrapper(r4.A01, i);
                } else {
                    context = r4.A01;
                }
                r4.A02 = context;
            }
            return context;
        } else if (!(this instanceof AnonymousClass1rR)) {
            return null;
        } else {
            return ((AnonymousClass1rR) this).A02.A3d();
        }
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public final void A04(boolean z) {
        if (this instanceof C11201rK) {
            C11201rK r1 = (C11201rK) this;
            if (z != r1.A0H) {
                r1.A0H = z;
                ArrayList<ActionBar.OnMenuVisibilityListener> arrayList = r1.A0C;
                if (0 < arrayList.size()) {
                    arrayList.get(0);
                    throw new NullPointerException("onMenuVisibilityChanged");
                }
            }
        } else if (this instanceof AnonymousClass1rR) {
            AnonymousClass1rR r12 = (AnonymousClass1rR) this;
            if (z != r12.A04) {
                r12.A04 = z;
                ArrayList<ActionBar.OnMenuVisibilityListener> arrayList2 = r12.A03;
                if (0 < arrayList2.size()) {
                    arrayList2.get(0);
                    throw new NullPointerException("onMenuVisibilityChanged");
                }
            }
        }
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public final void A05(boolean z) {
        if (this instanceof C11201rK) {
            C11201rK r5 = (C11201rK) this;
            if (!r5.A0E) {
                int i = 0;
                if (z) {
                    i = 4;
                }
                AbstractC06001Eq r2 = r5.A0B;
                int A3o = r2.A3o();
                r5.A0E = true;
                r2.A9p((i & 4) | (-5 & A3o));
            }
        }
    }
}
