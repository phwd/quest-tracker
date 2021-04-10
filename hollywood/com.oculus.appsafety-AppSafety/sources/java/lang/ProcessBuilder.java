package java.lang;

import android.icu.text.PluralRules;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class ProcessBuilder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private List<String> command;
    private File directory;
    private Map<String, String> environment;
    private boolean redirectErrorStream;
    private Redirect[] redirects;

    public ProcessBuilder(List<String> command2) {
        if (command2 != null) {
            this.command = command2;
            return;
        }
        throw new NullPointerException();
    }

    public ProcessBuilder(String... command2) {
        this.command = new ArrayList(command2.length);
        for (String arg : command2) {
            this.command.add(arg);
        }
    }

    public ProcessBuilder command(List<String> command2) {
        if (command2 != null) {
            this.command = command2;
            return this;
        }
        throw new NullPointerException();
    }

    public ProcessBuilder command(String... command2) {
        this.command = new ArrayList(command2.length);
        for (String arg : command2) {
            this.command.add(arg);
        }
        return this;
    }

    public List<String> command() {
        return this.command;
    }

    public Map<String, String> environment() {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkPermission(new RuntimePermission("getenv.*"));
        }
        if (this.environment == null) {
            this.environment = ProcessEnvironment.environment();
        }
        return this.environment;
    }

    /* access modifiers changed from: package-private */
    public ProcessBuilder environment(String[] envp) {
        if (envp != null) {
            this.environment = ProcessEnvironment.emptyEnvironment(envp.length);
            for (String envstring : envp) {
                if (envstring.indexOf(0) != -1) {
                    envstring = envstring.replaceFirst("\u0000.*", "");
                }
                int eqlsign = envstring.indexOf(61, 0);
                if (eqlsign != -1) {
                    this.environment.put(envstring.substring(0, eqlsign), envstring.substring(eqlsign + 1));
                }
            }
        }
        return this;
    }

    public File directory() {
        return this.directory;
    }

    public ProcessBuilder directory(File directory2) {
        this.directory = directory2;
        return this;
    }

    static class NullInputStream extends InputStream {
        static final NullInputStream INSTANCE = new NullInputStream();

        private NullInputStream() {
        }

        @Override // java.io.InputStream
        public int read() {
            return -1;
        }

        @Override // java.io.InputStream
        public int available() {
            return 0;
        }
    }

    static class NullOutputStream extends OutputStream {
        static final NullOutputStream INSTANCE = new NullOutputStream();

        private NullOutputStream() {
        }

        @Override // java.io.OutputStream
        public void write(int b) throws IOException {
            throw new IOException("Stream closed");
        }
    }

    public static abstract class Redirect {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        public static final Redirect INHERIT = new Redirect() {
            /* class java.lang.ProcessBuilder.Redirect.AnonymousClass2 */

            @Override // java.lang.ProcessBuilder.Redirect
            public Type type() {
                return Type.INHERIT;
            }

            public String toString() {
                return type().toString();
            }
        };
        public static final Redirect PIPE = new Redirect() {
            /* class java.lang.ProcessBuilder.Redirect.AnonymousClass1 */

            @Override // java.lang.ProcessBuilder.Redirect
            public Type type() {
                return Type.PIPE;
            }

            public String toString() {
                return type().toString();
            }
        };

        public enum Type {
            PIPE,
            INHERIT,
            READ,
            WRITE,
            APPEND
        }

        public abstract Type type();

        public File file() {
            return null;
        }

        /* access modifiers changed from: package-private */
        public boolean append() {
            throw new UnsupportedOperationException();
        }

        public static Redirect from(final File file) {
            if (file != null) {
                return new Redirect() {
                    /* class java.lang.ProcessBuilder.Redirect.AnonymousClass3 */

                    @Override // java.lang.ProcessBuilder.Redirect
                    public Type type() {
                        return Type.READ;
                    }

                    @Override // java.lang.ProcessBuilder.Redirect
                    public File file() {
                        return File.this;
                    }

                    public String toString() {
                        return "redirect to read from file \"" + ((Object) File.this) + "\"";
                    }
                };
            }
            throw new NullPointerException();
        }

        public static Redirect to(final File file) {
            if (file != null) {
                return new Redirect() {
                    /* class java.lang.ProcessBuilder.Redirect.AnonymousClass4 */

                    @Override // java.lang.ProcessBuilder.Redirect
                    public Type type() {
                        return Type.WRITE;
                    }

                    @Override // java.lang.ProcessBuilder.Redirect
                    public File file() {
                        return File.this;
                    }

                    public String toString() {
                        return "redirect to write to file \"" + ((Object) File.this) + "\"";
                    }

                    /* access modifiers changed from: package-private */
                    @Override // java.lang.ProcessBuilder.Redirect
                    public boolean append() {
                        return false;
                    }
                };
            }
            throw new NullPointerException();
        }

        public static Redirect appendTo(final File file) {
            if (file != null) {
                return new Redirect() {
                    /* class java.lang.ProcessBuilder.Redirect.AnonymousClass5 */

                    @Override // java.lang.ProcessBuilder.Redirect
                    public Type type() {
                        return Type.APPEND;
                    }

                    @Override // java.lang.ProcessBuilder.Redirect
                    public File file() {
                        return File.this;
                    }

                    public String toString() {
                        return "redirect to append to file \"" + ((Object) File.this) + "\"";
                    }

                    /* access modifiers changed from: package-private */
                    @Override // java.lang.ProcessBuilder.Redirect
                    public boolean append() {
                        return true;
                    }
                };
            }
            throw new NullPointerException();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Redirect)) {
                return false;
            }
            Redirect r = (Redirect) obj;
            if (r.type() != type()) {
                return false;
            }
            return file().equals(r.file());
        }

        public int hashCode() {
            File file = file();
            if (file == null) {
                return super.hashCode();
            }
            return file.hashCode();
        }

        private Redirect() {
        }
    }

    private Redirect[] redirects() {
        if (this.redirects == null) {
            this.redirects = new Redirect[]{Redirect.PIPE, Redirect.PIPE, Redirect.PIPE};
        }
        return this.redirects;
    }

    public ProcessBuilder redirectInput(Redirect source) {
        if (source.type() == Redirect.Type.WRITE || source.type() == Redirect.Type.APPEND) {
            throw new IllegalArgumentException("Redirect invalid for reading: " + ((Object) source));
        }
        redirects()[0] = source;
        return this;
    }

    public ProcessBuilder redirectOutput(Redirect destination) {
        if (destination.type() != Redirect.Type.READ) {
            redirects()[1] = destination;
            return this;
        }
        throw new IllegalArgumentException("Redirect invalid for writing: " + ((Object) destination));
    }

    public ProcessBuilder redirectError(Redirect destination) {
        if (destination.type() != Redirect.Type.READ) {
            redirects()[2] = destination;
            return this;
        }
        throw new IllegalArgumentException("Redirect invalid for writing: " + ((Object) destination));
    }

    public ProcessBuilder redirectInput(File file) {
        return redirectInput(Redirect.from(file));
    }

    public ProcessBuilder redirectOutput(File file) {
        return redirectOutput(Redirect.to(file));
    }

    public ProcessBuilder redirectError(File file) {
        return redirectError(Redirect.to(file));
    }

    public Redirect redirectInput() {
        Redirect[] redirectArr = this.redirects;
        return redirectArr == null ? Redirect.PIPE : redirectArr[0];
    }

    public Redirect redirectOutput() {
        Redirect[] redirectArr = this.redirects;
        return redirectArr == null ? Redirect.PIPE : redirectArr[1];
    }

    public Redirect redirectError() {
        Redirect[] redirectArr = this.redirects;
        return redirectArr == null ? Redirect.PIPE : redirectArr[2];
    }

    public ProcessBuilder inheritIO() {
        Arrays.fill(redirects(), Redirect.INHERIT);
        return this;
    }

    public boolean redirectErrorStream() {
        return this.redirectErrorStream;
    }

    public ProcessBuilder redirectErrorStream(boolean redirectErrorStream2) {
        this.redirectErrorStream = redirectErrorStream2;
        return this;
    }

    public Process start() throws IOException {
        List<String> list = this.command;
        String[] cmdarray = (String[]) ((String[]) list.toArray(new String[list.size()])).clone();
        for (String arg : cmdarray) {
            if (arg == null) {
                throw new NullPointerException();
            }
        }
        String prog = cmdarray[0];
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkExec(prog);
        }
        File file = this.directory;
        String dir = file == null ? null : file.toString();
        for (int i = 1; i < cmdarray.length; i++) {
            if (cmdarray[i].indexOf(0) >= 0) {
                throw new IOException("invalid null character in command");
            }
        }
        try {
            return ProcessImpl.start(cmdarray, this.environment, dir, this.redirects, this.redirectErrorStream);
        } catch (IOException | IllegalArgumentException e) {
            String exceptionInfo = PluralRules.KEYWORD_RULE_SEPARATOR + e.getMessage();
            Throwable cause = e;
            if ((e instanceof IOException) && security != null) {
                try {
                    security.checkRead(prog);
                } catch (SecurityException se) {
                    exceptionInfo = "";
                    cause = se;
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Cannot run program \"");
            sb.append(prog);
            sb.append("\"");
            sb.append(dir == null ? "" : " (in directory \"" + dir + "\")");
            sb.append(exceptionInfo);
            throw new IOException(sb.toString(), cause);
        }
    }
}
