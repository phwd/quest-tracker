package com.oculus.horizon.cloudstorage2.task;

import X.AbstractC06640p5;
import X.AnonymousClass0DD;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.inject.Assisted;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.appmanager.downloader.OculusFileDownloader;
import com.oculus.downloader.OculusDownloadListener;
import com.oculus.downloader.dispatcher.OculusDownloadListenerDispatcher;
import com.oculus.downloader.model.DownloadInfo;
import com.oculus.extras.Extras;
import com.oculus.extras.ExtrasBuilder;
import com.oculus.horizon.cloudstorage2.DownloadFailureException;
import com.oculus.horizon.cloudstorage2.Reporter;
import com.oculus.util.thread.ThreadUtils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_downloader_OculusFileDownloader_ULSEP_BINDING_ID"})
public class FileDownloadTask extends AsyncWork<Map<String, String>> {
    public AnonymousClass0QC _UL_mInjectionContext;
    public CloudFileDownloadManager downloadManager;
    public final String mPrependUri;
    public final FileRequest[] mRequests;

    public class CloudFileDownloadManager implements OculusDownloadListener {
        public static final String EXTRA_KEY_RELATIVE_PATH = "relative_path";
        public static final String EXTRA_KEY_REQUEST_ID = "request_id";
        public final Map<String, String> downloadedToFinalDestination = new HashMap();
        public final AnonymousClass0DD<Map<String, String>> mCompletionSource = new AnonymousClass0DD<>();
        public final Set<Long> mRequestedDownloads = new HashSet();
        public final Set<String> mRequestedFiles = new HashSet();

        @Override // com.oculus.downloader.OculusDownloadListener
        public final void A5x(DownloadInfo downloadInfo) {
            new Object[1][0] = Long.valueOf(downloadInfo.id);
            String A01 = downloadInfo.extras.A01("request_id", "");
            if (this.mRequestedFiles.contains(A01)) {
                this.mRequestedFiles.remove(A01);
                long j = downloadInfo.id;
                if (j != -1) {
                    if (!this.mRequestedDownloads.contains(Long.valueOf(j))) {
                        A00(this, new DownloadFailureException(StringFormatUtil.formatStrLocaleSafe("File request (%s) does not have a matching requested download!", A01), downloadInfo));
                        return;
                    }
                    this.mRequestedDownloads.remove(Long.valueOf(downloadInfo.id));
                }
                try {
                    if (downloadInfo.status == 8) {
                        String orNull = downloadInfo.localUri.orNull();
                        if (orNull == null) {
                            throw new DownloadFailureException("No localUri", downloadInfo);
                        } else if (downloadInfo.extras.mData.containsKey(EXTRA_KEY_RELATIVE_PATH)) {
                            new Object[1][0] = Long.valueOf(downloadInfo.id);
                            this.downloadedToFinalDestination.put(orNull, downloadInfo.extras.A01(EXTRA_KEY_RELATIVE_PATH, ""));
                            if (this.mRequestedFiles.isEmpty()) {
                                OculusDownloadListenerDispatcher oculusDownloadListenerDispatcher = ((OculusFileDownloader) AnonymousClass0J2.A03(1, 429, FileDownloadTask.this._UL_mInjectionContext)).mDownloadListenerDispatcher;
                                oculusDownloadListenerDispatcher.mDynamicListeners.remove(this);
                                oculusDownloadListenerDispatcher.mStrongListeners.remove(this);
                                this.mCompletionSource.A02(this.downloadedToFinalDestination);
                            }
                        } else {
                            throw new DownloadFailureException("No relativePath", downloadInfo);
                        }
                    } else {
                        throw new DownloadFailureException("Status not successful", downloadInfo);
                    }
                } catch (DownloadFailureException e) {
                    A00(this, e);
                }
            }
        }

        public CloudFileDownloadManager() {
        }

        public static void A00(CloudFileDownloadManager cloudFileDownloadManager, Exception exc) {
            OculusDownloadListenerDispatcher oculusDownloadListenerDispatcher = ((OculusFileDownloader) AnonymousClass0J2.A03(1, 429, FileDownloadTask.this._UL_mInjectionContext)).mDownloadListenerDispatcher;
            oculusDownloadListenerDispatcher.mDynamicListeners.remove(cloudFileDownloadManager);
            oculusDownloadListenerDispatcher.mStrongListeners.remove(cloudFileDownloadManager);
            cloudFileDownloadManager.mCompletionSource.A03(exc);
            for (Long l : cloudFileDownloadManager.mRequestedDownloads) {
                OculusFileDownloader oculusFileDownloader = (OculusFileDownloader) AnonymousClass0J2.A03(1, 429, FileDownloadTask.this._UL_mInjectionContext);
                long longValue = l.longValue();
                ThreadUtils.A02();
                synchronized (oculusFileDownloader.mExtrasLock) {
                    ExtrasBuilder extrasBuilder = new ExtrasBuilder(OculusFileDownloader.A02(oculusFileDownloader, longValue).mData);
                    extrasBuilder.A00(OculusFileDownloader.KEY_USER_CANCELLED, Boolean.toString(true));
                    OculusFileDownloader.A03(oculusFileDownloader, longValue, new Extras(extrasBuilder.mData));
                    oculusFileDownloader.mDownloadManager.remove(longValue);
                    OculusFileDownloader.AnonymousClass1 r4 = 
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0070: CONSTRUCTOR  (r4v2 'r4' com.oculus.appmanager.downloader.OculusFileDownloader$1) = (r7v1 'oculusFileDownloader' com.oculus.appmanager.downloader.OculusFileDownloader), (r0v11 'longValue' long) call: com.oculus.appmanager.downloader.OculusFileDownloader.1.<init>(com.oculus.appmanager.downloader.OculusFileDownloader, long):void type: CONSTRUCTOR in method: com.oculus.horizon.cloudstorage2.task.FileDownloadTask.CloudFileDownloadManager.A00(com.oculus.horizon.cloudstorage2.task.FileDownloadTask$CloudFileDownloadManager, java.lang.Exception):void, file: classes2.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:217)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:110)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:56)
                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:99)
                        	at jadx.core.codegen.RegionGen.makeSynchronizedRegion(RegionGen.java:249)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:71)
                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:99)
                        	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:221)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:67)
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
                        Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.oculus.appmanager.downloader.OculusFileDownloader, state: GENERATED_AND_UNLOADED
                        	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:215)
                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:630)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:363)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:249)
                        	... 26 more
                        */
                    /*
                    // Method dump skipped, instructions count: 145
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.cloudstorage2.task.FileDownloadTask.CloudFileDownloadManager.A00(com.oculus.horizon.cloudstorage2.task.FileDownloadTask$CloudFileDownloadManager, java.lang.Exception):void");
                }
            }

            public static class FileRequest {
                public final String cloudFileUri;
                public final String id;
                public final String relativePath;

                public FileRequest(String str, String str2, String str3) {
                    this.id = str;
                    this.cloudFileUri = str2;
                    this.relativePath = str3;
                }
            }

            @Inject
            public FileDownloadTask(AbstractC06640p5 r3, @Assisted String str, @Assisted FileRequest[] fileRequestArr, @Assisted Reporter reporter) {
                super(reporter);
                this._UL_mInjectionContext = new AnonymousClass0QC(2, r3);
                this.mPrependUri = str;
                this.mRequests = fileRequestArr;
            }
        }
