package nl.ing.app.provider;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import nl.ing.app.model.ErrorData;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.glassfish.jersey.Severity;

@Provider
@Slf4j
public class ConstraintViolationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException e) {
        log.error("Json parsing fails with ConstraintViolation {} " , ExceptionUtils.getStackTrace(e));
        ErrorData errorResponse = new ErrorData(e.getMessage(),
                Response.Status.BAD_REQUEST.name(), Severity.FATAL.name());

        return  Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }
}
