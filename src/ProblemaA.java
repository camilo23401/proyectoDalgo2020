import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problema A 
 * @authors Nicolás Ortega 201814515 & Camilo García 201821149
 * DALGO 2020-20
 */
public class ProblemaA {
	public static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));
	public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
	public static Integer[] strToIntArr(String s) {
		List<Integer> list = new ArrayList<>();
		Arrays.asList(s.split(" ")).forEach(s1 -> list.add(Integer.parseInt(s1)));
		return list.toArray(new Integer[0]);
	}

	public static int modulo(int x, int y) {
		return (x >= 0) ?  x%y : y - (-x)%y;
	}

	public static Integer[][] submatriz (Integer mat[][], int fila, int col, int n) {
		int i = 0;
		int j = 0;

		Integer[][] resp = new Integer[n][n];

		for (int x=0; x<mat[0].length; x++) {
			if (x != fila) {
				for (int y=0; y<mat[0].length; y++) {
					if (y != col) {		
						resp[i][j++] = mat[x][y];
						if (j == n) {
							j = 0;
							i++;
						}			
					}	
				}
			}
		}

		return resp;
	}

	public static Integer determinante(Integer mat[][], int n) {

		int resp = 0, signo = 1;
		if (n == 1) 
			return mat[0][0];


		for (int col=0; col<n; col++) {
			Integer[][] cof = submatriz(mat, 0, col, n-1);
			resp += mat[0][col]*determinante(cof,n-1)*signo;
			signo = -signo;
		}

		return resp;
	}


	public static Integer[][] adjunta (Integer[][] mat, int n, int p){

		Integer[][] resp = new Integer[n][n], cof;
		int signo = 1;

		for(int fila=0; fila<n; fila++) {
			for (int col=0; col<n; col++) {
				cof = submatriz(mat, fila, col, n-1);
				resp [col][fila] = modulo(determinante(cof, n-1) * signo, p);
				signo = -signo;
			}
			if (n%2 == 0) signo = -signo;
		}
		return resp;
	}

	public static void imprimirMatriz(int[][] mat) throws IOException{
		for (int i = 0; i < mat[0].length; i++) 
		{ 
			for (int j = 0; j < mat[0].length; j++) 
				bw.write(mat[i][j]+ " ");
			bw.write("\n"); 
		} 
		bw.flush();
	}

	/* Template code ends here. */

	public static int[][] inversa(Integer[][] mat, int n, int p){
		int d = modulo(determinante(mat, n), p);
		if (d == 0) {
			return null;		
		}

		Integer[][] adj = adjunta(mat, n, p);
		int[][] inversa = new int[n][n];

		//imprimirMatriz(adj);

		int detInv = d != 1 ? (1+p)/d : d;
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				inversa[i][j] = adj[i][j]*detInv;
			}
		}
		return inversa;
	}

	public static void main(String[] args) throws IOException {
		// Read an integer N and then an integer array:

		ArrayList<Integer[][]> matrices = new ArrayList<Integer[][]>();
		ArrayList<Integer> ns = new ArrayList<Integer>();
		ArrayList<Integer> ps = new ArrayList<Integer>();

		while (true) {
			Integer[] arr = strToIntArr(br.readLine());
			int n = arr[0];
			int p = arr[1];
			if (n == 0 && p == 0) break;
			Integer mat[][] = new Integer[n][n];
			for (int i=0; i<n; i++) {
				Integer[] fila = strToIntArr(br.readLine());
				for (int j=0; j<n; j++)
					mat[i][j] = fila[j];
			}

			ns.add(n);
			ps.add(p);
			matrices.add(mat);
		}

		for (int i=0; i<matrices.size(); i++) {
			Integer[][] mat = matrices.get(i);
			int n = ns.get(i);
			int p = ps.get(i);

			int[][] inversa = inversa(mat, n, p);

			if (inversa != null)
				imprimirMatriz(inversa);
			else 
				bw.write("X\n");
			bw.write("*\n");
			bw.flush();


		}

		br.close();
	}

}

