package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Constants;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.example.mapper.UserMapper;
import com.example.utils.SaltUtils;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public void add(User user) {
        // 业务方法
        // 1. 判断用户账号是否重复
        User dbUser = userMapper.selectByUsername(user.getUsername());
        if (dbUser != null) {
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
        }
        // 2. 判断用户密码是不是空
        if (ObjectUtil.isEmpty(user.getPassword())) {
            user.setPassword(Constants.USER_DEFAULT_PASSWORD); // 默认密码 123
        }
        // 3. 判断用户名称是不是空
        if (ObjectUtil.isEmpty(user.getName())) {
            user.setName(user.getUsername());
        }
        // 4. 默认用户角色
        user.setRole(RoleEnum.USER.name());
        // 5. 随机生成盐字符串
        String salt = SaltUtils.generateSalt();
        user.setSalt(salt);
        // 别忘了传到数据库的密码应该是hash过的
        String hashedPassword = SaltUtils.hashedPassword(user.getPassword(), salt);
        user.setPassword(hashedPassword);
        userMapper.insert(user);
    }

    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            this.deleteById(id);
        }
    }

    public void updateById(User user) {
        userMapper.updateById(user);
    }

    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    public List<User> selectAll(User user) {
        return userMapper.selectAll(user);
    }

    public PageInfo<User> selectPage(User user, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userMapper.selectAll(user);
        return PageInfo.of(list);
    }

    public Account login(Account account) {
        Account dbUser = userMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbUser)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        // 检查密码
        String password = account.getPassword();
        String salt = dbUser.getSalt();
        if (!SaltUtils.hashedPassword(password, salt).equals(dbUser.getPassword())) {
            throw new CustomException(ResultCodeEnum.USER_ACCOUNT_ERROR);
        }
        // 生成token
        String tokenData = dbUser.getId() + "-" + RoleEnum.USER.name();
        String token = TokenUtils.createToken(tokenData, dbUser.getPassword());
        dbUser.setToken(token);
        return dbUser;
    }

    /**
     * 注册
     */
    public void register(Account account) {
        User user = new User();
        BeanUtils.copyProperties(account, user);
        this.add(user);
    }

    public void updatePassword(Account account) {
        User dbUser = userMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbUser)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        String newHashedPW = SaltUtils.hashedPassword(account.getPassword(), dbUser.getSalt());// 使用旧盐
        if (!newHashedPW.equals(dbUser.getPassword())) {
            throw new CustomException(ResultCodeEnum.PARAM_PASSWORD_ERROR);
        }
        String salt = SaltUtils.generateSalt();
        newHashedPW = SaltUtils.hashedPassword(account.getNewPassword(), salt);// 使用新盐
        dbUser.setSalt(salt);
        dbUser.setPassword(newHashedPW);
        this.updateById(dbUser);
    }

}
