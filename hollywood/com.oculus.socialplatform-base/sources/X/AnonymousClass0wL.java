package X;

import android.app.Notification;
import android.app.RemoteInput;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.RestrictTo;
import androidx.core.app.NotificationCompat$CarExtender;
import androidx.core.graphics.drawable.IconCompat;
import bolts.AppLinks;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.0wL  reason: invalid class name */
public final class AnonymousClass0wL implements AbstractC000803d {
    public final Notification.Builder A00;
    public final AnonymousClass03m A01;
    public final Bundle A02 = new Bundle();
    public final List<Bundle> A03 = new ArrayList();

    public AnonymousClass0wL(AnonymousClass03m r23) {
        Notification.Builder builder;
        int i;
        Bundle bundle;
        Bundle[] bundleArr;
        Icon icon;
        Bundle bundle2;
        this.A01 = r23;
        int i2 = Build.VERSION.SDK_INT;
        Context context = r23.A08;
        if (i2 >= 26) {
            builder = new Notification.Builder(context, r23.A01);
        } else {
            builder = new Notification.Builder(context);
        }
        this.A00 = builder;
        Notification notification = r23.A06;
        int i3 = 0;
        builder.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, null).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(r23.A0C).setContentText(r23.A0B).setContentInfo(null).setContentIntent(r23.A07).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(null, (notification.flags & 128) != 0).setLargeIcon(r23.A00).setNumber(0).setProgress(0, 0, false);
        this.A00.setSubText(null).setUsesChronometer(false).setPriority(r23.A05);
        Iterator<AnonymousClass03i> it = r23.A0D.iterator();
        while (it.hasNext()) {
            AnonymousClass03i next = it.next();
            IconCompat A002 = next.A00();
            if (A002 != null) {
                icon = IconCompat.A03(A002, null);
            } else {
                icon = null;
            }
            Notification.Action.Builder builder2 = new Notification.Action.Builder(icon, next.A02, next.A01);
            AnonymousClass04A[] r0 = next.A09;
            if (r0 != null) {
                for (RemoteInput remoteInput : AnonymousClass04A.A00(r0)) {
                    builder2.addRemoteInput(remoteInput);
                }
            }
            Bundle bundle3 = next.A07;
            if (bundle3 != null) {
                bundle2 = new Bundle(bundle3);
            } else {
                bundle2 = new Bundle();
            }
            bundle2.putBoolean("android.support.allowGeneratedReplies", next.A03);
            builder2.setAllowGeneratedReplies(next.A03);
            int i4 = next.A06;
            bundle2.putInt("android.support.action.semanticAction", i4);
            int i5 = Build.VERSION.SDK_INT;
            if (i5 >= 28) {
                builder2.setSemanticAction(i4);
            }
            if (i5 >= 29) {
                builder2.setContextual(next.A08);
            }
            bundle2.putBoolean("android.support.action.showsUserInterface", next.A04);
            builder2.addExtras(bundle2);
            this.A00.addAction(builder2.build());
        }
        Bundle bundle4 = r23.A09;
        if (bundle4 != null) {
            this.A02.putAll(bundle4);
        }
        this.A00.setShowWhen(r23.A0F);
        this.A00.setLocalOnly(false).setGroup(null).setGroupSummary(false).setSortKey(null);
        this.A00.setCategory(null).setColor(r23.A04).setVisibility(0).setPublicVersion(null).setSound(notification.sound, notification.audioAttributes);
        Iterator<String> it2 = r23.A0E.iterator();
        while (it2.hasNext()) {
            this.A00.addPerson(it2.next());
        }
        if (r23.A02.size() > 0) {
            Bundle bundle5 = r23.A09;
            if (bundle5 == null) {
                bundle5 = new Bundle();
                r23.A09 = bundle5;
            }
            Bundle bundle6 = bundle5.getBundle(NotificationCompat$CarExtender.EXTRA_CAR_EXTENDER);
            bundle6 = bundle6 == null ? new Bundle() : bundle6;
            Bundle bundle7 = new Bundle();
            while (true) {
                ArrayList<AnonymousClass03i> arrayList = r23.A02;
                if (i3 >= arrayList.size()) {
                    break;
                }
                String num = Integer.toString(i3);
                AnonymousClass03i r8 = arrayList.get(i3);
                Bundle bundle8 = new Bundle();
                IconCompat A003 = r8.A00();
                if (A003 != null) {
                    i = A003.A0A();
                } else {
                    i = 0;
                }
                bundle8.putInt("icon", i);
                bundle8.putCharSequence("title", r8.A02);
                bundle8.putParcelable("actionIntent", r8.A01);
                Bundle bundle9 = r8.A07;
                if (bundle9 != null) {
                    bundle = new Bundle(bundle9);
                } else {
                    bundle = new Bundle();
                }
                bundle.putBoolean("android.support.allowGeneratedReplies", r8.A03);
                bundle8.putBundle(AppLinks.KEY_NAME_EXTRAS, bundle);
                AnonymousClass04A[] r5 = r8.A09;
                if (r5 == null) {
                    bundleArr = null;
                } else {
                    int length = r5.length;
                    bundleArr = new Bundle[length];
                    for (int i6 = 0; i6 < length; i6++) {
                        AnonymousClass04A r2 = r5[i6];
                        Bundle bundle10 = new Bundle();
                        bundle10.putString("resultKey", r2.A03);
                        bundle10.putCharSequence("label", r2.A02);
                        bundle10.putCharSequenceArray("choices", r2.A05);
                        bundle10.putBoolean("allowFreeFormInput", r2.A04);
                        bundle10.putBundle(AppLinks.KEY_NAME_EXTRAS, r2.A01);
                        bundleArr[i6] = bundle10;
                    }
                }
                bundle8.putParcelableArray("remoteInputs", bundleArr);
                bundle8.putBoolean("showsUserInterface", r8.A04);
                bundle8.putInt("semanticAction", r8.A06);
                bundle7.putBundle(num, bundle8);
                i3++;
            }
            bundle6.putBundle(NotificationCompat$CarExtender.EXTRA_INVISIBLE_ACTIONS, bundle7);
            Bundle bundle11 = r23.A09;
            if (bundle11 == null) {
                bundle11 = new Bundle();
                r23.A09 = bundle11;
            }
            bundle11.putBundle(NotificationCompat$CarExtender.EXTRA_CAR_EXTENDER, bundle6);
            this.A02.putBundle(NotificationCompat$CarExtender.EXTRA_CAR_EXTENDER, bundle6);
        }
        this.A00.setExtras(r23.A09).setRemoteInputHistory(null);
        if (Build.VERSION.SDK_INT >= 26) {
            this.A00.setBadgeIconType(0).setShortcutId(null).setTimeoutAfter(0).setGroupAlertBehavior(0);
            if (!TextUtils.isEmpty(r23.A01)) {
                this.A00.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
            }
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.A00.setAllowSystemGeneratedContextualActions(r23.A03);
            this.A00.setBubbleMetadata(AnonymousClass03l.A00());
        }
    }

    @Override // X.AbstractC000803d
    public final Notification.Builder A3T() {
        return this.A00;
    }
}
