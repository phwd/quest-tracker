package com.adobe.xmp.options;

import com.adobe.xmp.XMPException;
import java.util.Map;

public abstract class Options {
    private Map optionNames = null;
    private int options = 0;

    /* access modifiers changed from: protected */
    public abstract int getValidOptions();

    public Options() {
    }

    public Options(int options2) throws XMPException {
        assertOptionsValid(options2);
        setOptions(options2);
    }

    /* access modifiers changed from: protected */
    public boolean getOption(int optionBit) {
        return (this.options & optionBit) != 0;
    }

    public void setOption(int optionBits, boolean value) {
        this.options = value ? this.options | optionBits : this.options & (optionBits ^ -1);
    }

    public int getOptions() {
        return this.options;
    }

    public void setOptions(int options2) throws XMPException {
        assertOptionsValid(options2);
        this.options = options2;
    }

    public boolean equals(Object obj) {
        return getOptions() == ((Options) obj).getOptions();
    }

    public int hashCode() {
        return getOptions();
    }

    public String toString() {
        return "0x" + Integer.toHexString(this.options);
    }

    /* access modifiers changed from: protected */
    public void assertConsistency(int options2) throws XMPException {
    }

    private void assertOptionsValid(int options2) throws XMPException {
        int invalidOptions = options2 & (getValidOptions() ^ -1);
        if (invalidOptions == 0) {
            assertConsistency(options2);
            return;
        }
        throw new XMPException("The option bit(s) 0x" + Integer.toHexString(invalidOptions) + " are invalid!", 103);
    }
}
