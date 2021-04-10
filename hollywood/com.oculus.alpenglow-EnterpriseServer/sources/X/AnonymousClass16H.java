package X;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import javax.net.ssl.SSLException;

/* renamed from: X.16H  reason: invalid class name */
public final class AnonymousClass16H {
    public static byte[] A00(byte b, byte[] bArr) throws AnonymousClass11f {
        int length;
        if (!C096710a.A00.contains(Byte.valueOf(b)) || bArr == null || (length = bArr.length) > 16777215) {
            throw new AnonymousClass11f((byte) 80, new SSLException(AnonymousClass006.A03("Illegal arguments -  type ", b, " msg is null or bigger than", 16777215)));
        }
        ByteBuffer allocate = ByteBuffer.allocate(length + 4);
        allocate.put(b);
        allocate.put(AnonymousClass11e.A05(length));
        allocate.put(bArr);
        return allocate.array();
    }

    public static byte[] A01(byte[] bArr) throws AnonymousClass11f {
        if (bArr != null) {
            try {
                ByteBuffer wrap = ByteBuffer.wrap(bArr);
                byte b = wrap.get();
                byte[] bArr2 = new byte[3];
                wrap.get(bArr2);
                int A00 = AnonymousClass11e.A00(bArr2);
                byte[] bArr3 = new byte[A00];
                wrap.get(bArr3);
                if (C096710a.A00.contains(Byte.valueOf(b)) && A00 <= 16777215) {
                    return bArr3;
                }
                throw new AnonymousClass11f((byte) 80, new SSLException(AnonymousClass006.A03("Received an invalid handshake - type ", b, " len ", A00)));
            } catch (BufferUnderflowException e) {
                throw new AnonymousClass11f((byte) 80, new SSLException("Invalid handshake message", e));
            }
        } else {
            throw new AnonymousClass11f((byte) 80, new SSLException("Illegal argument - handshake is null"));
        }
    }
}
