package com.uit.coffeeshop.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultPaginationDTO {

    private Meta meta;
    private Object result;

    @Getter
    @Setter
    public static class Meta {

        private int page;
        private int pageSize;
        private int pages;
        private long total;
    }
}