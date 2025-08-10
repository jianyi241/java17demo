package com.example.java17demo.util;

import lombok.ToString;

import java.util.List;

@ToString
public class PageUtil<T> {

    public PageUtil() {
    }

    public PageUtil(Integer pageNum, Integer pageSize, Integer totalRecord, List<T> list) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;
        this.list = list;
    }

    private Integer pageNum;
    private Integer pageSize;
    private Integer totalRecord;
    private Integer totalPage;
    private List<T> list;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalRecord() {
        Integer totalPage = this.totalRecord % pageSize == 0 ? this.totalRecord / pageSize : (this.totalRecord / pageSize) + 1;
        this.setTotalPage(totalPage);
        return totalRecord;
    }

    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageUtil{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", totalRecord=" + totalRecord +
                ", totalPage=" + totalPage +
                ", list=" + list +
                '}';
    }
}
