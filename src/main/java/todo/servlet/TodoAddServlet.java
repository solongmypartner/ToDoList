package todo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.dao.TodoDao;
import todo.dto.TodoDto;

/**
 * Servlet implementation class TodoAddServlet
 */
@WebServlet("/todoadd")
public class TodoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodoAddServlet() {
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
		
		String name = request.getParameter("name");
		String title = request.getParameter("title");
		String sequence = request.getParameter("sequence");
		
		if(name != null && title != null && sequence != null) {
			dto.setName(name);
			dto.setTitle(title);
			dto.setSequence(Integer.parseInt(sequence));
			dao.addTodo(dto);
		}
		
		List<TodoDto> toDoList = dao.getTodos("TODO");
		request.setAttribute("toDoList", toDoList);
		List<TodoDto> doingList = dao.getTodos("DOING");
		request.setAttribute("doingList", doingList);
		List<TodoDto> doneList = dao.getTodos("DONE");
		request.setAttribute("doneList", doneList);
		
		response.sendRedirect("/todo/main");
	}

}
