package com.example.computershop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolRequest {
   private int size;
   private int page;
   private boolean desc;
   private String direction;
   private String properties;

}
