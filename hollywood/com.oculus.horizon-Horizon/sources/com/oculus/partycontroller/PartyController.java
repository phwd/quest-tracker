package com.oculus.partycontroller;

import android.os.Bundle;
import com.facebook.inject.RequiresBinding;
import com.oculus.platforminitexception.PlatformInitException;
import java.util.Set;

@RequiresBinding
public interface PartyController {
    boolean A3U(long j);

    Set<String> A3z();

    float A46() throws PlatformInitException;

    boolean A47();

    String A4V() throws PlatformInitException, IllegalArgumentException;

    void A8i(float f) throws PlatformInitException;

    int A8j(long j, int i);

    String A8p(int i);

    Bundle A9J(long j);

    Bundle A9S();
}
