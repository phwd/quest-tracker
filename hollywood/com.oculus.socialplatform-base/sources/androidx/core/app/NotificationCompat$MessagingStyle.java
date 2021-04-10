package androidx.core.app;

import X.AbstractC000803d;
import X.AnonymousClass02C;
import X.AnonymousClass03m;
import X.AnonymousClass03r;
import X.AnonymousClass03u;
import X.AnonymousClass044;
import X.AnonymousClass045;
import X.AnonymousClass06Q;
import X.AnonymousClass06Y;
import android.app.Notification;
import android.content.res.ColorStateList;
import android.os.Build;
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

public class NotificationCompat$MessagingStyle extends AnonymousClass03u {
    public static final int MAXIMUM_RETAINED_MESSAGES = 25;
    @Nullable
    public CharSequence mConversationTitle;
    @Nullable
    public Boolean mIsGroupConversation;
    public final List<AnonymousClass03r> mMessages = new ArrayList();
    public AnonymousClass045 mUser;

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
    private AnonymousClass03r findLatestIncomingMessage() {
        int size = this.mMessages.size();
        while (true) {
            size--;
            if (size >= 0) {
                AnonymousClass03r r1 = this.mMessages.get(size);
                AnonymousClass045 r0 = r1.A04;
                if (r0 != null && !TextUtils.isEmpty(r0.A01)) {
                    return r1;
                }
            } else if (this.mMessages.isEmpty()) {
                return null;
            } else {
                List<AnonymousClass03r> list = this.mMessages;
                return list.get(list.size() - 1);
            }
        }
    }

