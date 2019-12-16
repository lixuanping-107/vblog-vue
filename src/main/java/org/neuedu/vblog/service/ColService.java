package org.neuedu.vblog.service;

import org.neuedu.vblog.mapper.ColMapper;
import org.neuedu.vblog.model.Column;
import org.neuedu.vblog.model.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColService {
   @Autowired
    ColMapper colMapper;
    public List<Column> getAllColumn() {
        return colMapper.getAllColumn();
    }

    public RespBean addColumn(Column column) {
        Integer i=colMapper.addColumn(column);
        if (i==0){
            return RespBean.error("添加失败");
        }else {
            return RespBean.ok("添加成功");
        }
    }

    public RespBean updateColumn(Column column) {
        Integer i = colMapper.updateColumn(column);
        if (i == 0) {
            return RespBean.error("更新失败");
        } else {
            return RespBean.ok("更新成功");
        }
    }

    public RespBean delColumn(Integer id) {
        Integer i = colMapper.delColumn(id);
        if (i == 0) {
            return RespBean.error("删除失败");
        } else {
            return RespBean.ok("删除成功");
        }
    }

    public RespBean delManyColumn(Integer[] ids) {
        Integer i = colMapper.delManyColumn(ids);
        if (i == 0) {
            return RespBean.error("批量删除失败");
        } else {
            return RespBean.ok("批量删除成功");
        }
    }

    public List<Column> getColumnData() {
        return colMapper.getColumnData();
    }
}
