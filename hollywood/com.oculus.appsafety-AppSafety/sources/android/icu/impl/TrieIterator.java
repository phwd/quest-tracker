package android.icu.impl;

import android.icu.text.UTF16;
import android.icu.util.RangeValueIterator;

public class TrieIterator implements RangeValueIterator {
    private static final int BMP_INDEX_LENGTH_ = 2048;
    private static final int DATA_BLOCK_LENGTH_ = 32;
    private static final int LEAD_SURROGATE_MIN_VALUE_ = 55296;
    private static final int TRAIL_SURROGATE_COUNT_ = 1024;
    private static final int TRAIL_SURROGATE_INDEX_BLOCK_LENGTH_ = 32;
    private static final int TRAIL_SURROGATE_MIN_VALUE_ = 56320;
    private int m_currentCodepoint_;
    private int m_initialValue_;
    private int m_nextBlockIndex_;
    private int m_nextBlock_;
    private int m_nextCodepoint_;
    private int m_nextIndex_;
    private int m_nextTrailIndexOffset_;
    private int m_nextValue_;
    private Trie m_trie_;

    public TrieIterator(Trie trie) {
        if (trie != null) {
            this.m_trie_ = trie;
            this.m_initialValue_ = extract(this.m_trie_.getInitialValue());
            reset();
            return;
        }
        throw new IllegalArgumentException("Argument trie cannot be null");
    }

    @Override // android.icu.util.RangeValueIterator
    public final boolean next(RangeValueIterator.Element element) {
        int i = this.m_nextCodepoint_;
        if (i > 1114111) {
            return false;
        }
        if (i < 65536 && calculateNextBMPElement(element)) {
            return true;
        }
        calculateNextSupplementaryElement(element);
        return true;
    }

    @Override // android.icu.util.RangeValueIterator
    public final void reset() {
        this.m_currentCodepoint_ = 0;
        this.m_nextCodepoint_ = 0;
        this.m_nextIndex_ = 0;
        this.m_nextBlock_ = this.m_trie_.m_index_[0] << 2;
        if (this.m_nextBlock_ == this.m_trie_.m_dataOffset_) {
            this.m_nextValue_ = this.m_initialValue_;
        } else {
            this.m_nextValue_ = extract(this.m_trie_.getValue(this.m_nextBlock_));
        }
        this.m_nextBlockIndex_ = 0;
        this.m_nextTrailIndexOffset_ = 32;
    }

    /* access modifiers changed from: protected */
    public int extract(int value) {
        return value;
    }

    private final void setResult(RangeValueIterator.Element element, int start, int limit, int value) {
        element.start = start;
        element.limit = limit;
        element.value = value;
    }

    private final boolean calculateNextBMPElement(RangeValueIterator.Element element) {
        int currentValue = this.m_nextValue_;
        int i = this.m_nextCodepoint_;
        this.m_currentCodepoint_ = i;
        this.m_nextCodepoint_ = i + 1;
        this.m_nextBlockIndex_++;
        if (!checkBlockDetail(currentValue)) {
            setResult(element, this.m_currentCodepoint_, this.m_nextCodepoint_, currentValue);
            return true;
        }
        do {
            int i2 = this.m_nextCodepoint_;
            if (i2 < 65536) {
                if (i2 == 55296) {
                    this.m_nextIndex_ = 2048;
                } else if (i2 == 56320) {
                    this.m_nextIndex_ = i2 >> 5;
                } else {
                    this.m_nextIndex_++;
                }
                this.m_nextBlockIndex_ = 0;
            } else {
                this.m_nextCodepoint_ = i2 - 1;
                this.m_nextBlockIndex_--;
                return false;
            }
        } while (checkBlock(currentValue));
        setResult(element, this.m_currentCodepoint_, this.m_nextCodepoint_, currentValue);
        return true;
    }

