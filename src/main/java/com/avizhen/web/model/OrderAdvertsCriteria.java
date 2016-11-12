package com.avizhen.web.model;

/**
 * Created by Александр on 11.11.2016.
 */
public class OrderAdvertsCriteria {
    private int pageNumber;
    private int pageSize;
    private String sortField;
    private String sortDirection;

    public OrderAdvertsCriteria() {
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }
}
