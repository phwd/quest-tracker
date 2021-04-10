package com.oculus.vrshell.panels.telemetry;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.timer.TimeService;
import java.util.Arrays;

public class SectionTrackerImpl implements SectionTracker {
    private static final String TAG = LoggingUtil.tag(SectionTrackerImpl.class);
    private boolean mForeground = true;
    private String mSection;
    private long mSectionEnterTime = -1;
    private SectionListener mSectionListener;
    private String mSubSection;
    private final TimeService mTimeService;

    /* access modifiers changed from: package-private */
    public interface SectionListener {
        void onSectionEntryCallback();

        void onSectionExitCallback();
    }

    /* access modifiers changed from: private */
    public final class SubSectionException extends RuntimeException {
        public SubSectionException(String str, String str2, String str3) {
            super(String.format("%s current[%s, %s] reported[%s, %s]", str, SectionTrackerImpl.this.mSection, SectionTrackerImpl.this.mSubSection, str2, str3));
        }
    }

    public SectionTrackerImpl(TimeService timeService) {
        this.mTimeService = timeService;
    }

    public void setSectionListener(SectionListener sectionListener) {
        this.mSectionListener = sectionListener;
    }

    @Override // com.oculus.vrshell.panels.telemetry.SectionTracker
    public String[] addSectionData(String str, String[] strArr) {
        if (this.mSection == null || this.mSubSection == null) {
            String str2 = TAG;
            Log.w(str2, "Adding section data when there is no current section for eventName " + str);
        }
        String[] strArr2 = (String[]) Arrays.copyOf(strArr, strArr.length + 6);
        strArr2[strArr.length] = ExtraKey.SECTION_NAME.toString().toLowerCase();
        strArr2[strArr.length + 1] = this.mSection;
        strArr2[strArr.length + 2] = ExtraKey.SUB_SECTION_NAME.toString().toLowerCase();
        strArr2[strArr.length + 3] = this.mSubSection;
        strArr2[strArr.length + 4] = ExtraKey.ACTIVE_DURATION_MS.toString().toLowerCase();
        strArr2[strArr.length + 5] = Long.toString(this.mSectionEnterTime != -1 ? this.mTimeService.getCurrentMs() - this.mSectionEnterTime : 0);
        return strArr2;
    }

    @Override // com.oculus.vrshell.panels.telemetry.SectionTracker
    public void subSectionEnter(String str, String str2) {
        if (this.mForeground) {
            if (this.mSection == null && this.mSubSection == null) {
                this.mSection = str;
                this.mSubSection = str2;
            } else {
                throw new SubSectionException("Entering a subSection while still in a subSection!", str, str2);
            }
        }
        SectionListener sectionListener = this.mSectionListener;
        if (sectionListener != null) {
            sectionListener.onSectionEntryCallback();
        }
        this.mSectionEnterTime = this.mTimeService.getCurrentMs();
    }

    @Override // com.oculus.vrshell.panels.telemetry.SectionTracker
    public void subSectionExit(String str, String str2) {
        String str3 = this.mSection;
        if (str3 == null || this.mSubSection == null) {
            throw new SubSectionException("Exiting a subSection while not in a subSection!", str, str2);
        } else if (!str3.equals(str) || !this.mSubSection.equals(str2)) {
            throw new SubSectionException("SubSection exit mismatch error!", str, str2);
        } else {
            SectionListener sectionListener = this.mSectionListener;
            if (sectionListener != null) {
                sectionListener.onSectionExitCallback();
            }
            this.mSectionEnterTime = -1;
            if (this.mForeground) {
                this.mSection = null;
                this.mSubSection = null;
            }
        }
    }

    @Override // com.oculus.vrshell.panels.telemetry.SectionTracker
    public void onAppForeground() {
        String str;
        if (!this.mForeground) {
            String str2 = this.mSection;
            if (!(str2 == null || (str = this.mSubSection) == null)) {
                subSectionEnter(str2, str);
            }
            this.mForeground = true;
            return;
        }
        throw new RuntimeException("App foreground called when already in foreground state!");
    }

    @Override // com.oculus.vrshell.panels.telemetry.SectionTracker
    public void onAppBackground() {
        String str;
        if (this.mForeground) {
            this.mForeground = false;
            String str2 = this.mSection;
            if (str2 != null && (str = this.mSubSection) != null) {
                subSectionExit(str2, str);
                return;
            }
            return;
        }
        throw new RuntimeException("App background called when already in background state!");
    }
}
