package org.xml.sax.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.xml.XMLConstants;

public class NamespaceSupport {
    private static final Enumeration EMPTY_ENUMERATION = Collections.enumeration(Collections.emptyList());
    public static final String NSDECL = "http://www.w3.org/xmlns/2000/";
    public static final String XMLNS = "http://www.w3.org/XML/1998/namespace";
    private int contextPos;
    private Context[] contexts;
    private Context currentContext;
    private boolean namespaceDeclUris;

    public NamespaceSupport() {
        reset();
    }

    public void reset() {
        this.contexts = new Context[32];
        this.namespaceDeclUris = false;
        this.contextPos = 0;
        Context[] contextArr = this.contexts;
        int i = this.contextPos;
        Context context = new Context();
        this.currentContext = context;
        contextArr[i] = context;
        this.currentContext.declarePrefix(XMLConstants.XML_NS_PREFIX, "http://www.w3.org/XML/1998/namespace");
    }

    public void pushContext() {
        Context[] contextArr = this.contexts;
        int max = contextArr.length;
        int i = this.contextPos;
        contextArr[i].declsOK = false;
        this.contextPos = i + 1;
        if (this.contextPos >= max) {
            Context[] newContexts = new Context[(max * 2)];
            System.arraycopy(contextArr, 0, newContexts, 0, max);
            int max2 = max * 2;
            this.contexts = newContexts;
        }
        Context[] contextArr2 = this.contexts;
        int i2 = this.contextPos;
        this.currentContext = contextArr2[i2];
        if (this.currentContext == null) {
            Context context = new Context();
            this.currentContext = context;
            contextArr2[i2] = context;
        }
        int i3 = this.contextPos;
        if (i3 > 0) {
            this.currentContext.setParent(this.contexts[i3 - 1]);
        }
    }

    public void popContext() {
        this.contexts[this.contextPos].clear();
        this.contextPos--;
        int i = this.contextPos;
        if (i >= 0) {
            this.currentContext = this.contexts[i];
            return;
        }
        throw new EmptyStackException();
    }

    public boolean declarePrefix(String prefix, String uri) {
        if (prefix.equals(XMLConstants.XML_NS_PREFIX) || prefix.equals(XMLConstants.XMLNS_ATTRIBUTE)) {
            return false;
        }
        this.currentContext.declarePrefix(prefix, uri);
        return true;
    }

    public String[] processName(String qName, String[] parts, boolean isAttribute) {
        String[] myParts = this.currentContext.processName(qName, isAttribute);
        if (myParts == null) {
            return null;
        }
        parts[0] = myParts[0];
        parts[1] = myParts[1];
        parts[2] = myParts[2];
        return parts;
    }

    public String getURI(String prefix) {
        return this.currentContext.getURI(prefix);
    }

    public Enumeration getPrefixes() {
        return this.currentContext.getPrefixes();
    }

    public String getPrefix(String uri) {
        return this.currentContext.getPrefix(uri);
    }

    public Enumeration getPrefixes(String uri) {
        ArrayList<String> prefixes = new ArrayList<>();
        Enumeration allPrefixes = getPrefixes();
        while (allPrefixes.hasMoreElements()) {
            String prefix = (String) allPrefixes.nextElement();
            if (uri.equals(getURI(prefix))) {
                prefixes.add(prefix);
            }
        }
        return Collections.enumeration(prefixes);
    }

    public Enumeration getDeclaredPrefixes() {
        return this.currentContext.getDeclaredPrefixes();
    }

    public void setNamespaceDeclUris(boolean value) {
        int i = this.contextPos;
        if (i != 0) {
            throw new IllegalStateException();
        } else if (value != this.namespaceDeclUris) {
            this.namespaceDeclUris = value;
            if (value) {
                this.currentContext.declarePrefix(XMLConstants.XMLNS_ATTRIBUTE, NSDECL);
                return;
            }
            Context[] contextArr = this.contexts;
            Context context = new Context();
            this.currentContext = context;
            contextArr[i] = context;
            this.currentContext.declarePrefix(XMLConstants.XML_NS_PREFIX, "http://www.w3.org/XML/1998/namespace");
        }
    }

    public boolean isNamespaceDeclUris() {
        return this.namespaceDeclUris;
    }

    /* access modifiers changed from: package-private */
    public final class Context {
        Hashtable attributeNameTable;
        private boolean declSeen = false;
        private ArrayList<String> declarations = null;
        boolean declsOK = true;
        String defaultNS = null;
        Hashtable elementNameTable;
        private Context parent = null;
        Hashtable prefixTable;
        Hashtable uriTable;

        Context() {
            copyTables();
        }

