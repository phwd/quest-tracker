package com.fasterxml.jackson.databind.ext;

import X.AbstractC0122Rd;
import X.AnonymousClass06;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

public abstract class DOMDeserializer<T> extends FromStringDeserializer<T> {
    public static final DocumentBuilderFactory A00;
    public static final long serialVersionUID = 1;

    public static class DocumentDeserializer extends DOMDeserializer<Document> {
        public static final long serialVersionUID = 1;

        public DocumentDeserializer() {
            super(Document.class);
        }
    }

    public static class NodeDeserializer extends DOMDeserializer<Node> {
        public static final long serialVersionUID = 1;

        public NodeDeserializer() {
            super(Node.class);
        }
    }

    public static final Document A00(String str) throws IllegalArgumentException {
        try {
            return A00.newDocumentBuilder().parse(new InputSource(new StringReader(str)));
        } catch (Exception e) {
            throw new IllegalArgumentException(AnonymousClass06.A03("Failed to parse JSON String as XML: ", e.getMessage()), e);
        }
    }

    static {
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        A00 = newInstance;
        newInstance.setNamespaceAware(true);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.FromStringDeserializer
    public final T A0A(String str, AbstractC0122Rd rd) {
        return (T) A00(str);
    }

    public DOMDeserializer(Class<T> cls) {
        super(cls);
    }
}
