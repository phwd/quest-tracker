package X;

import X.AnonymousClass0DC;
import X.AnonymousClass0DD;
import X.AnonymousClass0J2;
import X.AnonymousClass0JA;
import X.AnonymousClass0NO;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import androidx.core.app.NotificationCompat$BigTextStyle;
import com.facebook.GraphRequest;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.oculus.aidl.IBugReporterService;
import com.oculus.cloudstoragehelper.CloudStorageHelper;
import com.oculus.cloudstoragehelper.CloudStorageResult;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import com.oculus.executors.OculusThreadExecutor;
import com.oculus.horizon.R;
import com.oculus.horizon.abuse_prevention.AbuseReportFileUtils;
import com.oculus.horizon.abuse_prevention.TypedByteArrayWithFilename;
import com.oculus.horizon.abuse_prevention.VideoUploadStartResponse;
import com.oculus.horizon.abuse_prevention.VideoUploadSubmitResponse;
import com.oculus.horizon.abuse_prevention.VideoUploadTransferResponse;
import com.oculus.horizon.abuse_prevention.VideoUploaderException;
import com.oculus.horizon.abuse_prevention.VideoUploaderMethods;
import com.oculus.horizon.abuse_prevention.VideoUploaderService;
import com.oculus.horizon.abuse_prevention.VideoUploaderServiceManager;
import com.oculus.horizon.api.common.user.ProfilePhoto;
import com.oculus.horizon.cloudstorage2.CloudStorageIntentService;
import com.oculus.horizon.cloudstorage2.CloudStorageLogger;
import com.oculus.horizon.cloudstorage2.CloudStorageManager;
import com.oculus.horizon.cloudstorage2.TaskProgressReporter;
import com.oculus.horizon.cloudstorage2.model.CloudStorage2ResolutionPolicy;
import com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTaskProvider;
import com.oculus.horizon.cloudstorage2.task.DownloadSyncTask;
import com.oculus.horizon.cloudstorage2.task.DownloadSyncTaskProvider;
import com.oculus.horizon.cloudstorage2.task.DownloadWildcardsTaskProvider;
import com.oculus.horizon.cloudstorage2.task.ResolveConflictTask;
import com.oculus.horizon.logging.LoggingEvents;
import com.oculus.horizon.logging.OculusLogger;
import com.oculus.horizon.notifications.legacy.contract.NotificationsContract;
import com.oculus.horizon.platformplugin.PlatformPluginManager;
import com.oculus.horizon.profile.UserProfileHelper;
import com.oculus.horizon.remotewipe.RemoteWipeMethods;
import com.oculus.horizon.remotewipe.RemoteWipeService;
import com.oculus.horizon.usermanagerupdater.UserManagerUpdater;
import com.oculus.horizon.usermanagerupdater.UserManagerUpdaterService;
import com.oculus.horizon.vrbugreporter.BugReport;
import com.oculus.horizon.vrbugreporter.BugReporterService;
import com.oculus.horizon.vrbugreporter.PackageUtil;
import com.oculus.http.core.base.ApiException;
import com.oculus.ipc.common.Constants;
import com.oculus.ipc.common.ParcelableCallbackReceiver;
import com.oculus.ipc.service.CallerIdentityHandshakeServer;
import com.oculus.library.model.App;
import com.oculus.library.model.CloudStorageStatus;
import com.oculus.libraryapi.OVRLibrary;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;
import com.oculus.os.ICompanionServer;
import com.oculus.os.IRemoteWipeCallback;
import com.oculus.remotewipe.CompanionBinder;
import com.oculus.remotewipe.WipeRequester;
import com.oculus.remotewipe.WipeTelemetry;
import com.oculus.security.basecomponent.OculusPublicIntentService;
import com.oculus.signature.inject.SignatureCheck;
import com.oculus.signature.inject.SignatureChecker;
import com.oculus.userserver.api.OculusUserManager;
import com.oculus.userserver.api.inject.OculusUserManagerProvider;
import com.oculus.userserver.api.user.OculusUserBundler;
import com.oculus.userserver.api.user.SparseOculusUser;
import com.oculus.userserver.managerservice.IOculusUserManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import javax.annotation.Nullable;

/* renamed from: X.1U8  reason: invalid class name */
public abstract class AnonymousClass1U8 extends IntentService {
    public static final String SUB_ENDPOINT_ON_BIND = "onBind";
    public static final String SUB_ENDPOINT_ON_HANDLE_INTENT = "onHandleIntent";
    public String mEndpointName;
    public AbstractC02910bj mPermissionChecker = AbstractC00900Hh.A01;
    public final String name;

