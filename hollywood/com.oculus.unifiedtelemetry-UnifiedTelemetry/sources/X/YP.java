package X;

import com.facebook.acra.NonCrashException;
import javax.annotation.Nullable;

public final class YP extends Exception implements NonCrashException {
    @Override // com.facebook.acra.NonCrashException
    public final String getExceptionFriendlyName() {
        return "soft error";
    }

    public YP(String str, @Nullable Throwable th) {
        super(str, th);
    }
}
