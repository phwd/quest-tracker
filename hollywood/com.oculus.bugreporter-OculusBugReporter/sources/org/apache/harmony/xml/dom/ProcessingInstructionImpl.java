package org.apache.harmony.xml.dom;

import org.w3c.dom.DOMException;
import org.w3c.dom.ProcessingInstruction;

public final class ProcessingInstructionImpl extends LeafNodeImpl implements ProcessingInstruction {
    private String data;
    private String target;

    ProcessingInstructionImpl(DocumentImpl document, String target2, String data2) {
        super(document);
        this.target = target2;
        this.data = data2;
    }

    @Override // org.w3c.dom.ProcessingInstruction
    public String getData() {
        return this.data;
    }

    @Override // org.apache.harmony.xml.dom.NodeImpl, org.w3c.dom.Node
    public String getNodeName() {
        return this.target;
    }

    @Override // org.apache.harmony.xml.dom.NodeImpl, org.w3c.dom.Node
    public short getNodeType() {
        return 7;
    }

    @Override // org.apache.harmony.xml.dom.NodeImpl, org.w3c.dom.Node
    public String getNodeValue() {
        return this.data;
    }

    @Override // org.w3c.dom.ProcessingInstruction
    public String getTarget() {
        return this.target;
    }

    @Override // org.w3c.dom.ProcessingInstruction
    public void setData(String data2) throws DOMException {
        this.data = data2;
    }
}
