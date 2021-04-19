package src.com.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import src.com.beans.Ville;

/**
 * Servlet implementation class ModifierVille
 */
@WebServlet("/ModifierVille")
public class ModifierVille extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierVille() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("ville");
		request.setAttribute("name", name);
		this.getServletContext().getRequestDispatcher("/WEB-INF/modifierVille.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String codeCommune = request.getParameter("codeCommune");
		String nom = request.getParameter("nomCommune");
		String codePostal = request.getParameter("codePostal");
		String libelle = request.getParameter("libelleAcheminement");
		String ligne = request.getParameter("ligne");
		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");
		
		
		Ville villeToChange = new Ville() ;
		villeToChange.setCodeCommune(codeCommune);
		villeToChange.setNomCommune(nom);
		villeToChange.setCodePostal(codePostal);
		villeToChange.setLibelleAcheminement(libelle);
		if (ligne == null) {
			villeToChange.setLigne("");
		} else {
			villeToChange.setLigne(ligne);
		}
		villeToChange.setLongitude(longitude);
		villeToChange.setLatitude(latitude);

		Gson gson = new Gson();
		String villeJSon = gson.toJson(villeToChange);
		System.out.println(villeJSon);
		String js = "{\r\n"
				+ "        \"codeCommune\": \""+ codeCommune+ "\" , "
				+ "        \"nomCommune\": \""+ nom+ "\" , "
				+ "        \"codePostal\": \"" + codePostal+ "\" , "
				+ "        \"lattitute\": \""+ latitude+ "\" , "
				+ "        \"longitude\": \""+ longitude+ "\" , "
				+ "        \"libelleAcheminement\": \"" + libelle+ "\" , "
				+ "        \"ligne\": \""+ ligne+ "\" , "
				+ "    } " ;
		
		
		
		try {

			URL url = new URL("http://127.0.0.1:9007/ville/update");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-Type","application/json");
			conn.setDoOutput(true);
			
			try(OutputStream os = conn.getOutputStream()) {
			    byte[] input = villeJSon.getBytes("utf-8");
			    os.write(input, 0, input.length);			
			}
			
			
						
			try(BufferedReader br = new BufferedReader(
					  new InputStreamReader(conn.getInputStream(), "utf-8"))) {
					    StringBuilder responsee = new StringBuilder();
					    String responseLine = null;
					    while ((responseLine = br.readLine()) != null) {
					        responsee.append(responseLine.trim());
					    }
					    System.out.println(response.toString());
					}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		
		doGet(request, response);
	}

}
