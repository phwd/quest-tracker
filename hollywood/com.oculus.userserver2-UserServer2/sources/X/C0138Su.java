package X;

import com.facebook.acra.NonCrashException;
import javax.annotation.Nullable;

/* renamed from: X.Su  reason: case insensitive filesystem */
public final class C0138Su extends Exception implements NonCrashException {
    @Override // com.facebook.acra.NonCrashException
    public final String getExceptionFriendlyName() {
        return "soft error";
    }

    public C0138Su(String str, @Nullable Throwable th) {
        super(str, th);
    }
}
