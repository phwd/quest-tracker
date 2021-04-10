package com.oculus.socialplatform.tablet.emoji;

import X.AbstractC09371ho;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.infer.annotation.ThreadSafe;
import java.io.File;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
@ThreadSafe
public class MessengerVrFallbackSpanProvider extends AbstractC09371ho {
    public File mFontFile;

    @Override // X.AbstractC09371ho
    @Nullable
    public File getFontFile() {
        return this.mFontFile;
    }

    public MessengerVrFallbackSpanProvider(String str) {
        this.mFontFile = new File(str);
    }
}
