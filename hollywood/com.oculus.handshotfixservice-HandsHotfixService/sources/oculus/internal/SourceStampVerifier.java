package oculus.internal;

import android.util.apk.SourceStampVerificationResult;
import java.io.File;

public final class SourceStampVerifier {
    public static int OCULUS_SOURCE_STAMP_BLOCK_ID = 4660;

    public enum Disposition {
        NOT_SUPPORTED(-2),
        INVALID(-1),
        NOT_PRESENT(0),
        VERIFIED(1);
        
        public final int value;

        private Disposition(int value2) {
            this.value = value2;
        }
    }

    public static Disposition verify(File apk) {
        SourceStampVerificationResult result = android.util.apk.SourceStampVerifier.verifyBlock(apk.getPath(), OCULUS_SOURCE_STAMP_BLOCK_ID);
        if (!result.isPresent()) {
            return Disposition.NOT_PRESENT;
        }
        if (result.isVerified()) {
            return Disposition.VERIFIED;
        }
        return Disposition.INVALID;
    }
}
