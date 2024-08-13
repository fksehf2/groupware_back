package com.group.groupware.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

    @JsonProperty("USER_ID")
    private String USER_ID;
    @JsonProperty("PWD")
    private String PWD;
    @JsonProperty("REG_STATUS")
    private String REG_STATUS;
    @JsonProperty("TOKEN")
    private String TOKEN;
    @JsonProperty("USER_NM")
    private String USER_NM;
    @JsonProperty("RESULTMESSEAGE")
    private String ResultMessage;
}
