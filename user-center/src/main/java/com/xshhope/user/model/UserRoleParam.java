package com.xshhope.user.model;

import lombok.Data;

import java.util.List;

/**
 * @author xshhope
 */
@Data
public class UserRoleParam {
    private Long id;
    private List<Long> set;
}
