package sun.nio.ch;

import java.io.IOException;
import java.nio.channels.Channel;
import java.nio.channels.spi.AbstractSelector;

public class PollSelectorProvider extends SelectorProviderImpl {
    @Override // java.nio.channels.spi.SelectorProvider, sun.nio.ch.SelectorProviderImpl
    public AbstractSelector openSelector() throws IOException {
        return new PollSelectorImpl(this);
    }

    @Override // java.nio.channels.spi.SelectorProvider
    public Channel inheritedChannel() throws IOException {
        return null;
    }
}
