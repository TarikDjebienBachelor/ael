public class ULMotClef implements Yytoken {

  private Token motcletoken;
  private Object valeur;
  
  public ULMotClef(Token ptoken){
   this.motcletoken = ptoken;
   this.valeur = null;
  }
  public Token getToken(){ 
  return this.motcletoken;
  } 
  public Object getValue(){ 
  return this.valeur;
  } 
  public String toString(){ 
  return "ULMotClef "+this.getToken().name()+" "+this.getValue();
  } 

}// class ULMotClef
