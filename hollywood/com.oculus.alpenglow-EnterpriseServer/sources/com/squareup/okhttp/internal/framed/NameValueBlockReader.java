package com.squareup.okhttp.internal.framed;

import X.AnonymousClass006;
import X.AnonymousClass0HO;
import X.AnonymousClass0HR;
import X.AnonymousClass0OU;
import X.AnonymousClass0OY;
import X.AnonymousClass0Od;
import X.C04610h7;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class NameValueBlockReader {
    public int compressedLimit;
    public final AnonymousClass0OU inflaterSource;
    public final AnonymousClass0Od source;

    private void doneReading() throws IOException {
        if (this.compressedLimit > 0) {
            this.inflaterSource.A00();
            int i = this.compressedLimit;
            if (i != 0) {
                throw new IOException(AnonymousClass006.A01("compressedLimit > 0: ", i));
            }
        }
    }

    private C04610h7 readByteString() throws IOException {
        return this.source.A7B((long) this.source.readInt());
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
                C04610h7 A0D = readByteString().A0D();
                C04610h7 readByteString = readByteString();
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
        str = AnonymousClass006.A01(str2, readInt);
        throw new IOException(str);
    }

    public NameValueBlockReader(AnonymousClass0Od r4) {
        AnonymousClass1 r1 = new AnonymousClass0OY(r4) {
            /* class com.squareup.okhttp.internal.framed.NameValueBlockReader.AnonymousClass1 */

            @Override // X.AnonymousClass0OY, X.AbstractC04550h0
            public long read(AnonymousClass0HR r7, long j) throws IOException {
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
        AnonymousClass0OU r12 = new AnonymousClass0OU(new AnonymousClass0HO(r1), new Inflater() {
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
        this.inflaterSource = r12;
        this.source = new AnonymousClass0HO(r12);
    }
}
