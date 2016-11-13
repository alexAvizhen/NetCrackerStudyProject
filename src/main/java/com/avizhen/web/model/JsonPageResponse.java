package com.avizhen.web.model;

import com.avizhen.web.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Александр on 11.11.2016.
 */
public class JsonPageResponse<T> extends PageImpl<T> {
    public JsonPageResponse(final List<T> content, final Pageable pageable, final long total) {
        super(content, pageable, total);
    }

    public JsonPageResponse(final List<T> content) {
        super(content);
    }

    public JsonPageResponse(final Page<T> page, final Pageable pageable) {
        super(page.getContent(), pageable, page.getTotalElements());
    }

    @JsonView(Views.Public.class)
    public int getTotalPages() {
        return super.getTotalPages();
    }

    @JsonView(Views.Public.class)
    public long getTotalElements() {
        return super.getTotalElements();
    }

    @JsonView(Views.Public.class)
    public boolean hasNext() {
        return super.hasNext();
    }

    @JsonView(Views.Public.class)
    public boolean isLast() {
        return super.isLast();
    }

    @JsonView(Views.Public.class)
    public boolean hasContent() {
        return super.hasContent();
    }

    @JsonView(Views.Public.class)
    public List<T> getContent() {
        return super.getContent();
    }

    @JsonView(Views.Public.class)
    public boolean hasPrevious() {
        return super.hasPrevious();
    }

    @JsonView(Views.Public.class)
    public int getNumber() {
        return super.getNumber();
    }

    @JsonView(Views.Public.class)
    public int getSize() {
        return super.getSize();
    }
}


