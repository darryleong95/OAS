package util.Exception;

public class InvalidBidException extends Exception{
     public InvalidBidException() {
    }
    
   public InvalidBidException(String msg){
       super(msg);
   }
}
