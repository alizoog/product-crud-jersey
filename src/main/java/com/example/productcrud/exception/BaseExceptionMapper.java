package com.example.productcrud.exception;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;


@Provider
public class BaseExceptionMapper implements ExceptionMapper<Exception> {
	@Override
	public Response toResponse(Exception ex) {
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).type(MediaType.TEXT_PLAIN_TYPE).build();
	}
}