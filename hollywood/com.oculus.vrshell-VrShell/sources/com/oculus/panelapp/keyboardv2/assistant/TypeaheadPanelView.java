package com.oculus.panelapp.keyboardv2.assistant;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import com.oculus.panelapp.keyboardv2.ActionType;
import com.oculus.panelapp.keyboardv2.KeyCode;
import com.oculus.panelapp.keyboardv2.Keyboard;
import com.oculus.panelapp.keyboardv2.KeyboardLocale;
import com.oculus.panelapp.keyboardv2.KeyboardPanelApp;
import com.oculus.panelapp.keyboardv2.KeyboardSize;
import com.oculus.panelapp.keyboardv2.KeyboardView;
import com.oculus.panelapp.keyboardv2.R;
import java.util.ArrayList;
import java.util.List;

public class TypeaheadPanelView extends LinearLayout {
    private static final String EMPTY_TYPEAHEAD_OUTPUT = "";
    private static final String TAG = "TypeaheadPanelView";
    private KeyboardPanelApp mPanelApp;
    private List<Keyboard.Key> mTypeaheadKeys = new ArrayList();
    private KeyboardView mTypeaheadSubView;

    public TypeaheadPanelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Log.i(TAG, "TypeaheadPanelView created");
    }

    public void initialize(KeyboardView.OnKeyboardActionListener onKeyboardActionListener) {
        this.mTypeaheadSubView = (KeyboardView) findViewById(R.id.keyboard_typeahead_view);
        this.mTypeaheadSubView.setOnKeyboardActionListener(onKeyboardActionListener);
        setVisibility(8);
    }

    public void setTypeaheadView(Context context, ActionType actionType, KeyboardLocale keyboardLocale, KeyboardSize keyboardSize, KeyboardPanelApp keyboardPanelApp) {
        int i;
        this.mPanelApp = keyboardPanelApp;
        if (keyboardSize == KeyboardSize.LARGE) {
            i = R.xml.typeahead_48_48;
        } else {
            i = R.xml.typeahead_32_32;
        }
        Keyboard keyboard = new Keyboard(context, i, actionType, keyboardLocale, keyboardSize, keyboardPanelApp, false);
        this.mTypeaheadSubView.setKeyboard(keyboard);
        this.mTypeaheadKeys.clear();
        for (Keyboard.Key key : keyboard.getKeys()) {
            if (KeyCode.WORD_PREDICTION.value == key.codes.get(0).intValue()) {
                this.mTypeaheadKeys.add(key);
            }
        }
    }

    public void clear() {
        for (Keyboard.Key key : this.mTypeaheadKeys) {
            key.label = "";
            key.text = "";
        }
        invalidateKeys();
    }

    public void setTypeaheadSuggestions(String str, String[] strArr) {
        if (str.isEmpty()) {
            clear();
            return;
        }
        int i = 0;
        this.mTypeaheadKeys.get(0).label = str;
        this.mTypeaheadKeys.get(0).text = str;
        int min = Math.min(this.mTypeaheadKeys.size() - 1, strArr.length);
        while (i < min) {
            int i2 = i + 1;
            this.mTypeaheadKeys.get(i2).label = strArr[i];
            this.mTypeaheadKeys.get(i2).text = strArr[i];
            i = i2;
        }
        while (true) {
            min++;
            if (min < this.mTypeaheadKeys.size()) {
                this.mTypeaheadKeys.get(min).label = "";
                this.mTypeaheadKeys.get(min).text = "";
            } else {
                invalidateKeys();
                return;
            }
        }
    }

    private void invalidateKeys() {
        for (Keyboard.Key key : this.mTypeaheadKeys) {
            this.mTypeaheadSubView.invalidateKey(key);
        }
    }
}
