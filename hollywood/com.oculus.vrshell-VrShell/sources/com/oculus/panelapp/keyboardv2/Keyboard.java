package com.oculus.panelapp.keyboardv2;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import com.facebook.debug.log.LoggingUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;
import org.xmlpull.v1.XmlPullParserException;

public class Keyboard {
    private static final String DEFAULT_FONT = "Fonts/Roboto-Regular.ttf";
    public static final int EDGE_BOTTOM = 8;
    public static final int EDGE_LEFT = 1;
    public static final int EDGE_RIGHT = 2;
    public static final int EDGE_TOP = 4;
    private static final int GRID_HEIGHT = 5;
    private static final int GRID_SIZE = 50;
    private static final int GRID_WIDTH = 10;
    public static final int NO_KEY_TO_HIGHLIGHT = -1;
    private static float SEARCH_DISTANCE = 1.8f;
    static final String TAG = "Keyboard";
    private static final String TAG_DYNAMIC_KEY_AREA = "DynamicKeyArea";
    private static final String TAG_KEY = "Key";
    private static final String TAG_KEYBOARD = "Keyboard";
    private static final String TAG_ROW = "Row";
    private Key mActionKey;
    private ActionType mActionType;
    private int mCellHeight;
    private int mCellWidth;
    private int mDefaultHeight;
    private int mDefaultHorizontalGap;
    private ColorStateList mDefaultKeyTextColor;
    private KeyboardSize mDefaultKeyboardSize;
    private KeyboardLocale mDefaultLocale;
    private int mDefaultVerticalGap;
    private int mDefaultWidth;
    private int mDisplayHeight;
    private int mDisplayWidth;
    private HashMap<Integer, DynamicKeyArea> mDynamicKeyAreaMap;
    private Map<String, Typeface> mFontMap;
    private int[][] mGridNeighbors;
    private String mIme;
    private boolean mIsAssistantInstalled;
    private String mKeyFontFilePath;
    private int mKeySubTextSize;
    private int mKeyTextSize;
    private int mKeyToHighlight;
    private String mKeyboardActionListener;
    private int mKeyboardMode;
    private List<Key> mKeys;
    private boolean mLayoutInvalid;
    private KeyboardPanelApp mPanelApp;
    private int mProximityThreshold;
    private ArrayList<Row> mRows;
    private Key mShiftKey;
    private int[] mShiftKeyIndices;
    private ShiftState mShiftState;
    private int mTotalHeight;
    private int mTotalWidth;
    private Key mTranscribeKey;
    private int mXmlLayoutResId;

    public enum KeyDimensionMode {
        FIXED,
        FIT_CONTENT
    }

    public interface KeyLayout {
        Rect getBounds();

        int getHeight();

        int getWidth();

        int getX();

        int getY();
    }

    public enum KeyPositionMode {
        FIXED,
        FLOW
    }

    public static class Row {
        public int defaultHeight;
        public int defaultHorizontalGap;
        public int defaultWidth;
        ArrayList<Key> mKeys = new ArrayList<>();
        public int mode;
        private Keyboard parent;
        public int rowEdgeFlags;
        public int verticalGap;

        public Row(Keyboard keyboard) {
            this.parent = keyboard;
        }

