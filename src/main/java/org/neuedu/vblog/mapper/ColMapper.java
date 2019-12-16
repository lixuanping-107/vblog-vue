package org.neuedu.vblog.mapper;

import org.apache.ibatis.annotations.Param;
import org.neuedu.vblog.model.Column;

import java.util.List;

public interface ColMapper {
    List<Column> getAllColumn();

    Integer addColumn(Column column);

    Integer updateColumn(Column column);

    Integer delColumn(Integer id);

    Integer delManyColumn(@Param("ids") Integer[] ids);

    List<Column> getColumnData();
}
