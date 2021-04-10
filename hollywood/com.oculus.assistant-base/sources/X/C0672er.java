package X;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/* renamed from: X.er  reason: case insensitive filesystem */
public final class C0672er extends AbstractC00346b {
    public FileLock A00;
    public final File A01;
    public final FileChannel A02;
    public final /* synthetic */ es A03;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0672er(es esVar, File file) {
        super(esVar, file);
        this.A03 = esVar;
        if (file.getParentFile().isDirectory() || file.getParentFile().mkdirs()) {
            File file2 = new File(file.getParentFile(), AnonymousClass08.A04(file.getName(), ".lock"));
            this.A01 = file2;
            this.A02 = new RandomAccessFile(file2, "rw").getChannel();
            return;
        }
        StringBuilder sb = new StringBuilder("Unable to create parent directories for: ");
        sb.append(file);
        throw new IOException(sb.toString());
    }
}
