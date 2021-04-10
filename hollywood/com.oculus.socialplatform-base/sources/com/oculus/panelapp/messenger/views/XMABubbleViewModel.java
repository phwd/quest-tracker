package com.oculus.panelapp.messenger.views;

import X.AnonymousClass1uc;
import androidx.databinding.Bindable;
import com.oculus.common.logutilities.LoggingUtil;

public class XMABubbleViewModel extends AnonymousClass1uc {
    public static final String TAG = LoggingUtil.tag(XMABubbleViewModel.class);
    public String mAttachmentSubtitle;
    public String mAttachmentTitle;
    public String mMessageDisplayText;
    public boolean mShouldShowFavicon;

    @Bindable
    public String getAttachmentSubtitle() {
        return this.mAttachmentSubtitle;
    }

    @Bindable
    public String getAttachmentTitle() {
        return this.mAttachmentTitle;
    }

    @Bindable
    public String getMessageDisplayText() {
        return this.mMessageDisplayText;
    }

    @Bindable
    public int getShouldShowDisplayText() {
        String str = this.mMessageDisplayText;
        if (str == null || str.isEmpty()) {
            return 8;
        }
        return 0;
    }

    @Bindable
    public int getShouldShowFavicon() {
        if (this.mShouldShowFavicon) {
            return 0;
        }
        return 8;
    }

    public void setAttachmentSubtitle(String str) {
        this.mAttachmentSubtitle = str;
        notifyPropertyChanged(126);
    }

    public void setAttachmentTitle(String str) {
        this.mAttachmentTitle = str;
        notifyPropertyChanged(136);
    }

    public void setMessageDisplayText(String str) {
        this.mMessageDisplayText = str;
        notifyPropertyChanged(122);
        notifyPropertyChanged(140);
    }

    public void setShouldShowFavicon(boolean z) {
        this.mShouldShowFavicon = z;
        notifyPropertyChanged(119);
    }
}
