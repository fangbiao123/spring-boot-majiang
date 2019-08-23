package com.example.majiang.dto;

import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * 分页数据
 */
@Data
public class PaginationDTO {

    private List<QuestionDTO> questions;
    private Integer totalPage;
    private Boolean showPrevious;
    private Boolean showFirstPage;
    private Boolean showNext;
    private Boolean showLastPage;
    // 当前页
    private Integer page;
    // 页码
    private List<Integer> pages = new ArrayList<>();
    private Integer limit;

    /***
     * 设置分页参数
     * @param page
     * @param limit
     * @param total
     */
    public void setPagination(Integer page, Integer limit, Integer total) {

        this.setLimit(limit);
        // 显示页码的数量 实际出现的页码为(4 * 2 - 1个页码)
        int showPageCount = 4;
        // 总页数
        totalPage = (total / limit) != 0 ? total / limit : 0;
        totalPage = (total % limit) != 0 ? totalPage++ : totalPage;
        //  超出最大页数
        page = page > totalPage ? totalPage : page;
        this.setPage(page);
        // 上一页
        showPrevious = page > 1 ? true : false;
        // 下一页
        showNext = page < totalPage ? true : false;

        // 页码
        for (Integer i = 1; i <= totalPage; i++) {
            if (i > (page - showPageCount) && i < (page + showPageCount)) {
                pages.add(i);
            }
        }

        // 第一页
        showFirstPage = pages.contains(1) ? false : true;
        // 最后一页
        showLastPage = pages.contains(totalPage) ? false : true;

    }
}
