package X;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;

public final class WL {
    public static final Logger A00 = Logger.getLogger(WL.class.getName());

    public static WG A00(Socket socket) throws IOException {
        String str;
        if (socket == null) {
            str = "socket == null";
        } else if (socket.getOutputStream() != null) {
            AnonymousClass8j r2 = new AnonymousClass8j(socket);
            OutputStream outputStream = socket.getOutputStream();
            if (outputStream != null) {
                return new Dx(r2, new Di(r2, outputStream));
            }
            str = "out == null";
        } else {
            throw new IOException("socket's output stream == null");
        }
        throw new IllegalArgumentException(str);
    }

    public static WF A01(Socket socket) throws IOException {
        String str;
        if (socket == null) {
            str = "socket == null";
        } else if (socket.getInputStream() != null) {
            AnonymousClass8j r2 = new AnonymousClass8j(socket);
            InputStream inputStream = socket.getInputStream();
            if (inputStream != null) {
                return new Dw(r2, new Dh(r2, inputStream));
            }
            str = "in == null";
        } else {
            throw new IOException("socket's input stream == null");
        }
        throw new IllegalArgumentException(str);
    }
}
