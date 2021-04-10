package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.util.SparseArray;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* access modifiers changed from: package-private */
@RequiresApi(16)
public class NotificationCompatJellybean {
    static final String EXTRA_ALLOW_GENERATED_REPLIES = "android.support.allowGeneratedReplies";
    static final String EXTRA_DATA_ONLY_REMOTE_INPUTS = "android.support.dataRemoteInputs";
    private static final String KEY_ACTION_INTENT = "actionIntent";
    private static final String KEY_ALLOWED_DATA_TYPES = "allowedDataTypes";
    private static final String KEY_ALLOW_FREE_FORM_INPUT = "allowFreeFormInput";
    private static final String KEY_CHOICES = "choices";
    private static final String KEY_DATA_ONLY_REMOTE_INPUTS = "dataOnlyRemoteInputs";
    private static final String KEY_EXTRAS = "extras";
    private static final String KEY_ICON = "icon";
    private static final String KEY_LABEL = "label";
    private static final String KEY_REMOTE_INPUTS = "remoteInputs";
    private static final String KEY_RESULT_KEY = "resultKey";
    private static final String KEY_SEMANTIC_ACTION = "semanticAction";
    private static final String KEY_SHOWS_USER_INTERFACE = "showsUserInterface";
    private static final String KEY_TITLE = "title";
    public static final String TAG = "NotificationCompat";
    private static Class<?> sActionClass;
    private static Field sActionIconField;
    private static Field sActionIntentField;
    private static Field sActionTitleField;
    private static boolean sActionsAccessFailed;
    private static Field sActionsField;
    private static final Object sActionsLock = new Object();
    private static Field sExtrasField;
    private static boolean sExtrasFieldAccessFailed;
    private static final Object sExtrasLock = new Object();

    public static SparseArray<Bundle> buildActionExtrasMap(List<Bundle> actionExtrasList) {
        SparseArray<Bundle> actionExtrasMap = null;
        int count = actionExtrasList.size();
        for (int i = 0; i < count; i++) {
            Bundle actionExtras = actionExtrasList.get(i);
            if (actionExtras != null) {
                if (actionExtrasMap == null) {
                    actionExtrasMap = new SparseArray<>();
                }
                actionExtrasMap.put(i, actionExtras);
            }
        }
        return actionExtrasMap;
    }

    public static Bundle getExtras(Notification notif) {
        synchronized (sExtrasLock) {
            if (sExtrasFieldAccessFailed) {
                return null;
            }
            try {
                if (sExtrasField == null) {
                    Field extrasField = Notification.class.getDeclaredField(KEY_EXTRAS);
                    if (!Bundle.class.isAssignableFrom(extrasField.getType())) {
                        Log.e(TAG, "Notification.extras field is not of type Bundle");
                        sExtrasFieldAccessFailed = true;
                        return null;
                    }
                    extrasField.setAccessible(true);
                    sExtrasField = extrasField;
                }
                Bundle extras = (Bundle) sExtrasField.get(notif);
                if (extras == null) {
                    extras = new Bundle();
                    sExtrasField.set(notif, extras);
                }
                return extras;
            } catch (IllegalAccessException e) {
                Log.e(TAG, "Unable to access notification extras", e);
                sExtrasFieldAccessFailed = true;
                return null;
            } catch (NoSuchFieldException e2) {
                Log.e(TAG, "Unable to access notification extras", e2);
                sExtrasFieldAccessFailed = true;
                return null;
            }
        }
    }

