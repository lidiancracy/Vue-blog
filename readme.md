## 8/9

学到p12

- 理解模块开发
- 使用Easycode快速创建实体
- 简单运行Vue前端

## 8/10

### Get请求

?name=..&age=.. 这种可以直接接收</br>
/1 需要就加上 @PathVariable </br>
封装在body里面 加上 @RequestBody

> 如果我们使用到了分页操作，最终用beanutils封装vo时，源数据应该在page.records里面

- 我们使用Stream流
- categories象征着一个class集合 categories.stream().filter可以筛选处里面自定义条件的实体
- 如果我们想把当前实体list的一个属性拿出来用到另一张表中查询,可以用categories.stream().map

> 在本serviceimpl中,可以直接list查询表中所有条,list(wrapper) 查询满足要求的条数
> 如果想用其他表的list 或者getbyid ,可以先@autowired service

今日完结 p31

## 8/11

今天学下函数式编程以及springsecurity框架

## 8/13

花了两天时间学了一下 security框架 以及stream流,今天继续学blog 登录实现

1. 首先写一个loginimpl实现类 ,类里我们需要注入第三方bean`AuthenticationManager`(系统不会自动注入)
   我们需要创建一个SecurityConfig在里面配置.这个配置类有许多权限的配置,不要放在公共模块里面
2. 我们实现类可以用securityManager的bean对象了,先在`UsernamePasswordAuthenticationToken`输入前端传过来的用户名和密码,
   `Authentication authenticate = authenticationManager.authenticate(authenticationToken);`会调用DetailService 相关操作
   于是我们写`UserDetailServiceImpl`,重写方法`loadUserByUsername`根据用户名获取用户,顾名思义.
3. 在实现类`UserDetailServiceImpl`中我们使用usermapper获取user对象,但不能直接返回user,因为方法返回值需要一个`UserDetails`类型,
   `UserDetails`是一个接口,我们定义一个LoginUser去实现`UserDetails`接口就可以了
4. ok,detailimpl写完后继续回到主线,返回一个`authenticate.getPrincipal()`,本质上就是一个LoginUser对象,我们
   可以强转.强转成loginuser对象以后获取其成员属性user的id,使用jwt工具类将其变成token(本质就是加密).
5. 用redis存储,key是bloglogin:id,value是loginUser实体对象
6. 返回响应 响应是`BlogUserLoginVo`对象,两个属性一个是token(id结果jwt加密),一个是userinfo(loginuser的浓缩版)
7. 登录功能完成了,如果发送请求需要携带token意味着他需要登录才能访问
> 终于知道为什么运行不了了 SecurityConfig 里面还要允许`/login`请求






