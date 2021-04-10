package com.adobe.xmp.impl;

import X.AnonymousClass006;
import com.adobe.xmp.XMPIterator;
import com.adobe.xmp.options.IteratorOptions;
import com.adobe.xmp.options.PropertyOptions;
import com.adobe.xmp.properties.XMPPropertyInfo;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class XMPIteratorImpl implements XMPIterator {
    public String baseNS = null;
    public Iterator nodeIterator = null;
    public IteratorOptions options;
    public boolean skipSiblings = false;
    public boolean skipSubtree = false;

    public class NodeIterator implements Iterator {
        public static final int ITERATE_CHILDREN = 1;
        public static final int ITERATE_NODE = 0;
        public static final int ITERATE_QUALIFIER = 2;
        public Iterator childrenIterator = null;
        public int index = 0;
        public String path;
        public XMPPropertyInfo returnProperty = null;
        public int state = 0;
        public Iterator subIterator = Collections.EMPTY_LIST.iterator();
        public XMPNode visitedNode;

        public XMPPropertyInfo createPropertyInfo(final XMPNode xMPNode, final String str, final String str2) {
            final String str3;
            if (xMPNode.getOptions().isSchemaNode()) {
                str3 = null;
            } else {
                str3 = xMPNode.value;
            }
            return new XMPPropertyInfo() {
                /* class com.adobe.xmp.impl.XMPIteratorImpl.NodeIterator.AnonymousClass1 */

                @Override // com.adobe.xmp.properties.XMPProperty
                public String getLanguage() {
                    return null;
                }

                @Override // com.adobe.xmp.properties.XMPPropertyInfo, com.adobe.xmp.properties.XMPProperty
                public PropertyOptions getOptions() {
                    return xMPNode.getOptions();
                }

                @Override // com.adobe.xmp.properties.XMPPropertyInfo
                public String getNamespace() {
                    return str;
                }

                @Override // com.adobe.xmp.properties.XMPPropertyInfo
                public String getPath() {
                    return str2;
                }

                @Override // com.adobe.xmp.properties.XMPPropertyInfo, com.adobe.xmp.properties.XMPProperty
                public Object getValue() {
                    return str3;
                }
            };
        }

        public boolean reportNode() {
            this.state = 1;
            XMPNode xMPNode = this.visitedNode;
            if (xMPNode.parent == null || (XMPIteratorImpl.this.options.getOption(512) && xMPNode.hasChildren())) {
                return hasNext();
            }
            this.returnProperty = createPropertyInfo(this.visitedNode, XMPIteratorImpl.this.baseNS, this.path);
            return true;
        }

        private boolean iterateChildren(Iterator it) {
            XMPIteratorImpl xMPIteratorImpl = XMPIteratorImpl.this;
            if (xMPIteratorImpl.skipSiblings) {
                xMPIteratorImpl.skipSiblings = false;
                this.subIterator = Collections.EMPTY_LIST.iterator();
            }
            if (!this.subIterator.hasNext() && it.hasNext()) {
                int i = this.index + 1;
                this.index = i;
                this.subIterator = new NodeIterator((XMPNode) it.next(), this.path, i);
            }
            if (!this.subIterator.hasNext()) {
                return false;
            }
            this.returnProperty = (XMPPropertyInfo) this.subIterator.next();
            return true;
        }

        public String accumulatePath(XMPNode xMPNode, String str, int i) {
            String str2;
            String str3;
            XMPNode xMPNode2 = xMPNode.parent;
            if (xMPNode2 == null || xMPNode.getOptions().isSchemaNode()) {
                return null;
            }
            if (xMPNode2.getOptions().getOption(512)) {
                str2 = AnonymousClass006.A09("[", String.valueOf(i), "]");
                str3 = "";
            } else {
                str2 = xMPNode.name;
                str3 = "/";
            }
            if (str == null || str.length() == 0) {
                return str2;
            }
            if (!XMPIteratorImpl.this.options.getOption(1024)) {
                return AnonymousClass006.A09(str, str3, str2);
            }
            if (str2.startsWith("?")) {
                return str2.substring(1);
            }
            return str2;
        }

        public boolean hasNext() {
            if (this.returnProperty != null) {
                return true;
            }
            int i = this.state;
            if (i == 0) {
                return reportNode();
            }
            if (i == 1) {
                Iterator it = this.childrenIterator;
                if (it == null) {
                    it = this.visitedNode.iterateChildren();
                    this.childrenIterator = it;
                }
                boolean iterateChildren = iterateChildren(it);
                if (iterateChildren || !this.visitedNode.hasQualifier() || XMPIteratorImpl.this.options.getOption(4096)) {
                    return iterateChildren;
                }
                this.state = 2;
                this.childrenIterator = null;
                return hasNext();
            }
            Iterator it2 = this.childrenIterator;
            if (it2 == null) {
                it2 = this.visitedNode.iterateQualifier();
                this.childrenIterator = it2;
            }
            return iterateChildren(it2);
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Iterator getChildrenIterator() {
            return this.childrenIterator;
        }

        public XMPPropertyInfo getReturnProperty() {
            return this.returnProperty;
        }

        @Override // java.util.Iterator
        public Object next() {
            if (hasNext()) {
                XMPPropertyInfo xMPPropertyInfo = this.returnProperty;
                this.returnProperty = null;
                return xMPPropertyInfo;
            }
            throw new NoSuchElementException("There are no more nodes to return");
        }

        public void setChildrenIterator(Iterator it) {
            this.childrenIterator = it;
        }

        public void setReturnProperty(XMPPropertyInfo xMPPropertyInfo) {
            this.returnProperty = xMPPropertyInfo;
        }

        public NodeIterator() {
        }

        public NodeIterator(XMPNode xMPNode, String str, int i) {
            this.visitedNode = xMPNode;
            this.state = 0;
            if (xMPNode.getOptions().isSchemaNode()) {
                XMPIteratorImpl.this.baseNS = xMPNode.name;
            }
            this.path = accumulatePath(xMPNode, str, i);
        }
    }

    public class NodeIteratorChildren extends NodeIterator {
        public Iterator childrenIterator;
        public int index = 0;
        public String parentPath;

        public NodeIteratorChildren(XMPNode xMPNode, String str) {
            super();
            if (xMPNode.getOptions().isSchemaNode()) {
                XMPIteratorImpl.this.baseNS = xMPNode.name;
            }
            this.parentPath = accumulatePath(xMPNode, str, 1);
            this.childrenIterator = xMPNode.iterateChildren();
        }

        @Override // com.adobe.xmp.impl.XMPIteratorImpl.NodeIterator
        public boolean hasNext() {
            if (this.returnProperty != null) {
                return true;
            }
            if (XMPIteratorImpl.this.skipSiblings || !this.childrenIterator.hasNext()) {
                return false;
            }
            XMPNode xMPNode = (XMPNode) this.childrenIterator.next();
            int i = this.index + 1;
            this.index = i;
            String str = null;
            if (xMPNode.getOptions().isSchemaNode()) {
                XMPIteratorImpl.this.baseNS = xMPNode.name;
            } else if (xMPNode.parent != null) {
                str = accumulatePath(xMPNode, this.parentPath, i);
            }
            if (XMPIteratorImpl.this.options.getOption(512) && xMPNode.hasChildren()) {
                return hasNext();
            }
            this.returnProperty = createPropertyInfo(xMPNode, XMPIteratorImpl.this.baseNS, str);
            return true;
        }
    }

    @Override // com.adobe.xmp.XMPIterator
    public void skipSubtree() {
        this.skipSubtree = true;
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

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0028, code lost:
        if (r10.length() <= 0) goto L_0x002a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001e, code lost:
        if (r9.length() <= 0) goto L_0x0020;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public XMPIteratorImpl(com.adobe.xmp.impl.XMPMetaImpl r8, java.lang.String r9, java.lang.String r10, com.adobe.xmp.options.IteratorOptions r11) throws com.adobe.xmp.XMPException {
        /*
        // Method dump skipped, instructions count: 143
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.xmp.impl.XMPIteratorImpl.<init>(com.adobe.xmp.impl.XMPMetaImpl, java.lang.String, java.lang.String, com.adobe.xmp.options.IteratorOptions):void");
    }

    public String getBaseNS() {
        return this.baseNS;
    }

    public IteratorOptions getOptions() {
        return this.options;
    }

    @Override // com.adobe.xmp.XMPIterator
    public void skipSiblings() {
        skipSubtree();
        this.skipSiblings = true;
    }

    public void setBaseNS(String str) {
        this.baseNS = str;
    }
}
