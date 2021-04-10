package X;

import com.facebook.common.stringformat.StringFormatUtil;
import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileLock;

/* renamed from: X.6b  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC00346b {
    public int A00;
    public Object A01;
    public final Object A02;
    public final /* synthetic */ AbstractC00356c A03;

    public final synchronized void A01(Object obj) {
        if (this.A01 != obj) {
            throw new IllegalMonitorStateException("Lock is not held by the provided owner");
        }
    }

    public final synchronized void A02(Object obj) {
        A01(obj);
        try {
            if (!(this instanceof C0679f8)) {
                C0672er erVar = (C0672er) this;
                FileLock fileLock = erVar.A00;
                if (fileLock != null) {
                    try {
                        fileLock.release();
                        erVar.A00 = null;
                    } catch (ClosedChannelException e) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("File Channel has been closed prematurely for: ");
                        sb.append(((AbstractC00346b) erVar).A02);
                        throw new RuntimeException(sb.toString(), e);
                    } catch (IOException e2) {
                        throw new RuntimeException("Failed to unlock the file due to an IOException!", e2);
                    }
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("File lock was never held for: ");
                    sb2.append(((AbstractC00346b) erVar).A02);
                    throw new IllegalStateException(sb2.toString());
                }
            }
        } finally {
            this.A01 = null;
            notifyAll();
        }
    }

    public final boolean A03() {
        boolean z;
        if (!(this instanceof C0679f8)) {
            C0672er erVar = (C0672er) this;
            synchronized (this) {
                z = !erVar.A01.exists();
            }
            return z;
        }
        C0679f8 f8Var = (C0679f8) this;
        synchronized (this) {
            z = f8Var.A00;
        }
        return z;
    }

    public final synchronized String toString() {
        String str;
        StringBuilder sb;
        Object obj = this.A01;
        if (obj != null) {
            str = obj.toString();
        } else {
            str = "null";
        }
        sb = new StringBuilder();
        sb.append("[key=");
        sb.append(this.A02);
        sb.append(",refCount=");
        sb.append(this.A00);
        sb.append(",lockOwner=");
        sb.append(str);
        sb.append(",isDeleted=");
        sb.append(A03());
        sb.append("]");
        return sb.toString();
    }

    public AbstractC00346b(AbstractC00356c r1, Object obj) {
        this.A03 = r1;
        this.A02 = obj;
    }

    public final void A00() {
        AbstractC00356c r5 = this.A03;
        synchronized (r5) {
            int i = this.A00 - 1;
            this.A00 = i;
            if (i < 0) {
                throw new IllegalStateException("Unbalance calls to acquire/release");
            } else if (i == 0) {
                if (this instanceof C0672er) {
                    C0672er erVar = (C0672er) this;
                    synchronized (erVar) {
                        try {
                            erVar.A02.close();
                        } catch (IOException e) {
                            Object[] objArr = {((AbstractC00346b) erVar).A02};
                            if (C0139Dd.A01.A3Y(6)) {
                                C0139Dd.A0N("CrossProcessBatchLock", StringFormatUtil.formatStrLocaleSafe("Failed to close the file channel associated with file: %s", objArr), e);
                            }
                        }
                    }
                }
                r5.A00.remove(this.A02);
            }
        }
    }
}
