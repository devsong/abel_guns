package com.stylefeng.guns.common.persistence.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liuzh
 * @since 2017/7/26.
 */
@Data
public class Base implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "JDBC")
    protected Integer id;
}
