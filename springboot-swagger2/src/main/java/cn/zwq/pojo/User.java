package cn.zwq.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value ="用户实体类",description = "封装用户信息")
public class User {
    @ApiModelProperty(name = "id",value = "用户id")
    private Long id;
    @ApiModelProperty(name = "username",value = "用户名")
    private String username;
    @ApiModelProperty(name = "password",value = "用户密码")
    private String password;
}