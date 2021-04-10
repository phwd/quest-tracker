package androidx.core.app;

import X.AnonymousClass02C;
import X.AnonymousClass03h;
import X.AnonymousClass03m;
import X.AnonymousClass03p;
import X.AnonymousClass03z;
import X.AnonymousClass040;
import X.AnonymousClass06K;
import X.AnonymousClass06S;
import android.app.Notification;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NotificationCompat$MessagingStyle extends AnonymousClass03p {
    public static final int MAXIMUM_RETAINED_MESSAGES = 25;
    @Nullable
    public CharSequence mConversationTitle;
    @Nullable
    public Boolean mIsGroupConversation;
    public final List<AnonymousClass03m> mMessages = new ArrayList();
    public AnonymousClass040 mUser;

    @Nullable
    public static NotificationCompat$MessagingStyle extractMessagingStyleFromNotification(Notification notification) {
        Bundle bundle = notification.extras;
        if (bundle != null && !bundle.containsKey("android.selfDisplayName") && !bundle.containsKey("android.messagingStyleUser")) {
            return null;
        }
        try {
            NotificationCompat$MessagingStyle notificationCompat$MessagingStyle = new NotificationCompat$MessagingStyle();
            notificationCompat$MessagingStyle.restoreFromCompatExtras(bundle);
            return notificationCompat$MessagingStyle;
        } catch (ClassCastException unused) {
            return null;
        }
    }

    @Nullable
    private AnonymousClass03m findLatestIncomingMessage() {
        int size = this.mMessages.size();
        while (true) {
            size--;
            if (size >= 0) {
                AnonymousClass03m r1 = this.mMessages.get(size);
                AnonymousClass040 r0 = r1.A04;
                if (r0 != null && !TextUtils.isEmpty(r0.A01)) {
                    return r1;
                }
            } else if (this.mMessages.isEmpty()) {
                return null;
            } else {
                List<AnonymousClass03m> list = this.mMessages;
                return list.get(list.size() - 1);
            }
        }
    }

    private boolean hasMessagesWithoutSender() {
        for (int size = this.mMessages.size() - 1; size >= 0; size--) {
            AnonymousClass040 r0 = this.mMessages.get(size).A04;
            if (r0 != null && r0.A01 == null) {
                return true;
            }
        }
        return false;
    }

    @Deprecated
    public CharSequence getUserDisplayName() {
        return this.mUser.A01;
    }

    public boolean isGroupConversation() {
        AnonymousClass03h r0 = this.mBuilder;
        if (r0 == null || r0.A0B.getApplicationInfo().targetSdkVersion >= 28 || this.mIsGroupConversation != null) {
            Boolean bool = this.mIsGroupConversation;
            if (bool != null) {
                return bool.booleanValue();
            }
            return false;
        } else if (this.mConversationTitle != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override // X.AnonymousClass03p
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public void restoreFromCompatExtras(Bundle bundle) {
        AnonymousClass040 r0;
        this.mMessages.clear();
        if (bundle.containsKey("android.messagingStyleUser")) {
            r0 = AnonymousClass040.A01(bundle.getBundle("android.messagingStyleUser"));
        } else {
            AnonymousClass03z r1 = new AnonymousClass03z();
            r1.A01 = bundle.getString("android.selfDisplayName");
            r0 = new AnonymousClass040(r1);
        }
        this.mUser = r0;
        CharSequence charSequence = bundle.getCharSequence("android.conversationTitle");
        this.mConversationTitle = charSequence;
        if (charSequence == null) {
            this.mConversationTitle = bundle.getCharSequence("android.hiddenConversationTitle");
        }
        Parcelable[] parcelableArray = bundle.getParcelableArray("android.messages");
        if (parcelableArray != null) {
            this.mMessages.addAll(AnonymousClass03m.A00(parcelableArray));
        }
        if (bundle.containsKey("android.isGroupConversation")) {
            this.mIsGroupConversation = Boolean.valueOf(bundle.getBoolean("android.isGroupConversation"));
        }
    }

    @NonNull
    private TextAppearanceSpan makeFontColorSpan(int i) {
        return new TextAppearanceSpan(null, 0, 0, ColorStateList.valueOf(i), null);
    }

    private CharSequence makeMessageLine(AnonymousClass03m r8) {
        AnonymousClass06K r6;
        CharSequence charSequence;
        boolean z = true;
        if (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) != 1) {
            z = false;
        }
        AnonymousClass06S r0 = AnonymousClass06K.A05;
        if (r0 != r0) {
            r6 = new AnonymousClass06K(z, r0);
        } else if (z) {
            r6 = AnonymousClass06K.A04;
        } else {
            r6 = AnonymousClass06K.A03;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        int i = -16777216;
        AnonymousClass040 r02 = r8.A04;
        CharSequence charSequence2 = "";
        if (r02 == null) {
            charSequence = charSequence2;
        } else {
            charSequence = r02.A01;
        }
        if (TextUtils.isEmpty(charSequence)) {
            charSequence = this.mUser.A01;
            int i2 = this.mBuilder.A05;
            if (i2 != 0) {
                i = i2;
            }
        }
        CharSequence A02 = r6.A02(charSequence);
        spannableStringBuilder.append(A02);
        spannableStringBuilder.setSpan(makeFontColorSpan(i), spannableStringBuilder.length() - A02.length(), spannableStringBuilder.length(), 33);
        CharSequence charSequence3 = r8.A05;
        if (charSequence3 != null) {
            charSequence2 = charSequence3;
        }
        spannableStringBuilder.append((CharSequence) "  ").append(r6.A02(charSequence2));
        return spannableStringBuilder;
    }

    @Override // X.AnonymousClass03p
    public void addCompatExtras(Bundle bundle) {
        super.addCompatExtras(bundle);
        bundle.putCharSequence("android.selfDisplayName", this.mUser.A01);
        bundle.putBundle("android.messagingStyleUser", this.mUser.A03());
        bundle.putCharSequence("android.hiddenConversationTitle", this.mConversationTitle);
        CharSequence charSequence = this.mConversationTitle;
        if (charSequence != null && this.mIsGroupConversation.booleanValue()) {
            bundle.putCharSequence("android.conversationTitle", charSequence);
        }
        if (!this.mMessages.isEmpty()) {
            bundle.putParcelableArray("android.messages", AnonymousClass03m.A01(this.mMessages));
        }
        Boolean bool = this.mIsGroupConversation;
        if (bool != null) {
            bundle.putBoolean("android.isGroupConversation", bool.booleanValue());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00a5, code lost:
        if (r4 != null) goto L_0x00a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00c2, code lost:
        if (hasMessagesWithoutSender() != false) goto L_0x00c4;
     */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00ce  */
    @Override // X.AnonymousClass03p
    @androidx.annotation.RestrictTo({X.AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void apply(X.AnonymousClass03Y r12) {
        /*
        // Method dump skipped, instructions count: 284
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.app.NotificationCompat$MessagingStyle.apply(X.03Y):void");
    }

    @Nullable
    public CharSequence getConversationTitle() {
        return this.mConversationTitle;
    }

    public List<AnonymousClass03m> getMessages() {
        return this.mMessages;
    }

    public AnonymousClass040 getUser() {
        return this.mUser;
    }

    public NotificationCompat$MessagingStyle setGroupConversation(boolean z) {
        this.mIsGroupConversation = Boolean.valueOf(z);
        return this;
    }

    public NotificationCompat$MessagingStyle setConversationTitle(@Nullable CharSequence charSequence) {
        this.mConversationTitle = charSequence;
        return this;
    }

    public NotificationCompat$MessagingStyle() {
    }

    public NotificationCompat$MessagingStyle(@NonNull AnonymousClass040 r3) {
        if (!TextUtils.isEmpty(r3.A01)) {
            this.mUser = r3;
            return;
        }
        throw new IllegalArgumentException("User's name must not be empty.");
    }

    @Deprecated
    public NotificationCompat$MessagingStyle(@NonNull CharSequence charSequence) {
        AnonymousClass03z r1 = new AnonymousClass03z();
        r1.A01 = charSequence;
        this.mUser = new AnonymousClass040(r1);
    }

    public NotificationCompat$MessagingStyle addMessage(AnonymousClass03m r3) {
        this.mMessages.add(r3);
        if (this.mMessages.size() > 25) {
            this.mMessages.remove(0);
        }
        return this;
    }

    public NotificationCompat$MessagingStyle addMessage(CharSequence charSequence, long j, AnonymousClass040 r5) {
        addMessage(new AnonymousClass03m(charSequence, j, r5));
        return this;
    }

    @Deprecated
    public NotificationCompat$MessagingStyle addMessage(CharSequence charSequence, long j, CharSequence charSequence2) {
        List<AnonymousClass03m> list = this.mMessages;
        AnonymousClass03z r0 = new AnonymousClass03z();
        r0.A01 = charSequence2;
        list.add(new AnonymousClass03m(charSequence, j, new AnonymousClass040(r0)));
        if (this.mMessages.size() > 25) {
            this.mMessages.remove(0);
        }
        return this;
    }
}
