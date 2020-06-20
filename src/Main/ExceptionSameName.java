package Main;

public class ExceptionSameName extends Exception{
    ExceptionSameName(String message){
        super(message);
    }
    public ExceptionSameName(Product product){
        super("Існує продукт з такою назвою: "+product.getName());
    }
    ExceptionSameName(ProductGroup productGroup){
       super("Існує група продуктів з такою назвою: "+productGroup.getName());
    }
}
