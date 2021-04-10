package com.oculus.panelapp.keyboardv2;

import android.util.Log;
import java.util.Arrays;

class KeyState {
    private static final int[] STATE_MAP = {16842919, 16842908, 16843623, 16842913, 16842911, 16842912, 16842910, 16843518, 16842909};
    private static final String TAG = "KeyState";
    private final int[] mOutputArray;
    private final int[] mState;

    KeyState() {
        int[] iArr = STATE_MAP;
        this.mState = new int[iArr.length];
        this.mOutputArray = new int[iArr.length];
    }

    public void add(int i) {
        int indexOf = indexOf(i);
        if (indexOf >= 0) {
            this.mState[indexOf] = i;
        }
    }

    public void remove(int i) {
        int indexOf = indexOf(i);
        if (indexOf >= 0) {
            this.mState[indexOf] = 0;
        }
    }

    public int[] asArray() {
        return compact();
    }

    private int[] compact() {
        Arrays.fill(this.mOutputArray, 0);
        int[] iArr = this.mState;
        int i = 0;
        for (int i2 : iArr) {
            if (i2 != 0) {
                this.mOutputArray[i] = i2;
                i++;
            }
        }
        return this.mOutputArray;
    }

    private int indexOf(int i) {
        int i2 = 0;
        while (true) {
            int[] iArr = STATE_MAP;
            if (i2 >= iArr.length) {
                Log.w(TAG, String.format("State value %d is not a valid Drawable state", Integer.valueOf(i)));
                return -1;
            } else if (iArr[i2] == i) {
                return i2;
            } else {
                i2++;
            }
        }
    }
}
