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
	public String handleStep1(@RequestParam(value = "articleId") String articleId, Model model) {
		Article article = ArticleDao.getArticle(articleId);

		model.addAttribute("article", article);

		return "Aritelces/view";
	}

	@GetMapping("/Articles")
	public String members(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {

		final int COUNT = 100;

		int offset = (page - 1) * COUNT;

		List<Article> AritcleList = ArticleDao.selectAll(offset, COUNT);

		int totalCount = ArticleDao.countAll();

		model.addAttribute("totalCount", totalCount);
		model.addAttribute("Articles", AritcleList);
		return "Articles";
	}

	@RequestMapping("/Aritelces/addform")
	public String handleStep2(HttpSession session) {
		return "Aritelces/addform";
	}

	@PostMapping("/Aritelces/add")
	public String articleAdd(Article article, @SessionAttribute("MEMBER") Member member) {
		article.setUserId(member.getMemberId());
		article.setName(member.getName());
		ArticleDao.addArticle(article);
		return "redirect:/app/Articles";
	}

	@GetMapping("/Aritelces/updp")
	public String updp(@RequestParam(value = "articleId") String articleId, @SessionAttribute("MEMBER") Member member,
			Model model) {
		Article article = ArticleDao.getArticle(articleId);
		if (!member.getMemberId().equals(article.getUserId()))
			return "redirect:/app/Aritelces/error?articleId=" + articleId;
		model.addAttribute("article", article);
		return "Aritelces/updp";
	}

	@PostMapping("/Aritelces/upArticle")
	public String upArticle(Article article, @RequestParam(value = "articleId") String articleId,
			@SessionAttribute("MEMBER") Member member) {
		article.setArticleId(articleId);
		try {
			ArticleDao.upArticle(article);
			return "redirect:/app/Aritelces/view?articleId=" + articleId;
		} catch (DuplicateKeyException e) {
			return "redirect:/app/Articles";
		}
	}

	@GetMapping("/Aritelces/deleteArticle")
	public String deleteArticle(@RequestParam(value = "articleId") String articleId,
			@SessionAttribute("MEMBER") Member member, Model model) {

		Article article = ArticleDao.getArticle(articleId);
		if (!member.getMemberId().equals(article.getUserId()))

			return "redirect:/app/Aritelces/view?articleId=" + articleId;
		try {
			ArticleDao.deleteArticle(articleId);
		} catch (DuplicateKeyException e) {
		}
		return "redirect:/app/Articles";
	}
	// 교수님꺼

	/**
	 * 글 수정 화면
	 */
	/*
	 * @GetMapping("/article/updateForm") public void
	 * updateForm(@RequestParam("articleId") String articleId,
	 * 
	 * @SessionAttribute("MEMBER") Member member, Model model) { Article article =
	 * articleDao.getArticle(articleId);
	 * 
	 * // 권한 체크 : 세션의 memberId와 글의 userId를 비교 if
	 * (!member.getMemberId().equals(article.getUserId())) // 자신의 글이 아니면 throw new
	 * RuntimeException("No Authority!");
	 * 
	 * model.addAttribute("article", article); }
	 */

	/**
	 * 글 수정
	 */
	/*
	 * @PostMapping("/article/update") public String update(Article article,
	 * 
	 * @SessionAttribute("MEMBER") Member member) {
	 * article.setUserId(member.getMemberId()); int updatedRows =
	 * articleDao.updateArticle(article);
	 * 
	 * // 권한 체크 : 글이 수정되었는지 확인 if (updatedRows == 0) // 글이 수정되지 않음. 자신이 쓴 글이 아님
	 * throw new RuntimeException("No Authority!");
	 * 
	 * return "redirect:/app/article/view?articleId=" + article.getArticleId(); }
	 */

	/**
	 * 글 삭제
	 */
	/*
	 * @GetMapping("/article/delete") public String
	 * delete(@RequestParam("articleId") String articleId,
	 * 
	 * @SessionAttribute("MEMBER") Member member) { int updatedRows =
	 * articleDao.deleteArticle(articleId, member.getMemberId());
	 * 
	 * // 권한 체크 : 글이 삭제되었는지 확인 if (updatedRows == 0) // 글이 삭제되지 않음. 자신이 쓴 글이 아님
	 * throw new RuntimeException("No Authority!");
	 * 
	 * logger.debug("글을 삭제했습니다. articleId={}", articleId); return
	 * "redirect:/app/article/list"; }
	 */

}
