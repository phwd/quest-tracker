package com.adobe.xmp.impl.xpath;

public class XMPPathSegment {
    public boolean alias;
    public int aliasForm;
    public int kind;
    public String name;

    public int getAliasForm() {
        return this.aliasForm;
    }

    public int getKind() {
        return this.kind;
    }

    public String getName() {
        return this.name;
    }

    public boolean isAlias() {
        return this.alias;
    }

    public String toString() {
        return this.name;
    }

    public void setAlias(boolean z) {
        this.alias = z;
    }

    public void setAliasForm(int i) {
        this.aliasForm = i;
    }

    public void setKind(int i) {
        this.kind = i;
    }

    public void setName(String str) {
        this.name = str;
    }

    public XMPPathSegment(String str) {
        this.name = str;
    }

    public XMPPathSegment(String str, int i) {
        this.name = str;
        this.kind = i;
    }
}
