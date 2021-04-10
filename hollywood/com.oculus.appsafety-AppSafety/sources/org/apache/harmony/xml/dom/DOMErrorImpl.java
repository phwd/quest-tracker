package org.apache.harmony.xml.dom;

import org.w3c.dom.DOMError;
import org.w3c.dom.DOMLocator;
import org.w3c.dom.Node;

public final class DOMErrorImpl implements DOMError {
    private static final DOMLocator NULL_DOM_LOCATOR = new DOMLocator() {
        /* class org.apache.harmony.xml.dom.DOMErrorImpl.AnonymousClass1 */

        @Override // org.w3c.dom.DOMLocator
        public int getLineNumber() {
            return -1;
        }

        @Override // org.w3c.dom.DOMLocator
        public int getColumnNumber() {
            return -1;
        }

        @Override // org.w3c.dom.DOMLocator
        public int getByteOffset() {
            return -1;
        }

        @Override // org.w3c.dom.DOMLocator
        public int getUtf16Offset() {
            return -1;
        }

        @Override // org.w3c.dom.DOMLocator
        public Node getRelatedNode() {
            return null;
        }

        @Override // org.w3c.dom.DOMLocator
        public String getUri() {
            return null;
        }
    };
    private final short severity;
    private final String type;

    public DOMErrorImpl(short severity2, String type2) {
        this.severity = severity2;
        this.type = type2;
    }

    @Override // org.w3c.dom.DOMError
    public short getSeverity() {
        return this.severity;
    }

    @Override // org.w3c.dom.DOMError
    public String getMessage() {
        return this.type;
    }

    @Override // org.w3c.dom.DOMError
    public String getType() {
        return this.type;
    }

    @Override // org.w3c.dom.DOMError
    public Object getRelatedException() {
        return null;
    }

    @Override // org.w3c.dom.DOMError
    public Object getRelatedData() {
        return null;
    }

    @Override // org.w3c.dom.DOMError
    public DOMLocator getLocation() {
        return NULL_DOM_LOCATOR;
    }
}
