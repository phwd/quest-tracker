package org.apache.harmony.xml.dom;

import org.w3c.dom.DocumentFragment;

public class DocumentFragmentImpl extends InnerNodeImpl implements DocumentFragment {
    DocumentFragmentImpl(DocumentImpl document) {
        super(document);
    }

    @Override // org.apache.harmony.xml.dom.NodeImpl, org.w3c.dom.Node
    public String getNodeName() {
        return "#document-fragment";
    }

    @Override // org.apache.harmony.xml.dom.NodeImpl, org.w3c.dom.Node
    public short getNodeType() {
        return 11;
    }
}
