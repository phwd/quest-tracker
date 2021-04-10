package X;

import android.app.Notification;
import android.app.RemoteInput;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RemoteViews;
import androidx.annotation.RestrictTo;
import androidx.core.app.NotificationCompat$CarExtender;
import androidx.core.graphics.drawable.IconCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.0dW  reason: invalid class name and case insensitive filesystem */
public final class C03920dW implements AnonymousClass06v {
    public final Notification.Builder A00;
    public final Bundle A01 = new Bundle();
    public final AnonymousClass074 A02;
    public final List<Bundle> A03 = new ArrayList();

    public C03920dW(AnonymousClass074 r23) {
        Notification.Builder builder;
        int i;
        Bundle bundle;
        Bundle[] bundleArr;
        Icon icon;
        Bundle bundle2;
        this.A02 = r23;
        int i2 = Build.VERSION.SDK_INT;
        Context context = r23.A03;
        if (i2 >= 26) {
            builder = new Notification.Builder(context, r23.A00);
        } else {
            builder = new Notification.Builder(context);
        }
        this.A00 = builder;
        Notification notification = r23.A02;
        int i3 = 0;
        builder.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, null).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(r23.A07).setContentText(r23.A06).setContentInfo(null).setContentIntent(null).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(null, (notification.flags & 128) != 0).setLargeIcon((Bitmap) null).setNumber(0).setProgress(0, 0, false);
        this.A00.setSubText(null).setUsesChronometer(false).setPriority(0);
        Iterator<AnonymousClass070> it = r23.A08.iterator();
        while (it.hasNext()) {
            AnonymousClass070 next = it.next();
            IconCompat A002 = next.A00();
            if (A002 != null) {
                icon = IconCompat.A03(A002, null);
            } else {
                icon = null;
            }
            Notification.Action.Builder builder2 = new Notification.Action.Builder(icon, next.A02, next.A01);
            AnonymousClass07S[] r0 = next.A09;
            if (r0 != null) {
                for (RemoteInput remoteInput : AnonymousClass07S.A00(r0)) {
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
        Bundle bundle4 = r23.A04;
        if (bundle4 != null) {
            this.A01.putAll(bundle4);
        }
        this.A00.setShowWhen(true);
        this.A00.setLocalOnly(false).setGroup(null).setGroupSummary(false).setSortKey(null);
        this.A00.setCategory(null).setColor(0).setVisibility(0).setPublicVersion(null).setSound(notification.sound, notification.audioAttributes);
        Iterator<String> it2 = r23.A09.iterator();
        while (it2.hasNext()) {
            this.A00.addPerson(it2.next());
        }
        if (r23.A01.size() > 0) {
            Bundle bundle5 = r23.A04;
            if (bundle5 == null) {
                bundle5 = new Bundle();
                r23.A04 = bundle5;
            }
            Bundle bundle6 = bundle5.getBundle(NotificationCompat$CarExtender.EXTRA_CAR_EXTENDER);
            bundle6 = bundle6 == null ? new Bundle() : bundle6;
            Bundle bundle7 = new Bundle();
            while (true) {
                ArrayList<AnonymousClass070> arrayList = r23.A01;
                if (i3 >= arrayList.size()) {
                    break;
                }
                String num = Integer.toString(i3);
                AnonymousClass070 r8 = arrayList.get(i3);
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
                bundle8.putBundle("extras", bundle);
                AnonymousClass07S[] r5 = r8.A09;
                if (r5 == null) {
                    bundleArr = null;
                } else {
                    int length = r5.length;
                    bundleArr = new Bundle[length];
                    for (int i6 = 0; i6 < length; i6++) {
                        AnonymousClass07S r2 = r5[i6];
                        Bundle bundle10 = new Bundle();
                        bundle10.putString("resultKey", r2.A03);
                        bundle10.putCharSequence("label", r2.A02);
                        bundle10.putCharSequenceArray("choices", r2.A05);
                        bundle10.putBoolean("allowFreeFormInput", r2.A04);
                        bundle10.putBundle("extras", r2.A01);
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
            Bundle bundle11 = r23.A04;
            if (bundle11 == null) {
                bundle11 = new Bundle();
                r23.A04 = bundle11;
            }
            bundle11.putBundle(NotificationCompat$CarExtender.EXTRA_CAR_EXTENDER, bundle6);
            this.A01.putBundle(NotificationCompat$CarExtender.EXTRA_CAR_EXTENDER, bundle6);
        }
        this.A00.setExtras(r23.A04).setRemoteInputHistory(null);
        if (Build.VERSION.SDK_INT >= 26) {
            this.A00.setBadgeIconType(0).setShortcutId(null).setTimeoutAfter(0).setGroupAlertBehavior(0);
            if (!TextUtils.isEmpty(r23.A00)) {
                this.A00.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
            }
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.A00.setAllowSystemGeneratedContextualActions(true);
            this.A00.setBubbleMetadata(AnonymousClass073.A00());
        }
    }

    public final Notification A00() {
        RemoteViews remoteViews;
        AnonymousClass074 r4 = this.A02;
        AnonymousClass07C r3 = r4.A05;
        if (r3 != null) {
            r3.apply(this);
            remoteViews = r3.makeContentView(this);
        } else {
            remoteViews = null;
        }
        Notification build = this.A00.build();
        if (remoteViews != null) {
            build.contentView = remoteViews;
        }
        if (r3 != null) {
            RemoteViews makeBigContentView = r3.makeBigContentView(this);
            if (makeBigContentView != null) {
                build.bigContentView = makeBigContentView;
            }
            RemoteViews makeHeadsUpContentView = r4.A05.makeHeadsUpContentView(this);
            if (makeHeadsUpContentView != null) {
                build.headsUpContentView = makeHeadsUpContentView;
            }
            Bundle bundle = build.extras;
            if (bundle != null) {
                r3.addCompatExtras(bundle);
            }
        }
        return build;
    }

    @Override // X.AnonymousClass06v
    public final Notification.Builder A3A() {
        return this.A00;
    }
}
