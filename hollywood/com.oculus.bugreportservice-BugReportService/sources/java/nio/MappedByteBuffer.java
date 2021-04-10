package java.nio;

import java.io.FileDescriptor;

public abstract class MappedByteBuffer extends ByteBuffer {
    private final FileDescriptor fd;

    MappedByteBuffer(int i, int i2, int i3, int i4, FileDescriptor fileDescriptor) {
        super(i, i2, i3, i4);
        this.fd = fileDescriptor;
    }

    MappedByteBuffer(int i, int i2, int i3, int i4, byte[] bArr, int i5) {
        super(i, i2, i3, i4, bArr, i5);
        this.fd = null;
    }
}
