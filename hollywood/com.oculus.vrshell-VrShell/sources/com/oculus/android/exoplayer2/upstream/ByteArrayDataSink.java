package com.oculus.android.exoplayer2.upstream;

import com.oculus.android.exoplayer2.util.Assertions;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public final class ByteArrayDataSink implements DataSink {
    private ByteArrayOutputStream stream;

    @Override // com.oculus.android.exoplayer2.upstream.DataSink
    public void open(DataSpec dataSpec) throws IOException {
        if (dataSpec.length == -1) {
            this.stream = new ByteArrayOutputStream();
            return;
        }
        Assertions.checkArgument(dataSpec.length <= 2147483647L);
        this.stream = new ByteArrayOutputStream((int) dataSpec.length);
    }

    @Override // com.oculus.android.exoplayer2.upstream.DataSink
    public void close() throws IOException {
        this.stream.close();
    }

    @Override // com.oculus.android.exoplayer2.upstream.DataSink
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.stream.write(bArr, i, i2);
    }

    public byte[] getData() {
        ByteArrayOutputStream byteArrayOutputStream = this.stream;
        if (byteArrayOutputStream == null) {
            return null;
        }
        return byteArrayOutputStream.toByteArray();
    }
}
