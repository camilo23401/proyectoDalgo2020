import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ProblemaB {

	public static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));
	public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
	public static int costomin = 10000;

	public static ArrayList<ArrayList<Integer>> permutacionesArreglo(int[] pArreglo) {
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

	public static void solucion(int pN, int pLongitud, int[] pArreglo)
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
		// Read an integer N and then an integer array:
		//int n = Integer.parseInt(br.readLine());
		//Integer[] arr = strToIntArr(br.readLine());

		// Example of doing an output of n in a new line:
		//bw.write(n + "\n");
		//bw.flush();

		//bw.close();
		//br.close();


		int[]secuenciaPrueba = {3,8,10};
		ArrayList<Integer> aux = new ArrayList<Integer>();
		//solucion(10000,20,secuenciaPrueba);
		//System.out.println(costomin);
		solucion(10000,20,secuenciaPrueba);

		//costoIzqDer(100, 20, secuenciaPrueba);
		//costoDerIzq(100, 20, secuenciaPrueba);
		System.out.println("Costo mínimo " + costomin);   
	}


}
