package X;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/* renamed from: X.rt  reason: case insensitive filesystem */
public class RunnableC0487rt implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.soloader.UnpackingSoSource$1";
    public final /* synthetic */ C0505sZ A00;
    public final /* synthetic */ C0518t2 A01;
    public final /* synthetic */ AbstractC0486rs A02;
    public final /* synthetic */ File A03;
    public final /* synthetic */ File A04;
    public final /* synthetic */ byte[] A05;

    public RunnableC0487rt(AbstractC0486rs rsVar, File file, byte[] bArr, C0518t2 t2Var, File file2, C0505sZ sZVar) {
        this.A02 = rsVar;
        this.A03 = file;
        this.A05 = bArr;
        this.A01 = t2Var;
        this.A04 = file2;
        this.A00 = sZVar;
    }

    public final void run() {
        Throwable th;
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(this.A03, "rw");
            try {
                randomAccessFile.write(this.A05);
                randomAccessFile.setLength(randomAccessFile.getFilePointer());
                randomAccessFile.close();
                File file = ((C0485rr) this.A02).A00;
                RandomAccessFile randomAccessFile2 = new RandomAccessFile(new File(file, "dso_manifest"), "rw");
                try {
                    C0518t2 t2Var = this.A01;
                    randomAccessFile2.writeByte(1);
                    C0519t9[] t9VarArr = t2Var.A00;
                    int length = t9VarArr.length;
                    randomAccessFile2.writeInt(length);
                    for (int i = 0; i < length; i++) {
                        randomAccessFile2.writeUTF(t9VarArr[i].A01);
                        randomAccessFile2.writeUTF(t9VarArr[i].A00);
                    }
                    randomAccessFile2.close();
                    A00(file);
                    AbstractC0486rs.A04(this.A04, (byte) 1);
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
