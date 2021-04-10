package sun.net.spi;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Pattern;
import sun.net.NetProperties;
import sun.net.SocksProxy;

public class DefaultProxySelector extends ProxySelector {
    private static boolean hasSystemProxies = false;
    static final String[][] props = {new String[]{"http", "http.proxy", "proxy", "socksProxy"}, new String[]{"https", "https.proxy", "proxy", "socksProxy"}, new String[]{"ftp", "ftp.proxy", "ftpProxy", "proxy", "socksProxy"}, new String[]{"gopher", "gopherProxy", "socksProxy"}, new String[]{"socket", "socksProxy"}};

    /* access modifiers changed from: package-private */
    public static class NonProxyInfo {
        static NonProxyInfo ftpNonProxyInfo = new NonProxyInfo("ftp.nonProxyHosts", null, null, "localhost|127.*|[::1]|0.0.0.0|[::0]");
        static NonProxyInfo httpNonProxyInfo = new NonProxyInfo("http.nonProxyHosts", null, null, "localhost|127.*|[::1]|0.0.0.0|[::0]");
        static NonProxyInfo httpsNonProxyInfo = new NonProxyInfo("https.nonProxyHosts", null, null, "localhost|127.*|[::1]|0.0.0.0|[::0]");
        static NonProxyInfo socksNonProxyInfo = new NonProxyInfo("socksNonProxyHosts", null, null, "localhost|127.*|[::1]|0.0.0.0|[::0]");
        final String defaultVal;
        String hostsSource;
        Pattern pattern;
        final String property;

        NonProxyInfo(String str, String str2, Pattern pattern2, String str3) {
            this.property = str;
            this.hostsSource = str2;
            this.pattern = pattern2;
            this.defaultVal = str3;
        }
    }