    private boolean hasMessagesWithoutSender() {
        for (int size = this.mMessages.size() - 1; size >= 0; size--) {
            AnonymousClass045 r0 = this.mMessages.get(size).A04;
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
        AnonymousClass03m r0 = this.mBuilder;
        if (r0 == null || r0.A08.getApplicationInfo().targetSdkVersion >= 28 || this.mIsGroupConversation != null) {
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

    @Override // X.AnonymousClass03u
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public void restoreFromCompatExtras(Bundle bundle) {
        AnonymousClass045 r0;
        this.mMessages.clear();
        if (bundle.containsKey("android.messagingStyleUser")) {
            r0 = AnonymousClass045.A01(bundle.getBundle("android.messagingStyleUser"));
        } else {
            AnonymousClass044 r1 = new AnonymousClass044();
            r1.A01 = bundle.getString("android.selfDisplayName");
            r0 = new AnonymousClass045(r1);
        }
        this.mUser = r0;
        CharSequence charSequence = bundle.getCharSequence("android.conversationTitle");
        this.mConversationTitle = charSequence;
        if (charSequence == null) {
            this.mConversationTitle = bundle.getCharSequence("android.hiddenConversationTitle");
        }
        Parcelable[] parcelableArray = bundle.getParcelableArray("android.messages");
        if (parcelableArray != null) {
            this.mMessages.addAll(AnonymousClass03r.A00(parcelableArray));
        }
        if (bundle.containsKey("android.isGroupConversation")) {
            this.mIsGroupConversation = Boolean.valueOf(bundle.getBoolean("android.isGroupConversation"));
        }
    }

    @NonNull
    private TextAppearanceSpan makeFontColorSpan(int i) {
        return new TextAppearanceSpan(null, 0, 0, ColorStateList.valueOf(i), null);
    }

    private CharSequence makeMessageLine(AnonymousClass03r r8) {
        AnonymousClass06Q r6;
        CharSequence charSequence;
        boolean z = true;
        if (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) != 1) {
            z = false;
        }
        AnonymousClass06Y r0 = AnonymousClass06Q.A05;
        if (r0 != r0) {
            r6 = new AnonymousClass06Q(z, r0);
        } else if (z) {
            r6 = AnonymousClass06Q.A04;
        } else {
            r6 = AnonymousClass06Q.A03;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        int i = -16777216;
        AnonymousClass045 r02 = r8.A04;
        CharSequence charSequence2 = "";
        if (r02 == null) {
            charSequence = charSequence2;
        } else {
            charSequence = r02.A01;
        }
        if (TextUtils.isEmpty(charSequence)) {
            charSequence = this.mUser.A01;
            int i2 = this.mBuilder.A04;
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

    @Override // X.AnonymousClass03u
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
            bundle.putParcelableArray("android.messages", AnonymousClass03r.A01(this.mMessages));
        }
        Boolean bool = this.mIsGroupConversation;
        if (bool != null) {
            bundle.putBoolean("android.isGroupConversation", bool.booleanValue());
        }
    }

    @Override // X.AnonymousClass03u
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public void apply(AbstractC000803d r11) {
        Notification.MessagingStyle messagingStyle;
        Notification.MessagingStyle.Message message;
        this.mIsGroupConversation = Boolean.valueOf(isGroupConversation());
        int i = Build.VERSION.SDK_INT;
        AnonymousClass045 r0 = this.mUser;
        if (i >= 28) {
            messagingStyle = new Notification.MessagingStyle(r0.A02());
        } else {
            messagingStyle = new Notification.MessagingStyle(r0.A01);
        }
        if (this.mIsGroupConversation.booleanValue() || i >= 28) {
            messagingStyle.setConversationTitle(this.mConversationTitle);
        }
        if (i >= 28) {
            messagingStyle.setGroupConversation(this.mIsGroupConversation.booleanValue());
        }
        for (AnonymousClass03r r7 : this.mMessages) {
            int i2 = Build.VERSION.SDK_INT;
            AnonymousClass045 r02 = r7.A04;
            if (i2 >= 28) {
                message = new Notification.MessagingStyle.Message(r7.A05, r7.A03, r02 == null ? null : r02.A02());
            } else {
                message = new Notification.MessagingStyle.Message(r7.A05, r7.A03, r02 != null ? r02.A01 : null);
            }
            String str = r7.A02;
            if (str != null) {
                message.setData(str, r7.A00);
            }
            messagingStyle.addMessage(message);
        }
        messagingStyle.setBuilder(r11.A3T());
    }

    @Nullable
    public CharSequence getConversationTitle() {
        return this.mConversationTitle;
    }

    public List<AnonymousClass03r> getMessages() {
        return this.mMessages;
    }

    public AnonymousClass045 getUser() {
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

    public NotificationCompat$MessagingStyle(@NonNull AnonymousClass045 r3) {
        if (!TextUtils.isEmpty(r3.A01)) {
            this.mUser = r3;
            return;
        }
        throw new IllegalArgumentException("User's name must not be empty.");
    }

    @Deprecated
    public NotificationCompat$MessagingStyle(@NonNull CharSequence charSequence) {
        AnonymousClass044 r1 = new AnonymousClass044();
        r1.A01 = charSequence;
        this.mUser = new AnonymousClass045(r1);
    }

    public NotificationCompat$MessagingStyle addMessage(AnonymousClass03r r3) {
        this.mMessages.add(r3);
        if (this.mMessages.size() > 25) {
            this.mMessages.remove(0);
        }
        return this;
    }

    public NotificationCompat$MessagingStyle addMessage(CharSequence charSequence, long j, AnonymousClass045 r5) {
        addMessage(new AnonymousClass03r(charSequence, j, r5));
        return this;
    }

    @Deprecated
    public NotificationCompat$MessagingStyle addMessage(CharSequence charSequence, long j, CharSequence charSequence2) {
        List<AnonymousClass03r> list = this.mMessages;
        AnonymousClass044 r0 = new AnonymousClass044();
        r0.A01 = charSequence2;
        list.add(new AnonymousClass03r(charSequence, j, new AnonymousClass045(r0)));
        if (this.mMessages.size() > 25) {
            this.mMessages.remove(0);
        }
        return this;
    }
}
