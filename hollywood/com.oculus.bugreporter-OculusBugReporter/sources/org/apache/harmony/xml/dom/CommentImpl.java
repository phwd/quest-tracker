package org.apache.harmony.xml.dom;

import org.w3c.dom.Comment;

public final class CommentImpl extends CharacterDataImpl implements Comment {
    CommentImpl(DocumentImpl document, String data) {
        super(document, data);
    }

    @Override // org.apache.harmony.xml.dom.NodeImpl, org.w3c.dom.Node
    public String getNodeName() {
        return "#comment";
    }

    @Override // org.apache.harmony.xml.dom.NodeImpl, org.w3c.dom.Node
    public short getNodeType() {
        return 8;
    }

    public boolean containsDashDash() {
        return this.buffer.indexOf("--") != -1;
    }
}
