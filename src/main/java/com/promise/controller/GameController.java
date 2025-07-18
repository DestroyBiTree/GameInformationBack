package com.promise.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.promise.dto.BaseResponse;
import com.promise.entity.Game;
import com.promise.service.GameService;
import com.promise.util.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/games")
public class GameController {
    @Autowired
    private GameService gameService;

    /**
     * 分页查询游戏信息
     *
     * @param currentPage 当前页码
     * @param pageSize    页面数据条数
     * @return BaseResponse
     */
    @GetMapping("/{currentPage}/{pageSize}")
    @ResponseBody
    public BaseResponse getGamesByPage(@PathVariable Integer currentPage,
                                     @PathVariable Integer pageSize) {
        IPage<Game> iPage = new Page<>(currentPage, pageSize);
        IPage<Game> page = gameService.page(iPage, null);
        
        if (page.getRecords() != null) {
            Map<String, Object> data = new HashMap<>();
            data.put("pages", page.getPages());
            data.put("currentPage", page.getCurrent());
            data.put("pageSize", page.getSize());
            data.put("games", page.getRecords());
            data.put("total", page.getTotal());
            return BaseResponse.success("查询成功", data);
        } else {
            return BaseResponse.error("查询失败");
        }
    }

    /**
     * 根据id查询游戏信息
     *
     * @param id 游戏的id
     * @return BaseResponse
     */
    @GetMapping("/detail/{id}")
    @ResponseBody
    public BaseResponse getGameById(@PathVariable Integer id) {
        Game game = gameService.getById(id);
        if (game != null) {
            return BaseResponse.success("查询成功", game);
        } else {
            return BaseResponse.error(ResponseCode.NOT_FOUND, "id不存在或查询失败");
        }
    }

    /**
     * 添加游戏信息
     *
     * @param game 游戏信息
     * @return BaseResponse
     */
    @PostMapping
    @ResponseBody
    public BaseResponse save(@RequestBody Game game) {
        boolean flag = gameService.save(game);
        if (flag) {
            return BaseResponse.success("添加成功", true);
        } else {
            return BaseResponse.error("添加失败");
        }
    }

    /**
     * 根据id删除游戏信息
     *
     * @param id 游戏的id
     * @return BaseResponse
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public BaseResponse delete(@PathVariable Integer id) {
        boolean flag = gameService.removeById(id);
        if (flag) {
            return BaseResponse.success("删除成功", true);
        } else {
            return BaseResponse.error("删除失败");
        }
    }

    /**
     * 根据id更新游戏信息
     *
     * @param id   游戏的id
     * @param game 游戏信息
     * @return BaseResponse
     */
    @PutMapping("/{id}")
    @ResponseBody
    public BaseResponse updateGame(@PathVariable Integer id, @RequestBody Game game) {
        game.setId(id);
        boolean flag = gameService.updateById(game);
        if (flag) {
            return BaseResponse.success("更新成功", true);
        } else {
            return BaseResponse.error("更新失败");
        }
    }
} 