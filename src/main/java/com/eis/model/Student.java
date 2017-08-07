package com.eis.model;

import java.io.Serializable;

public class Student implements Serializable {

    private Long id;

    private String firstname;

    private String lastname;

    private String phone;

    private String description;

    private City city;

    private District district;

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(final String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(final String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public City getCity() {
        return this.city;
    }

    public void setCity(final City city) {
        this.city = city;
    }

    public District getDistrict() {
        return this.district;
    }

    public void setDistrict(final District district) {
        this.district = district;
    }

    @Override
    public String toString() {
        return this.id + ": " + this.firstname + " " + this.lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return id.equals(student.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
