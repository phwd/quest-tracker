package android.support.v4.graphics.drawable;

import X.AnonymousClass2C;
import X.CW;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.drawable.IconCompat;

@RestrictTo({AnonymousClass2C.LIBRARY})
public final class IconCompatParcelizer extends androidx.core.graphics.drawable.IconCompatParcelizer {
    public static IconCompat read(CW cw) {
        return androidx.core.graphics.drawable.IconCompatParcelizer.read(cw);
    }

    public static void write(IconCompat iconCompat, CW cw) {
        androidx.core.graphics.drawable.IconCompatParcelizer.write(iconCompat, cw);
    }
}
