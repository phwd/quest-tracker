package com.oculus.externalstorage;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import com.android.internal.app.AlertController;

public class StorageLauncherActivity extends StorageWizardBase implements DialogInterface.OnClickListener {
    public static String INTENT_KEY_LAUNCH_TYPE = "LaunchType";
    private static final Uri OCULUS_GALLERY_URI = Uri.parse("systemux://gallery");
    private static final String TAG = StorageLauncherActivity.class.getSimpleName();
    private static final String VR_SHELL_MAIN_ACTIVITY = "com.oculus.vrshell.MainActivity";
    private LaunchType mLaunchType;

    public enum LaunchType {
        GALLERY,
        FORMAT
    }

    /* renamed from: com.oculus.externalstorage.StorageLauncherActivity$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$externalstorage$StorageLauncherActivity$LaunchType = new int[LaunchType.values().length];

        static {
            try {
                $SwitchMap$com$oculus$externalstorage$StorageLauncherActivity$LaunchType[LaunchType.GALLERY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$oculus$externalstorage$StorageLauncherActivity$LaunchType[LaunchType.FORMAT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public void onClick(DialogInterface dialog, int which) {
        if (which == -1) {
            int i = AnonymousClass1.$SwitchMap$com$oculus$externalstorage$StorageLauncherActivity$LaunchType[this.mLaunchType.ordinal()];
            if (i == 1) {
                startOculusGalleryActivity();
            } else if (i == 2) {
                checkAndFormatDrive();
            } else {
                throw new RuntimeException("Invalid launch type: " + this.mLaunchType);
            }
        }
        finish();
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.externalstorage.StorageWizardBase
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TypedValue alertIconTyped = new TypedValue();
        getTheme().resolveAttribute(16843605, alertIconTyped, true);
        AlertController.AlertParams ap = this.mAlertParams;
        ap.mIcon = getDrawable(alertIconTyped.resourceId);
        ap.mPositiveButtonText = getString(R.string.storage_launcher_accept);
        ap.mNegativeButtonText = getString(R.string.storage_launcher_refuse);
        ap.mPositiveButtonListener = this;
        ap.mNegativeButtonListener = this;
        this.mLaunchType = (LaunchType) getIntent().getSerializableExtra(INTENT_KEY_LAUNCH_TYPE);
        int i = AnonymousClass1.$SwitchMap$com$oculus$externalstorage$StorageLauncherActivity$LaunchType[this.mLaunchType.ordinal()];
        if (i == 1) {
            ap.mTitle = getString(R.string.storage_launcher_gallery_title);
            ap.mMessage = getString(R.string.storage_launcher_gallery_message);
        } else if (i == 2) {
            ap.mTitle = getString(R.string.storage_launcher_format_title);
            ap.mMessage = getString(R.string.storage_launcher_format_message);
        } else {
            throw new RuntimeException("invalid launch type: " + this.mLaunchType);
        }
        setupAlert();
    }

    private void startOculusGalleryActivity() {
        Intent shellIntent = new Intent();
        shellIntent.addFlags(65536);
        shellIntent.setComponent(new ComponentName("com.oculus.vrshell", VR_SHELL_MAIN_ACTIVITY));
        shellIntent.setData(OCULUS_GALLERY_URI);
        try {
            startActivity(shellIntent);
        } catch (ActivityNotFoundException e) {
            Log.e(TAG, "could not start the oculus gallery activity", e);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r8v0, resolved type: com.oculus.externalstorage.StorageLauncherActivity */
    /* JADX WARN: Multi-variable type inference failed */
    private void checkAndFormatDrive() {
        String diskId = getIntent().getStringExtra("android.os.storage.extra.DISK_ID");
        Intent intent = new Intent();
        if (isVolumeNotUnmountable()) {
            intent.setClass(this, StorageWizardReady.class);
            intent.putExtra("android.os.storage.extra.DISK_ID", diskId);
        } else if (Build.VERSION.SDK_INT >= 29) {
            intent.setComponent(new ComponentName("com.oculus.vrshell", VR_SHELL_MAIN_ACTIVITY));
            intent.putExtra("uri", new Uri.Builder().scheme("vrdesktop").authority("com.android.settings").appendPath("com.android.settings.deviceinfo.StorageWizardInit").appendQueryParameter("android.os.storage.extra.DISK_ID", diskId).build().toString());
            intent.setData(Uri.parse("apk://com.oculus.vrshell.desktop"));
        } else {
            intent.setClassName("com.android.settings", "com.android.settings.deviceinfo.StorageWizardInit");
            intent.putExtra("android.os.storage.extra.DISK_ID", diskId);
        }
        intent.addFlags(65536);
        startActivity(intent);
    }

    private boolean isVolumeNotUnmountable() {
        return (this.mVolume == null || this.mVolume.getType() != 0 || this.mVolume.getState() == 6) ? false : true;
    }
}
