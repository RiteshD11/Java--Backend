package com.springWebApp.springWebApp;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class homecontroller {


    @RequestMapping("/")
    public String home(){
        System.out.println("Home controller is  called ");
        return "home.jsp";
    }

//    now here lets handles the add request


    // by using servlet

    /*
     @RequestMapping("add")
    public  String add(HttpServletRequest req ){

// for to send the data between the pages you has to use sessions

      int num1= Integer.parseInt(req.getParameter("num1"));
        int num2= Integer.parseInt(req.getParameter("num2"));
        int result=num1+num2;
        System.out.println("Result "+result);
        return "result.jsp";
    }

     */


    /*
    @RequestMapping("add")
    public  String add(HttpServletRequest req , HttpSession session){

// for to send the data between the pages you has to use sessions

        int num1= Integer.parseInt(req.getParameter("num1"));
        int num2= Integer.parseInt(req.getParameter("num2"));
        int result=num1+num2;
        session.setAttribute("result",result);

        System.out.println("Result "+result);
        return "result.jsp";
    }

     */
    // lets do above in the same with another way
    /*
    @RequestMapping("add")
    public String add(int num1,int num2 ,HttpSession session){ // make sure there should be same name of the variables as of the sending name in the form

        int result=num1+num2;
        System.out.println("Result is "+result);
        session.setAttribute("result",result);
        return "result.jsp";
    }
*/
//    if you don't want the same name as of the varible name in form you can use the @Requestparam

    // here we can do remove the session  thing using the model also
    /*
    @RequestMapping("add")
    public String add(@RequestParam("num1") int n1, @RequestParam("num2") int n2 , HttpSession session){ // make sure there should be same name of the variables as of the sending name in the form

        int result=n1+n2;
        System.out.println("Result is "+result);
        session.setAttribute("result",result);
        return "result.jsp";
    }
    */



    @RequestMapping("add")
    public String add(@RequestParam("num1") int num1, @RequestParam("num2") int num2 , Model model){ // make sure there should be same name of the variables as of the sending name in the form

        int result=num1+num2;
        System.out.println("Result is "+result);
            model.addAttribute("result",result);
        return "result.jsp";
    }

//    @RequestMapping("addAlien")
//    public String addAlien(@RequestParam("num1") int num1,@RequestParam("num2") String num2,Model model){
//
//        Alien obj=new Alien();
//        obj.setNum1(num1);
//        obj.setNum2(num2);
//        model.addAttribute("obj",obj);
//        return "result.jsp";
//    }

//    @RequestMapping("addAlien")
//    public String addAlien(Alien obj,Model model){
//        model.addAttribute("obj",obj);
//        return "result.jsp";
//    }

    @ModelAttribute("course")
    public  String courseName(){
        return "Spring boot";
    }
    @RequestMapping("addAlien")
    public String addAlien(@ModelAttribute("obj") Alien alien){
        return "result.jsp";
    }

}
