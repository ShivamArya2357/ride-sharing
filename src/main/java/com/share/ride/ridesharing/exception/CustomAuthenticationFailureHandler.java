package com.share.ride.ridesharing.exception;

import com.share.ride.ridesharing.enums.ServiceStatus;
import com.share.ride.ridesharing.model.Error;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.share.ride.ridesharing.model.ServiceResponse;
import com.share.ride.ridesharing.util.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, 
                                      AuthenticationException exception
  ) throws IOException, ServletException {

    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    ServiceResponse<Object> serviceResponse = new ServiceResponse<>();
    serviceResponse.setStatus(ServiceStatus.FAIL);
    List<Error> errors = new ArrayList<>();
    Error error = new Error();
    error.setErrorCode(String.valueOf(HttpStatus.UNAUTHORIZED));
    error.setErrorDescription(getErrorDescription(response.getHeader("WWW-Authenticate")));
    errors.add(error);
    serviceResponse.setErrors(errors);
    // set response
    response.getOutputStream().println(serviceResponse.toString());
    response.addHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
    response.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
  }

  /**
   * @param keycloakErrorResponse keycloakErrorResponse
   * @return description
   */
  private String getErrorDescription(String keycloakErrorResponse) {

    String errorDesc = "";
    if (StringUtils.isNotEmpty(keycloakErrorResponse)) {
      String[] strArr = keycloakErrorResponse.split("error_description=");
      if (strArr.length == 2) {
        errorDesc = strArr[1];
        errorDesc = errorDesc.replace("\"", "");
      } else {
        errorDesc = keycloakErrorResponse;
      }
    }
    return errorDesc;
  }
}
