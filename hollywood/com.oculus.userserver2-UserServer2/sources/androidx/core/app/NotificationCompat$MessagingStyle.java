package androidx.core.app;

import X.AnonymousClass2O;
import X.AnonymousClass4I;
import X.AnonymousClass4W;
import X.AnonymousClass4Z;
import X.AnonymousClass4j;
import X.AnonymousClass74;
import X.AnonymousClass7C;
import X.C00114k;
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

public class NotificationCompat$MessagingStyle extends AnonymousClass4Z {
    public static final int MAXIMUM_RETAINED_MESSAGES = 25;
    @Nullable
    public CharSequence mConversationTitle;
    @Nullable
    public Boolean mIsGroupConversation;
    public final List<AnonymousClass4W> mMessages = new ArrayList();
    public C00114k mUser;

    public boolean isGroupConversation() {
        Boolean bool = this.mIsGroupConversation;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

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
    private AnonymousClass4W findLatestIncomingMessage() {
        int size = this.mMessages.size();
        while (true) {
            size--;
            if (size >= 0) {
                AnonymousClass4W r1 = this.mMessages.get(size);
                C00114k r0 = r1.A04;
                if (r0 != null && !TextUtils.isEmpty(r0.A01)) {
                    return r1;
                }
            } else if (this.mMessages.isEmpty()) {
                return null;
            } else {
                List<AnonymousClass4W> list = this.mMessages;
                return list.get(list.size() - 1);
            }
        }
    }

    private boolean hasMessagesWithoutSender() {
        for (int size = this.mMessages.size() - 1; size >= 0; size--) {
            C00114k r0 = this.mMessages.get(size).A04;
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

    @Override // X.AnonymousClass4Z
    @RestrictTo({AnonymousClass2O.LIBRARY_GROUP_PREFIX})
    public void restoreFromCompatExtras(Bundle bundle) {
        C00114k r0;
        this.mMessages.clear();
        if (bundle.containsKey("android.messagingStyleUser")) {
            r0 = C00114k.A01(bundle.getBundle("android.messagingStyleUser"));
        } else {
            AnonymousClass4j r1 = new AnonymousClass4j();
            r1.A01 = bundle.getString("android.selfDisplayName");
            r0 = new C00114k(r1);
        }
        this.mUser = r0;
        CharSequence charSequence = bundle.getCharSequence("android.conversationTitle");
        this.mConversationTitle = charSequence;
        if (charSequence == null) {
            this.mConversationTitle = bundle.getCharSequence("android.hiddenConversationTitle");
        }
        Parcelable[] parcelableArray = bundle.getParcelableArray("android.messages");
        if (parcelableArray != null) {
            this.mMessages.addAll(AnonymousClass4W.A00(parcelableArray));
        }
        if (bundle.containsKey("android.isGroupConversation")) {
            this.mIsGroupConversation = Boolean.valueOf(bundle.getBoolean("android.isGroupConversation"));
        }
    }

    @NonNull
    private TextAppearanceSpan makeFontColorSpan(int i) {
        return new TextAppearanceSpan(null, 0, 0, ColorStateList.valueOf(i), null);
    }

    private CharSequence makeMessageLine(AnonymousClass4W r8) {
        AnonymousClass74 r6;
        CharSequence charSequence;
        boolean z = true;
        if (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) != 1) {
            z = false;
        }
        AnonymousClass7C r0 = AnonymousClass74.A05;
        if (r0 != r0) {
            r6 = new AnonymousClass74(z, r0);
        } else if (z) {
            r6 = AnonymousClass74.A04;
        } else {
            r6 = AnonymousClass74.A03;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        C00114k r02 = r8.A04;
        CharSequence charSequence2 = "";
        if (r02 == null) {
            charSequence = charSequence2;
        } else {
            charSequence = r02.A01;
        }
        if (TextUtils.isEmpty(charSequence)) {
            throw null;
        }
        CharSequence A02 = r6.A02(charSequence);
        spannableStringBuilder.append(A02);
        spannableStringBuilder.setSpan(makeFontColorSpan(-16777216), spannableStringBuilder.length() - A02.length(), spannableStringBuilder.length(), 33);
        CharSequence charSequence3 = r8.A05;
        if (charSequence3 != null) {
            charSequence2 = charSequence3;
        }
        spannableStringBuilder.append((CharSequence) "  ").append(r6.A02(charSequence2));
        return spannableStringBuilder;
    }

    @Override // X.AnonymousClass4Z
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
            bundle.putParcelableArray("android.messages", AnonymousClass4W.A01(this.mMessages));
        }
        Boolean bool = this.mIsGroupConversation;
        if (bool != null) {
            bundle.putBoolean("android.isGroupConversation", bool.booleanValue());
        }
    }

    @Override // X.AnonymousClass4Z
    @RestrictTo({AnonymousClass2O.LIBRARY_GROUP_PREFIX})
    public void apply(AnonymousClass4I r11) {
        Notification.MessagingStyle messagingStyle;
        Notification.MessagingStyle.Message message;
        this.mIsGroupConversation = Boolean.valueOf(isGroupConversation());
        int i = Build.VERSION.SDK_INT;
        C00114k r0 = this.mUser;
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
        for (AnonymousClass4W r7 : this.mMessages) {
            int i2 = Build.VERSION.SDK_INT;
            C00114k r02 = r7.A04;
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
        throw null;
    }

    @Nullable
    public CharSequence getConversationTitle() {
        return this.mConversationTitle;
    }

    public List<AnonymousClass4W> getMessages() {
        return this.mMessages;
    }

    public C00114k getUser() {
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

    public NotificationCompat$MessagingStyle(@NonNull C00114k r3) {
        if (!TextUtils.isEmpty(r3.A01)) {
            this.mUser = r3;
            return;
        }
        throw new IllegalArgumentException("User's name must not be empty.");
    }

    @Deprecated
    public NotificationCompat$MessagingStyle(@NonNull CharSequence charSequence) {
        AnonymousClass4j r1 = new AnonymousClass4j();
        r1.A01 = charSequence;
        this.mUser = new C00114k(r1);
    }

    public NotificationCompat$MessagingStyle addMessage(AnonymousClass4W r3) {
        this.mMessages.add(r3);
        if (this.mMessages.size() > 25) {
            this.mMessages.remove(0);
        }
        return this;
    }

    public NotificationCompat$MessagingStyle addMessage(CharSequence charSequence, long j, C00114k r5) {
        addMessage(new AnonymousClass4W(charSequence, j, r5));
        return this;
    }

    @Deprecated
    public NotificationCompat$MessagingStyle addMessage(CharSequence charSequence, long j, CharSequence charSequence2) {
        List<AnonymousClass4W> list = this.mMessages;
        AnonymousClass4j r0 = new AnonymousClass4j();
        r0.A01 = charSequence2;
        list.add(new AnonymousClass4W(charSequence, j, new C00114k(r0)));
        if (this.mMessages.size() > 25) {
            this.mMessages.remove(0);
        }
        return this;
    }
}
