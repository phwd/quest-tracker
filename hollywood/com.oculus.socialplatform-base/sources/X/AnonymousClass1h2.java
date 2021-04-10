package X;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;

@VisibleForTesting
/* renamed from: X.1h2  reason: invalid class name */
public final class AnonymousClass1h2 {
    @Nullable
    @VisibleForTesting
    public static Integer A03;
    @Nullable
    public AnonymousClass1h4 A00;
    public final View A01;
    public final List<AbstractC09041fz> A02 = new ArrayList();

    public static int A00(AnonymousClass1h2 r3, int i, int i2, int i3) {
        int i4 = i2 - i3;
        if (i4 > 0) {
            return i4;
        }
        int i5 = i - i3;
        if (i5 > 0) {
            return i5;
        }
        View view = r3.A01;
        if (view.isLayoutRequested() || i2 != -2) {
            return 0;
        }
        Context context = view.getContext();
        Integer num = A03;
        if (num == null) {
            Object systemService = context.getSystemService("window");
            AnonymousClass1S2.A00(systemService);
            Display defaultDisplay = ((WindowManager) systemService).getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getSize(point);
            num = Integer.valueOf(Math.max(point.x, point.y));
            A03 = num;
        }
        return num.intValue();
    }

    public AnonymousClass1h2(@NonNull View view) {
        this.A01 = view;
    }
}
