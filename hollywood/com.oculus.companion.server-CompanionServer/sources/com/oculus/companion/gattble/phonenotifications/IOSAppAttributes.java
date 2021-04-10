package com.oculus.companion.gattble.phonenotifications;

import android.util.Log;
import java.io.EOFException;
import java.io.IOException;
import java.util.EnumSet;
import java.util.Locale;

/* access modifiers changed from: package-private */
public class IOSAppAttributes {
    final String appID;
    final String displayName;

    private IOSAppAttributes(String str, String str2) {
        this.appID = str;
        this.displayName = str2;
    }

    public String toString() {
        return String.format(Locale.US, "%s<displayName=%s>", IOSNotification.class.getSimpleName(), this.displayName);
    }

    /* access modifiers changed from: package-private */
    public static class Builder {
        private static final String TAG = "Builder";
        private String mAppID;
        private String mDisplayName;
        private EnumSet<State> mStatesCurrent = EnumSet.noneOf(State.class);
        private final EnumSet<State> mStatesExpected = EnumSet.allOf(State.class);
        private ANCSByteArrayOutputStream mUnprocessedPayload = new ANCSByteArrayOutputStream();

        /* access modifiers changed from: private */
        public enum State {
            VALIDATED,
            DISPLAY_NAME_SET
        }

        Builder(String str) {
            this.mAppID = str;
        }

        /* access modifiers changed from: package-private */
        public void appendDataSourcePayload(byte[] bArr) throws IOException {
            String str;
            this.mUnprocessedPayload.write(bArr);
            ANCSByteArrayInputStream aNCSByteArrayInputStream = new ANCSByteArrayInputStream(this.mUnprocessedPayload.toByteArray());
            this.mUnprocessedPayload.reset();
            boolean z = false;
            if (!this.mStatesCurrent.contains(State.VALIDATED)) {
                int readInt8 = aNCSByteArrayInputStream.readInt8();
                if (readInt8 != 1) {
                    Log.e(TAG, String.format(Locale.US, "Unexpected Command ID: Expecting %d whereas %d is received", 1, Integer.valueOf(readInt8)));
                    aNCSByteArrayInputStream.close();
                    return;
                }
                String readString = aNCSByteArrayInputStream.readString();
                if (!this.mAppID.equals(readString)) {
                    Log.e(TAG, String.format(Locale.US, "Unexpected App ID: Expecting %s whereas %s is received", this.mAppID, readString));
                    aNCSByteArrayInputStream.close();
                    return;
                }
                this.mStatesCurrent.add(State.VALIDATED);
            }
            while (aNCSByteArrayInputStream.available() > 0) {
                aNCSByteArrayInputStream.mark();
                try {
                    int readInt82 = aNCSByteArrayInputStream.readInt8();
                    int readInt16 = aNCSByteArrayInputStream.readInt16();
                    if (readInt16 > 0) {
                        str = aNCSByteArrayInputStream.readString(readInt16);
                    } else {
                        str = null;
                    }
                    if (readInt82 != 0) {
                        Log.w(TAG, "Unrecognized Attribute ID " + readInt82);
                    } else {
                        this.mDisplayName = str;
                        this.mStatesCurrent.add(State.DISPLAY_NAME_SET);
                    }
                    aNCSByteArrayInputStream.close();
                } catch (EOFException e) {
                    aNCSByteArrayInputStream.reset();
                    int available = aNCSByteArrayInputStream.available();
                    byte[] bArr2 = new byte[available];
                    if (aNCSByteArrayInputStream.read(bArr2, 0, available) == available) {
                        z = true;
                    }
                    aNCSByteArrayInputStream.close();
                    if (z) {
                        this.mUnprocessedPayload.write(bArr2);
                        return;
                    }
                    Log.e(TAG, "Unable to persist unprocessed payload for app " + this.mAppID, e);
                    return;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public byte[] buildGetNotificationAttributesCommand() {
            try {
                ANCSByteArrayOutputStream aNCSByteArrayOutputStream = new ANCSByteArrayOutputStream();
                aNCSByteArrayOutputStream.writeByte(1);
                aNCSByteArrayOutputStream.writeString(this.mAppID);
                aNCSByteArrayOutputStream.writeByte(0);
                byte[] byteArray = aNCSByteArrayOutputStream.toByteArray();
                aNCSByteArrayOutputStream.close();
                return byteArray;
            } catch (IOException e) {
                String str = TAG;
                Log.e(str, "Unable to populate Get App Attributes command for app ID " + this.mAppID, e);
                return null;
            }
        }

        /* access modifiers changed from: package-private */
        public IOSAppAttributes build() {
            if (!isComplete()) {
                Log.i(TAG, String.format(Locale.US, "Unable to build App Attributes %d: Notification is still waiting for more data", this.mAppID));
                return null;
            }
            try {
                this.mUnprocessedPayload.close();
                return new IOSAppAttributes(this.mAppID, this.mDisplayName);
            } catch (IOException e) {
                String str = TAG;
                Log.e(str, "Unable to build App Attributes " + this.mAppID, e);
                return null;
            }
        }

        private boolean isComplete() {
            return this.mStatesCurrent.equals(this.mStatesExpected);
        }

        /* access modifiers changed from: package-private */
        public String getAppID() {
            return this.mAppID;
        }
    }
}
