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
package me.zhengjie.modules.activitiescommonservice.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
* @website https://docs.auauz.net
* @description /
* @author wk
* @date 2020-05-15
**/
@Data
public class ActiMsgSmsRecordDto implements Serializable {

    private Integer id;

    private Integer activityId;

    private String content;

    private Integer courseType;

    private Timestamp createTime;

    private Integer del;

    private String description;

    private String title;
}