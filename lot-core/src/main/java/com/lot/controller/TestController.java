package com.lot.controller;

import com.lot.dao.LotAuthorityDao;
import com.lot.dao.LotCodeDao;
import com.lot.dao.LotResourcesDao;
import com.lot.dao.LotResourcesManageDao;
import com.lot.entity.LotCodeEntity;
import com.lot.entity.LotResourcesEntity;
import com.lot.entity.LotResourcesManageEntity;
import com.lot.mapper.LotCodeMapper;
import com.lot.service.*;
import com.lot.util.IDKeyUtil;
import com.lot.util.JwtUtil;
import com.lot.util.Msg;
import com.lot.util.RedisUtil;
import com.lot.vo.lotUser.LotUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;


/**
 * @Author zel
 * @Date 2020-05-21
 */
@Api(value = "demo测试")
@RestController
@RequestMapping("/demo")
public class TestController {
    @Autowired
    TestInterface testInterface;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private LotUserService lotUserService;
    @Autowired
    private LotRoleService lotRoleService;
    @Autowired
    private LotAuthorityService lotAuthorityService;
    @Autowired
    private LotAuthorityDao lotAuthorityDao;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private LotResourcesManageDao lotResourcesManageDao;
    @Autowired
    private LotResourcesDao lotResourcesDao;

    @Autowired
    private LotCodeDao lotCodeDao;
    @Autowired
    private LotCodeMapper lotCodeMapper;

    @GetMapping("test")
    public String test() {
        return "hello Springboot!";
    }

    @GetMapping("test2")
    public String test2() {
        return testInterface.demoTest();
    }

    @GetMapping("setstring")
    public Msg tests(@RequestParam String key, @RequestParam String velue) {
        redisUtil.set(key, velue, 60);
        return Msg.success().add(key, velue);
    }

    @GetMapping("getstring")
    public Msg tests(@RequestParam String key) {
        return Msg.success().add(key, redisUtil.get(key));
    }

    @PostMapping("setobject")
    public Msg testSet(@RequestBody LotUser user) {
        redisUtil.set(user.getUserName(), user, 60);
        return Msg.success().add(user.getUserName(), user);
    }

    @GetMapping("getobject")
    public Msg testGet(@RequestParam String key) {
        return Msg.success().add(key, redisUtil.get(key));
    }

//    @PostMapping("userlist")
//    @ApiOperation("获取用户列表")
//    public Msg test4() {
//        return Msg.success().add("userList", lotUserService.findList());
//    }

//    @PostMapping("rolelist")
//    @ApiOperation("获取角色列表")
//    public Msg test9() {
//        return Msg.success().add("RoleList", lotRoleService.findList());
//    }

    @PostMapping("roleslist")
    @ApiOperation("根据当前用户获取角色列表")
    public Msg tests() {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        return Msg.success().add("RoleList", simpleAuthorizationInfo.getRoles());
    }

    @PostMapping("authorityslist")
    @ApiOperation("根据当前用户获取权限列表")
    public Msg testt() {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        return Msg.success().add("AuthorityList", simpleAuthorizationInfo.getStringPermissions());
    }


//    @PostMapping("authoritylist")
//    @ApiOperation("获取权限列表")
//    @RequiresPermissions({"public"})
//    public Msg test8() {
//        return Msg.success().add("AuthorityList", lotAuthorityService.findList());
//    }

    @PostMapping("getrole")
    @ApiOperation("获取单个用户的角色")
    public Msg test7(@RequestParam String username) {
        return Msg.success().add("AuthorityList", lotRoleService.getRoleByAccount(username));
    }

    @PostMapping("getauthority")
    @ApiOperation("获取单个用户的权限")
    public Msg test6(@RequestParam String username) {
        return Msg.success().add("AuthorityList", lotAuthorityService.getAuthority(username));
    }

    @PostMapping("isauthorityurl")
    @ApiOperation("权限里面是否有这个Url地址")
    public Msg isAuthorityUrl(@RequestParam String url) {
        return Msg.success().add("UrlList", lotAuthorityService.isAuthorityUrl(url));
    }

    //    @PostMapping("/getname")
//    public Msg getName(String token) {
//        return Msg.success().add("token", JwtUtil.createToken("dsafkjkj_dsajk"));
//    }
//
//
//    @PostMapping("/y")
//    public Msg y(String token) {
//        return Msg.success().add("是否是这样的：", JwtUtil.checkToken(token));
//    }
//
    @PostMapping("/s")
    public Msg ys(String userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        return Msg.success().add("是否是这样的：", JwtUtil.createToken(userId, "weixin", map));
    }

    @PostMapping("/666")
    public Msg s(String token) {
        return Msg.success().add("是否是这样的：", JwtUtil.parseToken(token));
    }


