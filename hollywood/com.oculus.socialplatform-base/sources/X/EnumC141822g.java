package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.EOFException;
import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;
import java.util.zip.DataFormatException;
import javax.net.ssl.SSLException;

/* JADX INFO: Failed to restore enum class, 'enum' modifier removed */
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.22g  reason: invalid class name and case insensitive filesystem */
public final class EnumC141822g extends Enum<EnumC141822g> {
    public static final /* synthetic */ EnumC141822g[] $VALUES;
    public static final EnumC141822g ABORTED_PREEMPTIVE_RECONNECT;
    public static final EnumC141822g AUTH_CREDENTIALS_CHANGE;
    public static final EnumC141822g DISCONNECT_FROM_SERVER;
    public static final EnumC141822g EXPIRE_CONNECTION;
    public static final EnumC141822g KEEPALIVE_SHOULD_NOT_CONNECT;
    public static final EnumC141822g KICK_CONFIG_CHANGED;
    public static final EnumC141822g KICK_SHOULD_NOT_CONNECT;
    public static final EnumC141822g NETWORK_LOST;
    public static final EnumC141822g OPERATION_TIMEOUT;
    public static final EnumC141822g PING_UNRECEIVED;
    public static final EnumC141822g PREEMPTIVE_RECONNECT_SUCCESS;
    public static final EnumC141822g READ_EOF;
    public static final EnumC141822g READ_FAILURE_UNCLASSIFIED;
    public static final EnumC141822g READ_FORMAT;
    public static final EnumC141822g READ_IO;
    public static final EnumC141822g READ_SOCKET;
    public static final EnumC141822g READ_SSL;
    public static final EnumC141822g READ_TIMEOUT;
    public static final EnumC141822g SEND_FAILURE;
    public static final EnumC141822g SERIALIZER_FAILURE;
    public static final EnumC141822g SERVICE_DESTROY;
    public static final EnumC141822g SERVICE_STOP;
    public static final EnumC141822g UNKNOWN_RUNTIME;
    public static final EnumC141822g WRITE_EOF;
    public static final EnumC141822g WRITE_FAILURE_UNCLASSIFIED;
    public static final EnumC141822g WRITE_IO;
    public static final EnumC141822g WRITE_SOCKET;
    public static final EnumC141822g WRITE_SSL;
    public static final EnumC141822g WRITE_TIMEOUT;

    static {
        EnumC141822g r32 = new EnumC141822g("SERVICE_DESTROY", 0);
        SERVICE_DESTROY = r32;
        EnumC141822g r31 = new EnumC141822g("SERVICE_STOP", 1);
        SERVICE_STOP = r31;
        EnumC141822g r29 = new EnumC141822g("KICK_SHOULD_NOT_CONNECT", 2);
        KICK_SHOULD_NOT_CONNECT = r29;
        EnumC141822g r28 = new EnumC141822g("KICK_CONFIG_CHANGED", 3);
        KICK_CONFIG_CHANGED = r28;
        EnumC141822g r27 = new EnumC141822g("KEEPALIVE_SHOULD_NOT_CONNECT", 4);
        KEEPALIVE_SHOULD_NOT_CONNECT = r27;
        EnumC141822g r26 = new EnumC141822g("EXPIRE_CONNECTION", 5);
        EXPIRE_CONNECTION = r26;
        EnumC141822g r25 = new EnumC141822g("OPERATION_TIMEOUT", 6);
        OPERATION_TIMEOUT = r25;
        EnumC141822g r24 = new EnumC141822g("PING_UNRECEIVED", 7);
        PING_UNRECEIVED = r24;
        EnumC141822g r23 = new EnumC141822g("READ_TIMEOUT", 8);
        READ_TIMEOUT = r23;
        EnumC141822g r13 = new EnumC141822g("READ_EOF", 9);
        READ_EOF = r13;
        EnumC141822g r22 = new EnumC141822g("READ_SOCKET", 10);
        READ_SOCKET = r22;
        EnumC141822g r21 = new EnumC141822g("READ_SSL", 11);
        READ_SSL = r21;
        EnumC141822g r20 = new EnumC141822g("READ_IO", 12);
        READ_IO = r20;
        EnumC141822g r19 = new EnumC141822g("READ_FORMAT", 13);
        READ_FORMAT = r19;
        EnumC141822g r18 = new EnumC141822g("READ_FAILURE_UNCLASSIFIED", 14);
        READ_FAILURE_UNCLASSIFIED = r18;
        EnumC141822g r17 = new EnumC141822g("WRITE_TIMEOUT", 15);
        WRITE_TIMEOUT = r17;
        EnumC141822g r15 = new EnumC141822g("WRITE_EOF", 16);
        WRITE_EOF = r15;
        EnumC141822g r14 = new EnumC141822g("WRITE_SOCKET", 17);
        WRITE_SOCKET = r14;
        EnumC141822g r12 = new EnumC141822g("WRITE_SSL", 18);
        WRITE_SSL = r12;
        EnumC141822g r11 = new EnumC141822g("WRITE_IO", 19);
        WRITE_IO = r11;
        EnumC141822g r10 = new EnumC141822g("WRITE_FAILURE_UNCLASSIFIED", 20);
        WRITE_FAILURE_UNCLASSIFIED = r10;
        EnumC141822g r9 = new EnumC141822g("UNKNOWN_RUNTIME", 21);
        UNKNOWN_RUNTIME = r9;
        EnumC141822g r8 = new EnumC141822g("SEND_FAILURE", 22);
        SEND_FAILURE = r8;
        EnumC141822g r7 = new EnumC141822g("DISCONNECT_FROM_SERVER", 23);
        DISCONNECT_FROM_SERVER = r7;
        EnumC141822g r6 = new EnumC141822g("SERIALIZER_FAILURE", 24);
        SERIALIZER_FAILURE = r6;
        EnumC141822g r2 = new EnumC141822g("PREEMPTIVE_RECONNECT_SUCCESS", 25);
        PREEMPTIVE_RECONNECT_SUCCESS = r2;
        EnumC141822g r0 = new EnumC141822g("ABORTED_PREEMPTIVE_RECONNECT", 26);
        ABORTED_PREEMPTIVE_RECONNECT = r0;
        EnumC141822g r5 = new EnumC141822g("AUTH_CREDENTIALS_CHANGE", 27);
        AUTH_CREDENTIALS_CHANGE = r5;
        EnumC141822g r1 = new EnumC141822g("NETWORK_LOST", 28);
        NETWORK_LOST = r1;
        EnumC141822g[] r4 = new EnumC141822g[29];
        System.arraycopy(new EnumC141822g[]{r32, r31, r29, r28, r27, r26, r25, r24, r23, r13, r22, r21, r20, r19, r18, r17, r15, r14, r12, r11, r10, r9, r8, r7, r6, r2, r0}, 0, r4, 0, 27);
        System.arraycopy(new EnumC141822g[]{r5, r1}, 0, r4, 27, 2);
        $VALUES = r4;
    }

    public static EnumC141822g getFromReadException(Throwable th) {
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

    public static EnumC141822g getFromWriteException(Throwable th) {
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

    public static EnumC141822g valueOf(String str) {
        return (EnumC141822g) Enum.valueOf(EnumC141822g.class, str);
    }

    public static EnumC141822g[] values() {
        return (EnumC141822g[]) $VALUES.clone();
    }

    public EnumC141822g(String str, int i) {
    }
}
