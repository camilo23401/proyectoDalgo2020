import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProblemaB {

	public static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));
	public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
	public static int costomin = 10000;
	
	public static Integer[] strToIntArr(String s) {
		List<Integer> list = new ArrayList<>();
		Arrays.asList(s.split(" ")).forEach(s1 -> list.add(Integer.parseInt(s1)));
		return list.toArray(new Integer[0]);
	}

	public static ArrayList<ArrayList<Integer>> permutacionesArreglo(Integer[] pArreglo) {
		ArrayList<ArrayList<Integer>> resultado = new ArrayList<ArrayList<Integer>>();
		resultado.add(new ArrayList<Integer>());
		for (int i = 0; i < pArreglo.length; i++) {

			ArrayList<ArrayList<Integer>> current = new ArrayList<ArrayList<Integer>>();

			for (ArrayList<Integer> r : resultado) {

				for (int j = 0; j < r.size()+1; j++) {

					r.add(j, pArreglo[i]);
					ArrayList<Integer> aux = new ArrayList<Integer>(r);
					current.add(aux);
					r.remove(j);
				}
			}

			resultado = new ArrayList<ArrayList<Integer>>(current);
		}

		return resultado;
	}

	public static void solucion(int pN, int pLongitud, Integer[] pArreglo)
	{
		ArrayList<ArrayList<Integer>> permutaciones = permutacionesArreglo(pArreglo);
		for(ArrayList<Integer> r: permutaciones)
		{
			int	costperm = revisarCostoCorte(r, pLongitud);
			if(costperm<costomin)
			{
				costomin = costperm;	
			}
		}
	}

	public static int revisarCostoCorte(ArrayList<Integer> pR, int pLongitud)
	{
		int costo = 0;
		if(estaOrdenadoAsc(pR)||estaOrdenadoDesc(pR))
		{
			for(int i =0; i<pR.size();i++)
			{
				if(i==0)
				{
					costo+=pLongitud;
					pLongitud -= pR.get(i);
				}
				else
				{
					costo+=pLongitud;
					pLongitud-=Math.abs(pR.get(i)-pR.get(i-1));
				}
			}
		}
		else
		{
			costo = revisarCostoCorteDesordenado(pR, pLongitud);
		}
		System.out.println(pR + "-" + costo);
		return costo;
	}
	
	public static int revisarCostoCorteDesordenado(ArrayList<Integer> pR, int pLongitud)
	{
		int costo = 0;
		int vmin = 0;
		int vmax = pLongitud;
		for(int i =0; i<pR.size();i++)
		{
			costo += pLongitud;
			pLongitud-=pR.get(i);
		}
		return costo;
	}
	
	public static boolean estaOrdenadoAsc(ArrayList<Integer> datos){
	    for(int i = 1; i < datos.size(); i++){
	        if(datos.get(i-1) > datos.get(i)){
	            return false;
	        }
	    }
	    return true;
	}
	
	public static boolean estaOrdenadoDesc(ArrayList<Integer> datos){
	    for(int i = 1; i < datos.size(); i++){
	        if(datos.get(i-1) < datos.get(i)){
	            return false;
	        }
	    }
	    return true;
	}

	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine());
		Integer[] secuencia = strToIntArr(br.readLine());

		br.close();

       
		ArrayList<Integer> aux = new ArrayList<Integer>();
		solucion(10000,n,secuencia);

		bw.write("Costo minimo " + costomin);   
		bw.flush();
		bw.close();
	}


}
