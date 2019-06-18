package com.xshhope.user.model;

import lombok.Data;

/**
 * @author xshhope
 */
@Data
public class AppUserDTO {
    private Long id;
    private String username;
    private String nickname;
    private String phone;
    private String mail;
    private Integer sex;
    /**
     * 状态
     */
    private Boolean enabled;
    private String type;
    private Integer passed;
}
