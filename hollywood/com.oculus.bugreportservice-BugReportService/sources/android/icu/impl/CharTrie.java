package android.icu.impl;

import android.icu.impl.Trie;
import java.nio.ByteBuffer;

public class CharTrie extends Trie {
    private char[] m_data_;
    private char m_initialValue_;

    @Override // android.icu.impl.Trie
    public int hashCode() {
        return 42;
    }

    public CharTrie(ByteBuffer byteBuffer, Trie.DataManipulate dataManipulate) {
        super(byteBuffer, dataManipulate);
        if (!isCharTrie()) {
            throw new IllegalArgumentException("Data given does not belong to a char trie.");
        }
    }

    @Override // android.icu.impl.Trie
    public boolean equals(Object obj) {
        if (!super.equals(obj) || !(obj instanceof CharTrie) || this.m_initialValue_ != ((CharTrie) obj).m_initialValue_) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.Trie
    public final void unserialize(ByteBuffer byteBuffer) {
        this.m_index_ = ICUBinary.getChars(byteBuffer, this.m_dataOffset_ + this.m_dataLength_, 0);
        this.m_data_ = this.m_index_;
        this.m_initialValue_ = this.m_data_[this.m_dataOffset_];
    }
}
