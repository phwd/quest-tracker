package com.facebook.soloader;

import com.facebook.soloader.m;
import java.io.DataOutput;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/* access modifiers changed from: package-private */
public final class n implements Runnable {
    private /* synthetic */ File a;
    private /* synthetic */ byte[] b;
    private /* synthetic */ m.b c;
    private /* synthetic */ File d;
    private /* synthetic */ f e;
    private /* synthetic */ m f;

    n(m mVar, File file, byte[] bArr, m.b bVar, File file2, f fVar) {
        this.f = mVar;
        this.a = file;
        this.b = bArr;
        this.c = bVar;
        this.d = file2;
        this.e = fVar;
    }

    public final void run() {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(this.a, "rw");
            try {
                randomAccessFile.write(this.b);
                randomAccessFile.setLength(randomAccessFile.getFilePointer());
                randomAccessFile.close();
                RandomAccessFile randomAccessFile2 = new RandomAccessFile(new File(this.f.a, "dso_manifest"), "rw");
                try {
                    this.c.a((DataOutput) randomAccessFile2);
                    randomAccessFile2.close();
                    SysUtil.b(this.f.a);
                    m.b(this.d, (byte) 1);
                    try {
                        return;
                    } catch (IOException e2) {
                        throw new RuntimeException(e2);
                    }
                } catch (Throwable unused) {
                }
                throw th;
                throw th;
            } catch (Throwable unused2) {
            }
        } finally {
            StringBuilder sb = new StringBuilder("releasing dso store lock for ");
            sb.append(this.f.a);
            sb.append(" (from syncer thread)");
            this.e.close();
        }
    }
}
