package com.squareup.okhttp.internal.framed;

import X.AnonymousClass006;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.ForwardingSource;
import okio.InflaterSource;
import okio.Okio;

public class NameValueBlockReader {
    public int compressedLimit;
    public final InflaterSource inflaterSource;
    public final BufferedSource source;

    private void doneReading() throws IOException {
        if (this.compressedLimit > 0) {
            this.inflaterSource.refill();
            int i = this.compressedLimit;
            if (i != 0) {
                throw new IOException(AnonymousClass006.A03("compressedLimit > 0: ", i));
            }
        }
    }

    private ByteString readByteString() throws IOException {
        return this.source.readByteString((long) this.source.readInt());
    }

    public void close() throws IOException {
        this.source.close();
    }

    public List<Header> readNameValueBlock(int i) throws IOException {
        this.compressedLimit += i;
        int readInt = this.source.readInt();
        if (readInt < 0) {
            throw new IOException(AnonymousClass006.A03("numberOfPairs < 0: ", readInt));
        } else if (readInt <= 1024) {
            ArrayList arrayList = new ArrayList(readInt);
            for (int i2 = 0; i2 < readInt; i2++) {
                ByteString asciiLowercase = readByteString().toAsciiLowercase();
                ByteString readByteString = readByteString();
                if (asciiLowercase.size() != 0) {
                    arrayList.add(new Header(asciiLowercase, readByteString));
                } else {
                    throw new IOException("name.size == 0");
                }
            }
            doneReading();
            return arrayList;
        } else {
            throw new IOException(AnonymousClass006.A03("numberOfPairs > 1024: ", readInt));
        }
    }

    public NameValueBlockReader(BufferedSource bufferedSource) {
        InflaterSource inflaterSource2 = new InflaterSource(new ForwardingSource(bufferedSource) {
            /* class com.squareup.okhttp.internal.framed.NameValueBlockReader.AnonymousClass1 */

            @Override // okio.Source, okio.ForwardingSource
            public long read(Buffer buffer, long j) throws IOException {
                int i = NameValueBlockReader.this.compressedLimit;
                if (i != 0) {
                    long read = super.read(buffer, Math.min(j, (long) i));
                    if (read != -1) {
                        NameValueBlockReader nameValueBlockReader = NameValueBlockReader.this;
                        nameValueBlockReader.compressedLimit = (int) (((long) nameValueBlockReader.compressedLimit) - read);
                        return read;
                    }
                }
                return -1;
            }
        }, new Inflater() {
            /* class com.squareup.okhttp.internal.framed.NameValueBlockReader.AnonymousClass2 */

            @Override // java.util.zip.Inflater
            public int inflate(byte[] bArr, int i, int i2) throws DataFormatException {
                int inflate = super.inflate(bArr, i, i2);
                if (inflate != 0 || !needsDictionary()) {
                    return inflate;
                }
                setDictionary(Spdy3.DICTIONARY);
                return super.inflate(bArr, i, i2);
            }
        });
        this.inflaterSource = inflaterSource2;
        this.source = Okio.buffer(inflaterSource2);
    }
}
