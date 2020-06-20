package Main;

/**
 * Класс помилка для імен, які повторюються
 */
public class ExceptionSameName extends Exception{
    /**
     * Звичайна помилка
     * @param message що написати
     */
    ExceptionSameName(String message){
        super(message);
    }

    /**
     * Помилка з повтором імені продукта
     * @param product продукт
     */
    public ExceptionSameName(Product product){
        super("Існує продукт з такою назвою: "+product.getName());
    }

    /**
     * Помилка з повтором імені групи продукта
     * @param productGroup група продукта
     */
    public ExceptionSameName(ProductGroup productGroup){
       super("Існує група продуктів з такою назвою: "+productGroup.getName());
    }
}
