package X;

import com.facebook.common.stringformat.StringFormatUtil;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* renamed from: X.0qM  reason: invalid class name and case insensitive filesystem */
public class C06940qM extends AnonymousClass0GE<File>.BatchLock {
    @GuardedBy("this")
    @Nullable
    public FileLock A00;
    @GuardedBy("this")
    public final FileChannel A01;
    @GuardedBy("this")
    public final File A02;
    public final /* synthetic */ C06930qK A03;

    public final synchronized void A00() {
        try {
            this.A01.close();
        } catch (IOException e) {
            Object[] objArr = {this.A02};
            if (AnonymousClass0NO.A01.A54(6)) {
                AnonymousClass0NO.A0D("CrossProcessBatchLock", StringFormatUtil.formatStrLocaleSafe("Failed to close the file channel associated with file: %s", objArr), e);
            }
        }
    }

    public final synchronized void A02() {
        this.A02.delete();
    }

    public final synchronized void A03() {
        FileLock fileLock = this.A00;
        if (fileLock != null) {
            try {
                fileLock.release();
                this.A00 = null;
            } catch (ClosedChannelException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("File Channel has been closed prematurely for: ");
                sb.append(this.A02);
                throw new RuntimeException(sb.toString(), e);
            } catch (IOException e2) {
                throw new RuntimeException("Failed to unlock the file due to an IOException!", e2);
            }
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("File lock was never held for: ");
            sb2.append(this.A02);
            throw new IllegalStateException(sb2.toString());
        }
    }

    public final synchronized boolean A06() {
        boolean z;
        if (this.A00 == null) {
            try {
                this.A00 = this.A01.tryLock();
            } catch (ClosedChannelException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("File channel closed prematurely for: ");
                sb.append(this.A02);
                throw new RuntimeException(sb.toString(), e);
            } catch (IOException | OverlappingFileLockException e2) {
                AnonymousClass0NO.A0I("CrossProcessBatchLock", e2, "IOException happens when trying to lock the file.");
            }
        }
        z = false;
        if (this.A00 != null) {
            z = true;
        }
        return z;
    }

    public final synchronized boolean A07() {
        return !this.A02.exists();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C06940qM(C06930qK r5, File file) throws IOException {
        super(r5, file);
        this.A03 = r5;
        if (file.getParentFile().isDirectory() || file.getParentFile().mkdirs()) {
            File file2 = new File(file.getParentFile(), AnonymousClass006.A05(file.getName(), ".lock"));
            this.A02 = file2;
            this.A01 = new RandomAccessFile(file2, "rw").getChannel();
            return;
        }
        StringBuilder sb = new StringBuilder("Unable to create parent directories for: ");
        sb.append(file);
        throw new IOException(sb.toString());
    }
}
