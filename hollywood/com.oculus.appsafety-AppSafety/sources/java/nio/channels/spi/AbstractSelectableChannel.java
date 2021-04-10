package java.nio.channels.spi;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.IllegalBlockingModeException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

public abstract class AbstractSelectableChannel extends SelectableChannel {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    boolean blocking = true;
    private int keyCount = 0;
    private final Object keyLock = new Object();
    private SelectionKey[] keys = null;
    private final SelectorProvider provider;
    private final Object regLock = new Object();

    /* access modifiers changed from: protected */
    public abstract void implCloseSelectableChannel() throws IOException;

    /* access modifiers changed from: protected */
    public abstract void implConfigureBlocking(boolean z) throws IOException;

    protected AbstractSelectableChannel(SelectorProvider provider2) {
        this.provider = provider2;
    }

    @Override // java.nio.channels.SelectableChannel
    public final SelectorProvider provider() {
        return this.provider;
    }

    private void addKey(SelectionKey k) {
        int i = 0;
        SelectionKey[] selectionKeyArr = this.keys;
        if (selectionKeyArr != null && this.keyCount < selectionKeyArr.length) {
            i = 0;
            while (true) {
                SelectionKey[] selectionKeyArr2 = this.keys;
                if (i >= selectionKeyArr2.length || selectionKeyArr2[i] == null) {
                    break;
                }
                i++;
            }
        } else {
            SelectionKey[] selectionKeyArr3 = this.keys;
            if (selectionKeyArr3 == null) {
                this.keys = new SelectionKey[3];
            } else {
                SelectionKey[] ks = new SelectionKey[(selectionKeyArr3.length * 2)];
                int i2 = 0;
                while (true) {
                    SelectionKey[] selectionKeyArr4 = this.keys;
                    if (i2 >= selectionKeyArr4.length) {
                        break;
                    }
                    ks[i2] = selectionKeyArr4[i2];
                    i2++;
                }
                this.keys = ks;
                i = this.keyCount;
            }
        }
        this.keys[i] = k;
        this.keyCount++;
    }

    private SelectionKey findKey(Selector sel) {
        synchronized (this.keyLock) {
            if (this.keys == null) {
                return null;
            }
            for (int i = 0; i < this.keys.length; i++) {
                if (this.keys[i] != null && this.keys[i].selector() == sel) {
                    return this.keys[i];
                }
            }
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public void removeKey(SelectionKey k) {
        synchronized (this.keyLock) {
            for (int i = 0; i < this.keys.length; i++) {
                if (this.keys[i] == k) {
                    this.keys[i] = null;
                    this.keyCount--;
                }
            }
            ((AbstractSelectionKey) k).invalidate();
        }
    }

    private boolean haveValidKeys() {
        synchronized (this.keyLock) {
            if (this.keyCount == 0) {
                return false;
            }
            for (int i = 0; i < this.keys.length; i++) {
                if (this.keys[i] != null && this.keys[i].isValid()) {
                    return true;
                }
            }
            return false;
        }
    }

    @Override // java.nio.channels.SelectableChannel
    public final boolean isRegistered() {
        boolean z;
        synchronized (this.keyLock) {
            z = this.keyCount != 0;
        }
        return z;
    }

    @Override // java.nio.channels.SelectableChannel
    public final SelectionKey keyFor(Selector sel) {
        return findKey(sel);
    }

    @Override // java.nio.channels.SelectableChannel
    public final SelectionKey register(Selector sel, int ops, Object att) throws ClosedChannelException {
        SelectionKey k;
        synchronized (this.regLock) {
            if (!isOpen()) {
                throw new ClosedChannelException();
            } else if (((~validOps()) & ops) != 0) {
                throw new IllegalArgumentException();
            } else if (!this.blocking) {
                k = findKey(sel);
                if (k != null) {
                    k.interestOps(ops);
                    k.attach(att);
                }
                if (k == null) {
                    synchronized (this.keyLock) {
                        if (isOpen()) {
                            k = ((AbstractSelector) sel).register(this, ops, att);
                            addKey(k);
                        } else {
                            throw new ClosedChannelException();
                        }
                    }
                }
            } else {
                throw new IllegalBlockingModeException();
            }
        }
        return k;
    }

    /* access modifiers changed from: protected */
    @Override // java.nio.channels.spi.AbstractInterruptibleChannel
    public final void implCloseChannel() throws IOException {
        implCloseSelectableChannel();
        synchronized (this.keyLock) {
            int count = this.keys == null ? 0 : this.keys.length;
            for (int i = 0; i < count; i++) {
                SelectionKey k = this.keys[i];
                if (k != null) {
                    k.cancel();
                }
            }
        }
    }

    @Override // java.nio.channels.SelectableChannel
    public final boolean isBlocking() {
        boolean z;
        synchronized (this.regLock) {
            z = this.blocking;
        }
        return z;
    }

    @Override // java.nio.channels.SelectableChannel
    public final Object blockingLock() {
        return this.regLock;
    }

    @Override // java.nio.channels.SelectableChannel
    public final SelectableChannel configureBlocking(boolean block) throws IOException {
        synchronized (this.regLock) {
            if (!isOpen()) {
                throw new ClosedChannelException();
            } else if (this.blocking == block) {
                return this;
            } else {
                if (block) {
                    if (haveValidKeys()) {
                        throw new IllegalBlockingModeException();
                    }
                }
                implConfigureBlocking(block);
                this.blocking = block;
                return this;
            }
        }
    }
}
