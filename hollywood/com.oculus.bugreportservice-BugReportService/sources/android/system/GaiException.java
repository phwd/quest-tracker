package android.system;

import java.net.UnknownHostException;
import libcore.io.Libcore;

public final class GaiException extends RuntimeException {
    public final int error;
    private final String functionName;

    @Override // java.lang.Throwable
    public String getMessage() {
        String gaiName = OsConstants.gaiName(this.error);
        if (gaiName == null) {
            gaiName = "GAI_ error " + this.error;
        }
        return this.functionName + " failed: " + gaiName + " (" + Libcore.os.gai_strerror(this.error) + ")";
    }

    public UnknownHostException rethrowAsUnknownHostException(String str) {
        UnknownHostException unknownHostException = new UnknownHostException(str);
        unknownHostException.initCause(this);
        throw unknownHostException;
    }
}
