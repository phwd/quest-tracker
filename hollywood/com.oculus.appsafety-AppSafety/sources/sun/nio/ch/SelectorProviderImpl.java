package sun.nio.ch;

import java.io.IOException;
import java.net.ProtocolFamily;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Pipe;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.AbstractSelector;
import java.nio.channels.spi.SelectorProvider;

public abstract class SelectorProviderImpl extends SelectorProvider {
    @Override // java.nio.channels.spi.SelectorProvider
    public abstract AbstractSelector openSelector() throws IOException;

    @Override // java.nio.channels.spi.SelectorProvider
    public DatagramChannel openDatagramChannel() throws IOException {
        return new DatagramChannelImpl(this);
    }

    @Override // java.nio.channels.spi.SelectorProvider
    public DatagramChannel openDatagramChannel(ProtocolFamily family) throws IOException {
        return new DatagramChannelImpl(this, family);
    }

    @Override // java.nio.channels.spi.SelectorProvider
    public Pipe openPipe() throws IOException {
        return new PipeImpl(this);
    }

    @Override // java.nio.channels.spi.SelectorProvider
    public ServerSocketChannel openServerSocketChannel() throws IOException {
        return new ServerSocketChannelImpl(this);
    }

    @Override // java.nio.channels.spi.SelectorProvider
    public SocketChannel openSocketChannel() throws IOException {
        return new SocketChannelImpl(this);
    }
}
