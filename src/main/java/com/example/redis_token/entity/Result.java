package com.example.redis_token.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 吴成卓
 * @version V1.0
 * @Project: redis_token
 * @Package com.example.redis_token.entity
 * @Description:
 * @date 2020/1/5 星期日 11:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private Integer code;
    private String msg;
    private String data;
}
