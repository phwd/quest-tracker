package com.apple.dnssd;

public abstract class DNSSD {
    public static final int ALL_INTERFACES = 0;
    public static final int BROWSE_DOMAINS = 64;
    public static final int DEFAULT = 4;
    public static final int LOCALHOST_ONLY = -1;
    public static final int MAX_DOMAIN_NAME = 1009;
    public static final int MORE_COMING = 1;
    public static final int NO_AUTO_RENAME = 8;
    public static final int REGISTRATION_DOMAINS = 128;
    public static final int SHARED = 16;
    public static final int UNIQUE = 32;
    protected static DNSSD fInstance;

    /* access modifiers changed from: protected */
    public abstract String _constructFullName(String str, String str2, String str3) throws DNSSDException;

    /* access modifiers changed from: protected */
    public abstract DNSSDRecordRegistrar _createRecordRegistrar(RegisterRecordListener registerRecordListener) throws DNSSDException;

    /* access modifiers changed from: protected */
    public abstract DNSSDService _enumerateDomains(int i, int i2, DomainListener domainListener) throws DNSSDException;

    /* access modifiers changed from: protected */
    public abstract int _getIfIndexForName(String str);

    /* access modifiers changed from: protected */
    public abstract String _getNameForIfIndex(int i);

    /* access modifiers changed from: protected */
    public abstract DNSSDService _makeBrowser(int i, int i2, String str, String str2, BrowseListener browseListener) throws DNSSDException;

    /* access modifiers changed from: protected */
    public abstract DNSSDService _queryRecord(int i, int i2, String str, int i3, int i4, QueryListener queryListener) throws DNSSDException;

    /* access modifiers changed from: protected */
    public abstract void _reconfirmRecord(int i, int i2, String str, int i3, int i4, byte[] bArr);

    /* access modifiers changed from: protected */
    public abstract DNSSDRegistration _register(int i, int i2, String str, String str2, String str3, String str4, int i3, TXTRecord tXTRecord, RegisterListener registerListener) throws DNSSDException;

    /* access modifiers changed from: protected */
    public abstract DNSSDService _resolve(int i, int i2, String str, String str2, String str3, ResolveListener resolveListener) throws DNSSDException;

    public static DNSSDService browse(int i, int i2, String str, String str2, BrowseListener browseListener) throws DNSSDException {
        return getInstance()._makeBrowser(i, i2, str, str2, browseListener);
    }

    public static DNSSDService browse(String str, BrowseListener browseListener) throws DNSSDException {
        return browse(0, 0, str, "", browseListener);
    }

    public static DNSSDService resolve(int i, int i2, String str, String str2, String str3, ResolveListener resolveListener) throws DNSSDException {
        return getInstance()._resolve(i, i2, str, str2, str3, resolveListener);
    }

    public static DNSSDRegistration register(int i, int i2, String str, String str2, String str3, String str4, int i3, TXTRecord tXTRecord, RegisterListener registerListener) throws DNSSDException {
        return getInstance()._register(i, i2, str, str2, str3, str4, i3, tXTRecord, registerListener);
    }

    public static DNSSDRegistration register(String str, String str2, int i, RegisterListener registerListener) throws DNSSDException {
        return register(0, 0, str, str2, null, null, i, null, registerListener);
    }

    public static DNSSDRecordRegistrar createRecordRegistrar(RegisterRecordListener registerRecordListener) throws DNSSDException {
        return getInstance()._createRecordRegistrar(registerRecordListener);
    }

    public static DNSSDService queryRecord(int i, int i2, String str, int i3, int i4, QueryListener queryListener) throws DNSSDException {
        return getInstance()._queryRecord(i, i2, str, i3, i4, queryListener);
    }

    public static DNSSDService enumerateDomains(int i, int i2, DomainListener domainListener) throws DNSSDException {
        return getInstance()._enumerateDomains(i, i2, domainListener);
    }

    public static String constructFullName(String str, String str2, String str3) throws DNSSDException {
        return getInstance()._constructFullName(str, str2, str3);
    }

    public static void reconfirmRecord(int i, int i2, String str, int i3, int i4, byte[] bArr) {
        getInstance()._reconfirmRecord(i, i2, str, i3, i4, bArr);
    }

    public static String getNameForIfIndex(int i) {
        return getInstance()._getNameForIfIndex(i);
    }

    public static int getIfIndexForName(String str) {
        return getInstance()._getIfIndexForName(str);
    }

    protected DNSSD() {
    }

    protected static final DNSSD getInstance() {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(new RuntimePermission("getDNSSDInstance"));
        }
        return fInstance;
    }

    static {
        try {
            String property = System.getProperty("com.apple.dnssd.DNSSD");
            if (property == null) {
                property = "com.apple.dnssd.AppleDNSSD";
            }
            fInstance = (DNSSD) Class.forName(property).newInstance();
        } catch (Exception e) {
            throw new InternalError("cannot instantiate DNSSD" + e);
        }
    }
}
