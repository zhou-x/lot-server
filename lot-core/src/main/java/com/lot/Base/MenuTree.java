package com.lot.Base;

import com.lot.entity.LotRoutesEntity;
import com.lot.vo.LotRoutes.LotRoutesVo;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public class MenuTree {
    public List<LotRoutesVo> lists;

    public MenuTree(List<LotRoutesEntity> list) {
        List<LotRoutesVo> lists = new ArrayList<>();
        list.forEach(lotRoutesEntity -> {
            LotRoutesVo lotRoutesVo = new LotRoutesVo().from(lotRoutesEntity);
            lists.add(lotRoutesVo);
        });
        this.lists = lists;
    }

    public List<LotRoutesVo> buildMenu() {
        List<LotRoutesVo> newList = new ArrayList<>();
        lists.forEach(lotRoutesVo -> {
            if (lotRoutesVo.getpId().equals("-1")) {
                newList.add(lotRoutesVo);
            }
        });
        newList.forEach(lotRoutesVo -> {
            List<LotRoutesVo> child = buildChildTree(lotRoutesVo.getRoutesId());
            lotRoutesVo.setChildren(child);
        });
        return newList;
    }

    private List<LotRoutesVo> buildChildTree(String getPId) {
        //存放子菜单
        List<LotRoutesVo> childList = new ArrayList<>();
        for (LotRoutesVo lotRoutesVo : lists) {
            if (getPId.equals(lotRoutesVo.getpId())) {
                childList.add(lotRoutesVo);
            }
        }
        childList.forEach(lotRoutesVo -> {
            lotRoutesVo.setChildren(buildChildTree(lotRoutesVo.getRoutesId()));
        });
        return childList;
    }

}
