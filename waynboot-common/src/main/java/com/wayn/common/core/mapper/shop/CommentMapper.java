package com.wayn.common.core.mapper.shop;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wayn.common.core.domain.shop.Comment;

/**
 * <p>
 * 评论表 Mapper 接口
 * </p>
 *
 * @author wayn
 * @since 2020-10-03
 */
public interface CommentMapper extends BaseMapper<Comment> {

    IPage<Comment> selectListPage(Page<Comment> page, Comment comment);
}
