package org.xml.sax.ext;

import libcore.util.EmptyArray;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.AttributesImpl;

public class Attributes2Impl extends AttributesImpl implements Attributes2 {
    private boolean[] declared;
    private boolean[] specified;

    public Attributes2Impl() {
        this.declared = EmptyArray.BOOLEAN;
        this.specified = EmptyArray.BOOLEAN;
    }

    public Attributes2Impl(Attributes atts) {
        super(atts);
    }

    @Override // org.xml.sax.ext.Attributes2
    public boolean isDeclared(int index) {
        if (index >= 0 && index < getLength()) {
            return this.declared[index];
        }
        throw new ArrayIndexOutOfBoundsException("No attribute at index: " + index);
    }

    @Override // org.xml.sax.ext.Attributes2
    public boolean isDeclared(String uri, String localName) {
        int index = getIndex(uri, localName);
        if (index >= 0) {
            return this.declared[index];
        }
        throw new IllegalArgumentException("No such attribute: local=" + localName + ", namespace=" + uri);
    }

    @Override // org.xml.sax.ext.Attributes2
    public boolean isDeclared(String qName) {
        int index = getIndex(qName);
        if (index >= 0) {
            return this.declared[index];
        }
        throw new IllegalArgumentException("No such attribute: " + qName);
    }

    @Override // org.xml.sax.ext.Attributes2
    public boolean isSpecified(int index) {
        if (index >= 0 && index < getLength()) {
            return this.specified[index];
        }
        throw new ArrayIndexOutOfBoundsException("No attribute at index: " + index);
    }

    @Override // org.xml.sax.ext.Attributes2
    public boolean isSpecified(String uri, String localName) {
        int index = getIndex(uri, localName);
        if (index >= 0) {
            return this.specified[index];
        }
        throw new IllegalArgumentException("No such attribute: local=" + localName + ", namespace=" + uri);
    }

    @Override // org.xml.sax.ext.Attributes2
    public boolean isSpecified(String qName) {
        int index = getIndex(qName);
        if (index >= 0) {
            return this.specified[index];
        }
        throw new IllegalArgumentException("No such attribute: " + qName);
    }

    @Override // org.xml.sax.helpers.AttributesImpl
    public void setAttributes(Attributes atts) {
        int length = atts.getLength();
        super.setAttributes(atts);
        this.declared = new boolean[length];
        this.specified = new boolean[length];
        if (atts instanceof Attributes2) {
            Attributes2 a2 = (Attributes2) atts;
            for (int i = 0; i < length; i++) {
                this.declared[i] = a2.isDeclared(i);
                this.specified[i] = a2.isSpecified(i);
            }
            return;
        }
        for (int i2 = 0; i2 < length; i2++) {
            this.declared[i2] = !"CDATA".equals(atts.getType(i2));
            this.specified[i2] = true;
        }
    }

    @Override // org.xml.sax.helpers.AttributesImpl
    public void addAttribute(String uri, String localName, String qName, String type, String value) {
        super.addAttribute(uri, localName, qName, type, value);
        int length = getLength();
        if (length > this.specified.length) {
            boolean[] newFlags = new boolean[length];
            boolean[] zArr = this.declared;
            System.arraycopy((Object) zArr, 0, (Object) newFlags, 0, zArr.length);
            this.declared = newFlags;
            boolean[] newFlags2 = new boolean[length];
            boolean[] zArr2 = this.specified;
            System.arraycopy((Object) zArr2, 0, (Object) newFlags2, 0, zArr2.length);
            this.specified = newFlags2;
        }
        this.specified[length - 1] = true;
        this.declared[length - 1] = true ^ "CDATA".equals(type);
    }

    @Override // org.xml.sax.helpers.AttributesImpl
    public void removeAttribute(int index) {
        int origMax = getLength() - 1;
        super.removeAttribute(index);
        if (index != origMax) {
            boolean[] zArr = this.declared;
            System.arraycopy((Object) zArr, index + 1, (Object) zArr, index, origMax - index);
            boolean[] zArr2 = this.specified;
            System.arraycopy((Object) zArr2, index + 1, (Object) zArr2, index, origMax - index);
        }
    }

    public void setDeclared(int index, boolean value) {
        if (index < 0 || index >= getLength()) {
            throw new ArrayIndexOutOfBoundsException("No attribute at index: " + index);
        }
        this.declared[index] = value;
    }

    public void setSpecified(int index, boolean value) {
        if (index < 0 || index >= getLength()) {
            throw new ArrayIndexOutOfBoundsException("No attribute at index: " + index);
        }
        this.specified[index] = value;
    }
}
