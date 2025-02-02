package com.wayn.admin.api.controller.shop;

import com.wayn.common.base.controller.BaseController;
import com.wayn.common.constant.SysConstants;
import com.wayn.common.core.domain.shop.Channel;
import com.wayn.common.core.service.shop.IChannelService;
import com.wayn.common.enums.ReturnCodeEnum;
import com.wayn.common.util.R;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("shop/channel")
public class ChannelController extends BaseController {

    private IChannelService iChannelService;

    @PreAuthorize("@ss.hasPermi('shop:channel:list')")
    @GetMapping("/list")
    public R list(Channel channel) {
        return R.success().add("channelList", iChannelService.list(channel));
    }

    @PreAuthorize("@ss.hasPermi('shop:channel:add')")
    @PostMapping
    public R addChannel(@Validated @RequestBody Channel channel) {
        if (SysConstants.NOT_UNIQUE.equals(iChannelService.checkChannelNameUnique(channel))) {
            return R.error(ReturnCodeEnum.CUSTOM_ERROR
                    .setMsg(String.format("新增栏目名称[%s]失败，栏目名称已存在", channel.getName())));
        } else if (SysConstants.NOT_UNIQUE.equals(iChannelService.checkChannelCodeUnique(channel))) {
            return R.error(ReturnCodeEnum.CUSTOM_ERROR
                    .setMsg(String.format("新增栏目编码[%s]失败，栏目编码已存在", channel.getName())));
        }
        channel.setCreateTime(new Date());
        return R.result(iChannelService.save(channel));
    }

    @PreAuthorize("@ss.hasPermi('shop:channel:update')")
    @PutMapping
    public R updateChannel(@Validated @RequestBody Channel channel) {
        if (SysConstants.NOT_UNIQUE.equals(iChannelService.checkChannelNameUnique(channel))) {
            return R.error(ReturnCodeEnum.CUSTOM_ERROR
                    .setMsg(String.format("更新栏目名称[%s]失败，栏目名称已存在", channel.getName())));
        } else if (SysConstants.NOT_UNIQUE.equals(iChannelService.checkChannelCodeUnique(channel))) {
            return R.error(ReturnCodeEnum.CUSTOM_ERROR
                    .setMsg(String.format("更新栏目编码[%s]失败，栏目编码已存在", channel.getName())));
        }
        channel.setUpdateTime(new Date());
        return R.result(iChannelService.updateById(channel));
    }

    @PreAuthorize("@ss.hasPermi('shop:channel:info')")
    @GetMapping("{channelId}")
    public R getChannel(@PathVariable Long channelId) {
        return R.success().add("data", iChannelService.getById(channelId));
    }

    @PreAuthorize("@ss.hasPermi('shop:channel:delete')")
    @DeleteMapping("{channelIds}")
    public R deleteChannel(@PathVariable List<Long> channelIds) {
        return R.result(iChannelService.removeByIds(channelIds));
    }
}
