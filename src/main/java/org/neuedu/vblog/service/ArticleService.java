package org.neuedu.vblog.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.neuedu.vblog.mapper.ArticleMapper;
import org.neuedu.vblog.model.Article;
import org.neuedu.vblog.model.ArticlePageBean;
import org.neuedu.vblog.model.RespBean;
import org.neuedu.vblog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import sun.plugin.liveconnect.SecurityContextHelper;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleMapper articleMapper;
    public PageInfo getAllArticles(ArticlePageBean articlePageBean) {
        if (articlePageBean.getStatus()!=-1){
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            articlePageBean.setUid(user.getId());
        }
        PageHelper.startPage(articlePageBean.getPageNum(),articlePageBean.getPageSize());
        List<Article> allArticles = articleMapper.getAllArticles(articlePageBean);
        PageInfo pageInfo = new PageInfo(allArticles);
        return pageInfo;
    }

    public RespBean delMangArticle(Integer[] ids) {
        Integer i= articleMapper.delMangArticle(ids);
        if (i == 0) {
            return RespBean.error("批量删除失败");
        } else {
            return RespBean.ok("批量删除成功");
        }
    }

    public RespBean addArticle(Article article) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        article.setUid(user.getId());
        Integer i = articleMapper.addArticle(article);
        if (i == 0) {
            return RespBean.error("发布文章失败");
        } else {
            return RespBean.ok("发布文章成功");
        }
    }

    public Article getArtByAid(Integer aid) {
        return articleMapper.getArtByAid(aid);
    }

    public RespBean updateArtById(Integer id) {
        Integer i=articleMapper.updateArtById(id);
        if (i == 0) {
            return RespBean.error("文章进入回收站失败");
        } else {
            return RespBean.ok("文章进入回收站成功");
        }
    }

    public RespBean delArtById(Integer id) {
        Integer i =articleMapper.delArtById(id);
        if (i == 0) {
            return RespBean.error("文章删除失败");
        } else {
            return RespBean.ok("文章删除成功");
        }
    }

    public RespBean updateArtContent(Article article) {
        Integer i =articleMapper.updateArtContent(article);
        if (i == 0) {
            return RespBean.error("编辑文章内容失败");
        } else {
            return RespBean.ok("编辑文章内容成功");
        }
    }

    public RespBean updateViewsById(Integer id) {
        Integer i =articleMapper.updateViewsById(id);
        if (i == 0) {
            return RespBean.error("浏览量修改失败");
        } else {
            return RespBean.ok("浏览量修改成功");
        }
    }
}
