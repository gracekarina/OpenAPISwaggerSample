package resource;

import data.QuoteData;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import model.Quote;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@OpenAPIDefinition(
        info = @Info(
                version = "1.0",
                title = "Quotes API",
                description = "This API manage  quotes",
                license = @License(name = "Apache 2.0", url = "http://foo.bar"),
                contact = @Contact(url = "http://gigantic-server.com", name = "Fred", email = "Fred@gigagantic-server.com")
        )
)
@Path("/quote")
@Produces({"application/json"})
public class QuoteService {
    static QuoteData quoteData = new QuoteData();

    @GET
    @Path("/quotes")
    @Operation(summary = "Gets a list of quotes",
            tags = {"quotes"},
            description = "Returns a list of quotes",
            responses = {
                    @ApiResponse(   content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Quote.class)
                    ))
            })
    public Response getQuotes(){
            return Response.ok().entity(quoteData.getQuotes()).build();
    }

    @GET
    @Path("/{quoteId}")
    @Operation(summary = "Find pet by ID",
            tags = {"quotes?"},
            description = "Returns a quote when 0 < ID <= 10.  ID > 10 or nonintegers will simulate API error conditions",
            responses = {
                    @ApiResponse(description = "The Quote", content = @Content(
                            schema = @Schema(implementation = Quote.class)
                    )),
                    @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
                    @ApiResponse(responseCode = "404", description = "Quote not found")
            })
    public Response getQuoteById(  @Parameter(
            description = "ID of quote that needs to be fetched",
            schema = @Schema(
                    type = "integer",
                    format = "int64",
                    description = "param ID of quote that needs to be fetched",
                    allowableValues = {"1","2","3"}
            ),
            required = true) @PathParam("quoteId") Long quoteId){
        Quote quote = QuoteData.getQuoteById(quoteId);
        if (null != quote) {
            return Response.ok().entity(quote).build();
        }else {
            return Response.ok().entity("Not Found").build();
        }
    }
    @POST
    @Consumes("application/json")
    @Operation(summary = "Add a new quote",
            tags = {"quotes"},
            responses = {
                    @ApiResponse(responseCode = "405", description = "Invalid input")
            })
   public Response createQuote(@Parameter(description = "Quote object that needs to be added to the list", required = true) Quote quote){
        QuoteData.addQuote(quote);
        return Response.ok().entity("SUCCESS").build();
    }

    @PUT
    @Consumes("application/json")
    @Operation( summary = "Update quote",
                responses = {
                        @ApiResponse(responseCode = "405", description = "Invalid Input")
                })
     public Response updateQuote(@Parameter(description = "Quote object that needs to be updated", required = true) Quote quote){
        QuoteData.addQuote(quote);
        return Response.ok().entity("SUCCESS").build();
    }

    @DELETE
    @Path("/{quoteId}")
    @Consumes("application/json")
    @Operation(
            summary = "delete quote",
            responses = {
                    @ApiResponse(responseCode = "405", description = "Invalid Input")
            })
    public Response deleteQuote(@Parameter(
            description = "ID of quote that needs to be fetched",
            schema = @Schema(
                    type = "integer",
                    format = "int64",
                    description = "param ID of quote that needs to be fetched",
                    allowableValues = {"1","2","3"}
            ),
            required = true) @PathParam("quoteId") Long quoteId){
        if (QuoteData.deleteQuote(quoteId)){
            return Response.ok().entity("SUCCESS").build();
        }
        return Response.ok().entity("FAILED").build();
    }
}
