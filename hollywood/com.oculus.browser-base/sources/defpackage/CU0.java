package defpackage;

import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.view.View;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/* renamed from: CU0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CU0 {

    /* renamed from: a  reason: collision with root package name */
    public static final HashSet f7813a = new HashSet(Arrays.asList(0, 1, 2, 3, 4, 5, 6));
    public static final ArrayList b = new ArrayList(Arrays.asList("com.whatsapp.ContactPicker", "com.facebook.composer.shareintent.ImplicitShareIntentHandlerDefaultAlias", "com.google.android.gm.ComposeActivityGmailExternal", "com.instagram.share.handleractivity.StoryShareHandlerActivity", "com.facebook.messenger.intents.ShareIntentHandler", "com.google.android.apps.messaging.ui.conversationlist.ShareIntentActivity", "com.twitter.composer.ComposerActivity", "com.snap.mushroom.MainActivity", "com.pinterest.activity.create.PinItActivity", "com.linkedin.android.publishing.sharing.LinkedInDeepLinkActivity", "jp.naver.line.android.activity.selectchat.SelectChatActivityLaunchActivity", "com.facebook.lite.composer.activities.ShareIntentMultiPhotoAlphabeticalAlias", "com.facebook.mlite.share.view.ShareActivity", "com.samsung.android.email.ui.messageview.MessageFileView", "com.yahoo.mail.ui.activities.ComposeActivity", "org.telegram.ui.LaunchActivity", "com.tencent.mm.ui.tools.ShareImgUI"));
    public final AbstractC4448qj c;
    public final PackageManager d;

    public CU0(AbstractC4448qj qjVar, PackageManager packageManager) {
        this.c = qjVar;
        this.d = packageManager;
    }

    public static UH0 a(Drawable drawable, String str, View.OnClickListener onClickListener, boolean z) {
        Map c2 = UH0.c(AU0.e);
        TH0 th0 = AU0.f7673a;
        LH0 lh0 = new LH0(null);
        lh0.f8415a = drawable;
        HashMap hashMap = (HashMap) c2;
        hashMap.put(th0, lh0);
        TH0 th02 = AU0.b;
        LH0 lh02 = new LH0(null);
        lh02.f8415a = str;
        hashMap.put(th02, lh02);
        TH0 th03 = AU0.c;
        LH0 lh03 = new LH0(null);
        lh03.f8415a = onClickListener;
        hashMap.put(th03, lh03);
        QH0 qh0 = AU0.d;
        GH0 gh0 = new GH0(null);
        gh0.f8081a = z;
        hashMap.put(qh0, gh0);
        return new UH0(c2, null);
    }
}
