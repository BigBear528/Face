package com.face.controller.dto;

import lombok.Data;

@Data
public class ChangePasswordDTO {
  private String id;
  private String currentPassword;
  private String newPassword;
}
