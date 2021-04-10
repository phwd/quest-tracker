package X;

import android.view.WindowInsets;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

@RequiresApi(29)
/* renamed from: X.08u  reason: invalid class name */
public class AnonymousClass08u extends C01430Gx {
    @Override // X.C03720d8, X.AnonymousClass0B6
    @NonNull
    public final AnonymousClass0B7 A06(int i, int i2, int i3, int i4) {
        return AnonymousClass0B7.A01(this.A01.inset(i, i2, i3, i4));
    }

    public AnonymousClass08u(@NonNull AnonymousClass0B7 r1, @NonNull WindowInsets windowInsets) {
        super(r1, windowInsets);
    }
}