    public void A03(@Nullable Intent intent) {
        String action;
        String str;
        String str2;
        String str3;
        IOException e;
        int i;
        byte[] bArr;
        if (this instanceof UserManagerUpdaterService) {
            UserManagerUpdaterService userManagerUpdaterService = (UserManagerUpdaterService) this;
            if (intent != null && (action = intent.getAction()) != null) {
                if (action.hashCode() != 1555314383 || !action.equals(UserManagerUpdaterService.ACTION_UPDATE_USER_MANAGER)) {
                    AnonymousClass0NO.A0E(UserManagerUpdaterService.TAG, "Unsupported action: %s", action);
                    return;
                }
                UserManagerUpdater userManagerUpdater = (UserManagerUpdater) AnonymousClass0J2.A03(0, 122, userManagerUpdaterService._UL_mInjectionContext);
                String str4 = UserProfileHelper.A02(((UserProfileHelper) AnonymousClass0J2.A03(0, 68, userManagerUpdater._UL_mInjectionContext)).mPrefs).name;
                String str5 = UserProfileHelper.A02(((UserProfileHelper) AnonymousClass0J2.A03(0, 68, userManagerUpdater._UL_mInjectionContext)).mPrefs).alias;
                if (Strings.isNullOrEmpty(str4)) {
                    str4 = str5;
                }
                ProfilePhoto profilePhoto = UserProfileHelper.A02(((UserProfileHelper) AnonymousClass0J2.A03(0, 68, userManagerUpdater._UL_mInjectionContext)).mPrefs).profile_photo;
                if (profilePhoto != null) {
                    str = profilePhoto.uri;
                } else {
                    str = null;
                }
                SparseOculusUser.Builder builder = new SparseOculusUser.Builder();
                if (!Strings.isNullOrEmpty(str4)) {
                    builder.mUserName = str4;
                }
                String str6 = "";
                if (str != null) {
                    str6 = str;
                }
                builder.mPictureUri = str6;
                SparseOculusUser sparseOculusUser = new SparseOculusUser(builder);
                OculusUserManager oculusUserManager = new OculusUserManager((Context) AnonymousClass0J2.A03(0, 294, ((OculusUserManagerProvider) AnonymousClass0J2.A03(1, 116, userManagerUpdater._UL_mInjectionContext))._UL_mInjectionContext));
                try {
                    IOculusUserManager A00 = OculusUserManager.A00(oculusUserManager);
                    Bundle bundle = new Bundle();
                    Integer num = sparseOculusUser.mUserId;
                    if (num != null) {
                        bundle.putInt("user_id", num.intValue());
                    }
                    String str7 = sparseOculusUser.mUserName;
                    if (str7 != null) {
                        bundle.putString("user_name", str7);
                    }
                    String str8 = sparseOculusUser.mPictureUri;
                    if (str8 != null) {
                        bundle.putString(OculusUserBundler.KEY_PICTURE_URI, str8);
                    }
                    A00.A9j(bundle);
                    try {
                        oculusUserManager.close();
                        return;
                    } catch (RemoteException | RuntimeException e2) {
                        ((IErrorReporter) AnonymousClass0J2.A03(2, 428, userManagerUpdater._UL_mInjectionContext)).A97("UserManagerUpdater", "Error updating user manager", e2);
                        return;
                    }
                } catch (Throwable unused) {
                }
            } else {
                return;
            }
        } else if (this instanceof RemoteWipeService) {
            RemoteWipeService remoteWipeService = (RemoteWipeService) this;
            if (intent == null) {
                str2 = RemoteWipeService.FAIL_NULL_INTENT;
            } else {
                WipeRequester wipeRequester = (WipeRequester) intent.getParcelableExtra(RemoteWipeService.KEY_WIPE_REQUESTER);
                if (wipeRequester == null) {
                    str2 = RemoteWipeService.FAIL_NO_REQUESTER;
                } else if (wipeRequester.mAttemptNum > 3) {
                    RemoteWipeService.A00(remoteWipeService, wipeRequester, RemoteWipeService.FAIL_MAX_TRIES);
                    return;
                } else {
                    try {
                        if (!((RemoteWipeMethods) AnonymousClass0J2.A03(1, 257, remoteWipeService._UL_mInjectionContext)).A00()) {
                            RemoteWipeService.A01(remoteWipeService, wipeRequester, RemoteWipeService.FAIL_NO_PENDING, null);
                            return;
                        }
                        CompanionBinder companionBinder = (CompanionBinder) AnonymousClass0J2.A03(0, 383, remoteWipeService._UL_mInjectionContext);
                        CompanionBinder.AnonymousClass1 r7 = new ServiceConnection(remoteWipeService.mCallback, wipeRequester) {
                            /* class com.oculus.remotewipe.CompanionBinder.AnonymousClass1 */
                            public final /* synthetic */ IRemoteWipeCallback val$callback;
                            public final /* synthetic */ WipeRequester val$requester;

                            {
                                this.val$callback = r2;
                                this.val$requester = r3;
                            }

                            public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                                ICompanionServer asInterface = ICompanionServer.Stub.asInterface(iBinder);
                                new OculusThreadExecutor().execute(new Runnable(asInterface, this.val$callback, this.val$requester, this) {
                                    /* class com.oculus.remotewipe.CompanionBinder.AnonymousClass2 */
                                    public final /* synthetic */ IRemoteWipeCallback val$callback;
                                    public final /* synthetic */ ICompanionServer val$companion;
                                    public final /* synthetic */ ServiceConnection val$connection;
                                    public final /* synthetic */ WipeRequester val$requester;

                                    public final void run() {
                                        try {
                                            this.val$companion.performRemoteWipe(this.val$callback);
                                        } catch (RemoteException e) {
                                            AnonymousClass0NO.A0B(CompanionBinder.TAG, "RemoteException happened", e);
                                            WipeRequester wipeRequester = this.val$requester;
                                            WipeTelemetry.A01(CompanionBinder.TAG, wipeRequester, CompanionBinder.FAIL_REMOTE_EXCEPTION, e, true);
                                            WipeTelemetry.A00((WipeTelemetry) AnonymousClass0J2.A03(1, 191, CompanionBinder.this._UL_mInjectionContext), WipeTelemetry.EVENT_WIPE_RETRY, wipeRequester, CompanionBinder.FAIL_REMOTE_EXCEPTION, e);
                                            ((IRemoteWipeManager) AnonymousClass0J2.A03(2, 558, CompanionBinder.this._UL_mInjectionContext)).A8a(this.val$requester);
                                        } catch (Throwable th) {
                                            ((Context) AnonymousClass0J2.A03(0, 294, CompanionBinder.this._UL_mInjectionContext)).unbindService(this.val$connection);
                                            throw th;
                                        }
                                        ((Context) AnonymousClass0J2.A03(0, 294, CompanionBinder.this._UL_mInjectionContext)).unbindService(this.val$connection);
                                    }

                                    {
                                        this.val$companion = r2;
                                        this.val$callback = r3;
                                        this.val$requester = r4;
                                        this.val$connection = r5;
                                    }
                                });
                            }

                            public final void onServiceDisconnected(ComponentName componentName) {
                            }
                        };
                        Intent intent2 = new Intent();
                        intent2.setComponent(new ComponentName("com.oculus.companion.server", "com.oculus.companion.server.CompanionServer"));
                        if (!((Context) AnonymousClass0J2.A03(0, 294, companionBinder._UL_mInjectionContext)).bindService(intent2, r7, 1)) {
                            WipeTelemetry.A01(CompanionBinder.TAG, wipeRequester, CompanionBinder.FAIL_BIND_FALSE, null, false);
                            WipeTelemetry.A00((WipeTelemetry) AnonymousClass0J2.A03(1, 191, companionBinder._UL_mInjectionContext), WipeTelemetry.EVENT_WIPE_FAIL, wipeRequester, CompanionBinder.FAIL_BIND_FALSE, null);
                            return;
                        }
                        return;
                    } catch (ApiException e3) {
                        RemoteWipeService.A01(remoteWipeService, wipeRequester, AnonymousClass006.A05(RemoteWipeService.FAIL_API_ERROR, e3.mApiError.type.name()), e3);
                        return;
                    }
                }
            }
            RemoteWipeService.A00(remoteWipeService, null, str2);
            return;
        } else if (!(this instanceof CloudStorageIntentService)) {
            VideoUploaderService videoUploaderService = (VideoUploaderService) this;
            String stringExtra = intent.getStringExtra("report_id");
            String stringExtra2 = intent.getStringExtra("recording_uuid");
            OculusLogger.VideoUploaderLogger videoUploaderLogger = new OculusLogger.VideoUploaderLogger(stringExtra2, stringExtra);
            OculusLogger.VideoUploaderLogger.A00(videoUploaderLogger, ((EventManager) AnonymousClass0J2.A03(0, 242, OculusLogger.this._UL_mInjectionContext)).A22(LoggingEvents.ABUSE_VIDEO_UPLOAD_START));
            if (stringExtra != null) {
                try {
                    if (!stringExtra.isEmpty()) {
                        if (stringExtra2 == null || stringExtra2.isEmpty()) {
                            throw new VideoUploaderException("no recording uuid provided; make sure to specify in your intent");
                        } else if (stringExtra.matches("^\\d{1,20}$")) {
                            try {
                                UUID fromString = UUID.fromString(stringExtra2);
                                if (fromString.toString().equals(stringExtra2)) {
                                    Intent intent3 = new Intent();
                                    intent3.putExtra("report_id", stringExtra);
                                    intent3.putExtra("recording_uuid", stringExtra2);
                                    intent3.setComponent(new ComponentName(videoUploaderService, videoUploaderService.getClass()));
                                    String stringExtra3 = intent3.getStringExtra("report_id");
                                    String stringExtra4 = intent3.getStringExtra("recording_uuid");
                                    String string = videoUploaderService.getString(R.string.abuse_prevention_upload_in_progress_title);
                                    String string2 = videoUploaderService.getString(R.string.abuse_prevention_upload_in_progress_message);
                                    AnonymousClass03h r1 = new AnonymousClass03h(videoUploaderService, null);
                                    r1.A0E = AnonymousClass03h.A00(string);
                                    r1.A0D = AnonymousClass03h.A00(string2);
                                    NotificationCompat$BigTextStyle notificationCompat$BigTextStyle = new NotificationCompat$BigTextStyle();
                                    notificationCompat$BigTextStyle.bigText(string2);
                                    r1.A06(notificationCompat$BigTextStyle);
                                    r1.A09.icon = R.drawable.status_icon;
                                    r1.A07(string);
                                    videoUploaderService.startForeground(NotificationsContract.VIDEO_UPLOADER_ID, r1.A01());
                                    NotificationManager notificationManager = (NotificationManager) videoUploaderService.getSystemService("notification");
                                    boolean z = videoUploaderService.mVRUtils.mStandaloneDevice;
                                    try {
                                        VideoUploaderServiceManager videoUploaderServiceManager = videoUploaderService.mVideoUploaderServiceManager;
                                        try {
                                            File file = new File(new File(videoUploaderService.getCacheDir(), AbuseReportFileUtils.UPLOADING_PATH), stringExtra3);
                                            AbuseReportFileUtils.A04(file);
                                            File file2 = new File(videoUploaderService.getCacheDir(), AnonymousClass006.A07(AbuseReportFileUtils.STAGING_PATH, File.separator, stringExtra4));
                                            File file3 = new File(file2, AbuseReportFileUtils.VIDEO_RECORDING_FILE);
                                            File file4 = new File(file, AbuseReportFileUtils.VIDEO_RECORDING_FILE);
                                            if (file4.exists()) {
                                                file3.getCanonicalPath();
                                                file4.getCanonicalPath();
                                            } else {
                                                C07590uC.A00(file3, file4);
                                            }
                                            if (file2.exists() && !file2.delete()) {
                                                AnonymousClass0NO.A0E(AbuseReportFileUtils.TAG, "failed to delete %s", file2.getCanonicalPath());
                                            }
                                            File file5 = new File(file, AbuseReportFileUtils.VIDEO_RECORDING_FILE);
                                            VideoUploaderMethods videoUploaderMethods = videoUploaderServiceManager.mRestMethods;
                                            try {
                                                FileInputStream fileInputStream = new FileInputStream(file5);
                                                videoUploaderMethods.mThreadUtils.A05();
                                                long length = file5.length();
                                                VideoUploadStartResponse videoUploadStartResponse = (VideoUploadStartResponse) new VideoUploaderMethods.Retryable<VideoUploadStartResponse>(file5.getName(), length) {
                                                    /* class com.oculus.horizon.abuse_prevention.VideoUploaderMethods.AnonymousClass1 */
                                                    public final /* synthetic */ int val$chunkSize = 2000000;
                                                    public final /* synthetic */ String val$fileName;
                                                    public final /* synthetic */ long val$fileSize;
                                                    public final /* synthetic */ String val$mimeType = "video/mp4";

                                                    {
                                                        this.val$fileName = r4;
                                                        this.val$fileSize = r5;
                                                    }
                                                }.A00(videoUploaderLogger);
                                                videoUploaderLogger.uploadSessionID = videoUploadStartResponse.upload_session_id;
                                                byte[] bArr2 = new byte[2000000];
                                                char c = 0;
                                                int i2 = 0;
                                                while (true) {
                                                    try {
                                                        int read = fileInputStream.read(bArr2);
                                                        if (read > 0) {
                                                            long[] jArr = new long[2];
                                                            jArr[c] = 2000000;
                                                            long j = (long) i2;
                                                            jArr[1] = length - j;
                                                            Preconditions.checkArgument(true);
                                                            long j2 = jArr[c];
                                                            long j3 = jArr[1];
                                                            if (j3 < j2) {
                                                                j2 = j3;
                                                            }
                                                            int i3 = (int) j2;
                                                            if (read == i3) {
                                                                if (read < 2000000) {
                                                                    bArr = Arrays.copyOf(bArr2, read);
                                                                } else {
                                                                    bArr = bArr2;
                                                                }
                                                                String str9 = videoUploadStartResponse.upload_session_id;
                                                                i = 2;
                                                                try {
                                                                    new VideoUploaderMethods.Retryable<VideoUploadTransferResponse>(str9, j, new TypedByteArrayWithFilename(bArr, file5.getName())) {
                                                                        /* class com.oculus.horizon.abuse_prevention.VideoUploaderMethods.AnonymousClass2 */
                                                                        public final /* synthetic */ TypedByteArrayWithFilename val$chunk;
                                                                        public final /* synthetic */ long val$startOffset;
                                                                        public final /* synthetic */ String val$uploadSessionId;

                                                                        {
                                                                            this.val$uploadSessionId = r2;
                                                                            this.val$startOffset = r3;
                                                                            this.val$chunk = r5;
                                                                        }
                                                                    }.A00(videoUploaderLogger);
                                                                    i2 += read;
                                                                    c = 0;
                                                                } catch (IOException e4) {
                                                                    e = e4;
                                                                    try {
                                                                        Object[] objArr = new Object[i];
                                                                        objArr[0] = file5.getName();
                                                                        objArr[1] = e;
                                                                        throw new VideoUploaderException(e, "I/O error while reading %s", objArr);
                                                                    } catch (Throwable th) {
                                                                        try {
                                                                            fileInputStream.close();
                                                                        } catch (IOException e5) {
                                                                            AnonymousClass0NO.A0B(VideoUploaderMethods.TAG, "could not close the input stream", e5);
                                                                        }
                                                                        throw th;
                                                                    }
                                                                }
                                                            } else {
                                                                throw new VideoUploaderException("expected %s bytes, got %s", Integer.valueOf(read), Integer.valueOf(i3));
                                                            }
                                                        } else {
                                                            fileInputStream.close();
                                                            try {
                                                                fileInputStream.close();
                                                            } catch (IOException e6) {
                                                                AnonymousClass0NO.A0B(VideoUploaderMethods.TAG, "could not close the input stream", e6);
                                                            }
                                                            if (((VideoUploadSubmitResponse) new VideoUploaderMethods.Retryable<VideoUploadSubmitResponse>(stringExtra3, videoUploadStartResponse.upload_session_id) {
                                                                /* class com.oculus.horizon.abuse_prevention.VideoUploaderMethods.AnonymousClass3 */
                                                                public final /* synthetic */ String val$targetObjectId;
                                                                public final /* synthetic */ String val$uploadSessionId;
                                                                public final /* synthetic */ String val$videoType = VideoUploaderMethods.ABUSE_REPORT_VIDEO;

                                                                {
                                                                    this.val$targetObjectId = r3;
                                                                    this.val$uploadSessionId = r4;
                                                                }
                                                            }.A00(videoUploaderLogger)).success) {
                                                                AbuseReportFileUtils.A03(file);
                                                                String string3 = videoUploaderService.getString(R.string.abuse_prevention_upload_succeeded_title);
                                                                String string4 = videoUploaderService.getString(R.string.abuse_prevention_upload_succeeded_message);
                                                                AnonymousClass03h r12 = new AnonymousClass03h(videoUploaderService, null);
                                                                r12.A0E = AnonymousClass03h.A00(string3);
                                                                r12.A0D = AnonymousClass03h.A00(string4);
                                                                NotificationCompat$BigTextStyle notificationCompat$BigTextStyle2 = new NotificationCompat$BigTextStyle();
                                                                notificationCompat$BigTextStyle2.bigText(string4);
                                                                r12.A06(notificationCompat$BigTextStyle2);
                                                                r12.A09.icon = R.drawable.status_icon;
                                                                r12.A07(string3);
                                                                r12.A02();
                                                                if (z) {
                                                                    r12.A06 = 1;
                                                                }
                                                                notificationManager.notify(stringExtra3, NotificationsContract.VIDEO_UPLOADER_ID, r12.A01());
                                                                OculusLogger.VideoUploaderLogger.A00(videoUploaderLogger, ((EventManager) AnonymousClass0J2.A03(0, 242, OculusLogger.this._UL_mInjectionContext)).A22(LoggingEvents.ABUSE_VIDEO_UPLOAD_SUCCESS));
                                                                return;
                                                            }
                                                            throw new VideoUploaderException("VideoUploadSubmitResponse.success == false");
                                                        }
                                                    } catch (IOException e7) {
                                                        e = e7;
                                                        i = 2;
                                                        Object[] objArr2 = new Object[i];
                                                        objArr2[0] = file5.getName();
                                                        objArr2[1] = e;
                                                        throw new VideoUploaderException(e, "I/O error while reading %s", objArr2);
                                                    }
                                                }
                                            } catch (FileNotFoundException e8) {
                                                throw new VideoUploaderException(e8, "Video file to upload not found", new Object[0]);
                                            }
                                        } catch (IOException e9) {
                                            throw new VideoUploaderException(e9);
                                        }
                                    } catch (VideoUploaderException e10) {
                                        PendingIntent service = PendingIntent.getService(videoUploaderService, stringExtra3.hashCode() + stringExtra4.hashCode(), intent3, 0);
                                        String string5 = videoUploaderService.getString(R.string.abuse_prevention_upload_failed_title);
                                        AnonymousClass03h r2 = new AnonymousClass03h(videoUploaderService, null);
                                        r2.A0E = AnonymousClass03h.A00(string5);
                                        r2.A09.icon = R.drawable.status_icon;
                                        r2.A0A = service;
                                        r2.A07(string5);
                                        if (z) {
                                            r2.A06 = 1;
                                            r2.A0D = AnonymousClass03h.A00(videoUploaderService.getString(R.string.abuse_prevention_upload_failed_message_no_retry));
                                        } else {
                                            String string6 = videoUploaderService.getString(R.string.abuse_prevention_upload_failed_message);
                                            r2.A0D = AnonymousClass03h.A00(string6);
                                            NotificationCompat$BigTextStyle notificationCompat$BigTextStyle3 = new NotificationCompat$BigTextStyle();
                                            notificationCompat$BigTextStyle3.bigText(string6);
                                            r2.A06(notificationCompat$BigTextStyle3);
                                        }
                                        notificationManager.notify(stringExtra3, NotificationsContract.VIDEO_UPLOADER_ID, r2.A01());
                                        throw e10;
                                    }
                                } else {
                                    throw new VideoUploaderException("recording UUID '%s' does not match parsed '%s'", stringExtra2, fromString.toString());
                                }
                            } catch (IllegalArgumentException e11) {
                                throw new VideoUploaderException(e11, "recording UUID '%s' is not a valid uuid", stringExtra2);
                            }
                        } else {
                            throw new VideoUploaderException("report ID '%s' is not a valid id", stringExtra);
                        }
                    }
                } catch (VideoUploaderException e12) {
                    AnonymousClass0NO.A0B(VideoUploaderService.TAG, "Video uploading failed", e12);
                    StringWriter stringWriter = new StringWriter();
                    PrintWriter printWriter = new PrintWriter(stringWriter);
                    e12.printStackTrace(printWriter);
                    printWriter.flush();
                    String obj = stringWriter.toString();
                    Event A22 = ((EventManager) AnonymousClass0J2.A03(0, 242, OculusLogger.this._UL_mInjectionContext)).A22(LoggingEvents.ABUSE_VIDEO_UPLOAD_FAILURE);
                    A22.A15("reason", obj);
                    OculusLogger.VideoUploaderLogger.A00(videoUploaderLogger, A22);
                    return;
                }
            }
            throw new VideoUploaderException("no abuse report id provided; make sure to specify in your intent");
        } else {
            CloudStorageIntentService cloudStorageIntentService = (CloudStorageIntentService) this;
            if (intent != null) {
                try {
                    Bundle extras = intent.getExtras();
                    if (!extras.containsKey("app_id")) {
                        throw new IllegalArgumentException("Application ID was not provided!");
                    } else if (!extras.containsKey(CloudStorageHelper.EXTRA_KEY_WORK_TYPE)) {
                        throw new IllegalArgumentException("Work type was not provided!");
                    } else if (extras.getParcelable(CloudStorageHelper.EXTRA_KEY_CALLBACK_RECEIVER) != null) {
                        String string7 = extras.getString("app_id");
                        CloudStorageHelper.RunType valueOf = CloudStorageHelper.RunType.valueOf(extras.getString(CloudStorageHelper.EXTRA_KEY_WORK_TYPE));
                        ParcelableCallbackReceiver parcelableCallbackReceiver = new ParcelableCallbackReceiver(extras.getParcelable(CloudStorageHelper.EXTRA_KEY_CALLBACK_RECEIVER));
                        try {
                            CallerIdentityHandshakeServer callerIdentityHandshakeServer = new CallerIdentityHandshakeServer();
                            IBinder binder = extras.getBinder(Constants.EXTRA_KEY_HANDSHAKE_BINDER);
                            if (binder != null) {
                                CallerIdentityHandshakeServer.HandshakeBinder handshakeBinder = new CallerIdentityHandshakeServer.HandshakeBinder();
                                Parcel obtain = Parcel.obtain();
                                obtain.writeStrongBinder(handshakeBinder);
                                binder.transact(1, obtain, null, 0);
                                int i4 = callerIdentityHandshakeServer.mCallingUid;
                                if (i4 != -1) {
                                    SignatureChecker signatureChecker = cloudStorageIntentService.mSignatureChecker;
                                    String[] packagesForUid = ((Context) AnonymousClass0J2.A03(0, 294, signatureChecker._UL_mInjectionContext)).getPackageManager().getPackagesForUid(i4);
                                    if (packagesForUid == null || packagesForUid.length == 0) {
                                        str3 = null;
                                    } else {
                                        str3 = packagesForUid[0];
                                    }
                                    SignatureCheck signatureCheck = new SignatureCheck(signatureChecker.mSignatureCheckProvider, str3);
                                    if (signatureCheck.A00()) {
                                        switch (valueOf.ordinal()) {
                                            case 0:
                                                cloudStorageIntentService.mCloudStorageLogger.A00(string7, CloudStorageLogger.SERVICE_OPERATION_LAUNCH_SYNC);
                                                CountDownLatch countDownLatch = new CountDownLatch(1);
                                                DownloadSyncTask downloadSyncTask = new DownloadSyncTask(cloudStorageIntentService.mDownloadSyncTaskProvider, string7);
                                                downloadSyncTask.mReporter.mProgressListener = new TaskProgressReporter.ProgressListener(parcelableCallbackReceiver) {
                                                    /* class com.oculus.horizon.cloudstorage2.CloudStorageIntentService.AnonymousClass1 */
                                                    public final /* synthetic */ ParcelableCallbackReceiver val$receiver;

                                                    {
                                                        this.val$receiver = r2;
                                                    }

                                                    @Override // com.oculus.horizon.cloudstorage2.TaskProgressReporter.ProgressListener
                                                    public final void A6d(float f) {
                                                        this.val$receiver.A00(new CloudStorageResult(CloudStorageResult.SyncResultType.PROGRESS, null, f, -1, -1));
                                                    }
                                                };
                                                downloadSyncTask.A00(false).A09(new AnonymousClass0D4<Void, Void>(downloadSyncTask, parcelableCallbackReceiver, countDownLatch) {
                                                    /* class com.oculus.horizon.cloudstorage2.CloudStorageIntentService.AnonymousClass2 */
                                                    public final /* synthetic */ DownloadSyncTask val$downloadSyncTask;
                                                    public final /* synthetic */ CountDownLatch val$latch;
                                                    public final /* synthetic */ ParcelableCallbackReceiver val$receiver;

                                                    {
                                                        this.val$downloadSyncTask = r2;
                                                        this.val$receiver = r3;
                                                        this.val$latch = r4;
                                                    }

                                                    /* Return type fixed from 'java.lang.Object' to match base method */
                                                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
                                                    @Override // X.AnonymousClass0D4
                                                    public final Void then(AnonymousClass0DC<Void> r11) throws Exception {
                                                        ParcelableCallbackReceiver parcelableCallbackReceiver;
                                                        CloudStorageResult.SyncResultType syncResultType;
                                                        String str;
                                                        float f;
                                                        long j;
                                                        long j2;
                                                        if (r11.A0K()) {
                                                            ConflictData conflictData = this.val$downloadSyncTask.mConflictData;
                                                            if (conflictData != null) {
                                                                parcelableCallbackReceiver = this.val$receiver;
                                                                j = conflictData.localTimestamp;
                                                                j2 = conflictData.remoteTimestamp;
                                                                syncResultType = CloudStorageResult.SyncResultType.CONFLICT;
                                                                str = null;
                                                                f = 1.0f;
                                                            } else {
                                                                this.val$receiver.A00(new CloudStorageResult(CloudStorageResult.SyncResultType.FAILURE, r11.A0F().getMessage(), 1.0f, -1, -1));
                                                                this.val$latch.countDown();
                                                                return null;
                                                            }
                                                        } else {
                                                            parcelableCallbackReceiver = this.val$receiver;
                                                            syncResultType = CloudStorageResult.SyncResultType.SUCCESS;
                                                            str = null;
                                                            f = 1.0f;
                                                            j = -1;
                                                            j2 = -1;
                                                        }
                                                        parcelableCallbackReceiver.A00(new CloudStorageResult(syncResultType, str, f, j, j2));
                                                        this.val$latch.countDown();
                                                        return null;
                                                    }
                                                });
                                                try {
                                                    countDownLatch.await();
                                                    return;
                                                } catch (InterruptedException e13) {
                                                    AnonymousClass0NO.A03(CloudStorageIntentService.class, "Launch sync latch was interrupted!", e13);
                                                    return;
                                                }
                                            case 1:
                                                if (extras.containsKey(CloudStorageHelper.EXTRA_KEY_CONFLICT_RESOLUTION)) {
                                                    CloudStorageHelper.ResolutionType valueOf2 = CloudStorageHelper.ResolutionType.valueOf(extras.getString(CloudStorageHelper.EXTRA_KEY_CONFLICT_RESOLUTION));
                                                    cloudStorageIntentService.mCloudStorageLogger.A00(string7, valueOf2.name().toLowerCase());
                                                    CountDownLatch countDownLatch2 = new CountDownLatch(1);
                                                    ResolveConflictTask resolveConflictTask = new ResolveConflictTask(cloudStorageIntentService.mResolveConflictTaskProvider, string7, CloudStorage2ResolutionPolicy.from(valueOf2));
                                                    resolveConflictTask.mReporter.mProgressListener = new TaskProgressReporter.ProgressListener(parcelableCallbackReceiver) {
                                                        /* class com.oculus.horizon.cloudstorage2.CloudStorageIntentService.AnonymousClass3 */
                                                        public final /* synthetic */ ParcelableCallbackReceiver val$receiver;

                                                        {
                                                            this.val$receiver = r2;
                                                        }

                                                        @Override // com.oculus.horizon.cloudstorage2.TaskProgressReporter.ProgressListener
                                                        public final void A6d(float f) {
                                                            this.val$receiver.A00(new CloudStorageResult(CloudStorageResult.SyncResultType.PROGRESS, null, f, -1, -1));
                                                        }
                                                    };
                                                    AnonymousClass0DD r6 = new AnonymousClass0DD();
                                                    App A02 = ((OVRLibrary) AnonymousClass0J2.A03(1, 569, resolveConflictTask._UL_mInjectionContext)).A02(resolveConflictTask.mAppId);
                                                    AnonymousClass0DC.A01(AnonymousClass0DC.A07(new Callable<Void>(A02) {
                                                        /* class com.oculus.horizon.cloudstorage2.task.ResolveConflictTask.AnonymousClass5 */
                                                        public final /* synthetic */ App val$app;

                                                        {
                                                            this.val$app = r2;
                                                        }

                                                        /* Return type fixed from 'java.lang.Object' to match base method */
                                                        @Override // java.util.concurrent.Callable
                                                        public final Void call() throws Exception {
                                                            String str;
                                                            ResolveConflictTask.this.mReporter.A02(Step.PREFLIGHT_CHECK);
                                                            App app = this.val$app;
                                                            if (app != null) {
                                                                ResolveConflictTask.this.mReporter.A01(app);
                                                                ResolveConflictTask resolveConflictTask = ResolveConflictTask.this;
                                                                CloudStorageStatus cloudStorageStatus = this.val$app.cloudStorageStatus;
                                                                new Object[1][0] = cloudStorageStatus.name();
                                                                if (!CloudStorageStatus.isEnabled(cloudStorageStatus)) {
                                                                    str = "Cloud syncing is not enabled for this app!";
                                                                } else if (CloudStorageStatus.isSyncing(cloudStorageStatus)) {
                                                                    str = "There is an existing sync running for this app!";
                                                                } else if (((CloudStorageManager) AnonymousClass0J2.A03(0, 73, resolveConflictTask._UL_mInjectionContext)).A07()) {
                                                                    return null;
                                                                } else {
                                                                    str = "External storage permission needs to be granted";
                                                                }
                                                                throw new IllegalStateException(str);
                                                            }
                                                            throw new IllegalArgumentException("App was not found in library!");
                                                        }
                                                    }, OculusThreadExecutor.A00(), null).A0D(new AnonymousClass0D4<Void, AnonymousClass0DC<String[]>>((DownloadWildcardsTaskProvider) AnonymousClass0J2.A04(270, resolveConflictTask._UL_mInjectionContext), A02) {
                                                        /* class com.oculus.horizon.cloudstorage2.task.ResolveConflictTask.AnonymousClass4 */
                                                        public final /* synthetic */ App val$app;
                                                        public final /* synthetic */ DownloadWildcardsTaskProvider val$downloadWildcardsProvider;

                                                        {
                                                            this.val$downloadWildcardsProvider = r2;
                                                            this.val$app = r3;
                                                        }

                                                        /* Return type fixed from 'java.lang.Object' to match base method */
                                                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
                                                        @Override // X.AnonymousClass0D4
                                                        public final AnonymousClass0DC<String[]> then(AnonymousClass0DC<Void> r5) throws Exception {
                                                            ResolveConflictTask.this.mReporter.A02(Step.DOWNLOAD_PATTERNS);
                                                            return new DownloadWildcardsTask(this.val$downloadWildcardsProvider, this.val$app.applicationGroupingId, ResolveConflictTask.this.mReporter).A00();
                                                        }
                                                    }, OculusThreadExecutor.A00()).A0D(new AnonymousClass0D4<String[], AnonymousClass0DC<Void>>(A02) {
                                                        /* class com.oculus.horizon.cloudstorage2.task.ResolveConflictTask.AnonymousClass3 */
                                                        public final /* synthetic */ App val$app;

                                                        {
                                                            this.val$app = r2;
                                                        }

                                                        /* Return type fixed from 'java.lang.Object' to match base method */
                                                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
                                                        @Override // X.AnonymousClass0D4
                                                        public final AnonymousClass0DC<Void> then(AnonymousClass0DC<String[]> r5) throws Exception {
                                                            ResolveConflictTask.this.mReporter.A02(Step.EXISTING_CONFLICT_CHECK);
                                                            if (PlatformPluginManager.nativeGetCloudStorage2DownloadConflictFiles(ResolveConflictTask.this.mGson.A06(((CloudStorageManager) AnonymousClass0J2.A03(0, 73, ResolveConflictTask.this._UL_mInjectionContext)).A01(this.val$app)), ((CloudStorageManager) AnonymousClass0J2.A03(0, 73, ResolveConflictTask.this._UL_mInjectionContext)).A03(this.val$app, r5.A0G())).length == 0) {
                                                                return AnonymousClass0DC.A06;
                                                            }
                                                            return AnonymousClass0DC.A04(null);
                                                        }
                                                    }, OculusThreadExecutor.A00()).A0D(new AnonymousClass0D4<Void, AnonymousClass0DC<Void>>((DownloadSyncTaskProvider) AnonymousClass0J2.A04(118, resolveConflictTask._UL_mInjectionContext), A02, (DownloadMetadataToFileTaskProvider) AnonymousClass0J2.A04(133, resolveConflictTask._UL_mInjectionContext)) {
                                                        /* class com.oculus.horizon.cloudstorage2.task.ResolveConflictTask.AnonymousClass2 */
                                                        public final /* synthetic */ App val$app;
                                                        public final /* synthetic */ DownloadMetadataToFileTaskProvider val$downloadMetadataToFileTaskProvider;
                                                        public final /* synthetic */ DownloadSyncTaskProvider val$downloadSyncTaskProvider;

                                                        {
                                                            this.val$downloadSyncTaskProvider = r2;
                                                            this.val$app = r3;
                                                            this.val$downloadMetadataToFileTaskProvider = r4;
                                                        }

                                                        /* Return type fixed from 'java.lang.Object' to match base method */
                                                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
                                                        @Override // X.AnonymousClass0D4
                                                        public final /* bridge */ /* synthetic */ AnonymousClass0DC<Void> then(AnonymousClass0DC<Void> r7) throws Exception {
                                                            ResolveConflictTask.this.mReporter.A02(Step.EXECUTE_POLICY);
                                                            ResolveConflictTask resolveConflictTask = ResolveConflictTask.this;
                                                            TaskProgressReporter<Step> taskProgressReporter = resolveConflictTask.mReporter;
                                                            CloudStorage2ResolutionPolicy cloudStorage2ResolutionPolicy = resolveConflictTask.mPolicy;
                                                            new Object[1][0] = cloudStorage2ResolutionPolicy;
                                                            switch (cloudStorage2ResolutionPolicy.ordinal()) {
                                                                case 0:
                                                                    DownloadMetadataToFileTask downloadMetadataToFileTask = new DownloadMetadataToFileTask(this.val$downloadMetadataToFileTaskProvider, this.val$app, taskProgressReporter);
                                                                    return new DownloadMetadataTask((AnonymousClass0JA) AnonymousClass0J2.A04(31, downloadMetadataToFileTask._UL_mInjectionContext), downloadMetadataToFileTask.mApp.applicationGroupingId, downloadMetadataToFileTask).A00().A0B(
                                                                    /*  JADX ERROR: Method code generation error
                                                                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0078: RETURN  
                                                                          (wrap: X.0DC<TContinuationResult> : 0x0074: INVOKE  (r0v12 X.0DC<TContinuationResult>) = 
                                                                          (wrap: X.0DC<TContinuationResult> : 0x0067: INVOKE  (r2v3 X.0DC<TContinuationResult>) = 
                                                                          (wrap: X.0DC<com.oculus.horizon.api.cloudstorage2.UserCloudFilesResponse> : 0x005e: INVOKE  (r1v6 X.0DC<com.oculus.horizon.api.cloudstorage2.UserCloudFilesResponse>) = 
                                                                          (wrap: com.oculus.horizon.cloudstorage2.task.DownloadMetadataTask : 0x005b: CONSTRUCTOR  (r0v9 com.oculus.horizon.cloudstorage2.task.DownloadMetadataTask) = 
                                                                          (wrap: X.0JA : 0x0049: CHECK_CAST (r3v2 X.0JA) = (X.0JA) (wrap: java.lang.Object : 0x0045: INVOKE  (r3v1 java.lang.Object) = 
                                                                          (31 int)
                                                                          (wrap: X.0QC : 0x0043: IGET  (r0v6 X.0QC) = (r4v0 'downloadMetadataToFileTask' com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask) com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask._UL_mInjectionContext X.0QC)
                                                                         type: STATIC call: X.0J2.A04(int, X.0QC):java.lang.Object))
                                                                          (wrap: java.lang.String : 0x0057: IGET  (r1v5 java.lang.String) = 
                                                                          (wrap: com.oculus.library.model.App : 0x0055: IGET  (r0v8 com.oculus.library.model.App) = (r4v0 'downloadMetadataToFileTask' com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask) com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask.mApp com.oculus.library.model.App)
                                                                         com.oculus.library.model.App.applicationGroupingId java.lang.String)
                                                                          (r4v0 'downloadMetadataToFileTask' com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask)
                                                                         call: com.oculus.horizon.cloudstorage2.task.DownloadMetadataTask.<init>(X.0p5, java.lang.String, com.oculus.horizon.cloudstorage2.Reporter):void type: CONSTRUCTOR)
                                                                         type: VIRTUAL call: com.oculus.horizon.cloudstorage2.task.DownloadMetadataTask.A00():X.0DC)
                                                                          (wrap: com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask$1 : 0x0064: CONSTRUCTOR  (r0v10 com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask$1) = 
                                                                          (r4v0 'downloadMetadataToFileTask' com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask)
                                                                          (wrap: com.oculus.horizon.cloudstorage2.callable.WriteMetadataToFileProvider : 0x0053: CHECK_CAST (r2v2 com.oculus.horizon.cloudstorage2.callable.WriteMetadataToFileProvider) = (com.oculus.horizon.cloudstorage2.callable.WriteMetadataToFileProvider) (wrap: java.lang.Object : 0x004f: INVOKE  (r2v1 java.lang.Object) = 
                                                                          (444 int)
                                                                          (wrap: X.0QC : 0x004d: IGET  (r0v7 X.0QC) = (r4v0 'downloadMetadataToFileTask' com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask) com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask._UL_mInjectionContext X.0QC)
                                                                         type: STATIC call: X.0J2.A04(int, X.0QC):java.lang.Object))
                                                                         call: com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask.1.<init>(com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask, com.oculus.horizon.cloudstorage2.callable.WriteMetadataToFileProvider):void type: CONSTRUCTOR)
                                                                         type: VIRTUAL call: X.0DC.A0B(X.0D4):X.0DC)
                                                                          (wrap: com.oculus.horizon.cloudstorage2.task.ResolveConflictTask$2$1 : 0x006d: CONSTRUCTOR  (r1v7 com.oculus.horizon.cloudstorage2.task.ResolveConflictTask$2$1) = (r6v0 'this' com.oculus.horizon.cloudstorage2.task.ResolveConflictTask$2 A[IMMUTABLE_TYPE, THIS]) call: com.oculus.horizon.cloudstorage2.task.ResolveConflictTask.2.1.<init>(com.oculus.horizon.cloudstorage2.task.ResolveConflictTask$2):void type: CONSTRUCTOR)
                                                                          (wrap: com.oculus.executors.OculusThreadExecutor : 0x0070: INVOKE  (r0v11 com.oculus.executors.OculusThreadExecutor) =  type: STATIC call: com.oculus.executors.OculusThreadExecutor.A00():com.oculus.executors.OculusThreadExecutor)
                                                                         type: VIRTUAL call: X.0DC.A0C(X.0D4, java.util.concurrent.Executor):X.0DC)
                                                                         in method: com.oculus.horizon.cloudstorage2.task.ResolveConflictTask.2.then(X.0DC<java.lang.Void>):X.0DC<java.lang.Void>, file: classes2.dex
                                                                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                                                                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:217)
                                                                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:110)
                                                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:56)
                                                                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                                                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                                                                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:99)
                                                                        	at jadx.core.codegen.RegionGen.makeSwitch(RegionGen.java:274)
                                                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:65)
                                                                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                                                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                                                                        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:244)
                                                                        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:237)
                                                                        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:342)
                                                                        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:295)
                                                                        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:264)
                                                                        	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                                                        	at java.util.ArrayList.forEach(ArrayList.java:1259)
                                                                        	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                                                        	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                                                        Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0074: INVOKE  (r0v12 X.0DC<TContinuationResult>) = 
                                                                          (wrap: X.0DC<TContinuationResult> : 0x0067: INVOKE  (r2v3 X.0DC<TContinuationResult>) = 
                                                                          (wrap: X.0DC<com.oculus.horizon.api.cloudstorage2.UserCloudFilesResponse> : 0x005e: INVOKE  (r1v6 X.0DC<com.oculus.horizon.api.cloudstorage2.UserCloudFilesResponse>) = 
                                                                          (wrap: com.oculus.horizon.cloudstorage2.task.DownloadMetadataTask : 0x005b: CONSTRUCTOR  (r0v9 com.oculus.horizon.cloudstorage2.task.DownloadMetadataTask) = 
                                                                          (wrap: X.0JA : 0x0049: CHECK_CAST (r3v2 X.0JA) = (X.0JA) (wrap: java.lang.Object : 0x0045: INVOKE  (r3v1 java.lang.Object) = 
                                                                          (31 int)
                                                                          (wrap: X.0QC : 0x0043: IGET  (r0v6 X.0QC) = (r4v0 'downloadMetadataToFileTask' com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask) com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask._UL_mInjectionContext X.0QC)
                                                                         type: STATIC call: X.0J2.A04(int, X.0QC):java.lang.Object))
                                                                          (wrap: java.lang.String : 0x0057: IGET  (r1v5 java.lang.String) = 
                                                                          (wrap: com.oculus.library.model.App : 0x0055: IGET  (r0v8 com.oculus.library.model.App) = (r4v0 'downloadMetadataToFileTask' com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask) com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask.mApp com.oculus.library.model.App)
                                                                         com.oculus.library.model.App.applicationGroupingId java.lang.String)
                                                                          (r4v0 'downloadMetadataToFileTask' com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask)
                                                                         call: com.oculus.horizon.cloudstorage2.task.DownloadMetadataTask.<init>(X.0p5, java.lang.String, com.oculus.horizon.cloudstorage2.Reporter):void type: CONSTRUCTOR)
                                                                         type: VIRTUAL call: com.oculus.horizon.cloudstorage2.task.DownloadMetadataTask.A00():X.0DC)
                                                                          (wrap: com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask$1 : 0x0064: CONSTRUCTOR  (r0v10 com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask$1) = 
                                                                          (r4v0 'downloadMetadataToFileTask' com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask)
                                                                          (wrap: com.oculus.horizon.cloudstorage2.callable.WriteMetadataToFileProvider : 0x0053: CHECK_CAST (r2v2 com.oculus.horizon.cloudstorage2.callable.WriteMetadataToFileProvider) = (com.oculus.horizon.cloudstorage2.callable.WriteMetadataToFileProvider) (wrap: java.lang.Object : 0x004f: INVOKE  (r2v1 java.lang.Object) = 
                                                                          (444 int)
                                                                          (wrap: X.0QC : 0x004d: IGET  (r0v7 X.0QC) = (r4v0 'downloadMetadataToFileTask' com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask) com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask._UL_mInjectionContext X.0QC)
                                                                         type: STATIC call: X.0J2.A04(int, X.0QC):java.lang.Object))
                                                                         call: com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask.1.<init>(com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask, com.oculus.horizon.cloudstorage2.callable.WriteMetadataToFileProvider):void type: CONSTRUCTOR)
                                                                         type: VIRTUAL call: X.0DC.A0B(X.0D4):X.0DC)
                                                                          (wrap: com.oculus.horizon.cloudstorage2.task.ResolveConflictTask$2$1 : 0x006d: CONSTRUCTOR  (r1v7 com.oculus.horizon.cloudstorage2.task.ResolveConflictTask$2$1) = (r6v0 'this' com.oculus.horizon.cloudstorage2.task.ResolveConflictTask$2 A[IMMUTABLE_TYPE, THIS]) call: com.oculus.horizon.cloudstorage2.task.ResolveConflictTask.2.1.<init>(com.oculus.horizon.cloudstorage2.task.ResolveConflictTask$2):void type: CONSTRUCTOR)
                                                                          (wrap: com.oculus.executors.OculusThreadExecutor : 0x0070: INVOKE  (r0v11 com.oculus.executors.OculusThreadExecutor) =  type: STATIC call: com.oculus.executors.OculusThreadExecutor.A00():com.oculus.executors.OculusThreadExecutor)
                                                                         type: VIRTUAL call: X.0DC.A0C(X.0D4, java.util.concurrent.Executor):X.0DC in method: com.oculus.horizon.cloudstorage2.task.ResolveConflictTask.2.then(X.0DC<java.lang.Void>):X.0DC<java.lang.Void>, file: classes2.dex
                                                                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                                                                        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
                                                                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
                                                                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:313)
                                                                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:249)
                                                                        	... 19 more
                                                                        Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0067: INVOKE  (r2v3 X.0DC<TContinuationResult>) = 
                                                                          (wrap: X.0DC<com.oculus.horizon.api.cloudstorage2.UserCloudFilesResponse> : 0x005e: INVOKE  (r1v6 X.0DC<com.oculus.horizon.api.cloudstorage2.UserCloudFilesResponse>) = 
                                                                          (wrap: com.oculus.horizon.cloudstorage2.task.DownloadMetadataTask : 0x005b: CONSTRUCTOR  (r0v9 com.oculus.horizon.cloudstorage2.task.DownloadMetadataTask) = 
                                                                          (wrap: X.0JA : 0x0049: CHECK_CAST (r3v2 X.0JA) = (X.0JA) (wrap: java.lang.Object : 0x0045: INVOKE  (r3v1 java.lang.Object) = 
                                                                          (31 int)
                                                                          (wrap: X.0QC : 0x0043: IGET  (r0v6 X.0QC) = (r4v0 'downloadMetadataToFileTask' com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask) com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask._UL_mInjectionContext X.0QC)
                                                                         type: STATIC call: X.0J2.A04(int, X.0QC):java.lang.Object))
                                                                          (wrap: java.lang.String : 0x0057: IGET  (r1v5 java.lang.String) = 
                                                                          (wrap: com.oculus.library.model.App : 0x0055: IGET  (r0v8 com.oculus.library.model.App) = (r4v0 'downloadMetadataToFileTask' com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask) com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask.mApp com.oculus.library.model.App)
                                                                         com.oculus.library.model.App.applicationGroupingId java.lang.String)
                                                                          (r4v0 'downloadMetadataToFileTask' com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask)
                                                                         call: com.oculus.horizon.cloudstorage2.task.DownloadMetadataTask.<init>(X.0p5, java.lang.String, com.oculus.horizon.cloudstorage2.Reporter):void type: CONSTRUCTOR)
                                                                         type: VIRTUAL call: com.oculus.horizon.cloudstorage2.task.DownloadMetadataTask.A00():X.0DC)
                                                                          (wrap: com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask$1 : 0x0064: CONSTRUCTOR  (r0v10 com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask$1) = 
                                                                          (r4v0 'downloadMetadataToFileTask' com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask)
                                                                          (wrap: com.oculus.horizon.cloudstorage2.callable.WriteMetadataToFileProvider : 0x0053: CHECK_CAST (r2v2 com.oculus.horizon.cloudstorage2.callable.WriteMetadataToFileProvider) = (com.oculus.horizon.cloudstorage2.callable.WriteMetadataToFileProvider) (wrap: java.lang.Object : 0x004f: INVOKE  (r2v1 java.lang.Object) = 
                                                                          (444 int)
                                                                          (wrap: X.0QC : 0x004d: IGET  (r0v7 X.0QC) = (r4v0 'downloadMetadataToFileTask' com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask) com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask._UL_mInjectionContext X.0QC)
                                                                         type: STATIC call: X.0J2.A04(int, X.0QC):java.lang.Object))
                                                                         call: com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask.1.<init>(com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask, com.oculus.horizon.cloudstorage2.callable.WriteMetadataToFileProvider):void type: CONSTRUCTOR)
                                                                         type: VIRTUAL call: X.0DC.A0B(X.0D4):X.0DC in method: com.oculus.horizon.cloudstorage2.task.ResolveConflictTask.2.then(X.0DC<java.lang.Void>):X.0DC<java.lang.Void>, file: classes2.dex
                                                                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                                                                        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
                                                                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
                                                                        	at jadx.core.codegen.InsnGen.addArgDot(InsnGen.java:87)
                                                                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:715)
                                                                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:367)
                                                                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
                                                                        	... 23 more
                                                                        Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0064: CONSTRUCTOR  (r0v10 com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask$1) = 
                                                                          (r4v0 'downloadMetadataToFileTask' com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask)
                                                                          (wrap: com.oculus.horizon.cloudstorage2.callable.WriteMetadataToFileProvider : 0x0053: CHECK_CAST (r2v2 com.oculus.horizon.cloudstorage2.callable.WriteMetadataToFileProvider) = (com.oculus.horizon.cloudstorage2.callable.WriteMetadataToFileProvider) (wrap: java.lang.Object : 0x004f: INVOKE  (r2v1 java.lang.Object) = 
                                                                          (444 int)
                                                                          (wrap: X.0QC : 0x004d: IGET  (r0v7 X.0QC) = (r4v0 'downloadMetadataToFileTask' com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask) com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask._UL_mInjectionContext X.0QC)
                                                                         type: STATIC call: X.0J2.A04(int, X.0QC):java.lang.Object))
                                                                         call: com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask.1.<init>(com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask, com.oculus.horizon.cloudstorage2.callable.WriteMetadataToFileProvider):void type: CONSTRUCTOR in method: com.oculus.horizon.cloudstorage2.task.ResolveConflictTask.2.then(X.0DC<java.lang.Void>):X.0DC<java.lang.Void>, file: classes2.dex
                                                                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                                                                        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
                                                                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
                                                                        	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:806)
                                                                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:746)
                                                                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:367)
                                                                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
                                                                        	... 29 more
                                                                        Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.oculus.horizon.cloudstorage2.task.DownloadMetadataToFileTask, state: NOT_LOADED
                                                                        	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:215)
                                                                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:630)
                                                                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:363)
                                                                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
                                                                        	... 35 more
                                                                        */
                                                                    /*
                                                                    // Method dump skipped, instructions count: 130
                                                                    */
                                                                    throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.cloudstorage2.task.ResolveConflictTask.AnonymousClass2.then(X.0DC):java.lang.Object");
                                                                }
                                                            }, OculusThreadExecutor.A00()), new AnonymousClass0D4<Void, Void>(r6) {
                                                                /* class com.oculus.horizon.cloudstorage2.task.ResolveConflictTask.AnonymousClass1 */
                                                                public final /* synthetic */ AnonymousClass0DD val$completionSource;

                                                                {
                                                                    this.val$completionSource = r2;
                                                                }

                                                                /* Return type fixed from 'java.lang.Object' to match base method */
                                                                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
                                                                @Override // X.AnonymousClass0D4
                                                                public final Void then(AnonymousClass0DC<Void> r5) throws Exception {
                                                                    String str;
                                                                    TaskProgressReporter<Step> taskProgressReporter = ResolveConflictTask.this.mReporter;
                                                                    Step step = Step.DONE;
                                                                    E e = taskProgressReporter.mProgress;
                                                                    TaskProgressReporter.A00(taskProgressReporter, ((Enum[]) e.getClass().getEnumConstants()).length - 1, step);
                                                                    Step step2 = (Step) e;
                                                                    if (r5.A0K()) {
                                                                        ResolveConflictException resolveConflictException = new ResolveConflictException(step2, r5.A0F());
                                                                        this.val$completionSource.A01(resolveConflictException);
                                                                        ResolveConflictTask.this.mReporter.A03(resolveConflictException);
                                                                        return null;
                                                                    }
                                                                    this.val$completionSource.A02(null);
                                                                    TaskProgressReporter<Step> taskProgressReporter2 = ResolveConflictTask.this.mReporter;
                                                                    if (r5.A0I()) {
                                                                        str = "canceled";
                                                                    } else {
                                                                        str = "success";
                                                                    }
                                                                    taskProgressReporter2.A04(str);
                                                                    return null;
                                                                }
                                                            }, OculusThreadExecutor.A00());
                                                            r6.A00.A09(new AnonymousClass0D4<Void, Void>(parcelableCallbackReceiver, countDownLatch2) {
                                                                /* class com.oculus.horizon.cloudstorage2.CloudStorageIntentService.AnonymousClass4 */
                                                                public final /* synthetic */ CountDownLatch val$latch;
                                                                public final /* synthetic */ ParcelableCallbackReceiver val$receiver;

                                                                {
                                                                    this.val$receiver = r2;
                                                                    this.val$latch = r3;
                                                                }

                                                                /* Return type fixed from 'java.lang.Object' to match base method */
                                                                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
                                                                @Override // X.AnonymousClass0D4
                                                                public final Void then(AnonymousClass0DC<Void> r11) throws Exception {
                                                                    if (r11.A0K()) {
                                                                        this.val$receiver.A00(new CloudStorageResult(CloudStorageResult.SyncResultType.FAILURE, r11.A0F().getMessage(), 1.0f, -1, -1));
                                                                    } else {
                                                                        this.val$receiver.A00(new CloudStorageResult(CloudStorageResult.SyncResultType.SUCCESS, null, 1.0f, -1, -1));
                                                                    }
                                                                    this.val$latch.countDown();
                                                                    return null;
                                                                }
                                                            });
                                                            try {
                                                                countDownLatch2.await();
                                                                return;
                                                            } catch (InterruptedException e14) {
                                                                AnonymousClass0NO.A03(CloudStorageIntentService.class, "Resolve conflict latch was interrupted!", e14);
                                                                return;
                                                            }
                                                        } else {
                                                            throw new IllegalArgumentException("Conflict resolution option was not provided!");
                                                        }
                                                    default:
                                                        return;
                                                }
                                            } else {
                                                throw new SignatureChecker.NotFirstPartySignatureException(signatureCheck);
                                            }
                                        } else {
                                            throw new RemoteException("HandshakeBinder did not receive a response from the client.");
                                        }
                                    } else {
                                        throw new RemoteException("Bundle is missing handshake binder!");
                                    }
                                } catch (RemoteException e15) {
                                    throw new SecurityException("Calling identity could not be verified!", e15);
                                }
                            } else {
                                throw new IllegalArgumentException("Result callback was not provided!");
                            }
                        } catch (Exception e16) {
                            AnonymousClass0NO.A03(CloudStorageIntentService.class, "Failed to start cloud storage action!", e16);
                            Event A222 = ((EventManager) AnonymousClass0J2.A03(0, 242, cloudStorageIntentService.mCloudStorageLogger._UL_mInjectionContext)).A22(CloudStorageLogger.EVENT_CLOUD_STORAGE_SERVICE_ERROR);
                            A222.A15("result", e16.getMessage());
                            A222.A5L();
                            return;
                        }
                    } else {
                        throw new IllegalArgumentException("Empty intent received!");
                    }
                }
                throw th;
            }

            /* JADX WARNING: Code restructure failed: missing block: B:9:0x001a, code lost:
                if (r0 == false) goto L_0x0020;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final void onHandleIntent(@javax.annotation.Nullable android.content.Intent r6) {
                /*
                    r5 = this;
                    java.lang.String r4 = "deny"
                    java.lang.String r3 = "onHandleIntent"
                    if (r6 == 0) goto L_0x0032
                    X.0bD r0 = X.C02670bA.A01()
                    boolean r0 = r0.A00(r5, r5, r6)
                    if (r0 == 0) goto L_0x0020
                    r2 = r5
                    monitor-enter(r2)
                    X.0bj r1 = r5.mPermissionChecker     // Catch:{ all -> 0x001d }
                    r0 = 0
                    boolean r0 = r1.A8w(r5, r5, r6, r0)     // Catch:{ all -> 0x001d }
                    monitor-exit(r2)
                    if (r0 != 0) goto L_0x0032
                    goto L_0x0020
                L_0x001d:
                    r0 = move-exception
                    monitor-exit(r2)
                    throw r0
                L_0x0020:
                    r1 = r5
                    boolean r0 = r5 instanceof com.oculus.security.basecomponent.OculusPublicIntentService
                    if (r0 != 0) goto L_0x002d
                    X.0iB r1 = X.C02650ay.A00
                L_0x0027:
                    java.lang.String r0 = r5.mEndpointName
                    r1.A00(r0, r3, r4, r6)
                    return
                L_0x002d:
                    com.oculus.security.basecomponent.OculusPublicIntentService r1 = (com.oculus.security.basecomponent.OculusPublicIntentService) r1
                    com.oculus.security.basecomponent.OculusIntentLogger r1 = r1.mOculusIntentLogger
                    goto L_0x0027
                L_0x0032:
                    r1 = r5
                    boolean r0 = r5 instanceof com.oculus.security.basecomponent.OculusPublicIntentService
                    if (r0 != 0) goto L_0x0044
                    X.0iB r2 = X.C02650ay.A00
                L_0x0039:
                    java.lang.String r1 = r5.mEndpointName
                    java.lang.String r0 = "allow"
                    r2.A00(r1, r3, r0, r6)
                    r5.A03(r6)
                    return
                L_0x0044:
                    com.oculus.security.basecomponent.OculusPublicIntentService r1 = (com.oculus.security.basecomponent.OculusPublicIntentService) r1
                    com.oculus.security.basecomponent.OculusIntentLogger r2 = r1.mOculusIntentLogger
                    goto L_0x0039
                */
                throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1U8.onHandleIntent(android.content.Intent):void");
            }

