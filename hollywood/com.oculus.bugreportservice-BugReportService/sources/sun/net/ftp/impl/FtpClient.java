package sun.net.ftp.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.SSLSocketFactory;
import sun.net.TelnetInputStream;
import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpDirParser;
import sun.net.ftp.FtpProtocolException;
import sun.net.ftp.FtpReplyCode;
import sun.util.logging.PlatformLogger;

public class FtpClient extends sun.net.ftp.FtpClient {
    private static String[] MDTMformats = {"yyyyMMddHHmmss.SSS", "yyyyMMddHHmmss"};
    private static SimpleDateFormat[] dateFormats = new SimpleDateFormat[MDTMformats.length];
    private static int defaultConnectTimeout;
    private static int defaultSoTimeout;
    private static String encoding;
    private static Pattern epsvPat = null;
    private static Pattern linkp = Pattern.compile("(\\p{Print}+) \\-\\> (\\p{Print}+)$");
    private static final PlatformLogger logger = PlatformLogger.getLogger("sun.net.ftp.FtpClient");
    private static Pattern pasvPat = null;
    private static String[] patStrings = {"([\\-ld](?:[r\\-][w\\-][x\\-]){3})\\s*\\d+ (\\w+)\\s*(\\w+)\\s*(\\d+)\\s*([A-Z][a-z][a-z]\\s*\\d+)\\s*(\\d\\d:\\d\\d)\\s*(\\p{Print}*)", "([\\-ld](?:[r\\-][w\\-][x\\-]){3})\\s*\\d+ (\\w+)\\s*(\\w+)\\s*(\\d+)\\s*([A-Z][a-z][a-z]\\s*\\d+)\\s*(\\d{4})\\s*(\\p{Print}*)", "(\\d{2}/\\d{2}/\\d{4})\\s*(\\d{2}:\\d{2}[ap])\\s*((?:[0-9,]+)|(?:<DIR>))\\s*(\\p{Graph}*)", "(\\d{2}-\\d{2}-\\d{2})\\s*(\\d{2}:\\d{2}[AP]M)\\s*((?:[0-9,]+)|(?:<DIR>))\\s*(\\p{Graph}*)"};
    private static int[][] patternGroups = {new int[]{7, 4, 5, 6, 0, 1, 2, 3}, new int[]{7, 4, 5, 0, 6, 1, 2, 3}, new int[]{4, 3, 1, 2, 0, 0, 0, 0}, new int[]{4, 3, 1, 2, 0, 0, 0, 0}};
    private static Pattern[] patterns = new Pattern[patStrings.length];
    private static Pattern transPat = null;
    private int connectTimeout = -1;
    private DateFormat df = DateFormat.getDateInstance(2, Locale.US);
    private InputStream in;
    private String lastFileName;
    private FtpReplyCode lastReplyCode = null;
    private long lastTransSize = -1;
    private boolean loggedIn = false;
    private FtpDirParser mlsxParser = new MLSxParser();
    private PrintStream out;
    private FtpDirParser parser = new DefaultParser();
    private final boolean passiveMode = true;
    private Proxy proxy;
    private int readTimeout = -1;
    private boolean replyPending = false;
    private long restartOffset = 0;
    private Socket server;
    private InetSocketAddress serverAddr;
    private Vector serverResponse = new Vector(1);
    private SSLSocketFactory sslFact;
    private FtpClient.TransferType type = FtpClient.TransferType.BINARY;
    private boolean useCrypto = false;
    private String welcomeMsg;

    static {
        encoding = "ISO8859_1";
        int i = 0;
        final int[] iArr = {0, 0};
        final String[] strArr = {null};
        AccessController.doPrivileged(new PrivilegedAction() {
            /* class sun.net.ftp.impl.FtpClient.AnonymousClass1 */

            @Override // java.security.PrivilegedAction
            public Object run() {
                iArr[0] = Integer.getInteger("sun.net.client.defaultReadTimeout", 0).intValue();
                iArr[1] = Integer.getInteger("sun.net.client.defaultConnectTimeout", 0).intValue();
                strArr[0] = System.getProperty("file.encoding", "ISO8859_1");
                return null;
            }
        });
        if (iArr[0] == 0) {
            defaultSoTimeout = -1;
        } else {
            defaultSoTimeout = iArr[0];
        }
        if (iArr[1] == 0) {
            defaultConnectTimeout = -1;
        } else {
            defaultConnectTimeout = iArr[1];
        }
        encoding = strArr[0];
        try {
            if (!isASCIISuperset(encoding)) {
                encoding = "ISO8859_1";
            }
        } catch (Exception unused) {
            encoding = "ISO8859_1";
        }
        int i2 = 0;
        while (true) {
            String[] strArr2 = patStrings;
            if (i2 >= strArr2.length) {
                break;
            }
            patterns[i2] = Pattern.compile(strArr2[i2]);
            i2++;
        }
        while (true) {
            String[] strArr3 = MDTMformats;
            if (i < strArr3.length) {
                dateFormats[i] = new SimpleDateFormat(strArr3[i]);
                dateFormats[i].setTimeZone(TimeZone.getTimeZone("GMT"));
                i++;
            } else {
                return;
            }
        }
    }

