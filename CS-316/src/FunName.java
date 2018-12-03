import java.util.HashMap;

public class FunName 
{
   Id id;
   
   public FunName(Id i){
      id = i;
   }

   void printParseTree(String indent) {
      IO.displayln(indent + indent.length() + " <FunName> " + id.id);
   }
   
}