package todo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import todo.dto.TodoDto;

public class TodoDao {
	private static String dburl = "jdbc:mysql://localhost:3306/connectdb";
	private static String dbUser = "connectuser";
	private static String dbpasswd = "connect123!@#";
	
	public int addTodo(TodoDto todo) {
		int insertCount = 0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql = "INSERT INTO todo(title, name, sequence) values(?, ?, ?)";
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, todo.getTitle());
			ps.setString(2, todo.getName());
			ps.setInt(3, todo.getSequence());
			
			insertCount = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return insertCount;
	}
	
	public List<TodoDto> getTodos(String typeState) {
		List<TodoDto> toDoList = new ArrayList<TodoDto>();
		List<TodoDto> doingList = new ArrayList<TodoDto>();
		List<TodoDto> doneList = new ArrayList<TodoDto>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String sql = "SELECT id, title, name, sequence, type, date_format(regdate, '%Y.%m.%d') AS regdate FROM todo order by regdate desc";
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {
			
			try (ResultSet rs = ps.executeQuery()) {
				while(rs.next()) {
					long id = rs.getLong("id");
					String title = rs.getString("title");
					String name = rs.getString("name");
					int sequence = rs.getInt("sequence");
					String type = rs.getString("type");
					String regdate = rs.getString("regdate");
					TodoDto todo = new TodoDto(id, name, regdate, sequence, title, type);
					
					if(type.equals(typeState)) {
						
						if(typeState.equals("TODO")) {
							toDoList.add(todo);
						}
						else if(typeState.equals("DOING")) {
							doingList.add(todo);
						}
						else if(typeState.equals("DONE")) {
							doneList.add(todo);	
						}
					} 
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		if(typeState.equals("TODO")) {
			return toDoList;
		}
		else if(typeState.equals("DOING")) {
			return doingList;
		}
		else if(typeState.equals("DONE")) {
			return doneList;	
		}
		return null;
	}
	
	public int updateTodo(TodoDto todo) {
		int updateCount = 0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql = "UPDATE todo SET type=? where id=?";
		try(Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, todo.getType());
			ps.setLong(2, todo.getId());
			
			updateCount = ps.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return updateCount;
	}
}
