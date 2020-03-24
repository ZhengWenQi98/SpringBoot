package cn.zwq.controller;

import cn.zwq.pojo.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "用户模块")
@RestController
@RequestMapping("/user")
public class UserController extends WebMvcConfigurationSupport {

    private Map<Long,User> users = Collections.synchronizedMap(new HashMap<>());

//    @ApiOperation(value = "跳转到用户登录页面",notes = "当访问项目根路径的时候，跳转到用户登录页面")
    @ApiIgnore
    @GetMapping("/")
    public String login(){
        return "login";
    }

    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    @PutMapping("/{id}")
    public String putUser(@PathVariable Long id, @RequestBody User user) {
        User u = new User();
        users.put(id, u);
        return "success";
    }

    @ApiResponses({
            @ApiResponse(code = 404,message = "请求路径找不到"),
            @ApiResponse(code = 400,message = "请求参数不正确")
    })
    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @PostMapping("/insert")
    public String postUser(@RequestBody User user) {
        users.put(user.getId(), user);
        return "success";
    }

    @ApiResponses({
            @ApiResponse(code = 404,message = "请求路径找不到"),
            @ApiResponse(code = 400,message = "请求参数不正确")
    })
    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return users.get(id);
    }

    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        users.remove(id);
        return "success";
    }
}