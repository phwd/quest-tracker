package com.facebook.analytics2.logger;

import X.AnonymousClass87;
import X.C0674ew;
import java.io.IOException;

public class PrivacyControlledUploader implements AnonymousClass87 {
    public static final IOException A02 = new IOException("Upload is skipped due to privacy control.");
    public C0674ew A00;
    public AnonymousClass87 A01;

    public PrivacyControlledUploader(AnonymousClass87 r1, C0674ew ewVar) {
        this.A01 = r1;
        this.A00 = ewVar;
    }
}
