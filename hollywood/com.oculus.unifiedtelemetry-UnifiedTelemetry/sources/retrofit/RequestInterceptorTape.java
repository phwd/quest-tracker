package retrofit;

import java.util.ArrayList;
import java.util.List;
import retrofit.RequestInterceptor;

public final class RequestInterceptorTape implements RequestInterceptor.RequestFacade, RequestInterceptor {
    public final List<CommandWithParams> tape = new ArrayList();

    public enum Command {
        ADD_HEADER {
            @Override // retrofit.RequestInterceptorTape.Command
            public void intercept(RequestInterceptor.RequestFacade requestFacade, String str, String str2) {
                requestFacade.addHeader(str, str2);
            }
        },
        ADD_PATH_PARAM {
            @Override // retrofit.RequestInterceptorTape.Command
            public void intercept(RequestInterceptor.RequestFacade requestFacade, String str, String str2) {
                requestFacade.addPathParam(str, str2);
            }
        },
        ADD_ENCODED_PATH_PARAM {
            @Override // retrofit.RequestInterceptorTape.Command
            public void intercept(RequestInterceptor.RequestFacade requestFacade, String str, String str2) {
                requestFacade.addEncodedPathParam(str, str2);
            }
        },
        ADD_QUERY_PARAM {
            @Override // retrofit.RequestInterceptorTape.Command
            public void intercept(RequestInterceptor.RequestFacade requestFacade, String str, String str2) {
                requestFacade.addQueryParam(str, str2);
            }
        },
        ADD_ENCODED_QUERY_PARAM {
            @Override // retrofit.RequestInterceptorTape.Command
            public void intercept(RequestInterceptor.RequestFacade requestFacade, String str, String str2) {
                requestFacade.addEncodedQueryParam(str, str2);
            }
        };

        public abstract void intercept(RequestInterceptor.RequestFacade requestFacade, String str, String str2);
    }

    public static final class CommandWithParams {
        public final Command command;
        public final String name;
        public final String value;

        public CommandWithParams(Command command2, String str, String str2) {
            this.command = command2;
            this.name = str;
            this.value = str2;
        }
    }

    @Override // retrofit.RequestInterceptor.RequestFacade
    public void addEncodedPathParam(String str, String str2) {
        this.tape.add(new CommandWithParams(Command.ADD_ENCODED_PATH_PARAM, str, str2));
    }

    @Override // retrofit.RequestInterceptor.RequestFacade
    public void addEncodedQueryParam(String str, String str2) {
        this.tape.add(new CommandWithParams(Command.ADD_ENCODED_QUERY_PARAM, str, str2));
    }

    @Override // retrofit.RequestInterceptor.RequestFacade
    public void addHeader(String str, String str2) {
        this.tape.add(new CommandWithParams(Command.ADD_HEADER, str, str2));
    }

    @Override // retrofit.RequestInterceptor.RequestFacade
    public void addPathParam(String str, String str2) {
        this.tape.add(new CommandWithParams(Command.ADD_PATH_PARAM, str, str2));
    }

    @Override // retrofit.RequestInterceptor.RequestFacade
    public void addQueryParam(String str, String str2) {
        this.tape.add(new CommandWithParams(Command.ADD_QUERY_PARAM, str, str2));
    }

    @Override // retrofit.RequestInterceptor
    public void intercept(RequestInterceptor.RequestFacade requestFacade) {
        for (CommandWithParams commandWithParams : this.tape) {
            commandWithParams.command.intercept(requestFacade, commandWithParams.name, commandWithParams.value);
        }
    }
}
