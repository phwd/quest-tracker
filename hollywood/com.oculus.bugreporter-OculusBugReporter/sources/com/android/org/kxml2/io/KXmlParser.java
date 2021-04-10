package com.android.org.kxml2.io;

import android.icu.impl.PatternTokenizer;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.os.EnvironmentCompat;
import android.support.v4.view.InputDeviceCompat;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import javax.xml.XMLConstants;
import javax.xml.transform.OutputKeys;
import libcore.internal.StringPool;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class KXmlParser implements XmlPullParser, Closeable {
    private static final char[] ANY = {'A', 'N', 'Y'};
    private static final int ATTLISTDECL = 13;
    private static final char[] COMMENT_DOUBLE_DASH = {'-', '-'};
    private static final Map<String, String> DEFAULT_ENTITIES = new HashMap();
    private static final char[] DOUBLE_QUOTE = {'\"'};
    private static final int ELEMENTDECL = 11;
    private static final char[] EMPTY = {'E', 'M', 'P', 'T', 'Y'};
    private static final char[] END_CDATA = {']', ']', '>'};
    private static final char[] END_COMMENT = {'-', '-', '>'};
    private static final char[] END_PROCESSING_INSTRUCTION = {'?', '>'};
    private static final int ENTITYDECL = 12;
    private static final String FEATURE_RELAXED = "http://xmlpull.org/v1/doc/features.html#relaxed";
    private static final char[] FIXED = {'F', 'I', 'X', 'E', 'D'};
    private static final String ILLEGAL_TYPE = "Wrong event type";
    private static final char[] IMPLIED = {'I', 'M', 'P', 'L', 'I', 'E', 'D'};
    private static final char[] NDATA = {'N', 'D', 'A', 'T', 'A'};
    private static final char[] NOTATION = {'N', 'O', 'T', 'A', 'T', 'I', 'O', 'N'};
    private static final int NOTATIONDECL = 14;
    private static final int PARAMETER_ENTITY_REF = 15;
    private static final String PROPERTY_LOCATION = "http://xmlpull.org/v1/doc/properties.html#location";
    private static final String PROPERTY_XMLDECL_STANDALONE = "http://xmlpull.org/v1/doc/properties.html#xmldecl-standalone";
    private static final String PROPERTY_XMLDECL_VERSION = "http://xmlpull.org/v1/doc/properties.html#xmldecl-version";
    private static final char[] PUBLIC = {'P', 'U', 'B', 'L', 'I', 'C'};
    private static final char[] REQUIRED = {'R', 'E', 'Q', 'U', 'I', 'R', 'E', 'D'};
    private static final char[] SINGLE_QUOTE = {PatternTokenizer.SINGLE_QUOTE};
    private static final char[] START_ATTLIST = {'<', '!', 'A', 'T', 'T', 'L', 'I', 'S', 'T'};
    private static final char[] START_CDATA = {'<', '!', '[', 'C', 'D', 'A', 'T', 'A', '['};
    private static final char[] START_COMMENT = {'<', '!', '-', '-'};
    private static final char[] START_DOCTYPE = {'<', '!', 'D', 'O', 'C', 'T', 'Y', 'P', 'E'};
    private static final char[] START_ELEMENT = {'<', '!', 'E', 'L', 'E', 'M', 'E', 'N', 'T'};
    private static final char[] START_ENTITY = {'<', '!', 'E', 'N', 'T', 'I', 'T', 'Y'};
    private static final char[] START_NOTATION = {'<', '!', 'N', 'O', 'T', 'A', 'T', 'I', 'O', 'N'};
    private static final char[] START_PROCESSING_INSTRUCTION = {'<', '?'};
    private static final char[] SYSTEM = {'S', 'Y', 'S', 'T', 'E', 'M'};
    private static final String UNEXPECTED_EOF = "Unexpected EOF";
    private static final int XML_DECLARATION = 998;
    private int attributeCount;
    private String[] attributes = new String[16];
    private char[] buffer = new char[8192];
    private StringBuilder bufferCapture;
    private int bufferStartColumn;
    private int bufferStartLine;
    private Map<String, Map<String, String>> defaultAttributes;
    private boolean degenerated;
    private int depth;
    private Map<String, char[]> documentEntities;
    private String[] elementStack = new String[16];
    private String encoding;
    private String error;
    private boolean isWhitespace;
    private boolean keepNamespaceAttributes;
    private int limit = 0;
    private String location;
    private String name;
    private String namespace;
    private ContentSource nextContentSource;
    private int[] nspCounts = new int[4];
    private String[] nspStack = new String[8];
    private boolean parsedTopLevelStartTag;
    private int position = 0;
    private String prefix;
    private boolean processDocDecl;
    private boolean processNsp;
    private String publicId;
    private Reader reader;
    private boolean relaxed;
    private String rootElementName;
    private Boolean standalone;
    public final StringPool stringPool = new StringPool();
    private String systemId;
    private String text;
    private int type;
    private boolean unresolved;
    private String version;

    /* access modifiers changed from: package-private */
    public enum ValueContext {
        ATTRIBUTE,
        TEXT,
        ENTITY_DECLARATION
    }

    static {
        DEFAULT_ENTITIES.put("lt", "<");
        DEFAULT_ENTITIES.put("gt", ">");
        DEFAULT_ENTITIES.put("amp", "&");
        DEFAULT_ENTITIES.put("apos", "'");
        DEFAULT_ENTITIES.put("quot", "\"");
    }

    public void keepNamespaceAttributes() {
        this.keepNamespaceAttributes = true;
    }

    private boolean adjustNsp() throws XmlPullParserException {
        int i;
        String prefix2;
        String attrName;
        boolean any = false;
        int i2 = 0;
        while (true) {
            i = this.attributeCount;
            if (i2 >= (i << 2)) {
                break;
            }
            String attrName2 = this.attributes[i2 + 2];
            int cut = attrName2.indexOf(58);
            if (cut != -1) {
                prefix2 = attrName2.substring(0, cut);
                attrName = attrName2.substring(cut + 1);
            } else if (attrName2.equals(XMLConstants.XMLNS_ATTRIBUTE)) {
                prefix2 = attrName2;
                attrName = null;
            } else {
                i2 += 4;
            }
            if (!prefix2.equals(XMLConstants.XMLNS_ATTRIBUTE)) {
                any = true;
            } else {
                int[] iArr = this.nspCounts;
                int i3 = this.depth;
                int i4 = iArr[i3];
                iArr[i3] = i4 + 1;
                int j = i4 << 1;
                this.nspStack = ensureCapacity(this.nspStack, j + 2);
                String[] strArr = this.nspStack;
                strArr[j] = attrName;
                String[] strArr2 = this.attributes;
                strArr[j + 1] = strArr2[i2 + 3];
                if (attrName != null && strArr2[i2 + 3].isEmpty()) {
                    checkRelaxed("illegal empty namespace");
                }
                if (this.keepNamespaceAttributes) {
                    this.attributes[i2] = XMLConstants.XMLNS_ATTRIBUTE_NS_URI;
                    any = true;
                } else {
                    String[] strArr3 = this.attributes;
                    int i5 = this.attributeCount - 1;
                    this.attributeCount = i5;
                    System.arraycopy(strArr3, i2 + 4, strArr3, i2, (i5 << 2) - i2);
                    i2 -= 4;
                }
            }
            i2 += 4;
        }
        if (any) {
            for (int i6 = (i << 2) - 4; i6 >= 0; i6 -= 4) {
                String attrName3 = this.attributes[i6 + 2];
                int cut2 = attrName3.indexOf(58);
                if (cut2 != 0 || this.relaxed) {
                    if (cut2 != -1) {
                        String attrPrefix = attrName3.substring(0, cut2);
                        String attrName4 = attrName3.substring(cut2 + 1);
                        String attrNs = getNamespace(attrPrefix);
                        if (attrNs != null || this.relaxed) {
                            String[] strArr4 = this.attributes;
                            strArr4[i6] = attrNs;
                            strArr4[i6 + 1] = attrPrefix;
                            strArr4[i6 + 2] = attrName4;
                        } else {
                            throw new RuntimeException("Undefined Prefix: " + attrPrefix + " in " + ((Object) this));
                        }
                    }
                } else {
                    throw new RuntimeException("illegal attribute name: " + attrName3 + " at " + ((Object) this));
                }
            }
        }
        int cut3 = this.name.indexOf(58);
        if (cut3 == 0) {
            checkRelaxed("illegal tag name: " + this.name);
        }
        if (cut3 != -1) {
            this.prefix = this.name.substring(0, cut3);
            this.name = this.name.substring(cut3 + 1);
        }
        this.namespace = getNamespace(this.prefix);
        if (this.namespace == null) {
            if (this.prefix != null) {
                checkRelaxed("undefined prefix: " + this.prefix);
            }
            this.namespace = "";
        }
        return any;
    }

    private String[] ensureCapacity(String[] arr, int required) {
        if (arr.length >= required) {
            return arr;
        }
        String[] bigger = new String[(required + 16)];
        System.arraycopy(arr, 0, bigger, 0, arr.length);
        return bigger;
    }

    private void checkRelaxed(String errorMessage) throws XmlPullParserException {
        if (!this.relaxed) {
            throw new XmlPullParserException(errorMessage, this, null);
        } else if (this.error == null) {
            this.error = "Error: " + errorMessage;
        }
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int next() throws XmlPullParserException, IOException {
        return next(false);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int nextToken() throws XmlPullParserException, IOException {
        return next(true);
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    private int next(boolean justOneToken) throws IOException, XmlPullParserException {
        int i;
        if (this.reader != null) {
            if (this.type == 3) {
                this.depth--;
            }
            if (this.degenerated) {
                this.degenerated = false;
                this.type = 3;
                return this.type;
            }
            String str = this.error;
            if (str != null) {
                if (justOneToken) {
                    this.text = str;
                    this.type = 9;
                    this.error = null;
                    return this.type;
                }
                this.error = null;
            }
            this.type = peekType(false);
            if (this.type == XML_DECLARATION) {
                readXmlDeclaration();
                this.type = peekType(false);
            }
            this.text = null;
            this.isWhitespace = true;
            this.prefix = null;
            this.name = null;
            this.namespace = null;
            this.attributeCount = -1;
            boolean throwOnResolveFailure = !justOneToken;
            while (true) {
                int i2 = this.type;
                switch (i2) {
                    case 1:
                        return i2;
                    case 2:
                        parseStartTag(false, throwOnResolveFailure);
                        return this.type;
                    case 3:
                        readEndTag();
                        return this.type;
                    case 4:
                        this.text = readValue('<', !justOneToken, throwOnResolveFailure, ValueContext.TEXT);
                        if (this.depth == 0 && this.isWhitespace) {
                            this.type = 7;
                            break;
                        }
                    case 5:
                        read(START_CDATA);
                        this.text = readUntil(END_CDATA, true);
                        break;
                    case 6:
                        if (justOneToken) {
                            StringBuilder entityTextBuilder = new StringBuilder();
                            readEntity(entityTextBuilder, true, throwOnResolveFailure, ValueContext.TEXT);
                            this.text = entityTextBuilder.toString();
                            break;
                        }
                        this.text = readValue('<', !justOneToken, throwOnResolveFailure, ValueContext.TEXT);
                        this.type = 7;
                        break;
                    case 7:
                    default:
                        throw new XmlPullParserException("Unexpected token", this, null);
                    case 8:
                        read(START_PROCESSING_INSTRUCTION);
                        String processingInstruction = readUntil(END_PROCESSING_INSTRUCTION, justOneToken);
                        if (justOneToken) {
                            this.text = processingInstruction;
                            break;
                        }
                        break;
                    case 9:
                        String commentText = readComment(justOneToken);
                        if (justOneToken) {
                            this.text = commentText;
                            break;
                        }
                        break;
                    case 10:
                        readDoctype(justOneToken);
                        if (this.parsedTopLevelStartTag) {
                            throw new XmlPullParserException("Unexpected token", this, null);
                        }
                        break;
                }
                if (!(this.depth == 0 && ((i = this.type) == 6 || i == 4 || i == 5))) {
                    if (justOneToken) {
                        return this.type;
                    }
                    if (this.type == 7) {
                        this.text = null;
                    }
                    int peek = peekType(false);
                    String str2 = this.text;
                    if (str2 == null || str2.isEmpty() || peek >= 4) {
                        this.type = peek;
                    } else {
                        this.type = 4;
                        return this.type;
                    }
                }
            }
            throw new XmlPullParserException("Unexpected token", this, null);
        }
        throw new XmlPullParserException("setInput() must be called first.", this, null);
    }

    private String readUntil(char[] delimiter, boolean returnText) throws IOException, XmlPullParserException {
        int start = this.position;
        StringBuilder result = null;
        if (returnText && this.text != null) {
            result = new StringBuilder();
            result.append(this.text);
        }
        while (true) {
            int i = this.position;
            if (delimiter.length + i > this.limit) {
                if (start < i && returnText) {
                    if (result == null) {
                        result = new StringBuilder();
                    }
                    result.append(this.buffer, start, this.position - start);
                }
                if (!fillBuffer(delimiter.length)) {
                    checkRelaxed(UNEXPECTED_EOF);
                    this.type = 9;
                    return null;
                }
                start = this.position;
            }
            for (int i2 = 0; i2 < delimiter.length; i2++) {
                char[] cArr = this.buffer;
                int i3 = this.position;
                if (cArr[i3 + i2] != delimiter[i2]) {
                    this.position = i3 + 1;
                }
            }
            int end = this.position;
            this.position += delimiter.length;
            if (!returnText) {
                return null;
            }
            if (result == null) {
                return this.stringPool.get(this.buffer, start, end - start);
            }
            result.append(this.buffer, start, end - start);
            return result.toString();
        }
    }

    private void readXmlDeclaration() throws IOException, XmlPullParserException {
        if (!(this.bufferStartLine == 0 && this.bufferStartColumn == 0 && this.position == 0)) {
            checkRelaxed("processing instructions must not start with xml");
        }
        read(START_PROCESSING_INSTRUCTION);
        parseStartTag(true, true);
        if (this.attributeCount < 1 || !"version".equals(this.attributes[2])) {
            checkRelaxed("version expected");
        }
        String[] strArr = this.attributes;
        this.version = strArr[3];
        int pos = 1;
        if (1 < this.attributeCount && OutputKeys.ENCODING.equals(strArr[6])) {
            this.encoding = this.attributes[7];
            pos = 1 + 1;
        }
        if (pos < this.attributeCount && OutputKeys.STANDALONE.equals(this.attributes[(pos * 4) + 2])) {
            String st = this.attributes[(pos * 4) + 3];
            if ("yes".equals(st)) {
                this.standalone = Boolean.TRUE;
            } else if ("no".equals(st)) {
                this.standalone = Boolean.FALSE;
            } else {
                checkRelaxed("illegal standalone value: " + st);
            }
            pos++;
        }
        if (pos != this.attributeCount) {
            checkRelaxed("unexpected attributes in XML declaration");
        }
        this.isWhitespace = true;
        this.text = null;
    }

    private String readComment(boolean returnText) throws IOException, XmlPullParserException {
        read(START_COMMENT);
        if (this.relaxed) {
            return readUntil(END_COMMENT, returnText);
        }
        String commentText = readUntil(COMMENT_DOUBLE_DASH, returnText);
        if (peekCharacter() == 62) {
            this.position++;
            return commentText;
        }
        throw new XmlPullParserException("Comments may not contain --", this, null);
    }

    private void readDoctype(boolean saveDtdText) throws IOException, XmlPullParserException {
        read(START_DOCTYPE);
        int startPosition = -1;
        if (saveDtdText) {
            this.bufferCapture = new StringBuilder();
            startPosition = this.position;
        }
        try {
            skip();
            this.rootElementName = readName();
            readExternalId(true, true);
            skip();
            if (peekCharacter() == 91) {
                readInternalSubset();
            }
            skip();
            read('>');
            skip();
        } finally {
            if (saveDtdText) {
                this.bufferCapture.append(this.buffer, 0, this.position);
                this.bufferCapture.delete(0, startPosition);
                this.text = this.bufferCapture.toString();
                this.bufferCapture = null;
            }
        }
    }

    private boolean readExternalId(boolean requireSystemName, boolean assignFields) throws IOException, XmlPullParserException {
        int delimiter;
        skip();
        int c = peekCharacter();
        if (c == 83) {
            read(SYSTEM);
        } else if (c != 80) {
            return false;
        } else {
            read(PUBLIC);
            skip();
            if (assignFields) {
                this.publicId = readQuotedId(true);
            } else {
                readQuotedId(false);
            }
        }
        skip();
        if (!requireSystemName && (delimiter = peekCharacter()) != 34 && delimiter != 39) {
            return true;
        }
        if (assignFields) {
            this.systemId = readQuotedId(true);
        } else {
            readQuotedId(false);
        }
        return true;
    }

    private String readQuotedId(boolean returnText) throws IOException, XmlPullParserException {
        char[] delimiter;
        int quote = peekCharacter();
        if (quote == 34) {
            delimiter = DOUBLE_QUOTE;
        } else if (quote == 39) {
            delimiter = SINGLE_QUOTE;
        } else {
            throw new XmlPullParserException("Expected a quoted string", this, null);
        }
        this.position++;
        return readUntil(delimiter, returnText);
    }

    private void readInternalSubset() throws IOException, XmlPullParserException {
        read('[');
        while (true) {
            skip();
            if (peekCharacter() == 93) {
                this.position++;
                return;
            }
            switch (peekType(true)) {
                case 8:
                    read(START_PROCESSING_INSTRUCTION);
                    readUntil(END_PROCESSING_INSTRUCTION, false);
                    break;
                case 9:
                    readComment(false);
                    break;
                case 10:
                default:
                    throw new XmlPullParserException("Unexpected token", this, null);
                case 11:
                    readElementDeclaration();
                    break;
                case 12:
                    readEntityDeclaration();
                    break;
                case 13:
                    readAttributeListDeclaration();
                    break;
                case 14:
                    readNotationDeclaration();
                    break;
                case 15:
                    throw new XmlPullParserException("Parameter entity references are not supported", this, null);
            }
        }
    }

    private void readElementDeclaration() throws IOException, XmlPullParserException {
        read(START_ELEMENT);
        skip();
        readName();
        readContentSpec();
        skip();
        read('>');
    }

    private void readContentSpec() throws IOException, XmlPullParserException {
        skip();
        int c = peekCharacter();
        if (c == 40) {
            int depth2 = 0;
            do {
                if (c == 40) {
                    depth2++;
                } else if (c == 41) {
                    depth2--;
                } else if (c == -1) {
                    throw new XmlPullParserException("Unterminated element content spec", this, null);
                }
                this.position++;
                c = peekCharacter();
            } while (depth2 > 0);
            if (c == 42 || c == 63 || c == 43) {
                this.position++;
                return;
            }
            return;
        }
        char[] cArr = EMPTY;
        if (c == cArr[0]) {
            read(cArr);
            return;
        }
        char[] cArr2 = ANY;
        if (c == cArr2[0]) {
            read(cArr2);
            return;
        }
        throw new XmlPullParserException("Expected element content spec", this, null);
    }

    private void readAttributeListDeclaration() throws IOException, XmlPullParserException {
        read(START_ATTLIST);
        skip();
        String elementName = readName();
        while (true) {
            skip();
            if (peekCharacter() == 62) {
                this.position++;
                return;
            }
            String attributeName = readName();
            skip();
            if (this.position + 1 < this.limit || fillBuffer(2)) {
                char[] cArr = this.buffer;
                int i = this.position;
                char c = cArr[i];
                char[] cArr2 = NOTATION;
                if (c == cArr2[0] && cArr[i + 1] == cArr2[1]) {
                    read(cArr2);
                    skip();
                }
                if (peekCharacter() == 40) {
                    this.position++;
                    while (true) {
                        skip();
                        readName();
                        skip();
                        int c2 = peekCharacter();
                        if (c2 == 41) {
                            this.position++;
                            break;
                        } else if (c2 == 124) {
                            this.position++;
                        } else {
                            throw new XmlPullParserException("Malformed attribute type", this, null);
                        }
                    }
                } else {
                    readName();
                }
                skip();
                int c3 = peekCharacter();
                if (c3 == 35) {
                    this.position++;
                    int c4 = peekCharacter();
                    if (c4 == 82) {
                        read(REQUIRED);
                    } else if (c4 == 73) {
                        read(IMPLIED);
                    } else if (c4 == 70) {
                        read(FIXED);
                    } else {
                        throw new XmlPullParserException("Malformed attribute type", this, null);
                    }
                    skip();
                    c3 = peekCharacter();
                }
                if (c3 == 34 || c3 == 39) {
                    this.position++;
                    String value = readValue((char) c3, true, true, ValueContext.ATTRIBUTE);
                    if (peekCharacter() == c3) {
                        this.position++;
                    }
                    defineAttributeDefault(elementName, attributeName, value);
                }
            } else {
                throw new XmlPullParserException("Malformed attribute list", this, null);
            }
        }
    }

    private void defineAttributeDefault(String elementName, String attributeName, String value) {
        if (this.defaultAttributes == null) {
            this.defaultAttributes = new HashMap();
        }
        Map<String, String> elementAttributes = this.defaultAttributes.get(elementName);
        if (elementAttributes == null) {
            elementAttributes = new HashMap();
            this.defaultAttributes.put(elementName, elementAttributes);
        }
        elementAttributes.put(attributeName, value);
    }

    private void readEntityDeclaration() throws IOException, XmlPullParserException {
        String entityValue;
        read(START_ENTITY);
        boolean generalEntity = true;
        skip();
        if (peekCharacter() == 37) {
            generalEntity = false;
            this.position++;
            skip();
        }
        String name2 = readName();
        skip();
        int quote = peekCharacter();
        if (quote == 34 || quote == 39) {
            this.position++;
            String entityValue2 = readValue((char) quote, true, false, ValueContext.ENTITY_DECLARATION);
            if (peekCharacter() == quote) {
                this.position++;
            }
            entityValue = entityValue2;
        } else if (readExternalId(true, false)) {
            entityValue = "";
            skip();
            int peekCharacter = peekCharacter();
            char[] cArr = NDATA;
            if (peekCharacter == cArr[0]) {
                read(cArr);
                skip();
                readName();
            }
        } else {
            throw new XmlPullParserException("Expected entity value or external ID", this, null);
        }
        if (generalEntity && this.processDocDecl) {
            if (this.documentEntities == null) {
                this.documentEntities = new HashMap();
            }
            this.documentEntities.put(name2, entityValue.toCharArray());
        }
        skip();
        read('>');
    }

    private void readNotationDeclaration() throws IOException, XmlPullParserException {
        read(START_NOTATION);
        skip();
        readName();
        if (readExternalId(false, false)) {
            skip();
            read('>');
            return;
        }
        throw new XmlPullParserException("Expected external ID or public ID for notation", this, null);
    }

    private void readEndTag() throws IOException, XmlPullParserException {
        read('<');
        read('/');
        this.name = readName();
        skip();
        read('>');
        int i = this.depth;
        int sp = (i - 1) * 4;
        if (i == 0) {
            checkRelaxed("read end tag " + this.name + " with no tags open");
            this.type = 9;
        } else if (this.name.equals(this.elementStack[sp + 3])) {
            String[] strArr = this.elementStack;
            this.namespace = strArr[sp];
            this.prefix = strArr[sp + 1];
            this.name = strArr[sp + 2];
        } else if (!this.relaxed) {
            throw new XmlPullParserException("expected: /" + this.elementStack[sp + 3] + " read: " + this.name, this, null);
        }
    }

    private int peekType(boolean inDeclaration) throws IOException, XmlPullParserException {
        if (this.position >= this.limit && !fillBuffer(1)) {
            return 1;
        }
        char[] cArr = this.buffer;
        int i = this.position;
        char c = cArr[i];
        if (c != '%') {
            if (c == '&') {
                return 6;
            }
            if (c != '<') {
                return 4;
            }
            if (i + 3 < this.limit || fillBuffer(4)) {
                char[] cArr2 = this.buffer;
                int i2 = this.position;
                char c2 = cArr2[i2 + 1];
                if (c2 == '!') {
                    char c3 = cArr2[i2 + 2];
                    if (c3 == '-') {
                        return 9;
                    }
                    if (c3 == 'A') {
                        return 13;
                    }
                    if (c3 == 'N') {
                        return 14;
                    }
                    if (c3 == '[') {
                        return 5;
                    }
                    if (c3 == 'D') {
                        return 10;
                    }
                    if (c3 == 'E') {
                        char c4 = cArr2[i2 + 3];
                        if (c4 == 'L') {
                            return 11;
                        }
                        if (c4 == 'N') {
                            return 12;
                        }
                    }
                    throw new XmlPullParserException("Unexpected <!", this, null);
                } else if (c2 == '/') {
                    return 3;
                } else {
                    if (c2 != '?') {
                        return 2;
                    }
                    if (i2 + 5 >= this.limit && !fillBuffer(6)) {
                        return 8;
                    }
                    char[] cArr3 = this.buffer;
                    int i3 = this.position;
                    if (cArr3[i3 + 2] != 'x' && cArr3[i3 + 2] != 'X') {
                        return 8;
                    }
                    char[] cArr4 = this.buffer;
                    int i4 = this.position;
                    if (cArr4[i4 + 3] != 'm' && cArr4[i4 + 3] != 'M') {
                        return 8;
                    }
                    char[] cArr5 = this.buffer;
                    int i5 = this.position;
                    if ((cArr5[i5 + 4] == 'l' || cArr5[i5 + 4] == 'L') && this.buffer[this.position + 5] == ' ') {
                        return XML_DECLARATION;
                    }
                    return 8;
                }
            } else {
                throw new XmlPullParserException("Dangling <", this, null);
            }
        } else if (inDeclaration) {
            return 15;
        } else {
            return 4;
        }
    }

    private void parseStartTag(boolean xmldecl, boolean throwOnResolveFailure) throws IOException, XmlPullParserException {
        Map<String, String> elementDefaultAttributes;
        if (!xmldecl) {
            read('<');
        }
        this.name = readName();
        this.attributeCount = 0;
        while (true) {
            skip();
            if (this.position < this.limit || fillBuffer(1)) {
                char[] cArr = this.buffer;
                int i = this.position;
                char c = cArr[i];
                if (!xmldecl) {
                    if (c != '/') {
                        if (c == '>') {
                            this.position = i + 1;
                            break;
                        }
                    } else {
                        this.degenerated = true;
                        this.position = i + 1;
                        skip();
                        read('>');
                        break;
                    }
                } else if (c == '?') {
                    this.position = i + 1;
                    read('>');
                    return;
                }
                String attrName = readName();
                int i2 = this.attributeCount;
                this.attributeCount = i2 + 1;
                int i3 = i2 * 4;
                this.attributes = ensureCapacity(this.attributes, i3 + 4);
                String[] strArr = this.attributes;
                strArr[i3] = "";
                strArr[i3 + 1] = null;
                strArr[i3 + 2] = attrName;
                skip();
                if (this.position < this.limit || fillBuffer(1)) {
                    char[] cArr2 = this.buffer;
                    int i4 = this.position;
                    if (cArr2[i4] == '=') {
                        this.position = i4 + 1;
                        skip();
                        if (this.position < this.limit || fillBuffer(1)) {
                            char delimiter = this.buffer[this.position];
                            if (delimiter == '\'' || delimiter == '\"') {
                                this.position++;
                            } else if (this.relaxed) {
                                delimiter = ' ';
                            } else {
                                throw new XmlPullParserException("attr value delimiter missing!", this, null);
                            }
                            this.attributes[i3 + 3] = readValue(delimiter, true, throwOnResolveFailure, ValueContext.ATTRIBUTE);
                            if (delimiter != ' ' && peekCharacter() == delimiter) {
                                this.position++;
                            }
                        } else {
                            checkRelaxed(UNEXPECTED_EOF);
                            return;
                        }
                    } else if (this.relaxed) {
                        this.attributes[i3 + 3] = attrName;
                    } else {
                        checkRelaxed("Attr.value missing f. " + attrName);
                        this.attributes[i3 + 3] = attrName;
                    }
                } else {
                    checkRelaxed(UNEXPECTED_EOF);
                    return;
                }
            } else {
                checkRelaxed(UNEXPECTED_EOF);
                return;
            }
        }
        int c2 = this.depth;
        this.depth = c2 + 1;
        int sp = c2 * 4;
        if (this.depth == 1) {
            this.parsedTopLevelStartTag = true;
        }
        this.elementStack = ensureCapacity(this.elementStack, sp + 4);
        this.elementStack[sp + 3] = this.name;
        int i5 = this.depth;
        int[] iArr = this.nspCounts;
        if (i5 >= iArr.length) {
            int[] bigger = new int[(i5 + 4)];
            System.arraycopy((Object) iArr, 0, (Object) bigger, 0, iArr.length);
            this.nspCounts = bigger;
        }
        int[] iArr2 = this.nspCounts;
        int i6 = this.depth;
        iArr2[i6] = iArr2[i6 - 1];
        if (this.processNsp) {
            adjustNsp();
        } else {
            this.namespace = "";
        }
        Map<String, Map<String, String>> map = this.defaultAttributes;
        if (!(map == null || (elementDefaultAttributes = map.get(this.name)) == null)) {
            for (Map.Entry<String, String> entry : elementDefaultAttributes.entrySet()) {
                if (getAttributeValue(null, entry.getKey()) == null) {
                    int i7 = this.attributeCount;
                    this.attributeCount = i7 + 1;
                    int i8 = i7 * 4;
                    this.attributes = ensureCapacity(this.attributes, i8 + 4);
                    String[] strArr2 = this.attributes;
                    strArr2[i8] = "";
                    strArr2[i8 + 1] = null;
                    strArr2[i8 + 2] = entry.getKey();
                    this.attributes[i8 + 3] = entry.getValue();
                }
            }
        }
        String[] strArr3 = this.elementStack;
        strArr3[sp] = this.namespace;
        strArr3[sp + 1] = this.prefix;
        strArr3[sp + 2] = this.name;
    }

    private void readEntity(StringBuilder out, boolean isEntityToken, boolean throwOnResolveFailure, ValueContext valueContext) throws IOException, XmlPullParserException {
        char[] resolved;
        int c;
        int start = out.length();
        char[] cArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        if (cArr[i] == '&') {
            out.append('&');
            while (true) {
                int c2 = peekCharacter();
                if (c2 == 59) {
                    out.append(';');
                    this.position++;
                    String code = out.substring(start + 1, out.length() - 1);
                    if (isEntityToken) {
                        this.name = code;
                    }
                    if (code.startsWith("#")) {
                        try {
                            if (code.startsWith("#x")) {
                                c = Integer.parseInt(code.substring(2), 16);
                            } else {
                                c = Integer.parseInt(code.substring(1));
                            }
                            out.delete(start, out.length());
                            out.appendCodePoint(c);
                            this.unresolved = false;
                            return;
                        } catch (NumberFormatException e) {
                            throw new XmlPullParserException("Invalid character reference: &" + code);
                        } catch (IllegalArgumentException e2) {
                            throw new XmlPullParserException("Invalid character reference: &" + code);
                        }
                    } else if (valueContext != ValueContext.ENTITY_DECLARATION) {
                        String defaultEntity = DEFAULT_ENTITIES.get(code);
                        if (defaultEntity != null) {
                            out.delete(start, out.length());
                            this.unresolved = false;
                            out.append(defaultEntity);
                            return;
                        }
                        Map<String, char[]> map = this.documentEntities;
                        if (map != null && (resolved = map.get(code)) != null) {
                            out.delete(start, out.length());
                            this.unresolved = false;
                            if (this.processDocDecl) {
                                pushContentSource(resolved);
                                return;
                            } else {
                                out.append(resolved);
                                return;
                            }
                        } else if (this.systemId != null) {
                            out.delete(start, out.length());
                            return;
                        } else {
                            this.unresolved = true;
                            if (throwOnResolveFailure) {
                                checkRelaxed("unresolved: &" + code + ";");
                                return;
                            }
                            return;
                        }
                    } else {
                        return;
                    }
                } else if (c2 >= 128 || ((c2 >= 48 && c2 <= 57) || ((c2 >= 97 && c2 <= 122) || ((c2 >= 65 && c2 <= 90) || c2 == 95 || c2 == 45 || c2 == 35)))) {
                    this.position++;
                    out.append((char) c2);
                } else if (!this.relaxed) {
                    throw new XmlPullParserException("unterminated entity ref", this, null);
                } else {
                    return;
                }
            }
        } else {
            throw new AssertionError();
        }
    }

    private String readValue(char delimiter, boolean resolveEntities, boolean throwOnResolveFailure, ValueContext valueContext) throws IOException, XmlPullParserException {
        int start = this.position;
        StringBuilder result = null;
        if (valueContext == ValueContext.TEXT && this.text != null) {
            result = new StringBuilder();
            result.append(this.text);
        }
        while (true) {
            int i = this.position;
            if (i >= this.limit) {
                if (start < i) {
                    if (result == null) {
                        result = new StringBuilder();
                    }
                    result.append(this.buffer, start, this.position - start);
                }
                if (!fillBuffer(1)) {
                    return result != null ? result.toString() : "";
                }
                start = this.position;
            }
            char c = this.buffer[this.position];
            if (c != delimiter) {
                if (delimiter != ' ' || (c > ' ' && c != '>')) {
                    if (c == '&' && !resolveEntities) {
                        break;
                    }
                    char c2 = '\n';
                    boolean z = false;
                    if (c == '\r' || ((c == '\n' && valueContext == ValueContext.ATTRIBUTE) || c == '&' || c == '<' || ((c == ']' && valueContext == ValueContext.TEXT) || (c == '%' && valueContext == ValueContext.ENTITY_DECLARATION)))) {
                        if (result == null) {
                            result = new StringBuilder();
                        }
                        result.append(this.buffer, start, this.position - start);
                        if (c == '\r') {
                            if (this.position + 1 < this.limit || fillBuffer(2)) {
                                char[] cArr = this.buffer;
                                int i2 = this.position;
                                if (cArr[i2 + 1] == '\n') {
                                    this.position = i2 + 1;
                                }
                            }
                            if (valueContext == ValueContext.ATTRIBUTE) {
                                c2 = ' ';
                            }
                            c = c2;
                        } else if (c == '\n') {
                            c = ' ';
                        } else if (c == '&') {
                            this.isWhitespace = false;
                            readEntity(result, false, throwOnResolveFailure, valueContext);
                            start = this.position;
                        } else if (c == '<') {
                            if (valueContext == ValueContext.ATTRIBUTE) {
                                checkRelaxed("Illegal: \"<\" inside attribute value");
                            }
                            this.isWhitespace = false;
                        } else if (c == ']') {
                            if (this.position + 2 < this.limit || fillBuffer(3)) {
                                char[] cArr2 = this.buffer;
                                int i3 = this.position;
                                if (cArr2[i3 + 1] == ']' && cArr2[i3 + 2] == '>') {
                                    checkRelaxed("Illegal: \"]]>\" outside CDATA section");
                                }
                            }
                            this.isWhitespace = false;
                        } else if (c == '%') {
                            throw new XmlPullParserException("This parser doesn't support parameter entities", this, null);
                        } else {
                            throw new AssertionError();
                        }
                        this.position++;
                        result.append(c);
                        start = this.position;
                    } else {
                        boolean z2 = this.isWhitespace;
                        if (c <= ' ') {
                            z = true;
                        }
                        this.isWhitespace = z2 & z;
                        this.position++;
                    }
                }
            } else {
                break;
            }
        }
        if (result == null) {
            return this.stringPool.get(this.buffer, start, this.position - start);
        }
        result.append(this.buffer, start, this.position - start);
        return result.toString();
    }

    private void read(char expected) throws IOException, XmlPullParserException {
        int c = peekCharacter();
        if (c != expected) {
            checkRelaxed("expected: '" + expected + "' actual: '" + ((char) c) + "'");
            if (c == -1) {
                return;
            }
        }
        this.position++;
    }

    private void read(char[] chars) throws IOException, XmlPullParserException {
        if (this.position + chars.length <= this.limit || fillBuffer(chars.length)) {
            for (int i = 0; i < chars.length; i++) {
                if (this.buffer[this.position + i] != chars[i]) {
                    checkRelaxed("expected: \"" + new String(chars) + "\" but was \"" + new String(this.buffer, this.position, chars.length) + "...\"");
                }
            }
            this.position += chars.length;
            return;
        }
        checkRelaxed("expected: '" + new String(chars) + "' but was EOF");
    }

    private int peekCharacter() throws IOException, XmlPullParserException {
        if (this.position < this.limit || fillBuffer(1)) {
            return this.buffer[this.position];
        }
        return -1;
    }

    private boolean fillBuffer(int minimum) throws IOException, XmlPullParserException {
        int i;
        while (this.nextContentSource != null) {
            if (this.position >= this.limit) {
                popContentSource();
                if (this.limit - this.position >= minimum) {
                    return true;
                }
            } else {
                throw new XmlPullParserException("Unbalanced entity!", this, null);
            }
        }
        int i2 = 0;
        while (true) {
            i = this.position;
            if (i2 >= i) {
                break;
            }
            if (this.buffer[i2] == '\n') {
                this.bufferStartLine++;
                this.bufferStartColumn = 0;
            } else {
                this.bufferStartColumn++;
            }
            i2++;
        }
        StringBuilder sb = this.bufferCapture;
        if (sb != null) {
            sb.append(this.buffer, 0, i);
        }
        int i3 = this.limit;
        int i4 = this.position;
        if (i3 != i4) {
            this.limit = i3 - i4;
            char[] cArr = this.buffer;
            System.arraycopy((Object) cArr, i4, (Object) cArr, 0, this.limit);
        } else {
            this.limit = 0;
        }
        this.position = 0;
        do {
            Reader reader2 = this.reader;
            char[] cArr2 = this.buffer;
            int i5 = this.limit;
            int total = reader2.read(cArr2, i5, cArr2.length - i5);
            if (total == -1) {
                return false;
            }
            this.limit += total;
        } while (this.limit < minimum);
        return true;
    }

    private String readName() throws IOException, XmlPullParserException {
        if (this.position < this.limit || fillBuffer(1)) {
            int start = this.position;
            StringBuilder result = null;
            char c = this.buffer[this.position];
            if ((c < 'a' || c > 'z') && ((c < 'A' || c > 'Z') && c != '_' && c != ':' && c < 192 && !this.relaxed)) {
                checkRelaxed("name expected");
                return "";
            }
            this.position++;
            while (true) {
                if (this.position >= this.limit) {
                    if (result == null) {
                        result = new StringBuilder();
                    }
                    result.append(this.buffer, start, this.position - start);
                    if (!fillBuffer(1)) {
                        return result.toString();
                    }
                    start = this.position;
                }
                char c2 = this.buffer[this.position];
                if ((c2 >= 'a' && c2 <= 'z') || ((c2 >= 'A' && c2 <= 'Z') || ((c2 >= '0' && c2 <= '9') || c2 == '_' || c2 == '-' || c2 == ':' || c2 == '.' || c2 >= 183))) {
                    this.position++;
                } else if (result == null) {
                    return this.stringPool.get(this.buffer, start, this.position - start);
                } else {
                    result.append(this.buffer, start, this.position - start);
                    return result.toString();
                }
            }
        } else {
            checkRelaxed("name expected");
            return "";
        }
    }

    private void skip() throws IOException, XmlPullParserException {
        while (true) {
            if (this.position < this.limit || fillBuffer(1)) {
                char[] cArr = this.buffer;
                int i = this.position;
                if (cArr[i] <= ' ') {
                    this.position = i + 1;
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public void setInput(Reader reader2) throws XmlPullParserException {
        this.reader = reader2;
        this.type = 0;
        this.parsedTopLevelStartTag = false;
        this.name = null;
        this.namespace = null;
        this.degenerated = false;
        this.attributeCount = -1;
        this.encoding = null;
        this.version = null;
        this.standalone = null;
        if (reader2 != null) {
            this.position = 0;
            this.limit = 0;
            this.bufferStartLine = 0;
            this.bufferStartColumn = 0;
            this.depth = 0;
            this.documentEntities = null;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // org.xmlpull.v1.XmlPullParser
    public void setInput(InputStream is, String charset) throws XmlPullParserException {
        this.position = 0;
        this.limit = 0;
        boolean detectCharset = charset == null;
        if (is != null) {
            if (detectCharset) {
                int firstFourBytes = 0;
                while (true) {
                    try {
                        if (this.limit >= 4) {
                            break;
                        }
                        int i = is.read();
                        if (i == -1) {
                            break;
                        }
                        firstFourBytes = (firstFourBytes << 8) | i;
                        char[] cArr = this.buffer;
                        int i2 = this.limit;
                        this.limit = i2 + 1;
                        cArr[i2] = (char) i;
                    } catch (Exception e) {
                        throw new XmlPullParserException("Invalid stream or encoding: " + ((Object) e), this, e);
                    }
                }
                if (this.limit == 4) {
                    switch (firstFourBytes) {
                        case -131072:
                            charset = "UTF-32LE";
                            this.limit = 0;
                            break;
                        case 60:
                            charset = "UTF-32BE";
                            this.buffer[0] = '<';
                            this.limit = 1;
                            break;
                        case 65279:
                            charset = "UTF-32BE";
                            this.limit = 0;
                            break;
                        case 3932223:
                            charset = "UTF-16BE";
                            this.buffer[0] = '<';
                            this.buffer[1] = '?';
                            this.limit = 2;
                            break;
                        case 1006632960:
                            charset = "UTF-32LE";
                            this.buffer[0] = '<';
                            this.limit = 1;
                            break;
                        case 1006649088:
                            charset = "UTF-16LE";
                            this.buffer[0] = '<';
                            this.buffer[1] = '?';
                            this.limit = 2;
                            break;
                        case 1010792557:
                            while (true) {
                                int i3 = is.read();
                                if (i3 != -1) {
                                    char[] cArr2 = this.buffer;
                                    int i4 = this.limit;
                                    this.limit = i4 + 1;
                                    cArr2[i4] = (char) i3;
                                    if (i3 == 62) {
                                        String s = new String(this.buffer, 0, this.limit);
                                        int i0 = s.indexOf(OutputKeys.ENCODING);
                                        if (i0 != -1) {
                                            while (s.charAt(i0) != '\"' && s.charAt(i0) != '\'') {
                                                i0++;
                                            }
                                            int i02 = i0 + 1;
                                            charset = s.substring(i02, s.indexOf(s.charAt(i0), i02));
                                            break;
                                        }
                                    }
                                } else {
                                    break;
                                }
                            }
                            break;
                        default:
                            if ((firstFourBytes & SupportMenu.CATEGORY_MASK) != -16842752) {
                                if ((-65536 & firstFourBytes) != -131072) {
                                    if ((firstFourBytes & InputDeviceCompat.SOURCE_ANY) == -272908544) {
                                        charset = "UTF-8";
                                        this.buffer[0] = this.buffer[3];
                                        this.limit = 1;
                                        break;
                                    }
                                } else {
                                    charset = "UTF-16LE";
                                    this.buffer[0] = (char) ((this.buffer[3] << '\b') | this.buffer[2]);
                                    this.limit = 1;
                                    break;
                                }
                            } else {
                                charset = "UTF-16BE";
                                this.buffer[0] = (char) ((this.buffer[2] << '\b') | this.buffer[3]);
                                this.limit = 1;
                                break;
                            }
                            break;
                    }
                }
            }
            if (charset == null) {
                charset = "UTF-8";
            }
            int savedLimit = this.limit;
            setInput(new InputStreamReader(is, charset));
            this.encoding = charset;
            this.limit = savedLimit;
            if (!detectCharset && peekCharacter() == 65279) {
                this.limit--;
                System.arraycopy((Object) this.buffer, 1, (Object) this.buffer, 0, this.limit);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("is == null");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        Reader reader2 = this.reader;
        if (reader2 != null) {
            reader2.close();
        }
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public boolean getFeature(String feature) {
        if (XmlPullParser.FEATURE_PROCESS_NAMESPACES.equals(feature)) {
            return this.processNsp;
        }
        if (FEATURE_RELAXED.equals(feature)) {
            return this.relaxed;
        }
        if (XmlPullParser.FEATURE_PROCESS_DOCDECL.equals(feature)) {
            return this.processDocDecl;
        }
        return false;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getInputEncoding() {
        return this.encoding;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public void defineEntityReplacementText(String entity, String value) throws XmlPullParserException {
        if (this.processDocDecl) {
            throw new IllegalStateException("Entity replacement text may not be defined with DOCTYPE processing enabled.");
        } else if (this.reader != null) {
            if (this.documentEntities == null) {
                this.documentEntities = new HashMap();
            }
            this.documentEntities.put(entity, value.toCharArray());
        } else {
            throw new IllegalStateException("Entity replacement text must be defined after setInput()");
        }
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public Object getProperty(String property) {
        if (property.equals(PROPERTY_XMLDECL_VERSION)) {
            return this.version;
        }
        if (property.equals(PROPERTY_XMLDECL_STANDALONE)) {
            return this.standalone;
        }
        if (!property.equals(PROPERTY_LOCATION)) {
            return null;
        }
        String str = this.location;
        return str != null ? str : this.reader.toString();
    }

    public String getRootElementName() {
        return this.rootElementName;
    }

    public String getSystemId() {
        return this.systemId;
    }

    public String getPublicId() {
        return this.publicId;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int getNamespaceCount(int depth2) {
        if (depth2 <= this.depth) {
            return this.nspCounts[depth2];
        }
        throw new IndexOutOfBoundsException();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getNamespacePrefix(int pos) {
        return this.nspStack[pos * 2];
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getNamespaceUri(int pos) {
        return this.nspStack[(pos * 2) + 1];
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getNamespace(String prefix2) {
        if (XMLConstants.XML_NS_PREFIX.equals(prefix2)) {
            return "http://www.w3.org/XML/1998/namespace";
        }
        if (XMLConstants.XMLNS_ATTRIBUTE.equals(prefix2)) {
            return XMLConstants.XMLNS_ATTRIBUTE_NS_URI;
        }
        for (int i = (getNamespaceCount(this.depth) << 1) - 2; i >= 0; i -= 2) {
            if (prefix2 == null) {
                String[] strArr = this.nspStack;
                if (strArr[i] == null) {
                    return strArr[i + 1];
                }
            } else if (prefix2.equals(this.nspStack[i])) {
                return this.nspStack[i + 1];
            }
        }
        return null;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int getDepth() {
        return this.depth;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getPositionDescription() {
        StringBuilder buf = new StringBuilder(this.type < TYPES.length ? TYPES[this.type] : EnvironmentCompat.MEDIA_UNKNOWN);
        buf.append(' ');
        int i = this.type;
        if (i == 2 || i == 3) {
            if (this.degenerated) {
                buf.append("(empty) ");
            }
            buf.append('<');
            if (this.type == 3) {
                buf.append('/');
            }
            if (this.prefix != null) {
                buf.append("{" + this.namespace + "}" + this.prefix + ":");
            }
            buf.append(this.name);
            int cnt = this.attributeCount * 4;
            for (int i2 = 0; i2 < cnt; i2 += 4) {
                buf.append(' ');
                if (this.attributes[i2 + 1] != null) {
                    buf.append("{" + this.attributes[i2] + "}" + this.attributes[i2 + 1] + ":");
                }
                buf.append(this.attributes[i2 + 2] + "='" + this.attributes[i2 + 3] + "'");
            }
            buf.append('>');
        } else if (i != 7) {
            if (i != 4) {
                buf.append(getText());
            } else if (this.isWhitespace) {
                buf.append("(whitespace)");
            } else {
                String text2 = getText();
                if (text2.length() > 16) {
                    text2 = text2.substring(0, 16) + "...";
                }
                buf.append(text2);
            }
        }
        buf.append("@" + getLineNumber() + ":" + getColumnNumber());
        if (this.location != null) {
            buf.append(" in ");
            buf.append(this.location);
        } else if (this.reader != null) {
            buf.append(" in ");
            buf.append(this.reader.toString());
        }
        return buf.toString();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int getLineNumber() {
        int result = this.bufferStartLine;
        for (int i = 0; i < this.position; i++) {
            if (this.buffer[i] == '\n') {
                result++;
            }
        }
        return result + 1;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int getColumnNumber() {
        int result = this.bufferStartColumn;
        for (int i = 0; i < this.position; i++) {
            if (this.buffer[i] == '\n') {
                result = 0;
            } else {
                result++;
            }
        }
        return result + 1;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public boolean isWhitespace() throws XmlPullParserException {
        int i = this.type;
        if (i == 4 || i == 7 || i == 5) {
            return this.isWhitespace;
        }
        throw new XmlPullParserException(ILLEGAL_TYPE, this, null);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getText() {
        int i = this.type;
        if (i < 4) {
            return null;
        }
        if (i == 6 && this.unresolved) {
            return null;
        }
        String str = this.text;
        if (str == null) {
            return "";
        }
        return str;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public char[] getTextCharacters(int[] poslen) {
        String text2 = getText();
        if (text2 == null) {
            poslen[0] = -1;
            poslen[1] = -1;
            return null;
        }
        char[] result = text2.toCharArray();
        poslen[0] = 0;
        poslen[1] = result.length;
        return result;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getNamespace() {
        return this.namespace;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getName() {
        return this.name;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getPrefix() {
        return this.prefix;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public boolean isEmptyElementTag() throws XmlPullParserException {
        if (this.type == 2) {
            return this.degenerated;
        }
        throw new XmlPullParserException(ILLEGAL_TYPE, this, null);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int getAttributeCount() {
        return this.attributeCount;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getAttributeType(int index) {
        return "CDATA";
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public boolean isAttributeDefault(int index) {
        return false;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getAttributeNamespace(int index) {
        if (index < this.attributeCount) {
            return this.attributes[index * 4];
        }
        throw new IndexOutOfBoundsException();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getAttributeName(int index) {
        if (index < this.attributeCount) {
            return this.attributes[(index * 4) + 2];
        }
        throw new IndexOutOfBoundsException();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getAttributePrefix(int index) {
        if (index < this.attributeCount) {
            return this.attributes[(index * 4) + 1];
        }
        throw new IndexOutOfBoundsException();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getAttributeValue(int index) {
        if (index < this.attributeCount) {
            return this.attributes[(index * 4) + 3];
        }
        throw new IndexOutOfBoundsException();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getAttributeValue(String namespace2, String name2) {
        for (int i = (this.attributeCount * 4) - 4; i >= 0; i -= 4) {
            if (this.attributes[i + 2].equals(name2) && (namespace2 == null || this.attributes[i].equals(namespace2))) {
                return this.attributes[i + 3];
            }
        }
        return null;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int getEventType() throws XmlPullParserException {
        return this.type;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int nextTag() throws XmlPullParserException, IOException {
        next();
        if (this.type == 4 && this.isWhitespace) {
            next();
        }
        int i = this.type;
        if (i == 3 || i == 2) {
            return this.type;
        }
        throw new XmlPullParserException("unexpected type", this, null);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public void require(int type2, String namespace2, String name2) throws XmlPullParserException, IOException {
        if (type2 != this.type || ((namespace2 != null && !namespace2.equals(getNamespace())) || (name2 != null && !name2.equals(getName())))) {
            throw new XmlPullParserException("expected: " + TYPES[type2] + " {" + namespace2 + "}" + name2, this, null);
        }
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String nextText() throws XmlPullParserException, IOException {
        String result;
        if (this.type == 2) {
            next();
            if (this.type == 4) {
                result = getText();
                next();
            } else {
                result = "";
            }
            if (this.type == 3) {
                return result;
            }
            throw new XmlPullParserException("END_TAG expected", this, null);
        }
        throw new XmlPullParserException("precondition: START_TAG", this, null);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public void setFeature(String feature, boolean value) throws XmlPullParserException {
        if (XmlPullParser.FEATURE_PROCESS_NAMESPACES.equals(feature)) {
            this.processNsp = value;
        } else if (XmlPullParser.FEATURE_PROCESS_DOCDECL.equals(feature)) {
            this.processDocDecl = value;
        } else if (FEATURE_RELAXED.equals(feature)) {
            this.relaxed = value;
        } else {
            throw new XmlPullParserException("unsupported feature: " + feature, this, null);
        }
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public void setProperty(String property, Object value) throws XmlPullParserException {
        if (property.equals(PROPERTY_LOCATION)) {
            this.location = String.valueOf(value);
            return;
        }
        throw new XmlPullParserException("unsupported property: " + property);
    }

    /* access modifiers changed from: package-private */
    public static class ContentSource {
        private final char[] buffer;
        private final int limit;
        private final ContentSource next;
        private final int position;

        ContentSource(ContentSource next2, char[] buffer2, int position2, int limit2) {
            this.next = next2;
            this.buffer = buffer2;
            this.position = position2;
            this.limit = limit2;
        }
    }

    private void pushContentSource(char[] newBuffer) {
        this.nextContentSource = new ContentSource(this.nextContentSource, this.buffer, this.position, this.limit);
        this.buffer = newBuffer;
        this.position = 0;
        this.limit = newBuffer.length;
    }

    private void popContentSource() {
        this.buffer = this.nextContentSource.buffer;
        this.position = this.nextContentSource.position;
        this.limit = this.nextContentSource.limit;
        this.nextContentSource = this.nextContentSource.next;
    }
}
