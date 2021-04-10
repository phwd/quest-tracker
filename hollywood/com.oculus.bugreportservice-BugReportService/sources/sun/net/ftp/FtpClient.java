package sun.net.ftp;

import java.io.Closeable;
import java.io.InputStream;
import java.net.Proxy;
import java.net.SocketAddress;

public abstract class FtpClient implements Closeable {

    public enum TransferType {
        ASCII,
        BINARY,
        EBCDIC
    }

    public static final int defaultPort() {
        return 21;
    }

    public abstract FtpClient changeDirectory(String str);

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close();

    public abstract FtpClient connect(SocketAddress socketAddress);

    public abstract InputStream getFileStream(String str);

    public abstract long getLastTransferSize();

    public abstract InputStream list(String str);

    public abstract FtpClient login(String str, char[] cArr);

    public abstract InputStream nameList(String str);

    public abstract FtpClient setConnectTimeout(int i);

    public abstract FtpClient setProxy(Proxy proxy);

    public abstract FtpClient setReadTimeout(int i);

    public abstract FtpClient setType(TransferType transferType);

    protected FtpClient() {
    }

    public static FtpClient create() {
        return FtpClientProvider.provider().createFtpClient();
    }

    public FtpClient setBinaryType() {
        setType(TransferType.BINARY);
        return this;
    }

    public FtpClient setAsciiType() {
        setType(TransferType.ASCII);
        return this;
    }
}
