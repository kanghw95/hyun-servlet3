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
	{   Object memberObj = session.getAttribute("MEMBER");
	  if (memberObj == null)
      return "redirect:/app/loginForm";
		return "Aritelces/addform";
	}
	
	
	@PostMapping("/Aritelces/good")
	public String handleStep3(Article article, HttpSession session)
	{
		Object memberObj = session.getAttribute("MEMBER");
		if (memberObj == null)
				return "redirect:/app/login/loginForm";
		Member member = (Member) memberObj;
		article.setUserId(member.getMemberId());    
      article.setName(member.getName()); 
      
		try {
			ArticleDao.insert(article);
			logger.debug("회원 정보를 저장 {}", article);
			return "Aritelces/good";
		} catch (DuplicateKeyException e) {
			return "Aritelces/addform";
		}
	}
	
}

	