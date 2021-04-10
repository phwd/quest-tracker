package com.oculus.panelapp.keyboardv2;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Rect;
import android.util.Xml;
import com.oculus.panelapp.keyboardv2.Keyboard;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DynamicKeyArea implements Keyboard.KeyLayout {
    public static final String TAG = "com.oculus.panelapp.keyboardv2.DynamicKeyArea";
    private int dynamicKeysTotalWidth;
    private final Rect mBounds;
    private final Context mContext;
    private final int mHeight;
    private List<Keyboard.Key> mKeys = new ArrayList();
    private final Keyboard.Row mParentRow;
    private final int mResourceId;
    private final int mWidth;
    private final int mX;
    private final int mY;

    public DynamicKeyArea(Context context, Keyboard.Row row, int i, int i2, XmlResourceParser xmlResourceParser) {
        this.mContext = context;
        this.mParentRow = row;
        this.mY = i2;
        Keyboard keyboard = row.getKeyboard();
        TypedArray obtainAttributes = context.getResources().obtainAttributes(Xml.asAttributeSet(xmlResourceParser), R.styleable.Keyboard);
        this.mWidth = Keyboard.getDimensionOrFraction(obtainAttributes, R.styleable.Keyboard_keyWidth, keyboard.getMinWidth(), row.getDefaultWidth(), row.getDefaultWidth(), row.defaultHorizontalGap, false);
        this.mHeight = Keyboard.getDimensionOrFraction(obtainAttributes, R.styleable.Keyboard_keyHeight, keyboard.getHeight(), row.getDefaultHeight(), row.getDefaultHeight(), keyboard.getDefaultVerticalGap(), false);
        this.mX = i + Keyboard.getDimensionOrFraction(obtainAttributes, R.styleable.Keyboard_horizontalGap, keyboard.getMinWidth(), row.defaultHorizontalGap, row.getDefaultWidth(), row.defaultHorizontalGap, true);
        int i3 = this.mX;
        int i4 = this.mY;
        this.mBounds = new Rect(i3, i4, this.mWidth + i3, this.mHeight + i4);
        this.mResourceId = obtainAttributes.getResourceId(R.styleable.Keyboard_id, 0);
        this.dynamicKeysTotalWidth = -1;
    }

    @Override // com.oculus.panelapp.keyboardv2.Keyboard.KeyLayout
    public int getX() {
        return this.mX;
    }

    @Override // com.oculus.panelapp.keyboardv2.Keyboard.KeyLayout
    public int getY() {
        return this.mY;
    }

    @Override // com.oculus.panelapp.keyboardv2.Keyboard.KeyLayout
    public int getWidth() {
        return this.mWidth;
    }

    @Override // com.oculus.panelapp.keyboardv2.Keyboard.KeyLayout
    public int getHeight() {
        return this.mHeight;
    }

    public int getResourceId() {
        return this.mResourceId;
    }

    public Keyboard.Row getParentRow() {
        return this.mParentRow;
    }

    public Keyboard getKeyboard() {
        return this.mParentRow.getKeyboard();
    }

    @Override // com.oculus.panelapp.keyboardv2.Keyboard.KeyLayout
    public Rect getBounds() {
        return this.mBounds;
    }

    public List<Keyboard.Key> getKeys() {
        return this.mKeys;
    }

    public void clear() {
        this.mKeys.clear();
        this.dynamicKeysTotalWidth = -1;
        getKeyboard().invalidateLayout();
    }

    public void addKey(CharSequence charSequence, CharSequence charSequence2, KeyCode keyCode) {
        Keyboard.Key key = new Keyboard.Key(this.mParentRow);
        key.layoutIndex = this.mKeys.size();
        key.mDynamicArea = Optional.of(this);
        key.codes = new ArrayList();
        key.codes.add(Integer.valueOf(keyCode.getValue()));
        key.y = this.mY;
        key.label = charSequence2;
        key.text = charSequence;
        key.mWidthMode = Keyboard.KeyDimensionMode.FIT_CONTENT;
        key.keyTextColor = getKeyboard().getDefaultKeyTextColor();
        key.keyTextSize = getKeyboard().getDefaultKeyTextSize();
        key.subKeyTextSize = getKeyboard().getDefaultKeySubTextSize();
        if (this.mKeys.isEmpty()) {
            key.x = this.mX;
        } else {
            key.mXMode = Keyboard.KeyPositionMode.FLOW;
        }
        this.mKeys.add(key);
        getKeyboard().invalidateLayout();
    }

    public void invalidateLayout() {
        int size = this.mKeys.size();
        for (int i = 0; i < size; i++) {
            Keyboard.Key key = this.mKeys.get(i);
            if (i > 0) {
                key.mXMode = Keyboard.KeyPositionMode.FLOW;
            }
            key.mWidthMode = Keyboard.KeyDimensionMode.FIT_CONTENT;
        }
        getKeyboard().invalidateLayout();
    }

    public void addKey(CharSequence charSequence, KeyCode keyCode) {
        addKey(charSequence, charSequence, keyCode);
    }

    public void scrollHorizontal(int i) {
        Rect bounds = getBounds();
        if (this.mKeys.size() != 0) {
            Keyboard.Key key = this.mKeys.get(0);
            List<Keyboard.Key> list = this.mKeys;
            Keyboard.Key key2 = list.get(list.size() - 1);
            if (this.dynamicKeysTotalWidth == -1) {
                this.dynamicKeysTotalWidth = (key2.getX() + key2.getWidth()) - key.getX();
            }
            int i2 = bounds.right - bounds.left;
            if (i != 0 && this.dynamicKeysTotalWidth > i2) {
                if (i > 0) {
                    if (key.x == bounds.left) {
                        scrollKeysHorizontallyByDelta(((key2.x - bounds.right) + key2.width) * -1);
                        return;
                    }
                    if (key.x + i > bounds.left) {
                        i = bounds.left - key.x;
                    }
                    scrollKeysHorizontallyByDelta(i);
                } else if (key2.x + key2.width == bounds.right) {
                    scrollKeysHorizontallyByDelta(bounds.left - key.x);
                } else {
                    if (key2.x + key2.width + i < bounds.right) {
                        i = bounds.right - (key2.x + key2.width);
                    }
                    scrollKeysHorizontallyByDelta(i);
                }
            }
        }
    }

    private void scrollKeysHorizontallyByDelta(int i) {
        for (Keyboard.Key key : this.mKeys) {
            key.x += i;
        }
        getKeyboard().invalidateLayout();
    }
}
