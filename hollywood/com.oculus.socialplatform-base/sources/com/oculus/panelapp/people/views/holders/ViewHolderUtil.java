package com.oculus.panelapp.people.views.holders;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import com.oculus.horizoncontent.profile.ProfilePresenceType;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.socialplatform.R;

public class ViewHolderUtil {

    /* renamed from: com.oculus.panelapp.people.views.holders.ViewHolderUtil$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$horizoncontent$profile$ProfilePresenceType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.oculus.horizoncontent.profile.ProfilePresenceType[] r0 = com.oculus.horizoncontent.profile.ProfilePresenceType.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.panelapp.people.views.holders.ViewHolderUtil.AnonymousClass1.$SwitchMap$com$oculus$horizoncontent$profile$ProfilePresenceType = r2
                com.oculus.horizoncontent.profile.ProfilePresenceType r0 = com.oculus.horizoncontent.profile.ProfilePresenceType.MESSENGER     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.horizoncontent.profile.ProfilePresenceType r0 = com.oculus.horizoncontent.profile.ProfilePresenceType.VR     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.people.views.holders.ViewHolderUtil.AnonymousClass1.<clinit>():void");
        }
    }

    public static class PresenceIconInfo {
        public final boolean mIsPresenceIconVisible;
        public final Drawable mPresenceIcon;

        public PresenceIconInfo(boolean z, Drawable drawable) {
            this.mIsPresenceIconVisible = z;
            this.mPresenceIcon = drawable;
        }
    }

    public static PresenceIconInfo getPresenceIconInfo(Context context, ProfilePresenceType profilePresenceType) {
        int i;
        int ordinal = profilePresenceType.ordinal();
        boolean z = true;
        switch (ordinal) {
            case 0:
                i = R.drawable.oc_icon_headset_filled_16_d2d2d2;
                break;
            default:
                z = false;
            case 1:
                i = R.drawable.oc_icon_mobile_filled_16_d2d2d2;
                break;
        }
        return new PresenceIconInfo(z, context.getDrawable(i));
    }

    public static String getPresenceSubtitle(SocialUser socialUser, Resources resources) {
        String str;
        if (socialUser.getIsActive(resources) || (str = socialUser.mLastActive) == null) {
            return socialUser.mPresenceString;
        }
        return str;
    }
}
