package com.oculus.panelapp.keyboardv2.japanese;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import com.oculus.panelapp.keyboardv2.DynamicKeyArea;
import com.oculus.panelapp.keyboardv2.KeyCode;
import com.oculus.panelapp.keyboardv2.Keyboard;
import com.oculus.panelapp.keyboardv2.KeyboardPanelApp;
import com.oculus.panelapp.keyboardv2.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class KeyboardActionListenerKaomoji extends KeyboardPanelApp.KeyboardActionListenerDefault {
    public static final String TAG = "com.oculus.panelapp.keyboardv2.japanese.KeyboardActionListenerKaomoji";
    private final KeyboardPanelApp mApp;
    private final ArrayList<DynamicKeyArea> mKaomojiRows = new ArrayList<>();
    private final Keyboard mKeyboard;
    private final int mScrollStepSizeInPixels;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public KeyboardActionListenerKaomoji(KeyboardPanelApp keyboardPanelApp, Keyboard keyboard) {
        super();
        Objects.requireNonNull(keyboardPanelApp);
        this.mApp = keyboardPanelApp;
        this.mKeyboard = keyboard;
        this.mScrollStepSizeInPixels = keyboardPanelApp.getContext().getResources().getDimensionPixelSize(R.dimen.scroll_step);
        findKaomojiAreas();
    }

    @Override // com.oculus.panelapp.keyboardv2.KeyboardView.OnKeyboardActionListener, com.oculus.panelapp.keyboardv2.KeyboardPanelApp.KeyboardActionListenerDefault
    public void onPress(String str, boolean z, Keyboard.Key key) {
        if (key != null) {
            KeyCode keyCode = key.codes != null ? KeyCode.get(key.codes.get(0).intValue()) : KeyCode.NONE;
            if (keyCode == KeyCode.JP_SCROLL_LEFT) {
                retreatKaomoji();
            } else if (keyCode == KeyCode.JP_SCROLL_RIGHT) {
                advanceKaomoji();
            } else if (keyCode == KeyCode.JP_SELECT_KAOMOJI) {
                loadKaomojiKeyboard(key.tag);
            } else {
                super.onPress(str, z, key);
            }
        }
    }

    @Override // com.oculus.panelapp.keyboardv2.KeyboardView.OnKeyboardActionListener, com.oculus.panelapp.keyboardv2.KeyboardPanelApp.KeyboardActionListenerDefault
    public void onRelease(String str, boolean z, Keyboard.Key key) {
        if (key != null) {
            super.onRelease(str, z, key);
        }
    }

    private void scrollKaomoji(int i) {
        Iterator<DynamicKeyArea> it = this.mKaomojiRows.iterator();
        while (it.hasNext()) {
            it.next().scrollHorizontal(i);
        }
    }

    private void advanceKaomoji() {
        scrollKaomoji(-this.mScrollStepSizeInPixels);
    }

    private void retreatKaomoji() {
        scrollKaomoji(this.mScrollStepSizeInPixels);
    }

    private void findKaomojiAreas() {
        for (int i : new int[]{R.id.dynamic_row_1, R.id.dynamic_row_2, R.id.dynamic_row_3, R.id.dynamic_row_4, R.id.dynamic_row_5}) {
            this.mKaomojiRows.add(this.mKeyboard.getDynamicKeyArea(i));
        }
    }

    private void loadKaomojiKeyboard(Object obj) {
        Log.d(TAG, String.format("loadKaomojiKeyboard %s", obj.toString()));
        Context context = this.mApp.getContext();
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier(obj.toString(), "array", context.getPackageName());
        Log.d(TAG, String.format("Parsed identifier: %d", Integer.valueOf(identifier)));
        String[] stringArray = resources.getStringArray(identifier);
        clearKaomojiKeyboard();
        populateKaomojiAreas(stringArray);
    }

    private void clearKaomojiKeyboard() {
        Iterator<DynamicKeyArea> it = this.mKaomojiRows.iterator();
        while (it.hasNext()) {
            it.next().clear();
        }
    }

    private void populateKaomojiAreas(String[] strArr) {
        int length = strArr.length;
        int size = this.mKaomojiRows.size();
        for (int i = 0; i < length; i++) {
            this.mKaomojiRows.get(i % size).addKey(strArr[i], KeyCode.NONE);
        }
    }
}
