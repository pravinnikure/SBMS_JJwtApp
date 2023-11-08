package com.app.restapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

//It contains JWT token and some notes on login success
public class UserResponse {
	
	private String token;
	private String notes;

}
