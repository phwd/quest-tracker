package X;

import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* renamed from: X.05f  reason: invalid class name and case insensitive filesystem */
public final class C004805f {
    public static void A00(@NonNull View view, @Nullable CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 26) {
            view.setTooltipText(charSequence);
            return;
        }
        View$OnAttachStateChangeListenerC005105i r0 = View$OnAttachStateChangeListenerC005105i.A0A;
        if (r0 != null && r0.A05 == view) {
            View$OnAttachStateChangeListenerC005105i.A00(null);
        }
        if (TextUtils.isEmpty(charSequence)) {
            View$OnAttachStateChangeListenerC005105i r1 = View$OnAttachStateChangeListenerC005105i.A09;
            if (r1 != null && r1.A05 == view) {
                r1.A01();
            }
            view.setOnLongClickListener(null);
            view.setLongClickable(false);
            view.setOnHoverListener(null);
            return;
        }
        new View$OnAttachStateChangeListenerC005105i(view, charSequence);
    }
}
