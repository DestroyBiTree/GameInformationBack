package com.promise.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.promise.dto.BaseResponse;
import com.promise.entity.User;
import com.promise.service.UserService;
import com.promise.util.CodeUtil;
import com.promise.util.PasswordUtils;
import com.promise.util.ResponseCode;
import com.promise.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 分页查询用户
     *
     * @param currentPage 当前页码
     * @param pageSize    页面数据条数
     * @return BaseResponse
     */
    @PostMapping("/{currentPage}/{pageSize}")
    @ResponseBody
    public BaseResponse getUserByPage(@PathVariable Integer currentPage, @PathVariable Integer pageSize,
                                    @RequestBody User searchData) {
        // 查询条件
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(!StringUtils.isEmpty(searchData.getUsername()),
                        User::getUsername, searchData.getUsername())
                .eq(!StringUtils.isEmpty(searchData.getIdentity()), User::getIdentity,
                        searchData.getIdentity());

        IPage<User> iPage = new Page<>(currentPage, pageSize);
        IPage<User> page = userService.page(iPage, queryWrapper);
        
        if (page.getRecords() != null) {
            Map<String, Object> data = new HashMap<>();
            data.put("pages", page.getPages());
            data.put("currentPage", page.getCurrent());
            data.put("pageSize", page.getSize());
            data.put("users", page.getRecords());
            data.put("total", page.getTotal());
            return BaseResponse.success("查询成功", data);
        } else {
            return BaseResponse.error("查询失败");
        }
    }

    /**
     * 用户登录
     *
     * @param user 用户信息
     * @return BaseResponse
     */
    @PostMapping("/login")
    @ResponseBody
    public BaseResponse login(@RequestBody User user) throws NoSuchAlgorithmException {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, user.getUsername()).eq(User::getIdentity,
                user.getIdentity());
        List<User> users = userService.list(queryWrapper);

        Map<String, Object> data = new HashMap<>();
        if (users != null && users.size() == 1) {
            // 验证密码是否正确
            boolean flag = PasswordUtils.verifyPassword(user.getPassword(), users.get(0).getPassword());
            if (flag) {
                String token = TokenUtils.token(user.getUsername(), user.getPassword());
                data.put("token", token);
                data.put("userID", users.get(0).getId());
                data.put("username", users.get(0).getUsername());
                String identity = users.get(0).getIdentity() == 1 ? "管理员" : "用户";
                data.put("identity", identity);
                return BaseResponse.success("登录成功", data);
            } else {
                return BaseResponse.error(ResponseCode.LOGIN_ERROR, "用户名或密码或身份错误");
            }
        }
        return BaseResponse.error(ResponseCode.LOGIN_ERROR, "用户名或密码或身份错误");
    }

    /**
     * 用户注册
     *
     * @param user 用户数据
     * @return BaseResponse
     */
    @PostMapping("/register")
    @ResponseBody
    public BaseResponse save(HttpServletRequest request, @RequestBody User user) {
        // 验证码错误
        if (!request.getServletContext().getAttribute("code").equals(user.getCode().toUpperCase())) {
            return BaseResponse.error("验证码错误");
        }
        // 判断用户名是否重复
        List<User> list = userService.getByUsername(user.getUsername());
        if (list.size() > 0) {
            return BaseResponse.error(ResponseCode.DARA_REPEAR, "用户名重复");
        }

        boolean flag = userService.save(user);
        if (flag) {
            return BaseResponse.success("注册成功", true);
        } else {
            return BaseResponse.error("注册失败");
        }
    }

    /**
     * 根据id删除用户
     *
     * @param id 用户的id
     * @return BaseResponse
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public BaseResponse delete(@PathVariable Integer id) {
        boolean flag = userService.removeById(id);
        if (flag) {
            return BaseResponse.success("删除成功", true);
        } else {
            return BaseResponse.error("删除失败");
        }
    }

    /**
     * 查询逻辑删除的用户
     *
     * @return BaseResponse
     */
    @GetMapping("/deleted")
    @ResponseBody
    public BaseResponse getDeletedUsers() {
        List<User> deletedUsers = userService.getDeleted();
        if (deletedUsers != null) {
            return BaseResponse.success("查询成功", deletedUsers);
        } else {
            return BaseResponse.error("查询失败");
        }
    }

    /**
     * 根据id更新用户信息
     *
     * @param id   用户的id
     * @param user 用户信息
     * @return BaseResponse
     */
    @PutMapping("/{id}")
    @ResponseBody
    public BaseResponse updateUser(@PathVariable Integer id, @RequestBody User user) {
        user.setId(id); // 确保用户ID被设置
        boolean flag = userService.updateById(user);
        if (flag) {
            return BaseResponse.success("更新成功", true);
        } else {
            return BaseResponse.error("更新失败");
        }
    }

    /**
     * 获取验证码
     *
     * @param response 响应对象
     * @return 验证码
     * @throws IOException 异常
     */
    @GetMapping("/code")
    public String getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletOutputStream os = response.getOutputStream();
        String code = CodeUtil.outputVerifyImage(100, 50, os, 4);
        request.getServletContext().setAttribute("code", code);
        return code;
    }
}
