package com.exercise.demo.controller;

import com.exercise.demo.common.utils.DatabaseUtil;
import com.exercise.demo.model.Response;
import com.exercise.demo.model.po.order.ArticleInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/8/25 11:20
 */
@RestController
@RequestMapping("/database")
@Api(value = "db展示", description = "db展示")
public class DatabaseController {

    @ApiOperation(value = "获取表名", notes = "获取表名", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/getTableNames")
    public List<String> getTableNames() {
        return DatabaseUtil.getTableNames();
    }

    @ApiOperation(value = "获取表每行信息")
    @RequestMapping(value = "getTableColumnInfo", method = RequestMethod.GET)
    @ApiImplicitParam(name = "tableName", value = "表名", required = true,
            dataType = "String", paramType = "query")
    @ResponseBody
    public LinkedHashMap<String, List<String>> getTableColumnInfo(@RequestParam String tableName) {
        List<String> tablesNames = DatabaseUtil.getTableNames();
        if (tablesNames == null || tablesNames.size() <= 0) {
            return null;
        }
        if (!tablesNames.contains(tableName)) {
            return null;
        }
        return DatabaseUtil.getColumnAllInfo(tableName);
    }


    @ApiOperation(value = "获取表索引信息")
    @RequestMapping(value = "getIndexInfo", method = RequestMethod.GET)
    @ApiImplicitParam(name = "tableName", value = "表名", required = true,
            dataType = "String", paramType = "query")
    @ResponseBody
    public LinkedHashMap<String, List<String>> getIndexInfo(@RequestParam String tableName) {
        List<String> tablesNames = DatabaseUtil.getTableNames();
        if (tablesNames == null || tablesNames.size() <= 0) {
            return null;
        }
        if (!tablesNames.contains(tableName)) {
            return null;
        }
        return DatabaseUtil.getIndexInfo(tableName);
    }

}
