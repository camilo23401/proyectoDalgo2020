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
	public static int costomin;

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

	public static int solucion(int pN, int pLongitud, Integer[] pArreglo)
	{
		int costomin = 2147483647;
		ArrayList<ArrayList<Integer>> permutaciones = permutacionesArreglo(pArreglo);
		for(ArrayList<Integer> r: permutaciones)
		{
			int	costperm = revisarCostoCorte(pLongitud,r);
			if(costperm<costomin)
			{
				costomin = costperm;	
			}
		}

		return costomin;
	}

	public static int revisarCostoCorte(int longitud, ArrayList<Integer> cortes)
	{
		if (cortes.size() == 0)
			return 0;

		int costo = longitud;
		int longIzq = cortes.get(0);
		int longDer = longitud - longIzq;

		ArrayList<Integer> cortesIzq = new ArrayList<Integer>();
		ArrayList<Integer> cortesDer = new ArrayList<Integer>();
		for (int i=1; i<cortes.size(); i++) {
			Integer cut = cortes.get(i);
			if (cut < longIzq)
				cortesIzq.add(cut);
			else 
				cortesDer.add(cut-longIzq);
		}

		costo += revisarCostoCorte(longIzq, cortesIzq) + revisarCostoCorte(longDer, cortesDer);
		return costo;

	}

	public static void main(String[] args) throws IOException {

		ArrayList<Integer> ns = new ArrayList<Integer>();
		ArrayList<Integer[]> secuencias = new ArrayList<Integer[]>();
		String linea = br.readLine();
		while(!linea.equals("0"))
		{
			int n = Integer.parseInt(linea);
			ns.add(n);
			linea = br.readLine();
			Integer[] secuencia = strToIntArr(linea);
			secuencias.add(secuencia);
			linea = br.readLine();
		}
		br.close();

		for(int i=0;i<ns.size();i++)
		{
			bw.write(solucion(10000,ns.get(i),secuencias.get(i))+"\n");
			bw.flush();
		}
		bw.close();
	}


}
