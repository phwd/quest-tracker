package androidx.appcompat.widget;

import X.AbstractC003104h;
import X.AbstractC003204i;
import X.AnonymousClass02D;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
public class FitWindowsLinearLayout extends LinearLayout implements AbstractC003204i {
    public AbstractC003104h A00;

    public final boolean fitSystemWindows(Rect rect) {
        return super.fitSystemWindows(rect);
    }

    public void setOnFitSystemWindowsListener(AbstractC003104h r1) {
        this.A00 = r1;
    }

    public FitWindowsLinearLayout(@NonNull Context context) {
        super(context);
    }

    public FitWindowsLinearLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
