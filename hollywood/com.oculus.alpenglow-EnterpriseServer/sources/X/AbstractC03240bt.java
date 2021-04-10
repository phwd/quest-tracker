package X;

import android.content.Context;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: X.0bt  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC03240bt<IPluginType> implements Iterable<IPluginType>, Iterator<IPluginType> {
    public int A00 = 0;
    public int A01 = -1;
    public final Object A02 = new Object();
    public final Context A03;
    public final int[] A04;

    @Override // java.lang.Iterable
    public final Iterator<IPluginType> iterator() {
        this.A01 = 0;
        return this;
    }

    public AbstractC03240bt(Context context, int i) {
        int[] iArr;
        this.A03 = context;
        if (i == 0 && "default".hashCode() == 1544803905 && "default".equals("default")) {
            iArr = new int[]{0};
        } else {
            iArr = new int[0];
        }
        this.A04 = iArr;
    }

    public final boolean hasNext() {
        if (this.A00 < this.A04.length) {
            return true;
        }
        return false;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: X.0bt<IPluginType> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Iterator
    public final IPluginType next() {
        int i = this.A00;
        int[] iArr = this.A04;
        if (i != iArr.length) {
            this.A00 = i + 1;
            this.A01 = iArr[i];
            return this;
        }
        throw new NoSuchElementException("There were no registered Plugins for this Socket/PluginList combination.");
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
