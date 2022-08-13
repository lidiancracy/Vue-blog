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
花了两天时间学了一下 security框架 以及stream流,今天继续学blog

