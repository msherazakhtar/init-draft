package com.expense.tracker.dtos;

import java.util.List;

public class SearchCriteria {
    private Long id;
    private List<SearchCriteriaParmeters> parameters;
    private Integer pageSize;
    private Integer pageNumber;
    private String sortBy;
    private String sortingOrder;
    
    public SearchCriteria(Long id, List<SearchCriteriaParmeters> parameters, Integer pageSize, Integer pageNumber,String sortBy,String sortingOrder) {
        this.id = id;
        this.parameters = parameters;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.sortBy = sortBy;
        this.sortingOrder = sortingOrder;
    }   
    public SearchCriteria() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public List<SearchCriteriaParmeters> getParameters() {
        return parameters;
    }
    public void setParameters(List<SearchCriteriaParmeters> parameters) {
        this.parameters = parameters;
    }
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    public Integer getPageNumber() {
        return pageNumber;
    }
    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }
    public String getSortBy() {
        return sortBy;
    }
    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
    public String getSortingOrder() {
        return sortingOrder;
    }
    public void setSortingOrder(String sortingOrder) {
        this.sortingOrder = sortingOrder;
    }

   
   
 
    
}
