package X;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/* renamed from: X.0l6  reason: invalid class name */
public class AnonymousClass0l6 implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.soloader.UnpackingSoSource$1";
    public final /* synthetic */ C03040ks A00;
    public final /* synthetic */ AnonymousClass0l8 A01;
    public final /* synthetic */ AnonymousClass0T3 A02;
    public final /* synthetic */ File A03;
    public final /* synthetic */ File A04;
    public final /* synthetic */ byte[] A05;

    public AnonymousClass0l6(AnonymousClass0T3 r1, File file, byte[] bArr, AnonymousClass0l8 r4, File file2, C03040ks r6) {
        this.A02 = r1;
        this.A03 = file;
        this.A05 = bArr;
        this.A01 = r4;
        this.A04 = file2;
        this.A00 = r6;
    }

    public final void run() {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(this.A03, "rw");
            try {
                randomAccessFile.write(this.A05);
                randomAccessFile.setLength(randomAccessFile.getFilePointer());
                randomAccessFile.close();
                File file = ((AnonymousClass0jM) this.A02).A00;
                RandomAccessFile randomAccessFile2 = new RandomAccessFile(new File(file, "dso_manifest"), "rw");
                try {
                    AnonymousClass0l8 r1 = this.A01;
                    randomAccessFile2.writeByte(1);
                    AnonymousClass0l7[] r3 = r1.A00;
                    int length = r3.length;
                    randomAccessFile2.writeInt(length);
                    for (int i = 0; i < length; i++) {
                        randomAccessFile2.writeUTF(r3[i].A01);
                        randomAccessFile2.writeUTF(r3[i].A00);
                    }
                    randomAccessFile2.close();
                    A00(file);
                    AnonymousClass0T3.A00(this.A04, (byte) 1);
                    try {
                        return;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } catch (Throwable unused) {
                }
                throw th;
                throw th;
            } catch (Throwable unused2) {
            }
        } finally {
            this.A00.close();
        }
    }

    public static void A00(File file) throws IOException {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    A00(file2);
                }
                return;
            }
            StringBuilder sb = new StringBuilder("cannot list directory ");
            sb.append(file);
            throw new IOException(sb.toString());
        } else if (!file.getPath().endsWith("_lock")) {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            try {
                randomAccessFile.getFD().sync();
                randomAccessFile.close();
                return;
            } catch (Throwable unused) {
            }
        } else {
            return;
        }
        throw th;
    }
}
