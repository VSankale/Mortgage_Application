package nl.ing.app.provider;

import com.fasterxml.jackson.core.JsonParseException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import nl.ing.app.model.ErrorData;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.glassfish.jersey.Severity;

@Provider
@Slf4j
public class JsonParseExceptionHandler implements ExceptionMapper<JsonParseException> {
    @Override
    public Response toResponse(JsonParseException e) {
        log.error("Json parsing fails  {} " , ExceptionUtils.getStackTrace(e));
        ErrorData errorResponse = new ErrorData(e.getMessage() ,
                Response.Status.BAD_REQUEST.name(), Severity.FATAL.name());

        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }
}
