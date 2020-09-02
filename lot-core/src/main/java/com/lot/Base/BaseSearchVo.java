package com.lot.Base;

/***
 * @author zel
 * 定义默认的基础的搜索类，内容包括分页、筛选选项、默认搜索显示的>>>ynFlag="Y"<<<
 */
public class BaseSearchVo {
    private PagesVo pagesVo = new PagesVo();
    private String orderColumn;
    private String orderDirection;
    private String ynFlag = "Y";

    public PagesVo getPagesVo() {
        return pagesVo;
    }

    public void setPagesVo(PagesVo pagesVo) {
        this.pagesVo = pagesVo;
    }

    public String getOrderColumn() {
        return orderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public String getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }

    public String getYnFlag() {
        return ynFlag;
    }

    public void setYnFlag(String ynFlag) {
        this.ynFlag = ynFlag;
    }
}
