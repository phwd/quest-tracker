package com.adobe.xmp.options;

import X.AnonymousClass006;
import com.adobe.xmp.XMPException;
import java.util.HashMap;
import java.util.Map;

public abstract class Options {
    public Map optionNames = null;
    public int options = 0;

    public void assertConsistency(int i) throws XMPException {
    }

    public void clear() {
        this.options = 0;
    }

    public abstract String defineOptionName(int i);

    public abstract int getValidOptions();

    private Map procureOptionNames() {
        Map map = this.optionNames;
        if (map != null) {
            return map;
        }
        HashMap hashMap = new HashMap();
        this.optionNames = hashMap;
        return hashMap;
    }

    public boolean containsAllOptions(int i) {
        if ((this.options & i) == i) {
            return true;
        }
        return false;
    }

    public boolean containsOneOf(int i) {
        if ((i & this.options) != 0) {
            return true;
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (this.options == ((Options) obj).options) {
            return true;
        }
        return false;
    }

    public boolean getOption(int i) {
        if ((i & this.options) != 0) {
            return true;
        }
        return false;
    }

    public String getOptionsString() {
        if (this.options == 0) {
            return "<none>";
        }
        StringBuffer stringBuffer = new StringBuffer();
        int i = this.options;
        if (i != 0) {
            while (true) {
                int i2 = (i - 1) & i;
                stringBuffer.append(getOptionName(i ^ i2));
                if (i2 == 0) {
                    break;
                }
                stringBuffer.append(" | ");
                i = i2;
            }
        }
        return stringBuffer.toString();
    }

    public boolean isExactly(int i) {
        if (this.options == i) {
            return true;
        }
        return false;
    }

    public void setOption(int i, boolean z) {
        int i2;
        if (z) {
            i2 = i | this.options;
        } else {
            i2 = (i ^ -1) & this.options;
        }
        this.options = i2;
    }

    public String toString() {
        return AnonymousClass006.A07("0x", Integer.toHexString(this.options));
    }

    private void assertOptionsValid(int i) throws XMPException {
        int validOptions = (getValidOptions() ^ -1) & i;
        if (validOptions == 0) {
            assertConsistency(i);
            return;
        }
        throw new XMPException(AnonymousClass006.A09("The option bit(s) 0x", Integer.toHexString(validOptions), " are invalid!"), 103);
    }

    private String getOptionName(int i) {
        Map procureOptionNames = procureOptionNames();
        Integer num = new Integer(i);
        String str = (String) procureOptionNames.get(num);
        if (str == null) {
            str = defineOptionName(i);
            if (str == null) {
                return "<option name not defined>";
            }
            procureOptionNames.put(num, str);
        }
        return str;
    }

    public int getOptions() {
        return this.options;
    }

    public int hashCode() {
        return this.options;
    }

    public void setOptions(int i) throws XMPException {
        assertOptionsValid(i);
        this.options = i;
    }

    public Options() {
    }

    public Options(int i) throws XMPException {
        assertOptionsValid(i);
        setOptions(i);
    }
}
