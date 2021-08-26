package com.share.ride.ridesharing.validation;

import com.fasterxml.jackson.databind.JsonNode;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.ValidationMessage;
import com.share.ride.ridesharing.enums.ApiName;
import com.share.ride.ridesharing.enums.ErrorCode;
import com.share.ride.ridesharing.exception.RideSharingException;
import com.share.ride.ridesharing.util.ObjectMapperFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ApiRequestValidator implements IRequestValidator<Object> {

	private static final Logger logger = LoggerFactory.getLogger(ApiRequestValidator.class);

	@Autowired
   	private ValidationSchemaStore validationSchemaStore;

	@Autowired
	private ReportValidator reportValidator;

	@Override
	public void validate(Object input, ApiName api) {

		JsonNode jsonFile = ObjectMapperFactory.getMapper().convertValue(input, JsonNode.class);
		JsonSchema schema = validationSchemaStore.getSchema(api.name());
		Set<ValidationMessage> report = null;
		try {
			report = schema.validate(jsonFile);
			reportValidator.processReport(report);
		} catch (RideSharingException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Schema processing failed!!", e);
			throw new RideSharingException(ErrorCode.REQUEST_SCHEMA_PROCESSING_FAILED, api.name());
		}
	}
}
