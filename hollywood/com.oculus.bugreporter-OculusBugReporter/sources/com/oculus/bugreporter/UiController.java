package com.oculus.bugreporter;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.os.EnvironmentCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.oculus.bugreporter.DescriptionRecorder;
import com.oculus.bugreporter.ImagePickerAdapter;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.SettingsManager;
import com.oculus.os.UnifiedTelemetryLogger;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import oculus.internal.FileUtils;

public class UiController implements ImagePickerAdapter.ImageSelectedCallback, DescriptionRecorder.DescriptionRecorderCallback {
    private static final String COMPONENT_AUDIO = "Audio";
    private static final int DESCRIPTION_RECORDER_STATE_DISABLED = 0;
    private static final int DESCRIPTION_RECORDER_STATE_ENABLED = 1;
    private static final int DESCRIPTION_RECORDER_STATE_LOADING = 2;
    private static final int STATE_FILL_DETAILS = 0;
    private static final int STATE_PICK_IMAGE = 1;
    private static final String TAG = "BugReporter";
    private final Button mAddMediaButton;
    private final EditText mBugDescriptionEditText;
    private String mBugId;
    private final RadioButton mBugTypeRadionButton;
    private final Button mCancelButton;
    private final Button mClearMediaButton;
    private final Spinner mComponentSpinner;
    private final Context mContext;
    private final DescriptionRecorder mDescriptionRecorder;
    private final Button mDescriptionRecorderButton;
    private final Button mDescriptionRecorderButtonRemove;
    private int mDescriptionRecorderState = 0;
    private final TextView mDescriptionRecordingFilenameTextView;
    private final Set<ImagePickerAdapter.ImageData> mExtraMedia = new HashSet();
    private final TextView mExtraMediaTextView;
    private final ViewGroup mMainView;
    private final CheckBox mPastAudioDataConsentCheckBox;
    private final ViewGroup mPastAudioDataConsentView;
    private boolean mPausedState = false;
    private final ViewGroup mPickImageView;
    private final ViewGroup mReportBugView;
    private final CheckBox mScreenshotConsentCheckBox;
    private final ViewGroup mScreenshotConsentPanel;
    private final ImageView mScreenshotImageView;
    private ContentObserver mScreenshotObserver = new ContentObserver(new Handler()) {
        /* class com.oculus.bugreporter.UiController.AnonymousClass1 */

        public void onChange(boolean selfChange) {
            onChange(selfChange, null);
        }

        public void onChange(boolean selfChange, Uri uri) {
            UiController.this.updateScreenshot();
        }
    };
    private final ViewGroup mScreenshotPanel;
    private final SettingsManager mSettingsManager;
    private String mSource;
    private String mSourceApp;
    private final List<CharSequence> mSourceComponentList = new ArrayList();
    private final Button mSubmitButton;
    private int mUiState = 0;

