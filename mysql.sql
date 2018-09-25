/*--微信点餐系统

--商品类目表product-category,kind，varsity，
*/
CREATE TABLE product_category (

category_id INT not  null auto_increment,
category_name varchar (64) not null comment '类目名字',

category_type int  not null comment '类目编号',
create_time TIMESTAMP not null DEFAULT  CURRENT_TIMESTAMP comment '创建时间',
update_time TIMESTAMP not null DEFAULT  CURRENT_TIMESTAMP  on UPDATE  current_timestamp COMMENT '更新时间',


PRIMARY KEY (category_id)
);
/*
--商品表product_information
*/
CREATE TABLE product_infor(

product_id VARCHAR (32) NOT NULL ,
product_name VARCHAR (64) NOT NULL comment '商品名',
product_price DECIMAL (8,2) NOT NULL comment '商品价格',
product_stock INT not NULL comment '库存',
product_description VARCHAR (64) not NULL comment '描述',
product_icon VARCHAR (512) NOT NULL comment '小图标',
product_status tinyint(3) NOT NULL DEFAULT '0' comment '商品状态，0正常1下架',

category_type INT not  null comment '类目编号',
create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间',
update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP on UPDATE  CURRENT_TIMESTAMP comment '更新时间',
primary key (product_id)
);

/*
--订单表order_infor
*/
CREATE  TABLE  order_infor(
order_id VARCHAR (32) NOT NULL comment '订单号',
buyer_name VARCHAR (32) NOT NULL comment '买家名字',
buyer_phone VARCHAR (32) NOT NULL comment '买家电话',
buyer_openid VARCHAR (64) NOT NULL comment '买家微信openid',
buyer_adress VARCHAR (128) NOT NULL comment '买家地址',
order_amount decimal (8,2) NOT NULL  comment '订单总金额',
order_status tinyint(3) DEFAULT '0' comment '订单状态，0默认新下单',
pay_status tinyint(3) DEFAULT '0' comment '支付状态，0默认未支付',
create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间',
update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP on UPDATE CURRENT_TIMESTAMP comment '更新时间',
primary key (order_id),
key `idx_buyer_openid` (`buyer_openid`)
) ;

/*
--订单商品详情表order_product_detail
*/

CREATE TABLE order_product_detail(
detail_id VARCHAR (32) NOT  NULL comment '表的详情id',
order_id VARCHAR (32) NOT NULL comment '订单号',

product_id VARCHAR (32) NOT NULL ,
product_name VARCHAR (64) NOT NULL comment '商品名',
product_price DECIMAL (8,2) NOT NULL comment '商品价格',
product_quantity INT not NULL comment '数量',
product_icon VARCHAR (512) NOT NULL comment '小图标',

create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间',
update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP on UPDATE  CURRENT_TIMESTAMP comment '更新时间',

primary key (order_id),
key `idx_order_id` (`order_id`)
);

/*--卖家登陆表
*/
CREATE TABLE seller_infor(
seller_id varchar (32) not null,
seller_username VARCHAR (64) NOT NULL ,
seller_password VARCHAR (32) NOT NULL ,
seller_openid VARCHAR (64) NOT NULL ,

create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间',
update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP on UPDATE  CURRENT_TIMESTAMP comment '更新时间',
primary key (seller_id)

  );