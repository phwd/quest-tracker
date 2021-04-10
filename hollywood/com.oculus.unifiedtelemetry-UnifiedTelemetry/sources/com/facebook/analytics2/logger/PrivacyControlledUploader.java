package com.facebook.analytics2.logger;

import X.AbstractC0090Hb;
import X.C0089Ha;
import X.YW;
import X.Z3;
import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class PrivacyControlledUploader implements AbstractC0090Hb {
    public static final IOException A02 = new IOException("Upload is skipped due to privacy control.");
    public Z3 A00;
    public AbstractC0090Hb A01;

    @Override // X.AbstractC0090Hb
    public final void A5a(C0089Ha ha, YW yw) {
        this.A01.A5a(ha, yw);
    }

    public PrivacyControlledUploader(AbstractC0090Hb hb, Z3 z3) {
        this.A01 = hb;
        this.A00 = z3;
    }
}
