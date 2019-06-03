package org.hyun.letter;

import java.util.List;

import org.hyun.article.Article;
import org.hyun.chap11.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class LetterController {

	@Autowired
	LetterDao letterDao;

	/**
	 * 받은 목록
	 * @return 
	 */
	@GetMapping("/letter/listOfReceiver")
	public String listOfReceiver(@SessionAttribute("MEMBER") Member member,
			Model model) {
		List<Letter> listOfReceiver = letterDao
				.listOfReceiver(member.getMemberId());
		model.addAttribute("listOfReceiver", listOfReceiver);
		return "letter/listOfReceiver";
	}

	/**
	 * 보낸 목록
	 * @return 
	 */
	@GetMapping("/letter/listOfSender")
	public String listOfSender(@SessionAttribute("MEMBER") Member member,
			Model model) {
		List<Letter> listOfSender = letterDao
				.listOfSender(member.getMemberId());
		model.addAttribute("listOfSender", listOfSender);
		return "letter/listOfSender";
	}

	/**
	 * 보기
	 * @return 
	 */
	@GetMapping("/letter/view")
	public void view(@RequestParam("letterId") String letterId,
			@SessionAttribute("MEMBER") Member member, Model model) {
		Letter letter = letterDao.getLetter(letterId, member.getMemberId());
		model.addAttribute("LETTER", letter);
	}

	/**
	 * 편지 저장
	 */
	@PostMapping("/letter/add")
	public String add(Letter letter,
			@SessionAttribute("MEMBER") Member member) {
		letter.setSenderId(member.getMemberId());
		letter.setSenderName(member.getName());
		letterDao.add(letter);
		return "redirect:/app/letter/listOfSender";
	}

	/**
	 * 편지 삭제
	 */
	@GetMapping("/letter/delete")
	public String delete(
			@RequestParam(value = "mode", required = false) String mode,
			@RequestParam("letterId") String letterId,
			@SessionAttribute("MEMBER") Member member) {
		int updatedRows = letterDao.deleteLetter(letterId,
				member.getMemberId());
		if (updatedRows == 0)
			// 자신의 편지가 아닐 경우 삭제되지 않음
			throw new RuntimeException("No Authority!");

		if ("SENT".equals(mode))
			return "redirect:/app/letter/listOfSender";
		else
			return "redirect:/app/letter/listOfReceiver";
}
}