package com.lot.serviceImpl;

import com.lot.Base.PutMap;
import com.lot.dao.*;
import com.lot.entity.*;
import com.lot.mapper.LotCodeMapper;
import com.lot.mapper.LotResourcesMapper;
import com.lot.service.LotResourcesService;
import com.lot.util.DateUtils;
import com.lot.util.IDKeyUtil;
import com.lot.util.Msg;
import com.lot.vo.lotResources.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LotResourcesServiceImpl implements LotResourcesService {
    private static final Logger log = LogManager.getLogger(LotResourcesServiceImpl.class);
    @Autowired
    private LotResourcesMapper lotResourcesMapper;
    @Autowired
    private LotResourcesDao lotResourcesDao;
    @Autowired
    private LotCodeMapper lotCodeMapper;
    @Autowired
    private LotResourcesHistoryDao lotResourcesHistoryDao;
    @Autowired
    private LotResourcesManageDao lotResourcesManageDao;
    @Autowired
    private LotResourcesSaveDao lotResourcesSaveDao;
    @Autowired
    private LotResourcesFreightDao lotResourcesFreightDao;

    private List<List<LotResourcesEntity>> resultList;

    @Override
    public Msg findList(LotResourcesSearchVo searchVo) {
        Map<String, Object> conMap = new PutMap().returnMap(searchVo);
        if (searchVo.getBrand() != null && !searchVo.getBrand().equals("")) {
            conMap.put("brand", searchVo.getBrand());
        }
        if (searchVo.getMaterial_or_model() != null && !searchVo.getMaterial_or_model().equals("")) {
            conMap.put("material_or_model", searchVo.getMaterial_or_model());
        }
        if (searchVo.getCreateDate() != null && searchVo.getCreateDate().size() != 0) {
            conMap.put("createTimeStart", DateUtils.getDayBegin(searchVo.getCreateDate().get(0)));
            conMap.put("createTimeEnd", DateUtils.getDayEnd(searchVo.getCreateDate().get(1)));
        }
        if (searchVo.getSelectDate() != null && !searchVo.getMaterial_or_model().equals("")) {
            conMap.put("startSelectDate", DateUtils.getDayBegin(Timestamp.valueOf(searchVo.getSelectDate())));
            conMap.put("endSelectDate", DateUtils.getDayEnd(Timestamp.valueOf(searchVo.getSelectDate())));
        }
        List<LotResourcesEntity> list = lotResourcesMapper.findList(conMap);
        list.forEach(e -> {
            if (e.getLastPrice() != null) {
                e.setLastPrice(e.getPrice().subtract(e.getLastPrice()));
            }
        });
        return Msg.success().add("list", list).add("total", lotResourcesMapper.getCount(conMap));
    }

    @Override
    public LotResourcesEntity get(String resourcesId) {
        return lotResourcesDao.getOne(resourcesId);
    }

    @Override
    public void save(LotResourcesSaveVo saveVo) {
        LotResourcesEntity lotResourcesEntity;
        String rId;
        if (saveVo.getResourcesId() != null) {
            rId = String.valueOf(saveVo.getResourcesId());
            lotResourcesEntity = lotResourcesDao.getOne(rId);
        } else {
            lotResourcesEntity = new LotResourcesEntity();
            rId = IDKeyUtil.getStringId();
            lotResourcesEntity.setResourcesId(rId);
        }
        lotResourcesEntity = (LotResourcesEntity) saveVo.setInitial(lotResourcesEntity);
        lotResourcesEntity.setBrand(saveVo.getBrand());
        lotResourcesEntity.setModel(saveVo.getModel());
        lotResourcesEntity.setPrice(saveVo.getPrice());
        if (saveVo.getLastPrice() != null) {
            lotResourcesEntity.setLastPrice(saveVo.getLastPrice());
        }
        lotResourcesEntity.setMaterial(saveVo.getMaterial());
        lotResourcesEntity.setAddress(saveVo.getAddress());
        lotResourcesEntity.setSubordinateArea(saveVo.getSubordinateArea());
        lotResourcesEntity.setFreight(saveVo.getFreight());

        //保存历史信息
        LotResourcesHistoryEntity lotResourcesHistoryEntity = new LotResourcesHistoryEntity();
        lotResourcesHistoryEntity.setResourcesHistoryId(IDKeyUtil.getStringId());
        lotResourcesHistoryEntity.setResourcesId(rId);
        lotResourcesHistoryEntity.setLastPrice(saveVo.getLastPrice());
        lotResourcesHistoryEntity.setAddress(saveVo.getAddress());
        lotResourcesHistoryEntity.setSubordinateArea(saveVo.getSubordinateArea());
        lotResourcesHistoryEntity.setModel(saveVo.getModel());
        lotResourcesHistoryEntity.setBrand(saveVo.getBrand());
        lotResourcesHistoryEntity.setPrice(saveVo.getPrice());
        lotResourcesHistoryEntity.setMaterial(saveVo.getMaterial());
        lotResourcesHistoryEntity.setYnFlag("Y");
        lotResourcesHistoryDao.save(lotResourcesHistoryEntity);

        lotResourcesDao.save(lotResourcesEntity);
    }

    @Override
    public void delete(String resourcesId) {
        LotResourcesEntity lotResourcesEntity = lotResourcesDao.getOne(resourcesId);
        lotResourcesEntity.setYnFlag("N");
        lotResourcesDao.save(lotResourcesEntity);
    }

    @Override
    public List<LotResourcesEntity> getList(String address, String brand) {
        String[] str = address.split(",");
        List<LotResourcesEntity> list = new ArrayList<>();
        if (str.length >= 2) {
            list = lotResourcesMapper.getSimpleList(str[1], brand);
            if (list.size() == 0) {
                list = lotResourcesMapper.getSimpleList(str[0], brand);
            }
        }
        list.forEach(e -> {
            if (e.getLastPrice() != null) {
                e.setLastPrice(e.getPrice().subtract(e.getLastPrice()));
            }
        });
        return list;
    }

    @Override
    public List<LotResourcesEntity> searchList(String searchField) {
        return lotResourcesMapper.searchList(searchField);
    }

    @Override
    public Msg getConditionList() {
//        List<LotResourcesEntity> list = lotResourcesDao.findAll();

        List<LotResourcesFreightEntity> list = lotResourcesFreightDao.findAll();
        Set<String> firstAddressList = new HashSet<>();
        list.forEach(e -> {
            if (e.getYnFlag() != null && e.getYnFlag().equals("Y")) {
                firstAddressList.add(e.getAddress());
            }
        });
        List<Object> maps = new ArrayList<>();
        firstAddressList.forEach(e -> {
            List<LotResourcesFreightEntity> list1 = lotResourcesFreightDao.findAllByAddress(e);
            Set<String> twoAddressList = new HashSet<>();
            list1.forEach(es -> {
                if (es.getSubordinateArea() != null) {
                    twoAddressList.add(es.getSubordinateArea());
                }
            });
            maps.add(twoAddressList);
        });
        Map<String, Object> mapss = new HashMap<>();
        mapss.put("addressSum", firstAddressList);
        mapss.put("addressData", maps);


//        List<LotCodeEntity> addressList = lotCodeMapper.getCode("ADDRESS");
        List<LotCodeEntity> steelList = lotCodeMapper.getCode("STEEL");
        List<LotCodeEntity> normList = lotCodeMapper.getCode("NORM");
        List<LotCodeEntity> MODELList = lotCodeMapper.getCode("MODEL");
        List<LotCodeEntity> WH_brandList = lotCodeMapper.getCode("ADDRESS_WH");

//        List<String> addressSet = new ArrayList<>();
        List<String> WH_brandSet = new ArrayList<>();
        List<String> materialSet = new ArrayList<>();
        List<String> modelSet = new ArrayList<>();
        List<String> modelSetTo = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
//        addressList.forEach(e -> {
//            addressSet.add(e.getCodeDesc());
//        });
        WH_brandList.forEach(e -> {
            WH_brandSet.add(e.getCodeDesc());
        });
        steelList.forEach(e -> {
            materialSet.add(e.getCodeDesc());
        });
        normList.forEach(e -> {
            modelSet.add(e.getCodeDesc());
        });
        MODELList.forEach(e -> {
            modelSetTo.add(e.getCodeDesc());
        });
        map.put("address", mapss);
        map.put("wuhan", WH_brandSet);
        map.put("material", materialSet);
        map.put("model", modelSet);
        map.put("models", modelSetTo);
        return Msg.success().add("eve", map);
    }

    /**
     * 根据给定的条件生成多个方案
     * 条件有地区、一个或多个品牌、一个或多个规格。。。
     * 返回多个方案，其中有一个最优方案
     * 最优方案规则如下： 根据一个规格获取输入的多个品牌中取得最低价格的一条   以此类推>>
     * 其余方案规则如下： 根据每个品牌的每个规格匹配出价格>
     * ps: 有多少条品牌数据就有多少条方案？    例：两条品牌的具有两条品牌方案+一条最优方案。
     * <p>
     * 先把规格对应的品牌的价格查出来，比较大小，取最小值到结果类中
     */
    @Override
    public Msg generateScheme(GenerateSchemeVo vo) {
        List<List<LotResourcesEntity>> list = new ArrayList<>();    //获取整个的状态的值
        String[] addressStr = vo.getAddress().split(",");
        LotResourcesFreightEntity freightEntityList = new LotResourcesFreightEntity();
        if (addressStr.length >= 2) {
            freightEntityList = lotResourcesFreightDao.getBySubordinateAreaIsAndYnFlagIs(addressStr[1], "Y");
        }

        if (vo.getMaterialAndModelList() != null && vo.getMaterialAndModelList().size() != 0) {
            int count = vo.getMaterialAndModelList().size();
            for (int i = 0; i < count; i++) {
                if (vo.getBrandList() != null && vo.getBrandList().size() != 0) {
                    List<LotResourcesEntity> resourcesList = new ArrayList<>();
                    if (addressStr.length >= 2) {
                        resourcesList = lotResourcesMapper.findAllByBrandAndModel(addressStr[1], vo.getBrandList(), vo.getMaterialAndModelList().get(i).getMaterial(), vo.getMaterialAndModelList().get(i).getModel());
                        if (resourcesList.size() == 0) {
                            resourcesList = lotResourcesMapper.findAllByBrandAndModel(addressStr[0], vo.getBrandList(), vo.getMaterialAndModelList().get(i).getMaterial(), vo.getMaterialAndModelList().get(i).getModel());
                        }
                    }
                    resourcesList = this.returnGenerateList(resourcesList);
                    list.add(resourcesList);
                }
            }
        }
        if (list.size() != 0 && list != null) {
            decare(0, list, new ArrayList<>());
        }
        List<Map<String, Object>> resultSchemeVoLists = new ArrayList<>();
        if (this.resultList != null && this.resultList.size() != 0) {
            LotResourcesFreightEntity finalFreightEntityList = freightEntityList;
            this.resultList.forEach(e -> {
                int i = 0;
                int sumWeight = 0;
                BigDecimal sumPrice = new BigDecimal(0);
                Map<String, Object> resultSchemeVoMap = new LinkedHashMap<>();
                List<ResultSchemeVo> resultSchemeVoList = new ArrayList<>();
                for (LotResourcesEntity lotResourcesEntity : e) {
                    ResultSchemeVo resultSchemeVo = new ResultSchemeVo();
                    resultSchemeVo.setAddress(lotResourcesEntity.getAddress());
                    resultSchemeVo.setBrand(lotResourcesEntity.getBrand());
                    resultSchemeVo.setMaterial(lotResourcesEntity.getMaterial());
                    resultSchemeVo.setModel(lotResourcesEntity.getModel());
                    resultSchemeVo.setPrice(lotResourcesEntity.getPrice());
                    resultSchemeVo.setResourcesId(lotResourcesEntity.getResourcesId());
                    resultSchemeVo.setSubordinateArea(lotResourcesEntity.getSubordinateArea());
                    resultSchemeVo.setWeight(vo.getMaterialAndModelList().get(i).getWeight());
                    sumWeight = sumWeight + Integer.parseInt(vo.getMaterialAndModelList().get(i).getWeight());
                    sumPrice = sumPrice.add(lotResourcesEntity.getPrice());
                    resultSchemeVoList.add(resultSchemeVo);
                    i++;
                }
                resultSchemeVoMap.put("sumPrice", sumPrice);
                resultSchemeVoMap.put("sumWeight", sumWeight);
                resultSchemeVoMap.put("insideList", resultSchemeVoList);
                if (finalFreightEntityList != null) {
                    resultSchemeVoMap.put("freight", finalFreightEntityList.getFreight());
                }
                resultSchemeVoLists.add(resultSchemeVoMap);
            });
        }
        return Msg.success().add("result", resultSchemeVoLists);
    }

    //组合添加方案
    public void decare(int index, List<List<LotResourcesEntity>> all, List<LotResourcesEntity> temp) {
        resultList = new ArrayList<>();
        List<LotResourcesEntity> list = all.get(index);
        int allSize = all.size();
        int removeCount = allSize - index;
        for (int i = 0; i < list.size(); i++) {
            LotResourcesEntity value = list.get(i);
            if (i > 0) {
                for (int j = 0; j < removeCount; j++) {
                    temp.remove(temp.size() - 1);
                }
            }
            temp.add(value);
            if (index == allSize - 1) {
                List<LotResourcesEntity> addList = new ArrayList<>();
                temp.forEach(e -> {
                    addList.add(e);
                });
                this.resultList.add(addList);
            }
            if (index < allSize - 1) {
                decare(index + 1, all, temp);
            }
        }
    }

    @Override
    public void addSubPrice(int prices) {
        BigDecimal price = new BigDecimal(prices);
        List<LotResourcesEntity> list = lotResourcesDao.findAll();
        list.forEach(e -> {
            e.setLastPrice(e.getPrice());
            e.setPrice(e.getPrice().add(price));
        });

        lotResourcesDao.saveAll(list);

        List<LotResourcesManageEntity> manageList = lotResourcesManageDao.findAll();
        manageList.forEach(e -> {
            if(e.getPrice()!=null){
            }else{
                e.setPrice(BigDecimal.valueOf(0));
            }
            e.setPrice(e.getPrice().add(price));
        });

        lotResourcesManageDao.saveAll(manageList);

        //设置历史信息
        list.forEach(e -> {
            LotResourcesHistoryEntity lotResourcesHistoryEntity = new LotResourcesHistoryEntity();
            lotResourcesHistoryEntity.setResourcesHistoryId(IDKeyUtil.getStringId());
            lotResourcesHistoryEntity.setResourcesId(e.getResourcesId());
            lotResourcesHistoryEntity.setAddress(e.getAddress());
            lotResourcesHistoryEntity.setModel(e.getModel());
            lotResourcesHistoryEntity.setSubordinateArea(e.getSubordinateArea());
            lotResourcesHistoryEntity.setBrand(e.getBrand());
            lotResourcesHistoryEntity.setPrice(e.getPrice());
            lotResourcesHistoryEntity.setMaterial(e.getMaterial());
            lotResourcesHistoryEntity.setYnFlag("Y");
            lotResourcesHistoryDao.save(lotResourcesHistoryEntity);
        });
    }

    @Override
    public Msg getAddressListByAddress(String address) {
        String[] str = address.split(",");
        List<LotResourcesManageEntity> list = new ArrayList<>();
        String nowAddress = "";
        if (str.length >= 2) {
            list = lotResourcesManageDao.findAllBySubordinateArea(str[1]);
            nowAddress = str[1];
            if (list.size() == 0) {
                list = lotResourcesManageDao.findAllBySubordinateArea(str[0]);
                nowAddress = str[0];
            }
        }
        Set<String> addressList = new HashSet<>();
        if (list != null && list.size() != 0) {
            list.forEach(e -> {
                addressList.add(e.getBrand());
            });
        }
        return Msg.success().add("addressList", addressList).add("nowAddress", nowAddress);
    }


    /**
     * 根据单个日期获取历史的资源数据   如果当前的单个日期里面有多个数据，应当只取其中最后创建的数据
     */
    @Override
    public Msg getResourcesListByDate(Timestamp date, String mark, String address, String brand) {
        Map<String, Object> map = new HashMap<>();
        if (date != null) {
            map.put("startDate", DateUtils.getDayBegin(date));
            map.put("endDate", DateUtils.getDayEnd(date));
        }
        if (mark != null) {
            map.put("mark", mark);
        }
        String[] str = address.split(",");
        if (brand != null) {
            map.put("brand", brand);
        }
        List<LotResourcesHistoryEntity> list = new ArrayList<>();
        if (str.length >= 2) {
            if (address != null) {
                map.put("address", str[1]);
            }
            list = lotResourcesMapper.getResourcesListByDate(map);
            if (list.size() == 0) {
                if (address != null) {
                    map.put("address", str[0]);
                }
                list = lotResourcesMapper.getResourcesListByDate(map);
            }
        }
        if (mark != null) {
            list.forEach(e -> {
                e.setModel(e.getModel() + "-22");
            });
        }
        list = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getMaterial() + ";" + o.getModel()))), ArrayList::new));