    @PostMapping("/wangbadan")
    public void wangbadan(String address) {
        List<LotResourcesManageEntity> list = new ArrayList<>();

//        Map<String, BigDecimal> strList = new LinkedHashMap<>();
//        strList.put("武钢汉钢", BigDecimal.valueOf(3570));
//        strList.put("宝武襄钢", BigDecimal.valueOf(3560));
//        strList.put("鄂钢", BigDecimal.valueOf(3600));
//        strList.put("亚新", BigDecimal.valueOf(3590));
//        strList.put("陕西龙钢", BigDecimal.valueOf(3560));
//        strList.put("十堰榕峰", BigDecimal.valueOf(3510));
//        strList.put("荆钢", BigDecimal.valueOf(3540));
//        strList.put("立恒", null);
//        strList.put("高义", null);
//        strList.put("建龙", null);
//        strList.put("建邦", null);

        Map<String, BigDecimal> strList = new LinkedHashMap<>();
//        strList.put("宝武鄂钢", BigDecimal.valueOf(3570));
//        strList.put("武钢汉钢", BigDecimal.valueOf(3540));
//        strList.put("冷钢", BigDecimal.valueOf(3550));
//        strList.put("信钢", BigDecimal.valueOf(3540));
//        strList.put("金罡", BigDecimal.valueOf(3520));
//        strList.put("萍钢", BigDecimal.valueOf(3550));
//        strList.put("富鑫", BigDecimal.valueOf(3520));
//        strList.put("龙钢", BigDecimal.valueOf(3550));
//        strList.put("新兴铸管", BigDecimal.valueOf(3550));
//        strList.put("马长江", BigDecimal.valueOf(3550));
//        strList.put("达钢", BigDecimal.valueOf(3550));
//        strList.put("彭钢", BigDecimal.valueOf(3520));
//        strList.put("桂鑫", BigDecimal.valueOf(3520));
//        strList.put("高义", BigDecimal.valueOf(3650));
//        strList.put("荆钢", BigDecimal.valueOf(3520));
//        strList.put("扬钢", BigDecimal.valueOf(3500));
//        strList.put("十堰榕峰", BigDecimal.valueOf(3520));
//        strList.put("中新", BigDecimal.valueOf(3520));
//        strList.put("贵航", BigDecimal.valueOf(3520));
//        strList.put("鄂州鸿泰", BigDecimal.valueOf(3520));
//        strList.put("亚新", BigDecimal.valueOf(3650));
//        strList.put("敬业", BigDecimal.valueOf(3550));
//        strList.put("铜陵旋力", BigDecimal.valueOf(3520));
//        strList.put("湖北立晋", BigDecimal.valueOf(3520));
//        strList.put("十堰福堰", BigDecimal.valueOf(3520));

        //宜昌
//        strList.put("武钢汉钢",BigDecimal.valueOf(0));
//        strList.put("鄂钢",BigDecimal.valueOf(0));
//        strList.put("金罡",BigDecimal.valueOf(0));
//        strList.put("萍钢",BigDecimal.valueOf(0));
//        strList.put("信钢",BigDecimal.valueOf(0));
//        strList.put("冷钢",BigDecimal.valueOf(0));
//        strList.put("敬业",BigDecimal.valueOf(0));
//        strList.put("达钢",BigDecimal.valueOf(0));
//        strList.put("新兴铸管",BigDecimal.valueOf(0));
//        strList.put("中新",BigDecimal.valueOf(0));

//        //恩施
//        strList.put("武钢汉钢",BigDecimal.valueOf(0));
//        strList.put("鄂钢",BigDecimal.valueOf(0));
//        strList.put("冷钢",BigDecimal.valueOf(0));
//        strList.put("陕西龙钢",BigDecimal.valueOf(0));
//        strList.put("萍钢",BigDecimal.valueOf(0));

        //荆州
//        strList.put("武钢汉钢",BigDecimal.valueOf(0));
//        strList.put("鄂钢",BigDecimal.valueOf(0));
//        strList.put("陕西龙钢",BigDecimal.valueOf(0));
//        strList.put("萍钢",BigDecimal.valueOf(0));
//        strList.put("十堰福堰",BigDecimal.valueOf(0));

        //咸宁
        strList.put("宝武鄂钢",BigDecimal.valueOf(0));
        strList.put("武钢汉钢",BigDecimal.valueOf(0));
        strList.put("冷钢",BigDecimal.valueOf(0));
        strList.put("信钢",BigDecimal.valueOf(0));
        strList.put("金罡",BigDecimal.valueOf(0));
        strList.put("萍钢",BigDecimal.valueOf(0));
        strList.put("富鑫",BigDecimal.valueOf(0));
        strList.put("龙钢",BigDecimal.valueOf(0));
        strList.put("马长江",BigDecimal.valueOf(0));
        strList.put("达钢",BigDecimal.valueOf(0));

//        strList.forEach(e -> {
//            LotResourcesManageEntity lotResourcesManageEntity = new LotResourcesManageEntity();
//            lotResourcesManageEntity.setResourcesManageId(IDKeyUtil.getStringId());
//            lotResourcesManageEntity.setAddress(address);
//            lotResourcesManageEntity.setBrand(e);
//            lotResourcesManageEntity.setMaterial("螺纹钢");
//            lotResourcesManageEntity.setModel("HRB400E φ18-22");
//            lotResourcesManageEntity.setYnFlag("Y");
//            lotResourcesManageEntity.setPrice(BigDecimal.valueOf(0));
//
//            list.add(lotResourcesManageEntity);
//        });
        List<String> addressTwoList = new ArrayList<>();
//        addressTwoList.add("襄阳市");
//        addressTwoList.add("枣阳市");
//        addressTwoList.add("南漳县");
//        addressTwoList.add("宜城市");
//        addressTwoList.add("谷城县");
//        addressTwoList.add("老河口市");

        //宜昌
//        addressTwoList.add("宜昌市");
//        addressTwoList.add("宜都市");
//        addressTwoList.add("枝江市");
//        addressTwoList.add("夷陵区");
//        addressTwoList.add("长阳土家族自治县");
//        addressTwoList.add("当阳市");
//        addressTwoList.add("远安县");

        //恩施
//        addressTwoList.add("恩施土家族苗族自治州");

        //荆州
//        addressTwoList.add("荆州市");
//        addressTwoList.add("监利县");
//        addressTwoList.add("公安县");
//        addressTwoList.add("石首市");
//        addressTwoList.add("松滋市");
//        addressTwoList.add("松滋市");
//        addressTwoList.add("江陵县");

        //咸宁市
//        addressTwoList.add("咸宁市");
//        addressTwoList.add("嘉鱼县");
//        addressTwoList.add("赤壁市");
//        addressTwoList.add("崇阳县");
//        addressTwoList.add("通山县");
//        addressTwoList.add("通城县");

//        //鄂州
//        addressTwoList.add("鄂州市");
//        addressTwoList.add("华容区葛店");

        //孝感市
//        addressTwoList.add("孝感市");
//        addressTwoList.add("安陆市");
//        addressTwoList.add("应城市");
//        addressTwoList.add("云梦县");
//        addressTwoList.add("孝昌县");
//        addressTwoList.add("大悟县");

        //黄石市
//        addressTwoList.add("黄石市");
//        addressTwoList.add("大冶市");
//        addressTwoList.add("阳新县");

        //黄冈市
        addressTwoList.add("黄冈市");
        addressTwoList.add("团风县");
        addressTwoList.add("蕲春县");
        addressTwoList.add("浠水县");
        addressTwoList.add("黄梅县");
        addressTwoList.add("红安县");
        addressTwoList.add("武穴市");
        addressTwoList.add("罗田县");
        addressTwoList.add("麻城市");

        //潜江
//        addressTwoList.add("潜江市");

        //仙桃
//        addressTwoList.add("仙桃市");

        //天门
//        addressTwoList.add("天门市");

//        随州
//        addressTwoList.add("随州市");
//        addressTwoList.add("随县");
//        addressTwoList.add("广水市");

        //荆门

//        addressTwoList.add("荆门市");
//        addressTwoList.add("京山市");
//        addressTwoList.add("钟祥市");
//        addressTwoList.add("沙洋县");

        //十堰
//        addressTwoList.add("十堰市");
//        addressTwoList.add("丹江口市");

//        addressTwoList.add("汉口区");
//        addressTwoList.add("江夏区");
//        addressTwoList.add("洪山区");
//        addressTwoList.add("黄陂区");
//        addressTwoList.add("青山区");
//        addressTwoList.add("汉南区");
//        addressTwoList.add("汉阳区");
//        addressTwoList.add("硚口区");
//        addressTwoList.add("东西湖区");
//        addressTwoList.add("蔡甸区");
//        addressTwoList.add("新洲区");
//        addressTwoList.add("汉川区");
//        addressTwoList.add("白沙洲区");
//        addressTwoList.add("烽火");
//        addressTwoList.add("光谷");
//        addressTwoList.add("阳逻");
//        addressTwoList.add("左岭");
//        addressTwoList.add("盘龙城");


        for (Map.Entry<String, BigDecimal> entry : strList.entrySet()) {
            addressTwoList.forEach(ey -> {
                LotResourcesManageEntity lotResourcesManageEntity = new LotResourcesManageEntity();
                lotResourcesManageEntity.setResourcesManageId(IDKeyUtil.getStringId());
                lotResourcesManageEntity.setAddress(address);
                lotResourcesManageEntity.setSubordinateArea(ey);
                lotResourcesManageEntity.setBrand(entry.getKey());
                lotResourcesManageEntity.setMaterial("螺纹钢");
                lotResourcesManageEntity.setModel("HRB400E φ18-22");
                lotResourcesManageEntity.setYnFlag("Y");
                if (entry.getValue() != null) {
                    lotResourcesManageEntity.setPrice(entry.getValue());
                }
                list.add(lotResourcesManageEntity);
                System.out.println("key:" + entry.getKey() + "   value:" + entry.getValue());
            });
        }


        lotResourcesManageDao.saveAll(list);

    }

