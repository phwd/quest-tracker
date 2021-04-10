package org.apache.harmony.xml.parsers;

import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXNotRecognizedException;

public class SAXParserFactoryImpl extends SAXParserFactory {
    private static final String NAMESPACES = "http://xml.org/sax/features/namespaces";
    private static final String VALIDATION = "http://xml.org/sax/features/validation";
    private Map<String, Boolean> features = new HashMap();

    @Override // javax.xml.parsers.SAXParserFactory
    public boolean getFeature(String name) throws SAXNotRecognizedException {
        if (name == null) {
            throw new NullPointerException("name == null");
        } else if (name.startsWith("http://xml.org/sax/features/")) {
            return Boolean.TRUE.equals(this.features.get(name));
        } else {
            throw new SAXNotRecognizedException(name);
        }
    }

    @Override // javax.xml.parsers.SAXParserFactory
    public boolean isNamespaceAware() {
        try {
            return getFeature(NAMESPACES);
        } catch (SAXNotRecognizedException ex) {
            throw new AssertionError(ex);
        }
    }

    @Override // javax.xml.parsers.SAXParserFactory
    public boolean isValidating() {
        try {
            return getFeature(VALIDATION);
        } catch (SAXNotRecognizedException ex) {
            throw new AssertionError(ex);
        }
    }

    @Override // javax.xml.parsers.SAXParserFactory
    public SAXParser newSAXParser() throws ParserConfigurationException {
        if (!isValidating()) {
            try {
                return new SAXParserImpl(this.features);
            } catch (Exception ex) {
                throw new ParserConfigurationException(ex.toString());
            }
        } else {
            throw new ParserConfigurationException("No validating SAXParser implementation available");
        }
    }

    @Override // javax.xml.parsers.SAXParserFactory
    public void setFeature(String name, boolean value) throws SAXNotRecognizedException {
        if (name == null) {
            throw new NullPointerException("name == null");
        } else if (!name.startsWith("http://xml.org/sax/features/")) {
            throw new SAXNotRecognizedException(name);
        } else if (value) {
            this.features.put(name, Boolean.TRUE);
        } else {
            this.features.put(name, Boolean.FALSE);
        }
    }

    @Override // javax.xml.parsers.SAXParserFactory
    public void setNamespaceAware(boolean value) {
        try {
            setFeature(NAMESPACES, value);
        } catch (SAXNotRecognizedException ex) {
            throw new AssertionError(ex);
        }
    }

    @Override // javax.xml.parsers.SAXParserFactory
    public void setValidating(boolean value) {
        try {
            setFeature(VALIDATION, value);
        } catch (SAXNotRecognizedException ex) {
            throw new AssertionError(ex);
        }
    }
}