    @Override // java.net.ProxySelector
    public List select(URI uri) {
        String authority;
        if (uri != null) {
            final String scheme = uri.getScheme();
            String host = uri.getHost();
            if (host == null && (authority = uri.getAuthority()) != null) {
                int indexOf = authority.indexOf(64);
                if (indexOf >= 0) {
                    authority = authority.substring(indexOf + 1);
                }
                int lastIndexOf = authority.lastIndexOf(58);
                if (lastIndexOf >= 0) {
                    authority = authority.substring(0, lastIndexOf);
                }
                host = authority;
            }
            if (scheme == null || host == null) {
                throw new IllegalArgumentException("protocol = " + scheme + " host = " + host);
            }
            ArrayList arrayList = new ArrayList(1);
            final NonProxyInfo nonProxyInfo = null;
            if ("http".equalsIgnoreCase(scheme)) {
                nonProxyInfo = NonProxyInfo.httpNonProxyInfo;
            } else if ("https".equalsIgnoreCase(scheme)) {
                nonProxyInfo = NonProxyInfo.httpsNonProxyInfo;
            } else if ("ftp".equalsIgnoreCase(scheme)) {
                nonProxyInfo = NonProxyInfo.ftpNonProxyInfo;
            } else if ("socket".equalsIgnoreCase(scheme)) {
                nonProxyInfo = NonProxyInfo.socksNonProxyInfo;
            }
            final String lowerCase = host.toLowerCase();
            arrayList.add((Proxy) AccessController.doPrivileged(new PrivilegedAction() {
                /* class sun.net.spi.DefaultProxySelector.AnonymousClass1 */

                @Override // java.security.PrivilegedAction
                public Proxy run() {
                    int i = 0;
                    while (true) {
                        String[][] strArr = DefaultProxySelector.props;
                        if (i >= strArr.length) {
                            return Proxy.NO_PROXY;
                        }
                        if (strArr[i][0].equalsIgnoreCase(scheme)) {
                            String str = null;
                            int i2 = 1;
                            while (i2 < DefaultProxySelector.props[i].length) {
                                str = NetProperties.get(DefaultProxySelector.props[i][i2] + "Host");
                                if (!(str == null || str.length() == 0)) {
                                    break;
                                }
                                i2++;
                            }
                            if (str == null || str.length() == 0) {
                                return Proxy.NO_PROXY;
                            }
                            NonProxyInfo nonProxyInfo = nonProxyInfo;
                            if (nonProxyInfo != null) {
                                String str2 = NetProperties.get(nonProxyInfo.property);
                                synchronized (nonProxyInfo) {
                                    if (str2 == null) {
                                        if (nonProxyInfo.defaultVal != null) {
                                            str2 = nonProxyInfo.defaultVal;
                                        } else {
                                            nonProxyInfo.hostsSource = null;
                                            nonProxyInfo.pattern = null;
                                        }
                                    } else if (str2.length() != 0) {
                                        str2 = str2 + "|localhost|127.*|[::1]|0.0.0.0|[::0]";
                                    }
                                    if (str2 != null && !str2.equals(nonProxyInfo.hostsSource)) {
                                        nonProxyInfo.pattern = DefaultProxySelector.toPattern(str2);
                                        nonProxyInfo.hostsSource = str2;
                                    }
                                    if (DefaultProxySelector.shouldNotUseProxyFor(nonProxyInfo.pattern, lowerCase)) {
                                        return Proxy.NO_PROXY;
                                    }
                                }
                            }
                            int intValue = NetProperties.getInteger(DefaultProxySelector.props[i][i2] + "Port", 0).intValue();
                            if (intValue == 0 && i2 < DefaultProxySelector.props[i].length - 1) {
                                int i3 = intValue;
                                for (int i4 = 1; i4 < DefaultProxySelector.props[i].length - 1; i4++) {
                                    if (i4 != i2 && i3 == 0) {
                                        i3 = NetProperties.getInteger(DefaultProxySelector.props[i][i4] + "Port", 0).intValue();
                                    }
                                }
                                intValue = i3;
                            }
                            if (intValue == 0) {
                                if (i2 == DefaultProxySelector.props[i].length - 1) {
                                    intValue = DefaultProxySelector.this.defaultPort("socket");
                                } else {
                                    intValue = DefaultProxySelector.this.defaultPort(scheme);
                                }
                            }
                            InetSocketAddress createUnresolved = InetSocketAddress.createUnresolved(str, intValue);
                            if (i2 == DefaultProxySelector.props[i].length - 1) {
                                return SocksProxy.create(createUnresolved, NetProperties.getInteger("socksProxyVersion", 5).intValue());
                            }
                            return new Proxy(Proxy.Type.HTTP, createUnresolved);
                        }
                        i++;
                    }
                }
            }));
            return arrayList;
        }
        throw new IllegalArgumentException("URI can't be null.");
    }

    @Override // java.net.ProxySelector
    public void connectFailed(URI uri, SocketAddress socketAddress, IOException iOException) {
        if (uri == null || socketAddress == null || iOException == null) {
            throw new IllegalArgumentException("Arguments can't be null.");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private int defaultPort(String str) {
        if ("http".equalsIgnoreCase(str)) {
            return 80;
        }
        if ("https".equalsIgnoreCase(str)) {
            return 443;
        }
        if ("ftp".equalsIgnoreCase(str)) {
            return 80;
        }
        if ("socket".equalsIgnoreCase(str)) {
            return 1080;
        }
        if ("gopher".equalsIgnoreCase(str)) {
            return 80;
        }
        return -1;
    }

    static boolean shouldNotUseProxyFor(Pattern pattern, String str) {
        if (pattern == null || str.isEmpty()) {
            return false;
        }
        return pattern.matcher(str).matches();
    }

    static Pattern toPattern(String str) {
        StringJoiner stringJoiner = new StringJoiner("|");
        String[] split = str.split("\\|");
        boolean z = true;
        for (String str2 : split) {
            if (!str2.isEmpty()) {
                stringJoiner.add(disjunctToRegex(str2.toLowerCase()));
                z = false;
            }
        }
        if (z) {
            return null;
        }
        return Pattern.compile(stringJoiner.toString());
    }

    static String disjunctToRegex(String str) {
        if (str.startsWith("*")) {
            return ".*" + Pattern.quote(str.substring(1));
        } else if (!str.endsWith("*")) {
            return Pattern.quote(str);
        } else {
            return Pattern.quote(str.substring(0, str.length() - 1)) + ".*";
        }
    }
}
