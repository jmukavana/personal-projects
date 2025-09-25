package com.jamlech.auth_service.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestResponse {
    private String id;
    private String username;
    private  String email;
    private String  phoneNumber;
}
