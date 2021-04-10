package X;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;

/* renamed from: X.cn  reason: case insensitive filesystem */
public final class C0604cn {
    public static final Logger A00 = Logger.getLogger(C0604cn.class.getName());

    public static AbstractC0608cr A00(Socket socket) {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        } else if (socket.getOutputStream() != null) {
            AnonymousClass31 r2 = new AnonymousClass31(socket);
            OutputStream outputStream = socket.getOutputStream();
            if (outputStream != null) {
                return new t9(r2, new C1110sy(r2, outputStream));
            }
            throw new IllegalArgumentException("out == null");
        } else {
            throw new IOException("socket's output stream == null");
        }
    }

    public static AbstractC0609cs A01(Socket socket) {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        } else if (socket.getInputStream() != null) {
            AnonymousClass31 r2 = new AnonymousClass31(socket);
            InputStream inputStream = socket.getInputStream();
            if (inputStream != null) {
                return new t8(r2, new C1109sx(r2, inputStream));
            }
            throw new IllegalArgumentException("in == null");
        } else {
            throw new IOException("socket's input stream == null");
        }
    }
}
