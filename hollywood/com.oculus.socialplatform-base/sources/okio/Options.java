package okio;

import java.util.AbstractList;
import java.util.RandomAccess;

public final class Options extends AbstractList<ByteString> implements RandomAccess {
    public final ByteString[] byteStrings;

    public int size() {
        return this.byteStrings.length;
    }

    public Options(ByteString[] byteStringArr) {
        this.byteStrings = byteStringArr;
    }

    public static Options of(ByteString... byteStringArr) {
        return new Options((ByteString[]) byteStringArr.clone());
    }

    @Override // java.util.List, java.util.AbstractList
    public ByteString get(int i) {
        return this.byteStrings[i];
    }
}
