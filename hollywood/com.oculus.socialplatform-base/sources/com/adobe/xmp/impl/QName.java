package com.adobe.xmp.impl;

public class QName {
    public String localName;
    public String prefix;

    public boolean hasPrefix() {
        String str = this.prefix;
        if (str == null || str.length() <= 0) {
            return false;
        }
        return true;
    }

    public String getLocalName() {
        return this.localName;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public QName(String str) {
        int indexOf = str.indexOf(58);
        if (indexOf >= 0) {
            this.prefix = str.substring(0, indexOf);
            str = str.substring(indexOf + 1);
        } else {
            this.prefix = "";
        }
        this.localName = str;
    }

    public QName(String str, String str2) {
        this.prefix = str;
        this.localName = str2;
    }
}
