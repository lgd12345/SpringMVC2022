package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Test2Service;

// .mvc로 끝나는 모든 요청은 여기서 처리하겠다., 밑에는 시리얼 넘버 적음
@WebServlet("*.mvc")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 요청한 주소를 가져온다.
		String url = request.getRequestURI();
//		System.out.println(url);
		String viewName = "";

		if (url.contains("main.mvc")) {
			System.out.println("main 요청");
			viewName = "main.jsp";

		} else if (url.contains("test1.mvc")) {
			System.out.println("test1 요청");
			// 파라미터 데이터 추출
			String str1 = request.getParameter("data1");
			String str2 = request.getParameter("data2");
			// 파라미터 값을 숫자로 변환
			int number1 = Integer.parseInt(str1);
			int number2 = Integer.parseInt(str2);

			int result = number1 + number2;

			request.setAttribute("result", result); // 리퀘스트객체에 데이터 담기

			viewName = "test1.jsp";

		} else if (url.contains("test2.mvc")) {
			System.out.println("test2 요청");
			// 모델 요청
			int result = Test2Service.minus(request); // 서비스 불러온다.

			request.setAttribute("result", result); // 리퀘스트객체에 데이터 담기
			
			viewName = "test2.jsp";
		}

		// 각각의 페이지로 이동
		// 요청 주소를 체크해서 포워드(request를 유지) 한다.
		RequestDispatcher dis = request.getRequestDispatcher(viewName);
		dis.forward(request, response);

	}

}
