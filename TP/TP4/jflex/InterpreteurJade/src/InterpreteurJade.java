import java.io.*;

/** 
 * Classe  InterpreteurJade
 * @author Djebien Tarik
 * @version avril 2010
 */
 
public class InterpreteurJade {
	// La fenetre o� dessiner
	private FenetreJade fenetre;
	// L'analyseur syntaxique d�crit dans le fichier "analyseurJade.lex"
	private Yylex analyseur;
	
	/**
	 * Constructeur
	 */
	public InterpreteurJade(){
		this.fenetre = new FenetreJade();
		// cr�er un Yylex qui va prendre ses entr�es au clavier
		this.analyseur = new Yylex(new BufferedReader(new InputStreamReader(System.in)));
	}
	
	/**
	 * R�cup�re la prochaine unit� lexicale lue par l'analyseur lexical.
	 */
	public Yytoken lireProchaineUniteLexicale() throws Exception {
		return analyseur.yylex();
	}
	
	/**
	 * Traitement de l'unit� lexicale courante lue par l'analyseur lexical.
	 */
	public void traiterUniteLexicale(Yytoken ul) throws Exception {
		int position = ul.getToken().ordinal();
		
		switch (position){
		  
		  /* INSTTRUCTIONS ELEMENTAIRES */
		  
		  // cas "nord"
		  case 0 : this.fenetre.nord();
		  break;
		  // cas "sud"
		  case 1 : this.fenetre.sud();
		  break;
		  // cas "est"
		  case 2 : this.fenetre.est();
		  break;
		  // cas "ouest"
		  case 3 : this.fenetre.ouest();
		  break;
		  // cas "lever"
		  case 5 : this.fenetre.lever();
		  break;
		  // cas "baisser"
		  case 6 : this.fenetre.baisser();
		  break;
		  
		  // cas "origine(x,y)"
		  case 7 :
		  { /*
		      origine  = ULMotClef = UL1
		         x     = ULEntier  = UL2
			 y     = ULEntier  = UL3
		    */
		    
		    // ici origine( est deja lu dans UL1 car on se trouve dans le cas 7
		    
		    // on passe donc a l'UL suivante : UL2
		    Yytoken temp = this.lireProchaineUniteLexicale(); 
		    int x = ((Number)temp.getValue()).intValue();
		    
		    // puis on passe a l'UL finale : UL3
		    temp = this.lireProchaineUniteLexicale(); 
		    int y = ((Number)temp.getValue()).intValue();
		    
		    // on execute la methode public void origine(Point p) de FenetreJade
		    // avec les coordonn�es lues.
		    this.fenetre.origine(new java.awt.Point(x,y));
		  }
		  break;
		  
		  // cas "pas n"
		  case 8 : 
		  {
		   Yytoken temp = this.lireProchaineUniteLexicale();
		   int pasValeur = ((Number)temp.getValue()).intValue();
		   this.fenetre.pas(pasValeur);
		  }break;
		  
		  // cas "n fois direction"
		  case 10 :
		  { int n = ((Number)ul.getValue()).intValue();
		    Yytoken temp = this.lireProchaineUniteLexicale();
		    temp = this.lireProchaineUniteLexicale();
		    int direction = temp.getToken().ordinal();
		    switch (direction){
		       case 0 : for(int i = 0; i < n ; i++) this.fenetre.nord();break;
		       case 1 : for(int i = 0; i < n ; i++) this.fenetre.sud();break;
		       case 2 : for(int i = 0; i < n ; i++) this.fenetre.est();break;
		       case 3 : for(int i = 0; i < n ; i++) this.fenetre.ouest();break;
		    }
		  } 
		  break;
		  
		  /* INSTRUCTIONS COMPLEXES */
		  
		  
		  
		
		}//switch
		
		System.out.println(ul.toString());
	}
	
	/**
	 * La classe principale de l'interpr�teur Jade.
	 */
	public static void main(String[] args){
		InterpreteurJade interpreteur = new InterpreteurJade();
		System.out.println("\nBienvenue dans l'interpr�teur Jade !\n");
		Yytoken ul = null;
		try{
			while (ul == null || ul.getToken() != Token.eof){
				if(ul != null){
					if(ul.getToken() == Token.erreur){
						System.out.println("Erreur : la valeur entr�e n'est pas une commande Jade valide.");
					} else {
						interpreteur.traiterUniteLexicale(ul);
					}
				}
				System.out.print("Jade > ");
				ul = interpreteur.lireProchaineUniteLexicale();
			}
			System.out.println("\n\nMerci d'avoir utilis� l'interpr�teur Jade !\n");
			System.exit(1);
		} catch(Exception e){
			System.out.println("\n\nUne erreur impr�vue est survenue.\nL'interpr�teur Jade doit se fermer.");
			System.exit(0);
		}
	}
}
