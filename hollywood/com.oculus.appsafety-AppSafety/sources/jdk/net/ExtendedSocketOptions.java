package jdk.net;

import java.net.SocketOption;

public final class ExtendedSocketOptions {
    public static final SocketOption<SocketFlow> SO_FLOW_SLA = new ExtSocketOption("SO_FLOW_SLA", SocketFlow.class);

    private static class ExtSocketOption<T> implements SocketOption<T> {
        private final String name;
        private final Class<T> type;

        ExtSocketOption(String name2, Class<T> type2) {
            this.name = name2;
            this.type = type2;
        }

        @Override // java.net.SocketOption
        public String name() {
            return this.name;
        }

        @Override // java.net.SocketOption
        public Class<T> type() {
            return this.type;
        }

        public String toString() {
            return this.name;
        }
    }

    private ExtendedSocketOptions() {
    }
}
