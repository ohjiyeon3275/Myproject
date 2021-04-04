package kr.co.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.dao.ReplyDAO;
import kr.co.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Inject
	private ReplyDAO replyDao;
	
	@Override
	public List<ReplyVO> readReply(int bno) throws Exception {
		
		return replyDao.readReply(bno);
	}

	@Override
	public void writeReply(ReplyVO replyVo) throws Exception {	
		replyDao.writeReply(replyVo);
	}

}
