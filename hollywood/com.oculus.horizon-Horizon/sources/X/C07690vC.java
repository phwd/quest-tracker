package X;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;

/* renamed from: X.0vC  reason: invalid class name and case insensitive filesystem */
public final class C07690vC {
    public static final Logger A00 = Logger.getLogger(C07690vC.class.getName());

    public static AbstractC07640v7 A00(Socket socket) throws IOException {
        String str;
        if (socket == null) {
            str = "socket == null";
        } else if (socket.getOutputStream() != null) {
            C00580Aw r2 = new C00580Aw(socket);
            OutputStream outputStream = socket.getOutputStream();
            if (outputStream != null) {
                return new AnonymousClass0M0(r2, new AnonymousClass0Lp(r2, outputStream));
            }
            str = "out == null";
        } else {
            throw new IOException("socket's output stream == null");
        }
        throw new IllegalArgumentException(str);
    }

    public static AbstractC07630v6 A01(Socket socket) throws IOException {
        String str;
        if (socket == null) {
            str = "socket == null";
        } else if (socket.getInputStream() != null) {
            C00580Aw r2 = new C00580Aw(socket);
            InputStream inputStream = socket.getInputStream();
            if (inputStream != null) {
                return new AnonymousClass0Lz(r2, new AnonymousClass0Lo(r2, inputStream));
            }
            str = "in == null";
        } else {
            throw new IOException("socket's input stream == null");
        }
        throw new IllegalArgumentException(str);
    }
}
