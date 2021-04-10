package org.chromium.content_public.common;

import J.N;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ResourceRequestBody {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f10941a;

    public ResourceRequestBody(byte[] bArr) {
        this.f10941a = bArr;
    }

    public static ResourceRequestBody a(byte[] bArr) {
        return createFromEncodedNativeForm(N.MugoAW_d(bArr));
    }

    public static ResourceRequestBody createFromEncodedNativeForm(byte[] bArr) {
        return new ResourceRequestBody(bArr);
    }

    public final byte[] getEncodedNativeForm() {
        return this.f10941a;
    }
}
