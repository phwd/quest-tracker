package com.adobe.xmp.options;

import com.adobe.xmp.XMPException;

public final class SerializeOptions extends Options {
    public static final int ENCODE_UTF16BE = 2;
    public static final int ENCODE_UTF16LE = 3;
    public static final int ENCODE_UTF8 = 0;
    public static final int ENCODING_MASK = 3;
    public static final int EXACT_PACKET_LENGTH = 512;
    public static final int INCLUDE_THUMBNAIL_PAD = 256;
    public static final int LITTLEENDIAN_BIT = 1;
    public static final int OMIT_PACKET_WRAPPER = 16;
    public static final int READONLY_PACKET = 32;
    public static final int SORT = 4096;
    public static final int USE_COMPACT_FORMAT = 64;
    public static final int UTF16_BIT = 2;
    public int baseIndent = 0;
    public String indent = "  ";
    public String newline = "\n";
    public boolean omitVersionAttribute = false;
    public int padding = 2048;

    @Override // com.adobe.xmp.options.Options
    public String defineOptionName(int i) {
        if (i == 16) {
            return "OMIT_PACKET_WRAPPER";
        }
        if (i == 32) {
            return "READONLY_PACKET";
        }
        if (i == 64) {
            return "USE_COMPACT_FORMAT";
        }
        if (i == 256) {
            return "INCLUDE_THUMBNAIL_PAD";
        }
        if (i == 512) {
            return "EXACT_PACKET_LENGTH";
        }
        if (i != 4096) {
            return null;
        }
        return "NORMALIZED";
    }

    @Override // com.adobe.xmp.options.Options
    public int getValidOptions() {
        return 4976;
    }

    public SerializeOptions setEncodeUTF16BE(boolean z) {
        setOption(3, false);
        setOption(2, z);
        return this;
    }

    public SerializeOptions setEncodeUTF16LE(boolean z) {
        setOption(3, false);
        setOption(3, z);
        return this;
    }

    public Object clone() throws CloneNotSupportedException {
        try {
            SerializeOptions serializeOptions = new SerializeOptions(this.options);
            serializeOptions.baseIndent = this.baseIndent;
            serializeOptions.indent = this.indent;
            serializeOptions.newline = this.newline;
            serializeOptions.padding = this.padding;
            return serializeOptions;
        } catch (XMPException unused) {
            return null;
        }
    }

    public boolean getEncodeUTF16BE() {
        if ((this.options & 3) == 2) {
            return true;
        }
        return false;
    }

    public boolean getEncodeUTF16LE() {
        if ((this.options & 3) == 3) {
            return true;
        }
        return false;
    }

    public boolean getExactPacketLength() {
        return getOption(512);
    }

    public boolean getIncludeThumbnailPad() {
        return getOption(256);
    }

    public boolean getOmitPacketWrapper() {
        return getOption(16);
    }

    public boolean getReadOnlyPacket() {
        return getOption(32);
    }

    public boolean getSort() {
        return getOption(4096);
    }

    public boolean getUseCompactFormat() {
        return getOption(64);
    }

    public SerializeOptions setExactPacketLength(boolean z) {
        setOption(512, z);
        return this;
    }

    public SerializeOptions setIncludeThumbnailPad(boolean z) {
        setOption(256, z);
        return this;
    }

    public SerializeOptions setOmitPacketWrapper(boolean z) {
        setOption(16, z);
        return this;
    }

    public SerializeOptions setReadOnlyPacket(boolean z) {
        setOption(32, z);
        return this;
    }

    public SerializeOptions setSort(boolean z) {
        setOption(4096, z);
        return this;
    }

    public SerializeOptions setUseCompactFormat(boolean z) {
        setOption(64, z);
        return this;
    }

    public int getBaseIndent() {
        return this.baseIndent;
    }

    public String getEncoding() {
        if (getEncodeUTF16BE()) {
            return "UTF-16BE";
        }
        if (getEncodeUTF16LE()) {
            return "UTF-16LE";
        }
        return "UTF-8";
    }

    public String getIndent() {
        return this.indent;
    }

    public String getNewline() {
        return this.newline;
    }

    public boolean getOmitVersionAttribute() {
        return this.omitVersionAttribute;
    }

    public int getPadding() {
        return this.padding;
    }

    public SerializeOptions setBaseIndent(int i) {
        this.baseIndent = i;
        return this;
    }

    public SerializeOptions setIndent(String str) {
        this.indent = str;
        return this;
    }

    public SerializeOptions setNewline(String str) {
        this.newline = str;
        return this;
    }

    public SerializeOptions setPadding(int i) {
        this.padding = i;
        return this;
    }

    public SerializeOptions() {
    }

    public SerializeOptions(int i) throws XMPException {
        super(i);
    }
}
