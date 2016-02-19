package com.eis.cloud.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by mingardia on 1/31/16.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="TodoModel")
public class TodoModel {

    protected String title;

    protected boolean finished;

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public boolean isFinished() {

        return finished;
    }

    public void setFinished(boolean finished) {

        this.finished = finished;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TodoModel todoModel = (TodoModel) o;

        if (finished != todoModel.finished) return false;
        return title != null ? title.equals(todoModel.title) : todoModel.title == null;

    }

    @Override
    public int hashCode() {

        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (finished ? 1 : 0);
        return result;
    }
}
