package org.neuedu.vblog.mapper;

import org.apache.ibatis.annotations.Param;
import org.neuedu.vblog.model.Article;
import org.neuedu.vblog.model.ArticlePageBean;
import org.neuedu.vblog.model.RespBean;

import java.util.List;

public interface ArticleMapper {
    List<Article> getAllArticles(ArticlePageBean articlePageBean);

    Integer delMangArticle(@Param("ids") Integer[] ids);

    Integer addArticle(Article article);


    Article getArtByAid(Integer aid);

    Integer updateArtById(Integer id);

    Integer delArtById(Integer id);

    Integer updateArtContent(Article article);

    Integer updateViewsById(Integer id);
}
