package org.hyun.letter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class LetterDao {

	static final String LIST_SEDER = "select letterId,title,receiverId,receiverName,cdate from letter where senderId=?";
	static final String LIST_RECEIVER = "select letterId,title,senderId,senderName,cdate from letter where receiverId=?";
	static final String ADD_LETTER = "insert letter(title,content,senderId,senderName,receiverId,receiverName) VALUES(?,?,?,?,?,?)";
	static final String GET_LETTER = "select letterId,title,content,senderId,senderName,receiverId,receiverName,cdate from letter where letterId=? and (senderId=? or receiverId=?)";
	static final String DELETE_LETTER = "delete from letter where letterId=? and (senderId=? or receiverId=?)";

	@Autowired
	JdbcTemplate jdbcTemplate;

	RowMapper<Letter> letterRowMapper = new BeanPropertyRowMapper<>(
			Letter.class);

	/**
	 * 보낸 목록
	 */
	public List<Letter> listOfSender(String senderId) {
		return jdbcTemplate.query(LIST_SEDER, letterRowMapper,
				senderId);
	}

	/**
	 * 받은 목록
	 */
	public List<Letter> listOfReceiver(String receiverId) {
		return jdbcTemplate.query(LIST_RECEIVER, letterRowMapper,
				receiverId);
	}

	/**
	 * 조회
	 */
	public Letter getLetter(String letterId, String memberId) {
		return jdbcTemplate.queryForObject(GET_LETTER, letterRowMapper,
				letterId, memberId, memberId);
	}

	/**
	 * 추가
	 */
	public int add(Letter letter) {
		return jdbcTemplate.update(ADD_LETTER, letter.getTitle(),
				letter.getContent(), letter.getSenderId(),
				letter.getSenderName(), letter.getReceiverId(),
				letter.getReceiverName());
	}

	/**
	 * 삭제
	 */
	public int deleteLetter(String letterId, String memberId) {
		return jdbcTemplate.update(DELETE_LETTER, letterId, memberId, memberId);
	}

}