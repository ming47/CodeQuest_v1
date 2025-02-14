package DAO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import DTO.MemberDTO;

public class MemberDAO {

	List<MemberDTO> mbList = new ArrayList<MemberDTO>();

	private MemberDAO() {
		// 생성자에서 예외가 발생하는지 확인
		try {
			// DB 연결 등의 코드
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static MemberDAO instance;

	public static MemberDAO getInstance() {
		if (instance == null) {
			try {
				instance = new MemberDAO();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	public Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/orcl");
		return ds.getConnection();
	}

	public void addList(MemberDTO m) throws Exception {
		mbList.add(m);
	}
	public List<MemberDTO> getList() throws Exception {
		return mbList;
	}
	
}
