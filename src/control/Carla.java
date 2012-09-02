package control;

/**
 * 
 * @author mergen
 */
public class Carla {

	public static boolean DEBUG = false;
	int b[][];
	int i;
	int j;
	int coord = 0;
	int maxCommonSize = 0;
	int sumCommonSizes = 0;
	int overlap = 0;
	int overlapX = 0;
	int overlapY = 0;
	String s1;
	String s2;
	int token = 2;
	double weight1 = 0.0;
	double weight2 = 1.0;

	public Carla() {
	}

	public Carla(int token) {
		this.token = token;
	}

	/** Creates a new instance of Nsubs */
	public Carla(int token, double weight1, double weight2) {
		this.token = token;
		this.weight1 = weight1;
		this.weight2 = weight2;
	}

	/**
	 * Bottom up dynamic programming algorithm that computes the longest common
	 * subsequence of two strings.
	 * 
	 * @param String
	 *            s1 The first string
	 * @param String
	 *            s2 The second string
	 * @param int i The length of the first string
	 * @param int j The length of the second string
	 * @param char c[][] An array that can be used to reconstruct the longest
	 *        common subsequence
	 * @return static int The length of the longest common subsequence
	 */
	private int getMatching(String s1, String s2, int i, int j) {

		b = new int[i + 1][j + 1];
		coord = 0;
		maxCommonSize = 0;
		sumCommonSizes = 0;
		this.s1 = s1;
		this.s2 = s2;
		overlap = 0;
		overlapX = 0;
		overlapY = 0;

		for (int k = 0; k < i; k++) {
			// if (k==2) break;
			for (int n = 0; n < j; n++) {
				if (s1.charAt(k) == s2.charAt(n)) {
					int score = 0;
					if (((k - 1) >= 0) && ((n - 1) >= 0)) {
						score = 1 + b[k - 1][n - 1];
					} else {
						score = 1;
					}

					if (score >= token) {
						b[i][n] = b[i][n] + 1;
						b[k][j] = b[k][j] + 1;
						if (b[k][j] > 1 || b[i][n] > 1) {
							if (b[k][j] > 1) {
								overlapX++;
								overlap++;
							}
							if (b[i][n] > 1) {
								overlapY++;
								overlap++;
							}
						}

						if (score == token) {
							sumCommonSizes = sumCommonSizes + token;
							for (int g = 1; g < token; g++) {
								b[i][n - g] = b[i][n - g] + 1;
								b[k - g][j] = b[k - g][j] + 1;
								if (b[k - g][j] > 1 | b[i][n - g] > 1) {
									if (b[k - g][j] > 1) {
										overlapX++;
										overlap++;
									}
									if (b[i][n - g] > 1) {
										overlapY++;
										overlap++;
									}

								}
							}

						} else {
							sumCommonSizes = sumCommonSizes + 1;
						}
					}

					if (score > maxCommonSize) {
						coord = k;
						maxCommonSize = score;
					}
					b[k][n] = score;
				}
			}
		}
		// print(s1,s2);
		return 1;
	}

	public String getCommonSubstring() {
		return s1.substring(coord - maxCommonSize + 1, coord + 1);
	}

	public int getCommonSubstringLength() {
		return maxCommonSize;
	}

	public void print(String s1, String s2) {

		System.out.print("     ");
		for (int y1 = 0; y1 < s2.length(); y1++) {
			System.out.print("  " + s2.charAt(y1) + "  |");
		}
		System.out.println("");
		for (int y = 0; y < s2.length() + 1; y++) {
			System.out.print("------");
		}

		System.out.println("");
		for (int x = 0; x < s1.length(); x++) {
			System.out.print("  " + s1.charAt(x) + "  ");

			for (int y = 0; y < s2.length(); y++) {
				System.out.print("  " + b[x][y] + "  |");
			}
			System.out.println("");
			for (int y = 0; y < s2.length() + 1; y++) {
				System.out.print("------");
			}
			System.out.println("");
		}

		// System.out.println("");

	}

	public double getScore(String s1, String s2) {
		int size1 = s1.length();
		int size2 = s2.length();
		s1 = s1.toUpperCase();
		s2 = s2.toUpperCase();

		int maxSize = (size1 > size2) ? size1 : size2;
		int minSize = (size1 < size2) ? size1 : size2;

		getMatching(s1, s2, size1, size2);

		overlap = (overlapX > overlapY) ? overlapX : overlapY;

		// //System.out.println("common "+sumCommonSizes);
		double score = (double) (sumCommonSizes - overlap)
				/ ((maxSize * weight1) + (minSize * weight2));

		if (DEBUG) {
			print(s1, s2);
		}
		// return (score>1)?1:score;
		return score;
	}

