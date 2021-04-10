package X;

import android.util.Log;
import androidx.annotation.NonNull;
import java.io.File;
import java.io.IOException;

/* renamed from: X.1es  reason: invalid class name and case insensitive filesystem */
public final class C08581es implements AnonymousClass1dV<AnonymousClass1gA> {
    @Override // X.AbstractC06701at
    public final boolean A2m(@NonNull Object obj, @NonNull File file, @NonNull AnonymousClass1cO r6) {
        try {
            C06631am.A00(((AnonymousClass1gA) ((AnonymousClass1fR) obj).get()).A09.A00.A0E.A09.asReadOnlyBuffer(), file);
            return true;
        } catch (IOException e) {
            if (!Log.isLoggable("GifEncoder", 5)) {
                return false;
            }
            Log.w("GifEncoder", "Failed to encode GIF drawable data", e);
            return false;
        }
    }

    @Override // X.AnonymousClass1dV
    @NonNull
    public final Integer A3s(@NonNull AnonymousClass1cO r2) {
        return AnonymousClass007.A00;
    }
}