    @PostMapping("/wangbadanTowo")
    public void wangbadanTowo(String address, BigDecimal price) {
        List<LotResourcesEntity> list = new ArrayList<>();

//        Map<String, BigDecimal> strList = new LinkedHashMap<>();
//        strList.put("武钢汉钢", BigDecimal.valueOf(3570));
//        strList.put("宝武襄钢", BigDecimal.valueOf(3560));
//        strList.put("鄂钢", BigDecimal.valueOf(3600));
//        strList.put("亚新", BigDecimal.valueOf(3590));
//        strList.put("陕西龙钢", BigDecimal.valueOf(3560));
//        strList.put("十堰榕峰", BigDecimal.valueOf(3510));
//        strList.put("荆钢", BigDecimal.valueOf(3540));
//        strList.put("立恒", null);
//        strList.put("高义", null);
//        strList.put("建龙", null);
//        strList.put("建邦", null);

        Map<String, BigDecimal> strList = new LinkedHashMap<>();
//        strList.put("宝武鄂钢", BigDecimal.valueOf(3570));
//        strList.put("武钢汉钢", BigDecimal.valueOf(3540));
//        strList.put("冷钢", BigDecimal.valueOf(3550));
//        strList.put("信钢", BigDecimal.valueOf(3540));
//        strList.put("金罡", BigDecimal.valueOf(3520));
//        strList.put("萍钢", BigDecimal.valueOf(3550));
//        strList.put("富鑫", BigDecimal.valueOf(3520));
//        strList.put("龙钢", BigDecimal.valueOf(3550));
//        strList.put("新兴铸管", BigDecimal.valueOf(3550));
//        strList.put("马长江", BigDecimal.valueOf(3550));
//        strList.put("达钢", BigDecimal.valueOf(3550));
//        strList.put("彭钢", BigDecimal.valueOf(3520));
//        strList.put("桂鑫", BigDecimal.valueOf(3520));
//        strList.put("高义", BigDecimal.valueOf(3650));
//        strList.put("荆钢", BigDecimal.valueOf(3520));
//        strList.put("扬钢", BigDecimal.valueOf(3500));
//        strList.put("十堰榕峰", BigDecimal.valueOf(3520));
//        strList.put("中新", BigDecimal.valueOf(3520));
//        strList.put("贵航", BigDecimal.valueOf(3520));
//        strList.put("鄂州鸿泰", BigDecimal.valueOf(3520));
//        strList.put("亚新", BigDecimal.valueOf(3650));
//        strList.put("敬业", BigDecimal.valueOf(3550));
//        strList.put("铜陵旋力", BigDecimal.valueOf(3520));
//        strList.put("湖北立晋", BigDecimal.valueOf(3520));
//        strList.put("十堰福堰", BigDecimal.valueOf(3520));

        //宜昌
//        strList.put("武钢汉钢",BigDecimal.valueOf(0));
//        strList.put("鄂钢",BigDecimal.valueOf(0));
//        strList.put("金罡",BigDecimal.valueOf(0));
//        strList.put("萍钢",BigDecimal.valueOf(0));
//        strList.put("信钢",BigDecimal.valueOf(0));
//        strList.put("冷钢",BigDecimal.valueOf(0));
//        strList.put("敬业",BigDecimal.valueOf(0));
//        strList.put("达钢",BigDecimal.valueOf(0));
//        strList.put("新兴铸管",BigDecimal.valueOf(0));
//        strList.put("中新",BigDecimal.valueOf(0));

        //恩施
//        strList.put("武钢汉钢",BigDecimal.valueOf(0));
//        strList.put("鄂钢",BigDecimal.valueOf(0));
//        strList.put("冷钢",BigDecimal.valueOf(0));
//        strList.put("陕西龙钢",BigDecimal.valueOf(0));
//        strList.put("萍钢",BigDecimal.valueOf(0));

        //荆州
//        strList.put("武钢汉钢",BigDecimal.valueOf(0));
//        strList.put("鄂钢",BigDecimal.valueOf(0));
//        strList.put("陕西龙钢",BigDecimal.valueOf(0));
//        strList.put("萍钢",BigDecimal.valueOf(0));
//        strList.put("十堰福堰",BigDecimal.valueOf(0));

        //咸宁
        strList.put("宝武鄂钢",BigDecimal.valueOf(0));
        strList.put("武钢汉钢",BigDecimal.valueOf(0));
        strList.put("冷钢",BigDecimal.valueOf(0));
        strList.put("信钢",BigDecimal.valueOf(0));
        strList.put("金罡",BigDecimal.valueOf(0));
        strList.put("萍钢",BigDecimal.valueOf(0));
        strList.put("富鑫",BigDecimal.valueOf(0));
        strList.put("龙钢",BigDecimal.valueOf(0));
        strList.put("马长江",BigDecimal.valueOf(0));
        strList.put("达钢",BigDecimal.valueOf(0));

        List<String> strTwoList = new ArrayList<>();
        strTwoList.add("HRB400E φ12");
        strTwoList.add("HRB400E φ14");
        strTwoList.add("HRB400E φ16");
        strTwoList.add("HRB400E φ18");
        strTwoList.add("HRB400E φ20");
        strTwoList.add("HRB400E φ22");
        strTwoList.add("HRB400E φ25");
        strTwoList.add("HRB400E φ28");
        strTwoList.add("HRB400E φ32");
        strTwoList.add("HRB400E φ6");
        strTwoList.add("HRB400E φ8");
        strTwoList.add("HRB400E φ10");


        List<String> strTwoList2 = new ArrayList<>();
        strTwoList2.add("HRB400E φ6");
        strTwoList2.add("HRB400E φ8");
        strTwoList2.add("HRB400E φ10");

        List<String> addressTwoList = new ArrayList<>();
//        addressTwoList.add("襄阳市");
//        addressTwoList.add("枣阳");
//        addressTwoList.add("南漳");
//        addressTwoList.add("宜城");
//        addressTwoList.add("谷城");
//        addressTwoList.add("老河口");

//        addressTwoList.add("武汉市");

        //宜昌
//        addressTwoList.add("宜昌市");
//        addressTwoList.add("宜都市");
//        addressTwoList.add("枝江市");
//        addressTwoList.add("夷陵区");
//        addressTwoList.add("长阳土家族自治县");
//        addressTwoList.add("当阳市");
//        addressTwoList.add("远安县");

        //恩施
//        addressTwoList.add("恩施土家族苗族自治州");

        //荆州
//        addressTwoList.add("荆州市");
//        addressTwoList.add("监利县");
//        addressTwoList.add("公安县");
//        addressTwoList.add("石首市");
//        addressTwoList.add("松滋市");
//        addressTwoList.add("松滋市");
//        addressTwoList.add("江陵县");


//        for (Map.Entry<String, BigDecimal> entry : strList.entrySet()) {
//            strTwoList.forEach(es -> {
//                LotResourcesEntity lotResourcesEntity = new LotResourcesEntity();
//                lotResourcesEntity.setResourcesId(IDKeyUtil.getStringId());
//                switch (es) {
//                    case "HRB400E φ12":
//                        lotResourcesEntity.setPrice(entry.getValue().add(BigDecimal.valueOf(130)));
//                        break;
//                    case "HRB400E φ14":
//                        lotResourcesEntity.setPrice(entry.getValue().add(BigDecimal.valueOf(70)));
//                        break;
//                    case "HRB400E φ16":
//                        lotResourcesEntity.setPrice(entry.getValue().add(BigDecimal.valueOf(20)));
//                        break;
//                    case "HRB400E φ18":
//                        lotResourcesEntity.setPrice(entry.getValue());
//                        break;
//                    case "HRB400E φ20":
//                        lotResourcesEntity.setPrice(entry.getValue());
//                        break;
//                    case "HRB400E φ22":
//                        lotResourcesEntity.setPrice(entry.getValue());
//                        break;
//                    case "HRB400E φ25":
//                        lotResourcesEntity.setPrice(entry.getValue().add(BigDecimal.valueOf(30)));
//                        break;
//                    case "HRB400E φ28":
//                        lotResourcesEntity.setPrice(entry.getValue().add(BigDecimal.valueOf(80)));
//                        break;
//                    case "HRB400E φ32":
//                        lotResourcesEntity.setPrice(entry.getValue().add(BigDecimal.valueOf(100)));
//                        break;
//                }
//
//                lotResourcesEntity.setYnFlag("Y");
//                lotResourcesEntity.setAddress(address);
//                lotResourcesEntity.setBrand(entry.getKey());
//                if (es.equals("HRB400E φ6") || es.equals("HRB400E φ8") || es.equals("HRB400E φ10")) {
//                    lotResourcesEntity.setMaterial("盘螺");
//                } else {
//                    lotResourcesEntity.setMaterial("螺纹钢");
//                }
//                lotResourcesEntity.setModel(es);
//                list.add(lotResourcesEntity);
//            });

//        //咸宁市
//        addressTwoList.add("咸宁市");
//        addressTwoList.add("嘉鱼县");
//        addressTwoList.add("赤壁市");
//        addressTwoList.add("崇阳县");
//        addressTwoList.add("通山县");
//        addressTwoList.add("通城县");

        //鄂州
//        addressTwoList.add("鄂州市");
//        addressTwoList.add("华容区葛店");

        //孝感市
//        addressTwoList.add("孝感市");
//        addressTwoList.add("安陆市");
//        addressTwoList.add("应城市");
//        addressTwoList.add("云梦县");
//        addressTwoList.add("孝昌县");
//        addressTwoList.add("大悟县");

        //黄石市
//        addressTwoList.add("黄石市");
//        addressTwoList.add("大冶市");
//        addressTwoList.add("阳新县");
        //黄冈市
        addressTwoList.add("黄冈市");
        addressTwoList.add("团风县");
        addressTwoList.add("蕲春县");
        addressTwoList.add("浠水县");
        addressTwoList.add("黄梅县");
        addressTwoList.add("红安县");
        addressTwoList.add("武穴市");
        addressTwoList.add("罗田县");
        addressTwoList.add("麻城市");

        //潜江
//        addressTwoList.add("潜江市");

        //仙桃
//        addressTwoList.add("仙桃市");

        //天门
//        addressTwoList.add("天门市");

        //        随州
//        addressTwoList.add("随州市");
//        addressTwoList.add("随县");
//        addressTwoList.add("广水市");

        //荆门市
//        addressTwoList.add("荆门市");
//        addressTwoList.add("京山市");
//        addressTwoList.add("钟祥市");
//        addressTwoList.add("沙洋县");

        //十堰
//        addressTwoList.add("十堰市");
//        addressTwoList.add("丹江口市");

        for (Map.Entry<String, BigDecimal> entry : strList.entrySet()) {
            addressTwoList.forEach(ey -> {
                strTwoList.forEach(es -> {
                    LotResourcesEntity lotResourcesEntity = new LotResourcesEntity();
                    lotResourcesEntity.setResourcesId(IDKeyUtil.getStringId());
                    lotResourcesEntity.setYnFlag("Y");
                    lotResourcesEntity.setPrice(BigDecimal.valueOf(0));
                    lotResourcesEntity.setAddress(address);
                    lotResourcesEntity.setSubordinateArea(ey);
                    lotResourcesEntity.setBrand(entry.getKey());
                    if (es.equals("HRB400E φ6") || es.equals("HRB400E φ8") || es.equals("HRB400E φ10")) {
                        lotResourcesEntity.setMaterial("盘螺");
                    } else {
                        lotResourcesEntity.setMaterial("螺纹钢");
                    }
                    lotResourcesEntity.setModel(es);
                    list.add(lotResourcesEntity);
                });
                strTwoList2.forEach(ess -> {
                    LotResourcesEntity lotResourcesEntity = new LotResourcesEntity();
                    lotResourcesEntity.setResourcesId(IDKeyUtil.getStringId());
                    lotResourcesEntity.setPrice(BigDecimal.valueOf(0));
                    lotResourcesEntity.setYnFlag("Y");
                    lotResourcesEntity.setAddress(address);
                    lotResourcesEntity.setSubordinateArea(ey);
                    lotResourcesEntity.setBrand(entry.getKey());
                    lotResourcesEntity.setMaterial("线材");
                    lotResourcesEntity.setModel(ess);
                    list.add(lotResourcesEntity);
                });
            });
        }

        lotResourcesDao.saveAll(list);
    }

