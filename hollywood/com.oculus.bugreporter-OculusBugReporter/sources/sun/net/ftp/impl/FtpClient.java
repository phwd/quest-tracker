package sun.net.ftp.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectStreamConstants;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import sun.net.TelnetInputStream;
import sun.net.TelnetOutputStream;
import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpDirEntry;
import sun.net.ftp.FtpDirParser;
import sun.net.ftp.FtpProtocolException;
import sun.net.ftp.FtpReplyCode;
import sun.security.util.DerValue;
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
    private Socket oldSocket;
    private PrintStream out;
    private FtpDirParser parser = new DefaultParser();
    private final boolean passiveMode = true;
    private Proxy proxy;
    private int readTimeout = -1;
    private boolean replyPending = false;
    private long restartOffset = 0;
    private Socket server;
    private InetSocketAddress serverAddr;
    private Vector<String> serverResponse = new Vector<>(1);
    private SSLSocketFactory sslFact;
    private FtpClient.TransferType type = FtpClient.TransferType.BINARY;
    private boolean useCrypto = false;
    private String welcomeMsg;

    static {
        encoding = "ISO8859_1";
        final int[] vals = {0, 0};
        final String[] encs = {null};
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            /* class sun.net.ftp.impl.FtpClient.AnonymousClass1 */

            @Override // java.security.PrivilegedAction
            public Object run() {
                vals[0] = Integer.getInteger("sun.net.client.defaultReadTimeout", 0).intValue();
                vals[1] = Integer.getInteger("sun.net.client.defaultConnectTimeout", 0).intValue();
                encs[0] = System.getProperty("file.encoding", "ISO8859_1");
                return null;
            }
        });
        if (vals[0] == 0) {
            defaultSoTimeout = -1;
        } else {
            defaultSoTimeout = vals[0];
        }
        if (vals[1] == 0) {
            defaultConnectTimeout = -1;
        } else {
            defaultConnectTimeout = vals[1];
        }
        encoding = encs[0];
        try {
            if (!isASCIISuperset(encoding)) {
                encoding = "ISO8859_1";
            }
        } catch (Exception e) {
            encoding = "ISO8859_1";
        }
        int i = 0;
        while (true) {
            String[] strArr = patStrings;
            if (i >= strArr.length) {
                break;
            }
            patterns[i] = Pattern.compile(strArr[i]);
            i++;
        }
        int i2 = 0;
        while (true) {
            String[] strArr2 = MDTMformats;
            if (i2 < strArr2.length) {
                dateFormats[i2] = new SimpleDateFormat(strArr2[i2]);
                dateFormats[i2].setTimeZone(TimeZone.getTimeZone("GMT"));
                i2++;
            } else {
                return;
            }
        }
    }

    private static boolean isASCIISuperset(String encoding2) throws Exception {
        return Arrays.equals("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz-_.!~*'();/?:@&=+$,".getBytes(encoding2), new byte[]{48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, ObjectStreamConstants.TC_REFERENCE, ObjectStreamConstants.TC_CLASSDESC, ObjectStreamConstants.TC_OBJECT, ObjectStreamConstants.TC_STRING, ObjectStreamConstants.TC_ARRAY, ObjectStreamConstants.TC_CLASS, ObjectStreamConstants.TC_BLOCKDATA, ObjectStreamConstants.TC_ENDBLOCKDATA, ObjectStreamConstants.TC_RESET, ObjectStreamConstants.TC_BLOCKDATALONG, 45, 95, 46, 33, 126, 42, 39, 40, 41, 59, 47, 63, 58, DerValue.TAG_APPLICATION, 38, 61, 43, 36, 44});
    }

    private class DefaultParser implements FtpDirParser {
        private DefaultParser() {
        }

        @Override // sun.net.ftp.FtpDirParser
        public FtpDirEntry parseLine(String line) {
            Date d;
            Date d2;
            String filename = null;
            Calendar now = Calendar.getInstance();
            int year = now.get(1);
            boolean dir = false;
            String groupname = null;
            String username = null;
            String permstring = null;
            String time = null;
            String fsize = null;
            String fdate = null;
            for (int j = 0; j < FtpClient.patterns.length; j++) {
                Matcher m = FtpClient.patterns[j].matcher(line);
                if (m.find()) {
                    String filename2 = m.group(FtpClient.patternGroups[j][0]);
                    fsize = m.group(FtpClient.patternGroups[j][1]);
                    fdate = m.group(FtpClient.patternGroups[j][2]);
                    if (FtpClient.patternGroups[j][4] > 0) {
                        fdate = fdate + ", " + m.group(FtpClient.patternGroups[j][4]);
                    } else if (FtpClient.patternGroups[j][3] > 0) {
                        fdate = fdate + ", " + String.valueOf(year);
                    }
                    if (FtpClient.patternGroups[j][3] > 0) {
                        time = m.group(FtpClient.patternGroups[j][3]);
                    }
                    if (FtpClient.patternGroups[j][5] > 0) {
                        permstring = m.group(FtpClient.patternGroups[j][5]);
                        dir = permstring.startsWith(android.icu.text.DateFormat.DAY);
                    }
                    if (FtpClient.patternGroups[j][6] > 0) {
                        username = m.group(FtpClient.patternGroups[j][6]);
                    }
                    if (FtpClient.patternGroups[j][7] > 0) {
                        groupname = m.group(FtpClient.patternGroups[j][7]);
                    }
                    if ("<DIR>".equals(fsize)) {
                        fsize = null;
                        dir = true;
                        filename = filename2;
                    } else {
                        filename = filename2;
                    }
                }
            }
            if (filename == null) {
                return null;
            }
            try {
                d = FtpClient.this.df.parse(fdate);
            } catch (Exception e) {
                d = null;
            }
            if (d == null || time == null) {
                d2 = d;
            } else {
                int c = time.indexOf(":");
                now.setTime(d);
                now.set(10, Integer.parseInt(time.substring(0, c)));
                now.set(12, Integer.parseInt(time.substring(c + 1)));
                d2 = now.getTime();
            }
            Matcher m2 = FtpClient.linkp.matcher(filename);
            if (m2.find()) {
                filename = m2.group(1);
            }
            boolean[][] perms = (boolean[][]) Array.newInstance(boolean.class, 3, 3);
            int i = 0;
            while (true) {
                if (i >= 3) {
                    break;
                }
                int j2 = 0;
                for (int i2 = 3; j2 < i2; i2 = 3) {
                    perms[i][j2] = permstring.charAt((i * 3) + j2) != '-';
                    j2++;
                    permstring = permstring;
                }
                i++;
                time = time;
                now = now;
            }
            FtpDirEntry file = new FtpDirEntry(filename);
            file.setUser(username).setGroup(groupname);
            file.setSize(Long.parseLong(fsize)).setLastModified(d2);
            file.setPermissions(perms);
            file.setType(dir ? FtpDirEntry.Type.DIR : line.charAt(0) == 'l' ? FtpDirEntry.Type.LINK : FtpDirEntry.Type.FILE);
            return file;
        }
    }

    private class MLSxParser implements FtpDirParser {
        private SimpleDateFormat df;

        private MLSxParser() {
            this.df = new SimpleDateFormat("yyyyMMddhhmmss");
        }

        @Override // sun.net.ftp.FtpDirParser
        public FtpDirEntry parseLine(String line) {
            String line2;
            String name;
            String s;
            int i = line.lastIndexOf(";");
            if (i > 0) {
                name = line.substring(i + 1).trim();
                line2 = line.substring(0, i);
            } else {
                name = line.trim();
                line2 = "";
            }
            FtpDirEntry file = new FtpDirEntry(name);
            while (!line2.isEmpty()) {
                int i2 = line2.indexOf(";");
                if (i2 > 0) {
                    s = line2.substring(0, i2);
                    line2 = line2.substring(i2 + 1);
                } else {
                    s = line2;
                    line2 = "";
                }
                int i3 = s.indexOf("=");
                if (i3 > 0) {
                    file.addFact(s.substring(0, i3), s.substring(i3 + 1));
                }
            }
            String s2 = file.getFact("Size");
            if (s2 != null) {
                file.setSize(Long.parseLong(s2));
            }
            String s3 = file.getFact("Modify");
            if (s3 != null) {
                Date d = null;
                try {
                    d = this.df.parse(s3);
                } catch (ParseException e) {
                }
                if (d != null) {
                    file.setLastModified(d);
                }
            }
            String s4 = file.getFact("Create");
            if (s4 != null) {
                Date d2 = null;
                try {
                    d2 = this.df.parse(s4);
                } catch (ParseException e2) {
                }
                if (d2 != null) {
                    file.setCreated(d2);
                }
            }
            String s5 = file.getFact("Type");
            if (s5 != null) {
                if (s5.equalsIgnoreCase("file")) {
                    file.setType(FtpDirEntry.Type.FILE);
                }
                if (s5.equalsIgnoreCase("dir")) {
                    file.setType(FtpDirEntry.Type.DIR);
                }
                if (s5.equalsIgnoreCase("cdir")) {
                    file.setType(FtpDirEntry.Type.CDIR);
                }
                if (s5.equalsIgnoreCase("pdir")) {
                    file.setType(FtpDirEntry.Type.PDIR);
                }
            }
            return file;
        }
    }

    private void getTransferSize() {
        this.lastTransSize = -1;
        String response = getLastResponseString();
        if (transPat == null) {
            transPat = Pattern.compile("150 Opening .*\\((\\d+) bytes\\).");
        }
        Matcher m = transPat.matcher(response);
        if (m.find()) {
            this.lastTransSize = Long.parseLong(m.group(1));
        }
    }

    private void getTransferName() {
        this.lastFileName = null;
        String response = getLastResponseString();
        int i = response.indexOf("unique file name:");
        int e = response.lastIndexOf(41);
        if (i >= 0) {
            this.lastFileName = response.substring(i + 17, e);
        }
    }

    private int readServerResponse() throws IOException {
        int code;
        StringBuffer replyBuf = new StringBuffer(32);
        int continuingCode = -1;
        this.serverResponse.setSize(0);
        while (true) {
            int read = this.in.read();
            int c = read;
            if (read != -1) {
                if (c == 13) {
                    int read2 = this.in.read();
                    c = read2;
                    if (read2 != 10) {
                        replyBuf.append('\r');
                    }
                }
                replyBuf.append((char) c);
                if (c != 10) {
                    continue;
                }
            }
            String response = replyBuf.toString();
            replyBuf.setLength(0);
            if (logger.isLoggable(PlatformLogger.Level.FINEST)) {
                PlatformLogger platformLogger = logger;
                platformLogger.finest("Server [" + ((Object) this.serverAddr) + "] --> " + response);
            }
            if (response.length() == 0) {
                code = -1;
            } else {
                try {
                    code = Integer.parseInt(response.substring(0, 3));
                } catch (NumberFormatException e) {
                    code = -1;
                } catch (StringIndexOutOfBoundsException e2) {
                }
            }
            this.serverResponse.addElement(response);
            if (continuingCode == -1) {
                if (response.length() < 4 || response.charAt(3) != '-') {
                    break;
                }
                continuingCode = code;
            } else if (code == continuingCode && (response.length() < 4 || response.charAt(3) != '-')) {
            }
        }
        return code;
    }

    private void sendServer(String cmd) {
        this.out.print(cmd);
        if (logger.isLoggable(PlatformLogger.Level.FINEST)) {
            PlatformLogger platformLogger = logger;
            platformLogger.finest("Server [" + ((Object) this.serverAddr) + "] <-- " + cmd);
        }
    }

    private String getResponseString() {
        return this.serverResponse.elementAt(0);
    }

    private Vector<String> getResponseStrings() {
        return this.serverResponse;
    }

    private boolean readReply() throws IOException {
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

    private boolean issueCommand(String cmd) throws IOException, FtpProtocolException {
        if (isConnected()) {
            if (this.replyPending) {
                try {
                    completePending();
                } catch (FtpProtocolException e) {
                }
            }
            if (cmd.indexOf(10) == -1) {
                sendServer(cmd + "\r\n");
                return readReply();
            }
            FtpProtocolException ex = new FtpProtocolException("Illegal FTP command");
            ex.initCause(new IllegalArgumentException("Illegal carriage return"));
            throw ex;
        }
        throw new IllegalStateException("Not connected");
    }

    private void issueCommandCheck(String cmd) throws FtpProtocolException, IOException {
        if (!issueCommand(cmd)) {
            throw new FtpProtocolException(cmd + ":" + getResponseString(), getLastReplyCode());
        }
    }

    private Socket openPassiveDataConnection(String cmd) throws FtpProtocolException, IOException {
        InetSocketAddress dest;
        Socket s;
        if (issueCommand("EPSV ALL")) {
            issueCommandCheck("EPSV");
            String serverAnswer = getResponseString();
            if (epsvPat == null) {
                epsvPat = Pattern.compile("^229 .* \\(\\|\\|\\|(\\d+)\\|\\)");
            }
            Matcher m = epsvPat.matcher(serverAnswer);
            if (m.find()) {
                int port = Integer.parseInt(m.group(1));
                InetAddress add = this.server.getInetAddress();
                if (add != null) {
                    dest = new InetSocketAddress(add, port);
                } else {
                    dest = InetSocketAddress.createUnresolved(this.serverAddr.getHostName(), port);
                }
            } else {
                throw new FtpProtocolException("EPSV failed : " + serverAnswer);
            }
        } else {
            issueCommandCheck("PASV");
            String serverAnswer2 = getResponseString();
            if (pasvPat == null) {
                pasvPat = Pattern.compile("227 .* \\(?(\\d{1,3},\\d{1,3},\\d{1,3},\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\)?");
            }
            Matcher m2 = pasvPat.matcher(serverAnswer2);
            if (m2.find()) {
                dest = new InetSocketAddress(m2.group(1).replace(',', '.'), (Integer.parseInt(m2.group(2)) << 8) + Integer.parseInt(m2.group(3)));
            } else {
                throw new FtpProtocolException("PASV failed : " + serverAnswer2);
            }
        }
        Proxy proxy2 = this.proxy;
        if (proxy2 == null) {
            s = new Socket();
        } else if (proxy2.type() == Proxy.Type.SOCKS) {
            s = (Socket) AccessController.doPrivileged(new PrivilegedAction<Socket>() {
                /* class sun.net.ftp.impl.FtpClient.AnonymousClass2 */

                @Override // java.security.PrivilegedAction
                public Socket run() {
                    return new Socket(FtpClient.this.proxy);
                }
            });
        } else {
            s = new Socket(Proxy.NO_PROXY);
        }
        s.bind(new InetSocketAddress((InetAddress) AccessController.doPrivileged(new PrivilegedAction<InetAddress>() {
            /* class sun.net.ftp.impl.FtpClient.AnonymousClass3 */

            @Override // java.security.PrivilegedAction
            public InetAddress run() {
                return FtpClient.this.server.getLocalAddress();
            }
        }), 0));
        int i = this.connectTimeout;
        if (i >= 0) {
            s.connect(dest, i);
        } else {
            int i2 = defaultConnectTimeout;
            if (i2 > 0) {
                s.connect(dest, i2);
            } else {
                s.connect(dest);
            }
        }
        int i3 = this.readTimeout;
        if (i3 >= 0) {
            s.setSoTimeout(i3);
        } else {
            int i4 = defaultSoTimeout;
            if (i4 > 0) {
                s.setSoTimeout(i4);
            }
        }
        if (this.useCrypto) {
            try {
                s = this.sslFact.createSocket(s, dest.getHostName(), dest.getPort(), true);
            } catch (Exception e) {
                throw new FtpProtocolException("Can't open secure data channel: " + ((Object) e));
            }
        }
        if (issueCommand(cmd)) {
            return s;
        }
        s.close();
        if (getLastReplyCode() == FtpReplyCode.FILE_UNAVAILABLE) {
            throw new FileNotFoundException(cmd);
        }
        throw new FtpProtocolException(cmd + ":" + getResponseString(), getLastReplyCode());
    }

    private Socket openDataConnection(String cmd) throws FtpProtocolException, IOException {
        ServerSocket portSocket;
        try {
            return openPassiveDataConnection(cmd);
        } catch (FtpProtocolException e) {
            String errmsg = e.getMessage();
            if (errmsg.startsWith("PASV") || errmsg.startsWith("EPSV")) {
                Proxy proxy2 = this.proxy;
                if (proxy2 == null || proxy2.type() != Proxy.Type.SOCKS) {
                    portSocket = new ServerSocket(0, 1, this.server.getLocalAddress());
                    InetAddress myAddress = portSocket.getInetAddress();
                    if (myAddress.isAnyLocalAddress()) {
                        myAddress = this.server.getLocalAddress();
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("EPRT |");
                    sb.append(myAddress instanceof Inet6Address ? "2" : "1");
                    sb.append("|");
                    sb.append(myAddress.getHostAddress());
                    sb.append("|");
                    sb.append(portSocket.getLocalPort());
                    sb.append("|");
                    if (!issueCommand(sb.toString()) || !issueCommand(cmd)) {
                        String portCmd = "PORT ";
                        byte[] addr = myAddress.getAddress();
                        for (int i = 0; i < addr.length; i++) {
                            portCmd = portCmd + (addr[i] & 255) + ",";
                        }
                        issueCommandCheck(portCmd + ((portSocket.getLocalPort() >>> 8) & 255) + "," + (portSocket.getLocalPort() & 255));
                        issueCommandCheck(cmd);
                    }
                    if (this.connectTimeout >= 0) {
                        portSocket.setSoTimeout(this.connectTimeout);
                    } else if (defaultConnectTimeout > 0) {
                        portSocket.setSoTimeout(defaultConnectTimeout);
                    }
                    Socket clientSocket = portSocket.accept();
                    if (this.readTimeout >= 0) {
                        clientSocket.setSoTimeout(this.readTimeout);
                    } else if (defaultSoTimeout > 0) {
                        clientSocket.setSoTimeout(defaultSoTimeout);
                    }
                    portSocket.close();
                    if (!this.useCrypto) {
                        return clientSocket;
                    }
                    try {
                        return this.sslFact.createSocket(clientSocket, this.serverAddr.getHostName(), this.serverAddr.getPort(), true);
                    } catch (Exception ex) {
                        throw new IOException(ex.getLocalizedMessage());
                    }
                } else {
                    throw new FtpProtocolException("Passive mode failed");
                }
            } else {
                throw e;
            }
        } catch (Throwable th) {
            portSocket.close();
            throw th;
        }
    }

    private InputStream createInputStream(InputStream in2) {
        if (this.type == FtpClient.TransferType.ASCII) {
            return new TelnetInputStream(in2, false);
        }
        return in2;
    }

    private OutputStream createOutputStream(OutputStream out2) {
        if (this.type == FtpClient.TransferType.ASCII) {
            return new TelnetOutputStream(out2, false);
        }
        return out2;
    }

    protected FtpClient() {
    }

    public static sun.net.ftp.FtpClient create() {
        return new FtpClient();
    }

    @Override // sun.net.ftp.FtpClient
    public sun.net.ftp.FtpClient enablePassiveMode(boolean passive) {
        return this;
    }

    @Override // sun.net.ftp.FtpClient
    public boolean isPassiveModeEnabled() {
        return true;
    }

    @Override // sun.net.ftp.FtpClient
    public sun.net.ftp.FtpClient setConnectTimeout(int timeout) {
        this.connectTimeout = timeout;
        return this;
    }

    @Override // sun.net.ftp.FtpClient
    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    @Override // sun.net.ftp.FtpClient
    public sun.net.ftp.FtpClient setReadTimeout(int timeout) {
        this.readTimeout = timeout;
        return this;
    }

    @Override // sun.net.ftp.FtpClient
    public int getReadTimeout() {
        return this.readTimeout;
    }

    @Override // sun.net.ftp.FtpClient
    public sun.net.ftp.FtpClient setProxy(Proxy p) {
        this.proxy = p;
        return this;
    }

    @Override // sun.net.ftp.FtpClient
    public Proxy getProxy() {
        return this.proxy;
    }

    private void tryConnect(InetSocketAddress dest, int timeout) throws IOException {
        if (isConnected()) {
            disconnect();
        }
        this.server = doConnect(dest, timeout);
        try {
            this.out = new PrintStream((OutputStream) new BufferedOutputStream(this.server.getOutputStream()), true, encoding);
            this.in = new BufferedInputStream(this.server.getInputStream());
        } catch (UnsupportedEncodingException e) {
            throw new InternalError(encoding + "encoding not found", e);
        }
    }

    private Socket doConnect(InetSocketAddress dest, int timeout) throws IOException {
        Socket s;
        Proxy proxy2 = this.proxy;
        if (proxy2 == null) {
            s = new Socket();
        } else if (proxy2.type() == Proxy.Type.SOCKS) {
            s = (Socket) AccessController.doPrivileged(new PrivilegedAction<Socket>() {
                /* class sun.net.ftp.impl.FtpClient.AnonymousClass4 */

                @Override // java.security.PrivilegedAction
                public Socket run() {
                    return new Socket(FtpClient.this.proxy);
                }
            });
        } else {
            s = new Socket(Proxy.NO_PROXY);
        }
        if (timeout >= 0) {
            s.connect(dest, timeout);
        } else {
            int i = this.connectTimeout;
            if (i >= 0) {
                s.connect(dest, i);
            } else {
                int i2 = defaultConnectTimeout;
                if (i2 > 0) {
                    s.connect(dest, i2);
                } else {
                    s.connect(dest);
                }
            }
        }
        int i3 = this.readTimeout;
        if (i3 >= 0) {
            s.setSoTimeout(i3);
        } else {
            int i4 = defaultSoTimeout;
            if (i4 > 0) {
                s.setSoTimeout(i4);
            }
        }
        return s;
    }

    private void disconnect() throws IOException {
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

    @Override // sun.net.ftp.FtpClient
    public boolean isConnected() {
        return this.server != null;
    }

    @Override // sun.net.ftp.FtpClient
    public SocketAddress getServerAddress() {
        Socket socket = this.server;
        if (socket == null) {
            return null;
        }
        return socket.getRemoteSocketAddress();
    }

    @Override // sun.net.ftp.FtpClient
    public sun.net.ftp.FtpClient connect(SocketAddress dest) throws FtpProtocolException, IOException {
        return connect(dest, -1);
    }

    @Override // sun.net.ftp.FtpClient
    public sun.net.ftp.FtpClient connect(SocketAddress dest, int timeout) throws FtpProtocolException, IOException {
        if (dest instanceof InetSocketAddress) {
            this.serverAddr = (InetSocketAddress) dest;
            tryConnect(this.serverAddr, timeout);
            if (readReply()) {
                this.welcomeMsg = getResponseString().substring(4);
                return this;
            }
            throw new FtpProtocolException("Welcome message: " + getResponseString(), this.lastReplyCode);
        }
        throw new IllegalArgumentException("Wrong address type");
    }

    private void tryLogin(String user, char[] password) throws FtpProtocolException, IOException {
        issueCommandCheck("USER " + user);
        if (this.lastReplyCode == FtpReplyCode.NEED_PASSWORD && password != null && password.length > 0) {
            issueCommandCheck("PASS " + String.valueOf(password));
        }
    }

    @Override // sun.net.ftp.FtpClient
    public sun.net.ftp.FtpClient login(String user, char[] password) throws FtpProtocolException, IOException {
        if (!isConnected()) {
            throw new FtpProtocolException("Not connected yet", FtpReplyCode.BAD_SEQUENCE);
        } else if (user == null || user.length() == 0) {
            throw new IllegalArgumentException("User name can't be null or empty");
        } else {
            tryLogin(user, password);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < this.serverResponse.size(); i++) {
                String l = this.serverResponse.elementAt(i);
                if (l != null) {
                    if (l.length() >= 4 && l.startsWith("230")) {
                        l = l.substring(4);
                    }
                    sb.append(l);
                }
            }
            this.welcomeMsg = sb.toString();
            this.loggedIn = true;
            return this;
        }
    }

    @Override // sun.net.ftp.FtpClient
    public sun.net.ftp.FtpClient login(String user, char[] password, String account) throws FtpProtocolException, IOException {
        if (!isConnected()) {
            throw new FtpProtocolException("Not connected yet", FtpReplyCode.BAD_SEQUENCE);
        } else if (user == null || user.length() == 0) {
            throw new IllegalArgumentException("User name can't be null or empty");
        } else {
            tryLogin(user, password);
            if (this.lastReplyCode == FtpReplyCode.NEED_ACCOUNT) {
                issueCommandCheck("ACCT " + account);
            }
            StringBuffer sb = new StringBuffer();
            Vector<String> vector = this.serverResponse;
            if (vector != null) {
                Iterator<String> it = vector.iterator();
                while (it.hasNext()) {
                    String l = it.next();
                    if (l != null) {
                        if (l.length() >= 4 && l.startsWith("230")) {
                            l = l.substring(4);
                        }
                        sb.append(l);
                    }
                }
            }
            this.welcomeMsg = sb.toString();
            this.loggedIn = true;
            return this;
        }
    }

    @Override // sun.net.ftp.FtpClient, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (isConnected()) {
            try {
                issueCommand("QUIT");
            } catch (FtpProtocolException e) {
            }
            this.loggedIn = false;
        }
        disconnect();
    }

    @Override // sun.net.ftp.FtpClient
    public boolean isLoggedIn() {
        return this.loggedIn;
    }

    @Override // sun.net.ftp.FtpClient
    public sun.net.ftp.FtpClient changeDirectory(String remoteDirectory) throws FtpProtocolException, IOException {
        if (remoteDirectory == null || "".equals(remoteDirectory)) {
            throw new IllegalArgumentException("directory can't be null or empty");
        }
        issueCommandCheck("CWD " + remoteDirectory);
        return this;
    }

    @Override // sun.net.ftp.FtpClient
    public sun.net.ftp.FtpClient changeToParentDirectory() throws FtpProtocolException, IOException {
        issueCommandCheck("CDUP");
        return this;
    }

    @Override // sun.net.ftp.FtpClient
    public String getWorkingDirectory() throws FtpProtocolException, IOException {
        issueCommandCheck("PWD");
        String answ = getResponseString();
        if (!answ.startsWith("257")) {
            return null;
        }
        return answ.substring(5, answ.lastIndexOf(34));
    }

    @Override // sun.net.ftp.FtpClient
    public sun.net.ftp.FtpClient setRestartOffset(long offset) {
        if (offset >= 0) {
            this.restartOffset = offset;
            return this;
        }
        throw new IllegalArgumentException("offset can't be negative");
    }

    /* JADX INFO: finally extract failed */
    @Override // sun.net.ftp.FtpClient
    public sun.net.ftp.FtpClient getFile(String name, OutputStream local) throws FtpProtocolException, IOException {
        if (this.restartOffset > 0) {
            try {
                Socket s = openDataConnection("REST " + this.restartOffset);
                this.restartOffset = 0;
                issueCommandCheck("RETR " + name);
                getTransferSize();
                InputStream remote = createInputStream(s.getInputStream());
                byte[] buf = new byte[(1500 * 10)];
                while (true) {
                    int l = remote.read(buf);
                    if (l < 0) {
                        break;
                    } else if (l > 0) {
                        local.write(buf, 0, l);
                    }
                }
                remote.close();
            } catch (Throwable th) {
                this.restartOffset = 0;
                throw th;
            }
        } else {
            Socket s2 = openDataConnection("RETR " + name);
            getTransferSize();
            InputStream remote2 = createInputStream(s2.getInputStream());
            byte[] buf2 = new byte[(1500 * 10)];
            while (true) {
                int l2 = remote2.read(buf2);
                if (l2 < 0) {
                    break;
                } else if (l2 > 0) {
                    local.write(buf2, 0, l2);
                }
            }
            remote2.close();
        }
        return completePending();
    }

    @Override // sun.net.ftp.FtpClient
    public InputStream getFileStream(String name) throws FtpProtocolException, IOException {
        if (this.restartOffset > 0) {
            try {
                Socket s = openDataConnection("REST " + this.restartOffset);
                if (s == null) {
                    return null;
                }
                issueCommandCheck("RETR " + name);
                getTransferSize();
                return createInputStream(s.getInputStream());
            } finally {
                this.restartOffset = 0;
            }
        } else {
            Socket s2 = openDataConnection("RETR " + name);
            if (s2 == null) {
                return null;
            }
            getTransferSize();
            return createInputStream(s2.getInputStream());
        }
    }

    @Override // sun.net.ftp.FtpClient
    public OutputStream putFileStream(String name, boolean unique) throws FtpProtocolException, IOException {
        String cmd = unique ? "STOU " : "STOR ";
        Socket s = openDataConnection(cmd + name);
        if (s == null) {
            return null;
        }
        return new TelnetOutputStream(s.getOutputStream(), this.type == FtpClient.TransferType.BINARY);
    }

    @Override // sun.net.ftp.FtpClient
    public sun.net.ftp.FtpClient putFile(String name, InputStream local, boolean unique) throws FtpProtocolException, IOException {
        String cmd = unique ? "STOU " : "STOR ";
        if (this.type == FtpClient.TransferType.BINARY) {
            OutputStream remote = createOutputStream(openDataConnection(cmd + name).getOutputStream());
            byte[] buf = new byte[(1500 * 10)];
            while (true) {
                int l = local.read(buf);
                if (l < 0) {
                    break;
                } else if (l > 0) {
                    remote.write(buf, 0, l);
                }
            }
            remote.close();
        }
        return completePending();
    }

    @Override // sun.net.ftp.FtpClient
    public sun.net.ftp.FtpClient appendFile(String name, InputStream local) throws FtpProtocolException, IOException {
        OutputStream remote = createOutputStream(openDataConnection("APPE " + name).getOutputStream());
        byte[] buf = new byte[(1500 * 10)];
        while (true) {
            int l = local.read(buf);
            if (l < 0) {
                remote.close();
                return completePending();
            } else if (l > 0) {
                remote.write(buf, 0, l);
            }
        }
    }

    @Override // sun.net.ftp.FtpClient
    public sun.net.ftp.FtpClient rename(String from, String to) throws FtpProtocolException, IOException {
        issueCommandCheck("RNFR " + from);
        issueCommandCheck("RNTO " + to);
        return this;
    }

    @Override // sun.net.ftp.FtpClient
    public sun.net.ftp.FtpClient deleteFile(String name) throws FtpProtocolException, IOException {
        issueCommandCheck("DELE " + name);
        return this;
    }

    @Override // sun.net.ftp.FtpClient
    public sun.net.ftp.FtpClient makeDirectory(String name) throws FtpProtocolException, IOException {
        issueCommandCheck("MKD " + name);
        return this;
    }

    @Override // sun.net.ftp.FtpClient
    public sun.net.ftp.FtpClient removeDirectory(String name) throws FtpProtocolException, IOException {
        issueCommandCheck("RMD " + name);
        return this;
    }

    @Override // sun.net.ftp.FtpClient
    public sun.net.ftp.FtpClient noop() throws FtpProtocolException, IOException {
        issueCommandCheck("NOOP");
        return this;
    }

    @Override // sun.net.ftp.FtpClient
    public String getStatus(String name) throws FtpProtocolException, IOException {
        issueCommandCheck(name == null ? "STAT" : "STAT " + name);
        Vector<String> resp = getResponseStrings();
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i < resp.size() - 1; i++) {
            sb.append(resp.get(i));
        }
        return sb.toString();
    }

    @Override // sun.net.ftp.FtpClient
    public List<String> getFeatures() throws FtpProtocolException, IOException {
        ArrayList<String> features = new ArrayList<>();
        issueCommandCheck("FEAT");
        Vector<String> resp = getResponseStrings();
        for (int i = 1; i < resp.size() - 1; i++) {
            String s = resp.get(i);
            features.add(s.substring(1, s.length() - 1));
        }
        return features;
    }

    @Override // sun.net.ftp.FtpClient
    public sun.net.ftp.FtpClient abort() throws FtpProtocolException, IOException {
        issueCommandCheck("ABOR");
        return this;
    }

    @Override // sun.net.ftp.FtpClient
    public sun.net.ftp.FtpClient completePending() throws FtpProtocolException, IOException {
        while (this.replyPending) {
            this.replyPending = false;
            if (!readReply()) {
                throw new FtpProtocolException(getLastResponseString(), this.lastReplyCode);
            }
        }
        return this;
    }

    @Override // sun.net.ftp.FtpClient
    public sun.net.ftp.FtpClient reInit() throws FtpProtocolException, IOException {
        issueCommandCheck("REIN");
        this.loggedIn = false;
        if (this.useCrypto) {
            Socket socket = this.server;
            if (socket instanceof SSLSocket) {
                ((SSLSocket) socket).getSession().invalidate();
                this.server = this.oldSocket;
                this.oldSocket = null;
                try {
                    this.out = new PrintStream((OutputStream) new BufferedOutputStream(this.server.getOutputStream()), true, encoding);
                    this.in = new BufferedInputStream(this.server.getInputStream());
                } catch (UnsupportedEncodingException e) {
                    throw new InternalError(encoding + "encoding not found", e);
                }
            }
        }
        this.useCrypto = false;
        return this;
    }

    @Override // sun.net.ftp.FtpClient
    public sun.net.ftp.FtpClient setType(FtpClient.TransferType type2) throws FtpProtocolException, IOException {
        String cmd = "NOOP";
        this.type = type2;
        if (type2 == FtpClient.TransferType.ASCII) {
            cmd = "TYPE A";
        }
        if (type2 == FtpClient.TransferType.BINARY) {
            cmd = "TYPE I";
        }
        if (type2 == FtpClient.TransferType.EBCDIC) {
            cmd = "TYPE E";
        }
        issueCommandCheck(cmd);
        return this;
    }

    @Override // sun.net.ftp.FtpClient
    public InputStream list(String path) throws FtpProtocolException, IOException {
        String str;
        if (path == null) {
            str = "LIST";
        } else {
            str = "LIST " + path;
        }
        Socket s = openDataConnection(str);
        if (s != null) {
            return createInputStream(s.getInputStream());
        }
        return null;
    }

    @Override // sun.net.ftp.FtpClient
    public InputStream nameList(String path) throws FtpProtocolException, IOException {
        String str;
        if (path == null) {
            str = "NLST";
        } else {
            str = "NLST " + path;
        }
        Socket s = openDataConnection(str);
        if (s != null) {
            return createInputStream(s.getInputStream());
        }
        return null;
    }

    @Override // sun.net.ftp.FtpClient
    public long getSize(String path) throws FtpProtocolException, IOException {
        if (path == null || path.length() == 0) {
            throw new IllegalArgumentException("path can't be null or empty");
        }
        issueCommandCheck("SIZE " + path);
        if (this.lastReplyCode != FtpReplyCode.FILE_STATUS) {
            return -1;
        }
        String s = getResponseString();
        return Long.parseLong(s.substring(4, s.length() - 1));
    }

    @Override // sun.net.ftp.FtpClient
    public Date getLastModified(String path) throws FtpProtocolException, IOException {
        issueCommandCheck("MDTM " + path);
        if (this.lastReplyCode != FtpReplyCode.FILE_STATUS) {
            return null;
        }
        String s = getResponseString().substring(4);
        Date d = null;
        for (SimpleDateFormat dateFormat : dateFormats) {
            try {
                d = dateFormat.parse(s);
            } catch (ParseException e) {
            }
            if (d != null) {
                return d;
            }
        }
        return null;
    }

    @Override // sun.net.ftp.FtpClient
    public sun.net.ftp.FtpClient setDirParser(FtpDirParser p) {
        this.parser = p;
        return this;
    }

    private class FtpFileIterator implements Iterator<FtpDirEntry>, Closeable {
        private boolean eof = false;
        private FtpDirParser fparser = null;
        private BufferedReader in = null;
        private FtpDirEntry nextFile = null;

        public FtpFileIterator(FtpDirParser p, BufferedReader in2) {
            this.in = in2;
            this.fparser = p;
            readNext();
        }

        private void readNext() {
            String line;
            this.nextFile = null;
            if (!this.eof) {
                do {
                    try {
                        line = this.in.readLine();
                        if (line != null) {
                            this.nextFile = this.fparser.parseLine(line);
                            if (this.nextFile != null) {
                                return;
                            }
                        }
                    } catch (IOException e) {
                    }
                } while (line != null);
                this.in.close();
                this.eof = true;
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.nextFile != null;
        }

        @Override // java.util.Iterator
        public FtpDirEntry next() {
            FtpDirEntry ret = this.nextFile;
            readNext();
            return ret;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            BufferedReader bufferedReader = this.in;
            if (bufferedReader != null && !this.eof) {
                bufferedReader.close();
            }
            this.eof = true;
            this.nextFile = null;
        }
    }

    @Override // sun.net.ftp.FtpClient
    public Iterator<FtpDirEntry> listFiles(String path) throws FtpProtocolException, IOException {
        String str;
        String str2;
        Socket s = null;
        if (path == null) {
            str2 = "MLSD";
        } else {
            try {
                str2 = "MLSD " + path;
            } catch (FtpProtocolException e) {
            }
        }
        s = openDataConnection(str2);
        if (s != null) {
            return new FtpFileIterator(this.mlsxParser, new BufferedReader(new InputStreamReader(s.getInputStream())));
        }
        if (path == null) {
            str = "LIST";
        } else {
            str = "LIST " + path;
        }
        Socket s2 = openDataConnection(str);
        if (s2 == null) {
            return null;
        }
        return new FtpFileIterator(this.parser, new BufferedReader(new InputStreamReader(s2.getInputStream())));
    }

    private boolean sendSecurityData(byte[] buf) throws IOException, FtpProtocolException {
        String s = new BASE64Encoder().encode(buf);
        return issueCommand("ADAT " + s);
    }

    private byte[] getSecurityData() {
        String s = getLastResponseString();
        if (!s.substring(4, 9).equalsIgnoreCase("ADAT=")) {
            return null;
        }
        try {
            return new BASE64Decoder().decodeBuffer(s.substring(9, s.length() - 1));
        } catch (IOException e) {
            return null;
        }
    }

    @Override // sun.net.ftp.FtpClient
    public sun.net.ftp.FtpClient useKerberos() throws FtpProtocolException, IOException {
        return this;
    }

    @Override // sun.net.ftp.FtpClient
    public String getWelcomeMsg() {
        return this.welcomeMsg;
    }

    @Override // sun.net.ftp.FtpClient
    public FtpReplyCode getLastReplyCode() {
        return this.lastReplyCode;
    }

    @Override // sun.net.ftp.FtpClient
    public String getLastResponseString() {
        StringBuffer sb = new StringBuffer();
        Vector<String> vector = this.serverResponse;
        if (vector != null) {
            Iterator<String> it = vector.iterator();
            while (it.hasNext()) {
                String l = it.next();
                if (l != null) {
                    sb.append(l);
                }
            }
        }
        return sb.toString();
    }

    @Override // sun.net.ftp.FtpClient
    public long getLastTransferSize() {
        return this.lastTransSize;
    }

    @Override // sun.net.ftp.FtpClient
    public String getLastFileName() {
        return this.lastFileName;
    }

    @Override // sun.net.ftp.FtpClient
    public sun.net.ftp.FtpClient startSecureSession() throws FtpProtocolException, IOException {
        if (isConnected()) {
            if (this.sslFact == null) {
                try {
                    this.sslFact = (SSLSocketFactory) SSLSocketFactory.getDefault();
                } catch (Exception e) {
                    throw new IOException(e.getLocalizedMessage());
                }
            }
            issueCommandCheck("AUTH TLS");
            try {
                Socket s = this.sslFact.createSocket(this.server, this.serverAddr.getHostName(), this.serverAddr.getPort(), true);
                this.oldSocket = this.server;
                this.server = s;
                try {
                    this.out = new PrintStream((OutputStream) new BufferedOutputStream(this.server.getOutputStream()), true, encoding);
                    this.in = new BufferedInputStream(this.server.getInputStream());
                    issueCommandCheck("PBSZ 0");
                    issueCommandCheck("PROT P");
                    this.useCrypto = true;
                    return this;
                } catch (UnsupportedEncodingException e2) {
                    throw new InternalError(encoding + "encoding not found", e2);
                }
            } catch (SSLException ssle) {
                try {
                    disconnect();
                } catch (Exception e3) {
                }
                throw ssle;
            }
        } else {
            throw new FtpProtocolException("Not connected yet", FtpReplyCode.BAD_SEQUENCE);
        }
    }

    @Override // sun.net.ftp.FtpClient
    public sun.net.ftp.FtpClient endSecureSession() throws FtpProtocolException, IOException {
        if (!this.useCrypto) {
            return this;
        }
        issueCommandCheck("CCC");
        issueCommandCheck("PROT C");
        this.useCrypto = false;
        this.server = this.oldSocket;
        this.oldSocket = null;
        try {
            this.out = new PrintStream((OutputStream) new BufferedOutputStream(this.server.getOutputStream()), true, encoding);
            this.in = new BufferedInputStream(this.server.getInputStream());
            return this;
        } catch (UnsupportedEncodingException e) {
            throw new InternalError(encoding + "encoding not found", e);
        }
    }

    @Override // sun.net.ftp.FtpClient
    public sun.net.ftp.FtpClient allocate(long size) throws FtpProtocolException, IOException {
        issueCommandCheck("ALLO " + size);
        return this;
    }

    @Override // sun.net.ftp.FtpClient
    public sun.net.ftp.FtpClient structureMount(String struct) throws FtpProtocolException, IOException {
        issueCommandCheck("SMNT " + struct);
        return this;
    }

    @Override // sun.net.ftp.FtpClient
    public String getSystem() throws FtpProtocolException, IOException {
        issueCommandCheck("SYST");
        return getResponseString().substring(4);
    }

    @Override // sun.net.ftp.FtpClient
    public String getHelp(String cmd) throws FtpProtocolException, IOException {
        issueCommandCheck("HELP " + cmd);
        Vector<String> resp = getResponseStrings();
        if (resp.size() == 1) {
            return resp.get(0).substring(4);
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i < resp.size() - 1; i++) {
            sb.append(resp.get(i).substring(3));
        }
        return sb.toString();
    }

    @Override // sun.net.ftp.FtpClient
    public sun.net.ftp.FtpClient siteCmd(String cmd) throws FtpProtocolException, IOException {
        issueCommandCheck("SITE " + cmd);
        return this;
    }
}
