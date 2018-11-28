package dev.jetty.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class Topic {
    private String id;
    private String content;
    private String created_date;
    private String created_by;
    private String lastupdated_date;
    private String lastupdated_by;
}