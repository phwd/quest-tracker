package com.apple.dnssd;

/* compiled from: DNSSD */
class AppleQuery extends AppleService {
    /* access modifiers changed from: protected */
    public native int CreateQuery(int i, int i2, String str, int i3, int i4);

    public AppleQuery(int i, int i2, String str, int i3, int i4, QueryListener queryListener) throws DNSSDException {
        super(queryListener);
        ThrowOnErr(CreateQuery(i, i2, str, i3, i4));
        if (!AppleDNSSD.hasAutoCallbacks) {
            new Thread(this).start();
        }
    }
}
