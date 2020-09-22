package com.wipro.candidate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.wipro.candidate.bean.CandidateBean;
import com.wipro.candidate.util.DBUtil;

public class CandidateDAO {
	public String addCandidate(CandidateBean candidateBean) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into candidate_tbl values(?,?,?,?,?,?,?)";
		try {
			conn = DBUtil.getDBConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, candidateBean.getId());
			pstmt.setString(2,candidateBean.getName());
			pstmt.setInt(3, candidateBean.getM1());
			pstmt.setInt(4, candidateBean.getM2());
			pstmt.setInt(5, candidateBean.getM3());
			pstmt.setString(6, candidateBean.getResult());
			pstmt.setString(7, candidateBean.getGrade());
			
			int n = pstmt.executeUpdate();
			if( n == 1 )
				return "SUCCESS";
			else
				return "FAIL";
		}catch(SQLException e) {
			e.printStackTrace();
			return "FAIL";
		}
	}
	public ArrayList<CandidateBean> getByResult(String criteria){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select * from candidate_tbl where result = ?";
		String sql1 = "select * from candidate_tbl";
		try {
			conn = DBUtil.getDBConn();
			if( criteria.equals("PASS")) {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,criteria);
				ResultSet rs = pstmt.executeQuery();
				ArrayList<CandidateBean> list = new ArrayList<CandidateBean>();
				while(rs.next()) {
					CandidateBean cb = new CandidateBean();
					cb.setId(rs.getString(1));
					cb.setName(rs.getString(2));
					cb.setM1(rs.getInt(3));
					cb.setM2(rs.getInt(4));
					cb.setM3(rs.getInt(5));
					cb.setResult(rs.getString(6));
					cb.setGrade(rs.getString(7));
					list.add(cb);
				}
				return list;
			}else if( criteria.equals("FAIL")) {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,criteria);
				ResultSet rs = pstmt.executeQuery();
				ArrayList<CandidateBean> list = new ArrayList<CandidateBean>();
				while(rs.next()) {
					CandidateBean cb = new CandidateBean();
					cb.setId(rs.getString(1));
					cb.setName(rs.getString(2));
					cb.setM1(rs.getInt(3));
					cb.setM2(rs.getInt(4));
					cb.setM3(rs.getInt(5));
					cb.setResult(rs.getString(6));
					cb.setGrade(rs.getString(7));
					list.add(cb);
				}
				return list;
			}else if( criteria.equals("ALL")) {
				pstmt = conn.prepareStatement(sql1);
				pstmt.setString(1,criteria);
				ResultSet rs = pstmt.executeQuery();
				ArrayList<CandidateBean> list = new ArrayList<CandidateBean>();
				while(rs.next()) {
					CandidateBean cb = new CandidateBean();
					cb.setId(rs.getString(1));
					cb.setName(rs.getString(2));
					cb.setM1(rs.getInt(3));
					cb.setM2(rs.getInt(4));
					cb.setM3(rs.getInt(5));
					cb.setResult(rs.getString(6));
					cb.setGrade(rs.getString(7));
					list.add(cb);
				}
				return list;
			}else {
				return null;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public String generateCandidateId(String name) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT CANDID_SQL.NEXTVAL FROM DUAL";
		int SEQ_STUD_ID = 0;
		String out = "";
		
		try {
			conn = DBUtil.getDBConn();
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			SEQ_STUD_ID = rs.getInt(1);
			
			out += name.substring(0, 2);
			out.toUpperCase();
			out += SEQ_STUD_ID;
			
			return out;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