    public static NotificationCompat.Action readAction(int icon, CharSequence title, PendingIntent actionIntent, Bundle extras) {
        boolean allowGeneratedReplies;
        RemoteInput[] dataOnlyRemoteInputs;
        RemoteInput[] remoteInputs;
        if (extras != null) {
            remoteInputs = fromBundleArray(getBundleArrayFromBundle(extras, NotificationCompatExtras.EXTRA_REMOTE_INPUTS));
            dataOnlyRemoteInputs = fromBundleArray(getBundleArrayFromBundle(extras, EXTRA_DATA_ONLY_REMOTE_INPUTS));
            allowGeneratedReplies = extras.getBoolean(EXTRA_ALLOW_GENERATED_REPLIES);
        } else {
            remoteInputs = null;
            dataOnlyRemoteInputs = null;
            allowGeneratedReplies = false;
        }
        return new NotificationCompat.Action(icon, title, actionIntent, extras, remoteInputs, dataOnlyRemoteInputs, allowGeneratedReplies, 0, true);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: android.os.Bundle */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v6, types: [android.os.Bundle[], android.os.Parcelable[]] */
    /* JADX WARN: Type inference failed for: r1v8, types: [android.os.Bundle[], android.os.Parcelable[]] */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.os.Bundle writeActionAndGetExtras(android.app.Notification.Builder r3, android.support.v4.app.NotificationCompat.Action r4) {
        /*
            int r0 = r4.getIcon()
            java.lang.CharSequence r1 = r4.getTitle()
            android.app.PendingIntent r2 = r4.getActionIntent()
            r3.addAction(r0, r1, r2)
            android.os.Bundle r0 = new android.os.Bundle
            android.os.Bundle r1 = r4.getExtras()
            r0.<init>(r1)
            android.support.v4.app.RemoteInput[] r1 = r4.getRemoteInputs()
            if (r1 == 0) goto L_0x002c
            android.support.v4.app.RemoteInput[] r1 = r4.getRemoteInputs()
            android.os.Bundle[] r1 = toBundleArray(r1)
            java.lang.String r2 = "android.support.remoteInputs"
            r0.putParcelableArray(r2, r1)
        L_0x002c:
            android.support.v4.app.RemoteInput[] r1 = r4.getDataOnlyRemoteInputs()
            if (r1 == 0) goto L_0x0040
            android.support.v4.app.RemoteInput[] r1 = r4.getDataOnlyRemoteInputs()
            android.os.Bundle[] r1 = toBundleArray(r1)
            java.lang.String r2 = "android.support.dataRemoteInputs"
            r0.putParcelableArray(r2, r1)
        L_0x0040:
            boolean r1 = r4.getAllowGeneratedReplies()
            java.lang.String r2 = "android.support.allowGeneratedReplies"
            r0.putBoolean(r2, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.NotificationCompatJellybean.writeActionAndGetExtras(android.app.Notification$Builder, android.support.v4.app.NotificationCompat$Action):android.os.Bundle");
    }

    public static int getActionCount(Notification notif) {
        int length;
        synchronized (sActionsLock) {
            Object[] actionObjects = getActionObjectsLocked(notif);
            length = actionObjects != null ? actionObjects.length : 0;
        }
        return length;
    }

    public static NotificationCompat.Action getAction(Notification notif, int actionIndex) {
        SparseArray<Bundle> actionExtrasMap;
        synchronized (sActionsLock) {
            try {
                Object[] actionObjects = getActionObjectsLocked(notif);
                if (actionObjects == null) {
                    return null;
                }
                Object actionObject = actionObjects[actionIndex];
                Bundle actionExtras = null;
                Bundle extras = getExtras(notif);
                if (!(extras == null || (actionExtrasMap = extras.getSparseParcelableArray(NotificationCompatExtras.EXTRA_ACTION_EXTRAS)) == null)) {
                    actionExtras = actionExtrasMap.get(actionIndex);
                }
                return readAction(sActionIconField.getInt(actionObject), (CharSequence) sActionTitleField.get(actionObject), (PendingIntent) sActionIntentField.get(actionObject), actionExtras);
            } catch (IllegalAccessException e) {
                Log.e(TAG, "Unable to access notification actions", e);
                sActionsAccessFailed = true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private static Object[] getActionObjectsLocked(Notification notif) {
        synchronized (sActionsLock) {
            if (!ensureActionReflectionReadyLocked()) {
                return null;
            }
            try {
                return (Object[]) sActionsField.get(notif);
            } catch (IllegalAccessException e) {
                Log.e(TAG, "Unable to access notification actions", e);
                sActionsAccessFailed = true;
                return null;
            }
        }
    }

    private static boolean ensureActionReflectionReadyLocked() {
        if (sActionsAccessFailed) {
            return false;
        }
        try {
            if (sActionsField == null) {
                sActionClass = Class.forName("android.app.Notification$Action");
                sActionIconField = sActionClass.getDeclaredField(KEY_ICON);
                sActionTitleField = sActionClass.getDeclaredField(KEY_TITLE);
                sActionIntentField = sActionClass.getDeclaredField(KEY_ACTION_INTENT);
                sActionsField = Notification.class.getDeclaredField("actions");
                sActionsField.setAccessible(true);
            }
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "Unable to access notification actions", e);
            sActionsAccessFailed = true;
        } catch (NoSuchFieldException e2) {
            Log.e(TAG, "Unable to access notification actions", e2);
            sActionsAccessFailed = true;
        }
        return !sActionsAccessFailed;
    }

    static NotificationCompat.Action getActionFromBundle(Bundle bundle) {
        Bundle extras = bundle.getBundle(KEY_EXTRAS);
        boolean allowGeneratedReplies = false;
        if (extras != null) {
            allowGeneratedReplies = extras.getBoolean(EXTRA_ALLOW_GENERATED_REPLIES, false);
        }
        return new NotificationCompat.Action(bundle.getInt(KEY_ICON), bundle.getCharSequence(KEY_TITLE), (PendingIntent) bundle.getParcelable(KEY_ACTION_INTENT), bundle.getBundle(KEY_EXTRAS), fromBundleArray(getBundleArrayFromBundle(bundle, KEY_REMOTE_INPUTS)), fromBundleArray(getBundleArrayFromBundle(bundle, KEY_DATA_ONLY_REMOTE_INPUTS)), allowGeneratedReplies, bundle.getInt(KEY_SEMANTIC_ACTION), bundle.getBoolean(KEY_SHOWS_USER_INTERFACE));
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: android.os.Bundle */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v6, types: [android.os.Bundle[], android.os.Parcelable[]] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static android.os.Bundle getBundleForAction(android.support.v4.app.NotificationCompat.Action r4) {
        /*
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            int r1 = r4.getIcon()
            java.lang.String r2 = "icon"
            r0.putInt(r2, r1)
            java.lang.CharSequence r1 = r4.getTitle()
            java.lang.String r2 = "title"
            r0.putCharSequence(r2, r1)
            android.app.PendingIntent r1 = r4.getActionIntent()
            java.lang.String r2 = "actionIntent"
            r0.putParcelable(r2, r1)
            android.os.Bundle r1 = r4.getExtras()
            if (r1 == 0) goto L_0x0030
            android.os.Bundle r1 = new android.os.Bundle
            android.os.Bundle r2 = r4.getExtras()
            r1.<init>(r2)
            goto L_0x0035
        L_0x0030:
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
        L_0x0035:
            boolean r2 = r4.getAllowGeneratedReplies()
            java.lang.String r3 = "android.support.allowGeneratedReplies"
            r1.putBoolean(r3, r2)
            java.lang.String r2 = "extras"
            r0.putBundle(r2, r1)
            android.support.v4.app.RemoteInput[] r2 = r4.getRemoteInputs()
            android.os.Bundle[] r2 = toBundleArray(r2)
            java.lang.String r3 = "remoteInputs"
            r0.putParcelableArray(r3, r2)
            boolean r2 = r4.getShowsUserInterface()
            java.lang.String r3 = "showsUserInterface"
            r0.putBoolean(r3, r2)
            int r2 = r4.getSemanticAction()
            java.lang.String r3 = "semanticAction"
            r0.putInt(r3, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.NotificationCompatJellybean.getBundleForAction(android.support.v4.app.NotificationCompat$Action):android.os.Bundle");
    }

    private static RemoteInput fromBundle(Bundle data) {
        ArrayList<String> allowedDataTypesAsList = data.getStringArrayList(KEY_ALLOWED_DATA_TYPES);
        Set<String> allowedDataTypes = new HashSet<>();
        if (allowedDataTypesAsList != null) {
            Iterator<String> it = allowedDataTypesAsList.iterator();
            while (it.hasNext()) {
                allowedDataTypes.add(it.next());
            }
        }
        return new RemoteInput(data.getString(KEY_RESULT_KEY), data.getCharSequence(KEY_LABEL), data.getCharSequenceArray(KEY_CHOICES), data.getBoolean(KEY_ALLOW_FREE_FORM_INPUT), data.getBundle(KEY_EXTRAS), allowedDataTypes);
    }

    private static Bundle toBundle(RemoteInput remoteInput) {
        Bundle data = new Bundle();
        data.putString(KEY_RESULT_KEY, remoteInput.getResultKey());
        data.putCharSequence(KEY_LABEL, remoteInput.getLabel());
        data.putCharSequenceArray(KEY_CHOICES, remoteInput.getChoices());
        data.putBoolean(KEY_ALLOW_FREE_FORM_INPUT, remoteInput.getAllowFreeFormInput());
        data.putBundle(KEY_EXTRAS, remoteInput.getExtras());
        Set<String> allowedDataTypes = remoteInput.getAllowedDataTypes();
        if (allowedDataTypes != null && !allowedDataTypes.isEmpty()) {
            ArrayList<String> allowedDataTypesAsList = new ArrayList<>(allowedDataTypes.size());
            for (String type : allowedDataTypes) {
                allowedDataTypesAsList.add(type);
            }
            data.putStringArrayList(KEY_ALLOWED_DATA_TYPES, allowedDataTypesAsList);
        }
        return data;
    }

    private static RemoteInput[] fromBundleArray(Bundle[] bundles) {
        if (bundles == null) {
            return null;
        }
        RemoteInput[] remoteInputs = new RemoteInput[bundles.length];
        for (int i = 0; i < bundles.length; i++) {
            remoteInputs[i] = fromBundle(bundles[i]);
        }
        return remoteInputs;
    }

    private static Bundle[] toBundleArray(RemoteInput[] remoteInputs) {
        if (remoteInputs == null) {
            return null;
        }
        Bundle[] bundles = new Bundle[remoteInputs.length];
        for (int i = 0; i < remoteInputs.length; i++) {
            bundles[i] = toBundle(remoteInputs[i]);
        }
        return bundles;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: android.os.Bundle */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v6, types: [android.os.Bundle[], android.os.Parcelable[]] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.os.Bundle[] getBundleArrayFromBundle(android.os.Bundle r3, java.lang.String r4) {
        /*
            android.os.Parcelable[] r0 = r3.getParcelableArray(r4)
            boolean r1 = r0 instanceof android.os.Bundle[]
            if (r1 != 0) goto L_0x0018
            if (r0 != 0) goto L_0x000b
            goto L_0x0018
        L_0x000b:
            int r1 = r0.length
            java.lang.Class<android.os.Bundle[]> r2 = android.os.Bundle[].class
            java.lang.Object[] r1 = java.util.Arrays.copyOf(r0, r1, r2)
            android.os.Bundle[] r1 = (android.os.Bundle[]) r1
            r3.putParcelableArray(r4, r1)
            return r1
        L_0x0018:
            r1 = r0
            android.os.Bundle[] r1 = (android.os.Bundle[]) r1
            android.os.Bundle[] r1 = (android.os.Bundle[]) r1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.NotificationCompatJellybean.getBundleArrayFromBundle(android.os.Bundle, java.lang.String):android.os.Bundle[]");
    }

    private NotificationCompatJellybean() {
    }
}
