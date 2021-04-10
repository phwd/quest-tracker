package java.nio.channels;

import java.nio.channels.spi.AbstractSelectableChannel;

public abstract class SocketChannel extends AbstractSelectableChannel implements ByteChannel, ScatteringByteChannel, GatheringByteChannel, NetworkChannel {
}
