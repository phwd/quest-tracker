package com.oculus.horizon.cloudstorage2;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass117;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.inject.Assisted;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.cloudstorage2.task.MasterTask;
import com.oculus.library.model.App;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;
import java.lang.Enum;
import java.util.UUID;

@Dependencies({"_UL__ULSEP_com_oculus_horizon_cloudstorage2_CloudStorageLogger_ULSEP_BINDING_ID"})
public class TaskProgressReporter<E extends Enum> implements Reporter {
    public App mApp;
    public final String mAppId;
    @Inject
    @Eager
    public final CloudStorageLogger mLogger;
    public E mProgress;
    public ProgressListener mProgressListener;
    public String mReporterId;
    public final Class<? extends MasterTask> mTaskClass;

    public interface ProgressListener {
        void A6d(float f);
    }

    /* JADX WARN: Incorrect args count in method signature: (ITE;)V */
    public static void A00(TaskProgressReporter taskProgressReporter, int i, Enum r13) {
        int length;
        String formatStrLocaleSafe;
        String str;
        String str2;
        E e = taskProgressReporter.mProgress;
        if (e.ordinal() == ((Enum[]) taskProgressReporter.mProgress.getClass().getEnumConstants()).length - 1) {
            formatStrLocaleSafe = "Cannot set progress on a completed reporter!";
        } else {
            Enum[] enumArr = (Enum[]) e.getClass().getEnumConstants();
            if (i < 0 || i >= (length = enumArr.length)) {
                throw new IllegalArgumentException(StringFormatUtil.formatStrLocaleSafe("New progress value %d is outside of valid range [%d, %d].", Integer.valueOf(i), 0, Integer.valueOf(enumArr.length - 1)));
            }
            E e2 = (E) enumArr[i];
            if (e2 == r13) {
                Object[] objArr = {Integer.valueOf(e2.ordinal()), e2};
                CloudStorageLogger cloudStorageLogger = taskProgressReporter.mLogger;
                App app = taskProgressReporter.mApp;
                if (app == null) {
                    str = taskProgressReporter.mAppId;
                } else {
                    str = app.id;
                }
                if (app == null) {
                    str2 = "";
                } else {
                    str2 = app.packageName;
                }
                Class<? extends MasterTask> cls = taskProgressReporter.mTaskClass;
                String str3 = taskProgressReporter.mReporterId;
                String name = taskProgressReporter.mProgress.name();
                Event A22 = ((EventManager) AnonymousClass0J2.A03(0, 242, cloudStorageLogger._UL_mInjectionContext)).A22(CloudStorageLogger.EVENT_CLOUD_STORAGE_PROGRESS);
                A22.A15("app_package_name", str2);
                A22.A15(CloudStorageLogger.KEY_APP_ITEM_ID, str);
                A22.A15(CloudStorageLogger.KEY_OPERATION_TYPE, cls.getSimpleName());
                A22.A15(CloudStorageLogger.KEY_OPERATION_ID, str3);
                A22.A15("result", name);
                A22.A5L();
                taskProgressReporter.mProgress = e2;
                ProgressListener progressListener = taskProgressReporter.mProgressListener;
                if (progressListener != null) {
                    progressListener.A6d(((float) i) / ((float) length));
                    return;
                }
                return;
            }
            formatStrLocaleSafe = StringFormatUtil.formatStrLocaleSafe("Next state %s does not match expected state %s.", e2, r13);
        }
        throw new IllegalStateException(formatStrLocaleSafe);
    }

    public final void A01(App app) {
        String str = this.mAppId;
        String str2 = app.id;
        if (!str.equals(str2)) {
            AnonymousClass0NO.A06(TaskProgressReporter.class, "%s is setting app with id (%s) that does not match original app id (%s)", this.mTaskClass.getSimpleName(), str2, str);
        }
        new Object[1][0] = app.packageName;
        this.mApp = app;
    }

