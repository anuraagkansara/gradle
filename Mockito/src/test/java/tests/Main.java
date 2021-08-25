package tests;

import com.mphasis.Math;
import com.mphasis.Portfolio;
import com.mphasis.entities.Stock;
import com.mphasis.services.CalculatorService;
import com.mphasis.services.StockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class Main {

    @InjectMocks
    Portfolio portfolio;
    Math maths;


    @Mock
    StockService stockService;
    CalculatorService calculatorService;



//    public static void main(String[] args){
//        Main main = new Main();
//        main.setUp();
//        System.out.println(main.testMarketValue());
//    }

//    public void setUp(){
//        portfolio = new Portfolio();
//
////        stockService = mock(StockService.class);
//
//        portfolio.setStockService(stockService);
//    }
    @Test
    public void testMarketValue(){
        List<Stock> stocks = new ArrayList<Stock>();
        Stock googleStock = new Stock("1","Google",10);
        Stock microsoftStock = new Stock("2","Microsoft",10);

        stocks.add(googleStock);
        stocks.add(microsoftStock);
        portfolio.setStocks(stocks);


        when(stockService.getPrice(googleStock)).thenReturn(50.00);   //Mocking the method,
        when(stockService.getPrice(microsoftStock)).thenReturn(1000.00);

        when(calculatorService.add(1,2)).thenReturn(3.0);
        double addResult = maths.add(1,2);

        assertEquals(3.0,addResult,1);

        double marketValue = portfolio.getMarketValue();
//        return marketValue == 10500.0;
        assertEquals(10500.0,marketValue,1);
    }

}
