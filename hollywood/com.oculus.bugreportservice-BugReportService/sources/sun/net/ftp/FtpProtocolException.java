package sun.net.ftp;

public class FtpProtocolException extends Exception {
    private static final long serialVersionUID = 5978077070276545054L;
    private final FtpReplyCode code;

    public FtpProtocolException(String str) {
        super(str);
        this.code = FtpReplyCode.UNKNOWN_ERROR;
    }

    public FtpProtocolException(String str, FtpReplyCode ftpReplyCode) {
        super(str);
        this.code = ftpReplyCode;
    }
}
