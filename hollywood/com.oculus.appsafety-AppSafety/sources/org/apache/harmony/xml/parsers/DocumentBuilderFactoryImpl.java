package org.apache.harmony.xml.parsers;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class DocumentBuilderFactoryImpl extends DocumentBuilderFactory {
    private static final String NAMESPACES = "http://xml.org/sax/features/namespaces";
    private static final String VALIDATION = "http://xml.org/sax/features/validation";

    @Override // javax.xml.parsers.DocumentBuilderFactory
    public Object getAttribute(String name) throws IllegalArgumentException {
        throw new IllegalArgumentException(name);
    }

    @Override // javax.xml.parsers.DocumentBuilderFactory
    public boolean getFeature(String name) throws ParserConfigurationException {
        if (name == null) {
            throw new NullPointerException("name == null");
        } else if (NAMESPACES.equals(name)) {
            return isNamespaceAware();
        } else {
            if (VALIDATION.equals(name)) {
                return isValidating();
            }
            throw new ParserConfigurationException(name);
        }
    }

    @Override // javax.xml.parsers.DocumentBuilderFactory
    public DocumentBuilder newDocumentBuilder() throws ParserConfigurationException {
        if (!isValidating()) {
            DocumentBuilderImpl builder = new DocumentBuilderImpl();
            builder.setCoalescing(isCoalescing());
            builder.setIgnoreComments(isIgnoringComments());
            builder.setIgnoreElementContentWhitespace(isIgnoringElementContentWhitespace());
            builder.setNamespaceAware(isNamespaceAware());
            return builder;
        }
        throw new ParserConfigurationException("No validating DocumentBuilder implementation available");
    }

    @Override // javax.xml.parsers.DocumentBuilderFactory
    public void setAttribute(String name, Object value) throws IllegalArgumentException {
        throw new IllegalArgumentException(name);
    }

    @Override // javax.xml.parsers.DocumentBuilderFactory
    public void setFeature(String name, boolean value) throws ParserConfigurationException {
        if (name == null) {
            throw new NullPointerException("name == null");
        } else if (NAMESPACES.equals(name)) {
            setNamespaceAware(value);
        } else if (VALIDATION.equals(name)) {
            setValidating(value);
        } else {
            throw new ParserConfigurationException(name);
        }
    }
}
