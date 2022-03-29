package com.face.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class LoginDTO {
  private String id;
  private String password;
}
