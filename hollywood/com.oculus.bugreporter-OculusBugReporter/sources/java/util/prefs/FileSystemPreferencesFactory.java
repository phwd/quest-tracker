package java.util.prefs;

/* access modifiers changed from: package-private */
public class FileSystemPreferencesFactory implements PreferencesFactory {
    FileSystemPreferencesFactory() {
    }

    @Override // java.util.prefs.PreferencesFactory
    public Preferences userRoot() {
        return FileSystemPreferences.getUserRoot();
    }

    @Override // java.util.prefs.PreferencesFactory
    public Preferences systemRoot() {
        return FileSystemPreferences.getSystemRoot();
    }
}
