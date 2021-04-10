package com.apple.dnssd;

/* compiled from: DNSSD */
class AppleRecordRegistrar extends AppleService implements DNSSDRecordRegistrar {
    /* access modifiers changed from: protected */
    public native int CreateConnection();

    /* access modifiers changed from: protected */
    public native int RegisterRecord(int i, int i2, String str, int i3, int i4, byte[] bArr, int i5, AppleDNSRecord appleDNSRecord);

    public AppleRecordRegistrar(RegisterRecordListener registerRecordListener) throws DNSSDException {
        super(registerRecordListener);
        ThrowOnErr(CreateConnection());
        if (!AppleDNSSD.hasAutoCallbacks) {
            new Thread(this).start();
        }
    }

    @Override // com.apple.dnssd.DNSSDRecordRegistrar
    public DNSRecord registerRecord(int i, int i2, String str, int i3, int i4, byte[] bArr, int i5) throws DNSSDException {
        AppleDNSRecord appleDNSRecord = new AppleDNSRecord(this);
        ThrowOnErr(RegisterRecord(i, i2, str, i3, i4, bArr, i5, appleDNSRecord));
        return appleDNSRecord;
    }
}