            public AnonymousClass1U8(String str) {
                super(str);
                this.name = str;
            }

            @Nullable
            public final IBinder onBind(Intent intent) {
                AbstractC04590iB r1;
                boolean A8w;
                AbstractC04590iB r2;
                if (C02670bA.A01().A00(this, this, intent)) {
                    synchronized (this) {
                        A8w = this.mPermissionChecker.A8w(this, this, intent, null);
                    }
                    if (A8w) {
                        if (!(this instanceof OculusPublicIntentService)) {
                            r2 = C02650ay.A00;
                        } else {
                            r2 = ((OculusPublicIntentService) this).mOculusIntentLogger;
                        }
                        r2.A00(this.mEndpointName, "onBind", "allow", intent);
                        if (!(this instanceof BugReporterService)) {
                            return null;
                        }
                        BugReporterService bugReporterService = (BugReporterService) this;
                        BugReport A00 = BugReport.A00(bugReporterService);
                        if (A00 == null) {
                            AnonymousClass0NO.A08(BugReporterService.TAG, "could not create new bug report");
                            return null;
                        }
                        String currentAppOrPanelAppID = bugReporterService.mPresenceManager.getCurrentAppOrPanelAppID();
                        if (!BugReporterService.sFirstPartyAppIDsForFlytrap.contains(currentAppOrPanelAppID)) {
                            currentAppOrPanelAppID = "1481000308606657";
                        }
                        A00.mMetadata.put("appId", currentAppOrPanelAppID);
                        BugReport.A04(A00);
                        A00.A07(PackageUtil.A00(bugReporterService));
                        A00.A06();
                        return new IBugReporterService.Stub(A00) {
                            /* class com.oculus.horizon.vrbugreporter.BugReporterService.AnonymousClass3 */
                            public final /* synthetic */ BugReport val$report;

                            {
                                this.val$report = r2;
                            }

                            @Override // com.oculus.aidl.IBugReporterService
                            public final String getReportId() {
                                return this.val$report.A05();
                            }

                            @Override // com.oculus.aidl.IBugReporterService
                            @Nullable
                            public final ParcelFileDescriptor openScreenshotFile() {
                                try {
                                    return ParcelFileDescriptor.open(new File(BugReport.A01(this.val$report), "screenshot.png"), 671088640);
                                } catch (FileNotFoundException e) {
                                    AnonymousClass0NO.A0E(BugReporterService.TAG, "could not open screenshot file: %s", e.getMessage());
                                    return null;
                                }
                            }
                        };
                    }
                }
                if (!(this instanceof OculusPublicIntentService)) {
                    r1 = C02650ay.A00;
                } else {
                    r1 = ((OculusPublicIntentService) this).mOculusIntentLogger;
                }
                r1.A00(this.mEndpointName, "onBind", "deny", intent);
                return null;
            }

            public void onCreate() {
                super.onCreate();
                this.mEndpointName = String.format(GraphRequest.GRAPH_PATH_FORMAT, getPackageName(), getClass().getName());
            }
        }
