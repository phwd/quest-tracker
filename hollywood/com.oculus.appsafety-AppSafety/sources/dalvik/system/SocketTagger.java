package dalvik.system;

import java.io.FileDescriptor;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;

public abstract class SocketTagger {
    private static SocketTagger tagger = new SocketTagger() {
        /* class dalvik.system.SocketTagger.AnonymousClass1 */

        @Override // dalvik.system.SocketTagger
        public void tag(FileDescriptor socketDescriptor) throws SocketException {
        }

        @Override // dalvik.system.SocketTagger
        public void untag(FileDescriptor socketDescriptor) throws SocketException {
        }
    };

    public abstract void tag(FileDescriptor fileDescriptor) throws SocketException;

    public abstract void untag(FileDescriptor fileDescriptor) throws SocketException;

    public final void tag(Socket socket) throws SocketException {
        if (!socket.isClosed()) {
            tag(socket.getFileDescriptor$());
        }
    }

    public final void untag(Socket socket) throws SocketException {
        if (!socket.isClosed()) {
            untag(socket.getFileDescriptor$());
        }
    }

    public final void tag(DatagramSocket socket) throws SocketException {
        if (!socket.isClosed()) {
            tag(socket.getFileDescriptor$());
        }
    }

    public final void untag(DatagramSocket socket) throws SocketException {
        if (!socket.isClosed()) {
            untag(socket.getFileDescriptor$());
        }
    }

    public static synchronized void set(SocketTagger tagger2) {
        synchronized (SocketTagger.class) {
            if (tagger2 != null) {
                tagger = tagger2;
            } else {
                throw new NullPointerException("tagger == null");
            }
        }
    }

    public static synchronized SocketTagger get() {
        SocketTagger socketTagger;
        synchronized (SocketTagger.class) {
            socketTagger = tagger;
        }
        return socketTagger;
    }
}
