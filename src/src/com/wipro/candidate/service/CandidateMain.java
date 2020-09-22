package com.wipro.candidate.service;

import java.util.ArrayList;

import com.wipro.candidate.bean.CandidateBean;
import com.wipro.candidate.dao.CandidateDAO;
import com.wipro.candidate.util.WrongDataException;

public class CandidateMain {
	public static void main(String[] args) {
		CandidateMain canMain = new CandidateMain();
		String result = canMain.addCandidate(null);
		System.out.println(result);
	}
	public String addCandidate(CandidateBean candidateBean) {
		try {
			if(candidateBean == null )
				throw new WrongDataException();
			else if(candidateBean.getName().equals(""))
				throw new WrongDataException();
			else if(candidateBean.getName().length()<2)
				throw new WrongDataException();
			else if(candidateBean.getM1() < 0 || candidateBean.getM1() > 100)
				throw new WrongDataException();
			else if(candidateBean.getM2() < 0 || candidateBean.getM2() > 100)
				throw new WrongDataException();
			else if(candidateBean.getM3() < 0 || candidateBean.getM3() > 100)
				throw new WrongDataException();
			
			CandidateDAO dao = new CandidateDAO();
			String id = dao.generateCandidateId(candidateBean.getName());
			candidateBean.setId(id);
			
			int m1 = candidateBean.getM1();
			int m2 = candidateBean.getM2();
			int m3 = candidateBean.getM3();
			int total = (m1 + m2 + m3);
			if( total >= 240 ) {
				candidateBean.setResult("PASS");
				candidateBean.setGrade("Distinction");
			}else if( total >= 180 && total < 240 ) {
				candidateBean.setResult("PASS");
				candidateBean.setGrade("First Class");
			}else if( total >= 150 && total < 180 ) {
				candidateBean.setResult("PASS");
				candidateBean.setGrade("Second Class");
			}else if( total >= 105 && total < 150 ) {
				candidateBean.setResult("PASS");
				candidateBean.setGrade("Third Class");
			}else if( total < 105 ) {
				candidateBean.setResult("FAIL");
				candidateBean.setGrade("No Grade");
			}
			
			String store = dao.addCandidate(candidateBean);
			if(store.equals("SUCCESS")) {
				return candidateBean.getId() + ":" + candidateBean.getResult();
			}else
				return "Error";
			
		}catch(WrongDataException e) {
			return candidateBean.toString();
		}
	}
	public ArrayList<CandidateBean> displayAll(String criteria){
		CandidateDAO dao = new CandidateDAO();
		if( criteria.equals("PASS")) {
			return dao.getByResult(criteria);
		}else if( criteria.equals("FAIL")) {
			return dao.getByResult(criteria);
		}else if( criteria.equals("ALL")) {
			return dao.getByResult(criteria);
		}else {
			try {
				throw new WrongDataException();
			}catch(WrongDataException e) {
				return null;
			}
		}
	}
}
