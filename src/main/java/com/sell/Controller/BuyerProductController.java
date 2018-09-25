package com.sell.Controller;

import com.sell.DateObject.ProductCategory;
import com.sell.DateObject.ProductInfo;
import com.sell.Service.IMPL.CategoryServiceIMPL;
import com.sell.Service.IMPL.ProductInfoServiceIMPL;
import com.sell.ViewObject.ProductInfoViewO;
import com.sell.ViewObject.ResultProductViewO;
import com.sell.ViewObject.ResultViewO;
import com.sell.Utils.ResultViewOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/6
 * 10:46
 * #
 */

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoServiceIMPL productInfoServiceIMPL;

    @Autowired
    CategoryServiceIMPL categoryService;

    @RequestMapping("/list")
    public ResultViewO list (){

        //1-查询所有上架商品
        List<ProductInfo> productInfoList =  productInfoServiceIMPL.findUpAll();
        //2-查询类目-所有的一次性查询
        List<Integer> categoryTypeList = new ArrayList<>();
        //传统
      /*  for (ProductInfo productInfo:productInfoList
             ) {
            categoryTypeList.add(productInfo.getCategoryType());
        }*/
       //精简方法（java8 lmadba）
        categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        System.out.println(categoryTypeList);
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);//通过类目榜单编号categoryTypeList查询所有的商品榜单

        //3-数据拼接
       List<ResultProductViewO> resultProductViewOList = new ArrayList<>();
        //第一层循环，拼接销售榜单的信息
        for (ProductCategory productCategory: productCategoryList
             ) {
            ResultProductViewO resultProductViewO = new ResultProductViewO();
            resultProductViewO.setCategoryName(productCategory.getCategoryName());
            resultProductViewO.setCategoryType(productCategory.getCategoryType());

            //第二层循环，拼接在同一个榜单（类目）详细的产品的信息
            List<ProductInfoViewO> productInfoViewOList = new ArrayList<>();
            for (ProductInfo productInfo:productInfoList
                 ) {
                //判断 商品详情表ProductInfo-CategoryType和类目表productCategory的CategoryType是不是一个
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoViewO productInfoViewO =new ProductInfoViewO();
                    productInfoViewO.setProductId(productInfo.getProductId());
                    productInfoViewO.setProductName(productInfo.getProductName());
                    productInfoViewO.setProductPrice(productInfo.getProductPrice());
                    productInfoViewO.setProductDescription(productInfo.getProductDescription());
                    productInfoViewO.setProductIcon(productInfo.getProductIcon());

                    //精简方法
                    //BeanUtils.copyProperties(productInfo, productInfoViewO);

                    //在返回的商品详情—list 加入商品
                   productInfoViewOList.add(productInfoViewO);
                }
            }
            //给resultProductViewO表的  @JsonProperty("foods") List<ProductInfoViewO> 赋值
            resultProductViewO.setProductInfoViewO(productInfoViewOList);
            //找出所有的resultProductViewO    用List传值
            resultProductViewOList.add(resultProductViewO);
        }
        //最终的返回值
        /*
        ResultViewO resultViewO = new ResultViewO();
        resultViewO.setCode(0);
        resultViewO.setMsg("成功");
        resultViewO.setData(Arrays.asList(resultProductViewOList));
        */
        return  ResultViewOUtil.success(resultProductViewOList);
    }
}
