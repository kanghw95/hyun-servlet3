package org.hyun.article;


import java.util.List;

public interface ArticleDao {


	

	
	Article getArticle(String articleId);
	
	
	int countAll();

    List<Article> selectAll(int offset, int COUNT);
		

	void insert(Article article);


	void addArticle(Article article);

}