package X;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;

/* renamed from: X.0h6  reason: invalid class name and case insensitive filesystem */
public final class C04600h6 {
    public static final Logger A00 = Logger.getLogger(C04600h6.class.getName());

    public static AnonymousClass0h1 A00(Socket socket) throws IOException {
        String str;
        if (socket == null) {
            str = "socket == null";
        } else if (socket.getOutputStream() != null) {
            AnonymousClass0HQ r2 = new AnonymousClass0HQ(socket);
            OutputStream outputStream = socket.getOutputStream();
            if (outputStream != null) {
                return new AnonymousClass0Oh(r2, new AnonymousClass0OT(r2, outputStream));
            }
            str = "out == null";
        } else {
            throw new IOException("socket's output stream == null");
        }
        throw new IllegalArgumentException(str);
    }

    public static AbstractC04550h0 A01(Socket socket) throws IOException {
        String str;
        if (socket == null) {
            str = "socket == null";
        } else if (socket.getInputStream() != null) {
            AnonymousClass0HQ r2 = new AnonymousClass0HQ(socket);
            InputStream inputStream = socket.getInputStream();
            if (inputStream != null) {
                return new AnonymousClass0Og(r2, new AnonymousClass0OS(r2, inputStream));
            }
            str = "in == null";
        } else {
            throw new IOException("socket's input stream == null");
        }
        throw new IllegalArgumentException(str);
    }
}
