package com.face.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class StudentDTO {
  private String id;
  private String name;
  @JsonIgnore
  private String password;
  private int gender;
  private String face;
  private String token;
  private int img;
}
