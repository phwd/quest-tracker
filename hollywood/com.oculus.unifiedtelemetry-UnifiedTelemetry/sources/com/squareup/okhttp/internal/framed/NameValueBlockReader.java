package com.squareup.okhttp.internal.framed;

import X.AnonymousClass06;
import X.AnonymousClass93;
import X.AnonymousClass98;
import X.K0;
import X.K5;
import X.KC;
import X.ci;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class NameValueBlockReader {
    public int compressedLimit;
    public final K0 inflaterSource;
    public final KC source;

    private void doneReading() throws IOException {
        if (this.compressedLimit > 0) {
            this.inflaterSource.A00();
            int i = this.compressedLimit;
            if (i != 0) {
                throw new IOException(AnonymousClass06.A01("compressedLimit > 0: ", i));
            }
        }
    }

    private ci readByteString() throws IOException {
        return this.source.A4U((long) this.source.readInt());
    }

    public void close() throws IOException {
        this.source.close();
    }

    public List<Header> readNameValueBlock(int i) throws IOException {
        String str;
        String str2;
        this.compressedLimit += i;
        int readInt = this.source.readInt();
        if (readInt < 0) {
            str2 = "numberOfPairs < 0: ";
        } else if (readInt <= 1024) {
            ArrayList arrayList = new ArrayList(readInt);
            for (int i2 = 0; i2 < readInt; i2++) {
                ci A0D = readByteString().A0D();
                ci readByteString = readByteString();
                if (A0D.A07() != 0) {
                    arrayList.add(new Header(A0D, readByteString));
                } else {
                    str = "name.size == 0";
                    throw new IOException(str);
                }
            }
            doneReading();
            return arrayList;
        } else {
            str2 = "numberOfPairs > 1024: ";
        }
        str = AnonymousClass06.A01(str2, readInt);
        throw new IOException(str);
    }

    public NameValueBlockReader(KC kc) {
        AnonymousClass1 r1 = new K5(kc) {
            /* class com.squareup.okhttp.internal.framed.NameValueBlockReader.AnonymousClass1 */

            @Override // X.K5, X.AbstractC0312cb
            public long read(AnonymousClass98 r7, long j) throws IOException {
                int i = NameValueBlockReader.this.compressedLimit;
                if (i != 0) {
                    long read = super.read(r7, Math.min(j, (long) i));
                    if (read != -1) {
                        NameValueBlockReader nameValueBlockReader = NameValueBlockReader.this;
                        nameValueBlockReader.compressedLimit = (int) (((long) nameValueBlockReader.compressedLimit) - read);
                        return read;
                    }
                }
                return -1;
            }
        };
        K0 k0 = new K0(new AnonymousClass93(r1), new Inflater() {
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
        this.inflaterSource = k0;
        this.source = new AnonymousClass93(k0);
    }
}
