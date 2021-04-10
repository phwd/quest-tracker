package com.oculus.video.audio;

import java.io.StringReader;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Mp4Parser {
    private static final String FOCUS_ENABLED_ATTRIBUTE = "enabled";
    private static final boolean FOCUS_ENABLED_DEFAULT = false;
    private static final String FOCUS_TAG = "focus";
    private static final String FOCUS_WIDTH_DEGREES_ATTRIBUTE = "focusWidthDegrees";
    private static final float FOCUS_WIDTH_DEGREES_DEFAULT = 0.0f;
    private static final String OFF_FOCUS_LEVEL_ATTRIBUTE = "offFocusLevel";
    private static final String OFF_FOCUS_LEVEL_DB_ATTRIBUTE = "offFocusLevelDB";
    private static final float OFF_FOCUS_LEVEL_DB_DEFAULT = 0.0f;
    private static final float OFF_FOCUS_LEVEL_DEFAULT = 1.0f;
    private final Document mDocument;
    private final NamedNodeMap mFocusNodeAttributes;

    public static class ParserException extends Exception {
        public ParserException(Throwable th) {
            super(th);
        }
    }

    public Mp4Parser(String str) throws ParserException {
        try {
            this.mDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(str)));
            NodeList elementsByTagName = this.mDocument.getElementsByTagName(FOCUS_TAG);
            if (elementsByTagName.getLength() > 0) {
                this.mFocusNodeAttributes = elementsByTagName.item(0).getAttributes();
            } else {
                this.mFocusNodeAttributes = null;
            }
        } catch (Exception e) {
            throw new ParserException(e);
        }
    }

    public boolean getFocusEnabled() {
        Node namedItem;
        NamedNodeMap namedNodeMap = this.mFocusNodeAttributes;
        if (namedNodeMap == null || (namedItem = namedNodeMap.getNamedItem(FOCUS_ENABLED_ATTRIBUTE)) == null) {
            return false;
        }
        return "true".equalsIgnoreCase(namedItem.getNodeValue());
    }

    public float getOffFocusLevel() {
        Node namedItem;
        NamedNodeMap namedNodeMap = this.mFocusNodeAttributes;
        if (!(namedNodeMap == null || (namedItem = namedNodeMap.getNamedItem(OFF_FOCUS_LEVEL_ATTRIBUTE)) == null)) {
            try {
                return Float.valueOf(namedItem.getNodeValue()).floatValue();
            } catch (Exception unused) {
            }
        }
        return OFF_FOCUS_LEVEL_DEFAULT;
    }

    public float getOffFocusLeveldB() {
        Node namedItem;
        NamedNodeMap namedNodeMap = this.mFocusNodeAttributes;
        if (!(namedNodeMap == null || (namedItem = namedNodeMap.getNamedItem(OFF_FOCUS_LEVEL_DB_ATTRIBUTE)) == null)) {
            try {
                return Float.valueOf(namedItem.getNodeValue()).floatValue();
            } catch (Exception unused) {
            }
        }
        return 0.0f;
    }

    public float getFocusWidthDegrees() {
        Node namedItem;
        NamedNodeMap namedNodeMap = this.mFocusNodeAttributes;
        if (!(namedNodeMap == null || (namedItem = namedNodeMap.getNamedItem(FOCUS_WIDTH_DEGREES_ATTRIBUTE)) == null)) {
            try {
                return Float.valueOf(namedItem.getNodeValue()).floatValue();
            } catch (Exception unused) {
            }
        }
        return 0.0f;
    }
}
