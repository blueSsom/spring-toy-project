package com.bluessom.toyapp.member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bluessom.toyapp.member.Member;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class MemberDao implements IMemberDao {
	
	private JdbcTemplate template;
	
	@Autowired
	public MemberDao(ComboPooledDataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	@Override
	public int memberInsert(Member member) {
		int result = 0;
		
		final String sql = "INSERT INTO MEMBER (memId, memPw) VALUES (?, ?)";
		
		result = template.update(sql, member.getMemId(), member.getMemPw());
		
		return result;
	}

	@Override
	public Member memberSelect(Member member) {
		
		final String sql = "SELECT * FROM MEMBER WHERE memId = ? AND memPw = ?";
		
		//Member selectedMem = template.queryForObject(sql, new Object[]{member.getMemId(), member.getMemPw()}, new MemberMapper());
		
		/*class MemberMapper implements RowMapper<Member> {
			
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Member mem = new Member();
				mem.setMemId(rs.getString("memId"));
				mem.setMemPw(rs.getString("memPw"));
				
				return mem;
			}
		}*/
		
		Member selectedMem = null;
		
		try {
			selectedMem = template.queryForObject(sql, new Object[]{member.getMemId(), member.getMemPw()}, new RowMapper<Member>() {
				
				public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
					Member mem = new Member();
					mem.setMemId(rs.getString("memId"));
					mem.setMemPw(rs.getString("memPw"));
					
					return mem;
				}
			});
		} catch (EmptyResultDataAccessException e) {
			// TODO: handle exception
			return null;
		}
		
		
		
			
		return selectedMem;
	}

}
