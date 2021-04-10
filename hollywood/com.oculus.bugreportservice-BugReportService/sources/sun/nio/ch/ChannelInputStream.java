package sun.nio.ch;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.IllegalBlockingModeException;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SelectableChannel;

public class ChannelInputStream extends InputStream {
    public static int read(ReadableByteChannel readableByteChannel, ByteBuffer byteBuffer, boolean z) {
        int read;
        if (!(readableByteChannel instanceof SelectableChannel)) {
            return readableByteChannel.read(byteBuffer);
        }
        SelectableChannel selectableChannel = (SelectableChannel) readableByteChannel;
        synchronized (selectableChannel.blockingLock()) {
            boolean isBlocking = selectableChannel.isBlocking();
            if (isBlocking) {
                if (isBlocking != z) {
                    selectableChannel.configureBlocking(z);
                }
                read = readableByteChannel.read(byteBuffer);
                if (isBlocking != z) {
                    selectableChannel.configureBlocking(isBlocking);
                }
            } else {
                throw new IllegalBlockingModeException();
            }
        }
        return read;
    }
}
