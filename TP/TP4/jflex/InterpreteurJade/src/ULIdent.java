public class ULIdent implements Yytoken {

  private Token motcletoken;
  private String valeur;
  
  public ULIdent(String pval){
   this.motcletoken = Token.identificateur;
   this.valeur = pval;
  }
  public Token getToken(){ 
  return this.motcletoken;
  } 
  public Object getValue(){ 
  return this.valeur;
  } 
  public String toString(){ 
  return "ULIdent "+this.getToken().name()+" "+this.getValue();
  } 

}// class ULIdent
