package java.nio.channels;

import java.io.IOException;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.nio.channels.spi.AsynchronousChannelProvider;
import java.util.concurrent.Future;

public abstract class AsynchronousServerSocketChannel implements AsynchronousChannel, NetworkChannel {
    private final AsynchronousChannelProvider provider;

    public abstract Future<AsynchronousSocketChannel> accept();

    public abstract <A> void accept(A a, CompletionHandler<AsynchronousSocketChannel, ? super A> completionHandler);

    public abstract AsynchronousServerSocketChannel bind(SocketAddress socketAddress, int i) throws IOException;

    @Override // java.nio.channels.NetworkChannel
    public abstract SocketAddress getLocalAddress() throws IOException;

    @Override // java.nio.channels.NetworkChannel
    public abstract <T> AsynchronousServerSocketChannel setOption(SocketOption<T> socketOption, T t) throws IOException;

    protected AsynchronousServerSocketChannel(AsynchronousChannelProvider provider2) {
        this.provider = provider2;
    }

    public final AsynchronousChannelProvider provider() {
        return this.provider;
    }

    public static AsynchronousServerSocketChannel open(AsynchronousChannelGroup group) throws IOException {
        return (group == null ? AsynchronousChannelProvider.provider() : group.provider()).openAsynchronousServerSocketChannel(group);
    }

    public static AsynchronousServerSocketChannel open() throws IOException {
        return open(null);
    }

    @Override // java.nio.channels.NetworkChannel
    public final AsynchronousServerSocketChannel bind(SocketAddress local) throws IOException {
        return bind(local, 0);
    }
}
