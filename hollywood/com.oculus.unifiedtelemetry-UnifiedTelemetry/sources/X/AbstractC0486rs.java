package X;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* renamed from: X.rs  reason: case insensitive filesystem */
public abstract class AbstractC0486rs extends C0485rr {
    @Nullable
    public String A00;
    @Nullable
    public String[] A01;
    public final Context A02;
    public final Map<String, Object> A03 = new HashMap();

    public static void A04(File file, byte b) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        try {
            randomAccessFile.seek(0);
            randomAccessFile.write(b);
            randomAccessFile.setLength(randomAccessFile.getFilePointer());
            randomAccessFile.getFD().sync();
            randomAccessFile.close();
            return;
        } catch (Throwable unused) {
        }
        throw th;
    }

    public AbstractC0486rs(Context context, String str) {
        super(new File(AnonymousClass06.A05(context.getApplicationInfo().dataDir, "/", str)), 1);
        this.A02 = context;
    }

    public static void A03(File file) throws IOException {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    A03(file2);
                }
            } else {
                return;
            }
        }
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.canWrite() && !parentFile.setWritable(true)) {
            StringBuilder sb = new StringBuilder("Enable write permission failed: ");
            sb.append(parentFile);
            Log.e("SysUtil", sb.toString());
        }
        if (!file.delete() && file.exists()) {
            StringBuilder sb2 = new StringBuilder("Could not delete file ");
            sb2.append(file);
            throw new IOException(sb2.toString());
        }
    }
}
