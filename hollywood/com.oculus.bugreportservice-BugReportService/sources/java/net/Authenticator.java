package java.net;

public abstract class Authenticator {
    private static Authenticator theAuthenticator;
    private RequestorType requestingAuthType;
    private String requestingHost;
    private int requestingPort;
    private String requestingPrompt;
    private String requestingProtocol;
    private String requestingScheme;
    private InetAddress requestingSite;
    private URL requestingURL;

    public enum RequestorType {
        PROXY,
        SERVER
    }

    /* access modifiers changed from: protected */
    public abstract PasswordAuthentication getPasswordAuthentication();

    private void reset() {
        this.requestingHost = null;
        this.requestingSite = null;
        this.requestingPort = -1;
        this.requestingProtocol = null;
        this.requestingPrompt = null;
        this.requestingScheme = null;
        this.requestingURL = null;
        this.requestingAuthType = RequestorType.SERVER;
    }

    public static PasswordAuthentication requestPasswordAuthentication(String str, InetAddress inetAddress, int i, String str2, String str3, String str4) {
        PasswordAuthentication passwordAuthentication;
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager == null) {
            Authenticator authenticator = theAuthenticator;
            if (authenticator == null) {
                return null;
            }
            synchronized (authenticator) {
                authenticator.reset();
                authenticator.requestingHost = str;
                authenticator.requestingSite = inetAddress;
                authenticator.requestingPort = i;
                authenticator.requestingProtocol = str2;
                authenticator.requestingPrompt = str3;
                authenticator.requestingScheme = str4;
                passwordAuthentication = authenticator.getPasswordAuthentication();
            }
            return passwordAuthentication;
        }
        securityManager.checkPermission(new NetPermission("requestPasswordAuthentication"));
        throw null;
    }

    public static PasswordAuthentication requestPasswordAuthentication(String str, InetAddress inetAddress, int i, String str2, String str3, String str4, URL url, RequestorType requestorType) {
        PasswordAuthentication passwordAuthentication;
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager == null) {
            Authenticator authenticator = theAuthenticator;
            if (authenticator == null) {
                return null;
            }
            synchronized (authenticator) {
                authenticator.reset();
                authenticator.requestingHost = str;
                authenticator.requestingSite = inetAddress;
                authenticator.requestingPort = i;
                authenticator.requestingProtocol = str2;
                authenticator.requestingPrompt = str3;
                authenticator.requestingScheme = str4;
                authenticator.requestingURL = url;
                authenticator.requestingAuthType = requestorType;
                passwordAuthentication = authenticator.getPasswordAuthentication();
            }
            return passwordAuthentication;
        }
        securityManager.checkPermission(new NetPermission("requestPasswordAuthentication"));
        throw null;
    }
}
