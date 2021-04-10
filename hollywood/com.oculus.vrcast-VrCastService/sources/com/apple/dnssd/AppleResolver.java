package com.apple.dnssd;

/* compiled from: DNSSD */
class AppleResolver extends AppleService {
    /* access modifiers changed from: protected */
    public native int CreateResolver(int i, int i2, String str, String str2, String str3);

    public AppleResolver(int i, int i2, String str, String str2, String str3, ResolveListener resolveListener) throws DNSSDException {
        super(resolveListener);
        ThrowOnErr(CreateResolver(i, i2, str, str2, str3));
        if (!AppleDNSSD.hasAutoCallbacks) {
            new Thread(this).start();
        }
    }
}
