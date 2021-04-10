package com.oculus.companion.bletransport;

import android.bluetooth.BluetoothGattCharacteristic;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.util.NoSuchElementException;
import java.util.concurrent.LinkedBlockingDeque;

public class AsyncBLETransport {
    private static final String TAG = "com.oculus.companion.bletransport.AsyncBLETransport";
    protected static final byte[] TERMINATOR = {-1};
    protected BluetoothGattCharacteristic characteristic;
    protected int maxMtu = 20;
    protected final LinkedBlockingDeque<ReadBuffer> readQ = new LinkedBlockingDeque<>();
    protected final LinkedBlockingDeque<WriteBuffer> writeQ = new LinkedBlockingDeque<>();

    /* access modifiers changed from: protected */
    public static class Chunk {
        public byte[] data;
        public int sequence;

        protected Chunk() {
        }
    }

    /* access modifiers changed from: protected */
    public static class WriteBuffer {
        public Chunk[] writeBuffer;

        WriteBuffer(Chunk[] chunkArr) {
            this.writeBuffer = chunkArr;
        }
    }

    protected static class ReadBuffer {
        public int current_seq_num = -1;
        public boolean inprogress = true;
        public final ByteArrayOutputStream readBuffer = new ByteArrayOutputStream();

        protected ReadBuffer() {
        }

        /* access modifiers changed from: package-private */
        public boolean isReady() {
            return !this.inprogress;
        }

        /* access modifiers changed from: package-private */
        public void append(byte[] bArr, int i, boolean z) {
            if (z && !this.inprogress) {
                throw new IllegalStateException("Premature end of sequence!!!!");
            } else if (i == this.current_seq_num + 1) {
                if (bArr.length > 0) {
                    this.readBuffer.write(bArr, 2, bArr.length - 2);
                }
                if (z) {
                    this.inprogress = false;
                }
                this.current_seq_num++;
            } else {
                throw new IllegalStateException("Exceeded Sequence MAX!!!!(seqnum/current_seq_num)=" + i + "/" + this.current_seq_num);
            }
        }

        /* access modifiers changed from: package-private */
        public byte[] getBuffer() {
            return this.readBuffer.toByteArray();
        }
    }

    public AsyncBLETransport() {
    }

    public AsyncBLETransport(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        this.characteristic = bluetoothGattCharacteristic;
    }

    public void setMtu(int i) {
        if (i > 2) {
            this.maxMtu = i;
            return;
        }
        throw new IllegalArgumentException("MTU must be greater than header length");
    }

    public void send(byte[] bArr) throws InterruptedException {
        synchronized (this.writeQ) {
            this.writeQ.addLast(prepare(bArr));
            while (!this.writeQ.isEmpty()) {
                write();
            }
        }
    }

    public void dropFirst() {
        try {
            synchronized (this.writeQ) {
                this.writeQ.removeFirst();
            }
        } catch (NoSuchElementException unused) {
        }
    }

    /* access modifiers changed from: protected */
    public void write() throws InterruptedException {
        if (!this.writeQ.isEmpty()) {
            WriteBuffer peekFirst = this.writeQ.peekFirst();
            for (int i = 0; i < peekFirst.writeBuffer.length; i++) {
                try {
                    synchronized (this.characteristic) {
                        this.characteristic.setValue(peekFirst.writeBuffer[i].data);
                        this.characteristic.wait(15000);
                    }
                } catch (InterruptedException unused) {
                    String str = TAG;
                    Log.e(str, "Thread interrupted while sending data on CCS: " + this.characteristic.getUuid().toString());
                    this.characteristic.setValue(TERMINATOR);
                    throw new InterruptedException();
                }
            }
            this.characteristic.setValue(TERMINATOR);
            try {
                this.writeQ.removeFirst();
            } catch (NoSuchElementException unused2) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public WriteBuffer prepare(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            throw new IllegalArgumentException("input is zero length or null");
        }
        int i = this.maxMtu - 2;
        int length = ((bArr.length + i) - 1) / i;
        Chunk[] chunkArr = new Chunk[length];
        int i2 = i;
        byte b = 0;
        for (int i3 = 0; i3 < length; i3++) {
            int i4 = length - 1;
            if (i3 == i4) {
                i2 = bArr.length - (i4 * i);
                b = Byte.MIN_VALUE;
            }
            chunkArr[i3] = new Chunk();
            chunkArr[i3].data = new byte[(i2 + 2)];
            chunkArr[i3].data[0] = (byte) (((byte) ((i3 & 32512) >> 8)) | b);
            chunkArr[i3].data[1] = (byte) (i3 & 255);
            System.arraycopy(bArr, i3 * i, chunkArr[i3].data, 2, i2);
            chunkArr[i3].sequence = i3;
        }
        return new WriteBuffer(chunkArr);
    }

    /* access modifiers changed from: protected */
    public void read(byte[] bArr) {
        if (bArr.length >= 2) {
            boolean z = (bArr[0] & 128) > 0;
            int i = ((bArr[0] & Byte.MAX_VALUE) << 8) | ((bArr[1] << 24) >>> 24);
            synchronized (this.readQ) {
                if (!this.readQ.isEmpty()) {
                    if (!this.readQ.peekLast().isReady()) {
                        this.readQ.peekLast().append(bArr, i, z);
                        this.readQ.notify();
                    }
                }
                if (i == 0) {
                    ReadBuffer readBuffer = new ReadBuffer();
                    readBuffer.append(bArr, i, z);
                    this.readQ.addLast(readBuffer);
                    this.readQ.notify();
                } else {
                    throw new IllegalStateException("Bad start of sequence");
                }
            }
            return;
        }
        throw new IllegalStateException("Invalid size received");
    }

    public byte[] receive() throws InterruptedException {
        ReadBuffer removeFirst;
        synchronized (this.readQ) {
            while (true) {
                if (!this.readQ.isEmpty()) {
                    if (this.readQ.peekFirst().isReady()) {
                        removeFirst = this.readQ.removeFirst();
                    }
                }
                this.readQ.wait();
            }
        }
        return removeFirst.getBuffer();
    }

    public void reset() {
        this.readQ.clear();
        setMtu(20);
    }
}
