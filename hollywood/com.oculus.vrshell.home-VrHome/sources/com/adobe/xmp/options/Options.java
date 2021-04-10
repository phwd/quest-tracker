package com.adobe.xmp.options;

import com.adobe.xmp.XMPError;
import com.adobe.xmp.XMPException;
import java.util.HashMap;
import java.util.Map;

public abstract class Options {
    private Map optionNames = null;
    private int options = 0;

    /* access modifiers changed from: protected */
    public abstract String defineOptionName(int i);

    /* access modifiers changed from: protected */
    public abstract int getValidOptions();

    public Options() {
    }

    public Options(int options2) throws XMPException {
        assertOptionsValid(options2);
        setOptions(options2);
    }

    public void clear() {
        this.options = 0;
    }

    public boolean isExactly(int optionBits) {
        return getOptions() == optionBits;
    }

    public boolean containsAllOptions(int optionBits) {
        return (getOptions() & optionBits) == optionBits;
    }

    public boolean containsOneOf(int optionBits) {
        return (getOptions() & optionBits) != 0;
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

    public String getOptionsString() {
        if (this.options == 0) {
            return "<none>";
        }
        StringBuffer sb = new StringBuffer();
        int theBits = this.options;
        while (theBits != 0) {
            int oneLessBit = theBits & (theBits - 1);
            sb.append(getOptionName(theBits ^ oneLessBit));
            if (oneLessBit != 0) {
                sb.append(" | ");
            }
            theBits = oneLessBit;
        }
        return sb.toString();
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
        throw new XMPException("The option bit(s) 0x" + Integer.toHexString(invalidOptions) + " are invalid!", XMPError.BADOPTIONS);
    }

    private String getOptionName(int option) {
        Map optionsNames = procureOptionNames();
        Integer key = new Integer(option);
        String result = (String) optionsNames.get(key);
        if (result != null) {
            return result;
        }
        String result2 = defineOptionName(option);
        if (result2 == null) {
            return "<option name not defined>";
        }
        optionsNames.put(key, result2);
        return result2;
    }

    private Map procureOptionNames() {
        if (this.optionNames == null) {
            this.optionNames = new HashMap();
        }
        return this.optionNames;
    }
}
