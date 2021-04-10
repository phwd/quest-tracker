package android.icu.impl;

import java.nio.ByteBuffer;
import java.util.Arrays;

public abstract class Trie {
    protected int m_dataLength_;
    protected DataManipulate m_dataManipulate_;
    protected int m_dataOffset_;
    protected char[] m_index_;
    private boolean m_isLatin1Linear_;
    private int m_options_;

    public interface DataManipulate {
    }

    public int hashCode() {
        return 42;
    }

    private static class DefaultGetFoldingOffset implements DataManipulate {
        private DefaultGetFoldingOffset() {
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Trie)) {
            return false;
        }
        Trie trie = (Trie) obj;
        return this.m_isLatin1Linear_ == trie.m_isLatin1Linear_ && this.m_options_ == trie.m_options_ && this.m_dataLength_ == trie.m_dataLength_ && Arrays.equals(this.m_index_, trie.m_index_);
    }

    protected Trie(ByteBuffer byteBuffer, DataManipulate dataManipulate) {
        int i = byteBuffer.getInt();
        this.m_options_ = byteBuffer.getInt();
        if (checkHeader(i)) {
            if (dataManipulate != null) {
                this.m_dataManipulate_ = dataManipulate;
            } else {
                this.m_dataManipulate_ = new DefaultGetFoldingOffset();
            }
            this.m_isLatin1Linear_ = (this.m_options_ & 512) != 0;
            this.m_dataOffset_ = byteBuffer.getInt();
            this.m_dataLength_ = byteBuffer.getInt();
            unserialize(byteBuffer);
            return;
        }
        throw new IllegalArgumentException("ICU data file error: Trie header authentication failed, please check if you have the most updated ICU data file");
    }

    /* access modifiers changed from: protected */
    public void unserialize(ByteBuffer byteBuffer) {
        this.m_index_ = ICUBinary.getChars(byteBuffer, this.m_dataOffset_, 0);
    }

    /* access modifiers changed from: protected */
    public final boolean isCharTrie() {
        return (this.m_options_ & 256) == 0;
    }

    private final boolean checkHeader(int i) {
        if (i != 1416784229) {
            return false;
        }
        int i2 = this.m_options_;
        return (i2 & 15) == 5 && ((i2 >> 4) & 15) == 2;
    }
}
