package com.facebook.msys.mci;

import X.AnonymousClass0l0;
import android.annotation.SuppressLint;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.simplejni.NativeHolder;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@Immutable
public class Attachment {
    @DoNotStrip
    public final NativeHolder mNativeHolder;

    @DoNotStrip
    public static native NativeHolder initNativeHolder(String str, String str2, Long l, @Nullable String str3, @Nullable Long l2, @Nullable String str4, Long l3, boolean z, boolean z2, @Nullable String str5, @Nullable String str6, @Nullable Long l4, @Nullable String str7, @Nullable String str8, @Nullable String str9, @Nullable Long l5, @Nullable Long l6, @Nullable Long l7, @Nullable Long l8, @Nullable String str10, @Nullable String str11, @Nullable String str12, @Nullable String str13, @Nullable String str14, @Nullable String str15, @Nullable String str16, @Nullable String str17, @Nullable String str18, @Nullable String str19, @Nullable String str20, @Nullable byte[] bArr, @Nullable String str21, @Nullable String str22);

    @DoNotStrip
    private native boolean nativeEquals(Object obj);

    @DoNotStrip
    @Nullable
    public native String getActionUrl();

    @DoNotStrip
    public native String getAttachmentFbid();

    @DoNotStrip
    public native Long getAttachmentIndex();

    @DoNotStrip
    public native Long getAttachmentType();

    @DoNotStrip
    @Nullable
    public native String getCta1Id();

    @DoNotStrip
    @Nullable
    public native String getCta1Title();

    @DoNotStrip
    @Nullable
    public native String getCta2Id();

    @DoNotStrip
    @Nullable
    public native String getCta2Title();

    @DoNotStrip
    @Nullable
    public native String getCta3Id();

    @DoNotStrip
    @Nullable
    public native String getCta3Title();

    @DoNotStrip
    @Nullable
    public native String getDescriptionText();

    @DoNotStrip
    @Nullable
    public native String getFileName();

    @DoNotStrip
    @Nullable
    public native String getFilePath();

    @DoNotStrip
    @Nullable
    public native Long getFileSize();

    @DoNotStrip
    public native boolean getHasMedia();

    @DoNotStrip
    public native boolean getHasXma();

    @DoNotStrip
    @Nullable
    public native String getLocalAssetIdentifier();

    @DoNotStrip
    @Nullable
    public native String getMiniPreview();

    @DoNotStrip
    public native String getOfflineAttachmentId();

    @DoNotStrip
    @Nullable
    public native String getOriginalFileHash();

    @DoNotStrip
    @Nullable
    public native Long getPlayableDurationMs();

    @DoNotStrip
    @Nullable
    public native String getPlayableUrl();

    @DoNotStrip
    @Nullable
    public native String getPlayableUrlMimeType();

    @DoNotStrip
    @Nullable
    public native Long getPreviewHeight();

    @DoNotStrip
    @Nullable
    public native String getPreviewUrl();

    @DoNotStrip
    @Nullable
    public native String getPreviewUrlMimeType();

    @DoNotStrip
    @Nullable
    public native Long getPreviewWidth();

    @DoNotStrip
    @Nullable
    public native String getSourceText();

    @DoNotStrip
    @Nullable
    public native String getSubtitleText();

    @DoNotStrip
    @Nullable
    public native String getTitleText();

    @DoNotStrip
    @Nullable
    public native byte[] getUploadData();

    @DoNotStrip
    @Nullable
    public native Long getXmaLayoutType();

    @DoNotStrip
    @Nullable
    public native Long getXmasTemplateType();

    public native int hashCode();

    public native String toString();

    static {
        AnonymousClass0l0.A06("msysjniinfranosqlite");
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Attachment)) {
            return false;
        }
        return nativeEquals(obj);
    }

    @DoNotStrip
    public Attachment(NativeHolder nativeHolder) {
        this.mNativeHolder = nativeHolder;
    }
}
