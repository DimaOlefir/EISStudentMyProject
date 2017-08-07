package com.eis.model;

import java.io.Serializable;
import java.util.List;

public class City implements Serializable {

    private Long id;

    private String name;

    private List<District> districtList;

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

    public List<District> getDistrictList() {
        return this.districtList;
    }

    public void setDistrictList(List<District> districtList) {
        this.districtList = districtList;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        return id.equals(city.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
