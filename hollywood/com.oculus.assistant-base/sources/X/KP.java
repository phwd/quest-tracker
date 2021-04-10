package X;

import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public final class KP implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.soloader.UnpackingSoSource$1";
    public final /* synthetic */ KE A00;
    public final /* synthetic */ KR A01;
    public final /* synthetic */ VS A02;
    public final /* synthetic */ File A03;
    public final /* synthetic */ File A04;
    public final /* synthetic */ byte[] A05;

    public KP(VS vs, File file, byte[] bArr, KR kr, File file2, KE ke) {
        this.A02 = vs;
        this.A03 = file;
        this.A05 = bArr;
        this.A01 = kr;
        this.A04 = file2;
        this.A00 = ke;
    }

    public final void run() {
        try {
            Log.v("fb-UnpackingSoSource", "starting syncer worker");
            RandomAccessFile randomAccessFile = new RandomAccessFile(this.A03, "rw");
            try {
                randomAccessFile.write(this.A05);
                randomAccessFile.setLength(randomAccessFile.getFilePointer());
                randomAccessFile.close();
                File file = ((C0965ph) this.A02).A00;
                RandomAccessFile randomAccessFile2 = new RandomAccessFile(new File(file, "dso_manifest"), "rw");
                try {
                    KR kr = this.A01;
                    randomAccessFile2.writeByte(1);
                    KQ[] kqArr = kr.A00;
                    int length = kqArr.length;
                    randomAccessFile2.writeInt(length);
                    for (int i = 0; i < length; i++) {
                        randomAccessFile2.writeUTF(kqArr[i].A01);
                        randomAccessFile2.writeUTF(kqArr[i].A00);
                    }
                    randomAccessFile2.close();
                    A00(file);
                    VS.A00(this.A04, (byte) 1);
                    try {
                        StringBuilder sb = new StringBuilder();
                        sb.append("releasing dso store lock for ");
                        sb.append(file);
                        sb.append(" (from syncer thread)");
                        Log.v("fb-UnpackingSoSource", sb.toString());
                        this.A00.close();
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
        } catch (Throwable th) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("releasing dso store lock for ");
            sb2.append(((C0965ph) this.A02).A00);
            sb2.append(" (from syncer thread)");
            Log.v("fb-UnpackingSoSource", sb2.toString());
            this.A00.close();
            throw th;
        }
    }

    public static void A00(File file) {
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
