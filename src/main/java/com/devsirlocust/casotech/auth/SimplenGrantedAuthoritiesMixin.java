package com.devsirlocust.casotech.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SimplenGrantedAuthoritiesMixin {
  @JsonCreator
  public SimplenGrantedAuthoritiesMixin(@JsonProperty("authority") String role) {
  }

}
