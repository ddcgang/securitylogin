package com.demo.securitylogin.model.sys;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
/**
 * @author YYGang
 * @date 2020/10/15
 * @desc 系统角色实体
 */
@Data
@Table(name = "sys_role")
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "role_auth")
    private String roleAuth;
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date createTime;
}
