import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problema C
 * @authors Nicolas Ortega 201814515 & Camilo Garcia 201821149
 * DALGO 2020-20
 */
public class ProblemaC {

	public static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));
	public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
	private static int menosPasos;
	private static int tamano;
	private static boolean[] revisoIzq;
	private static boolean[] revisoDer;

	public static Integer[] strToIntArr(String s) {
		List<Integer> list = new ArrayList<>();
		Arrays.asList(s.split(" ")).forEach(s1 -> list.add(Integer.parseInt(s1)));
		return list.toArray(new Integer[0]);
	}

	public static void solucion(int pLongitudCamino, int pLongitudTablon, Integer[] pCamino, int pNumPasos, int pInicio)
	{
		int i = pInicio;
		if (pCamino[i]==-1) //Pierde o debe usar tablon
		{
			if(pLongitudTablon>=1)
			{
				pCamino[i]=1;
				solucion(pLongitudCamino, pLongitudTablon-1, pCamino, pNumPasos,pInicio);
			}
		}
		if (pCamino[i]==0) //Gana
		{
			if(pNumPasos<menosPasos)
			{
				menosPasos = pNumPasos;
			}
		}
		if(0<pCamino[i] && pCamino[i]<pLongitudCamino)
		{
			if(i>pCamino[i]&&!revisoIzq[i]) //Puede para atras
			{
				revisoIzq[i]=true;
				solucion(pLongitudCamino, pLongitudTablon, pCamino, pNumPasos+1,i-pCamino[i]);
			}
			if(i+pCamino[i]<=pLongitudCamino&&!revisoDer[i]) //Puede hacia delante
			{
				revisoDer[i]=true;
				solucion(pLongitudCamino, pLongitudTablon, pCamino, pNumPasos+1,i+pCamino[i]);
			}
		}
	}

	public static void main(String[] args) throws IOException {

		ArrayList<Integer[]> aux = new ArrayList<Integer[]>();
		ArrayList<Integer[]> secuencias = new ArrayList<Integer[]>();

		String linea = br.readLine();
		while(!linea.equals("0 0"))
		{
			Integer[] info = strToIntArr(linea);
			aux.add(info);
			linea = br.readLine();
			Integer[] secuencia = strToIntArr(linea);
			secuencias.add(secuencia);
			linea = br.readLine();
		}
		br.close();

		for(int i = 0; i<aux.size();i++)
		{
			int longitud = aux.get(i)[0];
			int longitudTablon = aux.get(i)[1];
			tamano = longitud;
			revisoDer = new boolean[tamano];
			revisoIzq = new boolean[tamano];
			Integer[] camino = secuencias.get(i);
			menosPasos = 2147483647;
			solucion(longitud,longitudTablon,camino,0,0);
			if(menosPasos!=2147483647)
			{
				bw.write(menosPasos+"\n");
				bw.flush();
			}
			else
			{
				bw.write("-1"+"\n");
				bw.flush();
			}
		}
		bw.close();
	}
}
