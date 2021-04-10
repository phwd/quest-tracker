package dalvik.system;

import java.io.FileDescriptor;

public abstract class SocketTagger {
    private static SocketTagger tagger = new SocketTagger() {
        /* class dalvik.system.SocketTagger.AnonymousClass1 */

        @Override // dalvik.system.SocketTagger
        public void tag(FileDescriptor fileDescriptor) {
        }
    };

    public abstract void tag(FileDescriptor fileDescriptor);

    public static synchronized SocketTagger get() {
        SocketTagger socketTagger;
        synchronized (SocketTagger.class) {
            socketTagger = tagger;
        }
        return socketTagger;
    }
}
