package com.example.redis_token.controller;

import com.alibaba.fastjson.JSON;
import com.example.redis_token.entity.Result;
import com.example.redis_token.entity.User;
import com.example.redis_token.service.UserService;
import com.example.redis_token.utils.MD5;
import com.example.redis_token.utils.RedisUtils;
import com.example.redis_token.utils.UserAgentUtil;
import cz.mallat.uasparser.UserAgentInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @author 吴成卓
 * @version V1.0
 * @Project: redis_token
 * @Package com.example.redis_token.controller
 * @Description:
 * @date 2020/1/5 星期日 09:42
 */
@Controller
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private RedisUtils redisUtils;
    @RequestMapping("/login")
    @ResponseBody
    public String login(User user, HttpServletRequest request)throws Exception{
        String h = request.getHeader("User-Agent");
        UserAgentInfo parse = UserAgentUtil.uaSparser.parse(h);
        String type = parse.getDeviceType();
        Object[] objects = loginByAccountAndPassword(user, type);
        Result result =new Result();
        if(objects==null){
            result.setCode(4);
            result.setMsg("登录失败");
        }else{
            result.setCode(0);
            result.setMsg("登录成功");
            result.setData(JSON.toJSONString(objects));
        }
        return JSON.toJSONString(result);
    }

    public Object[] loginByAccountAndPassword(User user,String type){
        User user1 = userService.queryUserByAccountAndPassword(user);
        if(user1==null){
            return null;
        }
        String token = this.createToken(user1, type);
        this.saveToken(user1,token);
        return new Object[]{user1,token};
    }

    /**
     * 保存token
     * @param user1
     * @param token
     */
    private void saveToken(User user1, String token) {
        String tokenKey="user"+user1.getId();
        String tokenValue=null;
        if((tokenValue=(String) redisUtils.get(tokenKey))!=null){
            redisUtils.delete(tokenKey);
            redisUtils.delete(tokenValue);
        }
        redisUtils.set(tokenKey,token,300);
        redisUtils.set(token, JSON.toJSONString(user1),300);
    }

    /**
     * 生成token
     * @param user
     * @param type
     * @return
     */
    private String createToken(User user, String type) {
        StringBuilder sb=new StringBuilder();
        sb.append("token-");
        sb.append(type+"-");
        sb.append(MD5.getMD5(user.getId().toString(),32)+"-");
        sb.append(LocalDateTime.now(ZoneOffset.of("+8")).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        sb.append(UUID.randomUUID().toString().substring(0,6));
        return sb.toString();
    }
}
