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
/* renamed from: X.0xi  reason: invalid class name and case insensitive filesystem */
public final class EnumC08720xi extends Enum<EnumC08720xi> {
    public static final /* synthetic */ EnumC08720xi[] $VALUES;
    public static final EnumC08720xi ABORTED_PREEMPTIVE_RECONNECT;
    public static final EnumC08720xi AUTH_CREDENTIALS_CHANGE;
    public static final EnumC08720xi DISCONNECT_FROM_SERVER;
    public static final EnumC08720xi EXPIRE_CONNECTION;
    public static final EnumC08720xi KEEPALIVE_SHOULD_NOT_CONNECT;
    public static final EnumC08720xi KICK_CONFIG_CHANGED;
    public static final EnumC08720xi KICK_SHOULD_NOT_CONNECT;
    public static final EnumC08720xi NETWORK_LOST;
    public static final EnumC08720xi OPERATION_TIMEOUT;
    public static final EnumC08720xi PING_UNRECEIVED;
    public static final EnumC08720xi PREEMPTIVE_RECONNECT_SUCCESS;
    public static final EnumC08720xi READ_EOF;
    public static final EnumC08720xi READ_FAILURE_UNCLASSIFIED;
    public static final EnumC08720xi READ_FORMAT;
    public static final EnumC08720xi READ_IO;
    public static final EnumC08720xi READ_SOCKET;
    public static final EnumC08720xi READ_SSL;
    public static final EnumC08720xi READ_TIMEOUT;
    public static final EnumC08720xi SEND_FAILURE;
    public static final EnumC08720xi SERIALIZER_FAILURE;
    public static final EnumC08720xi SERVICE_DESTROY;
    public static final EnumC08720xi SERVICE_STOP;
    public static final EnumC08720xi UNKNOWN_RUNTIME;
    public static final EnumC08720xi WRITE_EOF;
    public static final EnumC08720xi WRITE_FAILURE_UNCLASSIFIED;
    public static final EnumC08720xi WRITE_IO;
    public static final EnumC08720xi WRITE_SOCKET;
    public static final EnumC08720xi WRITE_SSL;
    public static final EnumC08720xi WRITE_TIMEOUT;

    static {
        EnumC08720xi r32 = new EnumC08720xi("SERVICE_DESTROY", 0);
        SERVICE_DESTROY = r32;
        EnumC08720xi r31 = new EnumC08720xi("SERVICE_STOP", 1);
        SERVICE_STOP = r31;
        EnumC08720xi r29 = new EnumC08720xi("KICK_SHOULD_NOT_CONNECT", 2);
        KICK_SHOULD_NOT_CONNECT = r29;
        EnumC08720xi r28 = new EnumC08720xi("KICK_CONFIG_CHANGED", 3);
        KICK_CONFIG_CHANGED = r28;
        EnumC08720xi r27 = new EnumC08720xi("KEEPALIVE_SHOULD_NOT_CONNECT", 4);
        KEEPALIVE_SHOULD_NOT_CONNECT = r27;
        EnumC08720xi r26 = new EnumC08720xi("EXPIRE_CONNECTION", 5);
        EXPIRE_CONNECTION = r26;
        EnumC08720xi r25 = new EnumC08720xi("OPERATION_TIMEOUT", 6);
        OPERATION_TIMEOUT = r25;
        EnumC08720xi r24 = new EnumC08720xi("PING_UNRECEIVED", 7);
        PING_UNRECEIVED = r24;
        EnumC08720xi r23 = new EnumC08720xi("READ_TIMEOUT", 8);
        READ_TIMEOUT = r23;
        EnumC08720xi r13 = new EnumC08720xi("READ_EOF", 9);
        READ_EOF = r13;
        EnumC08720xi r22 = new EnumC08720xi("READ_SOCKET", 10);
        READ_SOCKET = r22;
        EnumC08720xi r21 = new EnumC08720xi("READ_SSL", 11);
        READ_SSL = r21;
        EnumC08720xi r20 = new EnumC08720xi("READ_IO", 12);
        READ_IO = r20;
        EnumC08720xi r19 = new EnumC08720xi("READ_FORMAT", 13);
        READ_FORMAT = r19;
        EnumC08720xi r18 = new EnumC08720xi("READ_FAILURE_UNCLASSIFIED", 14);
        READ_FAILURE_UNCLASSIFIED = r18;
        EnumC08720xi r17 = new EnumC08720xi("WRITE_TIMEOUT", 15);
        WRITE_TIMEOUT = r17;
        EnumC08720xi r15 = new EnumC08720xi("WRITE_EOF", 16);
        WRITE_EOF = r15;
        EnumC08720xi r14 = new EnumC08720xi("WRITE_SOCKET", 17);
        WRITE_SOCKET = r14;
        EnumC08720xi r12 = new EnumC08720xi("WRITE_SSL", 18);
        WRITE_SSL = r12;
        EnumC08720xi r11 = new EnumC08720xi("WRITE_IO", 19);
        WRITE_IO = r11;
        EnumC08720xi r10 = new EnumC08720xi("WRITE_FAILURE_UNCLASSIFIED", 20);
        WRITE_FAILURE_UNCLASSIFIED = r10;
        EnumC08720xi r9 = new EnumC08720xi("UNKNOWN_RUNTIME", 21);
        UNKNOWN_RUNTIME = r9;
        EnumC08720xi r8 = new EnumC08720xi("SEND_FAILURE", 22);
        SEND_FAILURE = r8;
        EnumC08720xi r7 = new EnumC08720xi("DISCONNECT_FROM_SERVER", 23);
        DISCONNECT_FROM_SERVER = r7;
        EnumC08720xi r6 = new EnumC08720xi("SERIALIZER_FAILURE", 24);
        SERIALIZER_FAILURE = r6;
        EnumC08720xi r2 = new EnumC08720xi("PREEMPTIVE_RECONNECT_SUCCESS", 25);
        PREEMPTIVE_RECONNECT_SUCCESS = r2;
        EnumC08720xi r0 = new EnumC08720xi("ABORTED_PREEMPTIVE_RECONNECT", 26);
        ABORTED_PREEMPTIVE_RECONNECT = r0;
        EnumC08720xi r5 = new EnumC08720xi("AUTH_CREDENTIALS_CHANGE", 27);
        AUTH_CREDENTIALS_CHANGE = r5;
        EnumC08720xi r1 = new EnumC08720xi("NETWORK_LOST", 28);
        NETWORK_LOST = r1;
        EnumC08720xi[] r4 = new EnumC08720xi[29];
        System.arraycopy(new EnumC08720xi[]{r32, r31, r29, r28, r27, r26, r25, r24, r23, r13, r22, r21, r20, r19, r18, r17, r15, r14, r12, r11, r10, r9, r8, r7, r6, r2, r0}, 0, r4, 0, 27);
        System.arraycopy(new EnumC08720xi[]{r5, r1}, 0, r4, 27, 2);
        $VALUES = r4;
    }

    public static EnumC08720xi getFromReadException(Throwable th) {
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

    public static EnumC08720xi getFromWriteException(Throwable th) {
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

    public static EnumC08720xi valueOf(String str) {
        return (EnumC08720xi) Enum.valueOf(EnumC08720xi.class, str);
    }

    public static EnumC08720xi[] values() {
        return (EnumC08720xi[]) $VALUES.clone();
    }

    public EnumC08720xi(String str, int i) {
    }
}
