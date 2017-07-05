package servlets;

import clojush.problems.software.tcdg;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProblemServlet
 */
@WebServlet("/ProblemServlet")
public class ProblemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProblemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String problem = request.getParameter("problemType");
		Integer reps = new Integer(request.getParameter("reps"));
		String fileType=request.getParameter("fileType");
		String pathName = "/home/students/joshir/2017_Research/workspace/GpWebpage/WebContent/generated-data/";
		PrintWriter output = response.getWriter();
		String docType = "<!DOCTYPE html>";
		String html = "<html>\n";
		output.println(docType);
		output.println(html);
		output.println("<head><title>");
		output.println("Your Request");
		output.println("</title></head>");
		output.println("<body><h1>Your Request</h1>");
		output.println("Problem Type: "+problem+"<br/>");
		output.println("Number of Data Sets: "+reps+"<br/>");
		output.println("File Type: "+fileType);
				
		try {
			//Calls on Clojure code to generate data files
			String zip_location = tcdg.generate_data_files(problem, reps, fileType,pathName);

			output.println(zip_location+"<br>");
			//Download Link
			output.println("<a href='generated-data/"+zip_location+"' download>download file</a>");

		}
		catch (Throwable e) {
			output.println(e.getMessage());
		}
		
		output.println("<a href='generated-data/test.txt' download>download test file</a>");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
