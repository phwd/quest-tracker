package com.fasterxml.jackson.databind.ext;

import X.AbstractC04020gg;
import X.AnonymousClass006;
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

        /* Return type fixed from 'java.lang.Object' to match base method */
        @Override // com.fasterxml.jackson.databind.deser.std.FromStringDeserializer, com.fasterxml.jackson.databind.ext.DOMDeserializer
        public final Document A0Q(String str, AbstractC04020gg r3) {
            return DOMDeserializer.A00(str);
        }
    }

    public static class NodeDeserializer extends DOMDeserializer<Node> {
        public static final long serialVersionUID = 1;

        public NodeDeserializer() {
            super(Node.class);
        }

        /* Return type fixed from 'java.lang.Object' to match base method */
        @Override // com.fasterxml.jackson.databind.deser.std.FromStringDeserializer, com.fasterxml.jackson.databind.ext.DOMDeserializer
        public final Node A0Q(String str, AbstractC04020gg r3) {
            return DOMDeserializer.A00(str);
        }
    }

    @Override // com.fasterxml.jackson.databind.deser.std.FromStringDeserializer
    public abstract T A0Q(String str, AbstractC04020gg v);

    public static final Document A00(String str) throws IllegalArgumentException {
        try {
            return A00.newDocumentBuilder().parse(new InputSource(new StringReader(str)));
        } catch (Exception e) {
            throw new IllegalArgumentException(AnonymousClass006.A05("Failed to parse JSON String as XML: ", e.getMessage()), e);
        }
    }

    static {
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        A00 = newInstance;
        newInstance.setNamespaceAware(true);
    }

    public DOMDeserializer(Class<T> cls) {
        super(cls);
    }
}
