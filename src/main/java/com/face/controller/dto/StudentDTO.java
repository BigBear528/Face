package com.face.controller.dto;

import lombok.Data;

@Data
public class StudentDTO {
  private String id;
  private String name;
  private String password;
  private int gender;
  private String face;
  private String token;
}
