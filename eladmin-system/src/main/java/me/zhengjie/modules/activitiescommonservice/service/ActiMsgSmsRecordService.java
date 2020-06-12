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
package me.zhengjie.modules.activitiescommonservice.service;

import me.zhengjie.modules.activitiescommonservice.domain.ActiMsgSmsRecord;
import me.zhengjie.modules.activitiescommonservice.service.dto.ActiMsgSmsRecordDto;
import me.zhengjie.modules.activitiescommonservice.service.dto.ActiMsgSmsRecordQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @website https://docs.auauz.net
* @description 服务接口
* @author wk
* @date 2020-05-15
**/
public interface ActiMsgSmsRecordService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(ActiMsgSmsRecordQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<ActiMsgSmsRecordDto>
    */
    List<ActiMsgSmsRecordDto> queryAll(ActiMsgSmsRecordQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return ActiMsgSmsRecordDto
     */
    ActiMsgSmsRecordDto findById(Integer id);

    /**
    * 创建
    * @param resources /
    * @return ActiMsgSmsRecordDto
    */
    ActiMsgSmsRecordDto create(ActiMsgSmsRecord resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(ActiMsgSmsRecord resources);

    /**
    * 多选删除
    * @param ids /
    */
    void deleteAll(Integer[] ids);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<ActiMsgSmsRecordDto> all, HttpServletResponse response) throws IOException;
}