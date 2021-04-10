package com.fasterxml.jackson.databind.ext;

import X.AbstractC1022qr;
import X.AnonymousClass08;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

public abstract class DOMDeserializer extends FromStringDeserializer {
    public static final DocumentBuilderFactory A00;
    public static final long serialVersionUID = 1;

    public class DocumentDeserializer extends DOMDeserializer {
        public static final long serialVersionUID = 1;

        public DocumentDeserializer() {
            super(Document.class);
        }
    }

    public class NodeDeserializer extends DOMDeserializer {
        public static final long serialVersionUID = 1;

        public NodeDeserializer() {
            super(Node.class);
        }
    }

    public static final Document A00(String str) {
        try {
            return A00.newDocumentBuilder().parse(new InputSource(new StringReader(str)));
        } catch (Exception e) {
            throw new IllegalArgumentException(AnonymousClass08.A04("Failed to parse JSON String as XML: ", e.getMessage()), e);
        }
    }

    static {
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        A00 = newInstance;
        newInstance.setNamespaceAware(true);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.FromStringDeserializer
    public final Object A0O(String str, AbstractC1022qr qrVar) {
        return A00(str);
    }

    public DOMDeserializer(Class cls) {
        super(cls);
    }
}