    @PostMapping("/wangbadanTowossssss")
    public void wangbadanTowos(String address) {
        List<LotResourcesEntity> list = new ArrayList<>();
        Map<String, BigDecimal> strList = new LinkedHashMap<>();
        strList.put("武钢汉钢", BigDecimal.valueOf(3630));
//        strList.put("汉钢",BigDecimal.valueOf(3630));
        strList.put("鄂钢", BigDecimal.valueOf(3690));
        strList.put("达钢", BigDecimal.valueOf(3590));
        strList.put("萍钢", BigDecimal.valueOf(3610));
        strList.put("信钢", BigDecimal.valueOf(3660));
        strList.put("敬业", BigDecimal.valueOf(3580));
        strList.put("冷钢", BigDecimal.valueOf(3620));
        strList.put("彭钢", BigDecimal.valueOf(3570));
        strList.put("中新", BigDecimal.valueOf(3560));
        strList.put("扬钢", BigDecimal.valueOf(3560));
        strList.put("桂鑫", BigDecimal.valueOf(3560));
        strList.put("富鑫", BigDecimal.valueOf(3580));
//        strList.add("陕西龙钢");
        strList.put("新兴铸管", BigDecimal.valueOf(3620));
        strList.put("马长江", BigDecimal.valueOf(3620));

        List<String> strTwoList = new ArrayList<>();
        strTwoList.add("HRB400E φ12");
        strTwoList.add("HRB400E φ14");
        strTwoList.add("HRB400E φ16");
        strTwoList.add("HRB400E φ18");
        strTwoList.add("HRB400E φ20");
        strTwoList.add("HRB400E φ22");
        strTwoList.add("HRB400E φ25");
        strTwoList.add("HRB400E φ28");
        strTwoList.add("HRB400E φ32");
        strTwoList.add("HRB400E φ6");
        strTwoList.add("HRB400E φ8");
        strTwoList.add("HRB400E φ10");


        List<String> strTwoList2 = new ArrayList<>();
        strTwoList2.add("HRB400E φ6");
        strTwoList2.add("HRB400E φ8");
        strTwoList2.add("HRB400E φ10");

        List<String> addressTwoList = new ArrayList<>();
        addressTwoList.add("襄阳");
        addressTwoList.add("枣阳");
        addressTwoList.add("南漳");
        addressTwoList.add("宜城");
        addressTwoList.add("谷城");
        addressTwoList.add("老河口");


        for (Map.Entry<String, BigDecimal> entry : strList.entrySet()) {
            addressTwoList.forEach(ey -> {
                strTwoList.forEach(es -> {
                    LotResourcesEntity lotResourcesEntity = new LotResourcesEntity();
                    lotResourcesEntity.setResourcesId(IDKeyUtil.getStringId());
                    switch (es) {
                        case "HRB400E φ12":
                            lotResourcesEntity.setPrice(entry.getValue().add(BigDecimal.valueOf(130)));
                            break;
                        case "HRB400E φ14":
                            lotResourcesEntity.setPrice(entry.getValue().add(BigDecimal.valueOf(70)));
                            break;
                        case "HRB400E φ16":
                            lotResourcesEntity.setPrice(entry.getValue().add(BigDecimal.valueOf(20)));
                            break;
                        case "HRB400E φ18":
                            lotResourcesEntity.setPrice(entry.getValue());
                            break;
                        case "HRB400E φ20":
                            lotResourcesEntity.setPrice(entry.getValue());
                            break;
                        case "HRB400E φ22":
                            lotResourcesEntity.setPrice(entry.getValue());
                            break;
                        case "HRB400E φ25":
                            lotResourcesEntity.setPrice(entry.getValue().add(BigDecimal.valueOf(30)));
                            break;
                        case "HRB400E φ28":
                            lotResourcesEntity.setPrice(entry.getValue().add(BigDecimal.valueOf(80)));
                            break;
                        case "HRB400E φ32":
                            lotResourcesEntity.setPrice(entry.getValue().add(BigDecimal.valueOf(100)));
                            break;
                    }

                    lotResourcesEntity.setYnFlag("Y");
                    lotResourcesEntity.setAddress(address);
                    lotResourcesEntity.setSubordinateArea(ey);
                    lotResourcesEntity.setBrand(entry.getKey());
                    if (es.equals("HRB400E φ6") || es.equals("HRB400E φ8") || es.equals("HRB400E φ10")) {
                        lotResourcesEntity.setMaterial("盘螺");
                    } else {
                        lotResourcesEntity.setMaterial("螺纹钢");
                    }
                    lotResourcesEntity.setModel(es);
                    list.add(lotResourcesEntity);
                });
                strTwoList2.forEach(ess -> {
                    LotResourcesEntity lotResourcesEntity = new LotResourcesEntity();
                    lotResourcesEntity.setResourcesId(IDKeyUtil.getStringId());
                    lotResourcesEntity.setPrice(BigDecimal.valueOf(0));
                    lotResourcesEntity.setYnFlag("Y");
                    lotResourcesEntity.setAddress(address);
                    lotResourcesEntity.setSubordinateArea(ey);
                    lotResourcesEntity.setBrand(entry.getKey());
                    lotResourcesEntity.setMaterial("线材");
                    lotResourcesEntity.setModel(ess);
                    list.add(lotResourcesEntity);
                });
            });
        }

        lotResourcesDao.saveAll(list);
    }

