package org.apache.harmony.xml.dom;

import org.w3c.dom.CharacterData;
import org.w3c.dom.DOMException;

public abstract class CharacterDataImpl extends LeafNodeImpl implements CharacterData {
    protected StringBuffer buffer;

    CharacterDataImpl(DocumentImpl document, String data) {
        super(document);
        setData(data);
    }

    @Override // org.w3c.dom.CharacterData
    public void appendData(String arg) throws DOMException {
        this.buffer.append(arg);
    }

    @Override // org.w3c.dom.CharacterData
    public void deleteData(int offset, int count) throws DOMException {
        this.buffer.delete(offset, offset + count);
    }

    @Override // org.w3c.dom.CharacterData
    public String getData() throws DOMException {
        return this.buffer.toString();
    }

    public void appendDataTo(StringBuilder stringBuilder) {
        stringBuilder.append(this.buffer);
    }

    @Override // org.w3c.dom.CharacterData
    public int getLength() {
        return this.buffer.length();
    }

    @Override // org.apache.harmony.xml.dom.NodeImpl, org.w3c.dom.Node
    public String getNodeValue() {
        return getData();
    }

    @Override // org.w3c.dom.CharacterData
    public void insertData(int offset, String arg) throws DOMException {
        try {
            this.buffer.insert(offset, arg);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DOMException(1, null);
        }
    }

    @Override // org.w3c.dom.CharacterData
    public void replaceData(int offset, int count, String arg) throws DOMException {
        try {
            this.buffer.replace(offset, offset + count, arg);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DOMException(1, null);
        }
    }

    @Override // org.w3c.dom.CharacterData
    public void setData(String data) throws DOMException {
        this.buffer = new StringBuffer(data);
    }

    @Override // org.w3c.dom.CharacterData
    public String substringData(int offset, int count) throws DOMException {
        try {
            return this.buffer.substring(offset, offset + count);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DOMException(1, null);
        }
    }
}
