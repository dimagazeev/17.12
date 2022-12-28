package api.Kucoin;

import java.util.Comparator;

public class TicketComparator implements Comparator<TickerData> {
    /** создали отдельный класс, чтоб каждый раз не писать новый меиод для сравнения переменных
     *  далее, мы из 2х объектов получили % изменения -> преобразовали в тип double с помощью метода "parseDouble"
     *  вызвали метод ".compare" который сравнит объекты "o1 и o2"*/
    @Override
    public int compare(TickerData o1, TickerData o2) {
        double result = Double.compare(Double.parseDouble(o1.getChangeRate()),
                Double.parseDouble(o2.getChangeRate()));
                /* .compare метод для сравнения двух переменных
                   return (int) result - возвращаем полученных резудльтат
                * */
        return (int) result;
    }
}
