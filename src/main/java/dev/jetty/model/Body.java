package dev.jetty.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Body {
    private int status;
    private int code;
    private String message;
    private List<Topic> data;
    private Topic detail;
}
