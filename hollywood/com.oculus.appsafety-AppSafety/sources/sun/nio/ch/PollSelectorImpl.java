package sun.nio.ch;

import java.io.IOException;
import java.nio.channels.ClosedSelectorException;
import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;

class PollSelectorImpl extends AbstractPollSelectorImpl {
    private int fd0;
    private int fd1;
    private Object interruptLock = new Object();
    private boolean interruptTriggered = false;

    PollSelectorImpl(SelectorProvider sp) {
        super(sp, 1, 1);
        long pipeFds = IOUtil.makePipe(false);
        this.fd0 = (int) (pipeFds >>> 32);
        this.fd1 = (int) pipeFds;
        try {
            this.pollWrapper = new PollArrayWrapper(10);
            this.pollWrapper.initInterrupt(this.fd0, this.fd1);
            this.channelArray = new SelectionKeyImpl[10];
        } catch (Throwable t) {
            try {
                FileDispatcherImpl.closeIntFD(this.fd0);
            } catch (IOException ioe0) {
                t.addSuppressed(ioe0);
            }
            try {
                FileDispatcherImpl.closeIntFD(this.fd1);
            } catch (IOException ioe1) {
                t.addSuppressed(ioe1);
            }
            throw t;
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    @Override // sun.nio.ch.AbstractPollSelectorImpl, sun.nio.ch.SelectorImpl
    public int doSelect(long timeout) throws IOException {
        if (this.channelArray != null) {
            processDeregisterQueue();
            try {
                begin();
                this.pollWrapper.poll(this.totalChannels, 0, timeout);
                end();
                processDeregisterQueue();
                int numKeysUpdated = updateSelectedKeys();
                if (this.pollWrapper.getReventOps(0) != 0) {
                    this.pollWrapper.putReventOps(0, 0);
                    synchronized (this.interruptLock) {
                        IOUtil.drain(this.fd0);
                        this.interruptTriggered = false;
                    }
                }
                return numKeysUpdated;
            } catch (Throwable th) {
                end();
                throw th;
            }
        } else {
            throw new ClosedSelectorException();
        }
    }

    /* access modifiers changed from: protected */
    @Override // sun.nio.ch.AbstractPollSelectorImpl
    public void implCloseInterrupt() throws IOException {
        synchronized (this.interruptLock) {
            this.interruptTriggered = true;
        }
        FileDispatcherImpl.closeIntFD(this.fd0);
        FileDispatcherImpl.closeIntFD(this.fd1);
        this.fd0 = -1;
        this.fd1 = -1;
        this.pollWrapper.release(0);
    }

    @Override // sun.nio.ch.AbstractPollSelectorImpl, java.nio.channels.Selector, sun.nio.ch.SelectorImpl
    public Selector wakeup() {
        synchronized (this.interruptLock) {
            if (!this.interruptTriggered) {
                this.pollWrapper.interrupt();
                this.interruptTriggered = true;
            }
        }
        return this;
    }
}
