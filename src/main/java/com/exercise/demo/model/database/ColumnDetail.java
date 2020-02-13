package com.exercise.demo.model.database;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2020/2/13 14:44
 */
public class ColumnDetail {

    private String field;

    private String type;

    private String canEmpty;

    private String key;

    private String defaultValue;

    private String comment;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCanEmpty() {
        return canEmpty;
    }

    public void setCanEmpty(String canEmpty) {
        this.canEmpty = canEmpty;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
