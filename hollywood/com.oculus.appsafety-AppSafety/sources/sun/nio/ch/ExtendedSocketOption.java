package sun.nio.ch;

import java.net.SocketOption;

class ExtendedSocketOption {
    static final SocketOption<Boolean> SO_OOBINLINE = new SocketOption<Boolean>() {
        /* class sun.nio.ch.ExtendedSocketOption.AnonymousClass1 */

        @Override // java.net.SocketOption
        public String name() {
            return "SO_OOBINLINE";
        }

        @Override // java.net.SocketOption
        public Class<Boolean> type() {
            return Boolean.class;
        }

        public String toString() {
            return name();
        }
    };

    private ExtendedSocketOption() {
    }
}
