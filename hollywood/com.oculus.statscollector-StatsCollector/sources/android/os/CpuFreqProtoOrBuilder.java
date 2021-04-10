package android.os;

import android.os.CpuFreqProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface CpuFreqProtoOrBuilder extends MessageLiteOrBuilder {
    CpuFreqProto.Stats getCpuFreqs(int i);

    int getCpuFreqsCount();

    List<CpuFreqProto.Stats> getCpuFreqsList();

    int getJiffyHz();

    boolean hasJiffyHz();
}
