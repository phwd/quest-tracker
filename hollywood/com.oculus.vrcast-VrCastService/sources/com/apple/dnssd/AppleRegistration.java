package com.apple.dnssd;

/* compiled from: DNSSD */
class AppleRegistration extends AppleService implements DNSSDRegistration {
    /* access modifiers changed from: protected */
    public native int AddRecord(int i, int i2, byte[] bArr, int i3, AppleDNSRecord appleDNSRecord);

    /* access modifiers changed from: protected */
    public native int BeginRegister(int i, int i2, String str, String str2, String str3, String str4, int i3, byte[] bArr);

    public AppleRegistration(int i, int i2, String str, String str2, String str3, String str4, int i3, byte[] bArr, RegisterListener registerListener) throws DNSSDException {
        super(registerListener);
        ThrowOnErr(BeginRegister(i2, i, str, str2, str3, str4, i3, bArr));
        if (!AppleDNSSD.hasAutoCallbacks) {
            new Thread(this).start();
        }
    }

    @Override // com.apple.dnssd.DNSSDRegistration
    public DNSRecord addRecord(int i, int i2, byte[] bArr, int i3) throws DNSSDException {
        AppleDNSRecord appleDNSRecord = new AppleDNSRecord(this);
        ThrowOnErr(AddRecord(i, i2, bArr, i3, appleDNSRecord));
        return appleDNSRecord;
    }

    @Override // com.apple.dnssd.DNSSDRegistration
    public DNSRecord getTXTRecord() throws DNSSDException {
        return new AppleDNSRecord(this);
    }
}
