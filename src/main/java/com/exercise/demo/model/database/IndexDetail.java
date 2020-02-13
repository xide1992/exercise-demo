package com.exercise.demo.model.database;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2020/2/13 15:01
 */
public class IndexDetail {

    private String nonUnique;
    private String keyName;
    private String columnName;

    public String getNonUnique() {
        return nonUnique;
    }

    public void setNonUnique(String nonUnique) {
        this.nonUnique = nonUnique;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
}
