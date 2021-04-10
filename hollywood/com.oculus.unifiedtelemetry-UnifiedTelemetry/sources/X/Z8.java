package X;

import com.facebook.common.stringformat.StringFormatUtil;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.FileLockInterruptionException;
import java.nio.channels.NonWritableChannelException;
import java.nio.channels.OverlappingFileLockException;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class Z8 extends Fz<File>.BatchLock {
    @GuardedBy("this")
    @Nullable
    public FileLock A00;
    @GuardedBy("this")
    public final FileChannel A01;
    @GuardedBy("this")
    public final File A02;
    public final /* synthetic */ Z7 A03;

    public final String A01() {
        return "CrossProcessBatchLock";
    }

    public final synchronized void A02() {
        try {
            this.A01.close();
        } catch (IOException e) {
            Object[] objArr = {this.A02};
            if (Mu.A01.A3F(6)) {
                Mu.A04("CrossProcessBatchLock", StringFormatUtil.formatStrLocaleSafe("Failed to close the file channel associated with file: %s", objArr), e);
            }
        }
    }

    public final synchronized void A04() {
        while (this.A00 == null) {
            try {
                this.A00 = this.A01.lock();
            } catch (ClosedChannelException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("File channel is closed prematurely or opened non-writable for: ");
                sb.append(this.A02);
                throw new RuntimeException(sb.toString(), e);
            } catch (FileLockInterruptionException e2) {
                Mu.A0C("CrossProcessBatchLock", e2, "Interrupted while waiting to lock the file: %s", this.A02);
            } catch (IOException | NonWritableChannelException e3) {
                throw new RuntimeException("Failed to lock the file due to an IOException!", e3);
            }
        }
    }

    public final synchronized void A05() {
        this.A02.delete();
    }

    public final synchronized void A06() {
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

    public final synchronized boolean A09() {
        return !this.A02.exists();
    }

    public final synchronized boolean A0A() {
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
                Mu.A09("CrossProcessBatchLock", e2, "IOException happens when trying to lock the file.");
            }
        }
        z = false;
        if (this.A00 != null) {
            z = true;
        }
        return z;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Z8(Z7 z7, File file) throws IOException {
        super(z7, file);
        this.A03 = z7;
        if (file.getParentFile().isDirectory() || file.getParentFile().mkdirs()) {
            File file2 = new File(file.getParentFile(), AnonymousClass06.A04(file.getName(), ".lock"));
            this.A02 = file2;
            this.A01 = new RandomAccessFile(file2, "rw").getChannel();
            return;
        }
        StringBuilder sb = new StringBuilder("Unable to create parent directories for: ");
        sb.append(file);
        throw new IOException(sb.toString());
    }
}
