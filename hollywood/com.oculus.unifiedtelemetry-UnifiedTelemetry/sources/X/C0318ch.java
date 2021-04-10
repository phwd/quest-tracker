package X;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;

/* renamed from: X.ch  reason: case insensitive filesystem */
public final class C0318ch {
    public static final Logger A00 = Logger.getLogger(C0318ch.class.getName());

    public static AbstractC0313cc A00(Socket socket) throws IOException {
        String str;
        if (socket == null) {
            str = "socket == null";
        } else if (socket.getOutputStream() != null) {
            AnonymousClass95 r2 = new AnonymousClass95(socket);
            OutputStream outputStream = socket.getOutputStream();
            if (outputStream != null) {
                return new KM(r2, new Jz(r2, outputStream));
            }
            str = "out == null";
        } else {
            throw new IOException("socket's output stream == null");
        }
        throw new IllegalArgumentException(str);
    }

    public static AbstractC0312cb A01(Socket socket) throws IOException {
        String str;
        if (socket == null) {
            str = "socket == null";
        } else if (socket.getInputStream() != null) {
            AnonymousClass95 r2 = new AnonymousClass95(socket);
            InputStream inputStream = socket.getInputStream();
            if (inputStream != null) {
                return new KL(r2, new Jy(r2, inputStream));
            }
            str = "in == null";
        } else {
            throw new IOException("socket's input stream == null");
        }
        throw new IllegalArgumentException(str);
    }
}
