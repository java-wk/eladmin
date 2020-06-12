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
package me.zhengjie.modules.activitiescommonservice.service.impl;

import me.zhengjie.modules.activitiescommonservice.domain.ActiMsgSmsRecord;
import me.zhengjie.modules.activitiescommonservice.repository.ActiMsgSmsRecordRepository;
import me.zhengjie.modules.activitiescommonservice.service.ActiMsgSmsRecordService;
import me.zhengjie.modules.activitiescommonservice.service.dto.ActiMsgSmsRecordDto;
import me.zhengjie.modules.activitiescommonservice.service.dto.ActiMsgSmsRecordQueryCriteria;
import me.zhengjie.modules.activitiescommonservice.service.mapstruct.ActiMsgSmsRecordMapper;
import lombok.RequiredArgsConstructor;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import me.zhengjie.utils.ValidationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* @website https://docs.auauz.net
* @description 服务实现
* @author wk
* @date 2020-05-15
**/
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ActiMsgSmsRecordServiceImpl implements ActiMsgSmsRecordService {

    private final ActiMsgSmsRecordRepository actiMsgSmsRecordRepository;
    private final ActiMsgSmsRecordMapper actiMsgSmsRecordMapper;

    @Override
    public Map<String,Object> queryAll(ActiMsgSmsRecordQueryCriteria criteria, Pageable pageable){
        Page<ActiMsgSmsRecord> page = actiMsgSmsRecordRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(actiMsgSmsRecordMapper::toDto));
    }

    @Override
    public List<ActiMsgSmsRecordDto> queryAll(ActiMsgSmsRecordQueryCriteria criteria){
        return actiMsgSmsRecordMapper.toDto(actiMsgSmsRecordRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public ActiMsgSmsRecordDto findById(Integer id) {
        ActiMsgSmsRecord actiMsgSmsRecord = actiMsgSmsRecordRepository.findById(id).orElseGet(ActiMsgSmsRecord::new);
        ValidationUtil.isNull(actiMsgSmsRecord.getId(),"ActiMsgSmsRecord","id",id);
        return actiMsgSmsRecordMapper.toDto(actiMsgSmsRecord);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ActiMsgSmsRecordDto create(ActiMsgSmsRecord resources) {
        return actiMsgSmsRecordMapper.toDto(actiMsgSmsRecordRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ActiMsgSmsRecord resources) {
        ActiMsgSmsRecord actiMsgSmsRecord = actiMsgSmsRecordRepository.findById(resources.getId()).orElseGet(ActiMsgSmsRecord::new);
        ValidationUtil.isNull( actiMsgSmsRecord.getId(),"ActiMsgSmsRecord","id",resources.getId());
        actiMsgSmsRecord.copy(resources);
        actiMsgSmsRecordRepository.save(actiMsgSmsRecord);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            actiMsgSmsRecordRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<ActiMsgSmsRecordDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ActiMsgSmsRecordDto actiMsgSmsRecord : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put(" activityId",  actiMsgSmsRecord.getActivityId());
            map.put(" content",  actiMsgSmsRecord.getContent());
            map.put(" courseType",  actiMsgSmsRecord.getCourseType());
            map.put(" createTime",  actiMsgSmsRecord.getCreateTime());
            map.put(" del",  actiMsgSmsRecord.getDel());
            map.put(" description",  actiMsgSmsRecord.getDescription());
            map.put(" title",  actiMsgSmsRecord.getTitle());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}