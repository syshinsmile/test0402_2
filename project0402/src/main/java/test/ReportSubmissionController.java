package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class ReportSubmissionController {
	
	@Autowired
	private ReportService reportService;
	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	@RequestMapping(value="/submission.do",method=RequestMethod.GET)
	public String form() {return "report/submissionForm";}	
	
	
	@RequestMapping(value="/submitReport1.do",method=RequestMethod.POST)
	public String submitReport1(@RequestParam("studentNumber") String studentNumber,@RequestParam("report") MultipartFile report) {
		printInfo(studentNumber,report);
		reportService.upload(report);
		return "report/submissionComplete";
	}
	private void printInfo(String studentNumber,MultipartFile report) {
		System.out.println(studentNumber + "가 업로드 한 파일: "+report.getOriginalFilename());
	}
	@RequestMapping(value="/submitReport2.do", method=RequestMethod.POST)
	public String submitReport2(MultipartHttpServletRequest request) {
		String studentNumber = request.getParameter("studentNumber");
		MultipartFile report = request.getFile("report");
		printInfo(studentNumber,report);
		reportService.upload(report);
		return "report/submissionComplete";
	}

	@RequestMapping(value="/submitReport3.do", method=RequestMethod.POST)
	public String submitReport3(ReportCommand r) {
		reportService.upload(r.getReport());
		return "report/submissionComplete";
	}  // 밀리타임 + 이메일 @ 앞부분 더해서 파일 만드는 로직 추가하여 등록 예정.
		// 호출 하는 것도 테스트 해보고 매칭에 적용 할 것
}
