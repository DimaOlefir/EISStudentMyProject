package com.eis.model;

import java.io.Serializable;

public class District implements Serializable {

    private Long id;

    private String name;

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        District district = (District) o;

        return id.equals(district.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
