package com.xshhope.pay.feign;

import com.xshhope.model.user.AppUser;
import com.xshhope.model.user.LoginAppUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xshhope
 */
@FeignClient("user-center")
public interface UserClient {

    @GetMapping(value = "/users-anon/internal", params = "username")
    public LoginAppUser findByUsername(@RequestParam("username") String username);

    @GetMapping("/users-anon/{id}")
    public AppUser findUserByIdd(@PathVariable(value = "id") Long id);
}
