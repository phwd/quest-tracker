package java.nio.channels;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.nio.channels.spi.AbstractSelectableChannel;
import java.nio.channels.spi.SelectorProvider;

public abstract class ServerSocketChannel extends AbstractSelectableChannel implements NetworkChannel {
    public abstract SocketChannel accept() throws IOException;

    public abstract ServerSocketChannel bind(SocketAddress socketAddress, int i) throws IOException;

    @Override // java.nio.channels.NetworkChannel
    public abstract SocketAddress getLocalAddress() throws IOException;

    @Override // java.nio.channels.NetworkChannel
    public abstract <T> ServerSocketChannel setOption(SocketOption<T> socketOption, T t) throws IOException;

    public abstract ServerSocket socket();

    protected ServerSocketChannel(SelectorProvider provider) {
        super(provider);
    }

    public static ServerSocketChannel open() throws IOException {
        return SelectorProvider.provider().openServerSocketChannel();
    }

    @Override // java.nio.channels.SelectableChannel
    public final int validOps() {
        return 16;
    }

    @Override // java.nio.channels.NetworkChannel
    public final ServerSocketChannel bind(SocketAddress local) throws IOException {
        return bind(local, 0);
    }
}
