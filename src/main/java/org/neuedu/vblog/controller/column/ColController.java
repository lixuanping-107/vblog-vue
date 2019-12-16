package org.neuedu.vblog.controller.column;

import org.apache.ibatis.annotations.Param;
import org.neuedu.vblog.model.Column;
import org.neuedu.vblog.model.RespBean;
import org.neuedu.vblog.service.ColService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ColController {
    @Autowired
    ColService colService;
    @GetMapping("/getAllColumn")
    public List<Column> getAllColumn(){
      return colService.getAllColumn();
    }
    @PostMapping("/addColumn")
    public RespBean addColumn(@RequestBody Column column){
        return colService.addColumn(column);
    }
    @PutMapping("/updateColumn")
    public RespBean updateColumn(@RequestBody Column column){
        return colService.updateColumn(column);
    }
    @DeleteMapping("/{id}")
    public RespBean delColumn(@PathVariable Integer id){
        return colService.delColumn(id);
    }
    @DeleteMapping("/delManyColumn")
    public RespBean delManyColumn(Integer[] ids){
        return colService.delManyColumn(ids);
    }
    @GetMapping("/getColumnData")
    public List<Column> getColumnData(){
        return colService.getColumnData();
    }
}
