package shop.mtcoding.blogv2;

import org.junit.jupiter.api.Test;

public class LamdaTest {

    interface Calculator {
        int add(int a, int b);
    }

    @Test
    public void ex01_test(){
        Calculator cal = new Calculator() {
            @Override
            public int add(int a, int b) {
                return a+b;
            }
        };

        int result = cal.add(1,2);
        System.out.println(result);
    }

    @Test
    public void ex02_test(){
        Calculator cal = (a, b) -> a+b;

        int result = cal.add(1,2);
        System.out.println(result);
    }

    @Test
    public void ex03_test(){
        Calculator cal = (a, b) -> {
            // 여러줄을 적고 싶으면 중괄호적고 직접 return 하기
            return a+b;
        };

        int result = cal.add(1,2);
        System.out.println(result);
    }
}
