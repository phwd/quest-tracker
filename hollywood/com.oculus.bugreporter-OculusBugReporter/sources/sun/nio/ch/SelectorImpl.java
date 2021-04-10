package sun.nio.ch;

import java.io.IOException;
import java.net.SocketException;
import java.nio.channels.ClosedSelectorException;
import java.nio.channels.IllegalSelectorException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.spi.AbstractSelectableChannel;
import java.nio.channels.spi.AbstractSelector;
import java.nio.channels.spi.SelectorProvider;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public abstract class SelectorImpl extends AbstractSelector {
    protected HashSet<SelectionKey> keys = new HashSet<>();
    private Set<SelectionKey> publicKeys;
    private Set<SelectionKey> publicSelectedKeys;
    protected Set<SelectionKey> selectedKeys = new HashSet();

    /* access modifiers changed from: protected */
    public abstract int doSelect(long j) throws IOException;

    /* access modifiers changed from: protected */
    public abstract void implClose() throws IOException;

    /* access modifiers changed from: protected */
    public abstract void implDereg(SelectionKeyImpl selectionKeyImpl) throws IOException;

    /* access modifiers changed from: protected */
    public abstract void implRegister(SelectionKeyImpl selectionKeyImpl);

    @Override // java.nio.channels.Selector
    public abstract Selector wakeup();

    protected SelectorImpl(SelectorProvider sp) {
        super(sp);
        if (Util.atBugLevel("1.4")) {
            this.publicKeys = this.keys;
            this.publicSelectedKeys = this.selectedKeys;
            return;
        }
        this.publicKeys = Collections.unmodifiableSet(this.keys);
        this.publicSelectedKeys = Util.ungrowableSet(this.selectedKeys);
    }

    @Override // java.nio.channels.Selector
    public Set<SelectionKey> keys() {
        if (isOpen() || Util.atBugLevel("1.4")) {
            return this.publicKeys;
        }
        throw new ClosedSelectorException();
    }

    @Override // java.nio.channels.Selector
    public Set<SelectionKey> selectedKeys() {
        if (isOpen() || Util.atBugLevel("1.4")) {
            return this.publicSelectedKeys;
        }
        throw new ClosedSelectorException();
    }

    private int lockAndDoSelect(long timeout) throws IOException {
        int doSelect;
        synchronized (this) {
            if (isOpen()) {
                synchronized (this.publicKeys) {
                    synchronized (this.publicSelectedKeys) {
                        doSelect = doSelect(timeout);
                    }
                }
            } else {
                throw new ClosedSelectorException();
            }
        }
        return doSelect;
    }

    @Override // java.nio.channels.Selector
    public int select(long timeout) throws IOException {
        if (timeout >= 0) {
            return lockAndDoSelect(timeout == 0 ? -1 : timeout);
        }
        throw new IllegalArgumentException("Negative timeout");
    }

    @Override // java.nio.channels.Selector
    public int select() throws IOException {
        return select(0);
    }

    @Override // java.nio.channels.Selector
    public int selectNow() throws IOException {
        return lockAndDoSelect(0);
    }

    @Override // java.nio.channels.spi.AbstractSelector
    public void implCloseSelector() throws IOException {
        wakeup();
        synchronized (this) {
            synchronized (this.publicKeys) {
                synchronized (this.publicSelectedKeys) {
                    implClose();
                }
            }
        }
    }

    public void putEventOps(SelectionKeyImpl sk, int ops) {
    }

    /* access modifiers changed from: protected */
    @Override // java.nio.channels.spi.AbstractSelector
    public final SelectionKey register(AbstractSelectableChannel ch, int ops, Object attachment) {
        if (ch instanceof SelChImpl) {
            SelectionKeyImpl k = new SelectionKeyImpl((SelChImpl) ch, this);
            k.attach(attachment);
            synchronized (this.publicKeys) {
                implRegister(k);
            }
            k.interestOps(ops);
            return k;
        }
        throw new IllegalSelectorException();
    }

    /* access modifiers changed from: package-private */
    public void processDeregisterQueue() throws IOException {
        Set<SelectionKey> cks = cancelledKeys();
        synchronized (cks) {
            if (!cks.isEmpty()) {
                Iterator<SelectionKey> i = cks.iterator();
                while (i.hasNext()) {
                    try {
                        implDereg((SelectionKeyImpl) i.next());
                        i.remove();
                    } catch (SocketException se) {
                        throw new IOException("Error deregistering key", se);
                    } catch (Throwable th) {
                        i.remove();
                        throw th;
                    }
                }
            }
        }
    }
}
