package org.neuedu.vblog.controller;

import com.github.pagehelper.PageInfo;
import org.neuedu.vblog.mapper.ArticleMapper;
import org.neuedu.vblog.model.Article;
import org.neuedu.vblog.model.ArticlePageBean;
import org.neuedu.vblog.model.RespBean;
import org.neuedu.vblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArtilceController {
    @Autowired
    ArticleService articleService;
    @GetMapping("/getAllArticles")
    public PageInfo getAllArticles(ArticlePageBean articlePageBean){
        return articleService.getAllArticles(articlePageBean);
    }
    @DeleteMapping("/delMangArticle")
    public RespBean delMangArticle(Integer[] ids){
        return articleService.delMangArticle(ids);
    }
    @PostMapping("/addArticle")
    public RespBean addArticle(@RequestBody Article article){
        return articleService.addArticle(article);
    }
    @GetMapping("/getArtByAid")
    public Article getArtByAid(Integer aid){
        return articleService.getArtByAid(aid);
    }
    @PutMapping("/updateArtById")
    public RespBean updateArtById(Integer id){
        return articleService.updateArtById(id);
    }
    @DeleteMapping("/delArtById")
    public RespBean delArtById(Integer id){
        return articleService.delArtById(id);
    }
    @PutMapping("/updateArtContent")
    public RespBean updateArtContent(@RequestBody Article article){
        return articleService.updateArtContent(article);
    }
    @PutMapping("/updateViewsById")
    public RespBean updateViewsById(Integer id){
        return articleService.updateViewsById(id);
    }
}
