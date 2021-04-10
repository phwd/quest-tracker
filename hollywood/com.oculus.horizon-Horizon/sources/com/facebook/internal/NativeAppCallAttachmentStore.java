package com.facebook.internal;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

public final class NativeAppCallAttachmentStore {
    public static final String ATTACHMENTS_DIR_NAME = "com.facebook.NativeAppCallAttachmentStore.files";
    public static final String TAG = "com.facebook.internal.NativeAppCallAttachmentStore";
    public static File attachmentsDirectory;

    public static void cleanupAttachmentsForCall(UUID uuid) {
        File attachmentsDirectoryForCall = getAttachmentsDirectoryForCall(uuid, false);
        if (attachmentsDirectoryForCall != null) {
            Utility.deleteDirectory(attachmentsDirectoryForCall);
        }
    }

    public static final class Attachment {
        public final String attachmentName;
        public final String attachmentUrl;
        public Bitmap bitmap;
        public final UUID callId;
        public boolean isContentUri;
        public Uri originalUri;
        public boolean shouldCreateFile;

        public String getAttachmentUrl() {
            return this.attachmentUrl;
        }

        /* JADX WARNING: Removed duplicated region for block: B:11:0x0032  */
        /* JADX WARNING: Removed duplicated region for block: B:14:0x0039  */
        /* JADX WARNING: Removed duplicated region for block: B:17:0x0042  */
        /* JADX WARNING: Removed duplicated region for block: B:18:0x004e  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public Attachment(java.util.UUID r5, android.graphics.Bitmap r6, android.net.Uri r7) {
            /*
            // Method dump skipped, instructions count: 124
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.NativeAppCallAttachmentStore.Attachment.<init>(java.util.UUID, android.graphics.Bitmap, android.net.Uri):void");
        }
    }

    public static void addAttachments(Collection<Attachment> collection) {
        if (collection != null && collection.size() != 0) {
            if (attachmentsDirectory == null) {
                cleanupAllAttachments();
            }
            getAttachmentsDirectory().mkdirs();
            ArrayList arrayList = new ArrayList();
            try {
                for (Attachment attachment : collection) {
                    if (attachment.shouldCreateFile) {
                        File attachmentFile = getAttachmentFile(attachment.callId, attachment.attachmentName, true);
                        arrayList.add(attachmentFile);
                        Bitmap bitmap = attachment.bitmap;
                        if (bitmap != null) {
                            processAttachmentBitmap(bitmap, attachmentFile);
                        } else {
                            Uri uri = attachment.originalUri;
                            if (uri != null) {
                                processAttachmentFile(uri, attachment.isContentUri, attachmentFile);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                StringBuilder sb = new StringBuilder("Got unexpected exception:");
                sb.append(e);
                Log.e(TAG, sb.toString());
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    try {
                        ((File) it.next()).delete();
                    } catch (Exception unused) {
                    }
                }
                throw new FacebookException(e);
            }
        }
    }

    public static synchronized File getAttachmentsDirectory() {
        File file;
        synchronized (NativeAppCallAttachmentStore.class) {
            file = attachmentsDirectory;
            if (file == null) {
                Validate.sdkInitialized();
                file = new File(FacebookSdk.applicationContext.getCacheDir(), ATTACHMENTS_DIR_NAME);
                attachmentsDirectory = file;
            }
        }
        return file;
    }

    public static File getAttachmentsDirectoryForCall(UUID uuid, boolean z) {
        File file = attachmentsDirectory;
        if (file == null) {
            return null;
        }
        File file2 = new File(file, uuid.toString());
        if (z && !file2.exists()) {
            file2.mkdirs();
        }
        return file2;
    }

    public static void processAttachmentBitmap(Bitmap bitmap, File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
        } finally {
            Utility.closeQuietly(fileOutputStream);
        }
    }

    public static void processAttachmentFile(Uri uri, boolean z, File file) throws IOException {
        InputStream inputStream;
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        if (!z) {
            try {
                inputStream = new FileInputStream(uri.getPath());
            } catch (Throwable th) {
                Utility.closeQuietly(fileOutputStream);
                throw th;
            }
        } else {
            Validate.sdkInitialized();
            inputStream = FacebookSdk.applicationContext.getContentResolver().openInputStream(uri);
        }
        Utility.copyAndCloseInputStream(inputStream, fileOutputStream);
        Utility.closeQuietly(fileOutputStream);
    }

    public static void cleanupAllAttachments() {
        Utility.deleteDirectory(getAttachmentsDirectory());
    }

    public static File ensureAttachmentsDirectoryExists() {
        File attachmentsDirectory2 = getAttachmentsDirectory();
        attachmentsDirectory2.mkdirs();
        return attachmentsDirectory2;
    }

    public static File getAttachmentFile(UUID uuid, String str, boolean z) throws IOException {
        File attachmentsDirectoryForCall = getAttachmentsDirectoryForCall(uuid, z);
        if (attachmentsDirectoryForCall == null) {
            return null;
        }
        try {
            return new File(attachmentsDirectoryForCall, URLEncoder.encode(str, "UTF-8"));
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    public static File openAttachment(UUID uuid, String str) throws FileNotFoundException {
        if (Utility.isNullOrEmpty(str) || uuid == null) {
            throw new FileNotFoundException();
        }
        try {
            return getAttachmentFile(uuid, str, false);
        } catch (IOException unused) {
            throw new FileNotFoundException();
        }
    }

    public static Attachment createAttachment(UUID uuid, Bitmap bitmap) {
        Validate.notNull(uuid, "callId");
        Validate.notNull(bitmap, "attachmentBitmap");
        return new Attachment(uuid, bitmap, null);
    }

    public static Attachment createAttachment(UUID uuid, Uri uri) {
        Validate.notNull(uuid, "callId");
        Validate.notNull(uri, "attachmentUri");
        return new Attachment(uuid, null, uri);
    }
}