//        list = removeDuplicateOutputField(list);
        return Msg.success().add("list", list);
    }

    /**
     * 新增快捷修改资源的值
     */
    @Override
    public void addSubPrices(SubPrices subPrices) {
        if (subPrices.getStatus().equals("Y")) {                  //根据地区品牌修改全部的价格
            List<LotResourcesEntity> list = lotResourcesDao.findAllByAddressAndBrandAndSubordinateArea(subPrices.getAddress(), subPrices.getBrand(), subPrices.getSubordinateArea());
            if (list != null && list.size() != 0) {
                List<LotResourcesHistoryEntity> historyEntityList = new ArrayList<>();
                list.forEach(e -> {
                    e.setLastPrice(e.getPrice());
                    String models = e.getMaterial() + e.getModel();
                    switch (models) {
                        case "螺纹钢HRB400E φ12":
                            e.setPrice(subPrices.getPrice().add(subPrices.getModelDetailed().getHBR400E_L_12()));
                            break;
                        case "螺纹钢HRB400E φ14":
                            e.setPrice(subPrices.getPrice().add(subPrices.getModelDetailed().getHBR400E_L_14()));
                            break;
                        case "螺纹钢HRB400E φ16":
                            e.setPrice(subPrices.getPrice().add(subPrices.getModelDetailed().getHBR400E_L_16()));
                            break;
                        case "螺纹钢HRB400E φ18":
                            e.setPrice(subPrices.getPrice().add(subPrices.getModelDetailed().getHBR400E_L_18()));
                            break;
                        case "螺纹钢HRB400E φ20":
                            e.setPrice(subPrices.getPrice().add(subPrices.getModelDetailed().getHBR400E_L_20()));
                            break;
                        case "螺纹钢HRB400E φ22":
                            e.setPrice(subPrices.getPrice().add(subPrices.getModelDetailed().getHBR400E_L_22()));
                            break;
                        case "螺纹钢HRB400E φ25":
                            e.setPrice(subPrices.getPrice().add(subPrices.getModelDetailed().getHBR400E_L_25()));
                            break;
                        case "螺纹钢HRB400E φ28":
                            e.setPrice(subPrices.getPrice().add(subPrices.getModelDetailed().getHBR400E_L_28()));
                            break;
                        case "螺纹钢HRB400E φ32":
                            e.setPrice(subPrices.getPrice().add(subPrices.getModelDetailed().getHBR400E_L_32()));
                            break;
                        case "盘螺HRB400E φ6":
                            e.setPrice(subPrices.getPrice().add(subPrices.getModelDetailed().getHBR400E_P_6()));
                            break;
                        case "盘螺HRB400E φ8":
                            e.setPrice(subPrices.getPrice().add(subPrices.getModelDetailed().getHBR400E_P_8()));
                            break;
                        case "盘螺HRB400E φ10":
                            e.setPrice(subPrices.getPrice().add(subPrices.getModelDetailed().getHBR400E_P_10()));
                            break;
                        case "线材HRB400E φ6":
                            e.setPrice(subPrices.getPrice().add(subPrices.getModelDetailed().getHBR400E_X_6()));
                            break;
                        case "线材HRB400E φ8":
                            e.setPrice(subPrices.getPrice().add(subPrices.getModelDetailed().getHBR400E_X_8()));
                            break;
                        case "线材HRB400E φ10":
                            e.setPrice(subPrices.getPrice().add(subPrices.getModelDetailed().getHBR400E_X_10()));
                            break;
                    }
                    LotResourcesHistoryEntity lotResourcesHistoryEntity = new LotResourcesHistoryEntity();
                    lotResourcesHistoryEntity.setModel(e.getModel());
                    lotResourcesHistoryEntity.setBrand(e.getBrand());
                    lotResourcesHistoryEntity.setPrice(e.getPrice());
                    lotResourcesHistoryEntity.setResourcesId(e.getResourcesId());
                    lotResourcesHistoryEntity.setResourcesHistoryId(IDKeyUtil.getStringId());
                    lotResourcesHistoryEntity.setAddress(e.getAddress());
                    lotResourcesHistoryEntity.setSubordinateArea(e.getSubordinateArea());
                    lotResourcesHistoryEntity.setMaterial(e.getMaterial());
                    lotResourcesHistoryEntity.setYnFlag("Y");
                    historyEntityList.add(lotResourcesHistoryEntity);
                });
                //保存历史信息
                lotResourcesHistoryDao.saveAll(historyEntityList);
            }

            //更改首页信息
            List<LotResourcesManageEntity> lotResourcesManageEntityList = lotResourcesManageDao.findAllByAddressAndBrandAndSubordinateArea(subPrices.getAddress(), subPrices.getBrand(), subPrices.getSubordinateArea());
            lotResourcesManageEntityList.forEach(e -> {
                e.setPrice(subPrices.getPrice());
            });
            lotResourcesManageDao.saveAll(lotResourcesManageEntityList);

            LotResourcesSaveEntity lotResourcesSaveEntity = lotResourcesSaveDao.getOne("999999999");
            lotResourcesSaveEntity.setHbr400EL14(subPrices.getModelDetailed().getHBR400E_L_14());
            lotResourcesSaveEntity.setHbr400EL16(subPrices.getModelDetailed().getHBR400E_L_16());
            lotResourcesSaveEntity.setHbr400EL18(subPrices.getModelDetailed().getHBR400E_L_18());
            lotResourcesSaveEntity.setHbr400EL20(subPrices.getModelDetailed().getHBR400E_L_20());
            lotResourcesSaveEntity.setHbr400EL22(subPrices.getModelDetailed().getHBR400E_L_22());
            lotResourcesSaveEntity.setHrb400EL12(subPrices.getModelDetailed().getHBR400E_L_12());
            lotResourcesSaveEntity.setHbr400EL25(subPrices.getModelDetailed().getHBR400E_L_25());
            lotResourcesSaveEntity.setHbr400EL28(subPrices.getModelDetailed().getHBR400E_L_28());
            lotResourcesSaveEntity.setHbr400EL32(subPrices.getModelDetailed().getHBR400E_L_32());
            lotResourcesSaveEntity.setHbr400EP6(subPrices.getModelDetailed().getHBR400E_P_6());
            lotResourcesSaveEntity.setHbr400EP8(subPrices.getModelDetailed().getHBR400E_P_8());
            lotResourcesSaveEntity.setHbr400EP10(subPrices.getModelDetailed().getHBR400E_P_10());
            lotResourcesSaveEntity.setHbr400EX6(subPrices.getModelDetailed().getHBR400E_X_6());
            lotResourcesSaveEntity.setHbr400EX8(subPrices.getModelDetailed().getHBR400E_X_8());
            lotResourcesSaveEntity.setHbr400EX10(subPrices.getModelDetailed().getHBR400E_X_10());
            lotResourcesSaveDao.save(lotResourcesSaveEntity);
            lotResourcesDao.saveAll(list);
        } else if (subPrices.getStatus().equals("S")) {            //根据地区修改全部的价格(有点扯淡)
//            List<LotResourcesEntity> list = lotResourcesDao.findAllByAddress(subPrices.getAddress());

        } else if (subPrices.getStatus().equals("L")) {            //根据规格和地区修改全部的价格   //有点扯淡，暂无

        } else if (subPrices.getStatus().equals("M")) {            //根据地区和材质修改全部的价格   //有点扯淡，暂无

        } else if (subPrices.getStatus().equals("N")) {            //根据地区、材质、规格修改价格   //有点扯淡，暂无

        } else if (subPrices.getStatus().equals("P")) {            //根据地区、品牌、材质修改价格

        } else if (subPrices.getStatus().equals("W")) {            //根据地区、品牌、材质、规格修改价格
            List<LotResourcesEntity> list = lotResourcesDao.findAllByAddressAndBrandAndMaterialAndModelAndSubordinateArea(subPrices.getAddress(), subPrices.getBrand(), subPrices.getMaterial(), subPrices.getModel(), subPrices.getSubordinateArea());
            list.forEach(e -> {
                e.setPrice(subPrices.getPrice());
            });
            lotResourcesDao.saveAll(list);
        }
    }

    @Override
    public LotResourcesSaveEntity getSaveData() {
        return lotResourcesSaveDao.getOne("999999999");
    }

    /**
     * 根据提前排好序的List  先获取最小值有多少，有多少组就返回多少组排在前面的多少实体对象。。
     */
    public List<LotResourcesEntity> returnGenerateList(List<LotResourcesEntity> list) {
        List<LotResourcesEntity> returnList = new ArrayList<>();
        List<BigDecimal> bigList = list.stream().map(e -> e.getPrice()).collect(Collectors.toList());
        if (bigList.size() != 0) {
            int count = Collections.frequency(bigList, bigList.get(0));
            for (int i = 0; i < count; i++) {
                returnList.add(list.get(i));
            }
        }
        return returnList;
    }


    //根据相应的字段去重
    public List<LotResourcesHistoryEntity> removeDuplicateOutputField(List<LotResourcesHistoryEntity> list) {
        Set<LotResourcesHistoryEntity> set = new TreeSet<>(new Comparator<LotResourcesHistoryEntity>() {
            @Override
            public int compare(LotResourcesHistoryEntity old, LotResourcesHistoryEntity neo) {
                int compareToResult = 1;//==0表示重复
                if (old.getModel().equals(neo.getModel()) && old.getMaterial().equals(neo.getMaterial())) {
                    compareToResult = 0;
                }
                return compareToResult;
            }
        });
        set.addAll(list);
        return new ArrayList<>(set);
    }

}
