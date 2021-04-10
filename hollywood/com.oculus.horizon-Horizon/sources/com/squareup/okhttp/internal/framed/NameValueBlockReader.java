package com.squareup.okhttp.internal.framed;

import X.AnonymousClass006;
import X.AnonymousClass0B3;
import X.AnonymousClass0Lq;
import X.AnonymousClass0Lt;
import X.AnonymousClass0Lw;
import X.C00560Au;
import X.C07700vD;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class NameValueBlockReader {
    public int compressedLimit;
    public final AnonymousClass0Lq inflaterSource;
    public final AnonymousClass0Lw source;

    private void doneReading() throws IOException {
        if (this.compressedLimit > 0) {
            this.inflaterSource.A00();
            int i = this.compressedLimit;
            if (i != 0) {
                throw new IOException(AnonymousClass006.A01("compressedLimit > 0: ", i));
            }
        }
    }

    private C07700vD readByteString() throws IOException {
        return this.source.A7t((long) this.source.readInt());
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
                C07700vD A0D = readByteString().A0D();
                C07700vD readByteString = readByteString();
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

    public NameValueBlockReader(AnonymousClass0Lw r4) {
        AnonymousClass1 r1 = new AnonymousClass0Lt(r4) {
            /* class com.squareup.okhttp.internal.framed.NameValueBlockReader.AnonymousClass1 */

            @Override // X.AbstractC07630v6, X.AnonymousClass0Lt
            public long read(AnonymousClass0B3 r7, long j) throws IOException {
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
        AnonymousClass0Lq r12 = new AnonymousClass0Lq(new C00560Au(r1), new Inflater() {
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
        this.source = new C00560Au(r12);
    }
}