    public UiController(Context context, ViewGroup mainView) {
        this.mContext = context;
        this.mMainView = mainView;
        this.mDescriptionRecorder = new DescriptionRecorder(context, this);
        this.mReportBugView = (ViewGroup) this.mMainView.findViewById(R.id.report_bug_view);
        this.mPickImageView = (ViewGroup) this.mMainView.findViewById(R.id.pick_image_view);
        this.mPastAudioDataConsentView = (ViewGroup) this.mReportBugView.findViewById(R.id.past_audio_data_consent_view);
        this.mExtraMediaTextView = (TextView) this.mReportBugView.findViewById(R.id.extra_media_text_view);
        this.mDescriptionRecordingFilenameTextView = (TextView) this.mReportBugView.findViewById(R.id.description_recorder_filename);
        this.mAddMediaButton = (Button) this.mReportBugView.findViewById(R.id.add_extra_media_button);
        this.mClearMediaButton = (Button) this.mReportBugView.findViewById(R.id.clear_extra_media_button);
        this.mDescriptionRecorderButton = (Button) this.mReportBugView.findViewById(R.id.description_recorder_button);
        this.mDescriptionRecorderButtonRemove = (Button) this.mReportBugView.findViewById(R.id.description_recorder_button_remove);
        this.mMainView.findViewById(R.id.description_recorder).setVisibility(8);
        this.mCancelButton = (Button) this.mReportBugView.findViewById(R.id.cancel_button);
        this.mSubmitButton = (Button) this.mReportBugView.findViewById(R.id.submit_button);
        this.mScreenshotPanel = (ViewGroup) this.mMainView.findViewById(R.id.screenshot_panel);
        this.mScreenshotConsentPanel = (ViewGroup) this.mMainView.findViewById(R.id.screenshot_consent_panel);
        this.mScreenshotImageView = (ImageView) this.mReportBugView.findViewById(R.id.screenshot_image_view);
        this.mComponentSpinner = (Spinner) this.mReportBugView.findViewById(R.id.bug_component_spinner);
        this.mBugDescriptionEditText = (EditText) this.mReportBugView.findViewById(R.id.bug_description);
        this.mBugTypeRadionButton = (RadioButton) this.mReportBugView.findViewById(R.id.bug_type_bug_radio_button);
        this.mScreenshotConsentCheckBox = (CheckBox) this.mReportBugView.findViewById(R.id.screenshot_consent);
        this.mPastAudioDataConsentCheckBox = (CheckBox) this.mPastAudioDataConsentView.findViewById(R.id.past_audio_data_consent_check);
        this.mSettingsManager = new SettingsManager();
        setupReportBugView();
        setupPickImageView();
        BugReportUploaderJobService.scheduleUploadJob(this.mContext);
        BugReportCleanupJobService.scheduleCleanupJob(this.mContext);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUiState(int newState) {
        this.mUiState = newState;
        if (this.mUiState == 1) {
            this.mReportBugView.setVisibility(8);
            this.mPickImageView.setVisibility(0);
            return;
        }
        this.mReportBugView.setVisibility(0);
        this.mPickImageView.setVisibility(8);
    }

    public void refresh() {
        this.mScreenshotImageView.setImageResource(R.drawable.screenshot);
        this.mExtraMedia.clear();
        refreshExtraMedia();
        this.mBugDescriptionEditText.setText("");
        setUiState(0);
        setRecordingState(0);
        this.mDescriptionRecordingFilenameTextView.setText("");
        this.mDescriptionRecorderButtonRemove.setVisibility(8);
    }

    public void onFreshReport(Intent launchIntent) {
        if (this.mPausedState) {
            this.mPausedState = false;
            return;
        }
        refresh();
        this.mBugId = launchIntent.getStringExtra(Constants.EXTRA_BUG_ID);
        this.mSource = launchIntent.getStringExtra(Constants.EXTRA_SOURCE);
        this.mSourceApp = launchIntent.getStringExtra(Constants.EXTRA_SOURCE_APP);
        boolean isScreenshotExpected = "1".equals(launchIntent.getStringExtra(Constants.EXTRA_IS_SCREENSHOT_EXPECTED));
        if (!launchIntent.getBooleanExtra(Constants.EXTRA_IS_PAST_AUDIO_DATA_EXPECTED, false)) {
            ContentValues values = new ContentValues();
            values.put(BugReportProvider.PASTAUDIODATA, (Boolean) true);
            ContentResolver contentResolver = this.mContext.getContentResolver();
            contentResolver.update(Uri.parse("content://com.oculus.bugreporter.provider/report/id/" + this.mBugId), values, "", new String[0]);
        }
        if (isScreenshotExpected) {
            this.mScreenshotPanel.setVisibility(0);
            this.mScreenshotConsentPanel.setVisibility(0);
        } else {
            this.mScreenshotPanel.setVisibility(8);
            this.mScreenshotConsentPanel.setVisibility(8);
        }
        Intent logIntent = new Intent(this.mContext, BugReportLogService.class);
        logIntent.putExtra(Constants.EXTRA_BUG_ID, this.mBugId);
        this.mContext.startService(logIntent);
        if (this.mSource == null) {
            this.mSource = EnvironmentCompat.MEDIA_UNKNOWN;
        }
        resetComponentList();
        registerScreenshotObserver();
        setUiState(this.mUiState);
    }

    public void onPause() {
        setRecordingState(0);
        this.mPausedState = true;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRecordingState(int newState) {
        if (newState == 1 && this.mDescriptionRecorderState == 0) {
            this.mDescriptionRecorder.start(this.mBugId);
            this.mDescriptionRecorderButton.setEnabled(false);
            this.mSubmitButton.setEnabled(false);
            this.mDescriptionRecordingFilenameTextView.setText(R.string.description_recorder_preparing);
            this.mDescriptionRecorderState = 2;
        } else if (newState == 0 && this.mDescriptionRecorderState == 1) {
            this.mDescriptionRecordingFilenameTextView.setText(this.mDescriptionRecorder.stop());
            this.mDescriptionRecorderButtonRemove.setVisibility(0);
            this.mDescriptionRecorderButton.setText(R.string.description_recorder_button_start);
            this.mSubmitButton.setEnabled(true);
            this.mDescriptionRecorderState = 0;
        }
    }

    private void resetComponentList() {
        this.mSourceComponentList.clear();
        if (!TextUtils.isEmpty(this.mSourceApp) && !"com.oculus.vrshell".equals(this.mSourceApp)) {
            this.mSourceComponentList.add(this.mSourceApp);
        }
        this.mSourceComponentList.addAll(Arrays.asList(this.mContext.getResources().getStringArray(R.array.bug_component)));
        this.mSourceComponentList.add(COMPONENT_AUDIO);
        this.mComponentSpinner.setAdapter((SpinnerAdapter) new ArrayAdapter<>(this.mContext, 17367049, this.mSourceComponentList));
    }

    private void setupReportBugView() {
        resetComponentList();
        this.mSubmitButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.bugreporter.UiController.AnonymousClass2 */

            public void onClick(View v) {
                BugFileUtils bugFileUtils = new BugFileUtils(UiController.this.mContext);
                String selectedComponent = ((CharSequence) UiController.this.mSourceComponentList.get(UiController.this.mComponentSpinner.getSelectedItemPosition())).toString();
                Extradata extradata = new Extradata();
                extradata.source = UiController.this.mSource;
                extradata.shouldUploadScreenshot = UiController.this.mScreenshotConsentCheckBox.isChecked();
                extradata.shouldUploadPastAudioData = selectedComponent.equals(UiController.COMPONENT_AUDIO) && UiController.this.mPastAudioDataConsentCheckBox.isChecked();
                extradata.extraMedia = TextUtils.join(",", (Iterable) UiController.this.mExtraMedia.stream().map($$Lambda$UiController$2$YPoDTZX3WMEExj8szXWAbFftyFY.INSTANCE).collect(Collectors.toList()));
                extradata.appId = SystemInfoUtils.getComponentAppId(selectedComponent);
                extradata.categoryId = SystemInfoUtils.getComponentCategoryId(selectedComponent);
                Extradata.writeToFile(new File(bugFileUtils.reportFilename(UiController.this.mBugId, BugFileUtils.EXTRADATA)), extradata);
                boolean infiniteOfficePlatformEnabled = UiController.this.mSettingsManager.getBoolean("productivity_mode_enabled", false);
                StringBuilder descriptionBuilder = new StringBuilder();
                descriptionBuilder.append(String.format("Description: %s", UiController.this.mBugDescriptionEditText.getText()));
                descriptionBuilder.append("\n");
                Object[] objArr = new Object[1];
                objArr[0] = UiController.this.mBugTypeRadionButton.isChecked() ? "Bug" : "Feedback";
                descriptionBuilder.append(String.format("Type: %s", objArr));
                descriptionBuilder.append("\n");
                descriptionBuilder.append(String.format("Component: %s", selectedComponent));
                descriptionBuilder.append("\n");
                Object[] objArr2 = new Object[1];
                objArr2[0] = infiniteOfficePlatformEnabled ? "Yes" : "No";
                descriptionBuilder.append(String.format("Using Infinite Office Platform: %s", objArr2));
                descriptionBuilder.append("\n");
                FileOutputStream os = null;
                try {
                    os = new FileOutputStream(new File(bugFileUtils.reportFilename(UiController.this.mBugId, BugFileUtils.DESCRIPTION)));
                    os.write(descriptionBuilder.toString().getBytes());
                } catch (IOException e) {
                    Log.e(UiController.TAG, "Couldn't save description file.", e);
                } catch (Throwable th) {
                    FileUtils.closeQuietly(os);
                    throw th;
                }
                FileUtils.closeQuietly(os);
                ContentValues values = new ContentValues();
                values.put(BugReportProvider.DESCRIPTION, (Boolean) true);
                ContentResolver contentResolver = UiController.this.mContext.getContentResolver();
                contentResolver.update(Uri.parse("content://com.oculus.bugreporter.provider/report/id/" + UiController.this.mBugId), values, null, null);
                ((Activity) UiController.this.mContext).finish();
                AnalyticsEvent event = new AnalyticsEvent(Constants.EVENT_BUGREPORT);
                event.setExtra(Constants.EXTRA_BUG_ID, UiController.this.mBugId).setExtra(Constants.EXTRA_STATE, Constants.STATE_SUBMITTED);
                UnifiedTelemetryLogger.getInstance(UiController.this.mContext).reportEvent(event, false);
                Log.d(UiController.TAG, "Submitted report " + UiController.this.mBugId);
            }
        });
        this.mCancelButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.bugreporter.UiController.AnonymousClass3 */

            public void onClick(View v) {
                AnalyticsEvent event = new AnalyticsEvent(Constants.EVENT_BUGREPORT);
                event.setExtra(Constants.EXTRA_STATE, Constants.STATE_SUBMISSION_CANCELED);
                UnifiedTelemetryLogger.getInstance(UiController.this.mContext).reportEvent(event, false);
                ((Activity) UiController.this.mContext).finish();
            }
        });
        this.mAddMediaButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.bugreporter.UiController.AnonymousClass4 */

            public void onClick(View v) {
                UiController.this.setUiState(1);
            }
        });
        this.mClearMediaButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.bugreporter.UiController.AnonymousClass5 */

            public void onClick(View v) {
                UiController.this.mExtraMedia.clear();
                UiController.this.refreshExtraMedia();
            }
        });
        this.mDescriptionRecorderButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.bugreporter.UiController.AnonymousClass6 */

            public void onClick(View v) {
                if (UiController.this.mDescriptionRecorderState == 0) {
                    UiController.this.setRecordingState(1);
                } else if (UiController.this.mDescriptionRecorderState == 1) {
                    UiController.this.setRecordingState(0);
                }
            }
        });
        this.mDescriptionRecorderButtonRemove.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.bugreporter.UiController.AnonymousClass7 */

            public void onClick(View v) {
                UiController.this.mDescriptionRecorder.delete(UiController.this.mBugId);
                UiController.this.mDescriptionRecordingFilenameTextView.setText("");
                UiController.this.mDescriptionRecorderButtonRemove.setVisibility(8);
            }
        });
        this.mComponentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /* class com.oculus.bugreporter.UiController.AnonymousClass8 */

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (parentView.getItemAtPosition(position).toString().equals(UiController.COMPONENT_AUDIO)) {
                    UiController.this.mPastAudioDataConsentView.setVisibility(0);
                } else {
                    UiController.this.mPastAudioDataConsentView.setVisibility(8);
                }
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void registerScreenshotObserver() {
        Uri screenshotUri = Uri.parse("content://com.oculus.bugreporter.provider/report/screenshot/id/" + this.mBugId);
        try {
            this.mContext.getContentResolver().unregisterContentObserver(this.mScreenshotObserver);
        } catch (Exception e) {
        }
        this.mContext.getContentResolver().registerContentObserver(screenshotUri, true, this.mScreenshotObserver);
        Cursor cursor = this.mContext.getContentResolver().query(screenshotUri, null, null, null, null);
        if (cursor.moveToNext()) {
            updateScreenshot();
        }
        cursor.close();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateScreenshot() {
        if (this.mBugId != null) {
            this.mScreenshotImageView.setImageURI(Uri.fromFile(new File(new BugFileUtils(this.mContext).reportFilename(this.mBugId, BugFileUtils.SCREENSHOT))));
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void refreshExtraMedia() {
        if (this.mExtraMedia.isEmpty()) {
            this.mExtraMediaTextView.setText(R.string.no_extra_media_added);
        } else {
            this.mExtraMediaTextView.setText(TextUtils.join(", ", (Iterable) this.mExtraMedia.stream().map($$Lambda$UiController$BldjeSLvfZRl1uzsfcXhpRrt268.INSTANCE).collect(Collectors.toList())));
        }
    }

    @Override // com.oculus.bugreporter.ImagePickerAdapter.ImageSelectedCallback
    public void onImageSelected(ImagePickerAdapter.ImageData data) {
        this.mExtraMedia.add(data);
        refreshExtraMedia();
        setUiState(0);
    }

    @Override // com.oculus.bugreporter.DescriptionRecorder.DescriptionRecorderCallback
    public void onDescriptionRecordingStarted(boolean success) {
        this.mDescriptionRecorderButton.setEnabled(true);
        if (success) {
            this.mDescriptionRecorderButton.setText(R.string.description_recorder_button_stop);
            this.mDescriptionRecordingFilenameTextView.setText(R.string.description_recorder_recording);
            this.mDescriptionRecorderState = 1;
            return;
        }
        this.mDescriptionRecordingFilenameTextView.setText("");
        this.mSubmitButton.setEnabled(true);
        this.mDescriptionRecorderState = 0;
    }

    private void setupPickImageView() {
        RecyclerView recyclerView = (RecyclerView) this.mPickImageView.findViewById(R.id.images_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.mContext);
        ((LinearLayoutManager) layoutManager).setOrientation(0);
        recyclerView.setLayoutManager(layoutManager);
        ImagePickerAdapter adapter = new ImagePickerAdapter(this.mContext);
        adapter.setImageSelectedCallback(this);
        recyclerView.setAdapter(adapter);
        this.mPickImageView.findViewById(R.id.cancel_image_picker_button).setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.bugreporter.UiController.AnonymousClass9 */

            public void onClick(View v) {
                UiController.this.setUiState(0);
            }
        });
    }
}
