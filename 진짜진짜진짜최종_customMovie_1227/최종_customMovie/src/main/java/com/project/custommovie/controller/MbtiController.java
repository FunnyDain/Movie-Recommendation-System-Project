
package com.project.custommovie.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


@Controller
//@ComponentScan(basePackages = "com.project.custommovie")
@RequiredArgsConstructor
public class MbtiController {
    //생성자 주입
    @GetMapping("/ranking/ranking")
    public String rankinghome() {
        return "/ranking/ranking";
    }



    @RequestMapping(value = "/IS", method = RequestMethod.GET)
    public ModelAndView Test() {
        ModelAndView mav = new ModelAndView();
        String url = "http://192.168.0.12:5000/IS";
        String sb = "";
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();


            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

            String line = null;

            while ((line = br.readLine()) != null) {
                sb = sb + line + "\n";
            }
            System.out.println("========br======" + sb.toString());
            if (sb.toString().contains("ok")) {
                System.out.println("test");

            }
            br.close();

            System.out.println("" + sb.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mav.addObject("test1", sb.toString()); // "test1"는 jsp파일에서 받을때 이름,
        //sb.toString은 value값(여기에선 test)
        mav.addObject("fail", false);
        mav.setViewName("/mbti/test");   // jsp파일 이름
        return mav;

    }

}





