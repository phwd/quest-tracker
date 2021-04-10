package com.adobe.xmp.impl;

import com.adobe.xmp.XMPError;
import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPIterator;
import com.adobe.xmp.impl.xpath.XMPPath;
import com.adobe.xmp.impl.xpath.XMPPathParser;
import com.adobe.xmp.options.IteratorOptions;
import com.adobe.xmp.options.PropertyOptions;
import com.adobe.xmp.properties.XMPPropertyInfo;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class XMPIteratorImpl implements XMPIterator {
    private String baseNS = null;
    private Iterator nodeIterator = null;
    private IteratorOptions options;
    protected boolean skipSiblings = false;
    protected boolean skipSubtree = false;

    public XMPIteratorImpl(XMPMetaImpl xmp, String schemaNS, String propPath, IteratorOptions options2) throws XMPException {
        XMPNode startNode;
        this.options = options2 == null ? new IteratorOptions() : options2;
        String initialPath = null;
        boolean baseSchema = schemaNS != null && schemaNS.length() > 0;
        boolean baseProperty = propPath != null && propPath.length() > 0;
        if (!baseSchema && !baseProperty) {
            startNode = xmp.getRoot();
        } else if (baseSchema && baseProperty) {
            XMPPath path = XMPPathParser.expandXPath(schemaNS, propPath);
            XMPPath basePath = new XMPPath();
            for (int i = 0; i < path.size() - 1; i++) {
                basePath.add(path.getSegment(i));
            }
            startNode = XMPNodeUtils.findNode(xmp.getRoot(), path, false, null);
            this.baseNS = schemaNS;
            initialPath = basePath.toString();
        } else if (!baseSchema || baseProperty) {
            throw new XMPException("Schema namespace URI is required", XMPError.BADSCHEMA);
        } else {
            startNode = XMPNodeUtils.findSchemaNode(xmp.getRoot(), schemaNS, false);
        }
        if (startNode == null) {
            this.nodeIterator = Collections.EMPTY_LIST.iterator();
        } else if (!this.options.isJustChildren()) {
            this.nodeIterator = new NodeIterator(startNode, initialPath, 1);
        } else {
            this.nodeIterator = new NodeIteratorChildren(startNode, initialPath);
        }
    }

    @Override // com.adobe.xmp.XMPIterator
    public void skipSubtree() {
        this.skipSubtree = true;
    }

    @Override // com.adobe.xmp.XMPIterator
    public void skipSiblings() {
        skipSubtree();
        this.skipSiblings = true;
    }

    public boolean hasNext() {
        return this.nodeIterator.hasNext();
    }

    @Override // java.util.Iterator
    public Object next() {
        return this.nodeIterator.next();
    }

    public void remove() {
        throw new UnsupportedOperationException("The XMPIterator does not support remove().");
    }

    /* access modifiers changed from: protected */
    public IteratorOptions getOptions() {
        return this.options;
    }

    /* access modifiers changed from: protected */
    public String getBaseNS() {
        return this.baseNS;
    }

    /* access modifiers changed from: protected */
    public void setBaseNS(String baseNS2) {
        this.baseNS = baseNS2;
    }

    private class NodeIterator implements Iterator {
        protected static final int ITERATE_CHILDREN = 1;
        protected static final int ITERATE_NODE = 0;
        protected static final int ITERATE_QUALIFIER = 2;
        private Iterator childrenIterator = null;
        private int index = 0;
        private String path;
        private XMPPropertyInfo returnProperty = null;
        private int state = 0;
        private Iterator subIterator = Collections.EMPTY_LIST.iterator();
        private XMPNode visitedNode;

        public NodeIterator() {
        }

        public NodeIterator(XMPNode visitedNode2, String parentPath, int index2) {
            this.visitedNode = visitedNode2;
            this.state = 0;
            if (visitedNode2.getOptions().isSchemaNode()) {
                XMPIteratorImpl.this.setBaseNS(visitedNode2.getName());
            }
            this.path = accumulatePath(visitedNode2, parentPath, index2);
        }

        public boolean hasNext() {
            if (this.returnProperty != null) {
                return true;
            }
            if (this.state == 0) {
                return reportNode();
            }
            if (this.state == 1) {
                if (this.childrenIterator == null) {
                    this.childrenIterator = this.visitedNode.iterateChildren();
                }
                boolean hasNext = iterateChildren(this.childrenIterator);
                if (hasNext || !this.visitedNode.hasQualifier() || XMPIteratorImpl.this.getOptions().isOmitQualifiers()) {
                    return hasNext;
                }
                this.state = 2;
                this.childrenIterator = null;
                return hasNext();
            }
            if (this.childrenIterator == null) {
                this.childrenIterator = this.visitedNode.iterateQualifier();
            }
            return iterateChildren(this.childrenIterator);
        }

        /* access modifiers changed from: protected */
        public boolean reportNode() {
            this.state = 1;
            if (this.visitedNode.getParent() == null || (XMPIteratorImpl.this.getOptions().isJustLeafnodes() && this.visitedNode.hasChildren())) {
                return hasNext();
            }
            this.returnProperty = createPropertyInfo(this.visitedNode, XMPIteratorImpl.this.getBaseNS(), this.path);
            return true;
        }

        private boolean iterateChildren(Iterator iterator) {
            if (XMPIteratorImpl.this.skipSiblings) {
                XMPIteratorImpl.this.skipSiblings = false;
                this.subIterator = Collections.EMPTY_LIST.iterator();
            }
            if (!this.subIterator.hasNext() && iterator.hasNext()) {
                this.index++;
                this.subIterator = new NodeIterator((XMPNode) iterator.next(), this.path, this.index);
            }
            if (!this.subIterator.hasNext()) {
                return false;
            }
            this.returnProperty = (XMPPropertyInfo) this.subIterator.next();
            return true;
        }

        @Override // java.util.Iterator
        public Object next() {
            if (hasNext()) {
                XMPPropertyInfo result = this.returnProperty;
                this.returnProperty = null;
                return result;
            }
            throw new NoSuchElementException("There are no more nodes to return");
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        /* access modifiers changed from: protected */
        public String accumulatePath(XMPNode currNode, String parentPath, int currentIndex) {
            String separator;
            String segmentName;
            if (currNode.getParent() == null || currNode.getOptions().isSchemaNode()) {
                return null;
            }
            if (currNode.getParent().getOptions().isArray()) {
                separator = "";
                segmentName = "[" + String.valueOf(currentIndex) + "]";
            } else {
                separator = "/";
                segmentName = currNode.getName();
            }
            if (parentPath == null || parentPath.length() == 0) {
                return segmentName;
            }
            if (!XMPIteratorImpl.this.getOptions().isJustLeafname()) {
                return parentPath + separator + segmentName;
            }
            if (segmentName.startsWith("?")) {
                return segmentName.substring(1);
            }
            return segmentName;
        }

        /* access modifiers changed from: protected */
        public XMPPropertyInfo createPropertyInfo(final XMPNode node, final String baseNS, final String path2) {
            final String value = node.getOptions().isSchemaNode() ? null : node.getValue();
            return new XMPPropertyInfo() {
                /* class com.adobe.xmp.impl.XMPIteratorImpl.NodeIterator.AnonymousClass1 */

                @Override // com.adobe.xmp.properties.XMPPropertyInfo
                public String getNamespace() {
                    return baseNS;
                }

                @Override // com.adobe.xmp.properties.XMPPropertyInfo
                public String getPath() {
                    return path2;
                }

                @Override // com.adobe.xmp.properties.XMPPropertyInfo, com.adobe.xmp.properties.XMPProperty
                public Object getValue() {
                    return value;
                }

                @Override // com.adobe.xmp.properties.XMPPropertyInfo, com.adobe.xmp.properties.XMPProperty
                public PropertyOptions getOptions() {
                    return node.getOptions();
                }

                @Override // com.adobe.xmp.properties.XMPProperty
                public String getLanguage() {
                    return null;
                }
            };
        }

        /* access modifiers changed from: protected */
        public Iterator getChildrenIterator() {
            return this.childrenIterator;
        }

        /* access modifiers changed from: protected */
        public void setChildrenIterator(Iterator childrenIterator2) {
            this.childrenIterator = childrenIterator2;
        }

        /* access modifiers changed from: protected */
        public XMPPropertyInfo getReturnProperty() {
            return this.returnProperty;
        }

        /* access modifiers changed from: protected */
        public void setReturnProperty(XMPPropertyInfo returnProperty2) {
            this.returnProperty = returnProperty2;
        }
    }

    private class NodeIteratorChildren extends NodeIterator {
        private Iterator childrenIterator;
        private int index = 0;
        private String parentPath;

        public NodeIteratorChildren(XMPNode parentNode, String parentPath2) {
            super();
            if (parentNode.getOptions().isSchemaNode()) {
                XMPIteratorImpl.this.setBaseNS(parentNode.getName());
            }
            this.parentPath = accumulatePath(parentNode, parentPath2, 1);
            this.childrenIterator = parentNode.iterateChildren();
        }

        @Override // com.adobe.xmp.impl.XMPIteratorImpl.NodeIterator
        public boolean hasNext() {
            if (getReturnProperty() != null) {
                return true;
            }
            if (XMPIteratorImpl.this.skipSiblings) {
                return false;
            }
            if (!this.childrenIterator.hasNext()) {
                return false;
            }
            XMPNode child = (XMPNode) this.childrenIterator.next();
            this.index++;
            String path = null;
            if (child.getOptions().isSchemaNode()) {
                XMPIteratorImpl.this.setBaseNS(child.getName());
            } else if (child.getParent() != null) {
                path = accumulatePath(child, this.parentPath, this.index);
            }
            if (XMPIteratorImpl.this.getOptions().isJustLeafnodes() && child.hasChildren()) {
                return hasNext();
            }
            setReturnProperty(createPropertyInfo(child, XMPIteratorImpl.this.getBaseNS(), path));
            return true;
        }
    }
}
