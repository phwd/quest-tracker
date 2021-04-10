package java.nio.channels;

import java.nio.channels.spi.AbstractInterruptibleChannel;

public abstract class SelectableChannel extends AbstractInterruptibleChannel implements Channel {
    public abstract Object blockingLock();

    public abstract SelectableChannel configureBlocking(boolean z);

    public abstract boolean isBlocking();

    protected SelectableChannel() {
    }
}
