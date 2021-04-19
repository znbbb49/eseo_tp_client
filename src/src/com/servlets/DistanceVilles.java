package src.com.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


import src.com.beans.Ville;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class Accueil
 */
@WebServlet("/DistanceVilles")
public class DistanceVilles extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DistanceVilles() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
    	List <Ville> villes = this.doGetVilles(request, response);
    	System.out.print(villes.get(2).getNomCommune());
    	request.setAttribute("ville", villes);
    	this.getServletContext().getRequestDispatcher("/WEB-INF/distance.jsp").forward(request, response);
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
		
		HttpSession session = request.getSession();
		List<Ville> villes = this.doGetVilles(request, response);
		String ville1 = request.getParameter("ville1");
		String ville2 = request.getParameter("ville2");
		System.out.println(ville1);
		System.out.println(ville2);
		session.setAttribute("ville1", ville1);
		session.setAttribute("ville2", ville2);

		String latitude1 = null;
		String longitude1 = null;
		String latitude2 = null;
		String longitude2 = null;

		for (Ville ville : villes) {
			if (ville.getNomCommune().equals(ville1)) {
				latitude1 = ville.getLatitude();
				longitude1 = ville.getLongitude();
			}
			if (ville.getNomCommune().equals(ville2)) {
				latitude2 = ville.getLatitude();
				longitude2 = ville.getLongitude();
			}
			
		}
		System.out.println(longitude1);
		System.out.println(latitude1);
		System.out.println(longitude2);
		System.out.println(latitude2);
		double distance = this.calculDistance(latitude1, longitude1, latitude2, longitude2) ;

		request.setAttribute("distance", distance);
		this.doGet(request, response);
		}
	
	public double calculDistance(String latitude1, String longitude1, String latitude2, String longitude2) {
		int earth_radius = 6371; 
		double b2 = Double.parseDouble(latitude1);
		double c2 = Double.parseDouble(longitude1);

		double b3 = Double.parseDouble(latitude2);
		double c3 = Double.parseDouble(longitude2);
		
		double distance = Math.acos(Math.sin(Math.toRadians(b2)) * Math.sin(Math.toRadians(b3))
				+ Math.cos(Math.toRadians(b2)) * Math.cos(Math.toRadians(b3)) * Math.cos(Math.toRadians(c2 - c3)))
				* earth_radius;
		return distance ;
	}

		
	}