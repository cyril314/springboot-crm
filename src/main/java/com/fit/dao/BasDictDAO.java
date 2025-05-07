package com.fit.dao;

import com.fit.base.BaseDAO;
import com.fit.entity.BasDict;
import org.springframework.stereotype.Repository;

/**
 * @AUTO
 * @Author AIM
 * @DATE 2025/5/6
 */
@Repository
public class BasDictDAO extends BaseDAO<BasDict, Long> {

    private static final String DICT_TYPE = "dictType";
    private static final String DICT_ITEM = "dictItem";
    private static final String DICT_VALUE = "dictValue";
    private static final String DICT_IS_EDITABLE = "dictIsEditable";

}
