package org.hyun.letter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class LetterDao {
//편지조회
	static final String LIST_LETTER = "select letterId, title, senderId, senderName, receiverId, receiverName,cdate from letter where letterId=? and (senderId=? or receiverId=?)";
//보내목록
	static final String GET_LETTER = "select letterId, title, content, senderId, senderName, cdate, where senderId=?";
//받은목록
	static final String VIEW_LETTER = "select letterId, title, content, receiverId, receiverName, from letter where receiverId=?";
//편지저장
	static final String SAVE_LETTER = "insert letter(title,content,senderId,senderName,receiverId,receiverName) values(?,?,?,?,?,?)";
//편지삭제
	static final String DELETE_LETTER = "delete from letter where letterId=? and (senderId=? or userId =?) ";
//편지쓰기
	//static final String ADD_LETTER = "insert letter(title,content,senderId,senderName,receiverId,receiverName) values(?,?,?,?,?,?)";
	@Autowired
	JdbcTemplate jdbcTemplate;

	RowMapper<Letter> letterRowMapper = new BeanPropertyRowMapper<>(
			Letter.class);

	/**
	 * 편지 조회
	 */
	public Letter listletter(String senderId) {
		return jdbcTemplate.queryForObject(LIST_LETTER, letterRowMapper,
				senderId);
}
	/**
	 * 편지 저장
	 */
	public int saveletter(Letter letter) {
		return jdbcTemplate.update(SAVE_LETTER,letter.getTitle(),
				letter.getContent(), letter.getsenderId(), letter.getsenderName(),letter.getreceiverId(), letter.getreceiverName());
}
	
	/**
	 * 편지 삭제
	 */
	public int deleteletter(String letterId, String senderId) {
		return jdbcTemplate.update(DELETE_LETTER, letterId, senderId);
}
	
	/**
	 * 편지 쓰기
	 */
	//public int addLetter(Letter letter) {
		//return jdbcTemplate.update(ADD_LETTER, letter.getTitle(),
			//	letter.getContent(), letter.getsenderId(), letter.getsenderName(),letter.getreceiverId(), letter.getreceiverName());
//}
	
}
