package java.nio.channels;

import java.io.IOException;
import java.nio.channels.spi.AbstractSelectableChannel;
import java.nio.channels.spi.SelectorProvider;

public abstract class Pipe {
    public abstract SinkChannel sink();

    public abstract SourceChannel source();

    public static abstract class SourceChannel extends AbstractSelectableChannel implements ReadableByteChannel, ScatteringByteChannel {
        protected SourceChannel(SelectorProvider provider) {
            super(provider);
        }

        @Override // java.nio.channels.SelectableChannel
        public final int validOps() {
            return 1;
        }
    }

    public static abstract class SinkChannel extends AbstractSelectableChannel implements WritableByteChannel, GatheringByteChannel {
        protected SinkChannel(SelectorProvider provider) {
            super(provider);
        }

        @Override // java.nio.channels.SelectableChannel
        public final int validOps() {
            return 4;
        }
    }

    protected Pipe() {
    }

    public static Pipe open() throws IOException {
        return SelectorProvider.provider().openPipe();
    }
}