    @PostMapping("/createCode")
    public void createCode() {
        List<String> strList = new ArrayList<>();
//        address.add("武汉市");
//        address.add("黄石市");
//        address.add("十堰市");
//        address.add("宜昌市");
//        address.add("襄阳市");
//        address.add("鄂州市");
//        address.add("荆门市");
//        address.add("孝感市");
//        address.add("荆州市");
//        address.add("黄冈市");
//        address.add("咸宁市");
//        address.add("随州市");
//        address.add("恩施土家族苗族自治州");
//        address.add("螺纹钢");
//        address.add("盘螺");
//        address.add("线材");
//        strList.add("φ6");
//        strList.add("φ8");
//        strList.add("φ10");
//        address.add("φ12");
//        address.add("φ14");
//        address.add("φ16");
//        address.add("φ18");
//        address.add("φ20");
//        address.add("φ22");
//        address.add("φ25");
//        address.add("φ28");
//        address.add("φ32");
        strList.add("武钢汉钢");
        strList.add("宝武襄钢");
        strList.add("鄂钢");
        strList.add("亚新");
        strList.add("陕西龙钢");
        strList.add("十堰榕峰");
        strList.add("荆钢");
        strList.add("立恒");
        strList.add("高义");
        strList.add("建龙");
        strList.add("建邦");
//        strList.add("宝武鄂钢");
//        strList.add("武钢汉钢");
//        strList.add("冷钢");
//        strList.add("信钢");
//        strList.add("金罡");
//        strList.add("萍钢");
//        strList.add("富鑫");
//        strList.add("龙钢");
//        strList.add("新兴铸管");
//        strList.add("马长江");
//        strList.add("达钢");
//        strList.add("彭钢");
//        strList.add("桂鑫");
//        strList.add("高义");
//        strList.add("荆钢");
//        strList.add("扬钢");
//        strList.add("十堰榕峰");
//        strList.add("中新");
//        strList.add("贵航");
//        strList.add("鄂州鸿泰");
//        strList.add("亚新");
//        strList.add("敬业");
//        strList.add("铜陵旋力");
//        strList.add("湖北立晋");
//        strList.add("十堰福堰");


        List<LotCodeEntity> lotCodeEntityList = new ArrayList<>();
        for (int j = 0; j < strList.size(); j++) {
            LotCodeEntity lotCodeEntity = new LotCodeEntity();
            lotCodeEntity.setCode("ADDRESS_XY" + j);
            lotCodeEntity.setCodeBelong("ADDRESS_XY");
            lotCodeEntity.setCodeDesc(strList.get(j));
            lotCodeEntity.setLotCodeId(IDKeyUtil.getStringId());
            lotCodeEntity.setShowOrder(j + 1);
            lotCodeEntity.setParentCode("襄阳市");
            lotCodeEntity.setYnFlag("Y");
            lotCodeEntityList.add(lotCodeEntity);
        }
        lotCodeDao.saveAll(lotCodeEntityList);
    }

//    @PostMapping("/inputDataToSources")
//    @ApiOperation("输入数据到资源中")
//    public void inputDataToSources(@RequestParam String address, @RequestParam String brand, @RequestParam BigDecimal price) {
//        List<LotResourcesEntity> list = new ArrayList<>();
//    }

