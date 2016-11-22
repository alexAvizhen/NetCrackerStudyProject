package com.avizhen.web.model;

/**
 * Created by Александр on 12.11.2016.
 */
public class SearchOrderAdvertsCriteria {
    private OrderAdvertsCriteria orderCriteria;
    private SearchAdvertsCriteria searchCriteria;

    public SearchOrderAdvertsCriteria() {
    }

    public OrderAdvertsCriteria getOrderCriteria() {
        return orderCriteria;
    }

    public void setOrderCriteria(OrderAdvertsCriteria orderCriteria) {
        this.orderCriteria = orderCriteria;
    }

    public SearchAdvertsCriteria getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(SearchAdvertsCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }
}
