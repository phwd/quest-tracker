package android.icu.lang;

import android.icu.impl.UCharacterName;
import android.icu.util.ValueIterator;

class UCharacterNameIterator implements ValueIterator {
    private static char[] GROUP_LENGTHS_ = new char[33];
    private static char[] GROUP_OFFSETS_ = new char[33];
    private int m_algorithmIndex_ = -1;
    private int m_choice_;
    private int m_current_;
    private int m_groupIndex_ = -1;
    private int m_limit_;
    private UCharacterName m_name_;
    private int m_start_;

    @Override // android.icu.util.ValueIterator
    public boolean next(ValueIterator.Element element) {
        int length;
        if (this.m_current_ >= this.m_limit_) {
            return false;
        }
        int i = this.m_choice_;
        if ((i == 0 || i == 2) && this.m_algorithmIndex_ < (length = this.m_name_.getAlgorithmLength())) {
            while (true) {
                int i2 = this.m_algorithmIndex_;
                if (i2 >= length || (i2 >= 0 && this.m_name_.getAlgorithmEnd(i2) >= this.m_current_)) {
                    int i3 = this.m_algorithmIndex_;
                } else {
                    this.m_algorithmIndex_++;
                }
            }
            int i32 = this.m_algorithmIndex_;
            if (i32 < length) {
                int start = this.m_name_.getAlgorithmStart(i32);
                if (this.m_current_ < start) {
                    int end = start;
                    if (this.m_limit_ <= start) {
                        end = this.m_limit_;
                    }
                    if (!iterateGroup(element, end)) {
                        this.m_current_++;
                        return true;
                    }
                }
                int end2 = this.m_current_;
                if (end2 >= this.m_limit_) {
                    return false;
                }
                element.integer = end2;
                element.value = this.m_name_.getAlgorithmName(this.m_algorithmIndex_, end2);
                this.m_groupIndex_ = -1;
                this.m_current_++;
                return true;
            }
        }
        if (!iterateGroup(element, this.m_limit_)) {
            this.m_current_++;
            return true;
        } else if (this.m_choice_ != 2 || iterateExtended(element, this.m_limit_)) {
            return false;
        } else {
            this.m_current_++;
            return true;
        }
    }

    @Override // android.icu.util.ValueIterator
    public void reset() {
        this.m_current_ = this.m_start_;
        this.m_groupIndex_ = -1;
        this.m_algorithmIndex_ = -1;
    }

    @Override // android.icu.util.ValueIterator
    public void setRange(int start, int limit) {
        if (start < limit) {
            if (start < 0) {
                this.m_start_ = 0;
            } else {
                this.m_start_ = start;
            }
            if (limit > 1114112) {
                this.m_limit_ = 1114112;
            } else {
                this.m_limit_ = limit;
            }
            this.m_current_ = this.m_start_;
            return;
        }
        throw new IllegalArgumentException("start or limit has to be valid Unicode codepoints and start < limit");
    }

    protected UCharacterNameIterator(UCharacterName name, int choice) {
        if (name != null) {
            this.m_name_ = name;
            this.m_choice_ = choice;
            this.m_start_ = 0;
            this.m_limit_ = 1114112;
            this.m_current_ = this.m_start_;
            return;
        }
        throw new IllegalArgumentException("UCharacterName name argument cannot be null. Missing unames.icu?");
    }

    private boolean iterateSingleGroup(ValueIterator.Element result, int limit) {
        synchronized (GROUP_OFFSETS_) {
            synchronized (GROUP_LENGTHS_) {
                int index = this.m_name_.getGroupLengths(this.m_groupIndex_, GROUP_OFFSETS_, GROUP_LENGTHS_);
                while (this.m_current_ < limit) {
                    int offset = UCharacterName.getGroupOffset(this.m_current_);
                    String name = this.m_name_.getGroupName(GROUP_OFFSETS_[offset] + index, GROUP_LENGTHS_[offset], this.m_choice_);
                    if ((name == null || name.length() == 0) && this.m_choice_ == 2) {
                        name = this.m_name_.getExtendedName(this.m_current_);
                    }
                    if (name == null || name.length() <= 0) {
                        this.m_current_++;
                    } else {
                        result.integer = this.m_current_;
                        result.value = name;
                        return false;
                    }
                }
                return true;
            }
        }
    }

    private boolean iterateGroup(ValueIterator.Element result, int limit) {
        int i;
        if (this.m_groupIndex_ < 0) {
            this.m_groupIndex_ = this.m_name_.getGroup(this.m_current_);
        }
        while (this.m_groupIndex_ < this.m_name_.m_groupcount_ && (i = this.m_current_) < limit) {
            int startMSB = UCharacterName.getCodepointMSB(i);
            int gMSB = this.m_name_.getGroupMSB(this.m_groupIndex_);
            if (startMSB == gMSB) {
                if (startMSB == UCharacterName.getCodepointMSB(limit - 1)) {
                    return iterateSingleGroup(result, limit);
                }
                if (!iterateSingleGroup(result, UCharacterName.getGroupLimit(gMSB))) {
                    return false;
                }
                this.m_groupIndex_++;
            } else if (startMSB > gMSB) {
                this.m_groupIndex_++;
            } else {
                int gMIN = UCharacterName.getGroupMin(gMSB);
                if (gMIN > limit) {
                    gMIN = limit;
                }
                if (this.m_choice_ == 2 && !iterateExtended(result, gMIN)) {
                    return false;
                }
                this.m_current_ = gMIN;
            }
        }
        return true;
    }

    private boolean iterateExtended(ValueIterator.Element result, int limit) {
        while (true) {
            int i = this.m_current_;
            if (i >= limit) {
                return true;
            }
            String name = this.m_name_.getExtendedOr10Name(i);
            if (name == null || name.length() <= 0) {
                this.m_current_++;
            } else {
                result.integer = this.m_current_;
                result.value = name;
                return false;
            }
        }
    }
}
