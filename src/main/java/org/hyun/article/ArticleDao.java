package org.hyun.article;


import java.util.List;

public interface ArticleDao {


	

	
	Article getArticle(String articleId);
	
	
	int countAll();

    List<Article> selectAll(int offset, int COUNT);
		

	void insertArticle(Article article);


	void addArticle(Article article);
	
	void upArticle(Article article);
	
	void deleteArticle(String articleId);

}