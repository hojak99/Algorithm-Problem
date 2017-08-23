package com.kjh.hojak.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kjh.hojak.service.BoardService;
import com.kjh.hojak.vo.BoardVO;

// ���� Ŭ������ ��Ʈ�ѷ� bean ���� ���
@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Inject
	BoardService boardService;
	
	// 01. �Խñ� ���
	@RequestMapping("list.do")
	public ModelAndView list() throws Exception {
		List<BoardVO> list = boardService.listAll();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("list");			// �並 list.jsp �� ����
		mav.addObject("list", list);			// ������ ����
		return mav;				// list.jsp �� list ����
	}
	
	// 02_01. �Խñ� �ۼ�ȭ��
	// @RequestMapping("board/writer.do")
	// value="", method="���۹��"
	@RequestMapping(value="write.do", method=RequestMethod.GET)
	public String write(){
		return "board/wrter";	// writer.jsp �� �̵�
	}
	
	// 02_02. �Խñ� �ۼ�ó��
	@RequestMapping (value="insert.do", method=RequestMethod.POST)
	public String insert(@ModelAttribute BoardVO vo) throws Exception{
		boardService.create(vo);
		return "redirect:list.do";
	}
	
	// 03. �Խñ� �󼼳��� ��ȸ, �Խñ� ��ȸ�� ����ó��
	// @RequestParam : get/post ������� ���޵� ���� 1��
	// HttpSession ���� ��ü
	@RequestMapping(value="view.do", method=RequestMethod.GET)
	public ModelAndView view(@RequestParam int bno, HttpSession session) throws Exception {
		// ��ȸ�� ���� ó��
		boardService.increaseViewCount(bno, session);
		
		// ��(������) + ��(ȭ��)�� �Բ� �����ϴ� ��ü
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/view");
		mav.addObject("dto", boardService.read(bno));
		return mav;
	}
	
	// 04. �Խñ� ����
	// ������ �Է��� ������� @ModelAttribute BoardVO vo �� ����
	@RequestMapping(value="update.do", method=RequestMethod.POST)
	public String update(@ModelAttribute BoardVO vo) throws Exception {
		boardService.update(vo);
		return "redirect:list.do";
	}
	
	// 05. �Խñ� ����
	@RequestMapping("delete.do")
	public String delete(@RequestParam int bno) throws Exception {
		boardService.delete(bno);
		return "redirect:list.do";
	}
}
