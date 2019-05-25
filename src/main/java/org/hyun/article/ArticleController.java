package org.hyun.article;

import java.util.List;


import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hyun.chap11.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;


@Controller
public class ArticleController {

	@Autowired
    ArticleDao ArticleDao;

	static final Logger logger = LogManager.getLogger();


	
	@GetMapping("/Aritelces/view")
	public String handleStep1(@RequestParam(value = "articleId") String articleId,
			Model model)  {
	    Article article = ArticleDao.getArticle(articleId);
	    
	    model.addAttribute("article", article);
		
		return "Aritelces/view";
	}

	@GetMapping("/Articles")
	public String members(
			@RequestParam(value = "page", defaultValue = "1") int page,
			Model model) {

	
		final int COUNT = 100;
		
		int offset = (page - 1) * COUNT;

		List<Article> AritcleList = ArticleDao.selectAll(offset, COUNT);

		int totalCount = ArticleDao.countAll();

		model.addAttribute("totalCount", totalCount);
		model.addAttribute("Articles", AritcleList);
		return "Articles";
	}
	
	@RequestMapping("/Aritelces/addform")
	public String handleStep2(HttpSession session)
	{ 
     return "Aritelces/addform";
	}
	
	
	@PostMapping("/Aritelces/add")
	public String articleAdd(Article article,
			@SessionAttribute("MEMBER") Member member) {
		article.setUserId(member.getMemberId());
		article.setName(member.getName());
		ArticleDao.addArticle(article);
		return "redirect:/app/Articles";
}
	@GetMapping("/Aritelces/updp")
	public String updp(@RequestParam(value = "articleId") String articleId,
			@SessionAttribute("MEMBER") Member member
			,Model model)
	{
		Article article = ArticleDao.getArticle(articleId);
		if(!member.getMemberId().equals(article.getUserId()))
			return "redirect:/app/Aritelces/view?articleId="+articleId;
			model.addAttribute("article",article);
			return "Aritelces/updp";
}

	
	@PostMapping("/Aritelces/upArticle")
	public String upArticle(Article article,
			@RequestParam(value="articleId") String articleId, @SessionAttribute("MEMBER") Member member) 
{
		article.setArticleId(articleId);
		try {
			ArticleDao.upArticle(article);
			return "redirect:/app/Aritelces/view?articleId="+articleId;
		} catch (DuplicateKeyException e) {
			return "redirect:/app/Articles";
		}
}
	@GetMapping("/Aritelces/deleteArticle")
	public String deleteArticle(@RequestParam(value = "articleId") String articleId,
			@SessionAttribute("MEMBER") Member member
			,Model model)  {
		
		Article article = ArticleDao.getArticle(articleId);
		if(!member.getMemberId().equals(article.getUserId()))
		return "redirect:/app/Aritelces/view?articleId="+articleId;
		try {
			ArticleDao.deleteArticle(articleId);
		} catch (DuplicateKeyException e) {
		}
return "redirect:/app/Articles";
	}

	
}
	