package org.apache.harmony.xml;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import libcore.io.IoUtils;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.LexicalHandler;

public class ExpatReader implements XMLReader {
    private static final String LEXICAL_HANDLER_PROPERTY = "http://xml.org/sax/properties/lexical-handler";
    ContentHandler contentHandler;
    DTDHandler dtdHandler;
    EntityResolver entityResolver;
    ErrorHandler errorHandler;
    LexicalHandler lexicalHandler;
    private boolean processNamespacePrefixes = false;
    private boolean processNamespaces = true;

    private static class Feature {
        private static final String BASE_URI = "http://xml.org/sax/features/";
        private static final String EXTERNAL_GENERAL_ENTITIES = "http://xml.org/sax/features/external-general-entities";
        private static final String EXTERNAL_PARAMETER_ENTITIES = "http://xml.org/sax/features/external-parameter-entities";
        private static final String NAMESPACES = "http://xml.org/sax/features/namespaces";
        private static final String NAMESPACE_PREFIXES = "http://xml.org/sax/features/namespace-prefixes";
        private static final String STRING_INTERNING = "http://xml.org/sax/features/string-interning";
        private static final String VALIDATION = "http://xml.org/sax/features/validation";

        private Feature() {
        }
    }

    @Override // org.xml.sax.XMLReader
    public boolean getFeature(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (name == null) {
            throw new NullPointerException("name == null");
        } else if (name.equals("http://xml.org/sax/features/validation") || name.equals("http://xml.org/sax/features/external-general-entities") || name.equals("http://xml.org/sax/features/external-parameter-entities")) {
            return false;
        } else {
            if (name.equals("http://xml.org/sax/features/namespaces")) {
                return this.processNamespaces;
            }
            if (name.equals("http://xml.org/sax/features/namespace-prefixes")) {
                return this.processNamespacePrefixes;
            }
            if (name.equals("http://xml.org/sax/features/string-interning")) {
                return true;
            }
            throw new SAXNotRecognizedException(name);
        }
    }

    @Override // org.xml.sax.XMLReader
    public void setFeature(String name, boolean value) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (name == null) {
            throw new NullPointerException("name == null");
        } else if (name.equals("http://xml.org/sax/features/validation") || name.equals("http://xml.org/sax/features/external-general-entities") || name.equals("http://xml.org/sax/features/external-parameter-entities")) {
            if (value) {
                throw new SAXNotSupportedException("Cannot enable " + name);
            }
        } else if (name.equals("http://xml.org/sax/features/namespaces")) {
            this.processNamespaces = value;
        } else if (name.equals("http://xml.org/sax/features/namespace-prefixes")) {
            this.processNamespacePrefixes = value;
        } else if (!name.equals("http://xml.org/sax/features/string-interning")) {
            throw new SAXNotRecognizedException(name);
        } else if (!value) {
            throw new SAXNotSupportedException("Cannot disable " + name);
        }
    }

    @Override // org.xml.sax.XMLReader
    public Object getProperty(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (name == null) {
            throw new NullPointerException("name == null");
        } else if (name.equals(LEXICAL_HANDLER_PROPERTY)) {
            return this.lexicalHandler;
        } else {
            throw new SAXNotRecognizedException(name);
        }
    }

    @Override // org.xml.sax.XMLReader
    public void setProperty(String name, Object value) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (name == null) {
            throw new NullPointerException("name == null");
        } else if (!name.equals(LEXICAL_HANDLER_PROPERTY)) {
            throw new SAXNotRecognizedException(name);
        } else if ((value instanceof LexicalHandler) || value == null) {
            this.lexicalHandler = (LexicalHandler) value;
        } else {
            throw new SAXNotSupportedException("value doesn't implement org.xml.sax.ext.LexicalHandler");
        }
    }

    @Override // org.xml.sax.XMLReader
    public void setEntityResolver(EntityResolver resolver) {
        this.entityResolver = resolver;
    }

    @Override // org.xml.sax.XMLReader
    public EntityResolver getEntityResolver() {
        return this.entityResolver;
    }

    @Override // org.xml.sax.XMLReader
    public void setDTDHandler(DTDHandler dtdHandler2) {
        this.dtdHandler = dtdHandler2;
    }

    @Override // org.xml.sax.XMLReader
    public DTDHandler getDTDHandler() {
        return this.dtdHandler;
    }

    @Override // org.xml.sax.XMLReader
    public void setContentHandler(ContentHandler handler) {
        this.contentHandler = handler;
    }

    @Override // org.xml.sax.XMLReader
    public ContentHandler getContentHandler() {
        return this.contentHandler;
    }

    @Override // org.xml.sax.XMLReader
    public void setErrorHandler(ErrorHandler handler) {
        this.errorHandler = handler;
    }

    @Override // org.xml.sax.XMLReader
    public ErrorHandler getErrorHandler() {
        return this.errorHandler;
    }

    public LexicalHandler getLexicalHandler() {
        return this.lexicalHandler;
    }

    public void setLexicalHandler(LexicalHandler lexicalHandler2) {
        this.lexicalHandler = lexicalHandler2;
    }

    public boolean isNamespaceProcessingEnabled() {
        return this.processNamespaces;
    }

    public void setNamespaceProcessingEnabled(boolean processNamespaces2) {
        this.processNamespaces = processNamespaces2;
    }

    @Override // org.xml.sax.XMLReader
    public void parse(InputSource input) throws IOException, SAXException {
        if (!this.processNamespacePrefixes || !this.processNamespaces) {
            Reader reader = input.getCharacterStream();
            if (reader != null) {
                try {
                    parse(reader, input.getPublicId(), input.getSystemId());
                } finally {
                    IoUtils.closeQuietly(reader);
                }
            } else {
                InputStream in = input.getByteStream();
                String encoding = input.getEncoding();
                if (in != null) {
                    try {
                        parse(in, encoding, input.getPublicId(), input.getSystemId());
                    } finally {
                        IoUtils.closeQuietly(in);
                    }
                } else {
                    String systemId = input.getSystemId();
                    if (systemId != null) {
                        InputStream in2 = ExpatParser.openUrl(systemId);
                        try {
                            parse(in2, encoding, input.getPublicId(), systemId);
                        } finally {
                            IoUtils.closeQuietly(in2);
                        }
                    } else {
                        throw new SAXException("No input specified.");
                    }
                }
            }
        } else {
            throw new SAXNotSupportedException("The 'namespace-prefix' feature is not supported while the 'namespaces' feature is enabled.");
        }
    }

    private void parse(Reader in, String publicId, String systemId) throws IOException, SAXException {
        new ExpatParser("UTF-16", this, this.processNamespaces, publicId, systemId).parseDocument(in);
    }

    private void parse(InputStream in, String charsetName, String publicId, String systemId) throws IOException, SAXException {
        new ExpatParser(charsetName, this, this.processNamespaces, publicId, systemId).parseDocument(in);
    }

    @Override // org.xml.sax.XMLReader
    public void parse(String systemId) throws IOException, SAXException {
        parse(new InputSource(systemId));
    }
}