        public Row(Resources resources, Keyboard keyboard, XmlResourceParser xmlResourceParser) {
            this.parent = keyboard;
            TypedArray obtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlResourceParser), R.styleable.Keyboard);
            this.defaultWidth = Keyboard.getDimensionOrFraction(obtainAttributes, R.styleable.Keyboard_keyWidth, keyboard.mDisplayWidth, keyboard.mDefaultWidth, keyboard.mDefaultWidth, keyboard.mDefaultHorizontalGap, false);
            this.defaultHeight = Keyboard.getDimensionOrFraction(obtainAttributes, R.styleable.Keyboard_keyHeight, keyboard.mDisplayHeight, keyboard.mDefaultHeight, keyboard.mDefaultHeight, keyboard.mDefaultVerticalGap, false);
            this.defaultHorizontalGap = Keyboard.getDimensionOrFraction(obtainAttributes, R.styleable.Keyboard_horizontalGap, keyboard.mDisplayWidth, keyboard.mDefaultHorizontalGap, keyboard.mDefaultWidth, keyboard.mDefaultHorizontalGap, true);
            this.verticalGap = Keyboard.getDimensionOrFraction(obtainAttributes, R.styleable.Keyboard_verticalGap, keyboard.mDisplayHeight, keyboard.mDefaultVerticalGap, keyboard.mDefaultHeight, keyboard.mDefaultVerticalGap, true);
            obtainAttributes.recycle();
            TypedArray obtainAttributes2 = resources.obtainAttributes(Xml.asAttributeSet(xmlResourceParser), R.styleable.Keyboard_Row);
            this.rowEdgeFlags = obtainAttributes2.getInt(R.styleable.Keyboard_Row_rowEdgeFlags, 0);
            this.mode = obtainAttributes2.getResourceId(R.styleable.Keyboard_Row_keyboardMode, 0);
        }

        public List<Key> getKeys() {
            return this.mKeys;
        }

        public Keyboard getKeyboard() {
            return this.parent;
        }

        public int getDefaultWidth() {
            return this.defaultWidth;
        }

        public int getDefaultHeight() {
            return this.defaultHeight;
        }
    }

    public static class Key implements KeyLayout {
        private static final int[][] EQUIV_LAYOUTS = {new int[]{R.xml.en_us_text_default_32, R.xml.en_us_text_default_48, R.xml.en_us_text_default_transcription_32, R.xml.en_us_text_default_transcription_48, R.xml.en_us_text_simple_32, R.xml.en_us_text_simple_48}};
        public int automationId;
        public List<Integer> codes;
        public boolean disabled;
        public int edgeFlags;
        public Typeface font;
        public int gap;
        public int height;
        public boolean hoveredOver;
        public Drawable icon;
        public Drawable iconPreview;
        public int iconRef;
        public int id;
        public boolean isExitKey;
        public boolean isShiftPopupKeyboardDifferent;
        public Drawable keyBackground;
        private final Rect keyBounds;
        public ColorStateList keyTextColor;
        public int keyTextSize;
        private Keyboard keyboard;
        public int keyboardId;
        public KeyboardLocale keyboardLocale;
        public KeyboardSize keyboardSize;
        public CharSequence label;
        public int layoutIndex;
        public Optional<DynamicKeyArea> mDynamicArea;
        public KeyDimensionMode mWidthMode;
        public KeyPositionMode mXMode;
        public boolean modifier;
        public boolean on;
        public CharSequence popupCharacters;
        public int popupResId;
        public boolean pressed;
        public boolean repeatable;
        public Drawable shiftIcon;
        public CharSequence shiftLabel;
        public int shiftPopupResId;
        public CharSequence shiftText;
        public boolean sticky;
        public int subKeyTextSize;
        public CharSequence subLabel;
        public Object tag;
        public CharSequence text;
        public int width;
        public int x;
        public int y;

        public Key() {
            this.mWidthMode = KeyDimensionMode.FIXED;
            this.mXMode = KeyPositionMode.FIXED;
            this.mDynamicArea = Optional.empty();
            this.keyBounds = new Rect();
        }

        public Key(Row row) {
            this.mWidthMode = KeyDimensionMode.FIXED;
            this.mXMode = KeyPositionMode.FIXED;
            this.mDynamicArea = Optional.empty();
            this.keyBounds = new Rect();
            this.layoutIndex = row.getKeys().size();
            this.keyboard = row.parent;
            this.height = row.defaultHeight;
            this.width = row.defaultWidth;
            this.gap = row.defaultHorizontalGap;
            this.edgeFlags = row.rowEdgeFlags;
        }

        public Key(Context context, Row row, int i, int i2, XmlResourceParser xmlResourceParser, ActionType actionType, KeyboardLocale keyboardLocale2, KeyboardSize keyboardSize2) {
            this(row);
            Drawable drawable;
            this.x = i;
            this.y = i2;
            Resources resources = context.getResources();
            TypedArray obtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlResourceParser), R.styleable.Keyboard);
            this.id = obtainAttributes.getResourceId(R.styleable.Keyboard_id, 0);
            this.width = Keyboard.getDimensionOrFraction(obtainAttributes, R.styleable.Keyboard_keyWidth, this.keyboard.mDisplayWidth, row.defaultWidth, row.defaultWidth, this.keyboard.mDefaultHorizontalGap, false);
            this.height = Keyboard.getDimensionOrFraction(obtainAttributes, R.styleable.Keyboard_keyHeight, this.keyboard.mDisplayHeight, row.defaultHeight, row.defaultHeight, this.keyboard.mDefaultVerticalGap, false);
            this.gap = Keyboard.getDimensionOrFraction(obtainAttributes, R.styleable.Keyboard_horizontalGap, this.keyboard.mDisplayWidth, row.defaultHorizontalGap, row.defaultWidth, row.defaultHorizontalGap, true);
            obtainAttributes.recycle();
            TypedArray obtainAttributes2 = resources.obtainAttributes(Xml.asAttributeSet(xmlResourceParser), R.styleable.Keyboard_Key);
            if (this.x > 0 || this.gap != this.keyboard.mDefaultHorizontalGap) {
                this.x += this.gap;
            }
            TypedValue typedValue = new TypedValue();
            obtainAttributes2.getValue(R.styleable.Keyboard_Key_codes, typedValue);
            if (typedValue.type == 16 || typedValue.type == 17) {
                this.codes = new ArrayList();
                this.codes.add(Integer.valueOf(typedValue.data));
            } else if (typedValue.type == 3) {
                this.codes = parseCSV(typedValue.string.toString());
            }
            this.tag = Integer.valueOf(obtainAttributes2.getResourceId(R.styleable.Keyboard_Key_tag, 0));
            if (this.tag.equals(0)) {
                this.tag = obtainAttributes2.getString(R.styleable.Keyboard_Key_tag);
            }
            this.iconPreview = obtainAttributes2.getDrawable(R.styleable.Keyboard_Key_iconPreview);
            Drawable drawable2 = this.iconPreview;
            if (drawable2 != null) {
                drawable2.setBounds(0, 0, drawable2.getIntrinsicWidth(), this.iconPreview.getIntrinsicHeight());
            }
            this.keyTextColor = obtainAttributes2.getColorStateList(R.styleable.Keyboard_Key_textColor);
            if (this.keyTextColor == null) {
                this.keyTextColor = this.keyboard.mDefaultKeyTextColor;
            }
            this.keyTextSize = obtainAttributes2.getDimensionPixelSize(R.styleable.Keyboard_Key_textSize, this.keyboard.mKeyTextSize);
            this.subKeyTextSize = obtainAttributes2.getDimensionPixelSize(R.styleable.Keyboard_Key_subTextSize, this.keyboard.mKeySubTextSize);
            this.popupCharacters = obtainAttributes2.getText(R.styleable.Keyboard_Key_popupCharacters);
            this.popupResId = obtainAttributes2.getResourceId(R.styleable.Keyboard_Key_popupKeyboard, 0);
            this.isShiftPopupKeyboardDifferent = obtainAttributes2.getBoolean(R.styleable.Keyboard_Key_isShiftPopupKeyboardDifferent, false);
            if (this.isShiftPopupKeyboardDifferent) {
                this.shiftPopupResId = obtainAttributes2.getResourceId(R.styleable.Keyboard_Key_shiftPopupKeyboard, 0);
            } else {
                this.shiftPopupResId = this.popupResId;
            }
            this.keyboardId = obtainAttributes2.getResourceId(R.styleable.Keyboard_Key_keyboard, 0);
            this.repeatable = obtainAttributes2.getBoolean(R.styleable.Keyboard_Key_isRepeatable, false);
            this.modifier = obtainAttributes2.getBoolean(R.styleable.Keyboard_Key_isModifier, false);
            this.sticky = obtainAttributes2.getBoolean(R.styleable.Keyboard_Key_isSticky, false);
            this.isExitKey = obtainAttributes2.getBoolean(R.styleable.Keyboard_Key_isExitKey, false);
            this.edgeFlags = obtainAttributes2.getInt(R.styleable.Keyboard_Key_keyEdgeFlags, 0);
            this.edgeFlags = row.rowEdgeFlags | this.edgeFlags;
            this.iconRef = obtainAttributes2.getResourceId(R.styleable.Keyboard_Key_keyIcon, 0);
            this.icon = obtainAttributes2.getDrawable(R.styleable.Keyboard_Key_keyIcon);
            this.shiftIcon = obtainAttributes2.getDrawable(R.styleable.Keyboard_Key_shiftKeyIcon);
            Drawable drawable3 = this.icon;
            if (drawable3 != null) {
                drawable3.setBounds(0, 0, drawable3.getIntrinsicWidth(), this.icon.getIntrinsicHeight());
            }
            Drawable drawable4 = this.shiftIcon;
            if (drawable4 != null) {
                drawable4.setBounds(0, 0, drawable4.getIntrinsicWidth(), this.shiftIcon.getIntrinsicHeight());
            }
            String string = obtainAttributes2.getString(R.styleable.Keyboard_Key_keyboardSize);
            this.keyboardSize = string != null ? KeyboardSize.valueOf(string.toUpperCase()) : KeyboardSize.UNKNOWN;
            String string2 = obtainAttributes2.getString(R.styleable.Keyboard_Key_keyboardLocale);
            this.keyboardLocale = string2 != null ? KeyboardLocale.valueOf(string2.toUpperCase()) : KeyboardLocale.UNKNOWN;
            CharSequence text2 = obtainAttributes2.getText(R.styleable.Keyboard_Key_fontPath);
            this.font = loadFont(context, text2 == null ? this.keyboard.mKeyFontFilePath : text2.toString());
            this.label = obtainAttributes2.getText(R.styleable.Keyboard_Key_keyLabel);
            this.subLabel = obtainAttributes2.getText(R.styleable.Keyboard_Key_keySubLabel);
            this.text = obtainAttributes2.getText(R.styleable.Keyboard_Key_keyOutputText);
            this.shiftLabel = obtainAttributes2.getText(R.styleable.Keyboard_Key_shiftKeyLabel);
            this.shiftText = obtainAttributes2.getText(R.styleable.Keyboard_Key_shiftKeyOutputText);
            this.keyBackground = obtainAttributes2.getDrawable(R.styleable.Keyboard_Key_background);
            if (this.codes == null && !TextUtils.isEmpty(this.label)) {
                this.codes = new ArrayList();
                this.codes.add(Integer.valueOf(this.label.charAt(0) - 0));
            }
            if (this.codes.get(0).intValue() == KeyCode.ACTION_KEY.value) {
                CharSequence charSequence = this.label;
                CharSequence charSequence2 = this.subLabel;
                CharSequence charSequence3 = this.shiftLabel;
                Drawable drawable5 = this.icon;
                Drawable drawable6 = this.shiftIcon;
                this.label = null;
                this.subLabel = null;
                this.shiftLabel = null;
                this.icon = null;
                this.shiftIcon = null;
                switch (actionType) {
                    case GO:
                        if (charSequence == null) {
                            if (drawable5 != null) {
                                this.icon = drawable5;
                                break;
                            }
                        } else {
                            this.label = charSequence;
                            this.subLabel = charSequence2;
                            this.shiftLabel = charSequence3;
                            break;
                        }
                        break;
                    case NEWLINE:
                        this.icon = resources.getDrawable(R.drawable.ic_keyboard_enter_outline_24);
                        break;
                    case SEARCH:
                        this.icon = resources.getDrawable(R.drawable.ic_search_outline_24);
                        break;
                    case NEXT:
                        this.icon = resources.getDrawable(R.drawable.ic_arrow_right_circle_outline_24);
                        break;
                    case SEND:
                        this.icon = resources.getDrawable(R.drawable.ic_send_outline_24);
                        break;
                    case DONE:
                        this.icon = resources.getDrawable(R.drawable.ic_check_outline_24);
                        break;
                }
            }
            if (this.keyBackground == null && (this.codes.get(0).intValue() == KeyCode.SHIFT.value || this.codes.get(0).intValue() == KeyCode.ACTION_KEY.value || this.codes.get(0).intValue() == KeyCode.LAYOUT.value || this.codes.get(0).intValue() == KeyCode.DISMISS.value || this.codes.get(0).intValue() == KeyCode.BACKSPACE.value || this.codes.get(0).intValue() == KeyCode.PHONE_INPUT.value)) {
                this.keyBackground = resources.getDrawable(R.drawable.keyboard_key_background_highlighted);
            }
            if (this.codes.get(0).intValue() == KeyCode.TRANSCRIBE.value) {
                if (this.keyboard.mIsAssistantInstalled) {
                    drawable = resources.getDrawable(R.drawable.keyboard_key_background_highlighted);
                } else {
                    drawable = resources.getDrawable(R.drawable.keyboard_key_background_disabled);
                }
                this.keyBackground = drawable;
            }
            obtainAttributes2.recycle();
        }

        public void onPressed() {
            this.pressed = true;
        }

        public void onHoverEnter() {
            this.hoveredOver = true;
        }

        public void onHoverExit() {
            this.hoveredOver = false;
        }

        public void onReleased() {
            this.pressed = false;
            if (this.sticky && this.codes.get(0).intValue() != KeyCode.TRANSCRIBE.value) {
                this.on = !this.on;
            }
        }

        /* access modifiers changed from: package-private */
        public List<Integer> parseCSV(String str) {
            int i = 0;
            if (str.length() > 0) {
                int i2 = 1;
                while (true) {
                    i = str.indexOf(",", i + 1);
                    if (i <= 0) {
                        break;
                    }
                    i2++;
                }
                i = i2;
            }
            ArrayList arrayList = new ArrayList(i);
            StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
            while (stringTokenizer.hasMoreTokens()) {
                try {
                    arrayList.add(Integer.valueOf(Integer.parseInt(stringTokenizer.nextToken())));
                } catch (NumberFormatException unused) {
                    Log.e("Keyboard", "Error parsing keycodes " + str);
                }
            }
            return arrayList;
        }

        public boolean isInside(int i, int i2) {
            boolean z = (this.edgeFlags & 1) > 0;
            boolean z2 = (this.edgeFlags & 2) > 0;
            boolean z3 = (this.edgeFlags & 4) > 0;
            boolean z4 = (this.edgeFlags & 8) > 0;
            Rect bounds = getBounds();
            return (i >= bounds.left || (z && i <= bounds.right)) && (i < bounds.right || (z2 && i >= bounds.left)) && ((i2 >= bounds.top || (z3 && i2 <= bounds.bottom)) && (i2 < bounds.bottom || (z4 && i2 >= bounds.top)));
        }

        @Override // com.oculus.panelapp.keyboardv2.Keyboard.KeyLayout
        public Rect getBounds() {
            if (this.mDynamicArea.isPresent()) {
                Rect bounds = this.mDynamicArea.get().getBounds();
                this.keyBounds.left = Math.max(this.x, bounds.left);
                this.keyBounds.right = Math.min(this.x + this.width, bounds.right);
                this.keyBounds.top = Math.max(this.y, bounds.top);
                this.keyBounds.bottom = Math.min(this.y + this.height, bounds.bottom);
            } else if (this.keyBounds.isEmpty()) {
                Rect rect = this.keyBounds;
                int i = this.x;
                rect.left = i;
                rect.right = i + this.width;
                int i2 = this.y;
                rect.top = i2;
                rect.bottom = i2 + this.height;
            }
            return this.keyBounds;
        }

        public int[] getCurrentDrawableState(int i) {
            KeyState keyState = new KeyState();
            keyState.add(16842910);
            if (this.disabled) {
                keyState.remove(16842910);
            }
            if (!(!targetLayoutIdEquals(i) || this.iconRef == R.drawable.ic_arrow_left_circle_outline_24 || this.iconRef == R.drawable.ic_arrow_right_circle_outline_24)) {
                keyState.add(16842919);
            }
            if (this.hoveredOver) {
                keyState.add(16843623);
            }
            if (this.pressed) {
                keyState.add(16842919);
            }
            if (this.sticky) {
                keyState.add(16842911);
                if (this.on) {
                    keyState.add(16842912);
                }
            }
            return keyState.asArray();
        }

        private boolean targetLayoutIdEquals(int i) {
            if (this.keyboardId == i) {
                return true;
            }
            int[][] iArr = EQUIV_LAYOUTS;
            if (iArr.length > 0) {
                int[] iArr2 = iArr[0];
                boolean z = false;
                boolean z2 = false;
                for (int i2 : iArr2) {
                    if (this.keyboardId == i2) {
                        z = true;
                    } else if (i == i2) {
                        z2 = true;
                    }
                    if (z && z2) {
                        return true;
                    }
                }
            }
            return false;
        }

        @Override // com.oculus.panelapp.keyboardv2.Keyboard.KeyLayout
        public int getX() {
            return this.x;
        }

        @Override // com.oculus.panelapp.keyboardv2.Keyboard.KeyLayout
        public int getY() {
            return this.y;
        }

        @Override // com.oculus.panelapp.keyboardv2.Keyboard.KeyLayout
        public int getWidth() {
            return this.width;
        }

        @Override // com.oculus.panelapp.keyboardv2.Keyboard.KeyLayout
        public int getHeight() {
            return this.height;
        }

        public Keyboard getKeyboard() {
            return this.keyboard;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(Key.class.getCanonicalName());
            sb.append("\n\tlabel: ");
            sb.append(this.label);
            sb.append("\n\tshiftLabel: ");
            sb.append(this.shiftLabel);
            sb.append("\n\tcodes: ");
            List<Integer> list = this.codes;
            sb.append(list != null ? list.toString() : LoggingUtil.NO_HASHCODE);
            sb.append("\n\tx: ");
            sb.append(this.x);
            sb.append("\n\ty: ");
            sb.append(this.y);
            sb.append("\n\tmXMode: ");
            sb.append(this.mXMode.name());
            sb.append("\n\twidth: ");
            sb.append(this.width);
            sb.append("\n\theight: ");
            sb.append(this.height);
            sb.append("\n\tmWidthMode: ");
            sb.append(this.mWidthMode.name());
            return sb.toString();
        }

        private Typeface loadFont(Context context, String str) {
            if (this.keyboard.mFontMap.containsKey(str)) {
                return (Typeface) this.keyboard.mFontMap.get(str);
            }
            Typeface createFromAsset = Typeface.createFromAsset(context.getAssets(), str);
            this.keyboard.mFontMap.put(str, createFromAsset);
            return createFromAsset;
        }
    }

    public Keyboard(Context context, int i, ActionType actionType, KeyboardLocale keyboardLocale, KeyboardSize keyboardSize, KeyboardPanelApp keyboardPanelApp, boolean z) {
        this(context, i, 0, actionType, keyboardLocale, keyboardSize, keyboardPanelApp, z);
    }

    public Keyboard(Context context, int i, int i2, int i3, int i4, ActionType actionType, KeyboardLocale keyboardLocale, KeyboardSize keyboardSize, KeyboardPanelApp keyboardPanelApp, boolean z) {
        this.mShiftState = ShiftState.OFF;
        this.mShiftKeyIndices = new int[]{-1, -1};
        this.mLayoutInvalid = false;
        this.mFontMap = new HashMap();
        this.mRows = new ArrayList<>();
        this.mDefaultLocale = keyboardLocale;
        this.mDefaultKeyboardSize = keyboardSize;
        this.mActionType = actionType;
        this.mIsAssistantInstalled = z;
        this.mDisplayWidth = i3;
        this.mDisplayHeight = i4;
        this.mXmlLayoutResId = i;
        this.mPanelApp = keyboardPanelApp;
        this.mDefaultHorizontalGap = 0;
        this.mDefaultWidth = this.mDisplayWidth / 10;
        this.mDefaultVerticalGap = 0;
        this.mDefaultHeight = this.mDefaultWidth;
        this.mKeys = new ArrayList();
        this.mDynamicKeyAreaMap = new HashMap<>();
        this.mKeyboardMode = i2;
        loadKeyboard(context, context.getResources().getXml(this.mXmlLayoutResId));
        fixupRemoteInput();
    }

    public Keyboard(Context context, int i, int i2, ActionType actionType, KeyboardLocale keyboardLocale, KeyboardSize keyboardSize, KeyboardPanelApp keyboardPanelApp, boolean z) {
        this(context, i, i2, context.getResources().getDisplayMetrics().widthPixels, context.getResources().getDisplayMetrics().heightPixels, actionType, keyboardLocale, keyboardSize, keyboardPanelApp, z);
    }

    public Keyboard(Context context, int i, CharSequence charSequence, int i2, int i3, ActionType actionType, KeyboardLocale keyboardLocale, KeyboardSize keyboardSize, KeyboardPanelApp keyboardPanelApp, boolean z) {
        this(context, i, actionType, keyboardLocale, keyboardSize, keyboardPanelApp, z);
        this.mTotalWidth = 0;
        Row row = new Row(this);
        row.defaultHeight = this.mDefaultHeight;
        row.defaultWidth = this.mDefaultWidth;
        row.defaultHorizontalGap = this.mDefaultHorizontalGap;
        row.verticalGap = this.mDefaultVerticalGap;
        row.rowEdgeFlags = 12;
        int i4 = i2 == -1 ? Integer.MAX_VALUE : i2;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        for (int i8 = 0; i8 < charSequence.length(); i8++) {
            char charAt = charSequence.charAt(i8);
            if (i6 >= i4 || this.mDefaultWidth + i7 + i3 > this.mDisplayWidth) {
                i5 += this.mDefaultVerticalGap + this.mDefaultHeight;
                i6 = 0;
                i7 = 0;
            }
            Key key = new Key(row);
            key.x = i7 + key.gap;
            key.y = i5;
            key.label = String.valueOf(charAt);
            key.codes = new ArrayList();
            key.codes.add(Integer.valueOf(charAt - '0'));
            i6++;
            i7 = key.x + key.width;
            if (key.codes.contains(Integer.valueOf(KeyCode.TRANSCRIBE.value))) {
                this.mTranscribeKey = key;
            }
            if (key.codes.contains(Integer.valueOf(KeyCode.SHIFT.value))) {
                this.mShiftKey = key;
            }
            if (key.codes.contains(Integer.valueOf(KeyCode.ACTION_KEY.value))) {
                this.mActionKey = key;
            }
            this.mKeys.add(key);
            row.mKeys.add(key);
            if (i7 > this.mTotalWidth) {
                this.mTotalWidth = i7;
            }
        }
        this.mTotalHeight = i5 + this.mDefaultHeight;
        this.mRows.add(row);
    }

    public int getLayoutResId() {
        return this.mXmlLayoutResId;
    }

    public void disableKey(int i) {
        for (Key key : this.mKeys) {
            if (i == key.id) {
                key.disabled = true;
            }
        }
    }

    public void disableKeysByTag(String str) {
        if (str != null) {
            for (Key key : this.mKeys) {
                if (key.tag != null && (key.tag instanceof String) && str.equals((String) key.tag)) {
                    key.disabled = true;
                }
            }
        }
    }

    public ArrayList<Row> getRows() {
        return this.mRows;
    }

    public List<Key> getKeys() {
        if (this.mKeys.isEmpty()) {
            Iterator<Row> it = this.mRows.iterator();
            while (it.hasNext()) {
                this.mKeys.addAll(it.next().getKeys());
            }
            for (DynamicKeyArea dynamicKeyArea : this.mDynamicKeyAreaMap.values()) {
                this.mKeys.addAll(dynamicKeyArea.getKeys());
            }
        }
        return this.mKeys;
    }

    public void invalidateLayout() {
        this.mLayoutInvalid = true;
    }

    public boolean isLayoutInvalid() {
        if (!this.mLayoutInvalid) {
            return false;
        }
        this.mLayoutInvalid = false;
        this.mKeys.clear();
        this.mGridNeighbors = null;
        return true;
    }

    public DynamicKeyArea getDynamicKeyArea(int i) {
        return this.mDynamicKeyAreaMap.get(Integer.valueOf(i));
    }

    public Key getTranscribeKey() {
        return this.mTranscribeKey;
    }

    public Key getActionKey() {
        return this.mActionKey;
    }

    /* access modifiers changed from: protected */
    public int getHorizontalGap() {
        return this.mDefaultHorizontalGap;
    }

    /* access modifiers changed from: protected */
    public void setHorizontalGap(int i) {
        this.mDefaultHorizontalGap = i;
    }

    /* access modifiers changed from: protected */
    public int getVerticalGap() {
        return this.mDefaultVerticalGap;
    }

    /* access modifiers changed from: protected */
    public void setVerticalGap(int i) {
        this.mDefaultVerticalGap = i;
    }

    /* access modifiers changed from: protected */
    public int getKeyHeight() {
        return this.mDefaultHeight;
    }

    /* access modifiers changed from: protected */
    public void setKeyHeight(int i) {
        this.mDefaultHeight = i;
    }

    /* access modifiers changed from: protected */
    public int getKeyWidth() {
        return this.mDefaultWidth;
    }

    /* access modifiers changed from: protected */
    public void setKeyWidth(int i) {
        this.mDefaultWidth = i;
    }

    public int getHeight() {
        return this.mTotalHeight;
    }

    public int getMinWidth() {
        return this.mTotalWidth;
    }

    public ColorStateList getDefaultKeyTextColor() {
        return this.mDefaultKeyTextColor;
    }

    public int getDefaultKeyTextSize() {
        return this.mKeyTextSize;
    }

    public int getDefaultKeySubTextSize() {
        return this.mKeySubTextSize;
    }

    public int getDefaultHorizontalGap() {
        return this.mDefaultHorizontalGap;
    }

    public int getDefaultVerticalGap() {
        return this.mDefaultVerticalGap;
    }

    public boolean setShifted(ShiftState shiftState) {
        ShiftState shiftState2 = this.mShiftState;
        boolean z = false;
        if (shiftState == shiftState2) {
            return false;
        }
        boolean z2 = shiftState2 == ShiftState.OFF || shiftState == ShiftState.OFF;
        Key key = this.mShiftKey;
        if (key != null) {
            if (shiftState == ShiftState.CAPS_LOCK) {
                z = true;
            }
            key.on = z;
        }
        this.mShiftState = shiftState;
        return z2;
    }

    public boolean isShifted() {
        return this.mShiftState == ShiftState.ON || this.mShiftState == ShiftState.CAPS_LOCK;
    }

    public ShiftState getShiftState() {
        return this.mShiftState;
    }

    public int[] getShiftKeyIndices() {
        return this.mShiftKeyIndices;
    }

    public int getShiftKeyIndex() {
        return this.mShiftKeyIndices[0];
    }

    private void computeNearestNeighbors() {
        this.mCellWidth = ((getMinWidth() + 10) - 1) / 10;
        this.mCellHeight = ((getHeight() + 5) - 1) / 5;
        this.mGridNeighbors = new int[GRID_SIZE][];
        List<Key> keys = getKeys();
        int[] iArr = new int[keys.size()];
        int i = this.mCellWidth * 10;
        int i2 = this.mCellHeight * 5;
        Rect rect = new Rect();
        Rect rect2 = new Rect();
        int i3 = 0;
        while (i3 < i) {
            int i4 = 0;
            while (i4 < i2) {
                rect.left = i3;
                rect.top = i4;
                rect.right = this.mCellWidth + i3;
                rect.bottom = this.mCellHeight + i4;
                rect2.left = rect.left - this.mProximityThreshold;
                rect2.top = rect.top - this.mProximityThreshold;
                rect2.right = rect.right + this.mProximityThreshold;
                rect2.bottom = rect.bottom + this.mProximityThreshold;
                int i5 = 0;
                for (int i6 = 0; i6 < keys.size(); i6++) {
                    if (Rect.intersects(keys.get(i6).getBounds(), rect2)) {
                        iArr[i5] = i6;
                        i5++;
                    }
                }
                int[] iArr2 = new int[i5];
                System.arraycopy(iArr, 0, iArr2, 0, i5);
                int[][] iArr3 = this.mGridNeighbors;
                int i7 = this.mCellHeight;
                iArr3[((i4 / i7) * 10) + (i3 / this.mCellWidth)] = iArr2;
                i4 += i7;
            }
            i3 += this.mCellWidth;
        }
    }

    public int[] getNearestKeys(int i, int i2) {
        int i3;
        if (this.mGridNeighbors == null) {
            computeNearestNeighbors();
        }
        if (i < 0 || i >= getMinWidth() || i2 < 0 || i2 >= getHeight() || (i3 = ((i2 / this.mCellHeight) * 10) + (i / this.mCellWidth)) >= GRID_SIZE) {
            return new int[0];
        }
        return this.mGridNeighbors[i3];
    }

    public String getIme() {
        String str = this.mIme;
        return str != null ? str : "simple";
    }

    public String getListener() {
        String str = this.mKeyboardActionListener;
        return str != null ? str : "KeyboardPanelApp";
    }

    public int getKeyToHighlight() {
        return this.mKeyToHighlight;
    }

    public void setKeyToHighlight(int i) {
        this.mKeyToHighlight = i;
    }

    /* access modifiers changed from: protected */
    public Row createRowFromXml(Resources resources, XmlResourceParser xmlResourceParser) {
        return new Row(resources, this, xmlResourceParser);
    }

    /* access modifiers changed from: protected */
    public Key createKeyFromXml(Context context, Row row, int i, int i2, XmlResourceParser xmlResourceParser) {
        return new Key(context, row, i, i2, xmlResourceParser, this.mActionType, this.mDefaultLocale, this.mDefaultKeyboardSize);
    }

    /* access modifiers changed from: protected */
    public DynamicKeyArea createDynamicKeyAreaFromXml(Context context, Row row, int i, int i2, XmlResourceParser xmlResourceParser) {
        DynamicKeyArea dynamicKeyArea = new DynamicKeyArea(context, row, i, i2, xmlResourceParser);
        int resourceId = dynamicKeyArea.getResourceId();
        if (resourceId == -1) {
            Log.w("Keyboard", "Missing ID for DynamicKeyArea, will not be addressable");
        } else {
            this.mDynamicKeyAreaMap.put(Integer.valueOf(resourceId), dynamicKeyArea);
        }
        return dynamicKeyArea;
    }

    private void loadKeyboard(Context context, XmlResourceParser xmlResourceParser) {
        Key key;
        Resources resources = context.getResources();
        Key key2 = null;
        Row row = null;
        boolean z = false;
        int i = 0;
        int i2 = 0;
        loop0:
        while (true) {
            int i3 = i2;
            while (true) {
                try {
                    int next = xmlResourceParser.next();
                    if (next == 1) {
                        break loop0;
                    } else if (next == 2) {
                        String name = xmlResourceParser.getName();
                        if (TAG_ROW.equals(name)) {
                            row = createRowFromXml(resources, xmlResourceParser);
                            this.mRows.add(row);
                            if ((row.mode == 0 || row.mode == this.mKeyboardMode) ? false : true) {
                                break;
                            }
                            i3 = 0;
                            i2 = 1;
                        } else {
                            if (TAG_KEY.equals(name)) {
                                Key createKeyFromXml = createKeyFromXml(context, row, i3, i, xmlResourceParser);
                                if (createKeyFromXml.codes.contains(Integer.valueOf(KeyCode.TRANSCRIBE.value))) {
                                    this.mTranscribeKey = createKeyFromXml;
                                }
                                if (createKeyFromXml.codes.contains(Integer.valueOf(KeyCode.SHIFT.value))) {
                                    this.mShiftKey = createKeyFromXml;
                                }
                                if (createKeyFromXml.codes.contains(Integer.valueOf(KeyCode.ACTION_KEY.value))) {
                                    this.mActionKey = createKeyFromXml;
                                }
                                int i4 = createKeyFromXml.width;
                                key = createKeyFromXml;
                                if (i4 >= 0) {
                                    this.mKeys.add(createKeyFromXml);
                                    row.mKeys.add(createKeyFromXml);
                                    key = createKeyFromXml;
                                }
                            } else if (TAG_DYNAMIC_KEY_AREA.equals(name)) {
                                key = createDynamicKeyAreaFromXml(context, row, i3, i, xmlResourceParser);
                            } else if ("Keyboard".equals(name)) {
                                parseKeyboardAttributes(resources, xmlResourceParser);
                            }
                            key2 = key;
                            z = true;
                        }
                    } else if (next == 3) {
                        if (z) {
                            i3 = key2.getX() + key2.getWidth();
                            if (i3 > this.mTotalWidth) {
                                this.mTotalWidth = i3;
                            }
                            z = false;
                        } else if (i2 != 0) {
                            i = i + row.verticalGap + row.defaultHeight;
                            i2 = 0;
                        }
                    }
                } catch (Exception e) {
                    Log.e("Keyboard", "Parse error:" + e);
                    e.printStackTrace();
                }
            }
            skipToEndOfRow(xmlResourceParser);
            i2 = 0;
        }
        this.mTotalHeight = i - this.mDefaultVerticalGap;
    }

    private void skipToEndOfRow(XmlResourceParser xmlResourceParser) throws XmlPullParserException, IOException {
        while (true) {
            int next = xmlResourceParser.next();
            if (next == 1) {
                return;
            }
            if (next == 3 && xmlResourceParser.getName().equals(TAG_ROW)) {
                return;
            }
        }
    }

    private void parseKeyboardAttributes(Resources resources, XmlResourceParser xmlResourceParser) {
        String str;
        TypedArray obtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlResourceParser), R.styleable.Keyboard);
        int i = R.styleable.Keyboard_keyWidth;
        int i2 = this.mDisplayWidth;
        this.mDefaultWidth = getDimensionOrFraction(obtainAttributes, i, i2, i2 / 10, 0, 0, false);
        this.mDefaultHeight = getDimensionOrFraction(obtainAttributes, R.styleable.Keyboard_keyHeight, this.mDisplayHeight, GRID_SIZE, 0, 0, false);
        this.mDefaultHorizontalGap = getDimensionOrFraction(obtainAttributes, R.styleable.Keyboard_horizontalGap, this.mDisplayWidth, 0, 0, 0, true);
        this.mDefaultVerticalGap = getDimensionOrFraction(obtainAttributes, R.styleable.Keyboard_verticalGap, this.mDisplayHeight, 0, 0, 0, true);
        this.mDefaultKeyTextColor = obtainAttributes.getColorStateList(R.styleable.Keyboard_keyTextColor);
        this.mKeyToHighlight = obtainAttributes.getResourceId(R.styleable.Keyboard_keyToHighlight, -1);
        this.mKeyTextSize = obtainAttributes.getDimensionPixelSize(R.styleable.Keyboard_keyTextSize, 26);
        CharSequence text = obtainAttributes.getText(R.styleable.Keyboard_keyFont);
        if (text == null) {
            str = DEFAULT_FONT;
        } else {
            str = text.toString();
        }
        this.mKeyFontFilePath = str;
        this.mKeySubTextSize = obtainAttributes.getDimensionPixelSize(R.styleable.Keyboard_keySubTextSize, 8);
        this.mProximityThreshold = (int) (((float) this.mDefaultWidth) * SEARCH_DISTANCE);
        int i3 = this.mProximityThreshold;
        this.mProximityThreshold = i3 * i3;
        this.mIme = obtainAttributes.getString(R.styleable.Keyboard_ime);
        this.mKeyboardActionListener = obtainAttributes.getString(R.styleable.Keyboard_listener);
        obtainAttributes.recycle();
    }

    public static int getDimensionOrFraction(TypedArray typedArray, int i, int i2, int i3, int i4, int i5, boolean z) {
        TypedValue peekValue = typedArray.peekValue(i);
        if (peekValue == null) {
            return i3;
        }
        if (peekValue.type == 3) {
            return calculateDefaultKeySpan(typedArray.getString(i), i4, i5, z);
        }
        if (peekValue.type == 5) {
            return typedArray.getDimensionPixelOffset(i, i3);
        }
        return peekValue.type == 6 ? Math.round(typedArray.getFraction(i, i2, i2, (float) i3)) : i3;
    }

    public static int calculateDefaultKeySpan(String str, int i, int i2, boolean z) {
        int i3 = z ? i2 : i;
        int i4 = 1;
        if (str == null || !str.startsWith("spanDefaultKeys_")) {
            Log.w("Keyboard", String.format("Invalid specification format: %s", str));
            return i3;
        }
        String substring = str.substring(16);
        try {
            int parseInt = Integer.parseInt(substring);
            if (parseInt < 1) {
                Log.w("Keyboard", "Zero or negative values are not supported for spanDefaultKeys");
                return i3;
            }
            if (!z) {
                i4 = -1;
            }
            return (parseInt * i) + ((i4 + parseInt) * i2);
        } catch (NumberFormatException e) {
            Log.w("Keyboard", String.format("Value \"%s\" could not be parsed as an integer", substring), e);
            return i3;
        }
    }

    private void fixupRemoteInput() {
        if (!this.mPanelApp.isRemoteInputEnabled()) {
            int i = -1;
            int i2 = -1;
            for (int i3 = 0; i3 < this.mKeys.size(); i3++) {
                Key key = this.mKeys.get(i3);
                if (isSpace(key)) {
                    i = i3;
                } else if (isRemoteInput(key)) {
                    i2 = i3;
                }
            }
            if (i >= 0 && i2 >= 0) {
                Key key2 = this.mKeys.get(i);
                key2.width += key2.gap + this.mDefaultWidth;
                this.mKeys.remove(i2);
            }
        }
    }

    private static boolean isRemoteInput(Key key) {
        if (key.codes == null || key.codes.size() <= 0 || key.codes.get(0).intValue() != KeyCode.PHONE_INPUT.value) {
            return false;
        }
        return true;
    }

    private static boolean isSpace(Key key) {
        return key.text != null && key.text.toString().equals(" ");
    }
}
