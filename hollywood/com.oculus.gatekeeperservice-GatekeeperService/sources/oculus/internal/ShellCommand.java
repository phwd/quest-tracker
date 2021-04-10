package oculus.internal;

import android.os.Binder;
import android.os.ResultReceiver;
import java.io.FileDescriptor;

public abstract class ShellCommand extends android.os.ShellCommand {
    public int exec(Binder target, FileDescriptor in, FileDescriptor out, FileDescriptor err, String[] args, ResultReceiver resultReceiver) {
        return exec(target, in, out, err, args, null, resultReceiver);
    }
}
