package com.lot.serviceImpl;

import com.lot.Base.PutMap;
import com.lot.Base.ReflectUtil;
import com.lot.dao.LotRoleDao;
import com.lot.dao.LotUserDao;
import com.lot.dao.LotUserRoleDao;
import com.lot.entity.LotFileEntity;
import com.lot.entity.LotRoleEntity;
import com.lot.entity.LotUserEntity;
import com.lot.entity.LotUserRoleEntity;
import com.lot.mapper.LotUserMapper;
import com.lot.service.LotUserService;
import com.lot.util.DateUtils;
import com.lot.util.IDKeyUtil;
import com.lot.util.Msg;
import com.lot.vo.lotUser.LotUser;
import com.lot.vo.lotUser.LotUserSaveVo;
import com.lot.vo.lotUser.LotUserSearchVo;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LotUserServiceImpl implements LotUserService {
    final int times = 2;
    final String algorithmName = "MD5";
    String salt = new SecureRandomNumberGenerator().nextBytes().toString();

    @Autowired
    private LotUserMapper lotUserMapper;
    @Autowired
    private LotUserDao lotUserDao;
    @Autowired
    private LotUserRoleDao lotUserRoleDao;
    @Autowired
    private LotRoleDao lotRoleDao;


    @Override
    public LotUser getUser(String username) {
        return lotUserMapper.getUser(username);
    }

    @Override
    public Msg findList(LotUserSearchVo lotUserSearchVo) {
        Map<String, Object> conMap = new PutMap().returnMap(lotUserSearchVo);
        if (lotUserSearchVo.getUsername() != null && !lotUserSearchVo.getUsername().equals("")) {
            conMap.put("username", lotUserSearchVo.getUsername());
        }
        if (lotUserSearchVo.getCreateDate() != null && lotUserSearchVo.getCreateDate().size() != 0) {
            conMap.put("createTimeStart", DateUtils.getDayBegin(lotUserSearchVo.getCreateDate().get(0)));
            conMap.put("createTimeEnd", DateUtils.getDayEnd(lotUserSearchVo.getCreateDate().get(1)));
        }
        return Msg.success().add("user", lotUserMapper.findList(conMap)).add("total", lotUserMapper.getCount(conMap));
    }

    @Override
    public boolean register(LotUserSaveVo lotUserSaveVo) {
        if (isUserName(lotUserSaveVo.getUsername()) && isUserName(lotUserSaveVo.getEmail())) {
            LotUserEntity user = new LotUserEntity();
            user = (LotUserEntity) lotUserSaveVo.setInitial(user);
            user.setId(IDKeyUtil.getStringId());
            user.setUsername(lotUserSaveVo.getUsername());
            String encodedPassword = new SimpleHash(algorithmName, lotUserSaveVo.getPassword(), salt, times).toString();
            user.setPassword(encodedPassword);
            user.setSalt(salt);
            user.setStatus(lotUserSaveVo.getStatus());
            user.setYnFlag(lotUserSaveVo.getYnFlag());

            LotRoleEntity lotRoleEntity = lotRoleDao.getByName("user");

            LotUserRoleEntity lotUserRoleEntity = new LotUserRoleEntity();
            lotUserRoleEntity.setId(IDKeyUtil.getStringId());
            lotUserRoleEntity.setRoleId(lotRoleEntity.getId());
            lotUserRoleEntity.setUserId(user.getId());
            lotUserRoleDao.save(lotUserRoleEntity);

            lotUserDao.save(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean isUserName(String username) {
        if (lotUserMapper.getUser(username) == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object getUserById(String userId) {
        LotUserEntity lotUserEntity = lotUserDao.getOne(userId);
        Map<String, Object> newMap = new HashMap<String, Object>();
        if (lotUserEntity.getAvatar() != null) {
            List<LotFileEntity> list = new ArrayList<>();
            LotFileEntity lotFileEntity = new LotFileEntity();
            lotFileEntity.setUrl(lotUserEntity.getAvatar());
            lotFileEntity.setStatus("done");
            list.add(lotFileEntity);
            newMap.put("avatars", list);
        } else {
            newMap.put("avatars", null);
        }
        Object obj = ReflectUtil.getTarget(lotUserEntity, newMap);
        return obj;
    }

    @Override
    public String saveUser(LotUserSaveVo saveVo) {
        LotUserEntity lotUserEntity;
        if (saveVo.getUserId() == null ) {
            lotUserEntity = new LotUserEntity();
            lotUserEntity.setId(IDKeyUtil.getStringId());
            if (!(isUserName(saveVo.getUsername()) && isUserName(saveVo.getEmail()))) {
                return "用户名或邮箱已被注册";
            }
        } else {
            lotUserEntity = lotUserDao.getOne(saveVo.getUserId());
            if (!(lotUserEntity.getUsername().equals(saveVo.getUsername()) || lotUserEntity.getEmail().equals(saveVo.getEmail()))) {
                if (!(isUserName(saveVo.getUsername()) && isUserName(saveVo.getEmail()))) {
                    return "用户名或邮箱已被注册";
                }
            }
        }
        //saveVo.setInitial(lotUserEntity)必须在加入值的前面
        lotUserEntity = (LotUserEntity) saveVo.setInitial(lotUserEntity);
        lotUserEntity.setStatus(saveVo.getStatus());
        lotUserEntity.setUsername(saveVo.getUsername());
        lotUserEntity.setPassword(new SimpleHash(algorithmName, saveVo.getPassword(), salt, times).toString());
        lotUserEntity.setSalt(salt);
        lotUserEntity.setAvatar(String.valueOf(saveVo.getAvatars().get(0).getUrl()));
        lotUserEntity.setEmail(saveVo.getEmail());
        lotUserEntity.setTelphone(saveVo.getTelphone());
        lotUserDao.save(lotUserEntity);

        LotRoleEntity lotRoleEntity = lotRoleDao.getByName("user");
        System.out.println(lotRoleEntity.getId());

        LotUserRoleEntity lotUserRoleEntity = new LotUserRoleEntity();
        lotUserRoleEntity.setId(IDKeyUtil.getStringId());
        lotUserRoleEntity.setRoleId(lotRoleEntity.getId());
        lotUserRoleEntity.setUserId(lotUserEntity.getId());
        lotUserRoleEntity.setYnFlag("Y");
        lotUserRoleDao.save(lotUserRoleEntity);


        if (saveVo.getUserId() != null) {
            return "注册成功";
        } else {
            return "修改成功";
        }
    }

    @Override
    public String delete(String userId) {
        LotUserEntity lotUserEntity = lotUserDao.getOne(userId);
        lotUserEntity.setYnFlag("N");
        lotUserDao.save(lotUserEntity);
        return "Success";
    }

}
