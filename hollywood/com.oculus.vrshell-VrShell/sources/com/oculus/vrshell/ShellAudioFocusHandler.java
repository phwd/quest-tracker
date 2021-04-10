package com.oculus.vrshell;

import android.content.Context;
import android.media.AudioManager;
import com.oculus.android.exoplayer2.util.MimeTypes;

public class ShellAudioFocusHandler implements AudioManager.OnAudioFocusChangeListener {
    private final Context mContext;
    private int mCurrentFocus;

    public ShellAudioFocusHandler(Context context) {
        this.mContext = context;
    }

    public boolean requestFocus(boolean z) {
        if (((AudioManager) this.mContext.getSystemService(MimeTypes.BASE_TYPE_AUDIO)).requestAudioFocus(this, 3, z ? 3 : 1) == 1) {
            return true;
        }
        return false;
    }

    public void abandonFocus() {
        ((AudioManager) this.mContext.getSystemService(MimeTypes.BASE_TYPE_AUDIO)).abandonAudioFocus(this);
    }

    public boolean hasFocus() {
        int i = this.mCurrentFocus;
        return (i == -1 || i == -2 || i == -3) ? false : true;
    }

    public void onAudioFocusChange(int i) {
        this.mCurrentFocus = i;
    }
}
