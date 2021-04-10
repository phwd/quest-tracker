package androidx.appcompat.widget;

import X.AnonymousClass02C;
import X.AnonymousClass1Dq;
import X.AnonymousClass1Dr;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
public class FitWindowsFrameLayout extends FrameLayout implements AnonymousClass1Dq {
    public AnonymousClass1Dr A00;

    public final boolean fitSystemWindows(Rect rect) {
        return super.fitSystemWindows(rect);
    }

    public void setOnFitSystemWindowsListener(AnonymousClass1Dr r1) {
        this.A00 = r1;
    }

    public FitWindowsFrameLayout(@NonNull Context context) {
        super(context);
    }

    public FitWindowsFrameLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
