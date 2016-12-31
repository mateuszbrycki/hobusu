package controllers.action;

import play.libs.F;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import service.AuthenticationService;

import javax.inject.Inject;

import static play.mvc.Http.HeaderNames.AUTHORIZATION;
import static play.mvc.Http.HeaderNames.WWW_AUTHENTICATE;

/**
 * Created by Mateusz Brycki on 31/12/2016.
 */
public class BasicAuthAction extends Action<Result> {

    @Inject
    private AuthenticationService authenticationService;

    private static final String REALM = "Basic realm=\"Your Realm Here\"";

    @Override
    public F.Promise<Result> call(Http.Context context) throws Throwable {

        String token = context.request().getHeader(AUTHORIZATION);
        if (token == null) {
            context.response().setHeader(WWW_AUTHENTICATE, REALM);
            return F.Promise.pure(unauthorized());
        }

        if(authenticationService.isAuthorized(context, token)) {
            return delegate.call(context);
        } else {
            return F.Promise.pure(unauthorized());
        }
    }
}
