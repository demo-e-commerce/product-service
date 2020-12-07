package com.example.demo.controller.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class RequestPagingDto {
    int page = 0;
    int size = 100;
    String sortBy = "id";
    String sort = Sort.Direction.DESC.toString();

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Pageable getPageable() {
        return PageRequest.of(
                page,
                size,
                Sort.by(
                        Sort.Direction.fromString(sort),
                        sortBy
                )
        );
    }
}
