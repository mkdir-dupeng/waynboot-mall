package com.wayn.mobile.api.controller;

import com.wayn.common.util.R;
import com.wayn.mobile.api.service.IGoodsDetailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品接口
 *
 * @author wayn
 * @since 2024/1/15
 */
@RestController
@AllArgsConstructor
@RequestMapping("goods")
public class GoodsController {

    private IGoodsDetailService iGoodsDetailService;

    /**
     * 商品详情
     *
     * @param goodsId 商品id
     * @return R
     */
    @GetMapping("detail/{goodsId}")
    public R detail(@PathVariable Long goodsId) {
        return R.success(iGoodsDetailService.getGoodsDetailData(goodsId));
    }
}
