package nl.ing.app.controller;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import nl.ing.app.entity.InterestRate;
import nl.ing.app.model.MortgageCheckRequest;
import nl.ing.app.model.MortgageCheckResponse;
import nl.ing.app.service.MortgageService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Path("/api")
@Slf4j
public class MortgageController {


    private final MortgageService mortgageService;

    public MortgageController(MortgageService mortgageService) {
        this.mortgageService = mortgageService;
    }

    @GET
    @Path("/interest-rates")
    @Produces(MediaType.APPLICATION_JSON)
    public Response showAvailableHomeInterestRate() throws Exception {
        List<InterestRate> list = mortgageService.fetchInterestRate();
        if (!CollectionUtils.isEmpty(list)) {

            GenericEntity<List<InterestRate>> entity = new GenericEntity<>(list) {
            };
            return Response.ok(entity).build();
        }

        return Response.noContent().build();

    }

    @POST
    @Path("/mortgage-check")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response checkMortgageCheck(@Valid @RequestBody MortgageCheckRequest mortgageCheckRequest) throws Exception {
        log.debug("Checking Mortgage Check Request: {}", mortgageCheckRequest);
        MortgageCheckResponse response = mortgageService.checkLoanFeasibility(mortgageCheckRequest);
        log.debug("Mortgage Check Response: {}", response);
        if (response != null) {
            return Response.status(Response.Status.OK).entity(response).build();
        }

        return Response.noContent().build();

    }
}

