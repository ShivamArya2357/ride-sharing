package com.share.ride.ridesharing.validation;

import com.share.ride.ridesharing.enums.ApiName;

public interface IRequestValidator<K> {
	
	void validate(K input, ApiName api);
}
