package src.com.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import src.com.beans.Ville;

/**
 * Servlet implementation class AfficherVille
 */
@WebServlet("/AfficherVille")
public class AfficherVille extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AfficherVille() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String numero = request.getParameter("page");
		int page = numero != null ? Integer.parseInt(numero) : 1;
		session.setAttribute("villes", this.doGetVilles(request, response));

		List<Ville> villes = this.doGetVilles(request, response);
		villes = getElements(page, 50, villes);
		request.setAttribute("villesPage", villes);
		request.setAttribute("numPage", page);
		System.out.print(page);
		this.getServletContext().getRequestDispatcher("/WEB-INF/afficheVille.jsp").forward(request, response);
	}

	private List getElements(int start, int size, List list) {
		start--;
		List myList = new ArrayList();
		for (int i = start; i < start + size; i++) {
			if (i >= list.size()) {
				break;
			}
			myList.add(list.get(i));
		}

		return myList;
	}
		
		
		public List<Ville> doGetVilles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String result = "";
			try {

				URL url = new URL("http://127.0.0.1:9007/ville");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");
							
				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

				String output;

				System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					result += output;

				}

				conn.disconnect();

			} catch (MalformedURLException e) {

				e.printStackTrace();

			} catch (IOException e) {

				e.printStackTrace();

			}
			
			final Gson gson = new GsonBuilder().create();
			Type listType = new TypeToken<ArrayList<Ville>>(){}.getType();
			List<Ville> villes = gson.fromJson(result,listType);
			

			return villes ;
			
			
			// this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
			
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    
		doGet(request, response);
	}

}
