package X;

import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* renamed from: X.1FF  reason: invalid class name */
public final class AnonymousClass1FF {
    public static void A00(@NonNull View view, @Nullable CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 26) {
            view.setTooltipText(charSequence);
            return;
        }
        AnonymousClass1FD r0 = AnonymousClass1FD.A0A;
        if (r0 != null && r0.A05 == view) {
            AnonymousClass1FD.A00(null);
        }
        if (TextUtils.isEmpty(charSequence)) {
            AnonymousClass1FD r1 = AnonymousClass1FD.A09;
            if (r1 != null && r1.A05 == view) {
                r1.A01();
            }
            view.setOnLongClickListener(null);
            view.setLongClickable(false);
            view.setOnHoverListener(null);
            return;
        }
        new AnonymousClass1FD(view, charSequence);
    }
}
