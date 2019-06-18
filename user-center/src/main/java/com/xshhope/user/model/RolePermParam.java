package com.xshhope.user.model;

import lombok.Data;

import java.util.List;

/**
 * @author xshhope
 */
@Data
public class RolePermParam {
    private Long id;
    private List<Long> set;
}
