package sun.nio.ch;

import java.io.FileDescriptor;
import java.nio.ByteBuffer;

public class IOUtil {
    static final int IOV_MAX = iovMax();

    static native int iovMax();

    static int write(FileDescriptor fileDescriptor, ByteBuffer byteBuffer, long j, NativeDispatcher nativeDispatcher) {
        if (byteBuffer instanceof DirectBuffer) {
            return writeFromNativeBuffer(fileDescriptor, byteBuffer, j, nativeDispatcher);
        }
        int position = byteBuffer.position();
        int limit = byteBuffer.limit();
        ByteBuffer temporaryDirectBuffer = Util.getTemporaryDirectBuffer(position <= limit ? limit - position : 0);
        try {
            temporaryDirectBuffer.put(byteBuffer);
            temporaryDirectBuffer.flip();
            byteBuffer.position(position);
            int writeFromNativeBuffer = writeFromNativeBuffer(fileDescriptor, temporaryDirectBuffer, j, nativeDispatcher);
            if (writeFromNativeBuffer > 0) {
                byteBuffer.position(position + writeFromNativeBuffer);
            }
            return writeFromNativeBuffer;
        } finally {
            Util.offerFirstTemporaryDirectBuffer(temporaryDirectBuffer);
        }
    }

    private static int writeFromNativeBuffer(FileDescriptor fileDescriptor, ByteBuffer byteBuffer, long j, NativeDispatcher nativeDispatcher) {
        int i;
        int position = byteBuffer.position();
        int limit = byteBuffer.limit();
        int i2 = position <= limit ? limit - position : 0;
        if (i2 == 0) {
            return 0;
        }
        if (j != -1) {
            i = nativeDispatcher.pwrite(fileDescriptor, ((DirectBuffer) byteBuffer).address() + ((long) position), i2, j);
        } else {
            i = nativeDispatcher.write(fileDescriptor, ((DirectBuffer) byteBuffer).address() + ((long) position), i2);
        }
        if (i > 0) {
            byteBuffer.position(position + i);
        }
        return i;
    }

    static int read(FileDescriptor fileDescriptor, ByteBuffer byteBuffer, long j, NativeDispatcher nativeDispatcher) {
        if (byteBuffer.isReadOnly()) {
            throw new IllegalArgumentException("Read-only buffer");
        } else if (byteBuffer instanceof DirectBuffer) {
            return readIntoNativeBuffer(fileDescriptor, byteBuffer, j, nativeDispatcher);
        } else {
            ByteBuffer temporaryDirectBuffer = Util.getTemporaryDirectBuffer(byteBuffer.remaining());
            try {
                int readIntoNativeBuffer = readIntoNativeBuffer(fileDescriptor, temporaryDirectBuffer, j, nativeDispatcher);
                temporaryDirectBuffer.flip();
                if (readIntoNativeBuffer > 0) {
                    byteBuffer.put(temporaryDirectBuffer);
                }
                return readIntoNativeBuffer;
            } finally {
                Util.offerFirstTemporaryDirectBuffer(temporaryDirectBuffer);
            }
        }
    }

    private static int readIntoNativeBuffer(FileDescriptor fileDescriptor, ByteBuffer byteBuffer, long j, NativeDispatcher nativeDispatcher) {
        int i;
        int position = byteBuffer.position();
        int limit = byteBuffer.limit();
        int i2 = position <= limit ? limit - position : 0;
        if (i2 == 0) {
            return 0;
        }
        if (j != -1) {
            i = nativeDispatcher.pread(fileDescriptor, ((DirectBuffer) byteBuffer).address() + ((long) position), i2, j);
        } else {
            i = nativeDispatcher.read(fileDescriptor, ((DirectBuffer) byteBuffer).address() + ((long) position), i2);
        }
        if (i > 0) {
            byteBuffer.position(position + i);
        }
        return i;
    }
}
