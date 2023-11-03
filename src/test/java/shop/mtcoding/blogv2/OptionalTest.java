package shop.mtcoding.blogv2;

import org.junit.jupiter.api.Test;

import java.util.Optional;

public class OptionalTest {

    @Test
    public void ex01_test(){
        // 선물박스 == Optional
        Optional<String> op = Optional.ofNullable(null);

        // 선물박스에서 값 꺼내기 (null 을 꺼냈음)
        String name = op.get();
        
        // 출력 (오류)
        System.out.println(name);
    }

    @Test
    public void ex02_test(){
        Optional<String> op = Optional.ofNullable(null);

        if(op.isPresent()){
            String name = op.get();
            System.out.println(name);
        }else{
            System.out.println("선물박스에 값이 없어요");
        }
       
    }

    @Test
    public void ex03_test(){
        Optional<String> op = Optional.of("토토");
        if(op.isPresent()){
            String name = op.get();
            System.out.println(name);
        }else{
            System.out.println("선물박스에 값이 없어요");
        }
    }

    @Test
    public void ex04_test() {
        Optional<String> op = Optional.ofNullable(null);

        String name =  op
                .orElseThrow(() -> new RuntimeException("이름 없음"));

        System.out.println(name);
    }

    @Test
    public void ex05_test() {
        Optional<String> op = Optional.ofNullable("토토");
        

        String name =  op
                .orElseThrow(() -> new RuntimeException("이름 없음"));

        System.out.println(name);
    }

}
