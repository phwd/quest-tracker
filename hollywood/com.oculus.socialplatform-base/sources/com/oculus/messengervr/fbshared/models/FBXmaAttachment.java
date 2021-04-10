package com.oculus.messengervr.fbshared.models;

import android.annotation.TargetApi;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.messengervr.interfaces.MessengerMessage;
import com.oculus.messengervr.interfaces.XmaAttachment;
import java.util.Objects;
import java.util.Optional;

@TargetApi(25)
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class FBXmaAttachment implements XmaAttachment {
    public static final String XMA_WEB_URL = "xma_web_url";
    @Nullable
    public final String mAttachmentFbid;
    public final MessengerMessage.AttachmentType mAttachmentType;
    @Nullable
    public final String mDefaultCtaActionContentBlob;
    @Nullable
    public final String mDefaultCtaActionUrl;
    @Nullable
    public final Long mDefaultCtaTargetId;
    @Nullable
    public final String mDefaultCtaType;
    @Nullable
    public final String mFaviconUrl;
    @Nullable
    public final Integer mPreviewHeight;
    @Nullable
    public final String mPreviewUrl;
    @Nullable
    public final Integer mPreviewWidth;
    @Nullable
    public final String mSubtitleText;
    @Nullable
    public final String mTitleText;

    public static final class Builder {
        @Nullable
        public String mAttachmentFbid;
        @Nullable
        public String mDefaultCtaActionContentBlob;
        @Nullable
        public String mDefaultCtaActionUrl;
        @Nullable
        public Long mDefaultCtaTargetId;
        @Nullable
        public String mDefaultCtaType;
        @Nullable
        public String mFaviconUrl;
        @Nullable
        public Integer mPreviewHeight;
        @Nullable
        public String mPreviewUrl;
        @Nullable
        public Integer mPreviewWidth;
        @Nullable
        public String mSubtitleText;
        @Nullable
        public String mTitleText;

        public FBXmaAttachment build() {
            return new FBXmaAttachment(this.mAttachmentFbid, this.mTitleText, this.mSubtitleText, this.mFaviconUrl, this.mPreviewUrl, this.mPreviewHeight, this.mPreviewWidth, this.mDefaultCtaTargetId, this.mDefaultCtaType, this.mDefaultCtaActionUrl, this.mDefaultCtaActionContentBlob);
        }

        public Builder setAttachmentFbid(@Nullable String str) {
            this.mAttachmentFbid = str;
            return this;
        }

        public Builder setDefaultCtaActionContentBlob(@Nullable String str) {
            this.mDefaultCtaActionContentBlob = str;
            return this;
        }

        public Builder setDefaultCtaActionUrl(@Nullable String str) {
            this.mDefaultCtaActionUrl = str;
            return this;
        }

        public Builder setDefaultCtaTargetId(@Nullable Long l) {
            this.mDefaultCtaTargetId = l;
            return this;
        }

        public Builder setDefaultCtaType(@Nullable String str) {
            this.mDefaultCtaType = str;
            return this;
        }

        public Builder setFaviconUrl(@Nullable String str) {
            this.mFaviconUrl = str;
            return this;
        }

        public Builder setPreviewHeight(@Nullable Integer num) {
            this.mPreviewHeight = num;
            return this;
        }

        public Builder setPreviewUrl(@Nullable String str) {
            this.mPreviewUrl = str;
            return this;
        }

        public Builder setPreviewWidth(@Nullable Integer num) {
            this.mPreviewWidth = num;
            return this;
        }

        public Builder setSubtitleText(@Nullable String str) {
            this.mSubtitleText = str;
            return this;
        }

        public Builder setTitleText(@Nullable String str) {
            this.mTitleText = str;
            return this;
        }

        public Builder() {
        }

        public /* synthetic */ Builder(AnonymousClass1 r1) {
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override // com.oculus.messengervr.interfaces.XmaAttachment
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof FBXmaAttachment)) {
            return false;
        }
        FBXmaAttachment fBXmaAttachment = (FBXmaAttachment) obj;
        if (!getAttachmentFbid().equals(fBXmaAttachment.getAttachmentFbid()) || !getTitleText().equals(fBXmaAttachment.getTitleText()) || !getSubtitleText().equals(fBXmaAttachment.getSubtitleText()) || !getFaviconUrl().equals(fBXmaAttachment.getFaviconUrl()) || !getPreviewUrl().equals(fBXmaAttachment.getPreviewUrl()) || !getPreviewHeight().equals(fBXmaAttachment.getPreviewHeight()) || !getPreviewWidth().equals(fBXmaAttachment.getPreviewWidth()) || !getDefaultCtaTargetId().equals(fBXmaAttachment.getDefaultCtaTargetId()) || !getDefaultCtaType().equals(fBXmaAttachment.getDefaultCtaType()) || !getDefaultCtaActionUrl().equals(fBXmaAttachment.getDefaultCtaActionUrl()) || !getDefaultCtaActionContentBlob().equals(fBXmaAttachment.getDefaultCtaActionContentBlob())) {
            return false;
        }
        return true;
    }

    @Override // com.oculus.messengervr.interfaces.XmaAttachment
    public Optional<String> getAttachmentFbid() {
        return Optional.ofNullable(this.mAttachmentFbid);
    }

    @Override // com.oculus.messengervr.interfaces.XmaAttachment
    public MessengerMessage.AttachmentType getAttachmentType() {
        return this.mAttachmentType;
    }

    @Override // com.oculus.messengervr.interfaces.XmaAttachment
    public Optional<String> getDefaultCtaActionContentBlob() {
        return Optional.ofNullable(this.mDefaultCtaActionContentBlob);
    }

    @Override // com.oculus.messengervr.interfaces.XmaAttachment
    public Optional<String> getDefaultCtaActionUrl() {
        return Optional.ofNullable(this.mDefaultCtaActionUrl);
    }

    @Override // com.oculus.messengervr.interfaces.XmaAttachment
    public Optional<Long> getDefaultCtaTargetId() {
        return Optional.ofNullable(this.mDefaultCtaTargetId);
    }

    @Override // com.oculus.messengervr.interfaces.XmaAttachment
    public Optional<String> getDefaultCtaType() {
        return Optional.ofNullable(this.mDefaultCtaType);
    }

    @Override // com.oculus.messengervr.interfaces.XmaAttachment
    public Optional<String> getFaviconUrl() {
        return Optional.ofNullable(this.mFaviconUrl);
    }

    @Override // com.oculus.messengervr.interfaces.XmaAttachment
    public Optional<Integer> getPreviewHeight() {
        return Optional.ofNullable(this.mPreviewHeight);
    }

    @Override // com.oculus.messengervr.interfaces.XmaAttachment
    public Optional<String> getPreviewUrl() {
        return Optional.ofNullable(this.mPreviewUrl);
    }

    @Override // com.oculus.messengervr.interfaces.XmaAttachment
    public Optional<Integer> getPreviewWidth() {
        return Optional.ofNullable(this.mPreviewWidth);
    }

    @Override // com.oculus.messengervr.interfaces.XmaAttachment
    public Optional<String> getSubtitleText() {
        return Optional.ofNullable(this.mSubtitleText);
    }

    @Override // com.oculus.messengervr.interfaces.XmaAttachment
    public Optional<String> getTitleText() {
        return Optional.ofNullable(this.mTitleText);
    }

    @Override // com.oculus.messengervr.interfaces.XmaAttachment
    public int hashCode() {
        return Objects.hash(this.mAttachmentFbid, this.mTitleText, this.mSubtitleText, this.mFaviconUrl, this.mPreviewUrl, this.mPreviewHeight, this.mPreviewWidth, this.mDefaultCtaTargetId, this.mDefaultCtaType, this.mDefaultCtaActionUrl, this.mDefaultCtaActionContentBlob);
    }

    public FBXmaAttachment(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable Integer num, @Nullable Integer num2, @Nullable Long l, @Nullable String str6, @Nullable String str7, @Nullable String str8) {
        this.mAttachmentType = MessengerMessage.AttachmentType.XMA;
        this.mAttachmentFbid = str;
        this.mTitleText = str2;
        this.mSubtitleText = str3;
        this.mFaviconUrl = str4;
        this.mPreviewUrl = str5;
        this.mPreviewHeight = num;
        this.mPreviewWidth = num2;
        this.mDefaultCtaTargetId = l;
        this.mDefaultCtaType = str6;
        this.mDefaultCtaActionUrl = str7;
        this.mDefaultCtaActionContentBlob = str8;
    }
}
