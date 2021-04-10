package java.nio.channels.spi;

import java.io.IOException;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.Channel;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.InterruptibleChannel;
import sun.nio.ch.Interruptible;

public abstract class AbstractInterruptibleChannel implements Channel, InterruptibleChannel {
    private final Object closeLock = new Object();
    private volatile Thread interrupted;
    private Interruptible interruptor;
    private volatile boolean open = true;

    /* access modifiers changed from: protected */
    public abstract void implCloseChannel();

    protected AbstractInterruptibleChannel() {
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel
    public final void close() {
        synchronized (this.closeLock) {
            if (this.open) {
                this.open = false;
                implCloseChannel();
            }
        }
    }

    @Override // java.nio.channels.Channel
    public final boolean isOpen() {
        return this.open;
    }

    /* access modifiers changed from: protected */
    public final void begin() {
        if (this.interruptor == null) {
            this.interruptor = new Interruptible() {
                /* class java.nio.channels.spi.AbstractInterruptibleChannel.AnonymousClass1 */

                @Override // sun.nio.ch.Interruptible
                public void interrupt(Thread thread) {
                    synchronized (AbstractInterruptibleChannel.this.closeLock) {
                        if (AbstractInterruptibleChannel.this.open) {
                            AbstractInterruptibleChannel.this.open = false;
                            AbstractInterruptibleChannel.this.interrupted = thread;
                            try {
                                AbstractInterruptibleChannel.this.implCloseChannel();
                            } catch (IOException unused) {
                            }
                        }
                    }
                }
            };
        }
        blockedOn(this.interruptor);
        Thread currentThread = Thread.currentThread();
        if (currentThread.isInterrupted()) {
            this.interruptor.interrupt(currentThread);
        }
    }

    /* access modifiers changed from: protected */
    public final void end(boolean z) {
        blockedOn(null);
        Thread thread = this.interrupted;
        if (thread != null && thread == Thread.currentThread()) {
            throw new ClosedByInterruptException();
        } else if (!z && !this.open) {
            throw new AsynchronousCloseException();
        }
    }

    static void blockedOn(Interruptible interruptible) {
        Thread.currentThread().blockedOn(interruptible);
    }
}
