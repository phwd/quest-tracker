package java.security.cert;

public interface X509Extension {
    byte[] getExtensionValue(String str);

    boolean hasUnsupportedCriticalExtension();
}
