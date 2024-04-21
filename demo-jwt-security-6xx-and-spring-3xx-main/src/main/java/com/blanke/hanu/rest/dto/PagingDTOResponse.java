package com.blanke.hanu.rest.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PagingDTOResponse {
    long totalPage;
    long totalElement;
    Object data;
}