    private final void calculateNextSupplementaryElement(RangeValueIterator.Element element) {
        int currentValue = this.m_nextValue_;
        this.m_nextCodepoint_++;
        this.m_nextBlockIndex_++;
        if (UTF16.getTrailSurrogate(this.m_nextCodepoint_) != 56320) {
            if (checkNullNextTrailIndex() || checkBlockDetail(currentValue)) {
                this.m_nextIndex_++;
                this.m_nextTrailIndexOffset_++;
                if (!checkTrailBlock(currentValue)) {
                    setResult(element, this.m_currentCodepoint_, this.m_nextCodepoint_, currentValue);
                    this.m_currentCodepoint_ = this.m_nextCodepoint_;
                    return;
                }
            } else {
                setResult(element, this.m_currentCodepoint_, this.m_nextCodepoint_, currentValue);
                this.m_currentCodepoint_ = this.m_nextCodepoint_;
                return;
            }
        }
        int nextLead = UTF16.getLeadSurrogate(this.m_nextCodepoint_);
        while (nextLead < 56320) {
            int leadBlock = this.m_trie_.m_index_[nextLead >> 5] << 2;
            if (leadBlock == this.m_trie_.m_dataOffset_) {
                int i = this.m_initialValue_;
                if (currentValue != i) {
                    this.m_nextValue_ = i;
                    this.m_nextBlock_ = leadBlock;
                    this.m_nextBlockIndex_ = 0;
                    setResult(element, this.m_currentCodepoint_, this.m_nextCodepoint_, currentValue);
                    this.m_currentCodepoint_ = this.m_nextCodepoint_;
                    return;
                }
                nextLead += 32;
                this.m_nextCodepoint_ = Character.toCodePoint((char) nextLead, 56320);
            } else if (this.m_trie_.m_dataManipulate_ != null) {
                this.m_nextIndex_ = this.m_trie_.m_dataManipulate_.getFoldingOffset(this.m_trie_.getValue((nextLead & 31) + leadBlock));
                if (this.m_nextIndex_ <= 0) {
                    int i2 = this.m_initialValue_;
                    if (currentValue != i2) {
                        this.m_nextValue_ = i2;
                        this.m_nextBlock_ = this.m_trie_.m_dataOffset_;
                        this.m_nextBlockIndex_ = 0;
                        setResult(element, this.m_currentCodepoint_, this.m_nextCodepoint_, currentValue);
                        this.m_currentCodepoint_ = this.m_nextCodepoint_;
                        return;
                    }
                    this.m_nextCodepoint_ += 1024;
                } else {
                    this.m_nextTrailIndexOffset_ = 0;
                    if (!checkTrailBlock(currentValue)) {
                        setResult(element, this.m_currentCodepoint_, this.m_nextCodepoint_, currentValue);
                        this.m_currentCodepoint_ = this.m_nextCodepoint_;
                        return;
                    }
                }
                nextLead++;
            } else {
                throw new NullPointerException("The field DataManipulate in this Trie is null");
            }
        }
        setResult(element, this.m_currentCodepoint_, 1114112, currentValue);
    }

    private final boolean checkBlockDetail(int currentValue) {
        while (true) {
            int i = this.m_nextBlockIndex_;
            if (i >= 32) {
                return true;
            }
            this.m_nextValue_ = extract(this.m_trie_.getValue(this.m_nextBlock_ + i));
            if (this.m_nextValue_ != currentValue) {
                return false;
            }
            this.m_nextBlockIndex_++;
            this.m_nextCodepoint_++;
        }
    }

    private final boolean checkBlock(int currentValue) {
        int currentBlock = this.m_nextBlock_;
        this.m_nextBlock_ = this.m_trie_.m_index_[this.m_nextIndex_] << 2;
        if (this.m_nextBlock_ == currentBlock) {
            int i = this.m_nextCodepoint_;
            if (i - this.m_currentCodepoint_ >= 32) {
                this.m_nextCodepoint_ = i + 32;
                return true;
            }
        }
        if (this.m_nextBlock_ == this.m_trie_.m_dataOffset_) {
            int i2 = this.m_initialValue_;
            if (currentValue != i2) {
                this.m_nextValue_ = i2;
                this.m_nextBlockIndex_ = 0;
                return false;
            }
            this.m_nextCodepoint_ += 32;
            return true;
        } else if (!checkBlockDetail(currentValue)) {
            return false;
        } else {
            return true;
        }
    }

    private final boolean checkTrailBlock(int currentValue) {
        while (this.m_nextTrailIndexOffset_ < 32) {
            this.m_nextBlockIndex_ = 0;
            if (!checkBlock(currentValue)) {
                return false;
            }
            this.m_nextTrailIndexOffset_++;
            this.m_nextIndex_++;
        }
        return true;
    }

    private final boolean checkNullNextTrailIndex() {
        if (this.m_nextIndex_ > 0) {
            return false;
        }
        this.m_nextCodepoint_ += 1023;
        int nextLead = UTF16.getLeadSurrogate(this.m_nextCodepoint_);
        int leadBlock = this.m_trie_.m_index_[nextLead >> 5] << 2;
        if (this.m_trie_.m_dataManipulate_ != null) {
            this.m_nextIndex_ = this.m_trie_.m_dataManipulate_.getFoldingOffset(this.m_trie_.getValue((nextLead & 31) + leadBlock));
            this.m_nextIndex_--;
            this.m_nextBlockIndex_ = 32;
            return true;
        }
        throw new NullPointerException("The field DataManipulate in this Trie is null");
    }
}
