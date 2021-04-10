package X;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/* renamed from: X.0cQ  reason: invalid class name and case insensitive filesystem */
public class RunnableC03200cQ implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.soloader.UnpackingSoSource$1";
    public final /* synthetic */ C03110cA A00;
    public final /* synthetic */ C03220cS A01;
    public final /* synthetic */ AnonymousClass0HU A02;
    public final /* synthetic */ File A03;
    public final /* synthetic */ File A04;
    public final /* synthetic */ byte[] A05;

    public RunnableC03200cQ(AnonymousClass0HU r1, File file, byte[] bArr, C03220cS r4, File file2, C03110cA r6) {
        this.A02 = r1;
        this.A03 = file;
        this.A05 = bArr;
        this.A01 = r4;
        this.A04 = file2;
        this.A00 = r6;
    }

    public final void run() {
        Throwable th;
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(this.A03, "rw");
            try {
                randomAccessFile.write(this.A05);
                randomAccessFile.setLength(randomAccessFile.getFilePointer());
                randomAccessFile.close();
                File file = ((C04530hv) this.A02).A00;
                RandomAccessFile randomAccessFile2 = new RandomAccessFile(new File(file, "dso_manifest"), "rw");
                try {
                    C03220cS r1 = this.A01;
                    randomAccessFile2.writeByte(1);
                    C03210cR[] r3 = r1.A00;
                    int length = r3.length;
                    randomAccessFile2.writeInt(length);
                    for (int i = 0; i < length; i++) {
                        randomAccessFile2.writeUTF(r3[i].A01);
                        randomAccessFile2.writeUTF(r3[i].A00);
                    }
                    randomAccessFile2.close();
                    A00(file);
                    AnonymousClass0HU.A00(this.A04, (byte) 1);
                    try {
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } catch (Throwable unused) {
                }
            } catch (Throwable th2) {
                th = th2;
                randomAccessFile.close();
                throw th;
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
