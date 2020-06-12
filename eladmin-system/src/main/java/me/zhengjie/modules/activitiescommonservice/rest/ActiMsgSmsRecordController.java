/*
*  Copyright 2019-2020 Zheng Jie
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package me.zhengjie.modules.activitiescommonservice.rest;

import me.zhengjie.modules.activitiescommonservice.domain.ActiMsgSmsRecord;
import me.zhengjie.modules.activitiescommonservice.service.ActiMsgSmsRecordService;
import me.zhengjie.modules.activitiescommonservice.service.dto.ActiMsgSmsRecordQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.annotation.Log;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* @website https://docs.auauz.net
* @author wk
* @date 2020-05-15
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "msg管理")
@RequestMapping("/api/actiMsgSmsRecord")
public class ActiMsgSmsRecordController {

    private final ActiMsgSmsRecordService actiMsgSmsRecordService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('actiMsgSmsRecord:list')")
    public void download(HttpServletResponse response, ActiMsgSmsRecordQueryCriteria criteria) throws IOException {
        actiMsgSmsRecordService.download(actiMsgSmsRecordService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询msg")
    @ApiOperation("查询msg")
    @PreAuthorize("@el.check('actiMsgSmsRecord:list')")
    public ResponseEntity<Object> query(ActiMsgSmsRecordQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(actiMsgSmsRecordService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增msg")
    @ApiOperation("新增msg")
    @PreAuthorize("@el.check('actiMsgSmsRecord:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ActiMsgSmsRecord resources){
        return new ResponseEntity<>(actiMsgSmsRecordService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改msg")
    @ApiOperation("修改msg")
    @PreAuthorize("@el.check('actiMsgSmsRecord:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ActiMsgSmsRecord resources){
        actiMsgSmsRecordService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除msg")
    @ApiOperation("删除msg")
    @PreAuthorize("@el.check('actiMsgSmsRecord:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Integer[] ids) {
        actiMsgSmsRecordService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}