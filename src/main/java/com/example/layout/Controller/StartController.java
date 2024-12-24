package com.example.layout.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Random;

@Controller
public class StartController {
    @GetMapping("/")
    public String start(){
        return "index";
    }

    @GetMapping("/cals")
    public String calsForm(){
        return "cals";
    }

    @PostMapping("/cals")
    public String calsProc(Integer num1, Integer num2, String operator, Model model){
        Integer result =0;
        String oper="";

        if (operator.equals("add")){
            result = num1 + num2;
            oper = "+";
        } else if(operator.equals("sub")){
            result = num1-num2;
            oper = "-";
        } else if(operator.equals("mul")){
            result = num1*num2;
            oper ="*";
        } else if(operator.equals("div")){
            result = num1/num2;
            oper="/";
        }

        //변수들을 개별적으로 전달하는 방법
        model.addAttribute("num1", num1);
        model.addAttribute("num2", num2);
        model.addAttribute("oper", oper);
        model.addAttribute("result", result);

        //Map<String....>를 이용해서 하나의 변수로 모든값을 전송하는 방법
        //DTO를 이용해서 그룹으로 전달하는 방법

        return "index";
    }

    @GetMapping("/game")
    public String gameForm(){
        return "game";
    }

    @PostMapping("/game")
    public String gameProc(Integer game, String name, Model model) {
        //난수 컴퓨터가 임의의 수를 발생(0~0. 999999999999) 사이의 수에서
        //자바와 파이썬에서는 정수값으로 난수를 구하는 함수를 제공
        Random rand = new Random(); //난수 클래스 생성, C언어 int(random()*범위) : 0~범위전 +1=>1부터 범위
        int computer = rand.nextInt(3) + 1; //0~2까지 중 발생, 1~3

        //가위바위보 판별(가위(1)<바위(2), 바위(2)<보(3), 보(3)<가위(1))
        String win = "컴퓨터가 이겼습니다."; //가상의 결과를 컴퓨터가 이긴 경우로 설정

        if (game == 1) { //플레이어가 가위
            if (computer == 3) {
                win = name + "가 이겼습니다."; // 플레이어가 이긴경우
            }
        } else if (game == 2) { // 플레이어가 바위
            if (computer == 1) { //컴퓨터 가위
                win = name + "가 이겼습니다.";
            }
        } else if (game == 3) { //플레이어가 보
            if (computer == 2) { //컴퓨터 바위
                win = name + "가 이겼습니다.";
            }
        }
        if (game == computer) {
            win = "비겼습니다.";
        }
        model.addAttribute("win", win);
        return "index";
    }

    @GetMapping("/member")
    public String memberForm(){
        return "member";
    }


}
