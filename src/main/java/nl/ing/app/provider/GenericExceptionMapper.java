package nl.ing.app.provider;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import nl.ing.app.model.ErrorData;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.glassfish.jersey.Severity;


@Provider
@Slf4j
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(final Exception e) {
        log.error(ExceptionUtils.getStackTrace(e));
        ErrorData errorResponse = new ErrorData(e.getMessage(),
                Response.Status.INTERNAL_SERVER_ERROR.name(), Severity.FATAL.name());

        return Response.serverError().entity(errorResponse).build();
    }
}
