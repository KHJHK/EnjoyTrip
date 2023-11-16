package com.ssafy.enjoytrip.attraction.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AttractionDto {
   int contentId;
   int contentTypeId;
   String title;
   String addr1;
   String addr2;
   String tel;
   String firstImage;
   String firstImage2;
   int sidoCode;
   int gugunCode;
   double latitude;
   double longitude;
}
