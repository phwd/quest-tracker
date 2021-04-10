package X;

import com.facebook.assistant.oacr.OacrConstants;

public final class E4 extends RuntimeException {
    public E4(Throwable th) {
        super(OacrConstants.AUTO_SPEECH_DOMAIN, th);
    }
}
