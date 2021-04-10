package com.oculus.appmanager.installer.common;

import com.facebook.ultralight.Dependencies;
import java.security.PublicKey;
import javax.annotation.Nullable;

@Dependencies({})
public class EcdsaSignatureVerifier {
    public static final byte[] OCULUS_PUBLIC_KEY = {48, 89, 48, 19, 6, 7, 42, -122, 72, -50, 61, 2, 1, 6, 8, 42, -122, 72, -50, 61, 3, 1, 7, 3, 66, 0, 4, 86, -117, -53, 81, -10, -119, 55, -25, 85, 7, -3, -112, 12, -88, 71, 31, 57, -54, -38, -34, -12, -59, -102, 13, -20, -37, 7, -47, 124, 20, 45, -33, Byte.MAX_VALUE, 14, 104, 69, -37, 72, -69, -38, 98, 111, -6, 27, -119, -18, 75, 92, 83, -14, -4, -3, -33, -26, -98, 26, 32, 80, 115, 114, 90, -62, 84, -100};
    @Nullable
    public PublicKey mPublicKey;
}
