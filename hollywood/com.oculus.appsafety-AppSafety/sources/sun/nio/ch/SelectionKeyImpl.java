package sun.nio.ch;

import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.spi.AbstractSelectionKey;

public class SelectionKeyImpl extends AbstractSelectionKey {
    final SelChImpl channel;
    private int index;
    private volatile int interestOps;
    private int readyOps;
    public final SelectorImpl selector;

    SelectionKeyImpl(SelChImpl ch, SelectorImpl sel) {
        this.channel = ch;
        this.selector = sel;
    }

    @Override // java.nio.channels.SelectionKey
    public SelectableChannel channel() {
        return (SelectableChannel) this.channel;
    }

    @Override // java.nio.channels.SelectionKey
    public Selector selector() {
        return this.selector;
    }

    /* access modifiers changed from: package-private */
    public int getIndex() {
        return this.index;
    }

    /* access modifiers changed from: package-private */
    public void setIndex(int i) {
        this.index = i;
    }

    private void ensureValid() {
        if (!isValid()) {
            throw new CancelledKeyException();
        }
    }

    @Override // java.nio.channels.SelectionKey
    public int interestOps() {
        ensureValid();
        return this.interestOps;
    }

    @Override // java.nio.channels.SelectionKey
    public SelectionKey interestOps(int ops) {
        ensureValid();
        return nioInterestOps(ops);
    }

    @Override // java.nio.channels.SelectionKey
    public int readyOps() {
        ensureValid();
        return this.readyOps;
    }

    public void nioReadyOps(int ops) {
        this.readyOps = ops;
    }

    public int nioReadyOps() {
        return this.readyOps;
    }

    public SelectionKey nioInterestOps(int ops) {
        if (((~channel().validOps()) & ops) == 0) {
            this.channel.translateAndSetInterestOps(ops, this);
            this.interestOps = ops;
            return this;
        }
        throw new IllegalArgumentException();
    }

    public int nioInterestOps() {
        return this.interestOps;
    }
}