	public double getScore(String s1, String s2, String expected) {
		int exp = Integer.valueOf(expected).intValue();
		// System.out.println("Comparing Strings: "+s1+" and "+s2);
		double result = getScore(s1, s2);
		// System.out.println("Expected: "+expected+" found "+common);

		int common1 = sumCommonSizes - overlap;

		int overlap2 = (overlapX > overlapY) ? overlapX : overlapY;
		int common2 = sumCommonSizes - overlap2;
		// if (exp!=common)
		{
			/*
			 * System.out.println("sumcommon "+ sumCommonSizes);
			 * System.out.println("overlap "+ overlap);
			 * System.out.println("overlapX "+ overlapX);
			 * System.out.println("overlapY "+ overlapY);
			 * 
			 * System.out.println("Comparing Strings: "+s1+" and "+s2);
			 * System.out.println("(SUM)Expected: "+expected+" found "+common1);
			 * System.out.println("(MAX)Expected: "+expected+" found "+common2);
			 */
			// System.out.println(s1+" , "+s2+" , "+token+" , "+expected+" , "+common2);
			//System.out.println(token + " , " + expected + " , " + common2);
		}

		return result;
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		String s1 = "executando o teste";
		String s2 = "teste executado";

		// Carla_2 carla = new Carla_2(3,0.0,1.0);
		// Carla_2.DEBUG = false;

		String[][] strings = {
				// {"11111111000111111111001000000000011000000000111000000001111111111011111111111111100000001111100000010000000000111000000001111110000011111110000"
				// ,"11110000000111110000001000000000011000000000111000000001111110000011111110000111111110001111111110010000000000111000000001111111111011111111111","6"}//6

				/*
				 * {"ocaxora","ocacaxoora","6"},//6
				 * {"ocaxoa","ocacaxoxoa","6"},//6
				 * {"ocacor","ocacaacor","6"},//6 {"acacaxa","acaxaca","6"},//6
				 * {"acaxaaca","acacaxa","7"},//7 {"ocaxaoca","ocacaxa","7"},//7
				 * {"acatoaca","acaatoa","6"},//6 {"ocaxexo","sexoca","3"},//3
				 * {"sexoca","ocaxexo","3"},//3 {"focaoca","ocafoc","6"},//6
				 * {"focaoca","fococa","6"},//6 {"foolilif","fooolifoo","6"},//6
				 * {"focalcri","focrical","6"},//6
				 * {"focalcri","focrica","3"},//3
				 * {"beapatricia","patybeatriz","6"},//6
				 * {"beapatri","patbeatri","6"},//6
				 * {"macacataca","atacaamacaca","10"},//10
				 * {"axacax","caxac","4"},//4 {"aaabbbabb","aaaabb","6"},//6
				 * {"aaabbbabb","bbaaabb","6"},//6
				 * {"aaabbbabb","bbaabb","4"},//5
				 * {"MMMAZZAMBYZAM","MMMBYZBBMAZZBM","9"},
				 * {"MMMBYZBBMAZZBM","KLMAUVAMBYVGF","3"},
				 * {"KLMBYVJBMAUVGFDEC","KLMAUVAMBYVGFDEC","14"},
				 * {"ruachamruaz","semruax","4"},
				 * {"acacaxaacacaxa","acaxacaacaxaca","12"},
				 * {"abcdefghijabcghi","abcXcdeXefgXghiXfgh","12"},
				 * {"abcdefabcabc","abcXcdeXefa","9"},
				 * {"pacacaxa","pacaxaca","6"}
				 */
				{ "nomeautor", "nomedoautor", "9" },// 6
				{ "nomeautor", "nome_do_autor", "9" },// 6
				{ "nomeautor", "autornome", "9" },// 6
				{ "nomeautor", "autor_nome", "9" },// 6
				{ "nomeautor", "nomeautor", "9" },// 6

				{ "nomedoautor", "nomeautor", "9" },// 6
				{ "nomedoautor", "nome_do_autor", "9" },// 6
				{ "nomedoautor", "autornome", "9" },// 6
				{ "nomedoautor", "autor_nome", "9" },// 6
				{ "nomedoautor", "nomedoautor", "11" },// 6

				{ "nome_do_autor", "nomedoautor", "9" },// 6
				{ "nome_do_autor", "nomeautor", "9" },// 6
				{ "nome_do_autor", "autornome", "9" },// 6
				{ "nome_do_autor", "autor_nome", "9" },// 6
				{ "nome_do_autor", "nome_do_autor", "13" },// 6

				{ "autornome", "nomedoautor", "9" },// 6
				{ "autornome", "nome_do_autor", "9" },// 6
				{ "autornome", "nomeautor", "9" },// 6
				{ "autornome", "autor_nome", "9" },// 6
				{ "autornome", "autornome", "9" },// 6

				{ "autor_nome", "nomedoautor", "9" },// 6
				{ "autor_nome", "nome_do_autor", "9" },// 6
				{ "autor_nome", "autornome", "9" },// 6
				{ "autor_nome", "nomeautor", "9" },// 6
				{ "autor_nome", "autor_nome", "10" },// 6
				{ "lach lan stonean", "lachl an rypan", "10" },// 6
		};

		/*
		 * for (int x=1;x<41;x++) for (int index=0;index<strings.length;index++)
		 * { Carla1 carla = new Carla1(x,0.0,1.0); Carla1.DEBUG = false; double
		 * score = carla.getScore(strings[index][1],strings[index][0],
		 * strings[index][2]); }
		 */

		Carla carla = new Carla(2, 0.5, 0.5);
		Carla.DEBUG = false;

		for (int index = 0; index < strings.length; index++) {
			double score = carla.getScore(strings[index][1], strings[index][0],
					strings[index][2]);
			System.out.println(score);
		}

	}
}
