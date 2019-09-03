package todo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.dao.TodoDao;
import todo.dto.TodoDto;

/**
 * Servlet implementation class TodoTypeServlet
 */
@WebServlet("/todotype")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodoTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		TodoDto dto = new TodoDto();
		TodoDao dao = new TodoDao();
		
		String type = request.getParameter("currentType");
		String id = request.getParameter("currentId");
		
		if(type != null && id != null) {
			if(type.equals("TODO")) {
				type = "DOING";
			} else if(type.equals("DOING")) {
				type = "DONE";
			}
			dto.setType(type);
			dto.setId(Long.parseLong(id));
			dao.updateTodo(dto);
		}
		
		response.sendRedirect("/todo/main");
	}

}
