package com.lot.Base;

/**
 * @author zel
 * 分页基础类
 * 给定默认的总数，初始位置以及一页所展示的条数
 * */
public class PagesVo {
    private long total = 0;
    private int current = 1;
    private int pageSize = 10;


    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
