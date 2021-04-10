package X;

import java.io.IOException;

/* renamed from: X.Yu  reason: case insensitive filesystem */
public class C0270Yu implements HK {
    @Override // X.HK
    public final void A3j(IOException iOException) {
        Mu.A08("InProcessUploadScheduler", iOException, "Failed to upload batch, it will not be retried");
    }

    @Override // X.HK
    public final void A43() {
    }
}
