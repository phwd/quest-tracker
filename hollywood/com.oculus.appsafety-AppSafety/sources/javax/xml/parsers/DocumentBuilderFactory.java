package javax.xml.parsers;

import javax.xml.validation.Schema;
import org.apache.harmony.xml.parsers.DocumentBuilderFactoryImpl;

public abstract class DocumentBuilderFactory {
    private boolean coalescing = false;
    private boolean expandEntityRef = true;
    private boolean ignoreComments = false;
    private boolean namespaceAware = false;
    private boolean validating = false;
    private boolean whitespace = false;

    public abstract Object getAttribute(String str) throws IllegalArgumentException;

    public abstract boolean getFeature(String str) throws ParserConfigurationException;

    public abstract DocumentBuilder newDocumentBuilder() throws ParserConfigurationException;

    public abstract void setAttribute(String str, Object obj) throws IllegalArgumentException;

    public abstract void setFeature(String str, boolean z) throws ParserConfigurationException;

    protected DocumentBuilderFactory() {
    }

    public static DocumentBuilderFactory newInstance() {
        return new DocumentBuilderFactoryImpl();
    }

    public static DocumentBuilderFactory newInstance(String factoryClassName, ClassLoader classLoader) {
        Class<?> type;
        if (factoryClassName != null) {
            if (classLoader == null) {
                classLoader = Thread.currentThread().getContextClassLoader();
            }
            if (classLoader != null) {
                try {
                    type = classLoader.loadClass(factoryClassName);
                } catch (ClassNotFoundException e) {
                    throw new FactoryConfigurationError(e);
                } catch (InstantiationException e2) {
                    throw new FactoryConfigurationError(e2);
                } catch (IllegalAccessException e3) {
                    throw new FactoryConfigurationError(e3);
                }
            } else {
                type = Class.forName(factoryClassName);
            }
            return (DocumentBuilderFactory) type.newInstance();
        }
        throw new FactoryConfigurationError("factoryClassName == null");
    }

    public void setNamespaceAware(boolean awareness) {
        this.namespaceAware = awareness;
    }

    public void setValidating(boolean validating2) {
        this.validating = validating2;
    }

    public void setIgnoringElementContentWhitespace(boolean whitespace2) {
        this.whitespace = whitespace2;
    }

    public void setExpandEntityReferences(boolean expandEntityRef2) {
        this.expandEntityRef = expandEntityRef2;
    }

    public void setIgnoringComments(boolean ignoreComments2) {
        this.ignoreComments = ignoreComments2;
    }

    public void setCoalescing(boolean coalescing2) {
        this.coalescing = coalescing2;
    }

    public boolean isNamespaceAware() {
        return this.namespaceAware;
    }

    public boolean isValidating() {
        return this.validating;
    }

    public boolean isIgnoringElementContentWhitespace() {
        return this.whitespace;
    }

    public boolean isExpandEntityReferences() {
        return this.expandEntityRef;
    }

    public boolean isIgnoringComments() {
        return this.ignoreComments;
    }

    public boolean isCoalescing() {
        return this.coalescing;
    }

    public Schema getSchema() {
        throw new UnsupportedOperationException("This parser does not support specification \"" + getClass().getPackage().getSpecificationTitle() + "\" version \"" + getClass().getPackage().getSpecificationVersion() + "\"");
    }

    public void setSchema(Schema schema) {
        throw new UnsupportedOperationException("This parser does not support specification \"" + getClass().getPackage().getSpecificationTitle() + "\" version \"" + getClass().getPackage().getSpecificationVersion() + "\"");
    }

    public void setXIncludeAware(boolean state) {
        throw new UnsupportedOperationException("This parser does not support specification \"" + getClass().getPackage().getSpecificationTitle() + "\" version \"" + getClass().getPackage().getSpecificationVersion() + "\"");
    }

    public boolean isXIncludeAware() {
        throw new UnsupportedOperationException("This parser does not support specification \"" + getClass().getPackage().getSpecificationTitle() + "\" version \"" + getClass().getPackage().getSpecificationVersion() + "\"");
    }
}
