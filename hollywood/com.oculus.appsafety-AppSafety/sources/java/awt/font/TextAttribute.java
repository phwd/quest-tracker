package java.awt.font;

import java.io.InvalidObjectException;
import java.text.AttributedCharacterIterator;
import java.util.HashMap;
import java.util.Map;

public final class TextAttribute extends AttributedCharacterIterator.Attribute {
    public static final TextAttribute BACKGROUND = new TextAttribute("background");
    public static final TextAttribute BIDI_EMBEDDING = new TextAttribute("bidi_embedding");
    public static final TextAttribute CHAR_REPLACEMENT = new TextAttribute("char_replacement");
    public static final TextAttribute FAMILY = new TextAttribute("family");
    public static final TextAttribute FONT = new TextAttribute("font");
    public static final TextAttribute FOREGROUND = new TextAttribute("foreground");
    public static final TextAttribute INPUT_METHOD_HIGHLIGHT = new TextAttribute("input method highlight");
    public static final TextAttribute INPUT_METHOD_UNDERLINE = new TextAttribute("input method underline");
    public static final TextAttribute JUSTIFICATION = new TextAttribute("justification");
    public static final Float JUSTIFICATION_FULL;
    public static final Float JUSTIFICATION_NONE;
    public static final TextAttribute KERNING = new TextAttribute("kerning");
    public static final Integer KERNING_ON = 1;
    public static final TextAttribute LIGATURES = new TextAttribute("ligatures");
    public static final Integer LIGATURES_ON = 1;
    public static final TextAttribute NUMERIC_SHAPING = new TextAttribute("numeric_shaping");
    public static final TextAttribute POSTURE = new TextAttribute("posture");
    public static final Float POSTURE_OBLIQUE = Float.valueOf(0.2f);
    public static final Float POSTURE_REGULAR;
    public static final TextAttribute RUN_DIRECTION = new TextAttribute("run_direction");
    public static final Boolean RUN_DIRECTION_LTR = Boolean.FALSE;
    public static final Boolean RUN_DIRECTION_RTL = Boolean.TRUE;
    public static final TextAttribute SIZE = new TextAttribute("size");
    public static final TextAttribute STRIKETHROUGH = new TextAttribute("strikethrough");
    public static final Boolean STRIKETHROUGH_ON = Boolean.TRUE;
    public static final TextAttribute SUPERSCRIPT = new TextAttribute("superscript");
    public static final Integer SUPERSCRIPT_SUB = -1;
    public static final Integer SUPERSCRIPT_SUPER = 1;
    public static final TextAttribute SWAP_COLORS = new TextAttribute("swap_colors");
    public static final Boolean SWAP_COLORS_ON = Boolean.TRUE;
    public static final TextAttribute TRACKING = new TextAttribute("tracking");
    public static final Float TRACKING_LOOSE = Float.valueOf(0.04f);
    public static final Float TRACKING_TIGHT = Float.valueOf(-0.04f);
    public static final TextAttribute TRANSFORM = new TextAttribute("transform");
    public static final TextAttribute UNDERLINE = new TextAttribute("underline");
    public static final Integer UNDERLINE_LOW_DASHED = 5;
    public static final Integer UNDERLINE_LOW_DOTTED = 3;
    public static final Integer UNDERLINE_LOW_GRAY = 4;
    public static final Integer UNDERLINE_LOW_ONE_PIXEL = 1;
    public static final Integer UNDERLINE_LOW_TWO_PIXEL = 2;
    public static final Integer UNDERLINE_ON = 0;
    public static final TextAttribute WEIGHT = new TextAttribute("weight");
    public static final Float WEIGHT_BOLD = Float.valueOf(2.0f);
    public static final Float WEIGHT_DEMIBOLD = Float.valueOf(1.75f);
    public static final Float WEIGHT_DEMILIGHT;
    public static final Float WEIGHT_EXTRABOLD = Float.valueOf(2.5f);
    public static final Float WEIGHT_EXTRA_LIGHT = Float.valueOf(0.5f);
    public static final Float WEIGHT_HEAVY = Float.valueOf(2.25f);
    public static final Float WEIGHT_LIGHT;
    public static final Float WEIGHT_MEDIUM;
    public static final Float WEIGHT_REGULAR;
    public static final Float WEIGHT_SEMIBOLD;
    public static final Float WEIGHT_ULTRABOLD = Float.valueOf(2.75f);
    public static final TextAttribute WIDTH = new TextAttribute("width");
    public static final Float WIDTH_CONDENSED;
    public static final Float WIDTH_EXTENDED;
    public static final Float WIDTH_REGULAR;
    public static final Float WIDTH_SEMI_CONDENSED;
    public static final Float WIDTH_SEMI_EXTENDED;
    private static final Map<String, TextAttribute> instanceMap = new HashMap(29);
    static final long serialVersionUID = 7744112784117861702L;

    static {
        Float valueOf = Float.valueOf(0.75f);
        WEIGHT_LIGHT = valueOf;
        Float valueOf2 = Float.valueOf(0.875f);
        WEIGHT_DEMILIGHT = valueOf2;
        Float valueOf3 = Float.valueOf(1.0f);
        WEIGHT_REGULAR = valueOf3;
        Float valueOf4 = Float.valueOf(1.25f);
        WEIGHT_SEMIBOLD = valueOf4;
        Float valueOf5 = Float.valueOf(1.5f);
        WEIGHT_MEDIUM = valueOf5;
        WIDTH_CONDENSED = valueOf;
        WIDTH_SEMI_CONDENSED = valueOf2;
        WIDTH_REGULAR = valueOf3;
        WIDTH_SEMI_EXTENDED = valueOf4;
        WIDTH_EXTENDED = valueOf5;
        Float valueOf6 = Float.valueOf(0.0f);
        POSTURE_REGULAR = valueOf6;
        JUSTIFICATION_FULL = valueOf3;
        JUSTIFICATION_NONE = valueOf6;
    }

    protected TextAttribute(String name) {
        super(name);
        if (getClass() == TextAttribute.class) {
            instanceMap.put(name, this);
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.text.AttributedCharacterIterator.Attribute
    public Object readResolve() throws InvalidObjectException {
        if (getClass() == TextAttribute.class) {
            TextAttribute instance = instanceMap.get(getName());
            if (instance != null) {
                return instance;
            }
            throw new InvalidObjectException("unknown attribute name");
        }
        throw new InvalidObjectException("subclass didn't correctly implement readResolve");
    }
}
