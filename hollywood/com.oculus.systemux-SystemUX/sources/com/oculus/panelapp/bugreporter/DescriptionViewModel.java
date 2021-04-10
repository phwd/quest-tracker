package com.oculus.panelapp.bugreporter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.oculus.panelapp.bugreporter.common.BugCategory;
import com.oculus.panelapp.bugreporter.util.BugReporterUtil;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.util.HorizonUtil;
import com.oculus.vrshell.util.PackageUtil;
import com.oculus.vrshell.util.SystemUXScreenshotUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DescriptionViewModel extends BaseObservable {
    private BugCategory mBugCategory;
    private final BugReporterUtil mBugReporterUtil;
    private final Context mContext;
    private String mDescriptionText = "";
    private ArrayList<BugCategory> mDropdownItems;
    private boolean mIncludeScreenshot = true;
    private Map<BugCategory, String> mLabelMap;
    private String mPausedAppName = "";
    private String mPausedAppPackageName = "";
    private Uri mPreselectedPhotoUri = null;

    public DescriptionViewModel(Context context, BugReporterUtil bugReporterUtil) {
        this.mContext = context;
        this.mBugReporterUtil = bugReporterUtil;
        this.mPausedAppPackageName = this.mBugReporterUtil.getReturnComponent();
        SystemUXRoute fromPath = SystemUXRoute.fromPath(this.mBugReporterUtil.getActiveComponent());
        if (!TextUtils.isEmpty(this.mPausedAppPackageName) && !PackageUtil.isShellApp(this.mPausedAppPackageName)) {
            this.mPausedAppName = HorizonUtil.getSafeApplicationName(context, this.mPausedAppPackageName);
            if ("Unknown".equals(this.mPausedAppName)) {
                this.mPausedAppName = this.mPausedAppPackageName;
            }
            this.mBugCategory = BugCategory.THIRD_PARTY;
        } else if (fromPath != null) {
            this.mBugCategory = BugCategory.fromSystemUX(fromPath);
        } else {
            this.mBugCategory = BugCategory.SELECT_CATEGORY;
        }
        String decode = Uri.decode(this.mBugReporterUtil.getPreselectedPhoto());
        if (!TextUtils.isEmpty(decode)) {
            File file = new File(decode);
            if (file.exists() && file.isFile()) {
                this.mPreselectedPhotoUri = Uri.fromFile(file);
            }
        }
    }

    public List<BugCategory> getBugCategoryDropdownItems() {
        if (this.mDropdownItems == null) {
            this.mDropdownItems = BugCategory.getEnabledValues(!this.mBugReporterUtil.isPublicUser(), this.mContext);
            if (TextUtils.isEmpty(this.mPausedAppName)) {
                this.mDropdownItems.remove(BugCategory.THIRD_PARTY);
            }
        }
        return this.mDropdownItems;
    }

    public Map<BugCategory, String> getBugCategoryDropdownLabelMap() {
        if (this.mLabelMap == null) {
            this.mLabelMap = new HashMap();
            Iterator<BugCategory> it = BugCategory.getEnabledValues(!this.mBugReporterUtil.isPublicUser(), this.mContext).iterator();
            while (it.hasNext()) {
                BugCategory next = it.next();
                this.mLabelMap.put(next, this.mContext.getResources().getString(next.getLabelId()));
            }
            if (TextUtils.isEmpty(this.mPausedAppName)) {
                this.mLabelMap.remove(BugCategory.THIRD_PARTY);
            } else {
                this.mLabelMap.replace(BugCategory.THIRD_PARTY, this.mPausedAppName);
            }
        }
        return this.mLabelMap;
    }

    @Bindable
    public BugCategory getBugCategory() {
        return this.mBugCategory;
    }

    public void setBugCategory(BugCategory bugCategory) {
        this.mBugCategory = bugCategory;
        notifyPropertyChanged(BR.bugCategory);
    }

    public Bitmap getScreenshotBitmap() {
        Bitmap appScreenshot = SystemUXScreenshotUtil.getAppScreenshot();
        return (appScreenshot == null || TextUtils.isEmpty(this.mPausedAppPackageName) || !this.mPausedAppPackageName.equals(SystemUXScreenshotUtil.getAppScreenshotPackageName())) ? SystemUXScreenshotUtil.getHomeScreenshot() : appScreenshot;
    }

    @Bindable
    public Drawable getScreenshot() {
        Bitmap screenshotBitmap = getScreenshotBitmap();
        if (screenshotBitmap != null) {
            return new BitmapDrawable(this.mContext.getResources(), screenshotBitmap);
        }
        return null;
    }

    @Bindable
    public boolean getIncludeScreenshot() {
        return this.mIncludeScreenshot && !getHasPreselectedPhoto();
    }

    public void setIncludeScreenshot(boolean z) {
        if (this.mIncludeScreenshot != z) {
            this.mIncludeScreenshot = z;
            notifyPropertyChanged(BR.includeScreenshot);
        }
    }

    @Bindable
    public String getDescriptionText() {
        return this.mDescriptionText;
    }

    public void setDescriptionText(String str) {
        if (!this.mDescriptionText.equals(str)) {
            this.mDescriptionText = str;
            notifyPropertyChanged(BR.descriptionText);
        }
    }

    @Bindable({"bugCategory", "descriptionText"})
    public boolean getContinueButtonEnabled() {
        return this.mBugCategory != BugCategory.SELECT_CATEGORY && !this.mDescriptionText.isEmpty();
    }

    @Bindable
    public int getContinueButtonText() {
        return shouldSkipToSubmit() ? R.string.bug_report_submit_button : R.string.bug_report_continue_button;
    }

    public boolean shouldSkipToSubmit() {
        return this.mBugReporterUtil.isPublicUser() && !this.mBugReporterUtil.getMediaViewModel().hasMediaFiles();
    }

    @Bindable
    public boolean getHasPreselectedPhoto() {
        return this.mPreselectedPhotoUri != null;
    }

    public Uri getPreselectedPhoto() {
        return this.mPreselectedPhotoUri;
    }
}
