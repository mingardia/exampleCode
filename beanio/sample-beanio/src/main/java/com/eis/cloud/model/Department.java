package com.eis.cloud.model;

/**
 * Created by mingardia on 2/1/16.
 */
public class Department {
    String name;

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {

        return name != null ? name.hashCode() : 0;
    }
}
