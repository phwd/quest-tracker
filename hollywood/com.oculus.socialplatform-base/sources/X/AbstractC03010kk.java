package X;

import android.content.Intent;
import com.facebook.secure.logger.IntentLogger$Status;
import javax.annotation.Nullable;

/* renamed from: X.0kk  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC03010kk {
    public abstract void logIntent(String str, @Nullable String str2, @IntentLogger$Status @Nullable String str3, @Nullable Intent intent);

    public void logIntent(String str, @Nullable Intent intent) {
        logIntent(str, null, null, intent);
    }
}
