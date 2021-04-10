package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.EOFException;
import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;
import java.util.zip.DataFormatException;
import javax.net.ssl.SSLException;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Wk  reason: invalid class name and case insensitive filesystem */
public enum EnumC01660Wk {
    SERVICE_DESTROY,
    SERVICE_STOP,
    KICK_SHOULD_NOT_CONNECT,
    KICK_CONFIG_CHANGED,
    KEEPALIVE_SHOULD_NOT_CONNECT,
    EXPIRE_CONNECTION,
    OPERATION_TIMEOUT,
    PING_UNRECEIVED,
    READ_TIMEOUT,
    READ_EOF,
    READ_SOCKET,
    READ_SSL,
    READ_IO,
    READ_FORMAT,
    READ_FAILURE_UNCLASSIFIED,
    WRITE_TIMEOUT,
    WRITE_EOF,
    WRITE_SOCKET,
    WRITE_SSL,
    WRITE_IO,
    WRITE_FAILURE_UNCLASSIFIED,
    UNKNOWN_RUNTIME,
    SEND_FAILURE,
    DISCONNECT_FROM_SERVER,
    SERIALIZER_FAILURE,
    PREEMPTIVE_RECONNECT_SUCCESS,
    ABORTED_PREEMPTIVE_RECONNECT,
    AUTH_CREDENTIALS_CHANGE,
    NETWORK_LOST;

    public static EnumC01660Wk getFromReadException(Throwable th) {
        if ((th instanceof TimeoutException) || (th instanceof SocketTimeoutException)) {
            return READ_TIMEOUT;
        }
        if (th instanceof EOFException) {
            return READ_EOF;
        }
        if (th instanceof SocketException) {
            return READ_SOCKET;
        }
        if (th instanceof SSLException) {
            return READ_SSL;
        }
        if (th instanceof IOException) {
            return READ_IO;
        }
        if (th instanceof DataFormatException) {
            return READ_FORMAT;
        }
        return READ_FAILURE_UNCLASSIFIED;
    }

    public static EnumC01660Wk getFromWriteException(Throwable th) {
        if ((th instanceof TimeoutException) || (th instanceof SocketTimeoutException)) {
            return WRITE_TIMEOUT;
        }
        if (th instanceof EOFException) {
            return WRITE_EOF;
        }
        if (th instanceof SocketException) {
            return WRITE_SOCKET;
        }
        if (th instanceof SSLException) {
            return WRITE_SSL;
        }
        if (th instanceof IOException) {
            return WRITE_IO;
        }
        return WRITE_FAILURE_UNCLASSIFIED;
    }
}