    public final void A02(E e) {
        E e2 = this.mProgress;
        int ordinal = e2.ordinal() + 1;
        if (ordinal != ((Enum[]) e2.getClass().getEnumConstants()).length - 1) {
            A00(this, ordinal, e);
            return;
        }
        throw new IllegalStateException(StringFormatUtil.formatStrLocaleSafe("Progress reporter reached last step when attempting to progress to %s, should call done() instead.", e));
    }

    public final void A03(Exception exc) {
        String str;
        String str2;
        CloudStorageLogger cloudStorageLogger = this.mLogger;
        App app = this.mApp;
        if (app == null) {
            str = this.mAppId;
        } else {
            str = app.id;
        }
        if (app == null) {
            str2 = "";
        } else {
            str2 = app.packageName;
        }
        Class<? extends MasterTask> cls = this.mTaskClass;
        String str3 = this.mReporterId;
        Event A22 = ((EventManager) AnonymousClass0J2.A03(0, 242, cloudStorageLogger._UL_mInjectionContext)).A22(CloudStorageLogger.EVENT_CLOUD_STORAGE_END);
        A22.A15(CloudStorageLogger.KEY_APP_ITEM_ID, str);
        A22.A15("app_package_name", str2);
        A22.A15(CloudStorageLogger.KEY_OPERATION_TYPE, cls.getSimpleName());
        A22.A15(CloudStorageLogger.KEY_OPERATION_ID, str3);
        A22.A15("result", exc.getMessage());
        A22.A5L();
    }

    public final void A04(String str) {
        String str2;
        String str3;
        CloudStorageLogger cloudStorageLogger = this.mLogger;
        App app = this.mApp;
        if (app == null) {
            str2 = this.mAppId;
        } else {
            str2 = app.id;
        }
        if (app == null) {
            str3 = "";
        } else {
            str3 = app.packageName;
        }
        Class<? extends MasterTask> cls = this.mTaskClass;
        String str4 = this.mReporterId;
        Event A22 = ((EventManager) AnonymousClass0J2.A03(0, 242, cloudStorageLogger._UL_mInjectionContext)).A22(CloudStorageLogger.EVENT_CLOUD_STORAGE_END);
        A22.A15(CloudStorageLogger.KEY_APP_ITEM_ID, str2);
        A22.A15("app_package_name", str3);
        A22.A15(CloudStorageLogger.KEY_OPERATION_TYPE, cls.getSimpleName());
        A22.A15(CloudStorageLogger.KEY_OPERATION_ID, str4);
        A22.A15("result", str);
        A22.A5L();
    }

    @Inject
    public TaskProgressReporter(AbstractC06640p5 r8, @Assisted Class<? extends MasterTask> cls, @Assisted String str, @Assisted E e) {
        String str2;
        String str3;
        this.mLogger = (CloudStorageLogger) AnonymousClass117.A00(527, r8);
        this.mTaskClass = cls;
        this.mAppId = str;
        this.mProgress = e;
        String obj = UUID.randomUUID().toString();
        this.mReporterId = obj;
        int ordinal = this.mProgress.ordinal();
        if (ordinal == 0) {
            CloudStorageLogger cloudStorageLogger = this.mLogger;
            App app = this.mApp;
            if (app == null) {
                str2 = this.mAppId;
            } else {
                str2 = app.id;
            }
            if (app == null) {
                str3 = "";
            } else {
                str3 = app.packageName;
            }
            Class<? extends MasterTask> cls2 = this.mTaskClass;
            Event A22 = ((EventManager) AnonymousClass0J2.A03(0, 242, cloudStorageLogger._UL_mInjectionContext)).A22(CloudStorageLogger.EVENT_CLOUD_STORAGE_START);
            A22.A15("app_package_name", str3);
            A22.A15(CloudStorageLogger.KEY_APP_ITEM_ID, str2);
            A22.A15(CloudStorageLogger.KEY_OPERATION_TYPE, cls2.getSimpleName());
            A22.A15(CloudStorageLogger.KEY_OPERATION_ID, obj);
            A22.A15("result", "start");
            A22.A5L();
            return;
        }
        throw new IllegalArgumentException(StringFormatUtil.formatStrLocaleSafe("Initial progress value must be the first item in the enum, but was %d (%s).", Integer.valueOf(ordinal), e));
    }
}
