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
public class MediaSendContentInfo {
    @DoNotStrip
    public final NativeHolder mNativeHolder;

    @DoNotStrip
    public static native NativeHolder initNativeHolder(@Nullable byte[] bArr, @Nullable String str, @Nullable String str2, String str3, String str4, String str5, Long l, String str6, String str7, @Nullable String str8, @Nullable String str9, @Nullable String str10, @Nullable Long l2, @Nullable Long l3, @Nullable byte[] bArr2);

    @DoNotStrip
    private native boolean nativeEquals(Object obj);

    @DoNotStrip
    @Nullable
    public native Long getAttachmentDurationMs();

    @DoNotStrip
    public native Long getAttachmentType();

    @DoNotStrip
    @Nullable
    public native String getCaptionText();

    @DoNotStrip
    @Nullable
    public native byte[] getEffects();

    @DoNotStrip
    public native String getFileName();

    @DoNotStrip
    @Nullable
    public native String getFilePath();

    @DoNotStrip
    @Nullable
    public native String getLocalAssetIdentifier();

    @DoNotStrip
    @Nullable
    public native Long getMessageSource();

    @DoNotStrip
    public native String getMimeType();

    @DoNotStrip
    public native String getOfflineAttachmentId();

    @DoNotStrip
    public native String getOfflineThreadingId();

    @DoNotStrip
    @Nullable
    public native String getOriginalFileHash();

    @DoNotStrip
    public native String getRecipientId();

    @DoNotStrip
    @Nullable
    public native String getReplySourceId();

    @DoNotStrip
    @Nullable
    public native byte[] getUploadData();

    public native int hashCode();

    public native String toString();

    static {
        AnonymousClass0l0.A06("msysjniinfranosqlite");
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof MediaSendContentInfo)) {
            return false;
        }
        return nativeEquals(obj);
    }

    @DoNotStrip
    public MediaSendContentInfo(NativeHolder nativeHolder) {
        this.mNativeHolder = nativeHolder;
    }
}
