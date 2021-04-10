package defpackage;

import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.background_task_scheduler.ChromeBackgroundTaskFactory;
import org.chromium.chrome.browser.explore_sites.ExploreSitesBackgroundTask;
import org.chromium.chrome.browser.notifications.scheduler.NotificationSchedulerTask;
import org.chromium.chrome.browser.offlinepages.prefetch.OfflineNotificationBackgroundTask;
import org.chromium.chrome.browser.offlinepages.prefetch.PrefetchBackgroundTask;

/* renamed from: hf  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2898hf {

    /* renamed from: a  reason: collision with root package name */
    public static C3581lf f10089a;
    public static ChromeBackgroundTaskFactory b;

    public static AbstractC0865Oe a(int i) {
        AbstractC0865Oe oe;
        Objects.requireNonNull(b);
        if (i == 1) {
            oe = new ZT();
        } else if (i != 2) {
            oe = null;
            if (i != 22) {
                if (i == 91) {
                    oe = new C2441ex1();
                } else if (i != 71300) {
                    switch (i) {
                        case 53:
                        case 54:
                        case 56:
                        case 57:
                            oe = new NG();
                            break;
                        case 55:
                            oe = new C5744yI();
                            break;
                        default:
                            switch (i) {
                                case 77:
                                    oe = new C2764gr0();
                                    break;
                                case 78:
                                    oe = new PrefetchBackgroundTask();
                                    break;
                                case 79:
                                    oe = new OfflineNotificationBackgroundTask();
                                    break;
                                default:
                                    switch (i) {
                                        case 100:
                                        case 101:
                                            oe = new ExploreSitesBackgroundTask();
                                            break;
                                        case 102:
                                            oe = new C0683Le();
                                            break;
                                        case 103:
                                            oe = new NotificationSchedulerTask();
                                            break;
                                        case 104:
                                            oe = new C2419eq0();
                                            break;
                                        case 105:
                                            oe = new GB0();
                                            break;
                                        case 106:
                                        case 107:
                                            oe = new C4042oI0();
                                            break;
                                        case 108:
                                            oe = new C0349Fr0();
                                            break;
                                        default:
                                            AbstractC1220Ua0.f("ChromeBkgrdTaskF", AbstractC2531fV.w("Unable to find BackgroundTask class for task id ", i), new Object[0]);
                                            break;
                                    }
                            }
                    }
                } else {
                    oe = new C3279js0();
                }
            }
        } else {
            oe = new Aq1();
        }
        if (oe instanceof AbstractC4798sm0) {
            ((AbstractC4798sm0) oe).e = new C3107is();
        }
        return oe;
    }

    public static C3581lf b() {
        Object obj = ThreadUtils.f10596a;
        if (f10089a == null) {
            f10089a = new C3581lf(new C4265pf(), new C2215df());
        }
        return f10089a;
    }
}