    @PostMapping("/inputDataToSources")
    @ApiOperation("输入数据到资源中")
    public void inputDataToSources() {
        Set<String> list = new HashSet<>();
        List<LotCodeEntity> lotCodeEntityList = lotCodeMapper.getCode("ADDRESS_WH");
        List<LotCodeEntity> lotCodeEntityList2 = lotCodeMapper.getCode("ADDRESS_XY");
        lotCodeEntityList.forEach(e -> {
            list.add(e.getCodeDesc());
        });
        lotCodeEntityList2.forEach(e -> {
            list.add(e.getCodeDesc());
        });
        List<String> lists = new ArrayList<>(list);
        List<LotCodeEntity> lotCodeEntityLists = new ArrayList<>();
        for (int j = 0; j < lists.size(); j++) {
            LotCodeEntity lotCodeEntity = new LotCodeEntity();
            lotCodeEntity.setCode("BRAND" + j);
            lotCodeEntity.setCodeBelong("BRAND");
            lotCodeEntity.setCodeDesc(lists.get(j));
            lotCodeEntity.setLotCodeId(IDKeyUtil.getStringId());
            lotCodeEntity.setShowOrder(j + 1);
            lotCodeEntity.setYnFlag("Y");
            lotCodeEntityLists.add(lotCodeEntity);
        }
        lotCodeDao.saveAll(lotCodeEntityLists);
    }

}

