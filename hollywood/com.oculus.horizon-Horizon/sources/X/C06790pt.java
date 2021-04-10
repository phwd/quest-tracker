package X;

import com.facebook.acra.NonCrashException;
import javax.annotation.Nullable;

/* renamed from: X.0pt  reason: invalid class name and case insensitive filesystem */
public final class C06790pt extends Exception implements NonCrashException {
    @Override // com.facebook.acra.NonCrashException
    public final String getExceptionFriendlyName() {
        return "soft error";
    }

    public C06790pt(String str, @Nullable Throwable th) {
        super(str, th);
    }
}
