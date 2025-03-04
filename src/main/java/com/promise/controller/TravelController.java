package com.promise.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.promise.result.TravelResult;
import com.promise.entity.Travel;
import com.promise.service.Impl.QuestionServiceImpl;
import com.promise.service.Impl.TravelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/travels")
public class TravelController {
    @Autowired
    private TravelServiceImpl travelService;

    @Autowired
    private QuestionServiceImpl questionService;

    /**
     * 分页查询旅游策略
     *
     * @param currentPage 当前页码
     * @param pageSize    页面数据条数
     * @return TravelResult
     */
    @GetMapping("/{currentPage}/{pageSize}")
    @ResponseBody
    public TravelResult getUserByPage(@PathVariable Integer currentPage,
                                      @PathVariable Integer pageSize) {
        IPage<Travel> iPage = new Page<>(currentPage, pageSize);
        IPage<Travel> page = travelService.page(iPage, null);
        Integer code = page.getRecords() != null ? Code.SUCCESS_CODE : Code.ERROR_CODE;
        String msg = page.getRecords() != null ? "查询成功" : "查询失败";
        Map<String, Object> data = new HashMap<>();
        data.put("pages", page.getPages());
        data.put("currentPage", page.getCurrent());
        data.put("pageSize", page.getSize());
        data.put("plannings", page.getRecords());
        data.put("total", page.getTotal());
        return new TravelResult(code, data, msg);
    }

    /**
     * 添加旅游策略
     *
     * @param travel 旅游策略
     * @return TravelResult
     */
    @PostMapping
    @ResponseBody
    public TravelResult save(@RequestBody Travel travel) {
        boolean flag = travelService.save(travel);
        Integer code = flag ? Code.SUCCESS_CODE : Code.ERROR_CODE;
        String msg = flag ? "添加成功" : "添加失败";
        return new TravelResult(code, flag, msg);
    }

    /**
     * 修改旅游策略
     *
     * @param travel 旅游策略
     * @return TravelResult
     */
    @PutMapping
    @ResponseBody
    public TravelResult update(@RequestBody Travel travel) {
        boolean flag = travelService.updateById(travel);
        Integer code = flag ? Code.SUCCESS_CODE : Code.ERROR_CODE;
        String msg = flag ? "修改成功" : "修改失败";
        return new TravelResult(code, flag, msg);
    }

    /**
     * 根据id查询旅游策略
     *
     * @param id 旅游策略的id
     * @return TravelResult
     */
    @GetMapping("/{id}")
    @ResponseBody
    public TravelResult getTravelById(@PathVariable Integer id) {
        Travel travel = travelService.getById(id);
        Integer code = travel != null ? Code.SUCCESS_CODE : Code.ERROR_CODE;
        String msg = travel != null ? "查询成功" : "id不存在或查询失败";
        return new TravelResult(code, travel, msg);
    }
}
