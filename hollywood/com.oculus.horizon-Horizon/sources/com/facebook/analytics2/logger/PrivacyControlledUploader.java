package com.facebook.analytics2.logger;

import X.AnonymousClass0qH;
import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class PrivacyControlledUploader {
    public static final IOException A02 = new IOException("Upload is skipped due to privacy control.");
    public AnonymousClass0qH A00;
    public PrivacyControlledUploader A01;

    public PrivacyControlledUploader(PrivacyControlledUploader privacyControlledUploader, AnonymousClass0qH r2) {
        this.A01 = privacyControlledUploader;
        this.A00 = r2;
    }
}
