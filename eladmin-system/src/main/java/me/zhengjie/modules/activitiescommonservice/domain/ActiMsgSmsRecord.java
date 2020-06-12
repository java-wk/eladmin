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
package me.zhengjie.modules.activitiescommonservice.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

/**
* @website https://docs.auauz.net
* @description /
* @author wk
* @date 2020-05-15
**/
@Entity
@Data
@Table(name="acti_msg_sms_record")
public class ActiMsgSmsRecord implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "id")
    private Integer id;

    @Column(name = "activity_id")
    @ApiModelProperty(value = "activityId")
    private Integer activityId;

    @Column(name = "content")
    @ApiModelProperty(value = "content")
    private String content;

    @Column(name = "course_type")
    @ApiModelProperty(value = "courseType")
    private Integer courseType;

    @Column(name = "create_time",nullable = false)
    @NotNull
    @ApiModelProperty(value = "createTime")
    private Timestamp createTime;

    @Column(name = "del")
    @ApiModelProperty(value = "del")
    private Integer del;

    @Column(name = "description")
    @ApiModelProperty(value = "description")
    private String description;

    @Column(name = "title")
    @ApiModelProperty(value = "title")
    private String title;

    public void copy(ActiMsgSmsRecord source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}