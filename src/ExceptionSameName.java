public class ExceptionSameName extends Exception{
    ExceptionSameName(String message){
        super(message);
    }
    ExceptionSameName(Product product){
        System.err.println("Існує продукт з такою назвою: "+product.getName());
    }
    ExceptionSameName(ProductGroup productGroup){
        System.err.println("Існує група продуктів з такою назвою: "+productGroup.getName());
    }
}
