package com.apple.dnssd;

/* compiled from: DNSSD */
class AppleDomainEnum extends AppleService {
    /* access modifiers changed from: protected */
    public native int BeginEnum(int i, int i2);

    public AppleDomainEnum(int i, int i2, DomainListener domainListener) throws DNSSDException {
        super(domainListener);
        ThrowOnErr(BeginEnum(i, i2));
        if (!AppleDNSSD.hasAutoCallbacks) {
            new Thread(this).start();
        }
    }
}
