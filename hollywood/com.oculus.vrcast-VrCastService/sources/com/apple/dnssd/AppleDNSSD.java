package com.apple.dnssd;

/* compiled from: DNSSD */
class AppleDNSSD extends DNSSD {
    public static boolean hasAutoCallbacks;

    protected static native int InitLibrary(int i);

    /* access modifiers changed from: protected */
    public native int ConstructName(String str, String str2, String str3, String[] strArr);

    /* access modifiers changed from: protected */
    public native int GetIfIndexForName(String str);

    /* access modifiers changed from: protected */
    public native String GetNameForIfIndex(int i);

    /* access modifiers changed from: protected */
    public native void ReconfirmRecord(int i, int i2, String str, int i3, int i4, byte[] bArr);

    AppleDNSSD() {
    }

    static {
        System.loadLibrary("jdns_sd");
        int InitLibrary = InitLibrary(2);
        if (InitLibrary != 0) {
            throw new InternalError("cannot instantiate DNSSD: " + new AppleDNSSDException(InitLibrary).getMessage());
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.apple.dnssd.DNSSD
    public DNSSDService _makeBrowser(int i, int i2, String str, String str2, BrowseListener browseListener) throws DNSSDException {
        return new AppleBrowser(i, i2, str, str2, browseListener);
    }

    /* access modifiers changed from: protected */
    @Override // com.apple.dnssd.DNSSD
    public DNSSDService _resolve(int i, int i2, String str, String str2, String str3, ResolveListener resolveListener) throws DNSSDException {
        return new AppleResolver(i, i2, str, str2, str3, resolveListener);
    }

    /* access modifiers changed from: protected */
    @Override // com.apple.dnssd.DNSSD
    public DNSSDRegistration _register(int i, int i2, String str, String str2, String str3, String str4, int i3, TXTRecord tXTRecord, RegisterListener registerListener) throws DNSSDException {
        return new AppleRegistration(i, i2, str, str2, str3, str4, i3, tXTRecord != null ? tXTRecord.getRawBytes() : null, registerListener);
    }

    /* access modifiers changed from: protected */
    @Override // com.apple.dnssd.DNSSD
    public DNSSDRecordRegistrar _createRecordRegistrar(RegisterRecordListener registerRecordListener) throws DNSSDException {
        return new AppleRecordRegistrar(registerRecordListener);
    }

    /* access modifiers changed from: protected */
    @Override // com.apple.dnssd.DNSSD
    public DNSSDService _queryRecord(int i, int i2, String str, int i3, int i4, QueryListener queryListener) throws DNSSDException {
        return new AppleQuery(i, i2, str, i3, i4, queryListener);
    }

    /* access modifiers changed from: protected */
    @Override // com.apple.dnssd.DNSSD
    public DNSSDService _enumerateDomains(int i, int i2, DomainListener domainListener) throws DNSSDException {
        return new AppleDomainEnum(i, i2, domainListener);
    }

    /* access modifiers changed from: protected */
    @Override // com.apple.dnssd.DNSSD
    public String _constructFullName(String str, String str2, String str3) throws DNSSDException {
        String[] strArr = new String[1];
        int ConstructName = ConstructName(str, str2, str3, strArr);
        if (ConstructName == 0) {
            return strArr[0];
        }
        throw new AppleDNSSDException(ConstructName);
    }

    /* access modifiers changed from: protected */
    @Override // com.apple.dnssd.DNSSD
    public void _reconfirmRecord(int i, int i2, String str, int i3, int i4, byte[] bArr) {
        ReconfirmRecord(i, i2, str, i3, i4, bArr);
    }

    /* access modifiers changed from: protected */
    @Override // com.apple.dnssd.DNSSD
    public String _getNameForIfIndex(int i) {
        return GetNameForIfIndex(i);
    }

    /* access modifiers changed from: protected */
    @Override // com.apple.dnssd.DNSSD
    public int _getIfIndexForName(String str) {
        return GetIfIndexForName(str);
    }
}