        /* access modifiers changed from: package-private */
        public void setParent(Context parent2) {
            this.parent = parent2;
            this.declarations = null;
            this.prefixTable = parent2.prefixTable;
            this.uriTable = parent2.uriTable;
            this.elementNameTable = parent2.elementNameTable;
            this.attributeNameTable = parent2.attributeNameTable;
            this.defaultNS = parent2.defaultNS;
            this.declSeen = false;
            this.declsOK = true;
        }

        /* access modifiers changed from: package-private */
        public void clear() {
            this.parent = null;
            this.prefixTable = null;
            this.uriTable = null;
            this.elementNameTable = null;
            this.attributeNameTable = null;
            this.defaultNS = null;
        }

        /* access modifiers changed from: package-private */
        public void declarePrefix(String prefix, String uri) {
            if (this.declsOK) {
                if (!this.declSeen) {
                    copyTables();
                }
                if (this.declarations == null) {
                    this.declarations = new ArrayList<>();
                }
                String prefix2 = prefix.intern();
                String uri2 = uri.intern();
                if (!"".equals(prefix2)) {
                    this.prefixTable.put(prefix2, uri2);
                    this.uriTable.put(uri2, prefix2);
                } else if ("".equals(uri2)) {
                    this.defaultNS = null;
                } else {
                    this.defaultNS = uri2;
                }
                this.declarations.add(prefix2);
                return;
            }
            throw new IllegalStateException("can't declare any more prefixes in this context");
        }

        /* access modifiers changed from: package-private */
        public String[] processName(String qName, boolean isAttribute) {
            Hashtable table;
            String uri;
            this.declsOK = false;
            if (isAttribute) {
                table = this.attributeNameTable;
            } else {
                table = this.elementNameTable;
            }
            String[] name = (String[]) table.get(qName);
            if (name != null) {
                return name;
            }
            String[] name2 = new String[3];
            name2[2] = qName.intern();
            int index = qName.indexOf(58);
            if (index == -1) {
                if (!isAttribute) {
                    String str = this.defaultNS;
                    if (str == null) {
                        name2[0] = "";
                    } else {
                        name2[0] = str;
                    }
                } else if (qName != XMLConstants.XMLNS_ATTRIBUTE || !NamespaceSupport.this.namespaceDeclUris) {
                    name2[0] = "";
                } else {
                    name2[0] = NamespaceSupport.NSDECL;
                }
                name2[1] = name2[2];
            } else {
                String prefix = qName.substring(0, index);
                String local = qName.substring(index + 1);
                if ("".equals(prefix)) {
                    uri = this.defaultNS;
                } else {
                    uri = (String) this.prefixTable.get(prefix);
                }
                if (uri == null) {
                    return null;
                }
                if (!isAttribute && XMLConstants.XMLNS_ATTRIBUTE.equals(prefix)) {
                    return null;
                }
                name2[0] = uri;
                name2[1] = local.intern();
            }
            table.put(name2[2], name2);
            return name2;
        }

        /* access modifiers changed from: package-private */
        public String getURI(String prefix) {
            if ("".equals(prefix)) {
                return this.defaultNS;
            }
            Hashtable hashtable = this.prefixTable;
            if (hashtable == null) {
                return null;
            }
            return (String) hashtable.get(prefix);
        }

        /* access modifiers changed from: package-private */
        public String getPrefix(String uri) {
            Hashtable hashtable = this.uriTable;
            if (hashtable == null) {
                return null;
            }
            return (String) hashtable.get(uri);
        }

        /* access modifiers changed from: package-private */
        public Enumeration getDeclaredPrefixes() {
            ArrayList<String> arrayList = this.declarations;
            return arrayList == null ? NamespaceSupport.EMPTY_ENUMERATION : Collections.enumeration(arrayList);
        }

        /* access modifiers changed from: package-private */
        public Enumeration getPrefixes() {
            Hashtable hashtable = this.prefixTable;
            if (hashtable == null) {
                return NamespaceSupport.EMPTY_ENUMERATION;
            }
            return hashtable.keys();
        }

        private void copyTables() {
            Hashtable hashtable = this.prefixTable;
            if (hashtable != null) {
                this.prefixTable = (Hashtable) hashtable.clone();
            } else {
                this.prefixTable = new Hashtable();
            }
            Hashtable hashtable2 = this.uriTable;
            if (hashtable2 != null) {
                this.uriTable = (Hashtable) hashtable2.clone();
            } else {
                this.uriTable = new Hashtable();
            }
            this.elementNameTable = new Hashtable();
            this.attributeNameTable = new Hashtable();
            this.declSeen = true;
        }
    }
}
