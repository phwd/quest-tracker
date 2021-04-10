package com.google.protobuf;

import com.apple.dnssd.DNSSD;
import com.google.protobuf.Utf8;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

public abstract class CodedOutputStream extends ByteOutput {
    private static final long ARRAY_BASE_OFFSET = ((long) byteArrayBaseOffset());
    private static final boolean HAS_UNSAFE_ARRAY_OPERATIONS = supportsUnsafeArrayOperations();
    private static final Unsafe UNSAFE = getUnsafe();
    private static final Logger logger = Logger.getLogger(CodedOutputStream.class.getName());

    public static int computeFixed32SizeNoTag(int i) {
        return 4;
    }

    public static int computeFixed64SizeNoTag(long j) {
        return 8;
    }

    static int computePreferredBufferSize(int i) {
        if (i > 4096) {
            return 4096;
        }
        return i;
    }

    public static int computeUInt32SizeNoTag(int i) {
        if ((i & -128) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) == 0) {
            return 3;
        }
        return (i & -268435456) == 0 ? 4 : 5;
    }

    public static int computeUInt64SizeNoTag(long j) {
        int i;
        if ((-128 & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        if ((-34359738368L & j) != 0) {
            i = 6;
            j >>>= 28;
        } else {
            i = 2;
        }
        if ((-2097152 & j) != 0) {
            i += 2;
            j >>>= 14;
        }
        return (j & -16384) != 0 ? i + 1 : i;
    }

    public abstract void flush() throws IOException;

    public abstract int spaceLeft();

    public abstract void writeBytes(int i, ByteString byteString) throws IOException;

    public abstract void writeFixed32(int i, int i2) throws IOException;

    public abstract void writeFixed64(int i, long j) throws IOException;

    public abstract void writeInt32(int i, int i2) throws IOException;

    public abstract void writeMessage(int i, MessageLite messageLite) throws IOException;

    public abstract void writeString(int i, String str) throws IOException;

    public abstract void writeTag(int i, int i2) throws IOException;

    public abstract void writeUInt32NoTag(int i) throws IOException;

    public abstract void writeUInt64(int i, long j) throws IOException;

    public static CodedOutputStream newInstance(OutputStream outputStream, int i) {
        return new OutputStreamEncoder(outputStream, i);
    }

    public static CodedOutputStream newInstance(byte[] bArr) {
        return newInstance(bArr, 0, bArr.length);
    }

    public static CodedOutputStream newInstance(byte[] bArr, int i, int i2) {
        return new ArrayEncoder(bArr, i, i2);
    }

    private CodedOutputStream() {
    }

    public final void writeEnum(int i, int i2) throws IOException {
        writeInt32(i, i2);
    }

    public static int computeFixed32Size(int i, int i2) {
        return computeTagSize(i) + computeFixed32SizeNoTag(i2);
    }

    public static int computeUInt64Size(int i, long j) {
        return computeTagSize(i) + computeUInt64SizeNoTag(j);
    }

    public static int computeFixed64Size(int i, long j) {
        return computeTagSize(i) + computeFixed64SizeNoTag(j);
    }

    public static int computeEnumSize(int i, int i2) {
        return computeTagSize(i) + computeEnumSizeNoTag(i2);
    }

    public static int computeStringSize(int i, String str) {
        return computeTagSize(i) + computeStringSizeNoTag(str);
    }

    public static int computeBytesSize(int i, ByteString byteString) {
        return computeTagSize(i) + computeBytesSizeNoTag(byteString);
    }

    public static int computeMessageSize(int i, MessageLite messageLite) {
        return computeTagSize(i) + computeMessageSizeNoTag(messageLite);
    }

    public static int computeTagSize(int i) {
        return computeUInt32SizeNoTag(WireFormat.makeTag(i, 0));
    }

    public static int computeInt32SizeNoTag(int i) {
        if (i >= 0) {
            return computeUInt32SizeNoTag(i);
        }
        return 10;
    }

    public static int computeEnumSizeNoTag(int i) {
        return computeInt32SizeNoTag(i);
    }

    public static int computeStringSizeNoTag(String str) {
        int i;
        try {
            i = Utf8.encodedLength(str);
        } catch (Utf8.UnpairedSurrogateException unused) {
            i = str.getBytes(Internal.UTF_8).length;
        }
        return computeLengthDelimitedFieldSize(i);
    }

    public static int computeBytesSizeNoTag(ByteString byteString) {
        return computeLengthDelimitedFieldSize(byteString.size());
    }

    public static int computeMessageSizeNoTag(MessageLite messageLite) {
        return computeLengthDelimitedFieldSize(messageLite.getSerializedSize());
    }

    private static int computeLengthDelimitedFieldSize(int i) {
        return computeUInt32SizeNoTag(i) + i;
    }

    public final void checkNoSpaceLeft() {
        if (spaceLeft() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public static class OutOfSpaceException extends IOException {
        private static final long serialVersionUID = -6947486886997889499L;

        OutOfSpaceException(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }
    }

    /* access modifiers changed from: package-private */
    public final void inefficientWriteStringNoTag(String str, Utf8.UnpairedSurrogateException unpairedSurrogateException) throws IOException {
        logger.log(Level.WARNING, "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) unpairedSurrogateException);
        byte[] bytes = str.getBytes(Internal.UTF_8);
        try {
            writeUInt32NoTag(bytes.length);
            writeLazy(bytes, 0, bytes.length);
        } catch (IndexOutOfBoundsException e) {
            throw new OutOfSpaceException(e);
        } catch (OutOfSpaceException e2) {
            throw e2;
        }
    }

    @Deprecated
    public final void writeRawVarint32(int i) throws IOException {
        writeUInt32NoTag(i);
    }

    @Deprecated
    public static int computeRawVarint32Size(int i) {
        return computeUInt32SizeNoTag(i);
    }

    /* access modifiers changed from: private */
    public static class ArrayEncoder extends CodedOutputStream {
        private final byte[] buffer;
        private final int limit;
        private final int offset;
        private int position;

        @Override // com.google.protobuf.CodedOutputStream
        public void flush() {
        }

        ArrayEncoder(byte[] bArr, int i, int i2) {
            super();
            if (bArr != null) {
                int i3 = i + i2;
                if ((i | i2 | (bArr.length - i3)) >= 0) {
                    this.buffer = bArr;
                    this.offset = i;
                    this.position = i;
                    this.limit = i3;
                    return;
                }
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)));
            }
            throw new NullPointerException("buffer");
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeTag(int i, int i2) throws IOException {
            writeUInt32NoTag(WireFormat.makeTag(i, i2));
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeInt32(int i, int i2) throws IOException {
            writeTag(i, 0);
            writeInt32NoTag(i2);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeFixed32(int i, int i2) throws IOException {
            writeTag(i, 5);
            writeFixed32NoTag(i2);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeUInt64(int i, long j) throws IOException {
            writeTag(i, 0);
            writeUInt64NoTag(j);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeFixed64(int i, long j) throws IOException {
            writeTag(i, 1);
            writeFixed64NoTag(j);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeString(int i, String str) throws IOException {
            writeTag(i, 2);
            writeStringNoTag(str);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeBytes(int i, ByteString byteString) throws IOException {
            writeTag(i, 2);
            writeBytesNoTag(byteString);
        }

        public final void writeBytesNoTag(ByteString byteString) throws IOException {
            writeUInt32NoTag(byteString.size());
            byteString.writeTo(this);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeMessage(int i, MessageLite messageLite) throws IOException {
            writeTag(i, 2);
            writeMessageNoTag(messageLite);
        }

        public final void writeMessageNoTag(MessageLite messageLite) throws IOException {
            writeUInt32NoTag(messageLite.getSerializedSize());
            messageLite.writeTo(this);
        }

        public final void writeInt32NoTag(int i) throws IOException {
            if (i >= 0) {
                writeUInt32NoTag(i);
            } else {
                writeUInt64NoTag((long) i);
            }
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeUInt32NoTag(int i) throws IOException {
            if (!CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS || spaceLeft() < 10) {
                while ((i & -128) != 0) {
                    byte[] bArr = this.buffer;
                    int i2 = this.position;
                    this.position = i2 + 1;
                    bArr[i2] = (byte) ((i & 127) | DNSSD.REGISTRATION_DOMAINS);
                    i >>>= 7;
                }
                try {
                    byte[] bArr2 = this.buffer;
                    int i3 = this.position;
                    this.position = i3 + 1;
                    bArr2[i3] = (byte) i;
                } catch (IndexOutOfBoundsException unused) {
                    throw new OutOfSpaceException(new IndexOutOfBoundsException(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1)));
                }
            } else {
                long j = CodedOutputStream.ARRAY_BASE_OFFSET + ((long) this.position);
                while ((i & -128) != 0) {
                    CodedOutputStream.UNSAFE.putByte(this.buffer, j, (byte) ((i & 127) | DNSSD.REGISTRATION_DOMAINS));
                    this.position++;
                    i >>>= 7;
                    j = 1 + j;
                }
                CodedOutputStream.UNSAFE.putByte(this.buffer, j, (byte) i);
                this.position++;
            }
        }

        public final void writeFixed32NoTag(int i) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                bArr[i2] = (byte) (i & 255);
                byte[] bArr2 = this.buffer;
                int i3 = this.position;
                this.position = i3 + 1;
                bArr2[i3] = (byte) ((i >> 8) & 255);
                byte[] bArr3 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                bArr3[i4] = (byte) ((i >> 16) & 255);
                byte[] bArr4 = this.buffer;
                int i5 = this.position;
                this.position = i5 + 1;
                bArr4[i5] = (byte) ((i >> 24) & 255);
            } catch (IndexOutOfBoundsException unused) {
                throw new OutOfSpaceException(new IndexOutOfBoundsException(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1)));
            }
        }

        public final void writeUInt64NoTag(long j) throws IOException {
            if (!CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS || spaceLeft() < 10) {
                while ((j & -128) != 0) {
                    byte[] bArr = this.buffer;
                    int i = this.position;
                    this.position = i + 1;
                    bArr[i] = (byte) ((((int) j) & 127) | DNSSD.REGISTRATION_DOMAINS);
                    j >>>= 7;
                }
                try {
                    byte[] bArr2 = this.buffer;
                    int i2 = this.position;
                    this.position = i2 + 1;
                    bArr2[i2] = (byte) ((int) j);
                } catch (IndexOutOfBoundsException unused) {
                    throw new OutOfSpaceException(new IndexOutOfBoundsException(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1)));
                }
            } else {
                long j2 = CodedOutputStream.ARRAY_BASE_OFFSET + ((long) this.position);
                while ((j & -128) != 0) {
                    CodedOutputStream.UNSAFE.putByte(this.buffer, j2, (byte) ((((int) j) & 127) | DNSSD.REGISTRATION_DOMAINS));
                    this.position++;
                    j >>>= 7;
                    j2 = 1 + j2;
                }
                CodedOutputStream.UNSAFE.putByte(this.buffer, j2, (byte) ((int) j));
                this.position++;
            }
        }

        public final void writeFixed64NoTag(long j) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                this.position = i + 1;
                bArr[i] = (byte) (((int) j) & 255);
                byte[] bArr2 = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                bArr2[i2] = (byte) (((int) (j >> 8)) & 255);
                byte[] bArr3 = this.buffer;
                int i3 = this.position;
                this.position = i3 + 1;
                bArr3[i3] = (byte) (((int) (j >> 16)) & 255);
                byte[] bArr4 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                bArr4[i4] = (byte) (((int) (j >> 24)) & 255);
                byte[] bArr5 = this.buffer;
                int i5 = this.position;
                this.position = i5 + 1;
                bArr5[i5] = (byte) (((int) (j >> 32)) & 255);
                byte[] bArr6 = this.buffer;
                int i6 = this.position;
                this.position = i6 + 1;
                bArr6[i6] = (byte) (((int) (j >> 40)) & 255);
                byte[] bArr7 = this.buffer;
                int i7 = this.position;
                this.position = i7 + 1;
                bArr7[i7] = (byte) (((int) (j >> 48)) & 255);
                byte[] bArr8 = this.buffer;
                int i8 = this.position;
                this.position = i8 + 1;
                bArr8[i8] = (byte) (((int) (j >> 56)) & 255);
            } catch (IndexOutOfBoundsException unused) {
                throw new OutOfSpaceException(new IndexOutOfBoundsException(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1)));
            }
        }

        public final void write(byte[] bArr, int i, int i2) throws IOException {
            try {
                System.arraycopy(bArr, i, this.buffer, this.position, i2);
                this.position += i2;
            } catch (IndexOutOfBoundsException unused) {
                throw new OutOfSpaceException(new IndexOutOfBoundsException(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(i2))));
            }
        }

        @Override // com.google.protobuf.ByteOutput
        public final void writeLazy(byte[] bArr, int i, int i2) throws IOException {
            write(bArr, i, i2);
        }

        public final void writeStringNoTag(String str) throws IOException {
            int i = this.position;
            try {
                int computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(str.length() * 3);
                int computeUInt32SizeNoTag2 = CodedOutputStream.computeUInt32SizeNoTag(str.length());
                if (computeUInt32SizeNoTag2 == computeUInt32SizeNoTag) {
                    this.position = i + computeUInt32SizeNoTag2;
                    int encode = Utf8.encode(str, this.buffer, this.position, spaceLeft());
                    this.position = i;
                    writeUInt32NoTag((encode - i) - computeUInt32SizeNoTag2);
                    this.position = encode;
                    return;
                }
                writeUInt32NoTag(Utf8.encodedLength(str));
                this.position = Utf8.encode(str, this.buffer, this.position, spaceLeft());
            } catch (Utf8.UnpairedSurrogateException e) {
                this.position = i;
                inefficientWriteStringNoTag(str, e);
            } catch (IndexOutOfBoundsException e2) {
                throw new OutOfSpaceException(e2);
            }
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final int spaceLeft() {
            return this.limit - this.position;
        }
    }

    /* access modifiers changed from: private */
    public static abstract class AbstractBufferedEncoder extends CodedOutputStream {
        final byte[] buffer;
        final int limit;
        int position;
        int totalBytesWritten;

        AbstractBufferedEncoder(int i) {
            super();
            if (i >= 0) {
                this.buffer = new byte[Math.max(i, 20)];
                this.limit = this.buffer.length;
                return;
            }
            throw new IllegalArgumentException("bufferSize must be >= 0");
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final int spaceLeft() {
            throw new UnsupportedOperationException("spaceLeft() can only be called on CodedOutputStreams that are writing to a flat array or ByteBuffer.");
        }

        /* access modifiers changed from: package-private */
        public final void bufferTag(int i, int i2) {
            bufferUInt32NoTag(WireFormat.makeTag(i, i2));
        }

        /* access modifiers changed from: package-private */
        public final void bufferInt32NoTag(int i) {
            if (i >= 0) {
                bufferUInt32NoTag(i);
            } else {
                bufferUInt64NoTag((long) i);
            }
        }

        /* access modifiers changed from: package-private */
        public final void bufferUInt32NoTag(int i) {
            if (CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS) {
                long j = CodedOutputStream.ARRAY_BASE_OFFSET + ((long) this.position);
                long j2 = j;
                while ((i & -128) != 0) {
                    CodedOutputStream.UNSAFE.putByte(this.buffer, j2, (byte) ((i & 127) | DNSSD.REGISTRATION_DOMAINS));
                    i >>>= 7;
                    j2 = 1 + j2;
                }
                CodedOutputStream.UNSAFE.putByte(this.buffer, j2, (byte) i);
                int i2 = (int) ((1 + j2) - j);
                this.position += i2;
                this.totalBytesWritten += i2;
                return;
            }
            while ((i & -128) != 0) {
                byte[] bArr = this.buffer;
                int i3 = this.position;
                this.position = i3 + 1;
                bArr[i3] = (byte) ((i & 127) | DNSSD.REGISTRATION_DOMAINS);
                this.totalBytesWritten++;
                i >>>= 7;
            }
            byte[] bArr2 = this.buffer;
            int i4 = this.position;
            this.position = i4 + 1;
            bArr2[i4] = (byte) i;
            this.totalBytesWritten++;
        }

        /* access modifiers changed from: package-private */
        public final void bufferUInt64NoTag(long j) {
            if (CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS) {
                long j2 = CodedOutputStream.ARRAY_BASE_OFFSET + ((long) this.position);
                long j3 = j;
                long j4 = j2;
                for (long j5 = 0; (j3 & -128) != j5; j5 = 0) {
                    CodedOutputStream.UNSAFE.putByte(this.buffer, j4, (byte) ((((int) j3) & 127) | DNSSD.REGISTRATION_DOMAINS));
                    j3 >>>= 7;
                    j4 = 1 + j4;
                }
                CodedOutputStream.UNSAFE.putByte(this.buffer, j4, (byte) ((int) j3));
                int i = (int) ((1 + j4) - j2);
                this.position += i;
                this.totalBytesWritten += i;
                return;
            }
            long j6 = j;
            while ((j6 & -128) != 0) {
                byte[] bArr = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                bArr[i2] = (byte) ((((int) j6) & 127) | DNSSD.REGISTRATION_DOMAINS);
                this.totalBytesWritten++;
                j6 >>>= 7;
            }
            byte[] bArr2 = this.buffer;
            int i3 = this.position;
            this.position = i3 + 1;
            bArr2[i3] = (byte) ((int) j6);
            this.totalBytesWritten++;
        }

        /* access modifiers changed from: package-private */
        public final void bufferFixed32NoTag(int i) {
            byte[] bArr = this.buffer;
            int i2 = this.position;
            this.position = i2 + 1;
            bArr[i2] = (byte) (i & 255);
            int i3 = this.position;
            this.position = i3 + 1;
            bArr[i3] = (byte) ((i >> 8) & 255);
            int i4 = this.position;
            this.position = i4 + 1;
            bArr[i4] = (byte) ((i >> 16) & 255);
            int i5 = this.position;
            this.position = i5 + 1;
            bArr[i5] = (byte) ((i >> 24) & 255);
            this.totalBytesWritten += 4;
        }

        /* access modifiers changed from: package-private */
        public final void bufferFixed64NoTag(long j) {
            byte[] bArr = this.buffer;
            int i = this.position;
            this.position = i + 1;
            bArr[i] = (byte) ((int) (j & 255));
            int i2 = this.position;
            this.position = i2 + 1;
            bArr[i2] = (byte) ((int) ((j >> 8) & 255));
            int i3 = this.position;
            this.position = i3 + 1;
            bArr[i3] = (byte) ((int) ((j >> 16) & 255));
            int i4 = this.position;
            this.position = i4 + 1;
            bArr[i4] = (byte) ((int) (255 & (j >> 24)));
            int i5 = this.position;
            this.position = i5 + 1;
            bArr[i5] = (byte) (((int) (j >> 32)) & 255);
            int i6 = this.position;
            this.position = i6 + 1;
            bArr[i6] = (byte) (((int) (j >> 40)) & 255);
            int i7 = this.position;
            this.position = i7 + 1;
            bArr[i7] = (byte) (((int) (j >> 48)) & 255);
            int i8 = this.position;
            this.position = i8 + 1;
            bArr[i8] = (byte) (((int) (j >> 56)) & 255);
            this.totalBytesWritten += 8;
        }
    }

    /* access modifiers changed from: private */
    public static final class OutputStreamEncoder extends AbstractBufferedEncoder {
        private final OutputStream out;

        OutputStreamEncoder(OutputStream outputStream, int i) {
            super(i);
            if (outputStream != null) {
                this.out = outputStream;
                return;
            }
            throw new NullPointerException("out");
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeTag(int i, int i2) throws IOException {
            writeUInt32NoTag(WireFormat.makeTag(i, i2));
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeInt32(int i, int i2) throws IOException {
            flushIfNotAvailable(20);
            bufferTag(i, 0);
            bufferInt32NoTag(i2);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeFixed32(int i, int i2) throws IOException {
            flushIfNotAvailable(14);
            bufferTag(i, 5);
            bufferFixed32NoTag(i2);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeUInt64(int i, long j) throws IOException {
            flushIfNotAvailable(20);
            bufferTag(i, 0);
            bufferUInt64NoTag(j);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeFixed64(int i, long j) throws IOException {
            flushIfNotAvailable(18);
            bufferTag(i, 1);
            bufferFixed64NoTag(j);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeString(int i, String str) throws IOException {
            writeTag(i, 2);
            writeStringNoTag(str);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeBytes(int i, ByteString byteString) throws IOException {
            writeTag(i, 2);
            writeBytesNoTag(byteString);
        }

        public void writeBytesNoTag(ByteString byteString) throws IOException {
            writeUInt32NoTag(byteString.size());
            byteString.writeTo(this);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeMessage(int i, MessageLite messageLite) throws IOException {
            writeTag(i, 2);
            writeMessageNoTag(messageLite);
        }

        public void writeMessageNoTag(MessageLite messageLite) throws IOException {
            writeUInt32NoTag(messageLite.getSerializedSize());
            messageLite.writeTo(this);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeUInt32NoTag(int i) throws IOException {
            flushIfNotAvailable(10);
            bufferUInt32NoTag(i);
        }

        public void writeStringNoTag(String str) throws IOException {
            int i;
            try {
                int length = str.length() * 3;
                int computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(length);
                int i2 = computeUInt32SizeNoTag + length;
                if (i2 > this.limit) {
                    byte[] bArr = new byte[length];
                    int encode = Utf8.encode(str, bArr, 0, length);
                    writeUInt32NoTag(encode);
                    writeLazy(bArr, 0, encode);
                    return;
                }
                if (i2 > this.limit - this.position) {
                    doFlush();
                }
                int computeUInt32SizeNoTag2 = CodedOutputStream.computeUInt32SizeNoTag(str.length());
                int i3 = this.position;
                if (computeUInt32SizeNoTag2 == computeUInt32SizeNoTag) {
                    try {
                        this.position = i3 + computeUInt32SizeNoTag2;
                        int encode2 = Utf8.encode(str, this.buffer, this.position, this.limit - this.position);
                        this.position = i3;
                        i = (encode2 - i3) - computeUInt32SizeNoTag2;
                        bufferUInt32NoTag(i);
                        this.position = encode2;
                    } catch (Utf8.UnpairedSurrogateException e) {
                        this.totalBytesWritten -= this.position - i3;
                        this.position = i3;
                        throw e;
                    } catch (ArrayIndexOutOfBoundsException e2) {
                        throw new OutOfSpaceException(e2);
                    }
                } else {
                    i = Utf8.encodedLength(str);
                    bufferUInt32NoTag(i);
                    this.position = Utf8.encode(str, this.buffer, this.position, i);
                }
                this.totalBytesWritten += i;
            } catch (Utf8.UnpairedSurrogateException e3) {
                inefficientWriteStringNoTag(str, e3);
            }
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void flush() throws IOException {
            if (this.position > 0) {
                doFlush();
            }
        }

        public void write(byte[] bArr, int i, int i2) throws IOException {
            int i3 = this.limit;
            int i4 = this.position;
            if (i3 - i4 >= i2) {
                System.arraycopy(bArr, i, this.buffer, i4, i2);
                this.position += i2;
                this.totalBytesWritten += i2;
                return;
            }
            int i5 = i3 - i4;
            System.arraycopy(bArr, i, this.buffer, i4, i5);
            int i6 = i + i5;
            int i7 = i2 - i5;
            this.position = this.limit;
            this.totalBytesWritten += i5;
            doFlush();
            if (i7 <= this.limit) {
                System.arraycopy(bArr, i6, this.buffer, 0, i7);
                this.position = i7;
            } else {
                this.out.write(bArr, i6, i7);
            }
            this.totalBytesWritten += i7;
        }

        @Override // com.google.protobuf.ByteOutput
        public void writeLazy(byte[] bArr, int i, int i2) throws IOException {
            write(bArr, i, i2);
        }

        private void flushIfNotAvailable(int i) throws IOException {
            if (this.limit - this.position < i) {
                doFlush();
            }
        }

        private void doFlush() throws IOException {
            this.out.write(this.buffer, 0, this.position);
            this.position = 0;
        }
    }

    private static Unsafe getUnsafe() {
        Unsafe unsafe;
        try {
            unsafe = (Unsafe) AccessController.doPrivileged(new PrivilegedExceptionAction<Unsafe>() {
                /* class com.google.protobuf.CodedOutputStream.AnonymousClass1 */

                @Override // java.security.PrivilegedExceptionAction
                public Unsafe run() throws Exception {
                    Field[] declaredFields = Unsafe.class.getDeclaredFields();
                    for (Field field : declaredFields) {
                        field.setAccessible(true);
                        Object obj = field.get(null);
                        if (Unsafe.class.isInstance(obj)) {
                            return (Unsafe) Unsafe.class.cast(obj);
                        }
                    }
                    return null;
                }
            });
        } catch (Throwable unused) {
            unsafe = null;
        }
        logger.log(Level.FINEST, "sun.misc.Unsafe: {}", unsafe != null ? "available" : "unavailable");
        return unsafe;
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x003b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean supportsUnsafeArrayOperations() {
        /*
            sun.misc.Unsafe r0 = com.google.protobuf.CodedOutputStream.UNSAFE
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0031
            java.lang.Class r0 = r0.getClass()     // Catch:{ all -> 0x0031 }
            java.lang.String r3 = "arrayBaseOffset"
            java.lang.Class[] r4 = new java.lang.Class[r1]     // Catch:{ all -> 0x0031 }
            java.lang.Class<java.lang.Class> r5 = java.lang.Class.class
            r4[r2] = r5     // Catch:{ all -> 0x0031 }
            r0.getMethod(r3, r4)     // Catch:{ all -> 0x0031 }
            sun.misc.Unsafe r0 = com.google.protobuf.CodedOutputStream.UNSAFE     // Catch:{ all -> 0x0031 }
            java.lang.Class r0 = r0.getClass()     // Catch:{ all -> 0x0031 }
            java.lang.String r3 = "putByte"
            r4 = 3
            java.lang.Class[] r4 = new java.lang.Class[r4]     // Catch:{ all -> 0x0031 }
            java.lang.Class<java.lang.Object> r5 = java.lang.Object.class
            r4[r2] = r5     // Catch:{ all -> 0x0031 }
            java.lang.Class r5 = java.lang.Long.TYPE     // Catch:{ all -> 0x0031 }
            r4[r1] = r5     // Catch:{ all -> 0x0031 }
            r5 = 2
            java.lang.Class r6 = java.lang.Byte.TYPE     // Catch:{ all -> 0x0031 }
            r4[r5] = r6     // Catch:{ all -> 0x0031 }
            r0.getMethod(r3, r4)     // Catch:{ all -> 0x0031 }
            goto L_0x0032
        L_0x0031:
            r1 = r2
        L_0x0032:
            java.util.logging.Logger r0 = com.google.protobuf.CodedOutputStream.logger
            java.util.logging.Level r2 = java.util.logging.Level.FINEST
            if (r1 == 0) goto L_0x003b
            java.lang.String r3 = "available"
            goto L_0x003d
        L_0x003b:
            java.lang.String r3 = "unavailable"
        L_0x003d:
            java.lang.String r4 = "Unsafe array operations: {}"
            r0.log(r2, r4, r3)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedOutputStream.supportsUnsafeArrayOperations():boolean");
    }

    private static <T> int byteArrayBaseOffset() {
        if (HAS_UNSAFE_ARRAY_OPERATIONS) {
            return UNSAFE.arrayBaseOffset(byte[].class);
        }
        return -1;
    }
}
