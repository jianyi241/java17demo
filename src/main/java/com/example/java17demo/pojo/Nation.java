package com.example.java17demo.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Nation {

    // 国家ID
    private Integer id;

    // 国家名称
    private String nationName;

    // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date creationTime;

    // 更新时间
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updateTime;
}