    private static boolean isASCIISuperset(String str) {
        return Arrays.equals("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz-_.!~*'();/?:@&=+$,".getBytes(str), new byte[]{48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 45, 95, 46, 33, 126, 42, 39, 40, 41, 59, 47, 63, 58, 64, 38, 61, 43, 36, 44});
    }

    private class DefaultParser implements FtpDirParser {
        private DefaultParser() {
        }
    }

    private class MLSxParser implements FtpDirParser {
        private SimpleDateFormat df;

        private MLSxParser() {
            this.df = new SimpleDateFormat("yyyyMMddhhmmss");
        }
    }

    private void getTransferSize() {
        this.lastTransSize = -1;
        String lastResponseString = getLastResponseString();
        if (transPat == null) {
            transPat = Pattern.compile("150 Opening .*\\((\\d+) bytes\\).");
        }
        Matcher matcher = transPat.matcher(lastResponseString);
        if (matcher.find()) {
            this.lastTransSize = Long.parseLong(matcher.group(1));
        }
    }

    private void getTransferName() {
        this.lastFileName = null;
        String lastResponseString = getLastResponseString();
        int indexOf = lastResponseString.indexOf("unique file name:");
        int lastIndexOf = lastResponseString.lastIndexOf(41);
        if (indexOf >= 0) {
            this.lastFileName = lastResponseString.substring(indexOf + 17, lastIndexOf);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0089  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int readServerResponse() {
        /*
        // Method dump skipped, instructions count: 153
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.net.ftp.impl.FtpClient.readServerResponse():int");
    }

    private void sendServer(String str) {
        this.out.print(str);
        if (logger.isLoggable(PlatformLogger.Level.FINEST)) {
            PlatformLogger platformLogger = logger;
            platformLogger.finest("Server [" + this.serverAddr + "] <-- " + str);
        }
    }

    private String getResponseString() {
        return (String) this.serverResponse.elementAt(0);
    }

    private boolean readReply() {
        this.lastReplyCode = FtpReplyCode.find(readServerResponse());
        if (this.lastReplyCode.isPositivePreliminary()) {
            this.replyPending = true;
            return true;
        } else if (!this.lastReplyCode.isPositiveCompletion() && !this.lastReplyCode.isPositiveIntermediate()) {
            return false;
        } else {
            if (this.lastReplyCode == FtpReplyCode.CLOSING_DATA_CONNECTION) {
                getTransferName();
            }
            return true;
        }
    }

    private boolean issueCommand(String str) {
        if (isConnected()) {
            if (this.replyPending) {
                try {
                    completePending();
                } catch (FtpProtocolException unused) {
                }
            }
            if (str.indexOf(10) == -1) {
                sendServer(str + "\r\n");
                return readReply();
            }
            FtpProtocolException ftpProtocolException = new FtpProtocolException("Illegal FTP command");
            ftpProtocolException.initCause(new IllegalArgumentException("Illegal carriage return"));
            throw ftpProtocolException;
        }
        throw new IllegalStateException("Not connected");
    }

    private void issueCommandCheck(String str) {
        if (!issueCommand(str)) {
            throw new FtpProtocolException(str + ":" + getResponseString(), getLastReplyCode());
        }
    }

    private Socket openPassiveDataConnection(String str) {
        InetSocketAddress inetSocketAddress;
        Socket socket;
        if (issueCommand("EPSV ALL")) {
            issueCommandCheck("EPSV");
            String responseString = getResponseString();
            if (epsvPat == null) {
                epsvPat = Pattern.compile("^229 .* \\(\\|\\|\\|(\\d+)\\|\\)");
            }
            Matcher matcher = epsvPat.matcher(responseString);
            if (matcher.find()) {
                int parseInt = Integer.parseInt(matcher.group(1));
                InetAddress inetAddress = this.server.getInetAddress();
                if (inetAddress != null) {
                    inetSocketAddress = new InetSocketAddress(inetAddress, parseInt);
                } else {
                    inetSocketAddress = InetSocketAddress.createUnresolved(this.serverAddr.getHostName(), parseInt);
                }
            } else {
                throw new FtpProtocolException("EPSV failed : " + responseString);
            }
        } else {
            issueCommandCheck("PASV");
            String responseString2 = getResponseString();
            if (pasvPat == null) {
                pasvPat = Pattern.compile("227 .* \\(?(\\d{1,3},\\d{1,3},\\d{1,3},\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\)?");
            }
            Matcher matcher2 = pasvPat.matcher(responseString2);
            if (matcher2.find()) {
                inetSocketAddress = new InetSocketAddress(matcher2.group(1).replace(',', '.'), Integer.parseInt(matcher2.group(3)) + (Integer.parseInt(matcher2.group(2)) << 8));
            } else {
                throw new FtpProtocolException("PASV failed : " + responseString2);
            }
        }
        Proxy proxy2 = this.proxy;
        if (proxy2 == null) {
            socket = new Socket();
        } else if (proxy2.type() == Proxy.Type.SOCKS) {
            socket = (Socket) AccessController.doPrivileged(new PrivilegedAction() {
                /* class sun.net.ftp.impl.FtpClient.AnonymousClass2 */

                @Override // java.security.PrivilegedAction
                public Socket run() {
                    return new Socket(FtpClient.this.proxy);
                }
            });
        } else {
            socket = new Socket(Proxy.NO_PROXY);
        }
        socket.bind(new InetSocketAddress((InetAddress) AccessController.doPrivileged(new PrivilegedAction() {
            /* class sun.net.ftp.impl.FtpClient.AnonymousClass3 */

            @Override // java.security.PrivilegedAction
            public InetAddress run() {
                return FtpClient.this.server.getLocalAddress();
            }
        }), 0));
        int i = this.connectTimeout;
        if (i >= 0) {
            socket.connect(inetSocketAddress, i);
        } else {
            int i2 = defaultConnectTimeout;
            if (i2 > 0) {
                socket.connect(inetSocketAddress, i2);
            } else {
                socket.connect(inetSocketAddress);
            }
        }
        int i3 = this.readTimeout;
        if (i3 >= 0) {
            socket.setSoTimeout(i3);
        } else {
            int i4 = defaultSoTimeout;
            if (i4 > 0) {
                socket.setSoTimeout(i4);
            }
        }
        if (this.useCrypto) {
            try {
                socket = this.sslFact.createSocket(socket, inetSocketAddress.getHostName(), inetSocketAddress.getPort(), true);
            } catch (Exception e) {
                throw new FtpProtocolException("Can't open secure data channel: " + e);
            }
        }
        if (issueCommand(str)) {
            return socket;
        }
        socket.close();
        if (getLastReplyCode() == FtpReplyCode.FILE_UNAVAILABLE) {
            throw new FileNotFoundException(str);
        }
        throw new FtpProtocolException(str + ":" + getResponseString(), getLastReplyCode());
    }

    private Socket openDataConnection(String str) {
        ServerSocket serverSocket;
        try {
            return openPassiveDataConnection(str);
        } catch (FtpProtocolException e) {
            String message = e.getMessage();
            if (message.startsWith("PASV") || message.startsWith("EPSV")) {
                Proxy proxy2 = this.proxy;
                if (proxy2 == null || proxy2.type() != Proxy.Type.SOCKS) {
                    serverSocket = new ServerSocket(0, 1, this.server.getLocalAddress());
                    InetAddress inetAddress = serverSocket.getInetAddress();
                    if (inetAddress.isAnyLocalAddress()) {
                        inetAddress = this.server.getLocalAddress();
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("EPRT |");
                    sb.append(inetAddress instanceof Inet6Address ? "2" : "1");
                    sb.append("|");
                    sb.append(inetAddress.getHostAddress());
                    sb.append("|");
                    sb.append(serverSocket.getLocalPort());
                    sb.append("|");
                    if (!this.issueCommand(sb.toString()) || !this.issueCommand(str)) {
                        String str2 = "PORT ";
                        byte[] address = inetAddress.getAddress();
                        for (int i = 0; i < address.length; i++) {
                            str2 = str2 + (address[i] & 255) + ",";
                        }
                        this.issueCommandCheck(str2 + ((serverSocket.getLocalPort() >>> 8) & 255) + "," + (serverSocket.getLocalPort() & 255));
                        this.issueCommandCheck(str);
                    }
                    if (this.connectTimeout >= 0) {
                        serverSocket.setSoTimeout(this.connectTimeout);
                    } else if (defaultConnectTimeout > 0) {
                        serverSocket.setSoTimeout(defaultConnectTimeout);
                    }
                    Socket accept = serverSocket.accept();
                    if (this.readTimeout >= 0) {
                        accept.setSoTimeout(this.readTimeout);
                    } else if (defaultSoTimeout > 0) {
                        accept.setSoTimeout(defaultSoTimeout);
                    }
                    serverSocket.close();
                    if (!this.useCrypto) {
                        return accept;
                    }
                    try {
                        return this.sslFact.createSocket(accept, this.serverAddr.getHostName(), this.serverAddr.getPort(), true);
                    } catch (Exception e2) {
                        throw new IOException(e2.getLocalizedMessage());
                    }
                } else {
                    throw new FtpProtocolException("Passive mode failed");
                }
            } else {
                throw e;
            }
        } catch (Throwable th) {
            serverSocket.close();
            throw th;
        }
    }

    private InputStream createInputStream(InputStream inputStream) {
        return this.type == FtpClient.TransferType.ASCII ? new TelnetInputStream(inputStream, false) : inputStream;
    }

    protected FtpClient() {
    }

    public static sun.net.ftp.FtpClient create() {
        return new FtpClient();
    }

    @Override // sun.net.ftp.FtpClient
    public sun.net.ftp.FtpClient setConnectTimeout(int i) {
        this.connectTimeout = i;
        return this;
    }

    @Override // sun.net.ftp.FtpClient
    public sun.net.ftp.FtpClient setReadTimeout(int i) {
        this.readTimeout = i;
        return this;
    }

    @Override // sun.net.ftp.FtpClient
    public sun.net.ftp.FtpClient setProxy(Proxy proxy2) {
        this.proxy = proxy2;
        return this;
    }

    private void tryConnect(InetSocketAddress inetSocketAddress, int i) {
        if (isConnected()) {
            disconnect();
        }
        this.server = doConnect(inetSocketAddress, i);
        try {
            this.out = new PrintStream((OutputStream) new BufferedOutputStream(this.server.getOutputStream()), true, encoding);
            this.in = new BufferedInputStream(this.server.getInputStream());
        } catch (UnsupportedEncodingException e) {
            throw new InternalError(encoding + "encoding not found", e);
        }
    }

    private Socket doConnect(InetSocketAddress inetSocketAddress, int i) {
        Socket socket;
        Proxy proxy2 = this.proxy;
        if (proxy2 == null) {
            socket = new Socket();
        } else if (proxy2.type() == Proxy.Type.SOCKS) {
            socket = (Socket) AccessController.doPrivileged(new PrivilegedAction() {
                /* class sun.net.ftp.impl.FtpClient.AnonymousClass4 */

                @Override // java.security.PrivilegedAction
                public Socket run() {
                    return new Socket(FtpClient.this.proxy);
                }
            });
        } else {
            socket = new Socket(Proxy.NO_PROXY);
        }
        if (i >= 0) {
            socket.connect(inetSocketAddress, i);
        } else {
            int i2 = this.connectTimeout;
            if (i2 >= 0) {
                socket.connect(inetSocketAddress, i2);
            } else {
                int i3 = defaultConnectTimeout;
                if (i3 > 0) {
                    socket.connect(inetSocketAddress, i3);
                } else {
                    socket.connect(inetSocketAddress);
                }
            }
        }
        int i4 = this.readTimeout;
        if (i4 >= 0) {
            socket.setSoTimeout(i4);
        } else {
            int i5 = defaultSoTimeout;
            if (i5 > 0) {
                socket.setSoTimeout(i5);
            }
        }
        return socket;
    }

    private void disconnect() {
        if (isConnected()) {
            this.server.close();
        }
        this.server = null;
        this.in = null;
        this.out = null;
        this.lastTransSize = -1;
        this.lastFileName = null;
        this.restartOffset = 0;
        this.welcomeMsg = null;
        this.lastReplyCode = null;
        this.serverResponse.setSize(0);
    }

    public boolean isConnected() {
        return this.server != null;
    }

    @Override // sun.net.ftp.FtpClient
    public sun.net.ftp.FtpClient connect(SocketAddress socketAddress) {
        connect(socketAddress, -1);
        return this;
    }

    public sun.net.ftp.FtpClient connect(SocketAddress socketAddress, int i) {
        if (socketAddress instanceof InetSocketAddress) {
            this.serverAddr = (InetSocketAddress) socketAddress;
            tryConnect(this.serverAddr, i);
            if (readReply()) {
                this.welcomeMsg = getResponseString().substring(4);
                return this;
            }
            throw new FtpProtocolException("Welcome message: " + getResponseString(), this.lastReplyCode);
        }
        throw new IllegalArgumentException("Wrong address type");
    }

    private void tryLogin(String str, char[] cArr) {
        issueCommandCheck("USER " + str);
        if (this.lastReplyCode == FtpReplyCode.NEED_PASSWORD && cArr != null && cArr.length > 0) {
            new StringBuilder().append("PASS ");
            String.valueOf(cArr);
            throw null;
        }
    }

    @Override // sun.net.ftp.FtpClient
    public sun.net.ftp.FtpClient login(String str, char[] cArr) {
        if (!isConnected()) {
            throw new FtpProtocolException("Not connected yet", FtpReplyCode.BAD_SEQUENCE);
        } else if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("User name can't be null or empty");
        } else {
            tryLogin(str, cArr);
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < this.serverResponse.size(); i++) {
                String str2 = (String) this.serverResponse.elementAt(i);
                if (str2 != null) {
                    if (str2.length() >= 4 && str2.startsWith("230")) {
                        str2 = str2.substring(4);
                    }
                    stringBuffer.append(str2);
                }
            }
            this.welcomeMsg = stringBuffer.toString();
            this.loggedIn = true;
            return this;
        }
    }

    @Override // sun.net.ftp.FtpClient, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (isConnected()) {
            try {
                issueCommand("QUIT");
            } catch (FtpProtocolException unused) {
            }
            this.loggedIn = false;
        }
        disconnect();
    }

    @Override // sun.net.ftp.FtpClient
    public sun.net.ftp.FtpClient changeDirectory(String str) {
        if (str == null || "".equals(str)) {
            throw new IllegalArgumentException("directory can't be null or empty");
        }
        issueCommandCheck("CWD " + str);
        return this;
    }

    @Override // sun.net.ftp.FtpClient
    public InputStream getFileStream(String str) {
        if (this.restartOffset > 0) {
            try {
                Socket openDataConnection = openDataConnection("REST " + this.restartOffset);
                if (openDataConnection == null) {
                    return null;
                }
                issueCommandCheck("RETR " + str);
                getTransferSize();
                return createInputStream(openDataConnection.getInputStream());
            } finally {
                this.restartOffset = 0;
            }
        } else {
            Socket openDataConnection2 = openDataConnection("RETR " + str);
            if (openDataConnection2 == null) {
                return null;
            }
            getTransferSize();
            return createInputStream(openDataConnection2.getInputStream());
        }
    }

    public sun.net.ftp.FtpClient completePending() {
        while (this.replyPending) {
            this.replyPending = false;
            if (!readReply()) {
                throw new FtpProtocolException(getLastResponseString(), this.lastReplyCode);
            }
        }
        return this;
    }

    @Override // sun.net.ftp.FtpClient
    public sun.net.ftp.FtpClient setType(FtpClient.TransferType transferType) {
        this.type = transferType;
        String str = transferType == FtpClient.TransferType.ASCII ? "TYPE A" : "NOOP";
        if (transferType == FtpClient.TransferType.BINARY) {
            str = "TYPE I";
        }
        if (transferType == FtpClient.TransferType.EBCDIC) {
            str = "TYPE E";
        }
        issueCommandCheck(str);
        return this;
    }

    @Override // sun.net.ftp.FtpClient
    public InputStream list(String str) {
        String str2;
        if (str == null) {
            str2 = "LIST";
        } else {
            str2 = "LIST " + str;
        }
        Socket openDataConnection = openDataConnection(str2);
        if (openDataConnection != null) {
            return createInputStream(openDataConnection.getInputStream());
        }
        return null;
    }

    @Override // sun.net.ftp.FtpClient
    public InputStream nameList(String str) {
        String str2;
        if (str == null) {
            str2 = "NLST";
        } else {
            str2 = "NLST " + str;
        }
        Socket openDataConnection = openDataConnection(str2);
        if (openDataConnection != null) {
            return createInputStream(openDataConnection.getInputStream());
        }
        return null;
    }

    public FtpReplyCode getLastReplyCode() {
        return this.lastReplyCode;
    }

    public String getLastResponseString() {
        StringBuffer stringBuffer = new StringBuffer();
        Vector vector = this.serverResponse;
        if (vector != null) {
            Iterator it = vector.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                if (str != null) {
                    stringBuffer.append(str);
                }
            }
        }
        return stringBuffer.toString();
    }

    @Override // sun.net.ftp.FtpClient
    public long getLastTransferSize() {
        return this.lastTransSize;
    }
}
