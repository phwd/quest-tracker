package com.facebook.msys.mci;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public interface Crypto {
    @DoNotStrip
    byte[] computeMd5(byte[] bArr);
}
