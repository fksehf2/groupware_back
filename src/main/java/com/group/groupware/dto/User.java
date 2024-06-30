package com.group.groupware.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
    @JsonProperty("USER_ID")
    private String USER_ID;
    @JsonProperty("PWD")
    private String PWD;
    private String REG_STATUS;
}
