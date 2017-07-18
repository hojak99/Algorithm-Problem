package hojak99.sample.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// @Controller ������̼��� ������ �����ӿ�ũ�� ���� Ŭ������ ��Ʈ�ѷ� ��� ���� ������ش�.
@Controller
public class SampleController {
	Logger log = Logger.getLogger(this.getClass());
	
	// @RequestMapping ������̼����� DispatcherServlet �� �� ������̼��� �������� �
	// ��Ʈ�ѷ��� �޼ҵ带 ȣ��Ǿ�� ������ �����Ѵ�.
	@RequestMapping(value="/sample/openSampleList.do")
	public ModelAndView openSampleList(Map<String, Object> commandMap) throws Exception {
		
		// new ModelAndView("") ���� ������ �κ��� �� ��Ʈ�ѷ��� ����ǰ� ���� ������ view �� ������ �� ����
		ModelAndView modelAndView = new ModelAndView("");
		log.debug("���ͼ�Ʈ �׽�Ʈ");
		
		return modelAndView;
	}
}
