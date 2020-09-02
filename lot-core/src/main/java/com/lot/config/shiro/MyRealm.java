package com.lot.config.shiro;

import com.lot.service.LotAuthorityService;
import com.lot.service.LotRoleService;
import com.lot.service.LotUserService;
import com.lot.vo.lotUser.LotUser;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MyRealm extends AuthorizingRealm {
    private static final Logger log = LogManager.getLogger(MyRealm.class);

    @Autowired
    private LotUserService lotUserService;
    @Autowired
    private LotRoleService lotRoleService;
    @Autowired
    private LotAuthorityService lotAuthorityService;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("开始授权>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        //能进入到这里，表示账号已经通过验证了
        String userName = (String) principalCollection.getPrimaryPrincipal();
        //通过service获取角色和权限
        Set<String> permissions = lotAuthorityService.getAuthority(userName);
        Set<String> roles = lotRoleService.getRoleByAccount(userName);

        //授权对象
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //把通过service获取到的角色和权限放进去
        simpleAuthorizationInfo.setStringPermissions(permissions);
        simpleAuthorizationInfo.setRoles(roles);
        Set<String> roless = simpleAuthorizationInfo.getRoles();
        log.info("角色为：");
        for (String s : roless) {
            log.info(s);
        }
        log.info("权限为：");
        Set<String> spermissions = simpleAuthorizationInfo.getStringPermissions();
        for (String spermission : spermissions) {
            log.info(spermission);
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("开始验证账号密码>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        //获取账号密码
        UsernamePasswordToken t = (UsernamePasswordToken) token;
        String userName = token.getPrincipal().toString();
        //获取数据库中的密码
        LotUser user = lotUserService.getUser(userName);
        if (user == null || user.getStatus().equals("0")) {
            return null;
        }
        String passwordInDB = user.getPassword();
        String salt = user.getSalt();
        //认证信息里存放账号密码, getName() 是当前Realm的继承方法,通常返回当前类名 :databaseRealm
        //盐也放进去
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userName, passwordInDB, ByteSource.Util.bytes(salt), getName());
        return simpleAuthenticationInfo;
    }
}
